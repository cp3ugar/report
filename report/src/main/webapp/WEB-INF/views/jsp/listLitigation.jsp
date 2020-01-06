<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html ng-app="Module">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>贷款列表</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="">
    <script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
</head>
<body>
<table align='center' border='1' cellspacing='0'>
    <tr>
        <form id="sel" action="" method="post">
            <td id="td">
            <select id="bank" >
                <option value ="">请选择银行</option>
            </select>
            </td>
            <td >
                <select id="num" >
                    <option value ="">立案起数</option>
                </select>
            </td>
            <td></td>
            <td></td>
            <td></td>
            <td><input type="submit" id="" name="" /></td>
        </form>
    </tr>

    <tr>
        <td>机构名称</td>
        <td>立案起数</td>
        <td>涉诉贷款起数</td>
        <td>涉案本金</td>
        <td>涉案利息</td>
        <td>判决起数</td>
    </tr>
    <c:forEach items="${litigationLoans}" var="s" varStatus="st">
        <tr>
            <td>${s.jgmc}</td>
            <td>${s.laqs}</td>
            <td>${s.ssdkqs}</td>
            <td>${s.sabj}</td>
            <td>${s.salx}</td>
            <td>${s.pjqs}</td>
        </tr>
    </c:forEach>
</table>

</body>
<script>
    $(function () {
        $.ajax({
            type:"get",
            url:"/listbank",
            dataType:"json",
            success:function (res) {

                var bankobj=res;
                for (var i=0; i<bankobj.length; i++) {
                    $("#bank").append("<option value='+bankobj[i]+'>"+bankobj[i]+"</option>");
                }
            }
        });
        $.ajax({
            type:"get",
            url:"/listnum",
            dataType:"json",
            success:function (res) {

                var numobj=res;
                for (var i=0; i<numobj.length; i++) {
                    $("#num").append("<option value='+numobj[i]+'>"+numobj[i]+"</option>");
                }
            }
        });
        $("#sel").click(function () {
            var _jgmc=$("#bank").val();
            var _laqs=$("#num").val();
            $.ajax({
                type:"get",
                url:"listselect",
                data:{
                    "jgmc":_jgmc,
                    "laqs":_laqs,
                },
                success:function (res) {

                }
            })
        })

    })





</script>