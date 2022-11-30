// for PGZLrg.html

$(document).ready(function () {
    // 設定確認視窗中的按鈕文字、每個JS都要加、不然不會有作用
    alertify.set({
        labels: {
            ok: "确认",
            cancel: "放弃"
        }
    });

    // 取得鞋廠輪次資料
    var roundData = [];
    $("#getDd").click(function () {
        // hide
        $("#inputArea").hide();
        $("#bg1").hide();
        $("#smddsArea").hide();
        $("#calArea").hide();
        $("#roundList").hide();
        $("#psTable").hide();
        $("#llPrivewPrint").hide();
        //$("#afPrivewPrint").hide();


        // 移除舊有表格
        $("#ddlcTable td").parent().remove();/*加上parent()避免出現空白格*/
        let pairsCount = 0;
        // 取得鞋廠輪次資料
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/PGZLrg/getSMDD/" + $("#textDdbh").val(),
            dataType: "json",
            success: function (getList) {
                $.each(getList, function (key, value) {

                    $("#ddlcTable").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
                        "<td>" + value.xh + "</td>" +
                        "<td>" + value.planDate + "</td>" +
                        "<td>" + value.qty + "</td>" +
                        "</tr>");
                    // jQuery文字轉數字
                    pairsCount += parseInt(value.qty);
                    roundData.push({ "xh": value.xh, "qty": parseInt(value.qty) });
                });
                $("#pairsCount").html("雙數：" + pairsCount);

            },
            beforeSend: function () {
                $.blockUI();
            },
            complete: function () {
                $.unblockUI();
            },
            error: function () {
                alert("未取得鞋廠輪次資料！請向鞋廠生管確認： \n1.已產生輪次表 \n2.已輸入上線日期 \n3.確認有加入「成型投入」工段 \n4.確認是否已派工過");
            }
        });
    });

    // 輪次明細表格
    // 动态添加内容后、会写上需要用到的事件、比如click事件。所以当添加第n条数据时、已经绑定了n次点击事件、绑定一直都在、而这个绑定被保存在一个叫做异步事件队列的地方。
    // 只要把绑定的事件解绑就可以了。jQuery提供 $(’#file_list’).off(‘click’)方法解绑
    // $(document).off("click", "#ListMJ tr");
    // Table Click to  Get Data // 動態產生input會無法觸發click、所以對父元件綁定事件、也可以全域綁(把父元件刪掉就好)
    $("#divDT").on("click", "#ddlcTable tr", function () {
        // show
        $("#inputArea").show();
        $("#bg1").show();
        $("#smddsArea").show();

        let tr = $(this);   // 找到目前click的 TR
        let td = tr.find("td");
        // Table 點擊後背景顏色改變(顏色的設定在CSS裡)
        let trs = $(this).parent().find("tr");  // 取得所有的tr
        if (trs.hasClass("on")) {   // 判斷有沒有Class on
            trs.removeClass("on");
        }
        $(this).addClass("on");

        let ddbh = $("#textDdbh").val();
        let round = td[0].innerText;

        // 移除舊有表格
        $("#smddsTable td").parent().remove();/*加上parent()避免出現空白格*/
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/PGZL/getSMDDS/" + ddbh + "/" + round,
            dataType: "json",
            success: function (getList) {
                $.each(getList, function (key, value) {
                    $("#smddsTable").append("<tr>" +
                        "<td>" + value.userid + "</td>" +
                        "<td>" + value.xxcc + "</td>" +
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
                alert("輪次表無资料！請連絡鞋廠生管");
            }

        });
    });

    // 派工Table Click to  Get Data
    $("#pgTable tr").click(function () {
        // show
        $("#inputArea").show();
        $("#calArea").show();
        $("#roundList").show();
        $("#psTable").show();

        // clear roundList 的Table
        let roundListRow = $("#roundList tr");
        for (let j = 1; j <= 5; j++) {
            $(roundListRow[0]).children("td:eq(" + j + ")").find("input").val("");
        }

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
        $("#pgSize td").parent().remove();/*加上parent()避免出現空白格*/
        $("#pgTablePrint td").parent().remove();/*加上parent()避免出現空白格*/
        let getlc = [];

        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/PGZLrg/getPGZLS/" + pgdate,
            dataType: "json",
            success: function (getList) {

                $.each(getList, function (key, value) {
                    $("#textClass").val(value.gssm);
                    $("#textTeam").val(value.userid);
                    $("#textCtime").val(value.userdate);
                    $("#textDdbh").val(value.cldh);
                    getlc.push({ "cldh": value.cldh, "lc": value.cqdh });
                });


            },
            beforeSend: function () {
                $.blockUI();
            },
            complete: function () {
                $.each(getlc, function (key, value) {
                    // 移除舊有表格
                    $("#smddsTable td").parent().remove();/*加上parent()避免出現空白格*/
                    $.ajax({
                        type: "GET",
                        contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                        url: "/PGZL/getSMDDS/" + value.cldh + "/" + value.lc,
                        dataType: "json",
                        success: function (getList) {
                            $.each(getList, function (key, value) {
                                $("#smddsTable").append("<tr>" +
                                    "<td>" + value.userid + "</td>" +
                                    "<td>" + value.xxcc + "</td>" +
                                    "<td>" + value.qty + "</td>" +
                                    "</tr>");
                            });
                        },
                        beforeSend: function () {
                            $.blockUI();
                        },
                        complete: function () {
                            // 取得 smddsTable 資料
                            let smddsTableRow = $("#smddsTable tr");
                            let round = "";
                            let S = []; // SMDDS Table's size
                            let SD = []; // SMDDS Table Data
                            let newPSD = []; // 寫回 pgSize 的資料
                            let pgPairs = 0; // 派工雙數
                            for (let i = 1, len = smddsTableRow.length; i < len; i++) {
                                round = $(smddsTableRow[i]).find("td").eq(0).html();
                                let size = ($(smddsTableRow[i]).find("td").eq(1).html());
                                let qty = ($(smddsTableRow[i]).find("td").eq(2).html());
                                S.push(size);
                                SD.push({ "size": size, "qty": qty });

                            }
                            // 排重(這個可能會有問題)
                            let newS = S.filter(function (el, i, S) {
                                return S.indexOf(el) === i;
                            });

                            // 排序 newS.sort(); 
                            newS.sort(function (a, b) {
                                return a - b; // a - b > 0
                            });
                            for (let i = 0; i < newS.length; i++) {
                                let newQty = 0;
                                $.each(SD, function (key, value) {
                                    if (value.size == newS[i]) {
                                        newQty += parseInt(value.qty);
                                    }
                                });
                                newPSD.push({ "newSize": newS[i], "newQty": newQty });
                            }
                            // 移除舊有表格
                            $("#pgSize td").parent().remove();/*加上parent()避免出現空白格*/
                            $.each(newPSD, function (key, value) {
                                $("#pgSize").append("<tr>" +
                                    "<td>" + value.newSize + "</td>" +
                                    "<td>" + value.newQty + "</td>" +
                                    "</tr>");

                                pgPairs += parseInt(value.newQty);
                            });
                            $("#pgPairs").val(pgPairs);
                            // 寫入 roundList 的Table
                            let roundListRow = $("#roundList tr");
                            for (let j = 1; j <= 5; j++) {
                                if ($(roundListRow[0]).children("td:eq(" + j + ")").find("input").val() == "") {
                                    $(roundListRow[0]).children("td:eq(" + j + ")").find("input").val(round);
                                    break;
                                }
                            }

                            $.unblockUI();
                        },
                        error: function () {
                            alert("輪次表無资料！請連絡鞋廠生管");
                        }

                    });

                });
                // 排序有很多要注意的地方、要再研究
                // S.sort(function (a, b) {
                //     return a - b; // a - b > 0
                // });

                $.unblockUI();
            },
            error: function () {
                alert("派工無资料！請新增資料");
            }
        });


    });



    // buttonPG
    $("#buttonPG").click(function () {

        if ($("#textDate").val() == "") {
            alertify.alert("请先選擇派工日期!!");
        } else if ($("#textClass").val() == "") {
            alertify.alert("请先選擇班別!!");
        } else {
            // show
            $("#calArea").show();
            $("#roundList").show();
            $("#psTable").show();

            // 取得派工單號(D+2位年+月日+3位流水碼、共10碼)
            $.ajax({
                type: "GET",
                contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                url: "/PGZL/getVersion/R",
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

            // 取得 smddsTable 資料
            let smddsTableRow = $("#smddsTable tr");
            let S = []; // SMDDS Table's size
            let SD = []; // SMDDS Table Data
            let PS = []; // pgSize Table's size
            let PSD = []; // pgSize Table Data
            let newPSD = []; // 寫回 pgSize 的資料
            let round = "";
            let pgPairs = 0; // 派工雙數
            // let checkRow = 0;
            for (let i = 1, len = smddsTableRow.length; i < len; i++) {
                round = $(smddsTableRow[i]).find("td").eq(0).html();
                let size = ($(smddsTableRow[i]).find("td").eq(1).html());
                let qty = ($(smddsTableRow[i]).find("td").eq(2).html());
                S.push(size);
                SD.push({ "size": size, "qty": qty });

            }

            // 取得 pgSize 資料
            let pgSizeRow = $("#pgSize tr");
            if (pgSizeRow.length <= 1) {

                $.each(SD, function (key, value) {
                    $("#pgSize").append("<tr>" +
                        "<td>" + value.size + "</td>" +
                        "<td>" + value.qty + "</td>" +
                        "</tr>");
                    pgPairs += parseInt(value.qty);
                });

            } else {
                let pgSizeRow = $("#pgSize tr");
                for (let i = 1, len = pgSizeRow.length; i < len; i++) {
                    let size = ($(pgSizeRow[i]).find("td").eq(0).html());
                    let qty = ($(pgSizeRow[i]).find("td").eq(1).html());
                    S.push(size);
                    PSD.push({ "size": size, "qty": qty });
                }

                // 排重(這個可能會有問題)
                let newS = S.filter(function (el, i, S) {
                    return S.indexOf(el) === i;
                });

                // 排序 newS.sort(); 
                newS.sort(function (a, b) {
                    return a - b; // a - b > 0
                });
                for (let i = 0; i < newS.length; i++) {
                    let newQty = 0;
                    $.each(SD, function (key, value) {
                        if (value.size == newS[i]) {
                            newQty += parseInt(value.qty);
                        }
                    });
                    $.each(PSD, function (key, value) {
                        if (value.size == newS[i]) {
                            newQty += parseInt(value.qty);
                        }
                    });
                    newPSD.push({ "newSize": newS[i], "newQty": newQty });
                }
                // 移除舊有表格
                $("#pgSize td").parent().remove();/*加上parent()避免出現空白格*/
                $.each(newPSD, function (key, value) {
                    $("#pgSize").append("<tr>" +
                        "<td>" + value.newSize + "</td>" +
                        "<td>" + value.newQty + "</td>" +
                        "</tr>");

                    pgPairs += parseInt(value.newQty);
                });

            }
            $("#pgPairs").val(pgPairs);
            // 寫入 roundList 的Table
            let roundListRow = $("#roundList tr");
            for (let j = 1; j <= 5; j++) {
                if ($(roundListRow[0]).children("td:eq(" + j + ")").find("input").val() == "") {
                    $(roundListRow[0]).children("td:eq(" + j + ")").find("input").val(round);
                    break;
                }
            }
        }

    });

    // buttonSave
    $("#buttonSave").click(function () {
        let rowsRD = $("#roundList tr");
        let tdRD = $(rowsRD[0]).children("td");
        let cldh;
        let pgzlData = [];
        let pgzlsData = []; // 預存資料-放這裡沒有意義只是防傳值錯誤        
        // PGDATE   派工編號			
        // CQDH 輪次
        // cldh 訂單號碼
        // KGS  雙數
        // PGSS		
        // KGS_RKS				
        // GSSM	班別
        // USERID   鞋廠組別
        // USERDATE 時間段
        tdRD.each(function (i) {
            // let getqty = 0;
            if (tdRD.eq(i).find("input").val() != "" && typeof (tdRD.eq(i).find("input").val()) != "undefined") {
                cldh = $("#textDdbh").val();
                let cqdh = tdRD.eq(i).find("input").val();
                // 取得派工明細資料
                pgzlsData.push({ cldh: cldh, cqdh: cqdh, gssm: $("#textClass").val(), userid: $("#textTeam").val(), userdate: $("#textCtime").val() });
            }

        });
        // 取得派工表頭資料
        pgzlData.push({ pgdate: $("#textPGNO").val(), date1: $("#textDate").val(), bz: "RG" });

        alertify.confirm("确认新增?", function (e) {
            if (e) {
                // OK、送出Insert // POST-Insert、 创建，会产生新的数据，则用POST
                $.ajax({
                    type: "POST",
                    contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
                    url: "/PGZLrg/insertPGZL/",
                    data: JSON.stringify({ "pgzl": pgzlData, "pgzls": pgzlsData }), // 因為jQuery安全性升高、所以要這樣寫
                    dataType: "text",
                    success: function (message) {
                        $("#content").load("PGZLrg");
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
        $("#content").load("PGZLrg");
    });

    // buttonbornPG-生成派工單
    $("#buttonbornPG").click(function () {
        // show
        $("#buttonpgPrint").attr("disabled", false);

        // 清空 afPrintZone
        // $("#dcpgPrint td").parent().remove();/*加上parent()避免出現空白格*/
        $("#afPrintZone").empty();

        let getListData = [];
        let sizeList = [];
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8", // 要寫、不然會報 415 Error
            url: "/PGZLrg/getrgpgPrint/" + $("#textDdbh").val() + "/" + $("#textPGNO").val(),
            dataType: "json",
            success: function (getList) {
                getListData = getList;
                sizeList = getList[2];

                // 網頁上資料降碼
                let sizeTrans = [];
                let psrows = $("#pgSize tr");
                for (let t = 0, len = psrows.length; t < len; t++) {
                    let size = ($(psrows[t]).find("td").eq(0).html());
                    let qty = ($(psrows[t]).find("td").eq(1).html());
                    sizeTrans.push({ "size": size, "qty": qty });
                }
                for (let u = 0; u < sizeTrans.length; u++) {
                    for (let v = 0; v < getListData[3].length; v++) {
                        if (sizeTrans[u].size == getListData[3][v].xxcc) {
                            // console.log(sizeTrans[u].size);
                            // console.log("xxcc :" + getListData[3][v].xxcc + "," + getListData[3][v].gjcc);
                            sizeTrans[u].size = getListData[3][v].gjcc;
                            // console.log(sizeTrans[u].size + "," + sizeTrans[u].qty);
                            break;
                        }
                    }
                }

                // get XXZLS1_B Data
                for (let j = 0; j < getListData[1].length; j++) {
                    let bwdh = "";
                    let bwdhcode = "";
                    for (let i = 0; i < getListData[0].length; i++) {
                        // 20220606-C01396C013*015後跟標(前面加上4碼鞋廠的部位代號)
                        // D01797D017*003前九齒
                        if (((getListData[0][i]).yssm).substring(4, 14) == getListData[1][j].no) {
                            bwdh = (getListData[0][i].yssm.substring(6, 10)) + (getListData[0][i].yssm.substring(14));
                            // console.log(bwdh);
                            bwdhcode = (getListData[0][i].yssm.substring(0, 4));
                            break;
                        }
                    }
                    sizeList = getList[2];
                    let prSize = "";
                    let prQty = "";


                    $.each(sizeList, function (key, value) {
                        if (bwdhcode == key && value.length != 0) {
                            for (let x = 0; x < value.length; x++) {

                                for (let y = 0; y < sizeTrans.length; y++) {
                                    if (value[x] == sizeTrans[y].size && !(sizeTrans[y].qty == "")) {
                                        // let size = value[x]; // 已降碼
                                        prSize += "<td>" + value[x] + "</td>";
                                        // prQty += "<td>" + $(psrows[x]).find('td').eq(1).html() + "</td>";
                                        prQty += "<td>" + sizeTrans[y].qty + "</td>";
                                        break;
                                    }
                                }

                            }

                            let rdrows = $("#roundList tr");
                            let tdRD = $(rdrows[0]).children("td");

                            $("#afPrintZone").append(
                                "<div class='page'>" +
                                "<label class='label_title'>" +
                                "<h2>打粗派工單</h2>" +
                                "</label>" +
                                "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +
                                "<table id='dcpgPrint'>" +
                                "<tr>" +
                                "<td>組別： " + $("#textTeam").val() + "</td>" +
                                "<td>派工單號： " + $("#textPGNO").val() + "</td>" +
                                "<td>派工日期： " + $("#textDate").val() + "</td>" +
                                "</tr>" +
                                "<tr>" +
                                "<td>訂單號碼： " + $("#textDdbh").val() + "</td>" +
                                "<td>成型時段： " + $("#textCtime").val() + "</td>" +
                                "<td>ARTICLE : " + getListData[0][0].cqdh + "</td>" +
                                "</tr>" +
                                "<tr>" +
                                "<td>部位代號： " + bwdh + "</td>" +
                                "<td>花輪： " + getListData[1][j].hw + "</td>" +
                                "<td>底模： " + getListData[1][j].mjbh + "</td>" +
                                "</tr>" +
                                "<tr>" +
                                "<td>寬度： " + getListData[1][j].width + "</td>" +
                                "<td>厚度： " + getListData[1][j].thickness + "</td>" +
                                "<td>顏色： " + getListData[1][j].yssm + "</td>" +
                                "</tr>" +
                                "<tr>" +
                                "<td colspan='3'>物料說明：" + getListData[1][j].bwlb + "</td>" +
                                "</tr>" +
                                "</table>" +
                                "<br>" +
                                "<hr style='height:1px;border:none;border-top:1px dashed #0066CC;' />" +

                                "<table>" +
                                "<tr>" +
                                "<td>輪次：</td>" +
                                "<td>" + tdRD.eq(1).find("input").val() + "</td>" +
                                "<td>" + tdRD.eq(2).find("input").val() + "</td>" +
                                "<td>" + tdRD.eq(3).find("input").val() + "</td>" +
                                "<td>" + tdRD.eq(4).find("input").val() + "</td>" +
                                "<td>" + tdRD.eq(5).find("input").val() + "</td>" +
                                "</tr>" +
                                "<tr>" +
                                "<td>派工雙數：</td>" +
                                "<td>" + $(rdrows[1]).children("td").eq(1).find("input").val() + "</td>" +
                                "<td>訂單總雙數：</td>" +
                                "<td>" + + getListData[0][0].lb + "</td>" +
                                "</tr>" +
                                "</table><br>" +
                                "<table>" +
                                "<tr>" +
                                "<td>尺寸：</td>" +
                                prSize +
                                "<tr>" +
                                "<td>雙數：</td>" +
                                prQty +
                                "</tr>" +
                                "</table>" +
                                "</div>"
                            );
                        }
                    });
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

    // buttonpgPrint
    $("#buttonpgPrint").click(function () {
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