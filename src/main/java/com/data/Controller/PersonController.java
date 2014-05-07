package com.data.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.Domain.Person;
import com.data.Domain.User;
import com.data.Service.LoginService;
import com.data.Service.PersonService;

public class PersonController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {	
		URL x = new URL( request.getRequestURL().toString() );
		String href = x.getProtocol() + "://" + x.getHost() +
		":" + x.getPort() + request.getContextPath();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action").toString();
		if(null == action || "".equalsIgnoreCase(action)){
			//TODO: Redirect to Home Page.
			response.sendRedirect(href+"/home.jsp");
		}else if(action.equalsIgnoreCase("add")){
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String dateOfBirth = request.getParameter("dateOfBirth");
			String function = request.getParameter("function");
			Person person = new Person(firstName,lastName,dateOfBirth,function);
			PersonService pService = new PersonService();
			boolean result = pService.savePerson(person);		
			 out.println("<html>");
			 out.println("<head>");		
			 out.println("<title>Person Added</title>");		
			 out.println("</head>");
			 out.println("<body>");
			 out.println("<center>");
			 if(result){
				 out.println("<h1>Person added Successfully</h1>");
				 out.println("To view Person Details, <a href="+href+"/jsp/person/listPerson.jsp>Click here</a>");
			 }else{
				 out.println("<h1>Person could not be added.</h1>");
				 out.println("To try again<a href="+href+"/jsp/person/newPerson.jsp>Click here</a>");
			 }
			 out.println("</center>");
			 out.println("</body>");
			 out.println("</html>");
		}else if(action.equalsIgnoreCase("edit")){
			
		}else if(action.equalsIgnoreCase("view")){
			
		}else if(action.equalsIgnoreCase("delete")){
			
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
