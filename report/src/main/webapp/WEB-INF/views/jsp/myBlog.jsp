<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html ng-app="Module">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Welcome</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="">
    <script type="text/javascript" src="../../../js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="../../../layui/css/layui.css">
    <script type="text/javascript" src="../../../layui/layui.js"></script>
    <script type="text/javascript" src="../../../js/myBlog.js"></script>
</head>
<body>
<div>
    <table id="myBlog" lay-filter="test" lay-data="{id: 'idTest'}"></table>
    <input hidden value="${sessionScope.userId}" id="userId">
</div>
</body>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <%--        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>--%>
        <%--        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>--%>
        <%--        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>--%>
        <button id="addBlog" type="button" class="layui-btn layui-btn-sm">添加博客</button>
        <button id="detailBlog" type="button" class="layui-btn layui-btn-sm">查看博客</button>
        <button id="editBlog" type="button" class="layui-btn layui-btn-sm">编辑博客</button>
        <button id="commentBlog" type="button" class="layui-btn layui-btn-sm">评论博客</button>
    </div>
</script>
<script type="text/html" id="barDemo">
<%--    <a class="layui-btn layui-btn- layui-btn-xs" lay-event="edit">编辑</a>--%>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
<%--    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="comment">评论</a>--%>
</script>