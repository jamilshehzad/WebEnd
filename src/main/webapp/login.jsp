<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
	 <link rel="stylesheet" type="text/css" href="css/style.css">
	 <title>Login Page</title>
</head>
<body>
<center>
<%
		 	URL x = new URL( request.getRequestURL().toString() );
			String href = x.getProtocol() + "://" + x.getHost() +
			":" + x.getPort() + request.getContextPath();
		  %>
	 <div id="mystyle" class="myform">
		 <form id="form" name="form" method="post" action="<%=href %>/LoginServlet">
			 <h1>Login</h1>
			 <p>Please enter your login information
				 <br/>New User? <a href="register.jsp">Register</a></p>
			 <label>User ID
				 <span class="small">Enter your user ID</span>
			 </label>
			 <input type="text" name="userId" id="userId" />

			 <label>Password
				 <span class="small">Min. size 6 chars</span>
			 </label>
			 <input type="password" name="password" id="password" />

			 <button type="submit">Sign-in</button>
			 <div class="spacer"></div>
		 </form>
	 </div>
</center>
</body>
</html>