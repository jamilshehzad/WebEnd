package com.data.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.Domain.User;
import com.data.Service.RegisterService;

public class RegisterServlet extends HttpServlet {
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
	URL x = new URL( request.getRequestURL().toString() );
	String href = x.getProtocol() + "://" + x.getHost() +
	":" + x.getPort() + request.getContextPath();
	 response.setContentType("text/html;charset=UTF-8");
	 PrintWriter out = response.getWriter();
	 String fName = request.getParameter("firstName");
	 String mName = request.getParameter("middleName");
	 String lName = request.getParameter("lastName");
	 String email = request.getParameter("email");
	 String userId = request.getParameter("userId");
	 String password = request.getParameter("password");
	 User user = new User(fName,mName,lName, email,userId, password);
			
	 try {	
		 RegisterService registerService = new RegisterService();
		 boolean result = registerService.register(user);		
		 out.println("<html>");
		 out.println("<head>");		
		 out.println("<title>Registration Successful</title>");		
		 out.println("</head>");
		 out.println("<body>");
		 out.println("<center>");
		 if(result){
			 out.println("<h1>Registration Successful</h1>");
			 out.println("To login with new UserId and Password<a href="+href+"/login.jsp>Click here</a>");
		 }else{
			 out.println("<h1>Registration Unsuccessful</h1>");
			 out.println("To try again<a href="+href+"register.jsp>Click here</a>");
		 }
		 out.println("</center>");
		 out.println("</body>");
		 out.println("</html>");
	 } finally {		
		 out.close();
	 }
}
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
	 processRequest(request, response);
}
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
	 processRequest(request, response);
}
@Override
public String getServletInfo() {
	 return "Short description";
}
}
