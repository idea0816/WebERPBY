// for PGZL.html

$(document).ready(function () {
    // 設定確認視窗中的按鈕文字、每個JS都要加、不然不會有作用
    alertify.set({
        labels: {
            ok: "确认",
            cancel: "放弃"
        }
    });

    // Input Control
    $("#textPGNO").attr("disabled", true);

    // button Control
    $("#getVR").attr("disabled", true);


    // 計算訂單胚料(舊系統中的CAL)
    $.ajax({
        type: "GET",
        contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
        url: "/PGZL/insertDDZLS1/",
        dataType: "text",
        success: function (message) {
            alertify.success("訂單胚料計算:" + message);
        }
    });

    // 派工
    $("#getPG").click(function () {
        // show
        $("#inputArea").show();
        $("#pfTable1").show();
        // hide
        $("#calArea").hide();
        $("#pgTableList").hide();
        $("#pgzls1Table").hide();

        // Input Controll
        $("#textDate").attr("disabled", false);
        $("#textClass").attr("disabled", false);
        $("#textDate").val("");
        $("#textClass").val("");
        // 移除舊有表格
        $("#pgTableList td").parent().remove();/*加上parent()避免出現空白格*/

        // 取得派工單號(年月日+2位流水碼、共10碼)
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/PGZL/getVersion/WML",
            dataType: "text",
            success: function (newVersion) {
                $("#textPGNO").val(newVersion)
            },
            beforeSend: function () {
                $.blockUI();
            },
            complete: function () {
                $.unblockUI();
            },
            error: function () {
                alert("未取得配工單號！");
            }
        });

    });

    // Table Click to  Get Data
    $("#pgTable tr").click(function () {
        // show
        $("#inputArea").show();
        $("#calArea").show();
        $("#pgTableList").show();
        // hide
        $("#bg1").hide();
        $("#pfInputArea").hide();
        $("#pgzls1Table").hide();
        $("#pfTable1").hide();

        // Input Control
        $("#textDate").attr("disabled", true);
        $("#textClass").attr("disabled", true);

        let tr = $(this);   // 找到目前click的 TR
        let td = tr.find("td");
        // Table 點擊後背景顏色改變(顏色的設定在CSS裡)
        let trs = $(this).parent().find("tr");  // 取得所有的tr
        if (trs.hasClass("on")) {   // 判斷有沒有Class on
            trs.removeClass("on");
        }
        $(this).addClass("on");

        let pgdate = td[0].innerText;
        $("#textPGNO").val(td[0].innerText);
        $("#textDate").val(td[1].innerText);


        // 派工明細資料
        // 移除舊有表格
        $("#pgTableList td").parent().remove();/*加上parent()避免出現空白格*/
        $("#pgTablePrint td").parent().remove();/*加上parent()避免出現空白格*/
        let sumOH = 0;
        let sumKg = 0.0
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/PGZL/getPGZLS/" + pgdate,
            dataType: "json",
            success: function (getList) {
                $.each(getList, function (key, value) {
                    $("#textClass").val(value.gssm);

                    $("#pgTableList").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                        "<td>" + value.cldh + "</td>" +
                        "<td>" + value.cqdh + "</td>" +
                        "<td>" + value.pgss + "</td>" +
                        "<td>" + value.kgs + "</td>" +
                        "<td class='delCol' style='display: none;'>" +
                        "<input class='delColButton' type='button' value='Del' style='font-size: 0.75rem; width: 2rem;' />" +
                        "</td>" +
                        "</tr>");

                    $("#pgTablePrint").append("<tr>" +
                        "<td>" + value.cldh + "</td>" +
                        "<td>" + value.cqdh + "</td>" +
                        "<td>" + value.pgss + "</td>" +
                        "<td>" + value.kgs + "</td>" +
                        "</tr>");
                    sumOH += parseInt(value.pgss);
                    sumKg += parseFloat(value.kgs);

                });
                // Print Page
                $("#lbPgno").text($("#textPGNO").val());
                $("#lbDate").text($("#textDate").val());
                $("#lbClass").text($("#textClass").val());
                $("#pgTablePrint").append("<tr>" +
                    "<td></td>" +
                    "<td><h3>*合計*</h3></td>" +
                    "<td><h3>" + sumOH + "<h3></td>" +
                    "<td><h3>" + sumKg + "</h3></td>" +
                    "</tr>");

            },
            beforeSend: function () {
                $.blockUI();
            },
            complete: function () {
                $.unblockUI();
            },
            error: function () {
                alert("派工無资料！請新增資料");
            }
        });

    });

    // 取得配方資料
    $("#getVR").click(function () {
        if ($("#getVR").val() == "不依需求") {
            $("#getVR").val("依需求");
            $("#pfTable1").hide();
            $("#pfTable2").show();
        } else {
            $("#getVR").val("不依需求");
            $("#pfTable1").show();
            $("#pfTable2").hide();
        }
    });


    // Table Click to  Get Data
    $("#pfTable1 tr").click(function () {
        // show
        $("#bg1").show();
        $("#pfInputArea").show();
        // hide
        $("#calArea").hide();
        $("#pgzls1Table").hide();

        let tr = $(this);
        let td = tr.find("td");

        // Table 點擊後背景顏色改變(顏色的設定在CSS裡)
        let trs = $(this).parent().find("tr");  // 取得所有的tr
        if (trs.hasClass("on")) {   // 判斷有沒有Class on
            trs.removeClass("on");
        }
        $(this).addClass("on");

        $("#textCldh").val(td[0].innerText);
        $("#textZwpm").val(td[1].innerText);

        // 實時監聽 input 
        $('#textPgss').on('input propertychange', function () {
            $("#textKgs").val(parseFloat($("#textPgss").val()) * parseFloat(td[2].innerText));
        });

    });

    // buttonAdd
    $("#buttonAdd").click(function () {
        if ($("#textDate").val() == "") {
            alertify.alert("请先選擇派工日期!!");
        } else if ($("#textClass").val() == "") {
            alertify.alert("请先選擇班別!!");
        } else {
            // show
            $("#pgTableList").show();

            // hide

            $("#pgTableList").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                "<td>" + $('#textCldh').val() + "</td>" +
                "<td>" + $('#textZwpm').val() + "</td>" +
                "<td>" + $('#textPgss').val() + "</td>" +
                "<td>" + $('#textKgs').val() + "</td>" +
                "<td class='delCol' style='display: none;'>" +
                "<input class='delColButton' type='button' value='Del' style='font-size: 0.75rem; width: 2rem;' />" +
                "</td>" +
                "</tr>");

            // Clear Data
            $(".delCol").hide(500);
            $('#textCldh').val("");
            $('#textZwpm').val("");
            $('#textPgss').val("");
            $('#textKgs').val("");

        }
    });

    // buttonDel
    $("#buttonDel").click(function () {
        $(".delCol").show(500);
    });

    // buttonSave
    $("#buttonSave").click(function () {

        let rows = $("#pgTableList tr");
        let pgzlData = [];
        let pgzlsData = [];
        // let pgzlsdcData = [];   // 預存資料-放這裡沒有意義只是防傳值錯誤
        for (let i = 1, len = rows.length; i < len; i++) {
            let cldh = $(rows[i]).children()[0].innerText;
            let kgs = $(rows[i]).children()[3].innerText;
            let pgss = $(rows[i]).children()[2].innerText;

            // 取得派工明細資料
            pgzlsData.push({ cldh: cldh, kgs: kgs, pgss: pgss, gssm: $("#textClass").val() });
        }
        // 取得派工表頭資料
        pgzlData.push({ pgdate: $("#textPGNO").val(), date1: $("#textDate").val(), bz: "WML" });

        alertify.confirm("确认新增?", function (e) {
            if (e) {
                // OK、送出Insert // POST-Insert、 创建，会产生新的数据，则用POST
                $.ajax({
                    type: "POST",
                    contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                    url: "/PGZL/insertPGZL/",
                    data: JSON.stringify({ "pgzl": pgzlData, "pgzls": pgzlsData }), // 因為jQuery安全性升高、所以要這樣寫
                    dataType: "text",
                    success: function (message) {
                        $("#content").load("PGZL");
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

    });

    // buttonQuit
    $("#buttonQuit").click(function () {
        $("#content").load("PGZL");
    });

    // Del Col // 動態產生input會無法觸發click、所以對父元件綁定事件、也可以全域綁(把父元件刪掉就好)
    $(document).on("click", "#pgTableList .delColButton", function () {
        // 找到目前click的 TR
        let nowTR = $(this).parent().parent();
        nowTR.remove();

    });

    // buttonPrint
    $("#buttonPrint").click(function () {
        // Show Print Page
        var newWin = window.open('', 'PreviewPrint Windows');
        newWin.document.open();
        newWin.document.write('<html>' +
            '<head>' +
            '<link rel="stylesheet" href="css/print.css">' +
            '</head>' +
            '<body onload="window.print()">' +
            $('#afPrintZone').html() +
            '</body>' +
            '</html>');
        newWin.document.close();
        setTimeout(function () { newWin.close(); }, 10);

    });


});
