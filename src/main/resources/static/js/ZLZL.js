// for ZLZL.html

$(document).ready(function () {


    // 設定確認視窗中的按鈕文字、每個JS都要加、不然不會有作用
    alertify.set({
        labels: {
            ok: "确认",
            cancel: "放弃"
        }
    });

    // 還有多少型體沒有部位資料
    $.ajax({
        type: "GET",
        contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
        url: "/XXZL/countNoXXZLData/",
        dataType: "text",
        success: function (countNoXXZLData) {
            alertify.success(countNoXXZLData);
        }
    });

    $("#getData").click(function () {

        // 移除舊有表格
        $("#ddTable td").parent().remove();/*加上parent()避免出現空白格*/
        // Get DDZL Data
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/ZLZL/getDDZLbyERP/" + $("#articleChoice").val(),
            dataType: "json",
            success: function (getDDZL) {
            },
            beforeSend: function () {
                $.blockUI();
            },
            complete: function () {
                $.unblockUI();
            },
            error: function () {
                alert("未取得资料!");
            }
        });

    });

    // Show DDZL
    $("#showDD").click(function () {
        $("#down").load("DDZL");
    });

});