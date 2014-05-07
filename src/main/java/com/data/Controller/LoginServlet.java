package com.data.Controller;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.data.Domain.User;

import com.data.Service.LoginService;


public class LoginServlet extends HttpServlet {
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {	
	URL x = new URL( request.getRequestURL().toString() );
	String href = x.getProtocol() + "://" + x.getHost() +
	":" + x.getPort() + request.getContextPath();
	 String userId = request.getParameter("userId");	
	 String password = request.getParameter("password");
	 LoginService loginService = new LoginService();
	 boolean result = loginService.authenticate(userId, password);
	 User user = loginService.getUserByUserId(userId);
	 if(result == true){
		 request.getSession().setAttribute("user", user);		
		 response.sendRedirect(href+"/home.jsp");
	 }
	 else{
		 response.sendRedirect(href+"/login.jsp");
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