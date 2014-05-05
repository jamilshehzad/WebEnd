<%--
Document : index
Created on : Nov 5, 2012, 6:06:23 PM
Author	 : mano
--%>

<%@page import="java.util.List"%>
<%@page import="com.data.Service.LoginService"%>
<%@page import="java.util.Date"%>
<%@page import="com.data.Domain.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <link rel="stylesheet" type="text/css" href="style.css"/>
	 <title>Home Page</title>	
</head>
<body>
<center>
	 <div id="mystyle">
		 <h1>Web End Integration</h1>
		 <%if(null != session && null != session.getAttribute("user")){ %>
		 <p><a href="http://localhost:8080/WebEnd">http://localhost:8080/WebEnd</a><br/>
			 <b>Web End</b><br/>
			 <%=new Date()%></br>
			 <%
				 	User user = (User) session.getAttribute("user");
			 %>		
			 <b>Welcome <%= user.getFirstName() + " " + user.getLastName()%></b>		
			 <br/>
			 <a href="logout.jsp">Logout</a>
		 </p>

		 <table>
			 <thead>
				 <tr>
					 <th>User ID</th>
					 <th>First Name</th>
					 <th>Middle Name</th>
					 <th>Last Name</th>
					 <th>Email</th>					
				 </tr>
			 </thead>
			 <tbody>
				 <%
					 LoginService loginService = new LoginService();
					 List<User> list = loginService.getListOfUsers();
					 for (User u : list) {
				 %>
				 <tr>
					 <td><%=u.getUserId()%></td>
					 <td><%=u.getFirstName()%></td>
					 <td><%=u.getMiddleName()%></td>
					 <td><%=u.getLastName()%></td>
					 <td><%=u.getEmail()%></td>
				 </tr>
				 <%}%>
			 <tbody>
		 </table>	
		 <jsp:include page="index.jsp"></jsp:include>	
		 <br/>
		 <%}else{ %>
		 	 <b>This page is not authorized for you to view. Please login.</b>		
			 <br/>
			 <a href="login.jsp">Login</a>
		 <%} %>
	 </div>

</center>		
</body>
</html>