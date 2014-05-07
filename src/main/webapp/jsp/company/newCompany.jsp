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
	 <title>New Company</title>	
</head>
<body>
<center>
	 <div id="mystyle" class="myform">
	 <%
	 	URL x = new URL( request.getRequestURL().toString() );
		String href = x.getProtocol() + "://" + x.getHost() +
		":" + x.getPort() + request.getContextPath();
	  %>
		 <form id="form" name="form" method="post" action="<%=href %>/CompanyController">
			 <h1>Add New Company</h1>
			 <p>Please enter the following information</p>
			<input type="hidden" id="action" name="action" value="add" />
			 <label>Company Name
				 <span class="small">Enter company name</span>
			 </label>
			 <input type="text" name="name" id="name" />

			 <label>Address
				 <span class="small">Enter Company Address</span>
			 </label>
			 <input type="text" name="address" id="address" />

			 <label>Creation Date
				 <span class="small">Enter Creation Date: (YYYY-MM-DD)</span>
			 </label>
			 <input type="text" name="creationDate" id="creationDate" />

			 <button type="submit">Add Company</button>
			 <div class="spacer"></div>

		 </form>
	 </div>
</center>
</body>
</html>