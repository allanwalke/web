<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
     <%@ page import="java.sql.* "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-UTF-8">
<title>管理员界面</title>
</head>
<body>
<table border="1" width="50%">
<caption>所有订单</caption>
<tr>
	<td>用户ID</td>
	<td>商品</td>
	<td>单价</td>
	<td>数量</td>
	<td>总价</td>
	<td>收货地址</td>
</tr>
<%
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
	       String url="jdbc:mysql://localhost:3306/company?useSSL=FALSE&serverTimezone=GMT";
		Connection conn=DriverManager.getConnection(url,"root","");
		String query="select * from orders ";
		PreparedStatement pstmt=conn.prepareStatement(query);
		ResultSet result=pstmt.executeQuery();
		while(result!=null&&result.next()){
			out.print("<tr><td>"+result.getString("user_id")+"</td>");
			out.print("<td>"+result.getString("gname")+"</td>");
			out.print("<td>￥"+result.getInt("price")+"元</td>");
			out.print("<td>"+result.getInt("quantity")+"</td>");
			out.print("<td>￥"+result.getInt("total")+"元</td>");
			out.print("<td>"+result.getString("get_address")+"</td></tr>");
		}
		result.close();
		pstmt.close();
		conn.close();
	}catch(SQLException e){
		out.print(e);
	}		
%>
</table>
</body>
</html>