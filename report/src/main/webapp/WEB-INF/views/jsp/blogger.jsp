<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html ng-app="Module">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>BLOG登录</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="">
    <script type="text/javascript" src="../../../js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="../../../layui/css/layui.css">
    <script type="text/javascript" src="../../../layui/layui.js"></script>
</head>
<body>

<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input type="text" name="account" id="account" required lay-verify="required" placeholder="请输入用户名"
                   autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux"><a id="register">没有账号？立即注册</a></div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-inline">
            <input type="password" name="password" id="password" required lay-verify="required" placeholder="请输入密码"
                   autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">请牢记密码，避免遗忘</div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" id="login" type="submit" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

</body>
<script>
    layui.use('layer', function () {
        var layer = layui.layer;

        //登录
        $("#login").on("click", function () {
            var account = $("#account").val();
            var password = $("#password").val();
            console.log(account, password);
            $.ajax({
                url: '/blogLogin',
                type: 'get',
                data: {
                    "account": account,
                    "password": password,
                },
                success: function (data) {
                    if (data.code === 0) {
                        location.href = "toMyBlog";
                    } else {

                    }
                }
            })
        });

        //注册
        $(document).on('click', '#register', function () {
            layer.open({
                type: 1,
                skin: 'layui-layer-demo', //样式类名
                // closeBtn: 0, //不显示关闭按钮
                anim: 2,
                edit: 'text',
                area: ['800px', '500px'],
                title: '注册账号',
                btn: ['确定', '取消'],
                shadeClose: false,
                content: '<form class="layui-form" action=""><div id="addTable" style="margin-top: 5%;width:90%">\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">昵称</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input id="name" name="content" lay-verify="required" ' +
                    '              placeholder="请输入" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">账号</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input id="phone" name="content" lay-verify="required" ' +
                    '              maxlength="11" minlength="11"   placeholder="请输入" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">密码</label>\n' +
                    '        <div class="layui-input-block">\n' +
                    '            <input id="pwd" type="password" name="content" lay-verify="required" ' +
                    '                   placeholder="请输入" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '    <div class="layui-form-item">\n' +
                    '        <label class="layui-form-label">验证码</label>\n' +
                    '        <div class="layui-input-inline">\n' +
                    '            <input id="yzmValue" type="text" name="content" lay-verify="required" ' +
                    '                   placeholder="请输入" autocomplete="off" class="layui-input">' +
                    '        </div>' +
                    '       <div class="layui-form-mid layui-word-aux">\n' +
                    '            <a href="javascript:getVerifyCode()">\n' +
                    '                <img id="yzm" style="margin-top: -8px;height:38px" title="点击刷新验证码" src="/getVerifyCode">\n' +
                    '            </a>\n' +
                    '        </div>'+
                    '    </div>\n' +
                    '</div></form>',
                yes: function (index, layero) {
                    $.ajax({
                        url: '/register',
                        type: 'post',
                        data: {
                            name: $("#name").val(),
                            account: $("#phone").val(),
                            password: $("#pwd").val(),
                            "code":$("#yzmValue").val()
                        },
                        success: function (data) {
                            if (data.code == 0) {
                                layer.msg(data.msg);
                                layer.close(index);
                                setTimeout('window.location.reload()', 500);
                            } else {
                                layer.msg(data.msg);
                            }
                        }
                    })
                },
                btn2: function (index, layero) {
                    layer.close(index);
                },
            });
            form.render();
        });
    })

    function getVerifyCode() {
        $("#yzm").prop('src', '/getVerifyCode?a=' + new Date().getTime());
    }
</script>
</html>
