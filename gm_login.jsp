<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<title>管理员登录界面</title>
</head>
<body>
<form action="UserServlet" method="post">
<table border="1" align="center">
<caption>管理员登录：</caption>
 <tr> 
    <td>管理员ID：</td> 
    <td><input type="text" name="id" /> 
    </td> 
   </tr> 
   <tr> 
    <td>密码：</td> 
    <td><input type="password" name="password" /> 
    </td> 
   </tr> 
   <tr> 
    <td colspan="2"><input type="submit" name="action" value="管理员登录" />  
    </td> 
   </tr> 
</table>
</form>
</body>
</html>