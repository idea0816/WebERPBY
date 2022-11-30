// for lbzls.html

$(document).ready(function () {

    // 設定確認視窗中的按鈕文字、每個JS都要加、不然不會有作用
    alertify.set({
        labels: {
            ok: "确认",
            cancel: "放弃"
        }
    });

    // 預存類別編號、為了改變資料時可以比對、預存時機在按下「修改」鍵時
    let templbzl = [];

    // Table Click to  Get Data
    $("#lbTable tr").click(function () {
        // button Control enabled
        $("#newL").attr("disabled", true);
        $("#delL").attr("disabled", false);
        $("#buttonAdd").attr("disabled", false);
        $("#buttonQuit").attr("disabled", false);

        let tr = $(this);
        let td = tr.find("td");

        // Get lb
        $("#textlb").val(td[0].innerText);
        $("#textzwsm").val(td[1].innerText);
        $("#textbz").val(td[2].innerText);
        // 移除舊有表格
        $("#lbzls_getList td").parent().remove();/*加上parent()避免出現空白格*/

        // Get lbzls Data
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/lbzls/getlbzlsList/" + td[0].innerText,
            // data: JSON.stringify(params), // 因為jQuery安全性升高、所以要這樣寫
            dataType: "json",
            success: function (lbzlsList) {
                $.each(lbzlsList, function (key, value) {

                    $("#lbzls_getList").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                        "<td>" + value.lbdh + "</td>" +
                        "<td>" + value.zwsm + "</td>" +
                        "<td>" + value.bz + "</td>" +
                        "<td>" + value.bz1 + "</td>" +
                        "<td class='delCol' style='display: none;'>" +
                        "<input class='delColButton' type='button' value='Del' style='font-size: 0.75rem; width: 2rem;' />" +
                        "</td>" +
                        "</tr>");
                });
            },
            beforeSend: function () {
                $.blockUI();
            },
            complete: function () {
                $.unblockUI();
            },
            error: function () {
                alert("未取得类别明细资料！");
            }

        });
    });

    // Elements Control
    $("#textlb").attr("disabled", true);
    $("#textzwsm").attr("disabled", true);
    $("#textbz").attr("disabled", true);

    // CRUD button
    $("#delL").attr("disabled", true);
    $("#buttonAdd").attr("disabled", true);
    $("#buttonDel").attr("disabled", true);
    $("#buttonSave").attr("disabled", true);
    $("#buttonQuit").attr("disabled", true);

    // 新建主類別
    $("#newL").click(function () {
        $("#textlb").attr("disabled", false);
        $("#textzwsm").attr("disabled", false);
        $("#textbz").attr("disabled", false);
        $("#buttonAdd").attr("disabled", false);
        $("#buttonQuit").attr("disabled", false);

        $("#buttonAdd").val("添加");
        // $("#lbTable").hide(1000);
    });

    // 刪除類別
    $("#delL").click(function () {

        alertify.confirm("确认删除类别：" + $("#textlb").val() + " ? (类别相关资料全部删除、无法回复）", function (e) {
            if (e) {
                // OK、送出DELETE
                let dellb = $("#textlb").val();
                $.ajax({
                    type: "DELETE",
                    contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                    url: "/lbzls/deletelbzl/",
                    data: $("#textlb").val(),
                    dataType: "text",
                    success: function (message) {
                        $("#content").load("lbzls");
                        alertify.success("删除成功");
                    },
                    beforeSend: function () {
                        $.blockUI();
                    },
                    complete: function () {
                        $.unblockUI();
                    },
                    error: function () {
                        alert("删除出问题、请检查资料");
                    }
                });
            }
            else {
                alertify.success("放弃删除");
            }
        });
    });

    // buttonAdd
    $("#buttonAdd").click(function () {
        $("#buttonDel").attr("disabled", false);
        $("#buttonSave").attr("disabled", false);
        $("#buttonQuit").attr("disabled", false);

        if ($("#buttonAdd").val() == "修改") {
            $("#buttonAdd").val("添加");
            $("#textlb").attr("disabled", false);
            $("#textzwsm").attr("disabled", false);
            $("#textbz").attr("disabled", false);

            // 預存資料
            templbzl = [];
            // 預存目前的类别编号 & 类别项目 & 说明
            templbzl = { lb: $("#textlb").val(), zwsm: $("#textzwsm").val(), bz: $("#textbz").val() };
            // console.log(templbzl);
        } else {
            $(".delCol").hide(1000);
            $("#lbzlTable").show(1000);
        };
    });

    // buttonDel
    $("#buttonDel").click(function () {
        $("#lbzlTable").hide(1000);
        $(".delCol").show(1000);

    });

    // buttonSave
    $("#buttonSave").click(function () {

        let rows = $("#lbzls_getList tr");
        let lbzlsData = [];
        for (let i = 1, len = rows.length; i < len; i++) {
            let lbdh = $(rows[i]).children()[0].innerText;
            let zwsm = $(rows[i]).children()[1].innerText;
            let bz = $(rows[i]).children()[2].innerText;
            let bz1 = $(rows[i]).children()[3].innerText;

            // 取得所有類別資料明細
            lbzlsData.push({ lbdh: lbdh, zwsm: zwsm, bz: bz, bz1: bz1 });
        }

        if ($("#textlb").val() == "") {
            alertify.alert("请输入类别编号!!");
        } else if ($("#textzwsm").val() == "") {
            alertify.alert("请输入类别项目!!");
        } else if (lbzlsData.length == 0) {
            alertify.alert("最少要有一笔类别明细资料!!");
        } else {
            // 取得类别编号 & 类别项目 & 说明
            let lbzl = { lb: $("#textlb").val(), zwsm: $("#textzwsm").val(), bz: $("#textbz").val() };
            // True
            if ($("#newL").attr("disabled")) {
                alertify.confirm("确认修改?", function (e) {
                    // let updatedata = [lbzl, lbzlsData];
                    if (e) {
                        // OK、送出Update // PUT-Update、更新，并不会产生新的数据，新的数据会覆盖老的数据，用PUT
                        $.ajax({
                            type: "PUT",
                            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                            url: "/lbzls/updatelbzl/",
                            data: JSON.stringify({ "oldlbzl": [templbzl], "newlbzl": [lbzl], "newlbzls": lbzlsData }), // 因為jQuery安全性升高、所以要這樣寫
                            dataType: "text",
                            success: function (message) {
                                // Elements Control
                                $("#textlb").attr("disabled", true);
                                $("#textzwsm").attr("disabled", true);
                                $("#textbz").attr("disabled", true);

                                // CRUD button
                                $("#newL").attr("disabled", false);
                                $("#delL").attr("disabled", true);
                                $("#buttonAdd").attr("disabled", true);
                                $("#buttonDel").attr("disabled", true);
                                $("#buttonSave").attr("disabled", true);
                                $("#buttonQuit").attr("disabled", true);

                                $("#content").load("lbzls");
                                alertify.success("修改成功!!");
                            },
                            beforeSend: function () {
                                $.blockUI();
                            },
                            complete: function () {
                                $.unblockUI();
                            },
                            error: function () {
                                alert("修改出问题、请检查资料");
                            }
                        });
                    } else {
                        alertify.success("放弃修改");
                    }
                });
            } else {
                // 取得类别编号 & 类别项目 & 说明
                let lbzl = { lb: $("#textlb").val(), zwsm: $("#textzwsm").val(), bz: $("#textbz").val() };
                // 預存資料-放這裡沒有意義只是防傳值錯誤
                templbzl = [];
                // 預存目前的类别编号 & 类别项目 & 说明-放這裡沒有意義只是防傳值錯誤
                templbzl = { lb: $("#textlb").val(), zwsm: $("#textzwsm").val(), bz: $("#textbz").val() };
                alertify.confirm("确认新增?", function (e) {
                    if (e) {
                        // OK、送出Insert // POST-Insert、 创建，会产生新的数据，则用POST
                        $.ajax({
                            type: "POST",
                            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                            url: "/lbzls/insertlbzl/",
                            data: JSON.stringify({ "oldlbzl": [templbzl], "newlbzl": [lbzl], "newlbzls": lbzlsData }), // 因為jQuery安全性升高、所以要這樣寫
                            dataType: "text",
                            success: function (message) {
                                $("#content").load("lbzls");

                                alertify.success("新增成功!!");
                            },
                            beforeSend: function () {
                                $.blockUI();
                            },
                            complete: function () {
                                $.unblockUI();
                            },
                            error: function () {
                                alert("新增出问题、请检查资料");
                            }
                        });
                    } else {
                        alertify.success("放弃新增");
                    }
                });
            }
        }
    });

    // buttonQuit
    $("#buttonQuit").click(function () {
        $("#content").load("lbzls");
    });

    // button - addCol
    $("#addCol").click(function () {
        if ($("#lbdhget").val() == "") {
            alertify.alert("请输入类别代号!!");
        } else if ($("#zwsmget").val() == "") {
            alertify.alert("请输入中文名称!!");
        } else {
            $("#lbzls_getList").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                "<td>" + $("#lbdhget").val() + "</td>" +
                "<td>" + $("#zwsmget").val() + "</td>" +
                "<td>" + $("#bzget").val() + "</td>" +
                "<td>" + $("#bz1get").val() + "</td>" +
                "<td class='delCol' style='display: none;'>" +
                "<input class='delColButton' type='button' value='Del' style='font-size: 0.75rem; width: 2rem;' />" +
                "</td>" +
                "</tr>"
            );

            // Clear
            $("#lbdhget").val("");
            $("#zwsmget").val("");
            $("#bzget").val("");
            $("#bz1get").val("");
        }
    });

    // Del Col // 動態產生input會無法觸發click、所以對父元件綁定事件、也可以全域綁(把父元件刪掉就好)
    $(document).on("click", "#lbzls_getList .delColButton", function () {
        // 找到目前click的 TR
        let nowTR = $(this).parent().parent();
        // let nowTd = nowTR.find("td");
        // console.log(nowTd[2].innerText);
        nowTR.remove();
    });

});