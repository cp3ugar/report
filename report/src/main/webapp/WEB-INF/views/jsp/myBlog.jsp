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
</head>
<body>
<div>
    <table id="myBlog" lay-filter="test"></table>
</div>
</body>
<script>
    window.onload = function () {
        layui.use(['form', 'layedit', 'laydate', 'upload', 'table', 'layer'], function () {
            var form = layui.form
                , layer = layui.layer
                , laydate = layui.laydate
                , $ = layui.jquery
                , table = layui.table
                , layer = layui.layer
                , upload = layui.upload;

            //博客列表
            table.render({
                elem: '#myBlog'
                , cellMinWidth: 80
                , url: '/listBlog'
                , method: 'get'
                , page: {
                    layout: ['prev', 'page', 'next', 'count', 'limit', 'skip']
                    , groups: 3
                }
                , skin: 'line' //行边框风格
                , even: true //开启隔行背景
                , size: 'lg' //小尺寸的表格
                , toolbar: '#toolbarDemo'
                , defaultToolbar: ['filter', 'exports']
                , cols: [[ //表头
                    {type: 'checkbox', fixed: 'left'}
                    , {field: 'id', title: 'ID', sort: true, fixed: 'left'}
                    , {field: 'content', title: '内容'}
                    , {field: 'comment', title: '评论'}
                ]]
            });
        })
    }
</script>