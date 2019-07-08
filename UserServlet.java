package beans;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 * Servlet implementation class UserServlet
 */
@SuppressWarnings("unused")
/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String action=request.getParameter("action");
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		if(action==null)action="";
		if(password==null)password="";
		if(id==null)id="";
		UserBean user=new UserBean();
		RequestDispatcher view;
		if(action.equals("登录")) {
			user=queryARecord(id,password);
			if(user!=null) {
				HttpSession session=request.getSession();
				session.setAttribute("userinfo", user);
				view=request.getRequestDispatcher("welcome.jsp");
				view.forward(request, response);
			}
			else {
				view=request.getRequestDispatcher("error1.jsp");
				response.setContentType("text/html;charest=UTF-8");
				view.forward(request, response);
			}
		}
		else if(action.equals("管理员登录")) {
			String gm_id=request.getParameter("id");
			String psw=request.getParameter("password");
			if(queryGM(gm_id,psw)) {
				HttpSession session=request.getSession();
				session.setAttribute("gminfo", gm_id);
				view=request.getRequestDispatcher("gm.jsp");
				view.forward(request, response);
			}
		}
		else if(action.equals("注册")) {
			String name=request.getParameter("name");
			int  age=Integer.valueOf(request.getParameter("age")).intValue();
			String phone=request.getParameter("phone");
			String address=request.getParameter("address");
			String city=request.getParameter("city");
			if(name==null)name="";
			if(phone==null)phone="";
			if(address==null)address="";
			if(city==null)city="";
			user.setId(id);
			user.setPassword(password);
			user.setName(name);
			user.setAge(age);
			user.setPhone(phone);
			user.setAddress(address);
			user.setCity(city);
			if(insertARecord(user)) {
				HttpSession session=request.getSession();
				session.setAttribute("userinfo", user);
				view=request.getRequestDispatcher("index.jsp");
				view.forward(request, response);
			}
			else {
				view=request.getRequestDispatcher("error2.jsp");
				view.forward(request, response);
			}
		}
	}


	private boolean queryGM(String gm_id, String psw) {
		// TODO 自动生成的方法存根
		String queryStr="select * from gm where gm_id=? and password=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(queryStr);
			pstmt.setString(1, gm_id);
			pstmt.setString(2, psw);
			rs=pstmt.executeQuery();
			if(rs!=null&&rs.next()) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResultSet(rs);
			closePstmt(pstmt);
			closeConnection(conn);
		}
		return false;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public Connection getConnection() {
		Connection connection=null;
		String driver="com.mysql.cj.jdbc.Driver";
		String jdbcurl="jdbc:mysql://localhost:3306/company?useSSL=FALSE&serverTimezone=GMT";
		try {
			Class.forName(driver);
			connection=DriverManager.getConnection(jdbcurl,"root","");
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}catch(SQLException e2){
			e2.printStackTrace();
		}
		return connection;
	}
	
	public void closeConnection(Connection connection) {
		try {
			if(connection!=null)
				connection.close();
			connection=null;
		}catch(SQLException e3){
			e3.printStackTrace();
		}
	}
	
	public void closePstmt(PreparedStatement pstmt) {
		try {
			if(pstmt!=null)
				pstmt.close();
			pstmt=null;
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void closeResultSet(ResultSet rs) {
		try {
			if(rs!=null)
				rs.close();
			rs=null;
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	private boolean insertARecord(UserBean user) {
		// TODO 自动生成的方法存根
		String insertStr="insert into myuser(id,name,psw,age,phone,address,city) values(?,?,?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		if(user==null) return false;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(insertStr);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			pstmt.setInt(4, user.getAge());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getAddress());
			pstmt.setString(7, user.getCity());
			if(pstmt.executeUpdate()!=0)return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			closeResultSet(rs);
			closePstmt(pstmt);
			closeConnection(conn);
		}
		return false;
	}

	private UserBean queryARecord(String id, String password) {
		// TODO 自动生成的方法存根
		String queryStr="select * from myuser where id=? and psw=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		UserBean result=null;
		if(id==null||password==null) return result;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(queryStr);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=new UserBean();
				result.setId(rs.getString(1));
				result.setName(rs.getString(2));
				result.setPassword(rs.getString(3));
				result.setAge(rs.getInt(4));
				result.setPhone(rs.getString(5));
				result.setAddress(rs.getString(6));
				result.setCity(rs.getString(7));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResultSet(rs);
			closePstmt(pstmt);
			closeConnection(conn);
		}
		return result;
	}
}