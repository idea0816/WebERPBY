// for XXZLList.html

$(document).ready(function () {
    // 設定確認視窗中的按鈕文字、每個JS都要加、不然不會有作用
    alertify.set({
        labels: {
            ok: "确认",
            cancel: "放弃"
        }
    });

    // 导入鞋厂型体资料(*注意、对应已放行的订单*)
    $.ajax({
        type: "GET",
        contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
        url: "/XXZL/updateDCXXZL/",
        dataType: "text",
        success: function (countImportXXZLData) {
            alertify.success(countImportXXZLData);
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

    // 宣告
    var lb; // 類別
    var BWData = []; // Get BW Data
    // Button Control
    $("#buttonAdd").attr("disabled", true);
    $("#buttonSave").attr("disabled", true);

    $("#getData").click(function () {

        // 移除舊有表格
        $("#pdTable td").parent().remove();/*加上parent()避免出現空白格*/
        // Get BOM Data
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/XXZL/getXXZLSErp/" + $("#articleChoice").val(),
            dataType: "json",
            success: function (getBOM) {
                let getBOMData = getBOM;
                // console.log(getBOMData.length);
                if (getBOMData.length != 2) { // 底廠沒有資料
                    // Button Control
                    $("#buttonAdd").attr("disabled", true);
                    $("#buttonSave").attr("disabled", false);
                    $.each(getBOMData, function (key, value) {
                        $("#textXieXing").val(value.xieXing);
                        $("#textSheHao").val(value.sheHao);

                        $("#pdTable").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                            "<td></td>" +
                            "<td>" + value.bwbh + "</td>" +
                            "<td >" + value.bwZwsm + "</td>" +
                            "<td colspan='4'>" + value.clZwpm + "</td>" +
                            "<td>" + value.daomh + "</td>" +
                            "<td>" + value.xtmh + "</td>" +
                            "</tr>");
                        $("#pdTable").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#8be0f1\"';>" +
                            "<td><input class='get' type='button' value='&#8609;' style='width: 50% ;'></td>" +
                            "<td><input id='lbChoice' list='lbList' type='text' placeholder='类别资料' style='width: 100%;'/>" +
                            "<datalist id='lbList'>" +
                            "<option>底模</option>" +
                            "<option>大車</option>" +
                            "<option>中底</option>" +
                            "<option>合併部件</option>" +
                            "</datalist>" +
                            "</td>" +
                            "<td><input class='bwToGet' list='bwList' type='text' value='' style='width: 95%;'>" +
                            "<datalist id='bwList'>" +
                            "</datalist>" +
                            "</td>" +
                            "<td><input class='noToGet' list='noList' type='text' value='' style='width: 95%;'>" +
                            "<datalist id='noList'>" +
                            "</datalist>" +
                            "</td>" +
                            "<td><input type='text' value='顏色' style='width: 95%; font-size: 0.75rem;' disabled ></td>" +
                            "<td><input type='text' value='底模斬刀' style='width: 95%; font-size: 0.75rem;' disabled ></td>" +
                            "<td><input type='text' value='花輪' style='width: 95%; font-size: 0.75rem;' disabled ></td>" +
                            "<td><input type='text' value='寬度' style='width: 95%;' disabled></td>" +
                            "<td><input type='text' value='厚度' style='width: 95%;' disabled></td>" +
                            "</tr>");
                    });

                } else {
                    // Button Control
                    $("#buttonAdd").attr("disabled", false);
                    $("#buttonSave").attr("disabled", true);
                    // console.log((getBOMData[1][0]).clbh);
                    $("#textXieXing").val((getBOMData[1][0]).xieXing);
                    $("#textSheHao").val((getBOMData[1][0]).sheHao);
                    for (let i = 0; i < getBOMData[0].length; i++) {
                        $("#pdTable").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                            "<td></td>" +
                            "<td>" + (getBOMData[1][i]).bwbh + "</td>" +
                            "<td>" + (getBOMData[1][i]).bwZwsm + "</td>" +
                            "<td colspan='4' >" + (getBOMData[1][i]).clZwpm + "</td>" +
                            "<td>" + (getBOMData[1][i]).daomh + "</td>" +
                            "<td>" + (getBOMData[1][i]).xtmh + "</td>" +
                            "</tr>");
                        // 20220606-C01396C013*015後跟標(前面加上4碼鞋廠的部位代號)
                        let showlb = ((getBOMData[0][i]).yssm).substring(4, 6);
                        if (showlb == "96") {
                            lb = "底模";
                        } else if (showlb == "97") {
                            lb = "大車";
                        } else if (showlb == "98") {
                            lb = "中底";
                        } else {
                            lb = "合併部件";
                        }

                        $("#pdTable").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#8be0f1\"';>" +
                            "<td><input class='get' type='button' value='&#8609;' style='width: 50% ;'></td>" +
                            "<td><input id='lbChoice' list='lbList' type='text' placeholder='类别资料' style='width: 100%;' value='" + lb + "'/>" +
                            "<datalist id='lbList'>" +
                            "<option>底模</option>" +
                            "<option>大車</option>" +
                            "<option>中底</option>" +
                            "<option>合併部件</option>" +
                            "</datalist>" +
                            "</td>" +
                            "<td><input class='bwToGet' list='bwList' type='text' style='width: 95%;' value='" + (getBOMData[0][i]).xieXing + "'/>" +
                            "<datalist id='bwList'>" +
                            "</datalist>" +
                            "</td>" +
                            "<td><input class='noToGet' list='noList' type='text' style='width: 95%;' value='" + (getBOMData[0][i]).cqdh + "'>" +
                            "<datalist id='noList'>" +
                            "</datalist>" +
                            "</td>" +
                            "<td><input type='text' value='" + (getBOMData[0][i]).xh + "' style='width: 95%; font-size: 0.75rem;' disabled ></td>" +
                            "<td><input type='text' value='" + (getBOMData[0][i]).cldh + "' style='width: 95%; font-size: 0.75rem;' disabled ></td>" +
                            "<td><input type='text' value='" + (getBOMData[0][i]).userid + "' style='width: 95%; font-size: 0.75rem;' disabled ></td>" +
                            "<td><input type='text' value='" + (getBOMData[0][i]).userdate + "' style='width: 95%;' disabled></td>" +
                            "<td><input type='text' value='" + (getBOMData[0][i]).lb + "' style='width: 95%;' disabled></td>" +
                            "</tr>");


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
                alert("未取得资料!");
            }
        });

    });

    $("#divPD").on("click", "#pdTable .get", function () {
        let tr = $(this).parent().parent();
        let td = tr.find("td");

        // 取得上一行的資料
        let beforeTr = $(this).parent().parent().prev("tr");
        let beforeTd = beforeTr.find("td");

        // 取得類別 //  96,底模 //  97,大車 //  98,中底 //  99,合併部件
        let showlb = tr.children("td:eq(1)").find("input").val();
        let choicebw = tr.children("td:eq(2)").find("input").val();
        if (showlb == "") {
            alertify.alert("请先選擇部位類別!!");
        } else {
            if (showlb == "底模") {
                lb = "96";
            } else if (showlb == "大車") {
                lb = "97";
            } else if (showlb == "中底") {
                lb = "98";
            } else {
                lb = "99";
            }
            $("#bwList option").remove();
            $.ajax({
                type: "GET",
                contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                url: "/XXZL/getBWbw/" + lb,
                dataType: "json",
                success: function (getBWbw) {
                    // BWData = getBWData;
                    $.each(getBWbw, function (key, value) {
                        let showchoice = value.lbdh + "," + value.zwsm;
                        $("#bwList").append("<option value='" + showchoice + "'></option>");
                    });
                },
                beforeSend: function () {
                    $.blockUI();
                },
                complete: function () {
                    $.unblockUI();
                },
                error: function () {
                    alert("未取得部位资料！");
                }
            });

            if (showlb != "" & choicebw == "") {
                alertify.alert("请選擇部位代號!!");
            } else {
                $("#noList option").remove();
                $.ajax({
                    type: "GET",
                    contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                    // url: "/XXZL/getBWData/" + lb + "/" + beforeTd[1].innerText,
                    url: "/XXZL/getBWData/" + lb + "/" + choicebw.substring(0, 4),
                    dataType: "json",
                    success: function (getBWData) {
                        BWData = getBWData;
                        $.each(getBWData, function (key, value) {
                            $("#noList").append("<option>" + value.no + "</option>");
                        });
                    },
                    beforeSend: function () {
                        $.blockUI();
                    },
                    complete: function () {
                        $.unblockUI();
                    },
                    error: function () {
                        alert("未取得部位资料！");
                    }
                });

            }
        }
    });

    $("#divPD").on("input propertychange", "#pdTable .noToGet", function () {
        let tr = $(this).parent().parent();
        let td = tr.find("td");
        // Table 點擊後背景顏色改變(顏色的設定在CSS裡)
        let trs = $(this).parent().find("tr");  // 取得所有的tr
        if (trs.hasClass("on")) {   // 判斷有沒有Class on
            trs.removeClass("on");
        }
        $(this).addClass("on");

        let choiceNo = $(this).val();
        $.each(BWData, function (key, value) {

            if (value.no == choiceNo) {
                tr.children("td:eq(4)").find("input").val(value.yssm);
                tr.children("td:eq(5)").find("input").val(value.mjbh);
                tr.children("td:eq(6)").find("input").val(value.hw);
                tr.children("td:eq(7)").find("input").val(value.width);
                tr.children("td:eq(8)").find("input").val(value.thickness);
            }

        });
    });

    // buttonAdd
    $("#buttonAdd").click(function () {
        let xxzlsData = [];
        // let getARTICLE = $("#articleChoice").val();

        let rows = $("#pdTable tr");
        for (let i = 3, len = rows.length; i < len; i += 2) {
            // 取得類別 //  96,底模 //  97,大車 //  98,中底 //  99,合併部件
            let lb;
            let showlb = $(rows[i]).children("td:eq(1)").find("input").val();
            if (showlb == "底模") {
                lb = "96";
            } else if (showlb == "大車") {
                lb = "97";
            } else if (showlb == "中底") {
                lb = "98";
            } else {
                lb = "99";
            }
            let getXieXing = $("#textXieXing").val();
            let getSheHao = $("#textSheHao").val();
            let getoldyssm;
            let getnewyssm;
            let yssm;
            let checkData = ($(rows[i]).children("td:eq(2)").find("input").val()).split(",");
            if ($(rows[i]).children("td:eq(2)").find("input").val() == "") {
                // yssm = lb + $(rows[i - 1]).children("td:eq(1)").text() + "*001" + $(rows[i - 1]).children("td:eq(2)").text();
                // 如果沒有資料、則用'00Z000*000'當假資料補入(前端補入)
                getoldyssm = $(rows[i - 1]).children("td:eq(1)").html(); // 鞋廠部位名稱
                yssm = getoldyssm + '00Z000*000';
            } else if (checkData[0] == "Z000") {
                getoldyssm = $(rows[i - 1]).children("td:eq(1)").html(); // 鞋廠部位名稱
                yssm = getoldyssm + '00Z000*000';
            } else {
                getoldyssm = $(rows[i - 1]).children("td:eq(1)").html(); // 鞋廠部位名稱
                getnewyssm = ($(rows[i]).children("td:eq(2)").find("input").val()).split(","); // 底廠部位名稱
                yssm = getoldyssm + lb + getnewyssm[0] + "*" + $(rows[i]).children("td:eq(3)").find("input").val() + getnewyssm[1];
            }

            // 取得資料
            xxzlsData.push({ xieXing: getXieXing, sheHao: getSheHao, CQDH: 'B7U', xh: '', cldh: '', yssm: yssm, userid: '', userdate: '', lb: '', hzs: '', dz: '', rs: '' });
        }

        alertify.confirm("确认修改?", function (e) {
            if (e) {

                // OK、送出Update // Update、更新，并不会产生新的数据，新的数据会覆盖老的数据，用PUT
                $.ajax({
                    type: "PUT",
                    contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                    url: "/XXZL/updateXXZLS/",
                    data: JSON.stringify(xxzlsData), // 因為jQuery安全性升高、所以要這樣寫
                    dataType: "text",
                    success: function (message) {
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


    });

    // buttonSave
    $("#buttonSave").click(function () {
        let xxzlsData = [];
        let getARTICLE = $("#articleChoice").val();

        let rows = $("#pdTable tr");
        for (let i = 3, len = rows.length; i < len; i += 2) {
            // 取得類別 //  96,底模 //  97,大車 //  98,中底 //  99,合併部件
            let lb;
            let showlb = $(rows[i]).children("td:eq(1)").find("input").val();
            if (showlb == "底模") {
                lb = "96";
            } else if (showlb == "大車") {
                lb = "97";
            } else if (showlb == "中底") {
                lb = "98";
            } else {
                lb = "99";
            }
            let getXieXing = $("#textXieXing").val();
            let getSheHao = $("#textSheHao").val();
            let getoldyssm;
            let getnewyssm;
            let yssm;
            if ($(rows[i]).children("td:eq(2)").find("input").val() == "") {
                // yssm = lb + $(rows[i - 1]).children("td:eq(1)").text() + "*001" + $(rows[i - 1]).children("td:eq(2)").text();
                // 如果沒有資料、則用'00Z000*000'當假資料補入(前端補入)
                getoldyssm = $(rows[i - 1]).children("td:eq(1)").html(); // 鞋廠部位名稱
                yssm = getoldyssm + '00Z000*000';
            } else {
                getoldyssm = $(rows[i - 1]).children("td:eq(1)").html(); // 鞋廠部位名稱
                getnewyssm = ($(rows[i]).children("td:eq(2)").find("input").val()).split(",");
                yssm = getoldyssm + lb + getnewyssm[0] + "*" + $(rows[i]).children("td:eq(3)").find("input").val() + getnewyssm[1];
            }

            // 取得資料
            xxzlsData.push({ xieXing: getXieXing, sheHao: getSheHao, CQDH: 'B7U', xh: '', cldh: '', yssm: yssm, userid: '', userdate: '', lb: '', hzs: '', dz: '', rs: '' });
        }

        alertify.confirm("确认存儲?", function (e) {
            if (e) {

                // 取得資料
                // mjData.push({ no: realNO, mjbh: mjbh, hw: hw, width: width, thickness: thickness, yssm: color });

                // OK、送出Insert // POST-Insert、 创建，会产生新的数据，则用POST
                $.ajax({
                    type: "POST",
                    contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                    url: "/XXZL/insertXXZLS/",
                    data: JSON.stringify(xxzlsData), // 因為jQuery安全性升高、所以要這樣寫
                    dataType: "text",
                    success: function (message) {
                        alertify.success("存儲成功!!");
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

    });

});