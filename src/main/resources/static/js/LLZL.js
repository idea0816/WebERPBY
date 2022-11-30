// for LLZL.html

$(document).ready(function () {

    // 設定確認視窗中的按鈕文字、每個JS都要加、不然不會有作用
    alertify.set({
        labels: {
            ok: "确认",
            cancel: "放弃"
        }
    });

    // 預存資料
    var llbh;

    // llTable
    $("#llTable tr").click(function () {
        let tr = $(this);   // 找到目前click的 TR
        let td = tr.find("td");
        // Table 點擊後背景顏色改變(顏色的設定在CSS裡)
        let trs = $(this).parent().find("tr");  // 取得所有的tr
        if (trs.hasClass("on")) {   // 判斷有沒有Class on
            trs.removeClass("on");
        }
        $(this).addClass("on");

        llbh = td[0].innerText;
        // 移除舊有表格
        $("#llListTable td").parent().remove();/*加上parent()避免出現空白格*/
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/LLZL/getLLZLS/" + llbh,
            dataType: "json",
            success: function (getList) {
                $.each(getList, function (key, value) {

                    $("#llListTable").append("<tr class='" + value.cldh + "' onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                        "<td class='tsmall'>" + value.cldh + "</td>" +
                        "<td class='tsmall'>" + value.cqdh + "</td>" +
                        "<td>" + value.kgs_LL + "</td>" +
                        "<td><input type='text' style='width: 95% ;'></td>" +
                        "<td><input type='text' style='width: 95% ;' disabled></td>" +
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
                alert("還沒有生成領料單、請連絡生管！");
            }
        });

    });

    // buttonSave
    $("#buttonSave").click(function () {
        console.log(pgdate);
        alertify.confirm("确认存儲?", function (e) {
            if (e) {
                let pgzls1Data = [];

                let llrows = $("#llListTable tr");
                for (let i = 1, len = llrows.length; i < len; i++) {
                    let td = $(llrows[i]).children("td");
                    // get llListTable Data
                    pgdate = pgdate;

                    // 取得資料
                    pgzls1Data.push({ pgdate: pgdate, cldh: td[0].innerText, clyl: td.eq(3).find("input").val() });
                }

                // OK、送出Update // Update、更新，并不会产生新的数据，新的数据会覆盖老的数据，用PUT
                $.ajax({
                    type: "PUT",
                    contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                    url: "/llwml/updatePGZLS1/",
                    data: JSON.stringify({ "updatepgzls1": pgzls1Data }), // 因為jQuery安全性升高、所以要這樣寫
                    dataType: "text",
                    success: function (message) {
                        alertify.success("領料完成!!");
                    },
                    beforeSend: function () {
                        $.blockUI();
                    },
                    complete: function () {
                        $.unblockUI();
                    },
                    error: function () {
                        alert("領料出问题、请检查资料");
                    }
                });
            } else {
                alertify.success("放弃領料");
            }
        });
    });

    // llListTable-click
    $("#divllList").on("click", "#llListTable tr", function () {
        let tr = $(this);   // 找到目前click的 TR
        let td = tr.find("td");
        // Table 點擊後背景顏色改變(顏色的設定在CSS裡)
        let trs = $(this).parent().find("tr");  // 取得所有的tr
        if (trs.hasClass("on")) {   // 判斷有沒有Class on
            trs.removeClass("on");
        }
        $(this).addClass("on");

        // 實時監聽 input 
        (td.eq(3).find("input")).on('input propertychange', function () {
            // console.log(parseFloat(td.eq(3).find("input").val()) - parseFloat(td[2].innerText));
            td.eq(4).find("input").val(parseFloat(td.eq(3).find("input").val()) - parseFloat(td[2].innerText));
        });
    });

    // llListTable-keydown
    $("#divllList").on("keydown", "#llListTable tr", function () {
        let tr = $(this);   // 找到目前click的 TR
        let td = tr.find("td");
        // Table 點擊後背景顏色改變(顏色的設定在CSS裡)
        let trs = $(this).parent().find("tr");  // 取得所有的tr
        if (trs.hasClass("on")) {   // 判斷有沒有Class on
            trs.removeClass("on");
        }
        $(this).addClass("on");

        // 實時監聽 input 
        (td.eq(3).find("input")).on('input propertychange', function () {
            // console.log(parseFloat(td.eq(3).find("input").val()) - parseFloat(td[2].innerText));
            td.eq(4).find("input").val(parseFloat(td.eq(3).find("input").val()) - parseFloat(td[2].innerText));
        });
    });

    // Button-getall
    $("#getall").click(function () {
        let llrows = $("#llListTable tr");
        for (let i = 1, len = llrows.length; i < len; i++) {
            $(llrows[i]).find("input").eq(0).val($(llrows[i]).find('td').eq(2).html());
        }
    });

    // buttonQuit
    $("#buttonQuit").click(function () {
        $("#content").load("llwml");
    });

    // button - addCol
    $("#addCol").click(function () {
        if ($("#zwpmChoice").val() == "") {
            alertify.alert("请选择原物料!!");
        } else if ($("#clylget").val() == "") {
            alertify.alert("请输入用量!!");
        } else {
            $("#Formula_getList").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                "<td>" + $("#zwpmChoice").val() + "</td>" +
                "<td>" + $("#clylget").val() + "</td>" +
                "<td class='delCol' style='display: none;'>" +
                "<input class='delColButton' type='button' value='Del' style='font-size: 0.75rem; width: 2rem;' />" +
                "</td>" +
                "</tr>"
            );

            // Clear
            // $("#cllbChoice").val("");
            // $("#zwpmChoice").val("");
            // $("#clylget").val("");
        }

    });

});