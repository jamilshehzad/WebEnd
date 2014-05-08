package com.VDK.Generator;

import java.net.URL;
import java.sql.*;
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;   
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;


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
	  
	 private Node mEndsNode;
	 private Node mLinksNode;
	 
	 private final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	 private final String DATA_SOURCE_CONNECTION_STRING = "jdbc:mysql://localhost/webdata";
	 //private final String DATA_SOURCE_CONNECTION_STRING = "jdbc:mysql://localhost/ixvsampledata";
	 private final String VLX_TYPE_CATALOG = "VLXCatalog.vlx";
	 private final String VLX_ERROR_TEMPLATE = "VLXErrorTemplate.vlx";
	 private final String ID_FILLER = "IDfiller.xslt";
	 private final String USER = "root";
	 private final String PASS = "root";
	   
	 DocumentBuilder mBuilder=null;
	 private String mLastError="";
	    
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
		System.out.println("Connecting to database: "+DATA_SOURCE_CONNECTION_STRING+":"+USER+":"+PASS);
	 	Class.forName(DATABASE_DRIVER).newInstance();
		return DriverManager.getConnection(DATA_SOURCE_CONNECTION_STRING,USER,PASS);
   }
      
	/**
	 * Each expand will request information from specific expand methods
	 * before the function returns the expanded ID is added as an attribute of the content element
	 *
	 * @param strType - the type of expand; either COMPANY, PERSON OR PRODUCT
	 * @param strId - the database ID to expand from; could be company.ID, person.ID, product.ID etc.
	 * @return new VLX DOM document with expanded ends relationships
	 */
	public String expand(String expandType, String expandID){
   	  
		Document domVLX = null;
		domVLX = loadVLXTemplate(VLX_TYPE_CATALOG);
		initNodes(domVLX);			
		if(domVLX==null)
			return null;
		expandType = expandType.toUpperCase();
	
		//Perform different queries based on the type that is being expanded
		if(expandType.equals("COMPANY"))
		        domVLX = expandCompany(expandID, domVLX);
		else if(expandType.equals("PERSON"))
		        domVLX = expandPerson(expandID, domVLX);
		else if(expandType.equals("ADDRESS"))
		    	domVLX = expandAddress(expandID, domVLX);
		else{
			mLastError = "Unrecognized expand type: " + expandType;
			return null;
		}
	   
		if(domVLX==null){
			mLastError = "Error occured whilst expanding type "+expandType+" - "+mLastError;
			return null;
		}
    		
		try{
			domVLX = (Document) runTransform(domVLX, "IDfiller.xslt");
		}
		catch(Exception e){
			mLastError = "failure whilst running IDfiller transform";
			return null;
		}
		return XMLDOM2String(domVLX);
	}
	   		
	/**
	 * Given the id of the person this function performs SQL queries to get
	 * related information which is in this case only companies that they are
	 * directors of.  It also generates the link between the person and company
	 * based on another SQL query.
	 * 
	 * @param strID id of end to expand
	 * @param domVLX VLX template loaded into a DOM Document
	 * @return updated VLX DOM document
	 */
	private Document expandPerson(String expandID, Document domVLX){

	    String queryString = "";

		try
		{
			// Search for the companies this person is a shareholder of
			// Get all fields from the database as defined by the Type Catalog for the Company type, including the link:
			// Company type properties: [identityProperty, Name, RegistrationNo, LineOfBusiness]
			// Has Shareholding Of type properties: [NoOfShares]
			queryString = "SELECT DISTINCT Organization.OrganizationID as identityProperty, Organization.Name, "+
			    "Organization.RegistrationNo, Organization.Trade as LineOfBusiness, SHAREHOLDERLINK.NumberOfShares as NoOfShares " +
				"FROM SHAREHOLDERLINK INNER JOIN Organization ON " +
				"SHAREHOLDERLINK.OrganizationID = Organization.OrganizationID " +
				"WHERE SHAREHOLDERLINK.PersonID='" + expandID + "'";
			    
			domVLX = processExpand(expandID, "Company", "Person", "Has Shareholding Of", queryString, domVLX, 4, "forward");
			
			// Search for the companies this person is an employee of
			// Get all fields from the database as defined by the Type Catalog for the Company type, including the link:
			// Company type properties: [identityProperty, Name, RegistrationNo, LineOfBusiness]
			// Is Employee Of type properties: [Role, EmployeeNo]
			queryString = "SELECT DISTINCT Organization.OrganizationID as identityProperty, Organization.Name, "+
			    "Organization.RegistrationNo, Organization.Trade as LineOfBusiness, EMPLOYEELINK.Role, EMPLOYEELINK.EmployeeNo " +
				"FROM Organization INNER JOIN EMPLOYEELINK ON Organization.OrganizationID = EMPLOYEELINK.OrganizationID " +
				"WHERE EMPLOYEELINK.PersonID='" + expandID + "'";
			       
			domVLX = processExpand(expandID, "Company", "Person", "Is Employee Of", queryString, domVLX, 4, "forward");
   
			// Search for the adresses this person has
			// Get all fields from the database as defined by the Type Catalog for the Address type, including the link:
			// Address type properties: [identityProperty, Street, City, State, Country, IsPOBox]
			// Has Address Of type properties: [IsRegisteredAddress]
			queryString = "SELECT DISTINCT `Postal Address`.POID as identityProperty, `Postal Address`.Street, "+
			    "`Postal Address`.`Town/City` as City, `Postal Address`.`State/County` as State, "+
			    "`Postal Address`.Country, `Postal Address`.`PO_Box` as IsPOBox, ADDRESSLINK.RegAddress as IsRegisteredAddress " +
				"FROM `Postal Address` INNER JOIN ADDRESSLINK ON `Postal Address`.POID = ADDRESSLINK.POID " +
				"WHERE ADDRESSLINK.ResidentID='" + expandID + "'";
			           
			return processExpand(expandID, "Address", "Person", "Has Address Of", queryString, domVLX, 6, "forward");			
		}
		catch(Exception e)
		{
			mLastError = "Error occured while expanding person with id " + expandID + " - " + e.getMessage();
			return null;
		}
	}
		
	/**
	 * Given the id of the product this function performs SQL queries to get
	 * related information which is in this case only companies that products
	 * are associated with.
	 * 
	 * @param strID id of end to expand
	 * @param domVLX VLX template loaded into a DOM Document
	 * @return updated VLX DOM document
	 */
	private Document expandAddress(String expandID, Document domVLX){
		
	    String queryString;
	    
		try
		{
			// Search for the companies with address
			// Get all fields from the database as defined by the Type Catalog for the Company type, including the link:
			// Company type properties: [identityProperty, Name, RegistrationNo, LineOfBusiness]
			// Has Address Of type properties: [IsRegisteredAddress]
			queryString = "SELECT DISTINCT Organization.OrganizationID as identityProperty, Organization.Name, Organization.RegistrationNo, Organization.Trade as LineOfBusiness, ADDRESSLINK.RegAddress as IsRegisteredAddress " +
				"FROM ADDRESSLINK INNER JOIN Organization ON " +
				"ADDRESSLINK.ResidentID = Organization.OrganizationID " +
				"WHERE ADDRESSLINK.POID='" + expandID + "'";
				    
			domVLX = processExpand(expandID, "Company", "Address", "Has Address Of", queryString, domVLX, 4, "reverse");
			
			// Search for the people with this address
			// Get all fields from the database as defined by the Type Catalog for the Person type, including the link:
			// Person type properties: [identityProperty, FirstName, LastName, FullName, Sex]
			// Has Address Of type properties: [IsRegisteredAddress]
			queryString = "SELECT DISTINCT Person.PersonID as identityProperty, Person.FirstName, Person.LastName, "+
			    "CONCAT(FirstName,' ',LastName) AS FullName, Person.Sex, ADDRESSLINK.RegAddress as IsRegisteredAddress " +
				"FROM Person INNER JOIN ADDRESSLINK ON Person.PersonID = ADDRESSLINK.ResidentID " +
				"WHERE ADDRESSLINK.POID='" + expandID + "'";
				        
			return processExpand(expandID, "Person", "Address", "Has Address Of", queryString, domVLX, 5, "reverse");
			
		}
		catch(Exception e)
		{
			mLastError = "Error occured while expanding address with id " + expandID + " - " + e.getMessage();
			return null;
		}
	}
	
	/**
	 * Expand the items related to a company.  This will display the other companies
	 * (though shareholder, subsidiary or competitor relationships), people, products,
	 * documents, news, events, websites and sic codes associated with the company
	 * identified by the strID.
	 * 
	 * @param strID - Id of company to expand
	 * @param domVLX - VLX XML DOM document
	 * @return - Updated VLX XML DOM document
	 * 
	 */
	
	private Document expandCompany(String expandID, Document domVLX){
		
		String queryString;
		
		try{
			
			// SUBSIDARIES
			// Search for the subsidiaries of this company
			queryString = "SELECT DISTINCT person.id as identityProperty, person.first_name, person.last_name,"+
			    "CONCAT(first_name,' ',last_name) AS FullName, person.function " +
			    "FROM person INNER JOIN company ON person.id = company.person_id " +
			    "WHERE company.id='" + expandID + "'";
			
			//Given SQL queries for ends and associated links execute the queries 
			//and populate the VLX document with the data returned
			domVLX = processExpand(expandID, "Person", "Company", "Is Employee Of", queryString, domVLX, 5, "reverse");
			          
			// Search for shareholders of this company
			// Get all fields from the database as defined by the Type Catalog for the Person type, including the link:
			// Person type properties: [identityProperty, FirstName, LastName, FullName, Sex]
			// Has Shareholding Of type properties: [NoOfShares]
			/*queryString = "SELECT DISTINCT Person.PersonID as identityProperty, Person.FirstName, Person.LastName,"+
				"CONCAT(FirstName,' ',LastName) AS FullName, Person.Sex, SHAREHOLDERLINK.NumberOfShares as NoOfShares " +
				"FROM Person INNER JOIN SHAREHOLDERLINK ON Person.PersonID = SHAREHOLDERLINK.PersonID " +
				"WHERE SHAREHOLDERLINK.OrganizationID='" + expandID + "'";
			        
			domVLX = processExpand(expandID, "Person", "Company", "Has Shareholding Of", queryString, domVLX, 5, "reverse");*/
		
			
			// Search for the adresses this company has
			// Get all fields from the database as defined by the Type Catalog for the Address type, including the link:
			// Address type properties: [identityProperty, Street, City, State, Country, IsPOBox]
			// Has Address Of type properties: [IsRegisteredAddress]
			/*queryString = "SELECT company.address as identityProperty " +
				"FROM company "+
				"WHERE company.id='" + expandID + "'";
			
		
			domVLX = processExpand(expandID, "Address", "Company", "Has Address Of", queryString, domVLX, 6, "forward");*/
			
		}
		catch(Exception e){
		    mLastError = "Error occured while expanding company with id " + expandID + " - " + e.getMessage();
			return null;
		}
		return domVLX;
	}
	
	private Document processExpand(String expandID, String entityType1, String entityType2, String linkType,
			String queryString, Document domVLX, int entityFields, String linkDirection) throws Exception
	{
	    Connection conn = connectDatabase();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
			
		// If an SQL query was specified execute the query and
		// populate the rs ResultSet with the results
		rs = stmt.executeQuery(queryString);
	
		while (rs.next()) {
		    addEntity(rs, entityType1, domVLX, entityFields);
			addLink(rs, expandID.trim(), rs.getString(1).trim(), linkType, linkDirection, domVLX, entityFields);		
		}
		
		stmt.close();
		conn.close();
		return domVLX;
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
			Document domVLX, int entityFields) throws SQLException {
		
		//Add as LINK to the VLX
		Node linkNode;
		Node propertiesNode;
		Node end1IdNode;
		Node end2IdNode;
		Node directionNode;
		Node catTypeNode;
		String fieldName;
		
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

	/**
	 * Called by search.jsp to generate the VLX for a search request
	 * 
	 * @param strType Type of search eg. company, person or product
	 * @param strTerm search term string that the user typed in the HTML form
	 * @return requested VLX as a string
	 */
	public String search(String strType, String searchTerm){
    
	    String queryString = new String();
	    Document domVLX = null;
		String typeId="";
	    domVLX = loadVLXTemplate(VLX_TYPE_CATALOG);
		initNodes(domVLX);
	    
		strType = strType.toUpperCase();
	    
	    if(domVLX==null)
		    return null;
	    	    
	    // Work out what type of search we are dealing with and then perform the search.
	    // We'll do a LIKE search so should end up with a set of matching records
	    if (strType.equals("COMPANY")){
	        // Get all fields from the database as defined by the Type Catalog
			// Company type properties: [identityProperty, Name, RegistrationNo, LineOfBusiness]
	        queryString = "SELECT id as identityProperty, name, address, creation_date " +
				"FROM company where name like '%" + searchTerm + "%'";
			typeId = "Company";
	    }
	    else if (strType.equals("PERSON")){
	        // Get all fields from the database as defined by the Type Catalog
			// Person type properties: [identityProperty, FirstName, LastName, FullName, Sex]
			queryString = "SELECT id as identityProperty, first_name, last_name " +
				"FROM person where CONCAT(first_name, ' ', last_name) like '%" + searchTerm + "%'";
			typeId = "Person";
	    }
	    else if (strType.equals("ADDRESS")){
	        // Get all fields from the database as defined by the Type Catalog
			// Address type properties: [identityProperty, City, State, Country, IsPOBox]
			queryString = "SELECT address as identityProperty FROM company where address like '%" + searchTerm + "%'";
			typeId = "Address";
	    }
	    else{
		   mLastError = "Unrecognised search type";
		   return null;
	    }
	   
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
			conn.close();
			//Run IDFiller
			domVLX = (Document) runTransform(domVLX, ID_FILLER);
		} catch (Exception e) {
			mLastError = "Error occured in search(...): "+e.getMessage();
			return null;
		}

	   	return XMLDOM2String(domVLX);
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
   
	private void addEntity(ResultSet rs, String catType, Document domVLX, int entityFields) throws SQLException
	{
	    Node endNode;
	    Node propertiesNode;
	    Node xPosNode;
	    Node yPosNode;
	    Node catTypeNode;
		String fieldName;

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
		 System.out.println(resultStringWriter.toString());
		return resultStringWriter.toString();
	}
}


