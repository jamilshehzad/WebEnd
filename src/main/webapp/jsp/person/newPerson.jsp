<%--
Document : register
Created on : May 02, 2014
Author	 : Jamil
--%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
	 <link rel="stylesheet" type="text/css" href="../../css/style.css">
	 <title>New Person</title>	
</head>
<body>
<center>
	 <div id="mystyle" class="myform">
	 <%
	 	URL x = new URL( request.getRequestURL().toString() );
		String href = x.getProtocol() + "://" + x.getHost() +
		":" + x.getPort() + request.getContextPath();
	  %>
		 <form id="form" name="form" method="post" action="<%=href %>/PersonController">
			 <h1>Add New Person</h1>
			 <p>Please enter the following information</p>
				<input type="hidden" id="action" name="action" value="add" />
			 <label>First Name
				 <span class="small">Enter your first name</span>
			 </label>
			 <input type="text" name="firstName" id="firstName" />

			 <label>Last Name
				 <span class="small">Enter your last name</span>
			 </label>
			 <input type="text" name="lastName" id="lastName" />

			 <label>Date Of Birth
				 <span class="small">Enter your birth date: (YYYY-MM-DD)</span>
			 </label>
			 <input type="text" name="dateOfBirth" id="dateOfBirth" />

			 <label>Function
				 <span class="small">Function</span>
			 </label>
			 <input type="text" name="function" id="function" />

			 <button type="submit">Add Person</button>
			 <div class="spacer"></div>

		 </form>
	 </div>
</center>
</body>
</html>