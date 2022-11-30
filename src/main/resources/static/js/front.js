// for front.html

$(document).ready(function () {

    // 基本资料
    // 类别明细资料
    $("#lbzls").click(() => {
        $("#content").load("lbzls");
    });

    // 模具明细资料
    $("#MJZLList").click(() => {
        $("#content").load("MJZLList");
    });

    // 基础配方资料
    $("#clzlsz").click(() => {
        $("#content").load("clzlsz");
    });


    // 型體资料
    // 部位明细资料
    // $("#bwList").click(() => {
    //     $("#content").load("bwList");
    // });

    // 部位规格建立
    $("#bwBuild").click(() => {
        $("#content").load("bwBuild");
    });

    // 型体部位對照
    $("#XXZLList").click(() => {
        $("#content").load("XXZLList");
    });

    // 領料庫存作業
    // 領料作業
    $("#LLZL").click(() => {
        $("#content").load("LLZL");
    });

});