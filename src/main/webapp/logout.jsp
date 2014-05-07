<%--
Document : logout
Created on : May 02, 2014
Author	 : Jamil
--%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
	 <link rel="stylesheet" type="text/css" href="style.css">
	 <title>logout Page</title>
</head>
<body>
<%
		 	URL x = new URL( request.getRequestURL().toString() );
			String href = x.getProtocol() + "://" + x.getHost() +
			":" + x.getPort() + request.getContextPath();
		  %>
	 <%		
		 session.removeAttribute("userId");
		 session.removeAttribute("password");
		 session.invalidate();
	 %>
<center>
	 <h1>You have successfully logged out</h1>
	 To login again <a href="<%=href %>/login.jsp">click here</a>.
</center>
</body>
</html>