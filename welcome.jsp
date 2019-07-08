<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="beans.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书城</title>
</head>
<style type="text/css">
<!--
table{border-spacing:150px 10px}
.margin{width:50px}
-->
</style>
<body>
<p>欢迎进入书城</p>
<div align="left">
<a href="welcome.jsp">返回首页</a>
</div>
<div align="right">
<a href="cart.jsp">我的购物车</a>
<a href="show_order.jsp">我的订单</a>
点击<a href="logout.jsp">注销</a>退出登录
</div>
<form action="ShoppingServlet" method="post">
<table>
<tr>
	<td>
		<span><img src="图1.jpg"></span><br>
		<span>福尔摩斯探案集 &nbsp;  ￥99</span><br>
	</td>
	<td>
		<span><img src="图2.jpg"></span><br>
		<span>死灵之书&nbsp;  ￥88</span><br>
	</td>
	<td>
		<span><img src="图3.jpg"></span><br>
		<span>解忧杂货店&nbsp;  ￥77</span><br>
	</td>
</tr>
</table>
<b>书籍</b>
<select name="disk">
	<option>福尔摩斯探案集 |99</option>
	<option>死灵之书 |88</option>
	<option>解忧杂货店 |77</option>
</select>
<b>数量</b>
<input type="text" name="quantity" value="1" size="4">
<input type="hidden" name="action" value="ADD">
<input type="submit" name=Submit value="加入购物车">
</form>
</body>
</html>