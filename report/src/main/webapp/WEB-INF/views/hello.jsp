<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html ng-app="Module">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Welcome</title>
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
<body background="../../img/bg.jpg" onload="startTime()">
<div id="textDiv">
    <a href="/toListStudent">
        <i id="comeIn" class="layui-icon" style="font-size:80px;color: white"></i>
    </a>
</div>
</body>
<script>
    function startTime(){
        var nowTime = new Date();
        var year = nowTime.getFullYear();
        var month = nowTime.getMonth()+1;
        var day = nowTime.getDate();
        var hour = nowTime.getHours();
        var minute = nowTime.getMinutes();
        var second = nowTime.getSeconds();
        month = checkTime(month);
        day = checkTime(day);
        hour = checkTime(hour);
        minute = checkTime(minute);
        second = checkTime(second);
        document.getElementById('comeIn').innerHTML=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
        setInterval(function(){
            startTime()
        },1000)
    }
    function checkTime(i){
        if(i<10){
            i = "0"+i;
        }
        return i;
    }
</script>