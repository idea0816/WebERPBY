// for PGZL1.html

$(document).ready(function () {
    // 設定確認視窗中的按鈕文字、每個JS都要加、不然不會有作用
    alertify.set({
        labels: {
            ok: "确认",
            cancel: "放弃"
        }
    });

    var llbh;

    // 派工Table Click to  Get Data
    $("#pgTable tr").click(function () {

        let tr = $(this);   // 找到目前click的 TR
        let td = tr.find("td");
        // Table 點擊後背景顏色改變(顏色的設定在CSS裡)
        let trs = $(this).parent().find("tr");  // 取得所有的tr
        if (trs.hasClass("on")) {   // 判斷有沒有Class on
            trs.removeClass("on");
        }
        $(this).addClass("on");

        // 取得派工單號
        let pgListRow = $("#pgList tr");
        let pgno = td[0].innerText;
        if (pgno.substring(0, 2) == "20") {
            // show
            $("#wmlInput").show();
            $("#llArea").show();
            // hide
            $("#pgList").hide();
            $("#pgzls1Table").hide();
            // clear
            for (let j = 1; j <= 5; j++) {
                $(pgListRow[0]).children("td:eq(" + j + ")").find("input").val("");
            }

            $("#textPGNO").val(pgno);
            $("#lbprint").html("萬馬力 ");
        } else {
            // show
            $("#pgList").show();
            $("#llArea").show();
            // hide
            $("#wmlInput").hide();
            $("#pgzls1Table").hide();
            // clear
            $("#textPGNO").val("");

            // 寫入 pgList 的Table
            for (let j = 1; j <= 5; j++) {
                if ($(pgListRow[0]).children("td:eq(" + j + ")").find("input").val() == "") {
                    $(pgListRow[0]).children("td:eq(" + j + ")").find("input").val(pgno);
                    break;
                }
            }
            $("#lbprint").html("大車 ");
        }
    });

    // 生成領料單
    $("#getPGZLS1").click(function () {
        // show
        $("#llArea").show();
        $("#pgzls1Table").show();

        // 算料資料
        // 移除舊有表格
        $("#pgzls1Table td").parent().remove();/*加上parent()避免出現空白格*/

        if (!($("#textPGNO").val() == "")) {
            $.ajax({
                type: "GET",
                contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                url: "/PGZLS1/getLLZLS/" + $("#textPGNO").val(),
                dataType: "json",
                success: function (getList) {
                    $.each(getList, function (key, value) {
                        llbh = value.llbh;

                        $("#pgzls1Table").append("<tr>" +
                            "<td>" + value.cldh + "</td>" +
                            "<td>" + value.cqdh + "</td>" +
                            "<td>" + value.kgs_LL + "</td>" +
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
                    alert("生成領料單出問題!! 請連繫IT");
                }
            });
        } else {

            // 取得派工單號
            let senddcpgdate = "";
            let pgListRow = $("#pgList tr");
            for (let j = 1; j <= 5; j++) {
                if (!($(pgListRow[0]).children("td:eq(" + j + ")").find("input").val() == "")) {
                    senddcpgdate += $(pgListRow[0]).children("td:eq(" + j + ")").find("input").val() + "/"
                } else {
                    senddcpgdate += " /"
                }
            }
            console.log(senddcpgdate);

            // console.log(dcpgdate);
            $.ajax({
                type: "GET",
                contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                url: "/PGZLS1/getLLZLSdc/" + senddcpgdate,
                dataType: "json",
                success: function (getList) {
                    $.each(getList, function (key, value) {
                        llbh = value.llbh;

                        $("#pgzls1Table").append("<tr>" +
                            "<td>" + value.cldh + "</td>" +
                            "<td>" + value.cqdh + "</td>" +
                            "<td>" + value.kgs_LL + "</td>" +
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
                    alert("生成領料單出問題!! 請連繫IT");
                }
            });

        }





    });

    // 列印領料單
    $("#pgzls1Print").click(function () {
        // Show Print Page
        var newWin = window.open('', 'PreviewPrint Windows');
        newWin.document.open();
        newWin.document.write('<html>' +
            '<head>' +
            '<link rel="stylesheet" href="css/print.css">' +
            '</head>' +
            '<body onload="window.print()">' +
            '領料單號：' + llbh +
            $('#afPrintZone').html() +
            '</body>' +
            '</html>');
        newWin.document.close();
        setTimeout(function () { newWin.close(); }, 10);

    });

});