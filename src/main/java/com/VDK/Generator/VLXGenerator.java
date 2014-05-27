package com.VDK.Generator;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * VLXGenerator
 * 
 */

class VLXProperty
{
	String identifier;
	String value;
} 

/**
 * @filename VLXGenerator.java
 */
public class VLXGenerator {
	
	//Change Database settings, connection manager 
	  
	 private Node mEndsNode;
	 private Node mLinksNode;
	 
	 /*private final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	 private final String DATA_SOURCE_CONNECTION_STRING = "jdbc:mysql://localhost/ixvdata";
	 //private final String DATA_SOURCE_CONNECTION_STRING = "jdbc:mysql://localhost/ixvsampledata";
	 private final String VLX_TYPE_CATALOG = "VLXCatalog.vlx";
	 private final String VLX_ERROR_TEMPLATE = "VLXErrorTemplate.vlx";
	 private final String ID_FILLER = "IDfiller.xslt";
	 private final String USER = "root";
	 private final String PASS = "root";*/
	   
	 DocumentBuilder mBuilder=null;
	 private String mLastError="";
	 
	 private final String DATABASE_DRIVER = "mongodb.jdbc.MongoDriver";
	 private final String DATA_SOURCE_CONNECTION_STRING = "jdbc:mongo://localhost:8444/meteor";
	 private final String VLX_TYPE_CATALOG = "VLXTemplate.vlx";
	 private final String VLX_ERROR_TEMPLATE = "VLXErrorTemplate.vlx";
	 private final String ID_FILLER = "IDfiller.xslt";
	    
	 public VLXGenerator() {}
	   
	 private void initNodes(Document domVLX){
			
		  NodeList nodes = domVLX.getElementsByTagName("ends");
		  mEndsNode = nodes.item(0); //should only be 1
			
		  nodes = domVLX.getElementsByTagName("links");
		  mLinksNode = nodes.item(0); //should only be 1
			
	 } 
          
    /**
	 * Loads mySQL JDBC driver and uses it to connect to iXvSampleData database
	 * 
	 * @return true if we connect to the database successfully 
	 */
	public Connection connectDatabase() throws Exception{
	 	Class.forName(DATABASE_DRIVER).newInstance();
		//return DriverManager.getConnection(DATA_SOURCE_CONNECTION_STRING,USER,PASS);
	 	return DriverManager.getConnection(DATA_SOURCE_CONNECTION_STRING);
   }
      
	private void addEntity(ResultSet rs, String catType, Document domVLX, int entityFields) throws SQLException
	{
	    Node endNode;
	    Node propertiesNode;
	    Node xPosNode;
	    Node yPosNode;
	    Node catTypeNode;
		String fieldName;
		Node id;

		endNode = mEndsNode.appendChild(domVLX.createElement("end"));
			
		// Set the catType
		catTypeNode = domVLX.createAttribute("catType");
		endNode.getAttributes().setNamedItem(catTypeNode);
		catTypeNode.setNodeValue(catType);
		// Set the ypos - just "0"
		yPosNode = domVLX.createAttribute("yPos");
		endNode.getAttributes().setNamedItem(yPosNode);
		yPosNode.setNodeValue("0");
		// Set the xpos - just "0"
		xPosNode = domVLX.createAttribute("xPos");
		endNode.getAttributes().setNamedItem(xPosNode);
		xPosNode.setNodeValue("0");
		
		id = domVLX.createAttribute("id");
		endNode.getAttributes().setNamedItem(id);
		id.setNodeValue("id-"+rs.getString("identityProperty"));
    
		propertiesNode = endNode.appendChild(domVLX.createElement("properties"));
        
		// loop through the fields (properties), creating them as elements (using the record field name as the element
		// name) and append them as a child of 'properties'. Set their value to the record value.
		
		//entityFields = rs.getMetaData().getColumnCount();
		for(int i = 1; i <= entityFields; i++)
		{
			ResultSetMetaData rsMetaData = rs.getMetaData();
		    fieldName = rsMetaData.getColumnLabel(i);
		    String propValue = rs.getString(i);
		    
			// don't add the property if the field value is null
			if (propValue!=null){
				Node propNode = propertiesNode.appendChild(domVLX.createElement(fieldName));
				Node textNode = domVLX.createTextNode(propValue);
				propNode.appendChild(textNode);
			}
		}
	}
       
   /**
	 * Given a Vector of VLXProperty objects this function generates the link 
	 * VLX element.
	 * 
	 * @param domVLX VLX DOM Document to be updated
	 * @param strCatType type of the link
	 * @param strEnd1Id an end that the link is attached to
	 * @param strEnd2Id another end that the link is attached to
	 * @param strDirection string defining hte links directions
	 * @param vlxProps Vector of VLXProperty objects to be sued to populate the links properties element
	 **/
	private void addLink(ResultSet rs, String end1Id, String end2Id, String catType, String direction, 
			Document domVLX, int entityFields, String status) throws SQLException {
		
		//Add as LINK to the VLX
		Node linkNode;
		Node propertiesNode;
		Node end1IdNode;
		Node end2IdNode;
		Node directionNode;
		Node catTypeNode;
		String fieldName;
		Node id;
		Node end1IdNode1, end2IdNode2;
		
		//Create a new <link> node and add it as a child of <links>
		linkNode = mLinksNode.appendChild(domVLX.createElement("link"));
	    
		end1IdNode = domVLX.createAttribute("end1identity");
		linkNode.getAttributes().setNamedItem(end1IdNode);
		end1IdNode.setNodeValue(end1Id);
	    
		end2IdNode = domVLX.createAttribute("end2identity");
		linkNode.getAttributes().setNamedItem(end2IdNode);
		end2IdNode.setNodeValue(end2Id);
	        
		directionNode = domVLX.createAttribute("direction");
		linkNode.getAttributes().setNamedItem(directionNode);
		directionNode.setNodeValue(direction); //"reverse" or "forward"
	    
		catTypeNode = domVLX.createAttribute("catType");
		linkNode.getAttributes().setNamedItem(catTypeNode);
		catTypeNode.setNodeValue(catType);		
		
		id = domVLX.createAttribute("id");
		linkNode.getAttributes().setNamedItem(id);
		id.setNodeValue("id-"+end1Id+"-"+end2Id+"-"+catType);
		
		end1IdNode1 = domVLX.createAttribute("end1id");
		linkNode.getAttributes().setNamedItem(end1IdNode1);
		end1IdNode1.setNodeValue("id-"+end1Id);
	    
		end2IdNode2 = domVLX.createAttribute("end2id");
		linkNode.getAttributes().setNamedItem(end2IdNode2);
		end2IdNode2.setNodeValue("id-"+end2Id);
		
		if(null != status && !status.trim().equalsIgnoreCase("") && "Function".equalsIgnoreCase(catType.trim())){
			Node dotStyleNode = domVLX.createAttribute("dotStyle");
			linkNode.getAttributes().setNamedItem(dotStyleNode);
			dotStyleNode.setNodeValue("Unconfirmed");
		}
	    
		//create a <properties> element as a child on the link element
		propertiesNode  = linkNode.appendChild(domVLX.createElement("properties"));
	
		//Loop through all properties adding a node under the <properties> node
		//for each of the form <propName>propValue</propName>
		for(int i = entityFields; i <= rs.getMetaData().getColumnCount(); i++)
		{
		    ResultSetMetaData rsMetaData = rs.getMetaData();
		    fieldName = rsMetaData.getColumnLabel(i);
		    String propValue = rs.getString(i);
		    
			// don't add the property if the field value is null
			if (propValue!=null){
				Node propNode = propertiesNode.appendChild(domVLX.createElement(fieldName));
				Node textNode = domVLX.createTextNode(propValue);
				propNode.appendChild(textNode);
			}
		}
			    	
		// Add identityProperty by concatenating the end ids.
		// Be sure to set the id according to the direction to prevent duplicate links with different ids
		Node textNode;
		if (direction=="reverse")
		    textNode = domVLX.createTextNode(end2Id + "-" + end1Id);
		else
		    textNode = domVLX.createTextNode(end1Id + "-" + end2Id);

		Node propNode = propertiesNode.appendChild(domVLX.createElement("identityProperty"));
		propNode.appendChild(textNode);
	}
	
	/**
	 * Gets the last error message mLastError and inserts it into a skeleton VLX document
	 * to be consumed by the applet as client-side Javascript as required.
	 * 
	 * @return Skeleton VLX document including the last error message in the "errorMessage" node
	 */
	public String getLastError(){
		Document domVLX = loadVLXTemplate(VLX_ERROR_TEMPLATE);
		NodeList nodes = domVLX.getElementsByTagName("errorMessage");
		Node textNode = domVLX.createTextNode("Error generating VLX: " + mLastError);
		nodes.item(0).appendChild(textNode);
    	return XMLDOM2String(domVLX);
	}


	public String showCompany(String strCompanyId) {
    
	    String queryString = new String();
	    Document domVLX = null;
		String typeId="";
	    domVLX = loadVLXTemplate(VLX_TYPE_CATALOG);
		initNodes(domVLX);
	  	    
	    if(domVLX==null)
		    return null;
	    	    
        // Get all fields from the database as defined by the Type Catalog
		// Company type properties: [identityProperty, Name, RegistrationNo, LineOfBusiness]
	   
	    Statement stmt;
		try {

		    Connection conn = connectDatabase();
			stmt = conn.createStatement();

	        queryString = "SELECT _id as identityProperty, name, address, status FROM companies WHERE _id = '" + strCompanyId + "';";
			typeId = "Company";
			ResultSet rs = stmt.executeQuery(queryString);
			Map<String,String> status = new HashMap<String,String>();
			if(rs == null)
				return null;
			while (rs.next()) {
				addEntity(rs, typeId, domVLX, rs.getMetaData().getColumnCount());
			}
			stmt.close();
			// company_persons
	        queryString = "SELECT DISTINCT personId FROM company_persons WHERE companyId = '" + strCompanyId + "';";
			Statement stmt_link = conn.createStatement();
			ResultSet rs_link = stmt_link.executeQuery(queryString);
			if(rs_link == null)
				return null;
			while (rs_link.next()) {
				
				
				queryString = "SELECT _id as identity, left_company FROM company_persons WHERE companyId = '" + strCompanyId + "' and personId = '"+rs_link.getString("personId")+"';";
				Statement stmt_link1 = conn.createStatement();
				ResultSet rs_link1 = stmt_link1.executeQuery(queryString);
				
				if(null != rs_link1){
					while(rs_link1.next()){

						String stat = rs_link1.getString("left_company");
						status.put(rs_link.getString("personId"),stat);
					}
				}
				
				
				
				String personId = rs_link.getString(1);
				String personQuery = "SELECT _id as identityProperty, name, address, status FROM persons WHERE _id = '" + personId + "';";
				typeId = "Person";
				Statement stmt_person = conn.createStatement();
				ResultSet rs_person = stmt_person.executeQuery(personQuery);
				if(rs_person == null)
					return null;
				while (rs_person.next()) {
					String id = rs_person.getString("identityProperty");
					addEntity(rs_person, typeId, domVLX, rs_person.getMetaData().getColumnCount());
				}
				stmt_person.close();
			}
			//stmt_link.close();


	        queryString = "SELECT _id as identityProperty, companyId, personId, function FROM company_persons WHERE companyId = '" + strCompanyId + "';";
			rs_link = stmt_link.executeQuery(queryString);
			while(rs_link.next()) {
				addLink(rs_link, rs_link.getString(2).trim(), rs_link.getString(3).trim(), "Function", "forward", domVLX, 4, status.get(rs_link.getString("personId")));
			}
			//stmt_link.close();


			// company_shares
	        queryString = "SELECT DISTINCT personId FROM company_shares WHERE companyId = '" + strCompanyId + "';";
			rs_link = stmt_link.executeQuery(queryString);
			if(rs_link == null)
				return null;
			while (rs_link.next()) {
				String personId = rs_link.getString(1);
				String personQuery = "SELECT _id as identityProperty, name, address, status FROM persons WHERE _id = '" + personId + "';";
				typeId = "Person";
				Statement stmt_person = conn.createStatement();
				ResultSet rs_person = stmt_person.executeQuery(personQuery);
				if(rs_person == null)
					return null;
				while (rs_person.next()) {
					addEntity(rs_person, typeId, domVLX, rs_person.getMetaData().getColumnCount());
				}
				stmt_person.close();
			}
			//stmt_link.close();


	        queryString = "SELECT _id as identityProperty, companyId, personId, share FROM company_shares WHERE companyId = '" + strCompanyId + "';";
			rs_link = stmt_link.executeQuery(queryString);
			while(rs_link.next()) {
				addLink(rs_link, rs_link.getString(2).trim(), rs_link.getString(3).trim(), "Has Shares", "forward", domVLX, 4, status.get(rs_link.getString("personId")));
			}
			stmt_link.close();


			conn.close();
			//Run IDFiller
			//domVLX = (Document) runTransform(domVLX, ID_FILLER);
		} catch (Exception e) {
			mLastError = "Error occured in search(...): "+e.getMessage();
			return null;
		}
	   	return XMLDOM2String(domVLX);
   }

	public String showPerson(String strPersonId) {
    
	    String queryString = new String();
	    Document domVLX = null;
		String typeId="";
	    domVLX = loadVLXTemplate(VLX_TYPE_CATALOG);
		initNodes(domVLX);
	  	    
	    if(domVLX==null)
		    return null;
	    	    
        // Get all fields from the database as defined by the Type Catalog
		// Company type properties: [identityProperty, Name, RegistrationNo, LineOfBusiness]
        queryString = "SELECT _id as identityProperty, name, address, status FROM persons WHERE _id = '" + strPersonId + "';";
		typeId = "Person";
	   
	    Statement stmt;
		try {

		    Connection conn = connectDatabase();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);
			if(rs == null)
				return null;
			while (rs.next()) {
				addEntity(rs, typeId, domVLX, rs.getMetaData().getColumnCount());
			}
			stmt.close();



			// company_persons
			Map<String,String> status = new HashMap<String,String>();
	        queryString = "SELECT DISTINCT companyId FROM company_persons WHERE personId = '" + strPersonId + "';";
			Statement stmt_link = conn.createStatement();
			ResultSet rs_link = stmt_link.executeQuery(queryString);
			if(rs_link == null)
				return null;
			while (rs_link.next()) {
				
				
				
				queryString = "SELECT _id as identity, left_company FROM company_persons WHERE personId = '" + strPersonId + "' and companyId = '" +rs_link.getString("companyId")+ "';";
				Statement stmt_link1 = conn.createStatement();
				ResultSet rs_link1 = stmt_link1.executeQuery(queryString);
				
				if(rs_link1 != null){
					while(rs_link1.next()){
						String stat = rs_link1.getString("left_company");
						status.put(rs_link.getString("companyId"), stat);
					}
				}
				
				
				String companyId = rs_link.getString(1);
				String companyQuery = "SELECT _id as identityProperty, name, address, status FROM companies WHERE _id = '" + companyId + "';";
				typeId = "Company";
				Statement stmt_company = conn.createStatement();
				ResultSet rs_company = stmt_company.executeQuery(companyQuery);
				if(rs_company == null)
					return null;
				while (rs_company.next()) {
					addEntity(rs_company, typeId, domVLX, rs_company.getMetaData().getColumnCount());
				}
				stmt_company.close();
			}
			//stmt_link.close();


	        queryString = "SELECT _id as identityProperty, companyId, personId, function FROM company_persons WHERE personId = '" + strPersonId + "';";
			rs_link = stmt_link.executeQuery(queryString);
			while(rs_link.next()) {
				addLink(rs_link, rs_link.getString(2).trim(), rs_link.getString(3).trim(), "Function", "forward", domVLX, 4, status.get(rs_link.getString("companyId")));
			}
			//stmt_link.close();


			// company_shares
	        queryString = "SELECT DISTINCT companyId FROM company_shares WHERE personId = '" + strPersonId + "';";
			stmt_link = conn.createStatement();
			rs_link = stmt_link.executeQuery(queryString);
			if(rs_link == null)
				return null;
			while (rs_link.next()) {
				String companyId = rs_link.getString(1);
				String companyQuery = "SELECT _id as identityProperty, name, address, status FROM companies WHERE _id = '" + companyId + "';";
				typeId = "Company";
				Statement stmt_company = conn.createStatement();
				ResultSet rs_company = stmt_company.executeQuery(companyQuery);
				if(rs_company == null)
					return null;
				while (rs_company.next()) {
					addEntity(rs_company, typeId, domVLX, rs_company.getMetaData().getColumnCount());
				}
				stmt_company.close();
			}
			//stmt_link.close();


	        queryString = "SELECT _id as identityProperty, companyId, personId, share FROM company_shares WHERE personId = '" + strPersonId + "';";
			rs_link = stmt_link.executeQuery(queryString);
			while(rs_link.next()) {
				addLink(rs_link, rs_link.getString(2).trim(), rs_link.getString(3).trim(), "Has Shares", "forward", domVLX, 4,status.get(rs_link.getString("companyId")));
			}
			stmt_link.close();



			conn.close();
			//Run IDFiller
			//domVLX = (Document) runTransform(domVLX, ID_FILLER);
		} catch (Exception e) {
			mLastError = "Error occured in search(...): "+e.getMessage();
			return null;
		}
	   	String val = XMLDOM2String(domVLX);
	   	System.out.println(val);
	   	return val;
   }

   
   /**
     * Loads up the VLX template into a Document object
     * 
	 * @return VLX template as a DOM Document
	 */
	private Document loadVLXTemplate(String templateResource){
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		mBuilder = null;	 
		Document document = null;
		//Get the VLX template file resource
		URL url = VLXGenerator.class.getResource(templateResource);
		try{
			factory.setNamespaceAware(true);
			mBuilder = factory.newDocumentBuilder();
			InputStream stream = url.openStream();
			//Parse the VLX template into an XML document object
		   	document = mBuilder.parse(stream);
		}
		catch(Exception e){
			mLastError = "Error Loading XML: " + e.getMessage();
		}
		return document;
   	}
   
		
	/**
	 * Applies the IDFiller.xslt to the newly generated VLX Document.  This must be done
	 * in order for the Viewer applet to render the VLX
	 * 
	 * @param vlxDom newly generated VLX Document to transform
	 * @param styleSheetURI URI of the xslt file to apply
	 * 
	 * @return transformed VLX as XML Node
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public Node runTransform(Document vlxDom, String styleSheetURI) throws ParserConfigurationException, SAXException, IOException, TransformerException{
	
	  //Instantiate a DocumentBuilderFactory.
	  TransformerFactory tFactory = TransformerFactory.newInstance();
      
	  //Use the DocumentBuilder to parse the XSL stylesheet.
	  URL url = VLXGenerator.class.getResource(styleSheetURI);
	  InputStream stream = url.openStream();
	  
	  Document xslDoc = mBuilder.parse(stream);

	  // Use the DOM Document to define a DOMSource object.
	  DOMSource xslDomSource = new DOMSource(xslDoc);

	  // Process the stylesheet DOMSource and generate a Transformer.
	  Transformer transformer = tFactory.newTransformer(xslDomSource);
      
	  // Use the DOM Document to define a DOMSource object.
	  DOMSource xmlDomSource = new DOMSource(vlxDom);
           
	  // Create an empty DOMResult for the Result.
	  DOMResult domResult = new DOMResult();
  
	  // Perform the transformation, placing the output in the DOMResult.
	  transformer.transform(xmlDomSource, domResult);
	  	  
	  return domResult.getNode();
	}
		
	/**
	 * Given an XML Node object this function returns the VLX as a string
	 * 
	 * @param domVLX
	 * @return VLX as a string
	 */
	public String XMLDOM2String (Node domVLX) {
			
		 Transformer xmlTransformer;
		 StringWriter resultStringWriter = null;
		 DOMSource nodeSource = new DOMSource(domVLX);
		 try {
			System.setProperty("javax.xml.transform.TransformerFactory", "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
			xmlTransformer = TransformerFactory.newInstance().newTransformer();
			resultStringWriter = new StringWriter();
			StreamResult streamResult = new StreamResult(resultStringWriter);
			xmlTransformer.transform(nodeSource, streamResult);
		} catch (Exception e) {
			mLastError = "Error serializing VLX document: "+e.getMessage();
			return null;
		}
		return resultStringWriter.toString();
	}
}


