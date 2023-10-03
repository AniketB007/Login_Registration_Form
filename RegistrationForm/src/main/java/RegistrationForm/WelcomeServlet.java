package RegistrationForm;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;


public class WelcomeServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
   
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		String s=request.getParameter("uname");
		
		PrintWriter pw=response.getWriter();
		pw.println("<html><body bgcolor=yellow text=black><h1>");
		pw.println("Welcome "+s);
		pw.println("</h1></body></html>");  

	}

}
