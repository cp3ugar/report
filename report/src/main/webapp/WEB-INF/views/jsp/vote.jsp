<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="Module">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>投票系统</title>
    <meta charset="UTF-8"/>
    <script type="text/javascript" src="../../../js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="../../../layui/css/layui.css">
    <script type="text/javascript" src="../../../layui/layui.js"></script>
    <script type="text/javascript" src="../../../js/vote.js"></script>
</head>
<body>
<div class="layui-row">
    <input hidden value="${sessionScope.userId}" id="userId">
    <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
        <table id="voteList" lay-filter="test" lay-data="{id: 'voteList'}"></table>
    </div>
</div>
</body>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button id="generateVote" class="layui-btn layui-btn-radius layui-btn-primary">发起投票</button>
        <button id="detailVote" class="layui-btn layui-btn-radius layui-btn-primary">查看投票</button>
    </div>
</script>
</html>
