<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>注册页面</title>
</head>
<style type="text/css">
<!--
.p1{font-size:medium;color:red;font-weight:900}
-->
</style>
<body>
<p class="p1" align="center">所有的要求的信息都要填写</p>
<form action="UserServlet" method="post"> 
  <table align="center"> 
   <tr> 
    <td colspan="2">注册窗口</td> 
   </tr> 
   <tr> 
    <td>用户ID：</td> 
    <td><input type="text" name="id" required/></td> 
   </tr> 
   <tr> 
    <td>密码：</td> 
    <td><input type="password" name="password" id="password" required/></td> 
    </tr>
     <tr> 
    <td>确认密码：</td> 
    <td><input type="password" name="repassword" id="repassword" required onblur="check()"/></td> 
    <td><span id="errorpwd" style="display:none;">两次输入密码不一致</span></td>
   </tr>  
   <tr> 
    <td>姓名：</td> 
    <td><input type="text" name="name" required/></td> 
    </tr> 
   <tr> 
    <td>电话：</td> 
    <td><input type="text" name="phone" required/></td> 
   </tr> 
    <tr> 
    <td>年龄：</td> 
    <td><input type="text" name="age" required/></td> 
    </tr> 
    <tr> 
    <td>地址：</td> 
    <td><input type="text" name="address" required/></td> 
    </tr>
     <tr> 
    <td>所在城市：</td> 
    <td><input type="text" name="city" required/></td> 
   </tr>  
  </table> 
  <div align="center">
  	<input type="submit" name="action" value="注册" />
  	<input type="reset" value="clear" />
  	<a href="index.jsp">返回</a>
  </div>
  
 </form> 
 <script language="JavaScript">
 function check(){
	   var password = document.getElementById("password").value;
	   var repsword = document.getElementById("repassword").value;
	   if(password != repsword) {
		   document.getElementById("errorpwd").style.display = "block";
		   return false;
	   }
	    
	}
 </script>
</body>
</html>