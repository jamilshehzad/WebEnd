<jsp:useBean id="VLXGenerator" class="com.VDK.Generator.VLXGenerator" scope="request">
</jsp:useBean>
<% 

//Retrieve the query string parameters passed back to us
String strTerm = request.getParameter("strTerm");
String strType = request.getParameter("strType");

//searchEntity will call down to the VLXGenerator class which generates a
//string of VLX based on the search criteria passed
String resultVLX = VLXGenerator.search(strType, strTerm);
if(resultVLX==null)
	out.print(VLXGenerator.getLastError());
else
	out.print(resultVLX);

%>