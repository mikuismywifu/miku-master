$("#gologin").click(function () {
    var account = $("#account_").val().trim();
    var password = $("#password_").val().trim();
    if (account != undefined && password != undefined) {
        $.ajax({
            url: ctxPath + "gologin",
            type: 'POST',
            data: {"email":account,"password":password},
            dataType: "json",
            success: function (result) {
                console.log(result);
                if (result.result == 0) {
                    window.location.href = ctxPath + result.url;
                } else {
                    toastr.error(result.message)
                }
            },
            error:function () {
                toastr.error('系统繁忙、稍后重试');
            }
        });
    } else {
        toastr.error('帐号、密码不正确');
    }
})

$("#goregister").click(function () {
    var account = $("#account_reg").val().trim();
    var password = $("#password_reg").val().trim();
    var name = $("#name_reg").val().trim();
    if (account != undefined && password != undefined) {
        $.ajax({
            url: ctxPath + "goregister",
            type: 'POST',
            data: {"email":account,"password":password,"account":name},
            dataType: "json",
            success: function (result) {
                console.log(result);
                if (result.result == 0) {
                    window.location.href = ctxPath + result.url;
                } else {
                    toastr.error(result.message)
                }
            },
            error:function () {
                toastr.error('系统繁忙、稍后重试');
            }
        });
    } else {
        toastr.error('帐号、密码不正确');
    }
})