// for manager.html

$(document).ready(function () {

    // 订单管理
    // 订单管理作业
    $("#DDZL").click(() => {
        $("#content").load("DDZL");
    });

    // 订单鞋型合并
    $("#ZLZL").click(() => {
        $("#content").load("ZLZL");
    });

    // 派工作业
    // 萬馬力派工作业
    $("#PGZL").click(() => {
        $("#content").load("PGZL");
    });

    // 大車派工作业
    $("#PGZLdc").click(() => {
        $("#content").load("PGZLdc");
    });

    // 打粗派工作业
    $("#PGZLrg").click(() => {
        $("#content").load("PGZLrg");
    });

    // 鞋墊派工作业
    $("#PGZLsl").click(() => {
        $("#content").load("PGZLsl");
    });

    // 發料單列印作业
    $("#PGZLS1").click(() => {
        $("#content").load("PGZLS1");
    });


});