// 控制網頁元件是否可用

$(document).ready(function () {

    // for clzlsl.html
    $("#textcldh").attr("disabled", true);
    $("#textzwpm").attr("disabled", true);

    // CRUD button
    $("#buttonAdd").attr("disabled", true);
    $("#buttonDel").attr("disabled", true);
    $("#buttonSave").attr("disabled", true);
    $("#buttonQuit").attr("disabled", true);

    // 新建配方
    $("#newVR").click(function () {
        $("#textcldh").attr("disabled", false);
        $("#textzwpm").attr("disabled", false);
        $("#buttonAdd").attr("disabled", false);

    });

    // buttonAdd
    $("#buttonAdd").click(function () {
        $("#buttonAdd").attr("disabled", true);
        $("#buttonDel").attr("disabled", false);
        $("#buttonSave").attr("disabled", false);
        $("#buttonQuit").attr("disabled", false);
    });


    // Example
    // $("form input").attr("disabled", true);
    // $(".btnSubmit").attr("disabled", true);

    // $(".btnNew").click(function () {
    //   $("input").attr("disabled", false);
    //   $(".btnSubmit").attr("disabled", false);
    //   $(".btnNew").attr("disabled", true);
    // });

});