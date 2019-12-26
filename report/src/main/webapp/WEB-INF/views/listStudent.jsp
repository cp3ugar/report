<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="Module">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>学生列表</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="/layui/layui.js"></script>
    <script type="text/javascript" src="/js/listStudent.js"></script>
</head>
<body>
<div>
    <table id="student" lay-filter="test"></table>
</div>
<button id="exportStu" type="button" class="layui-btn layui-btn-primary">导出</button>
<div id="addTable" hidden="hidden">
    <%--学号：<input id="studentId" type='text'>--%>
    <div class="layui-form-item">
        <label class="layui-form-label">学号</label>
        <div class="layui-input-block">
            <input id="studentId" type="text" name="username" lay-verify="required" lay-reqtext="学号是必填项，岂能为空？"
                   placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <%--姓名：<input id="name" type='text'>--%>
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" id="name" name="username" lay-verify="required" lay-reqtext="姓名是必填项，岂能为空？"
                   placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <%--性别：<select id="sex">
        <option value="男">男</option>
        <option value="女">女</option>
        </select>--%>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <select id="sex" name="interest" lay-filter="aihao">
                <option value=""></option>
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>
    </div>
    <%--年龄：<input id="age" type='number'>--%>
    <div class="layui-form-item">
        <label class="layui-form-label">年龄</label>
        <div class="layui-input-block">
            <input id="age" type="number" name="username" lay-verify="required" lay-reqtext="年龄是必填项，岂能为空？"
                   placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <%--生日：<input id="birthday" type='date'>--%>
    <div class="layui-inline">
        <label class="layui-form-label">生日</label>
        <div class="layui-input-inline">
            <input type="text" name="date" id="birthday" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
</div>
    <div>
        <%--<input id="showAdd" type="button" value="添加学生">--%>
        <button id="showAdd" type="button" class="layui-btn layui-btn-primary">添加学生</button>
        <button id="addStudent" type="button" class="layui-btn layui-btn-primary" hidden="hidden">提交</button>
        <button id="cancelAdd" type="button" class="layui-btn layui-btn-primary" hidden="hidden">取消</button>
        <%--<input id="addStudent" type="button" value="提交" hidden="hidden">
        <input id="cancelAdd" type="button" value="取消" hidden="hidden">--%>
    </div>
<%--    <div id="showImport">
        导入:<input id="stuFile" type="file" multiple="multiple">
        &lt;%&ndash;    <button id="stuFile" class="layui-btn" type="file" multiple="multiple">导入</button>&ndash;%&gt;
&lt;%&ndash;        <input id="importStu" type="button" value="导入">&ndash;%&gt;
        <button id="importStu" type="button" class="layui-btn layui-btn-primary">导入</button>
    </div>--%>
<div class="layui-upload" id="showImport">
    <button id="stuFile" type="button" class="layui-btn layui-btn-normal">选择文件</button>
    <button type="button" class="layui-btn" id="importStu">开始上传</button>
</div>
</body>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
