<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html ng-app="Module">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>学生列表</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="">
    <script type="text/javascript" src="../../js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="../../layui/css/layui.css">
    <script type="text/javascript" src="../../layui/layui.js"></script>
</head>
<style>
    #textDiv {
        position:absolute;
        top:50%;
        left:50%;
        transform: translate(-50%,-50%);
        color: white;
        font-size: 100px;
    }
</style>
<body background="../../img/bg.jpg">
<div id="textDiv">
    <a href="/toListStudent">
        <i id="in" class="layui-icon layui-icon-release" style="font-size:80px;color: white">   ${now}</i>
    </a>
</div>
</body>
