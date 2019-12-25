<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html ng-app="Module">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>学生列表</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="">
    <script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
</head>
<body>
<table align='center' border='1' cellspacing='0'>
    <tr>
        <td>学号</td>
        <td>姓名</td>
        <td>性别</td>
        <td>年龄</td>
        <td>生日</td>
    </tr>
    <c:forEach items="${students}" var="s" varStatus="st">
        <tr>
            <td>${s.jgmc}</td>
            <td>${s.laqs}</td>
            <td>${s.sex}</td>
            <td>${s.age}岁</td>
            <td>${s.birthday}</td>
        </tr>
    </c:forEach>
</table>
<div id="addTable"></div>
<input id="showAdd" type="button" value="添加学生">
<input id="addStudent" type="button" value="提交" hidden="hidden">
</body>
<script>
    window.onload=function(){
        var showAdd = document.getElementById("showAdd");
        showAdd.onclick = function(){
            var addTable = "<div>学号：<input id=\"studentId\">\n" +
                "    姓名：<input id=\"name\">\n" +
                "    性别：<input id=\"sex\">\n" +
                "    年龄：<input id=\"age\">\n" +
                "    生日：<input id=\"birthday\"><div>";
            $("#addTable").append(addTable);
            $("#showAdd").hide();
            $("#addStudent").show();
        }


        var addStudent = document.getElementById("addStudent");
        addStudent.onclick = function(){
            var studentId = $("#studentId").val();
            var name = $("#name").val();
            var sex = $("#sex").val();
            var age = $("#age").val();
            var birthday = $("#birthday").val();
            $.ajax({
                url : '/addStudent',
                type : 'post',
                data:{
                    "studentId":studentId,
                    "name":name,
                    "sex":sex,
                    "age":age,
                    "birthday":birthday
                },
                success : function(data) {
                    alert(data);
                }
            })
        }
    }
</script>