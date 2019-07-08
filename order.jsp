<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="beans.UserBean,java.util.*"%>
     <%@ page import="java.sql.* "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
</head>
<body>
<div align="left">
<a href="welcome.jsp">返回首页</a>
</div>
<div align="right">
<a href="cart.jsp">我的购物车</a>
<a href="show_order.jsp">我的订单</a>
点击<a href="logout.jsp">注销</a>退出登录
</div>
<table border="1" width="50%">
<caption>我的订单</caption>
<tr>
	<td>用户ID</td>
	<td>商品</td>
	<td>单价</td>
	<td>数量</td>
	<td>总价</td>
</tr>
<%
	try{
		UserBean user=(UserBean)session.getAttribute("userinfo");
		String get_address=user.getAddress();	
		String id=request.getParameter("action");	
		Class.forName("com.mysql.cj.jdbc.Driver");
	       String url="jdbc:mysql://localhost:3306/company?useSSL=FALSE&serverTimezone=GMT";
		Connection conn=DriverManager.getConnection(url,"root","");
		int temp=0;
		String sql="select * from goods where user_id='"+id+"'";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet result=pstmt.executeQuery();
		while(result!=null&&result.next())
		{
			String insertStr="insert into orders(user_id,gname,price,quantity,total,get_address) values(?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(insertStr);
			pstmt.setString(1, id);
			pstmt.setString(2, result.getString("gname"));
			pstmt.setInt(3, result.getInt("price"));
			pstmt.setInt(4, result.getInt("quantity"));
			pstmt.setInt(5, result.getInt("total"));
			pstmt.setString(6, get_address);
			temp+=result.getInt("total");
			pstmt.execute();
		}
		String deleteStr="delete from goods where user_id='"+id+"'";	
		pstmt=conn.prepareStatement(deleteStr);
		pstmt.execute();
		String query="select * from orders where user_id='"+id+"'";
		pstmt=conn.prepareStatement(query);
		result=pstmt.executeQuery();
		while(result!=null&&result.next()){
			out.print("<tr><td>"+result.getString("user_id")+"</td>");
			out.print("<td>"+result.getString("gname")+"</td>");
			out.print("<td>￥"+result.getInt("price")+"元</td>");
			out.print("<td>"+result.getInt("quantity")+"</td>");
			out.print("<td>￥"+result.getInt("total")+"元</td></tr>");
		}
		out.print("<tr><td colspan=\"5\">合计:"+temp+"</td></tr>");
		out.print("<tr><td colspan=\"5\">收货地址:"+get_address+"</td></tr>");
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