// for clzlsl.html

$(document).ready(function () {

    // 設定確認視窗中的按鈕文字、每個JS都要加、不然不會有作用
    alertify.set({
        labels: {
            ok: "确认",
            cancel: "放弃"
        }
    });

    // Table Click to  Get Data
    $("#pfTable tr").click(function () {
        // button Control enabled
        $("#buttonAdd").attr("disabled", false);
        $("#newVR").attr("disabled", true);
        $("#delVR").attr("disabled", false);
        $("#buttonQuit").attr("disabled", false);

        let tr = $(this);
        let td = tr.find("td");

        // Get cldh
        // console.log(td[0].innerText);
        $("#textcldh").val(td[0].innerText);
        $("#textzwpm").val(td[1].innerText);
        // 移除舊有表格
        $("#Formula_getList td").parent().remove();/*加上parent()避免出現空白格*/

        // Get clzlsl Data
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/clzlslList/" + td[0].innerText,
            // data: JSON.stringify(params), // 因為jQuery安全性升高、所以要這樣寫
            dataType: "json",
            success: function (clzlslList) {
                // }).done(function (clzlslList) {

                //     // var sum_amount = 0;//總金額
                //     // var sum_clyl = 0;//總重量
                //     // var sum_z_clyl = 0;//類別Z總重量
                $.each(clzlslList, function (key, value) {

                    //         // sum_clyl += value.clyl;
                    //         // sum_amount += value.amount;
                    //         // if (value.lb == "Z") { sum_z_clyl += value.clyl }

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

                // if (sum_clyl == 0 || sum_amount == 0) {
                //     $("#cldh_cldj").val(0);
                //     $("#cldh_TotKgs").val(0);
                // } else {
                //     $("#cldh_cldj").val(Math.round((sum_amount / sum_clyl) * 10000) / 10000);//小數點後4位四捨五入簡易設定
                //     $("#cldh_TotKgs").val(Math.round((sum_clyl - sum_z_clyl) * 100) / 100);//小數點後2位四捨五入簡易設定
                // }
                // },

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

    // CRUD button
    $("#delVR").attr("disabled", true);
    $("#buttonAdd").attr("disabled", true);
    $("#buttonDel").attr("disabled", true);
    $("#buttonSave").attr("disabled", true);
    $("#buttonQuit").attr("disabled", true);

    // 新建配方
    $("#newVR").click(function () {
        $("#textcldh").attr("disabled", false);
        $("#textzwpm").attr("disabled", false);
        $("#buttonAdd").attr("disabled", false);
        $("#buttonQuit").attr("disabled", false);

        $("#buttonAdd").val("添加");
        $("#pfTable").hide(1000);

    });

    // 刪除配方
    $("#delVR").click(function () {
        // Get clzlsl Data
        // $.ajax({
        //     type: "GET",
        //     contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
        //     url: "/clzlslList/" + td[0].innerText,
        //     // data: JSON.stringify(params), // 因為jQuery安全性升高、所以要這樣寫
        //     dataType: "json",
        //     success: function (clzlslList) { };
        // }).done(function (clzlslList) {

        alertify.confirm("确认删除配方：" + $("#textcldh").val() + " ? (配方相关资料全部删除、无法回复）", function (e) {
            if (e) {
                alertify.success($("#textcldh").val() + "删除成功");
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
            $("#textcldh").attr("disabled", false);
            $("#textzwpm").attr("disabled", false);
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
        let clzlslData = [];
        for (let i = 1, len = rows.length; i < len; i++) {
            let lb = $(rows[i]).children()[0].innerText;
            let cldhz = $(rows[i]).children()[1].innerText;
            let clyl = $(rows[i]).children()[4].innerText;

            // 取得變動材料資料
            clzlslData.push({ lb: lb, cldhz: cldhz, clyl: clyl });
        }

        alert(clzlslData.length);
        if ($("#textcldh").val() == "") {
            alertify.alert("请输入配方代号!!");
        } else if ($("#textzwpm").val() == "") {
            alertify.alert("请输入配方名称!!");
        } else if (clzlslData.length == 0) {
            alertify.alert("最少要有一笔材料资料!!");
        } else {
            // 取得配方代號 & 配方名稱
            let vCLZL = { cldh: $("#textcldh").val(), zwpm: $("#textzwpm").val() };
            // True
            if ($("#newVR").attr("disabled")) {
                alert("修改");
                alertify.confirm("确认修改?", function (e) {
                    if (e) {
                        // OK、送出Update // PUT-Update、更新，并不会产生新的数据，新的数据会覆盖老的数据，用PUT
                        $.ajax({
                            type: "PUT",
                            url: "EditFormula_getcldh.do?addcldhz="
                                + encodeURI(encodeURI(add)),
                            dataType: "json",
                            success: function () {
                            },
                            beforeSend: function () {
                                $.blockUI();
                            },
                            complete: function () {
                                $.unblockUI();
                            },
                            error: function () {
                                alert("error");
                            }
                        });
                    } else {
                        alertify.success("放弃修改");
                    }

                });
            } else {
                alert("新增");
                alertify.confirm("确认新增?", function (e) {
                    if (e) {
                        // OK、送出Insert // POST-Insert、 创建，会产生新的数据，则用POST
                        $.ajax({
                            type: "POST",
                            url: "EditFormula_getcldh.do?addcldhz="
                                + encodeURI(encodeURI(add)),
                            dataType: "json",
                            success: function () {
                            },
                            beforeSend: function () {
                                $.blockUI();
                            },
                            complete: function () {
                                $.unblockUI();
                            },
                            error: function () {
                                alert("error");
                            }
                        });
                    } else {
                        alertify.success("放弃新增");
                    }

                });
            }

            console.log(clzlslData);
            console.log(vCLZL);
        }
    });

    // buttonQuit
    $("#buttonQuit").click(function () {
        $("#content").load("clzlsl");
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

    // Del Col // 動態產生input會無法觸發click、所以對父元件綁定事件、也可以全域綁(把父元件刪掉就好)
    $(document).on("click", "#Formula_getList .delColButton", function () {

        // 找到目前click的 TR
        let nowTR = $(this).parent().parent();
        // let nowTd = nowTR.find("td");
        // console.log(nowTd[2].innerText);
        nowTR.remove();

    });
});