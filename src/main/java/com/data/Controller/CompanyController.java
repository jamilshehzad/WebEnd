package com.data.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.Domain.Company;
import com.data.Domain.User;
import com.data.Service.CompanyService;
import com.data.Service.LoginService;

public class CompanyController extends HttpServlet {

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
			String companyName = request.getParameter("name");
			String address = request.getParameter("address");
			String creationDate = request.getParameter("creationDate");
			Company company = new Company(companyName,address,creationDate);
			CompanyService cService = new CompanyService();
			boolean result = cService.saveCompany(company);		
			 out.println("<html>");
			 out.println("<head>");		
			 out.println("<title>Company Added</title>");		
			 out.println("</head>");
			 out.println("<body>");
			 out.println("<center>");
			 if(result){
				 out.println("<h1>Company Added Successfully</h1>");
				 out.println("To View Company Details, <a href="+href+"/jsp/company/listCompany.jsp>Click here</a>");
			 }else{
				 out.println("<h1>Company could not be added.</h1>");
				 out.println("To try again<a href="+href+"/jsp/company/newCompany.jsp>Click here</a>");
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
