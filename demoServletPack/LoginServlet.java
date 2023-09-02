package demoServletPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String unm=request.getParameter("unm");
		String pswd=request.getParameter("pswd");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/WPJavaDB", "root", "");
			String query="select * from Login where UserName=? and Password=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, unm);
			ps.setString(2,pswd);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
//				Cookie cookie=new Cookie("UserName", unm);
//				response.addCookie(cookie);
//				HttpSession session=request.getSession(true);
//				session.setAttribute("UserName", unm);
				response.sendRedirect("HomePage.jsp");
			}
			
			else
			{
				out.print("Invalid Login Details");
				RequestDispatcher rd=request.getRequestDispatcher("Login.html");
				rd.include(request, response);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
