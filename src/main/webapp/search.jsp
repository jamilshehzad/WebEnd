<jsp:useBean id="VLXGenerator" class="com.VDK.Generator.VLXGenerator" scope="request">
</jsp:useBean>
<% 

//Retrieve the query string parameters passed back to us
String strTerm = request.getParameter("strTerm");
String strType = request.getParameter("strType");
System.out.println("TERM IS: "+strTerm);
System.out.println("TYPE IS: "+strType);
//searchEntity will call down to the VLXGenerator class which generates a
//string of VLX based on the search criteria passed
String resultVLX = null;
if("PERSON".equalsIgnoreCase(strType)){
	resultVLX = VLXGenerator.showPerson(strTerm);
}else{
	resultVLX = VLXGenerator.showCompany(strTerm);
}
//String resultVLX = VLXGenerator.search(strType, strTerm);
if(resultVLX==null)
	out.print(VLXGenerator.getLastError());
else
	out.print(resultVLX);

%>