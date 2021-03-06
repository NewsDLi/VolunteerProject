$(document).ready(function () {
    // 初始化内容
    init();

});

function init() {
    $.post("/function.json",
        function (data, status) {
            if (isEmptyObject(data)) {
                var a = basisUrl("url");
                var b = $.i18n.prop("url");
                window.location.href = b + "/index";
            }
            var text = "";
            for (var i = 0; i < data.userPowers.length; i++) {
                text += "<a href=" + data.userPowers[i].powerUrl + " class='weui-grid'><div class='weui-grid__icon'><img src=" + data.userPowers[i].powerPicUrl + " alt=''></div><p class='weui-grid__label''>" + data.userPowers[i].powerName + "</p></a>";
            }
            $('.weui-grids').html(text);
        });
}

function basisUrl(name) {

    jQuery.i18n.properties({
        name: 'switch',
        path: 'config/',
        mode: 'map',
        encoding: 'UTF-8',
        callback: function () {
            console.log($.i18n.prop(name));
        }
    });
}

function isEmptyObject(obj) {

    for (var key in obj) {
        return false
    }
    ;
    return true
};
