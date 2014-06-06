<%--
Document : index
Created on : May 02, 2014
Author	 : Jamil
--%>
<%@page import="java.net.URL"%>
<%@page import="java.util.List"%>
<%@page import="com.data.Service.CompanyService"%>
<%@page import="java.util.Date"%>
<%@page import="com.data.Domain.User"%>
<%@page import="com.data.Domain.Company"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <link rel="stylesheet" type="text/css" href="../../css/style.css"/>
	 <title>Company List</title>	
</head>
<body>
<center>
	 <div id="mystyle">
		 <h1>Company List</h1>
		 <%
		 	URL x = new URL( request.getRequestURL().toString() );
			String href = x.getProtocol() + "://" + x.getHost() +
			":" + x.getPort() + request.getContextPath();
		  %>
		 <%if(null != session && null != session.getAttribute("user")){ %>
		 <p><a href="http://localhost:8080/WebEnd">http://localhost:8080/WebEnd</a><br/>
			 <b>Web End</b><br/>
			 <%=new Date()%></br>
			 <%
				 	User user = (User) session.getAttribute("user");
			 %>		
			 <b>Welcome <%= user.getFirstName() + " " + user.getLastName()%></b>		
			 <br/>
			 <a href="<%=href %>/logout.jsp">Logout</a>
		 </p>

		 <table>
			 <thead>
				 <tr>
					 <th>Name</th>
					 <th>Address</th>
					 <th>Creation Date</th>
				 </tr>
			 </thead>
			 <tbody>
				 <%
					 CompanyService companyService = new CompanyService();
					 List<Company> list = companyService.getListOfCompanys();
					 for (Company u : list) {
				 %>
				 <tr>
					 <td><%=u.getName()%></td>
					 <td><%=u.getAddress()%></td>
					 <td><%=u.getCreationDate()%></td>
				 </tr>
				 <%}%>
			 <tbody>
		 </table>	
		 <jsp:include page="../../index.jsp"></jsp:include>	
		 <br/>
		 <%}else{ %>
		 	 <b>This page is not authorized for you to view. Please login.</b>		
			 <br/>
			 <a href="<%=href %>/login.jsp">Login</a>
		 <%} %>
	 </div>

</center>		
</body>
</html>