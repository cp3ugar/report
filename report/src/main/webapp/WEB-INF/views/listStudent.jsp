<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="Module">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>学生列表</title>
    <meta charset="UTF-8"/>
    <script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <script type="text/javascript" src="/layui/layui.js"></script>
    <script type="text/javascript" src="/js/listStudent.js"></script>
</head>
<body>
<%-- 列表 --%>
<div>
    <table id="stuList" lay-filter="test"></table>
</div>
</body>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
        <button id="addStu" type="button" class="layui-btn layui-btn-sm">添加学生</button>
        <button id="importStu" type="button" class="layui-btn layui-btn-sm">导入学生</button>
<%--        <button id="exportStu" type="button" class="layui-btn layui-btn-sm">导出学生</button>--%>
    </div>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
