<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="beans.UserBean,java.util.*"%>
    <%@ page import="java.sql.* "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书城</title>
</head>
<script type="text/javascript">
function Bsumbit(){
	formid.action="ShoppingServlet";
}
</script>
<body>
<div align="left">
<a href="welcome.jsp">返回首页</a>
</div>
<div align="right">
<a href="cart.jsp">我的购物车</a>
<a href="show_address.jsp">我的订单</a>
点击<a href="logout.jsp">注销</a>退出登录
</div>
<form action="" method="post" name="formid">
<table border="1" width="50%">
<caption>购物车的东西</caption>
<tr>
	<td>商品</td>
	<td>单价</td>
	<td>数量</td>
	<td>总价</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
</tr>
<%
	try{
		String id;
		String action1="jia1",action2="jian1";
		UserBean user=(UserBean)session.getAttribute("userinfo");
		id=user.getId();
		Class.forName("com.mysql.cj.jdbc.Driver");
	       String url="jdbc:mysql://localhost:3306/company?useSSL=FALSE&serverTimezone=GMT";
		Connection conn=DriverManager.getConnection(url,"root","");
		String sql="select * from goods where user_id='"+id+"'";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet result=pstmt.executeQuery();
		while(result!=null&&result.next())
		{
			out.print("<tr><td>"+result.getString("gname")+"</td>");
			out.print("<td>￥"+result.getInt("price")+"元</td>");
			out.print("<td>"+result.getInt("quantity")+"</td>");
			out.print("<td>￥"+result.getInt("total")+"元</td>");
			out.print("<td><a href=\"ShoppingServlet?action="+action1+"&quantityName="+result.getString("gname")+"\">+1</a></td>");
			out.print("<td><a href=\"ShoppingServlet?action="+action2+"&quantityName="+result.getString("gname")+"\">-1</a></td></tr>");
		}
		
%>
</table>
<%
		out.print("<a href=\"order.jsp?action="+id+"\"><input type=\"button\" value=\"生成订单\"></a>");
		result.close();
		pstmt.close();
		conn.close();
		}catch(SQLException e){
			out.print(e);
		}
%>
</form>
</body>
</html>