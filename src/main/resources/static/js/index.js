// for index.html

$(document).ready(function () {

    $("#backHome").click(() => {
        location.reload(true);
    });

    $("#front").click(() => {
        $("#main_nav").load("front");
        $("#content").load("blank");
    });

    $("#manager").click(() => {
        $("#main_nav").load("manager");
        $("#content").load("blank");
    });


});