package beans;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.StringTokenizer;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class ShoppingServlet
 */
@WebServlet("/ShoppingServlet")
@SuppressWarnings("unused")
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session=request.getSession();
		String action=request.getParameter("action");
		if(action==null) action="";
		RequestDispatcher view;
		UserBean user=(UserBean)session.getAttribute("userinfo");
		String id=user.getId();
		if(action.equals("ADD")) {
			Product product=getProduct(request);
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				String queryStr="select * from goods where user_id='"+id+"' and gname='"+product.getName()+"'";
				conn=getConnection();
				pstmt=conn.prepareStatement(queryStr);
				rs=pstmt.executeQuery();
				if(rs!=null&&rs.next()) {
					int a=rs.getInt("quantity")+product.getQuantity();
					int b=rs.getInt("total")+product.getTotal();
					String sql="update goods set quantity='"+a+"',total='"+b+"' where user_id='"+id+"' and gname='"+product.getName()+"'";
				 	pstmt.executeUpdate(sql);
				}
				else {
					insertGood(product,id);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				closeResultSet(rs);
				closePstmt(pstmt);
				closeConnection(conn);
			}
			view=request.getRequestDispatcher("welcome.jsp");
			view.forward(request, response);
		}
		
		else if(action.equals("jia1")) {
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String quantityName=null;
			quantityName=request.getParameter("quantityName");
			System.out.println(quantityName);
			if(quantityName!=null) {
			try {
				String queryStr="select * from goods where user_id='"+id+"' and gname='"+quantityName+"'";
				conn=getConnection();
				pstmt=conn.prepareStatement(queryStr);
				rs=pstmt.executeQuery();
				if(rs!=null&&rs.next()) {
					int a=rs.getInt("quantity")+1;
					int b=rs.getInt("total")+rs.getInt("price");
					String sql="update goods set quantity='"+a+"',total='"+b+"' where user_id='"+id+"' and gname='"+quantityName+"'";
					pstmt.executeUpdate(sql);	
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				closeResultSet(rs);
				closePstmt(pstmt);
				closeConnection(conn);
			}
			}
			view=request.getRequestDispatcher("cart.jsp");
			view.forward(request, response);
		}
		
		else if(action.equals("jian1")) {
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String quantityName=null;
			quantityName=request.getParameter("quantityName");
			System.out.println(quantityName);
			if(quantityName!=null) {
			try {
				String queryStr="select * from goods where user_id='"+id+"' and gname='"+quantityName+"'";
				conn=getConnection();
				pstmt=conn.prepareStatement(queryStr);
				rs=pstmt.executeQuery();
				if(rs!=null&&rs.next()) {
					if(rs.getInt("quantity")>1) {
						int a=rs.getInt("quantity")-1;
						int b=rs.getInt("total")-rs.getInt("price");
						String sql="update goods set quantity='"+a+"',total='"+b+"' where user_id='"+id+"' and gname='"+quantityName+"'";
						pstmt.executeUpdate(sql);
					}
				 	else {
						String sql="delete from goods where user_id='"+id+"' and gname='"+quantityName+"'";
						pstmt.execute(sql);
					}
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				closeResultSet(rs);
				closePstmt(pstmt);
				closeConnection(conn);
			}
			}
			view=request.getRequestDispatcher("cart.jsp");
			view.forward(request, response);
		}
		
	}

	private void insertGood(Product product, String id) {
		// TODO 自动生成的方法存根
		String insertStr="insert into goods(user_id,gname,price,quantity,total) values(?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(insertStr);
			pstmt.setString(1, id);
			pstmt.setString(2, product.getName());
			pstmt.setInt(3, product.getprice());
			pstmt.setInt(4, product.getQuantity());
			pstmt.setInt(5, product.getTotal());
			pstmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	private Product getProduct(HttpServletRequest request)  {
		// TODO 自动生成的方法存根
		String info=request.getParameter("disk");
		String quantityStr=request.getParameter("quantity");
		StringTokenizer t=new StringTokenizer(info,"|");
		String disk=t.nextToken();
		String priceStr=t.nextToken();
		int price=0,quantity=0,total=0;
		try {
			quantity=Integer.parseInt(quantityStr);
			price=Integer.parseInt(priceStr);
			total=quantity*price;
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		Product product=new Product();
		product.setName(disk);
		product.setPrice(price);
		product.setQuantity(quantity);
		product.setTotal(total);
		return product;
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

}
