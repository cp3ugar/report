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
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-inline">
            <input type="password" name="password" id="password" required lay-verify="required" placeholder="请输入密码"
                   autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">请牢记密码</div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" id="login" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

</body>
<script>
    layui.use('layer', function () {
        var layer = layui.layer;
        $("#login").on("click", function () {
            var account = $("#account").val();
            var password = $("#password").val();
            console.log(account,password);
            $.ajax({
                url: '/blogLogin',
                type: 'get',
                data: {
                    "account": account,
                    "password": password
                },
                success: function (data) {
                    console.log(data);
                    if (data.code == 0) {
                        layer.msg(data.msg);
                    } else {
                        layer.msg(data.msg);
                    }
                }
            })
        });
    })
</script>
</html>
