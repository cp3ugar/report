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
<div>
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
            <td>${s.studentId}</td>
            <td>${s.name}</td>
            <td>${s.sex}</td>
            <td>${s.age}岁</td>
            <td>${s.birthday}</td>
        </tr>
    </c:forEach>
</table>
<input id="exportStu" type="button" value="导出">
</div>
<div id="addTable" hidden="hidden">
    学号：<input id="studentId" type='text'>
    姓名：<input id="name" type='text'>
    性别：<select id="sex">
        <option value="男">男</option>
        <option value="女">女</option>
        </select>
    年龄：<input id="age" type='number'>
    生日：<input id="birthday" type='date'>
</div>
<div>
    <input id="showAdd" type="button" value="添加学生">

    <input id="addStudent" type="button" value="提交" hidden="hidden">
    <input id="cancelAdd" type="button" value="取消" hidden="hidden">
</div>
<div id="showImport">
    导入学生:<input id="stuFile" type="file" multiple="multiple">
    <input id="importStu" type="button" value="导入">
</div>
</body>
<script>
    window.onload=function(){
        var exportStu = document.getElementById("exportStu");
        exportStu.onclick = function(){
            $.ajax({
                url : '/export',
                type : 'get',
                success : function(data) {

                }
            })
        }

        var showAdd = document.getElementById("showAdd");
        showAdd.onclick = function(){
            $("#addTable").show();
            $("#showAdd").hide();
            $("#showImport").hide();
            $("#addStudent").show();
            $("#cancelAdd").show();
        }


        var addStudent = document.getElementById("addStudent");
        addStudent.onclick = function(){
            var studentId = $("#studentId").val();
            var name = $("#name").val();
            var sex = $("#sex option:selected").val();
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
                    $("#studentId").val('');
                    $("#name").val('');
                    $("#sex").val('');
                    $("#age").val('');
                    $("#birthday").val('');
                    alert(data.message);
                }
            })
        }

        var cancelAdd = document.getElementById("cancelAdd");
        cancelAdd.onclick = function(){
            $("#showAdd").show();
            $("#showImport").show();
            $("#addStudent").hide();
            $("#cancelAdd").hide();
            $("#addTable").hide();
        }

        var importStu = document.getElementById("importStu");
        importStu.onclick = function(){
            var $file = $("#stuFile").val();//文件
            // 判断文件是否为空
            if ($file == "") {
                alert("请选择上传的目标文件! ")
                return false;
            }
            //判断文件类型
            var fileName = $file.substring($file.lastIndexOf(".") + 1).toLowerCase();
            if(fileName != "xls" && fileName !="xlsx"){
                alert("请选择Excel文件!");
                return false;
            }
            //判断文件大小
            var size = $("#stuFile")[0].files[0].size;
            if (size>104857600) {
                alert("上传文件不能大于100M!");
                return false;
            }

            var formData = new FormData();
            formData.append("file",$('#stuFile')[0].files[0]);
            $.ajax({
                url : '/import1',
                type : 'post',
                dataType:'json',
                data:formData,
                processData : false, // 使数据不做处理
                contentType : false, // 不要设置Content-Type请求头
                success : function(data) {
                    alert(data.message);
                    location.reload();
                }
            })
        }
    }
</script>