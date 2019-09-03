$(function(){

    //获取验证码
    $("#imgVerify").prop("src","/login/getVerify?"+Math.random());

    //登录
    $("#dl").click(function(){
        login();
    });

})
var falg = true;
function keyLogin()
{
    if (event.keyCode === 13 && falg)
    {   falg = false;
        login();
    }else if (!falg){
        parent.layer.closeAll();
        falg = true;
    }
}

function login(){
    var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
    if($("#username").val()==""){
        tips("#username","请输入用户名！");
        return false;
    }else if(reg.test($("#username").val())){
        tips("#username","用户名不能包含汉字！");
        return false;
    }
    if($("#password").val()==""){
        tips("#password","请输入密码！");
        return false;
    }else if(reg.test($("#password").val())){
        tips("#password","密码不能包含汉字！");
        return false;
    }
    if($("#verificationCode").val()==""){
        tips("#verificationCode","请输入验证码！");
        return false;
    }
    var username = $("#username").val();
    var password = $("#password").val();
    var inputStr = $("#verificationCode").val();

    $.ajax({
        url: "/login/userLogin",
        type:"POST",
        datatype : "json",
        data: {"username":username,"password":password,"inputStr":inputStr},
        success: function (result) {
            if(result.code === 200){
                location.href="/index";
            }else {
                layer.alert(result.message, {skin: 'layui-layer-molv',icon: 5});
            }
        }
        });
}

//获取验证码
function getVerify(obj){
    obj.src = "/login/getVerify?"+Math.random();
};

//提示
function tips(id,msg){
    $(id).tips({
        side : 2,
        msg : msg,
        bg : '#FF5080',
        time : 3
    });
    $(id).focus();
};