package RegistrationForm;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
    Connection con;
    
    public LoginServlet() {
        super();
        
    }

	
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Aniket");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		try {
			String s1=request.getParameter("uname");
			String s2=request.getParameter("pword");
			
			PreparedStatement pstmt=con.prepareStatement("select * from details where uname=? and pword=?");
			pstmt.setString(1, s1);
			pstmt.setString(2, s2);
			
			ResultSet rs=pstmt.executeQuery();
			PrintWriter pw=response.getWriter();
			 if(rs.next()) {
               RequestDispatcher rd=request.getRequestDispatcher("/welcome");
               rd.forward(request,response);
			 }
			 else {
				 
               RequestDispatcher rd=request.getRequestDispatcher("login.html");
               pw.print("Invalid Username/Password");
               response.setContentType("text/html");
               rd.include(request, response);
               
			 }
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
