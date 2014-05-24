<jsp:useBean id="VLXGenerator" class="com.VDK.Generator.VLXGenerator" scope="request">
</jsp:useBean><%

//Retrieve the query string parameters passed back to us
String strID = request.getParameter("strID");
String strType = request.getParameter("strType");

//expand will call down to the VLXGenerator class which queries the database
//generates a string of VLX based on the id and type of element expanded
String resultVLX = null;
if("PERSON".equalsIgnoreCase(strType)){
	resultVLX = VLXGenerator.showPerson(strID);
}else{
	resultVLX = VLXGenerator.showCompany(strID);
}
//String resultVLX = VLXGenerator.search(strType, strTerm);
if(resultVLX==null)
	out.print(VLXGenerator.getLastError());
else
	out.print(resultVLX);/* 

String resultVLX = VLXGenerator.expand(strType, strID);
if(resultVLX==null)
	out.print(VLXGenerator.getLastError());
else
	out.print(resultVLX); */

%>
