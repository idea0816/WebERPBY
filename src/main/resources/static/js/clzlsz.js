// for clzlsz.html

$(document).ready(function () {

    // 設定確認視窗中的按鈕文字、每個JS都要加、不然不會有作用
    alertify.set({
        labels: {
            ok: "确认",
            cancel: "放弃"
        }
    });

    // 預存材料資料、為了改變資料時可以比對、預存時機在按下「修改」和「新增版次」鍵時
    let tempCLZL = [];

    // Table Click to  Get Data
    $("#pfTable tr").click(function () {
        // button Control enabled
        $("#newR").attr("disabled", true);
        $("#delR").attr("disabled", false);
        $("#buttonAdd").attr("disabled", false);
        $("#addversion").attr("disabled", false);
        $("#buttonQuit").attr("disabled", false);

        let tr = $(this);   // 找到目前click的 TR
        let td = tr.find("td");
        // Table 點擊後背景顏色改變(顏色的設定在CSS裡)
        let trs = $(this).parent().find("tr");  // 取得所有的tr
        if (trs.hasClass("on")) {   // 判斷有沒有Class on
            trs.removeClass("on");
        }
        $(this).addClass("on");

        // Get cldh
        $("#textcldh").val(td[0].innerText);
        $("#textzwpm").val(td[2].innerText);
        $("#textcgcqdh").val(td[5].innerText);
        $("#textversion").val(td[1].innerText);
        // 移除舊有表格
        $("#Formula_getList td").parent().remove();/*加上parent()避免出現空白格*/

        // Get clzlsz Data
        // 加上版次、才能找到正確的配方代號
        let getcldh = $("#textcldh").val() + "*" + $("#textversion").val();
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/clzlszList/" + getcldh,
            dataType: "json",
            success: function (clzlszList) {
                $.each(clzlszList, function (key, value) {

                    $("#Formula_getList").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                        "<td class='td_center'>" + value.lb + "</td>" +
                        "<td>" + value.cldhz + "</td>" +
                        "<td>" + value.cldh + "</td>" +
                        "<td>" + value.phr + "</td>" +
                        "<td>" + value.clyl + "</td>" +
                        "<td>" + value.cldj + "</td>" +
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
                alert("未取得配方明细资料！");
            }
        });
    });

    // Elements Control
    $("#textcldh").attr("disabled", true);
    $("#textzwpm").attr("disabled", true);
    $("#textcgcqdh").attr("disabled", true);
    $("#textversion").attr("disabled", true);

    // CRUD button
    $("#delR").attr("disabled", true);
    $("#buttonAdd").attr("disabled", true);
    $("#buttonDel").attr("disabled", true);
    $("#buttonSave").attr("disabled", true);
    $("#buttonQuit").attr("disabled", true);
    $("#addversion").attr("disabled", true);
    $("#buttonAddR").attr("disabled", true);
    $("#tfCheck").attr("disabled", true);

    // 新建配方
    $("#newR").click(function () {
        $("#textcldh").attr("disabled", false);
        $("#textzwpm").attr("disabled", false);
        $("#textcgcqdh").attr("disabled", false);
        $("#buttonAdd").attr("disabled", false);
        $("#buttonQuit").attr("disabled", false);

        $("#buttonAdd").val("添加");
        $("#textversion").val("001");

    });

    // 刪除配方
    $("#delR").click(function () {

        alertify.confirm("确认删除配方：" + $("#textcldh").val() + ", 版次：" + $("#textversion").val() + " ? (配方相关资料全部删除、无法回复）", function (e) {
            if (e) {
                // OK、送出DELETE
                // 加上版次、才能找到正確的配方代號
                let delData = $("#textcldh").val() + "*" + $("#textversion").val();
                $.ajax({
                    type: "DELETE",
                    contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                    url: "/clzlsz/deleteCLZL/",
                    data: delData,
                    dataType: "text",
                    success: function (message) {
                        $("#content").load("clzlsz");
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
        $("#buttonAddR").attr("disabled", false);

        if ($("#buttonAdd").val() == "修改") {
            $("#buttonAdd").val("添加");
            $("#textcldh").attr("disabled", false);
            $("#textzwpm").attr("disabled", false);
            $("#textcgcqdh").attr("disabled", false);

            // 預存資料
            tempCLZL = [];
            // 預存目前的配方代號 & 配方名稱 & 配方類別
            // 加上版次、才能找到正確的配方代號
            let realcldh = $("#textcldh").val() + "*" + $("#textversion").val();
            tempCLZL = { cldh: realcldh, zwpm: $("#textzwpm").val(), cgcqdh: $("#textcgcqdh").val() };
        } else {
            $(".delCol").hide(1000);
            $("#wlTable").show(1000);
        };
    });

    // buttonDel
    $("#buttonDel").click(function () {
        $("#wlTable").hide(1000);
        $(".delCol").show(1000);

    });

    // buttonSave
    $("#buttonSave").click(function () {

        let rows = $("#Formula_getList tr");
        let clzlszData = [];
        for (let i = 1, len = rows.length; i < len; i++) {
            let lb = $(rows[i]).children()[0].innerText;
            let cldhz = $(rows[i]).children()[1].innerText;
            let clyl = $(rows[i]).children()[4].innerText;

            // 取得變動材料資料
            clzlszData.push({ lb: lb, cldhz: cldhz, clyl: clyl });
        }

        if ($("#textcldh").val() == "") {
            alertify.alert("请输入配方代号!!");
        } else if ($("#textzwpm").val() == "") {
            alertify.alert("请输入配方名称!!");
        } else if ($("#textcgcqdh").val() == "") {
            alertify.alert("请选择配方类别!!");
        } else if (clzlszData.length == 0) {
            alertify.alert("最少要有一笔材料资料!!");
        } else {
            // 取得配方代號 & 配方名稱 & 配方類別
            // 加上版次、才能找到正確的配方代號
            let realcldh = $("#textcldh").val() + "*" + $("#textversion").val();
            let newCLZL = { cldh: realcldh, zwpm: $("#textzwpm").val(), cgcqdh: $("#textcgcqdh").val() };
            // True(修改配方)
            if ($("#newR").attr("disabled")) {
                alertify.confirm("确认修改?", function (e) {
                    if (e) {
                        // OK、送出Update // Update、更新，并不会产生新的数据，新的数据会覆盖老的数据，用PUT
                        $.ajax({
                            type: "PUT",
                            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                            url: "/clzlsz/updateCLZL/",
                            data: JSON.stringify({ "oldCLZL": [tempCLZL], "newCLZL": [newCLZL], "newclzlsl": clzlszData }), // 因為jQuery安全性升高、所以要這樣寫
                            dataType: "text",
                            success: function (message) {
                                $("#content").load("clzlsz");
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
                // 加上版次、才能找到正確的配方代號
                let realcldh = $("#textcldh").val() + "*" + $("#textversion").val();
                // 取得配方代號 & 配方名稱 & 配方類別
                let newCLZL = { cldh: realcldh, zwpm: $("#textzwpm").val(), cgcqdh: $("#textcgcqdh").val() };
                // 預存資料-放這裡沒有意義只是防傳值錯誤
                tempCLZL = [];
                // 預存目前的配方代號 & 配方名稱 & 配方類別-放這裡沒有意義只是防傳值錯誤
                tempCLZL = { cldh: $("#textcldh").val(), zwpm: $("#textzwpm").val(), cgcqdh: $("#textcgcqdh").val() };

                alertify.confirm("确认新增?", function (e) {
                    if (e) {
                        // OK、送出Insert // POST-Insert、 创建，会产生新的数据，则用POST
                        $.ajax({
                            type: "POST",
                            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                            url: "/clzlsz/insertCLZL/",
                            data: JSON.stringify({ "oldCLZL": [tempCLZL], "newCLZL": [newCLZL], "newclzlsl": clzlszData }), // 因為jQuery安全性升高、所以要這樣寫
                            dataType: "text",
                            success: function (message) {
                                $("#content").load("clzlsz");
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
        $("#content").load("clzlsz");
    });

    // button - addCol
    $("#addCol").click(function () {
        if ($("#cllbChoice").val() == "") {
            alertify.alert("请选择类别!!");
        } else if ($("#zwpmChoice").val() == "") {
            alertify.alert("请选择原物料!!");
        } else if ($("#clylget").val() == "") {
            alertify.alert("请输入用量!!");
        } else {
            $("#Formula_getList").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                "<td class='td_center'>" + $("#cllbChoice").val() + "</td>" +
                "<td>" + $("#zwpmChoice").val() + "</td>" +
                "<td></td>" +
                "<td></td>" +
                "<td>" + $("#clylget").val() + "</td>" +
                "<td></td>" +
                "<td class='delCol' style='display: none;'>" +
                "<input class='delColButton' type='button' value='Del' style='font-size: 0.75rem; width: 2rem;' />" +
                "</td>" +
                "</tr>"
            );

            // Clear
            $("#cllbChoice").val("");
            $("#zwpmChoice").val("");
            $("#clylget").val("");
        }

    });

    // button - addR
    $("#addR").click(function () {
        if ($("#rChoice").val() == "") {
            alertify.alert("请选择配方!!");
        } else if ($("#rclylget").val() == "") {
            alertify.alert("请输入用量!!");
        } else {
            $("#Formula_getList").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                "<td>R</td>" +
                "<td>" + $("#rChoice").val() + "</td>" +
                "<td></td>" +
                "<td></td>" +
                "<td>" + $("#rclylget").val() + "</td>" +
                "<td></td>" +
                "<td class='delCol' style='display: none;'>" +
                "<input class='delColButton' type='button' value='Del' style='font-size: 0.75rem; width: 2rem;' />" +
                "</td>" +
                "</tr>"
            );
        }

    });


    // Del Col // 動態產生input會無法觸發click、所以對父元件綁定事件、也可以全域綁(把父元件刪掉就好)
    $(document).on("click", "#Formula_getList .delColButton", function () {
        // 找到目前click的 TR
        let nowTR = $(this).parent().parent();
        // let nowTd = nowTR.find("td");
        // console.log(nowTd[2].innerText);
        nowTR.remove();

    });

    // addVersion 新增版次按鈕
    $("#addversion").click(function () {
        $("#buttonAdd").val("添加");
        $("#newR").attr("disabled", false);
        $("#delR").attr("disabled", true);
        $("#buttonDel").attr("disabled", false);
        $("#buttonSave").attr("disabled", false);
        $("#textcldh").attr("disabled", false);
        $("#textzwpm").attr("disabled", false);
        $("#textcgcqdh").attr("disabled", false);

        // 加上版次、才能找到正確的配方代號
        let realcldh = $("#textcldh").val() + "*" + $("#textversion").val();

        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/clzlsz/getVersion/" + realcldh,
            dataType: "text",
            success: function (newVersion) {
                $("#textversion").val(newVersion);
            },
            beforeSend: function () {
                $.blockUI();
            },
            complete: function () {
                $.unblockUI();
            },
            error: function () {
                alert("未取得版本资料！");
            }

        });
    });

    // 配方再加入配方
    $("#buttonAddR").click(function () {
        $("#wlTable").hide(1000);
        $("#rTable").show(1000);
    });


});