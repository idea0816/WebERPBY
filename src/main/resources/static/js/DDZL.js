// for DDZL.html

$(document).ready(function () {
    // 設定確認視窗中的按鈕文字、每個JS都要加、不然不會有作用
    alertify.set({
        labels: {
            ok: "确认",
            cancel: "放弃"
        }
    });

    $("#renewDDZL").click(function () {
        let StarDate = $("#StarDate").val();
        let EndDate = $("#EndDate").val();
        // 導入多少新訂單資料
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/DDZL/renewDDZL/" + StarDate + "/" + EndDate,
            dataType: "text",
            success: function (countDDZLData) {
                alertify.success(countDDZLData);

                // 訂單列表
                // 移除舊有表格
                $("#DDZLTable td").parent().remove();/*加上parent()避免出現空白格*/
                let ddCount = [];
                $.ajax({
                    type: "GET",
                    contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                    url: "/DDZL/getDDZL/" + StarDate + "/" + EndDate,
                    dataType: "json",
                    success: function (DDZLList) {
                        ddCount = DDZLList;
                        let pairsCount = 0;

                        $.each(DDZLList, function (key, value) {

                            $("#DDZLTable").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                                "<td class='td_center tsmall'>" + value.ddbh + "</td>" +
                                "<td class='tsmall'>" + value.xieXing + "</td>" +
                                "<td class='tsmall'>" + value.sheHao + "</td>" +
                                "<td class='tsmall'>" + value.article + "</td>" +
                                "<td class='tsmall'><input  type='date' value=" + value.ddrq + "></td>" +
                                // "<td class='tsmall'><input  type='date' value='" + value.scrq + "'></td>" +
                                "<td class='tsmall'><input  type='date' value='" + value.ddjq + "'></td>" +
                                "<td class='tsmall'>" + value.pairs + "</td>" +
                                "</td>" +
                                "</tr>");
                            // jQuery文字轉數字
                            pairsCount += parseInt(value.pairs);
                        });
                        $("#ddzlCount").html("筆數：" + ddCount.length);
                        $("#pairsCount").html("雙數：" + pairsCount);
                    },
                    beforeSend: function () {
                        $.blockUI();
                    },
                    complete: function () {
                        $.unblockUI();
                    },
                    error: function () {
                        alert("未取得订单资料！");
                    }
                });
            },

            beforeSend: function () {
                $.blockUI();
            },
            complete: function () {
                $.unblockUI();
            },
            error: function () {
                alert("未取得订单资料！");
            }
        });

    });



    // 訂單(尺寸、數量)列表

    // Table Click to  Get Data
    $("#divDT").on("click", "#DDZLTable tr", function () {
        // $("#DDZLTable tr").click(function () {
        let tr = $(this);   // 找到目前click的 TR
        let td = tr.find("td");
        // Table 點擊後背景顏色改變(顏色的設定在CSS裡)
        let trs = $(this).parent().find("tr");  // 取得所有的tr
        if (trs.hasClass("on")) {   // 判斷有沒有Class on
            trs.removeClass("on");
        }
        $(this).addClass("on");

        // Get DDBH
        let DDBH = td[0].innerText;

        // 移除舊有表格
        $("#DDZLSTable td").parent().remove();/*加上parent()避免出現空白格*/
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/DDZL/getDDZLS/" + DDBH,
            dataType: "json",
            success: function (ddzlsList) {
                $.each(ddzlsList, function (key, value) {

                    $("#DDZLSTable").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                        "<td>" + value.cc + "</td>" +
                        "<td>" + value.qty + "</td>" +
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
                alert("未取得訂單明细资料！");
            }
        });



    });


});