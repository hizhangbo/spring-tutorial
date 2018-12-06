var login = (function () {

    var _load = function () {
        _getUserInfo();
    }

    var _getUserInfo = function () {
        var ip = returnCitySN["cip"];
        var city = returnCitySN["cname"];
        var info = new Browser();

        var explorerType = info.browser
        var explorerVersion = info.version
        var os = info.os + ' ' + info.osVersion
        var device = info.device
        var language = info.language

        var param = {
            ip: ip,
            city: city,
            explorerType: explorerType,
            explorerVersion: explorerVersion,
            os: os,
            device: device,
            language: language
        }

        $.post("/Home/Visitor", param, null)
    }

    var _signin = function () {
        $.post("/Bob/Manager/SignIn", { name: $("#inputEmail").val(), password: $("#inputPassword").val() }, function (data, status) {
            if (status == "success") {
                if (data.status == "success") {
                    window.location.href = "/Bob/Manager/Index";
                }
            }
        }, "json");
    }

    return {
        Load : _load,
        SignIn : _signin
    }

})();

$(function () {
    login.Load();
})