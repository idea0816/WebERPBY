// for bwBuild.html

$(document).ready(function () {

    // 設定確認視窗中的按鈕文字、每個JS都要加、不然不會有作用
    alertify.set({
        labels: {
            ok: "确认",
            cancel: "放弃"
        }
    });

    // CRUD button
    $("#buttonAdd").attr("disabled", true);
    $("#buttonDel").attr("disabled", true);
    $("#buttonSave").attr("disabled", true);
    $("#buttonQuit").attr("disabled", true);
    $("#buttonAdd2").attr("disabled", true);
    $("#buttonDel2").attr("disabled", true);
    $("#buttonSave2").attr("disabled", true);
    $("#buttonQuit2").attr("disabled", true);

    // input text
    $("#textlbdh").attr("disabled", true);
    $("#mjChoice").attr("disabled", true);
    $("#textColor").attr("disabled", true);
    $("#mjChoice2").attr("disabled", true);
    $("#hwChoice").attr("disabled", true);
    $("#textWidth").attr("disabled", true);
    $("#textThickness").attr("disabled", true);
    $("#textColor2").attr("disabled", true);
    $("#textbwName").attr("disabled", true);
    $("#rList").attr("disabled", true);

    // 宣告
    var lb; // 類別
    var nowNO; // 目前取得的NO
    var delNO; // 預存要刪的NO
    var getSizeNo; // 取得Size資料的No
    var oldsglData = []; // 預存XXZLS_BB舊資料



    $("#bwTable tr").click(function () {
        if ($("#lbChoice").val() == "") {
            alertify.alert("请先選擇部位類別!!");
        } else {
            // Table 不顯示
            $("#ListMJ").hide();
            $("#ListDCMJ").hide();
            $("#ListBWPF").hide();
            $("#bwInput").hide();
            $("#bg2").hide();
            $("#sizeInputTable").hide();
            $("#InputTable2").hide();

            // Clear
            $("#mjChoice").val("");
            $("#textColor").val("");
            $("#mjChoice2").val("");
            $("#hwChoice").val("");
            $("#textWidth").val("");
            $("#textThickness").val("");
            $("#textColor2").val("");


            // 取得類別 //  96,底模 //  97,大車 //  98,中底 //  99,合併部件
            let showlb = $("#lbChoice").val();
            if (showlb == "底模") {
                lb = "96";
            } else if (showlb == "大車") {
                lb = "97";
            } else if (showlb == "中底") {
                lb = "98";
            } else {
                lb = "99";
            }

            let tr = $(this);   // 找到目前click的 TR
            let td = tr.find("td");
            // Table 點擊後背景顏色改變(顏色的設定在CSS裡)
            let trs = $(this).parent().find("tr");  // 取得所有的tr
            if (trs.hasClass("on")) {   // 判斷有沒有Class on
                trs.removeClass("on");
            }
            $(this).addClass("on");

            // Get NO
            $("#textlbdh").val(td[0].innerText)
            nowNO = lb + td[0].innerText;

            if (lb == "96") {
                // 底模表格
                // 移除舊有表格
                $("#ListMJ td").parent().remove();/*加上parent()避免出現空白格*/
                $.ajax({
                    type: "GET",
                    contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                    url: "/bwBuild/getList/" + nowNO,
                    dataType: "json",
                    success: function (getList) {
                        $.each(getList, function (key, value) {

                            $("#ListMJ").append("<tr class='" + value.mjbh + "' onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                                "<td class='tsmall'>" + value.no + "</td>" +
                                "<td class='tsmall'>" + value.mjbh + "</td>" +
                                "<td class='tsmall'>" + value.yssm + "</td>" +
                                "</tr>");

                            // $("#mjSearch").append("<option value='" + value.mjbh + "'></option>");
                        });
                        $("#ListMJ").show();
                        $("#96input").show();
                        $("#mjChoice").attr("disabled", false);
                        $("#textColor").attr("disabled", false);
                        $("#ListDCMJ").hide();
                        $("#97input").hide();
                        // Button
                        $("#buttonAdd").val("新增");
                        $("#buttonAdd").attr("disabled", false);
                        $("#bg1").show();
                    },
                    beforeSend: function () {
                        $.blockUI();
                    },
                    complete: function () {
                        $.unblockUI();
                    },
                    error: function () {
                        alert("模具無资料！請新增資料");
                        // 輸入框
                        $("#96input").show();
                        $("#mjChoice").attr("disabled", false);
                        $("#textColor").attr("disabled", false);
                        $("#97input").hide();
                        // Button Change
                        $("#bg1").show();
                        $("#buttonAdd").val("添加");
                        $("#buttonAdd").attr("disabled", false);
                        $("#buttonSave").val("存档");

                    }
                });

                // Search 
                let searchrows;
                $("#divMJ").on("click", "#ListMJ tr", function () {
                    searchrows = $(this).parent().find("tr");
                });

                $("#search").keyup(function () {

                    console.log(searchrows);
                    let val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();

                    searchrows.show().filter(function () {
                        let text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
                        return !~text.indexOf(val);
                    }).hide();
                });
            } else {
                // 大車表格
                // 移除舊有表格
                $("#ListDCMJ td").parent().remove();/*加上parent()避免出現空白格*/
                $.ajax({
                    type: "GET",
                    contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                    url: "/bwBuild/getList/" + nowNO,
                    dataType: "json",
                    success: function (getList) {
                        $.each(getList, function (key, value) {

                            $("#ListDCMJ").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                                "<td class='tsmall'>" + value.no + "</td>" +
                                "<td class='tsmall'>" + value.mjbh + "</td>" +
                                "<td class='tsmall'>" + value.hw + "</td>" +
                                "<td class='tsmall'>" + value.width + "</td>" +
                                "<td class='tsmall'>" + value.thickness + "</td>" +
                                "<td class='tsmall'>" + value.yssm + "</td>" +
                                "</tr>");
                        });
                        $("#ListMJ").hide();
                        $("#96input").hide();
                        $("#ListDCMJ").show();
                        $("#97input").show();
                        // Button
                        $("#buttonAdd").val("新增");
                        $("#buttonAdd").attr("disabled", false);
                        $("#bg1").show();
                    },
                    beforeSend: function () {
                        $.blockUI();
                    },
                    complete: function () {
                        $.unblockUI();
                    },
                    error: function () {
                        alert("模具無资料！請新增資料");
                        // 輸入框
                        $("#96input").hide();
                        $("#97input").show();
                        $("#mjChoice2").attr("disabled", false);
                        $("#hwChoice").attr("disabled", false);
                        $("#textWidth").attr("disabled", false);
                        $("#textThickness").attr("disabled", false);
                        $("#textColor2").attr("disabled", false);

                        // Button Change
                        $("#bg1").show();
                        $("#buttonAdd").val("添加");
                        $("#buttonAdd").attr("disabled", false);
                        $("#buttonSave").val("存档");

                    }
                });

                // Search 
                let searchrows;
                $("#divMJ").on("click", "#ListDCMJ tr", function () {
                    searchrows = $(this).parent().find("tr");
                });

                $("#search").keyup(function () {

                    console.log(searchrows);
                    let val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();

                    searchrows.show().filter(function () {
                        let text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
                        return !~text.indexOf(val);
                    }).hide();
                });
            }


        }

    });

    // 底模模具列表
    // 动态添加内容后、会写上需要用到的事件、比如click事件。所以当添加第n条数据时、已经绑定了n次点击事件、绑定一直都在、而这个绑定被保存在一个叫做异步事件队列的地方。
    // 只要把绑定的事件解绑就可以了。jQuery提供 $(’#file_list’).off(‘click’)方法解绑
    // $(document).off("click", "#ListMJ tr");
    // Table Click to  Get Data // 動態產生input會無法觸發click、所以對父元件綁定事件、也可以全域綁(把父元件刪掉就好)
    $("#divMJ").on("click", "#ListMJ tr", function () {

        let tr = $(this);   // 找到目前click的 TR
        let td = tr.find("td");
        // Table 點擊後背景顏色改變(顏色的設定在CSS裡)
        let trs = $(this).parent().find("tr");  // 取得所有的tr
        if (trs.hasClass("on")) {   // 判斷有沒有Class on
            trs.removeClass("on");
        }
        $(this).addClass("on");

        // Get NO
        let no = nowNO + "*" + td[0].innerText;
        delNO = no;
        getSizeNo = no;

        // 移除舊有表格
        $("#ListBWPF td").parent().remove();/*加上parent()避免出現空白格*/
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/bwBuild/getP/" + no,
            dataType: "json",
            success: function (getBWList) {
                $.each(getBWList, function (key, value) {

                    $("#ListBWPF").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                        "<td>" + value.yssm + "</td>" +
                        "<td>" + value.cldh + "</td>" +
                        "<td class='delCol' style='display: none;'>" +
                        "<input class='delColButton' type='button' value='Del' style='font-size: 0.75rem; width: 2rem;' />" +
                        "</td>" +
                        "</tr>");
                });
                $("#mjChoice").val(td[1].innerText);
                $("#textColor").val(td[2].innerText);
                $("#96input").show();
                $("#97input").hide();
                $("#buttonAdd").attr("disabled", false);
                $("#buttonDel").attr("disabled", false);
                $("#buttonSave").val("修改");
                $("#buttonSave").attr("disabled", false);

                $("#ListBWPF").show();
                $("#sizeInputTable").hide();
                $("#bg2").show();
                $("#buttonAdd2").val("新增");
                $("#buttonAdd2").attr("disabled", false);

            },
            beforeSend: function () {
                $.blockUI();
            },
            complete: function () {
                $.unblockUI();
            },
            error: function () {
                alert("部位無资料！請新增資料");
                // 變更資料
                $("#mjChoice").val(td[1].innerText);
                $("#textColor").val(td[2].innerText);
                $("#mjChoice").attr("disabled", false);
                $("#textColor").attr("disabled", false);
                $("#96input").show();
                $("#97input").hide();
                $("#bg1").show();
                $("#buttonAdd").attr("disabled", false);
                $("#buttonDel").attr("disabled", false);
                $("#buttonSave").val("修改");
                $("#buttonSave").attr("disabled", false);

                $("#ListBWPF").show();
                $("#textbwName").val("");
                $("#textbwName").attr("disabled", false);
                $("#rChoice").val("");
                $("#rChoice").attr("disabled", false);
                $("#bwInput").show();
                $("#bg2").show();
                $("#buttonAdd2").val("添加");
                $("#buttonAdd2").attr("disabled", false);
                $("#sizeInputTable").hide();
                $("#InputTable2").hide();

                // Gram Clear
                let rows = $("#sizeInputTable tr");
                for (let i = 1, len = rows.length; i < len; i++) {
                    let td = $(rows[i]).children("td");
                    td.each(function (j) {
                        td.eq(j).find("input").val("");
                    });
                }
                $("#buttonDel").attr("disabled", false);
                $("#sizeInputTable").show();
            }
        });

    });

    // 大車模具列表
    $("#divMJ").on("click", "#ListDCMJ tr", function () {

        let tr = $(this);   // 找到目前click的 TR
        let td = tr.find("td");
        // Table 點擊後背景顏色改變(顏色的設定在CSS裡)
        let trs = $(this).parent().find("tr");  // 取得所有的tr
        if (trs.hasClass("on")) {   // 判斷有沒有Class on
            trs.removeClass("on");
        }
        $(this).addClass("on");

        // Get NO
        let no = nowNO + "*" + td[0].innerText;
        delNO = no;
        getSizeNo = no;

        // 移除舊有表格
        $("#ListBWPF td").parent().remove();/*加上parent()避免出現空白格*/
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/bwBuild/getP/" + no,
            dataType: "json",
            success: function (getBWList) {
                $.each(getBWList, function (key, value) {

                    $("#ListBWPF").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                        "<td>" + value.yssm + "</td>" +
                        "<td>" + value.cldh + "</td>" +
                        "<td class='delCol' style='display: none;'>" +
                        "<input class='delColButton' type='button' value='Del' style='font-size: 0.75rem; width: 2rem;' />" +
                        "</td>" +
                        "</tr>");
                });
                // input 
                $("#mjChoice2").val(td[1].innerText);
                $("#hwChoice").val(td[2].innerText);
                $("#textWidth").val(td[3].innerText);
                $("#textThickness").val(td[4].innerText);
                $("#textColor2").val(td[5].innerText);
                $("#mjChoice2").attr("disabled", false);
                $("#hwChoice").attr("disabled", false);
                $("#textWidth").attr("disabled", false);
                $("#textThickness").attr("disabled", false);
                $("#textColor2").attr("disabled", false);
                $("#96input").hide();
                $("#97input").show();
                $("#bg1").show();
                $("#buttonAdd").attr("disabled", false);
                $("#buttonDel").attr("disabled", false);
                $("#buttonSave").val("修改");
                $("#buttonSave").attr("disabled", false);


                $("#ListBWPF").show();
                $("#sizeInputTable").hide();
                $("#InputTable2").hide();
                $("#textbwName").val("");
                $("#textbwName").attr("disabled", false);
                $("#rChoice").val("");
                $("#bwInput").show();
                $("#bg1").show();
                $("#bg2").show();
                $("#buttonAdd2").val("新增");
                $("#buttonAdd2").attr("disabled", false);
            },
            beforeSend: function () {
                $.blockUI();
            },
            complete: function () {
                $.unblockUI();
            },
            error: function () {
                alert("部位無资料！請新增資料");
                // 變更資料
                $("#mjChoice2").val(td[1].innerText);
                $("#hwChoice").val(td[2].innerText);
                $("#textWidth").val(td[3].innerText);
                $("#textThickness").val(td[4].innerText);
                $("#textColor2").val(td[5].innerText);
                $("#mjChoice2").attr("disabled", false);
                $("#hwChoice").attr("disabled", false);
                $("#textWidth").attr("disabled", false);
                $("#textThickness").attr("disabled", false);
                $("#textColor2").attr("disabled", false);
                $("#96input").hide();
                $("#97input").show();
                $("#bg1").show();
                $("#buttonAdd").attr("disabled", false);
                $("#buttonDel").attr("disabled", false);
                $("#buttonSave").val("修改");
                $("#buttonSave").attr("disabled", false);

                $("#ListBWPF").show();
                $("#sizeInputTable").hide();
                $("#InputTable2").hide();
                $("#textbwName").val("");
                $("#textbwName").attr("disabled", false);
                $("#rChoice").val("");
                $("#rChoice").attr("disabled", false);
                $("#bwInput").show();
                $("#bg1").show();
                $("#bg2").show();
                $("#buttonAdd2").val("添加");
                $("#buttonAdd2").attr("disabled", false);

                // Gram & Length Clear
                let rowsGL = $("#InputTable2 tr");
                for (let i = 1, len = rowsGL.length; i < len; i++) {
                    let td = $(rowsGL[i]).children("td");
                    td.each(function (j) {
                        td.eq(j).find("input").val("");
                    });
                }
                $("#InputTable2").show();
            }
        });

    });

    // 部位配方列表列表
    $("#divBWPF").on("click", "#ListBWPF tr", function () {

        let tr = $(this);   // 找到目前click的 TR
        let td = tr.find("td");
        // Table 點擊後背景顏色改變(顏色的設定在CSS裡)
        let trs = $(this).parent().find("tr");  // 取得所有的tr
        if (trs.hasClass("on")) {   // 判斷有沒有Class on
            trs.removeClass("on");
        }
        $(this).addClass("on");
        // Get cldh & YSSM
        let cldh = td[1].innerText;
        let YSSM = (td[0].innerText).replace("#", "%23");

        //for Size
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/bwBuild/getSG/" + getSizeNo + "/" + cldh + "/" + YSSM,
            dataType: "json",
            success: function (getSG) {
                // 重量資料
                let gramData = [];
                // 長度資料
                let lengthData = [];
                $.each(getSG, function (key, value) {
                    gramData.push(value.g01);
                    lengthData.push(value.g02);
                });

                if (lb == "96") {
                    // 重量資料
                    let rows = $("#sizeInputTable tr");
                    let count = 0;
                    for (let i = 1, len = rows.length; i < len; i++) {
                        let td = $(rows[i]).children("td");
                        td.each(function (j) {
                            td.eq(j).find("input").val(gramData[count]);
                            count += 1;
                            if (count == 10) {
                                return false;
                            }
                        });
                    }
                    $("#sizeInputTable").show();

                } else {
                    // 重量&長度資料
                    let rows2 = $("#InputTable2 tr");
                    let count2 = 0;
                    let count3 = 0
                    for (let i = 1, len = rows2.length; i < len; i++) {
                        let td = $(rows2[i]).children("td");
                        if ((i % 3) - 1 == 0) {
                            td.each(function (j) {
                                td.eq(j).find("input").val(gramData[count2]);
                                count2 += 1;
                                if (count2 == 10) {
                                    return false;
                                }
                            });
                        } else if ((i % 3) - 1 == 1) {
                            td.each(function (j) {
                                td.eq(j).find("input").val(lengthData[count3]);
                                count3 += 1;
                                if (count3 == 10) {
                                    return false;
                                }
                            });
                        }

                    }

                    $("#InputTable2").show();
                }

            },
            beforeSend: function () {
                $.blockUI();
            },
            complete: function () {
                $.unblockUI();
            },
            error: function () {
                alert("未取得SIZE资料");
            }
        });
        $("#bg2").show();
        $("#textbwName").attr("disabled", true);
        $("#rChoice").attr("disabled", true);
        $("#bwInput").show();
        $("#buttonAdd2").attr("disabled", false);
        $("#buttonAdd2").val("修改");

        $("#textbwName").val(td[0].innerText);
        $("#rChoice").val(td[1].innerText);
    });

    // buttonAdd-第一組
    $("#buttonAdd").click(function () {
        $("#buttonDel").attr("disabled", true);
        $("#buttonSave").attr("disabled", false);
        $("#buttonQuit").attr("disabled", false);

        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/bwBuild/getVersion/" + nowNO,
            dataType: "text",
            success: function (newVersion) {
                if ($("#buttonAdd").val() == "修改") {
                    $("#buttonAdd").val("添加");
                    $("#mjChoice").attr("disabled", false);
                    $("#textColor").attr("disabled", false);
                } else if ($("#buttonAdd").val() == "新增") {
                    $("#buttonAdd").val("添加");
                    $("#buttonSave").val("存档");
                    if (lb == "96") {
                        // 底模
                        $("#mjChoice").attr("disabled", false);
                        $("#textColor").attr("disabled", false);
                        $("#mjChoice").val("");
                        $("#textColor").val("");
                        $("#96input").show();
                        $("#97input").hide();
                        $("#bwInput").hide();
                        $("#bg2").hide();
                        $("#divBWPF").hide();
                        $("#sizeInputTable").hide();
                        $("#InputTable2").hide();
                    } else {
                        $("#mjChoice2").attr("disabled", false);
                        $("#hwChoice").attr("disabled", false);
                        $("#textWidth").attr("disabled", false);
                        $("#textThickness").attr("disabled", false);
                        $("#textColor2").attr("disabled", false);
                        $("#mjChoice2").val("");
                        $("#hwChoice").val("");
                        $("#textWidth").val("");
                        $("#textThickness").val("");
                        $("#textColor2").val("");

                        $("#96input").hide();
                        $("#97input").show();
                        $("#bwInput").hide();
                        $("#bg2").hide();
                        $("#divBWPF").hide();
                        $("#sizeInputTable").hide();
                        $("#InputTable2").hide();

                    }
                } else {
                    if (lb == "96") {
                        // 底模
                        $("#ListMJ").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                            "<td>" + newVersion + "</td>" +
                            "<td>" + $("#mjChoice").val() + "</td>" +
                            "<td>" + $("#textColor").val() + "</td>" +
                            "<td class='delCol' style='display: none;'>" +
                            "<input class='delColButton' type='button' value='Del' style='font-size: 0.75rem; width: 2rem;' />" +
                            "</td>" +
                            "</tr>"
                        );
                        // Button
                        $("#buttonAdd").attr("disabled", true);

                        // 輸入框
                        $("#96input").hide();
                        $("#ListMJ").show();

                    } else {
                        // 大車
                        $("#ListDCMJ").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                            "<td class='tsmall'>" + newVersion + "</td>" +
                            "<td class='tsmall'>" + $("#mjChoice2").val() + "</td>" +
                            "<td class='tsmall'>" + $("#hwChoice").val() + "</td>" +
                            "<td class='tsmall'>" + $("#textWidth").val() + "</td>" +
                            "<td class='tsmall'>" + $("#textThickness").val() + "</td>" +
                            "<td class='tsmall'>" + $("#textColor2").val() + "</td>" +
                            "<td class='delCol' style='display: none;'>" +
                            "<input class='delColButton' type='button' value='Del' style='font-size: 0.75rem; width: 2rem;' />" +
                            "</td>" +
                            "</tr>"
                        );
                        // Button
                        $("#buttonAdd").attr("disabled", true);

                        // 輸入框
                        $("#97input").hide();
                        $("#ListDCMJ").show();
                    }
                }
            },
            beforeSend: function () {
                $.blockUI();
            },
            complete: function () {
                $.unblockUI();
            },
            error: function () {
                alert("未取得新項次！");
            }
        });
    });

    // buttonAdd-第二組
    $("#buttonAdd2").click(function () {
        $("#buttonDel2").attr("disabled", false);
        $("#buttonSave2").attr("disabled", false);
        $("#buttonQuit2").attr("disabled", false);

        if ($("#buttonAdd2").val() == "修改") {
            // 預存oldsglData
            oldsglData = [];
            oldsglData.push({ no: getSizeNo, yssm: $("#textbwName").val(), cldh: $("#rChoice").val() })
            $("#textbwName").attr("disabled", false);
            $("#rChoice").attr("disabled", false);
            $("#bwInput").show();

            $("#buttonAdd2").val("新增");

        } else if ($("#buttonAdd2").val() == "新增") {
            $("#buttonAdd2").val("添加");
            $("#bwInput").show();
            // Clear
            $("#textbwName").val("");
            $("#textbwName").attr("disabled", false);
            $("#rChoice").val("");
            if (lb == "96") {
                // Gram Clear
                let rows = $("#sizeInputTable tr");
                for (let i = 1, len = rows.length; i < len; i++) {
                    let td = $(rows[i]).children("td");
                    td.each(function (j) {
                        td.eq(j).find("input").val("");
                    });
                }
                $("#sizeInputTable").show();
            } else {
                // Gram & Length Clear
                let rowsGL = $("#InputTable2 tr");
                for (let i = 1, len = rowsGL.length; i < len; i++) {
                    let td = $(rowsGL[i]).children("td");
                    td.each(function (j) {
                        td.eq(j).find("input").val("");
                    });
                }
                $("#InputTable2").show();
            }

        } else {
            $("#ListBWPF").show();

            $("#ListBWPF").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                "<td>" + $("#textbwName").val() + "</td>" +
                "<td>" + $("#rChoice").val() + "</td>" +
                "<td class='delCol' style='display: none;'>" +
                "<input class='delColButton' type='button' value='Del' style='font-size: 0.75rem; width: 2rem;' />" +
                "</td>" +
                "</tr>"
            );

            // Button
            $("#buttonAdd2").attr("disabled", true);

            // 輸入框
            $("#bwInput").hide();
        }

    });

    // buttonDel-第一組
    $("#buttonDel").click(function () {
        // $(".delCol").show(1000);
        alertify.confirm("确认删除 ? (部位、尺寸相关资料全部删除、无法回复）", function (e) {

            if (e) {
                // OK、送出DELETE
                $.ajax({
                    type: "DELETE",
                    contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                    url: "/bwBuild/deleteXXZLS1_B/" + delNO,
                    data: "json",
                    dataType: "text",

                    success: function (message) {
                        $("#96input").hide();
                        $("#97input").hide();
                        $("#bg1").hide();
                        $("#ListMJ").hide();
                        $("#ListDCMJ").hide();
                        $("#bwInput").hide();
                        $("#bg2").hide();
                        $("#ListBWPF").hide();
                        $("#sizeInputTable").hide();
                        $("#InputTable2").hide();
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
            } else {
                alertify.success("放弃删除");
            }
        });

    });

    // buttonDel-第二組
    $("#buttonDel2").click(function () {
        alertify.confirm("确认删除部位：" + $("#textbwName").val() + ", 配方：" + $("#rChoice").val() + " ? (部位、尺寸相关资料全部删除、无法回复）", function (e) {
            let delData = [];
            delData.push({ no: getSizeNo, yssm: $("#textbwName").val(), cldh: $("#rChoice").val(), cc: "", g01: "", g02: "" });

            if (e) {
                // OK、送出DELETE
                $.ajax({
                    type: "DELETE",
                    contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                    url: "/bwBuild/deleteXXZLS1_BB/",
                    data: JSON.stringify(delData), // 因為jQuery安全性升高、所以要這樣寫,
                    dataType: "text",
                    success: function (message) {
                        $("#bwInput").hide();
                        $("#bg2").hide();
                        $("#ListBWPF").hide();
                        $("#sizeInputTable").hide();
                        $("#InputTable2").hide();
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
            } else {
                alertify.success("放弃删除");
            }
        });

    });

    // buttonSave-第一組
    $("#buttonSave").click(function () {
        if ($("#buttonSave").val() == "存档") {
            let mjData = [];
            alertify.confirm("确认存儲?", function (e) {
                if (e) {
                    if (lb == "96") {
                        let rows = $("#ListMJ tr");
                        for (let i = 1, len = rows.length; i < len; i++) {
                            // get ListMJ Data
                            let realNO = nowNO + "*" + $(rows[i]).children()[0].innerText;
                            let mjbh = $(rows[i]).children()[1].innerText;
                            let color = $(rows[i]).children()[2].innerText;

                            // 取得資料
                            mjData.push({ no: realNO, mjbh: mjbh, hw: '', width: '', thickness: '', yssm: color });
                        }
                    } else {
                        let rowsDC = $("#ListDCMJ tr");
                        for (let i = 1, len = rowsDC.length; i < len; i++) {
                            // get ListDCMJ Data
                            let realNO = nowNO + "*" + $(rowsDC[i]).children()[0].innerText;
                            let mjbh = $(rowsDC[i]).children()[1].innerText;
                            let hw = $(rowsDC[i]).children()[2].innerText;
                            let width = $(rowsDC[i]).children()[3].innerText;
                            let thickness = $(rowsDC[i]).children()[4].innerText;
                            let color = $(rowsDC[i]).children()[5].innerText;

                            // 取得資料
                            mjData.push({ no: realNO, mjbh: mjbh, hw: hw, width: width, thickness: thickness, yssm: color });
                        }
                    }
                    // OK、送出Insert // POST-Insert、 创建，会产生新的数据，则用POST
                    $.ajax({
                        type: "POST",
                        contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                        url: "/bwBuild/insertbwBuild/",
                        data: JSON.stringify(mjData), // 因為jQuery安全性升高、所以要這樣寫
                        dataType: "text",
                        success: function (message) {
                            alertify.success("存儲成功!!");
                            $("#buttonAdd").val("新增");
                            $("#buttonAdd").attr("disabled", false);
                            $("#buttonDel").attr("disabled", true);
                            $("#buttonSave").attr("disabled", true);
                            $("#buttonQuit").attr("disabled", true);
                            // Clear
                            $("#mjChoice").val("");
                            $("#textColor").val("");
                            $("#mjChoice2").val("");
                            $("#hwChoice").val("");
                            $("#textWidth").val("");
                            $("#textThickness").val("");
                            $("#textColor2").val("");

                        },
                        beforeSend: function () {
                            $.blockUI();
                        },
                        complete: function () {
                            $.unblockUI();
                        },
                        error: function () {
                            alert("存儲出问题、请检查资料");
                        }
                    });

                } else {
                    alertify.success("放弃存儲");
                }
            });
        } else {
            // delNO 代用修改要的NO
            // alert(delNO);
            let mjData = [];

            alertify.confirm("确认修改?", function (e) {
                if (e) {
                    if (lb == "96") {
                        // get ListMJ Data
                        let realNO = delNO;
                        let mjbh = $("#mjChoice").val();
                        let color = $("#textColor").val();

                        // 取得資料
                        mjData.push({ no: realNO, mjbh: mjbh, hw: '', width: '', thickness: '', yssm: color });
                    } else {
                        let rowsDC = $("#ListDCMJ tr");
                        for (let i = 1, len = rowsDC.length; i < len; i++) {
                            // get ListDCMJ Data
                            let realNO = delNO;
                            let mjbh = $("#mjChoice2").val();
                            let hw = $("#hwChoice").val();
                            let width = $("#textWidth").val();
                            let thickness = $("#textThickness").val();
                            let color = $("#textColor2").val();

                            // 取得資料
                            mjData.push({ no: realNO, mjbh: mjbh, hw: hw, width: width, thickness: thickness, yssm: color });
                        }
                    }
                    // OK、送出Update // Update、更新，并不会产生新的数据，新的数据会覆盖老的数据，用PUT
                    $.ajax({
                        type: "PUT",
                        contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                        url: "/bwBuild/updatebwBuild/",
                        data: JSON.stringify(mjData), // 因為jQuery安全性升高、所以要這樣寫
                        dataType: "text",
                        success: function (message) {
                            alertify.success("修改成功!!");

                            // Clear
                            $("#mjChoice").attr("disabled", true);
                            $("#textColor").attr("disabled", true);
                            $("#96input").hide();
                            $("#mjChoice2").attr("disabled", true);
                            $("#hwChoice").attr("disabled", true);
                            $("#textWidth").attr("disabled", true);
                            $("#textThickness").attr("disabled", true);
                            $("#textColor2").attr("disabled", true);
                            $("#97input").hide();
                            $("#bg1").hide();
                            $("#bg2").hide();
                            $("#ListMJ").hide();
                            $("#ListDCMJ").hide();
                            $("#bwInput").hide();
                            $("#ListBWPF").hide();
                            $("#sizeInputTable").hide();
                            $("#InputTable2").hide();
                            $("#mjChoice").val("");
                            $("#textColor").val("");
                            $("#mjChoice2").val("");
                            $("#hwChoice").val("");
                            $("#textWidth").val("");
                            $("#textThickness").val("");
                            $("#textColor2").val("");
                            $("#buttonSave").val("存档");

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
        }

    });

    // buttonSave-第二組
    $("#buttonSave2").click(function () {

        // sgData.push({ no: getSizeNo, yssm: $("#textbwName").val(), cldh: $("#rChoice").val() });


        alertify.confirm("确认新增?", function (e) {
            if (e) {
                let gramData = [];
                if (lb == "96") {
                    // Gram Save
                    let rows = $("#sizeInputTable tr");
                    for (let i = 1, len = rows.length; i < len; i++) {
                        let td = $(rows[i]).children("td");
                        td.each(function (j) {
                            gramData.push({ no: getSizeNo, yssm: $("#textbwName").val(), cldh: $("#rChoice").val(), cc: "", g01: td.eq(j).find("input").val(), g02: "" });
                        });
                    }
                } else {
                    // Input2 Save
                    let rowslength = $("#InputTable2 tr");
                    let tempgramData = [];
                    let templengthData = [];
                    for (let i = 1, len = rowslength.length; i < len; i++) {
                        let td = $(rowslength[i]).children("td");


                        if ((i % 3) - 1 == 0) {
                            td.each(function (j) {
                                tempgramData.push(td.eq(j).find("input").val());
                            });
                        } else if ((i % 3) - 1 == 1) {
                            td.each(function (j) {
                                templengthData.push(td.eq(j).find("input").val());
                            });
                        }
                    }
                    for (let x = 0, len = tempgramData.length; x < len; x++) {
                        gramData.push({ no: getSizeNo, yssm: $("#textbwName").val(), cldh: $("#rChoice").val(), cc: "", g01: tempgramData[x], g02: templengthData[x] });
                    }

                }

                // OK、先送出Delete、再送出Insert // POST-Insert、 创建，会产生新的数据，则用POST
                // OK、送出DELETE
                $.ajax({
                    type: "DELETE",
                    contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                    url: "/bwBuild/deleteXXZLS1_BB/",
                    data: JSON.stringify(oldsglData), // 因為jQuery安全性升高、所以要這樣寫,
                    dataType: "text",
                    success: function (message) {
                        // console.log("删除成功");
                        $.ajax({
                            type: "POST",
                            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                            url: "/bwBuild/insertXXZLS1_BB/",
                            data: JSON.stringify(gramData), // 因為jQuery安全性升高、所以要這樣寫
                            dataType: "text",
                            success: function (message) {
                                alertify.success("新增成功!!");
                                $("#buttonAdd2").val("新增");
                                $("#buttonAdd2").attr("disabled", false);
                                $("#buttonDel2").attr("disabled", true);
                                $("#buttonSave2").attr("disabled", true);
                                $("#buttonQuit2").attr("disabled", true);
                                // Clear
                                $("#textbwName").val("");
                                $("#rChoice").val("");

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


            } else {
                alertify.success("放弃新增");
            }
        });
    });

    // buttonQuit-第一組
    $("#buttonQuit").click(function () {
        $("#content").load("bwBuild");
        // location.reload();
    });

    // buttonQuit-第二組
    $("#buttonQuit2").click(function () {
        // Button
        $("#buttonAdd2").val("新增");
        $("#buttonAdd2").attr("disabled", false);
        $("#buttonDel2").attr("disabled", true);
        $("#buttonSave2").attr("disabled", true);
        $("#buttonQuit2").attr("disabled", true);

        $("#ListBWPF").hide();

        // Input Clear
        $("#textbwName").val("");
        $("#rChoice").val("");
        // Gram Clear
        let rows = $("#sizeInputTable tr");
        for (let i = 1, len = rows.length; i < len; i++) {
            let td = $(rows[i]).children("td");
            td.each(function (j) {
                td.eq(j).find("input").val("");
            });
        }
        // Gram & Length Clear
        let rowsGL = $("#InputTable2 tr");
        for (let i = 1, len = rowsGL.length; i < len; i++) {
            let td = $(rowsGL[i]).children("td");
            td.each(function (j) {
                td.eq(j).find("input").val("");
            });
        }
    });

    // Del Col-第一組 // 動態產生input會無法觸發click、所以對父元件綁定事件、也可以全域綁(把父元件刪掉就好)
    $(document).on("click", "#ListMJ .delColButton", function () {
        // 找到目前click的 TR
        let nowTR = $(this).parent().parent();
        // let nowTd = nowTR.find("td");
        // console.log(nowTd[2].innerText);
        nowTR.remove();
        // return false;
    });




    // add keydown event for all inputs
    $(':input').keydown(function (e) {

        if (e.keyCode == 13 /*Enter*/) {

            // focus next input elements
            $(':input:visible:enabled:eq(' + ($(':input:visible:enabled').index(this) + 1) + ')').focus();
            e.preventDefault();
        }

    });



    // 實時監聽 input 
    // $('#forMjSearch').on('input propertychange', function () {

    //     // let count = $(this).val().length;

    //     // if (count > 7) {
    //     let showListMJ = $(this).val();
    //     console.log(showListMJ);

    //     // 遍歷 table 下所有的 class(可以用id取代) ***如果在CSS中有設定class要注意也會影響到
    //     $("#ListMJ [class]").each(function () {
    //         let hidemjbh = $(this).attr('class');
    //         $("." + hidemjbh).show();
    //         if (hidemjbh != showListMJ) {
    //             $("." + hidemjbh).hide();
    //         } else {
    //             $("." + showListMJ).show();
    //         }
    //     });

    //     // }

    // });


});




