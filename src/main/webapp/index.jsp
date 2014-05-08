<%@ page import="java.util.*"%>

<%

//Server-side browser sniffing code
Enumeration e;
e = request.getHeaderNames();
String userAgent = null;

while (e.hasMoreElements())
{
    String key = (String)e.nextElement();
    if (key.equals("user-agent"))
    {
        userAgent = request.getHeader(key);
        break;
    }
}

boolean isIE = false;
if(userAgent.indexOf("MSIE")!=-1)
{
	isIE = true;
}
%>

<HTML>
<HEAD>
<title>Java Deployment Example</title>
<link rel="stylesheet" href="scripts/styles.css" type="text/css"></link>
<script language="JavaScript" src="scripts/events.js"></script>
<script language="JavaScript" src="scripts/filters.js"></script>
<script language="JavaScript" src="scripts/showProperties.js"></script>
<%if(null != session && null != session.getAttribute("user")){ %>
<SCRIPT LANGUAGE="JavaScript">
<!--
var arrSelEnds, arrSelLinks; //Holds the IDs of the most recently right clicked items.
var objChart
var netscape4 = false;
//Sniff the browser version
if ((navigator.appName == "Netscape") && (navigator.appVersion.indexOf("4.") == 0))
{
	netscape4 = true;
}

function onLoad()
{
	if (!(document.applets && document.VLVChart && document.VLVChart.isActive()))
	{
		setTimeout('onLoad()', 200);
		return;
	}

	objChart = document.VLVChart;
  	PollEvent();
  	if (!netscape4)
  	{
  	objChart.width = objChart.offsetWidth; //Fixes width to stop flickering.
  	objChart.height = objChart.offsetHeight; //Fixes height to stop flickering.
  	}
  	
  	//disable unused applet events
  	objChart.disableEvent(1);  //select
	objChart.disableEvent(2);  //unselect
	objChart.disableEvent(9);  //mouse in
	objChart.disableEvent(10); //mouse out

}

function FindThing ()
{
	document.form1.submit1.disabled = true;
	document.form1.searchterm.disabled = true;
	DoFindThing();
}

function DoFindThing()
{

	objChart.showWaiting(true);
	document.form1.searchterm.value = document.form1.searchterm.value.replace( /[£&"'<¬¦]/g, "" );
	objChart.setFile('search.jsp?strTerm=' + escape(document.form1.searchterm.value) + '&strType=' + document.form1.searchtype.value);	
	return false;
}

function OnFileDone() {
	objChart.showWaiting(false);
	
	document.form1.submit1.disabled = false;
	document.form1.searchterm.disabled = false;
	objChart.reorganize();		
}

function OnReorgDone() 
{
	objChart.showWaiting(false);
	objChart.zoomToFitEx(true, 1,false);
}

var lastClickedOnID = "";

function OnMenuCommand(strParam)
{
	if(strParam == 100) 
	{
		objChart.invertSelection();
	} 
	else 
	{
		
 		if(strParam == 101) 
  		{ 
  			//Show Relationships
  			objChart.showWaiting(true);
    			var strType = objChart.getItemType(lastClickedOnID); 
			var realID = objChart.getPropertyValue(lastClickedOnID, 'identityProperty');
  			objChart.mergeVLXEx("expand.jsp?strID="+realID+"&strType="+strType, "", lastClickedOnID, false, false);
		}
		else if(strParam == 102) 
		{ 
			showProperties(objChart, lastClickedOnID);
		}
	}
	lastClickedOnID = "";
}

function OnError(strParam){
	alert(strParam)
}

function OnHelp(url){
	window.open(url,"helpwin","resizable=yes,toolbar=yes,scrollbars=yes,height=660,width=805");			
}

function OnContextMenu(strParam){
	
	lastClickedOnID = strParam.substring(strParam.lastIndexOf(" : ")+3, strParam.length)

	objChart.clearMenuItems();	
	objChart.addMenuItem(100,"Invert Selection");

	if(lastClickedOnID.length>0)
	{
		//
		// Use getLinkEnd1 to check if the id we have is an end id or a link id
		// If its an end id there will be no link end so it will return ""
		if(objChart.getLinkEnd1(lastClickedOnID) == "") 
		{
			objChart.addMenuItem(101,"Show Relationships");
		}
		objChart.addMenuItem(102,"Show Properties");
	}	

  	objChart.showMenu();
}


function OnResize() 
{	//Called on Page Resize
	if(objChart)
	{
		objChart.width = "100%"; //Frees height and width to allow resizing
		objChart.height = "93%";
			
		objChart.width = objChart.offsetWidth; //Re-Fixes to stop flickering.
		objChart.height = objChart.offsetHeight;
	}
}


function PollEvent() 
{
	var strEvent = String(objChart.getEvent());
	var iEventID = Number(strEvent.substring(0, strEvent.search(/:/)));
	var strParam = String(strEvent.substring(strEvent.search(/:/)+1, strEvent.length));
	if(iEventID > 0) 
	{
		var strEventDescription="";//??
		switch(iEventID) 
		{
			case EVENT_MENU_COMMAND:
				OnMenuCommand(strParam)
				break;		
			case EVENT_ERROR_OCCURRED: 
				OnError(strParam);
				break;
			case EVENT_LAYOUTDONE:
				OnReorgDone();
				break;
			case EVENT_FILEDONE:
				OnFileDone();
				break;
			case EVENT_SHOW_HELP:
				OnHelp("Redistributable Help/Viewer online help/Viewer.html");
				break;
			case EVENT_CONTEXTMENU:
				OnContextMenu(strParam);
				break;
					
			//events below are not used in this example
			case EVENT_NONE:
			case EVENT_MOUSEIN:
			case EVENT_MOUSEOUT:
			case EVENT_DOUBLECLICK:
			case EVENT_CLICK:
			case EVENT_OVERRIDE:
			case EVENT_MODIFIED_CHANGED:
			case EVENT_BUFFERREADY:
			case EVENT_SELECTIONCHANGED:
			case EVENT_WARNING:
				break;
		}
		window.setTimeout(PollEvent,0); //event found so try again immediately
		return;
	}
	window.setTimeout(PollEvent,200); //no event found so try again in 1/5 sec
}

//-->	
</SCRIPT>



</HEAD>
<body onLoad="onLoad()" bgcolor="#ffffff" onResize="OnResize()">
	<table bgcolor="#ffffff" width="100%" cellspacing="0" cellpadding="0" border=0 id="tblBanner">
		<tr>
			<td valign=top colspan=2 height=30 width=100%></td>
		</tr>
	</table>
	
	<%if(isIE) 
	{
	%>
	<table width="100%" height="93%" cellspacing="0" cellpadding="0" border=0 id="tblMain" bgcolor="#f0f0f0">
	<%
	}
	else
	{
	%>
	<table width="800" height="400" cellspacing="0" cellpadding="0" border=0 id="tblMain" bgcolor="#f0f0f0">
	<%
	}
	%>
		<tr>
			<td height="70" colspan=2>
				<form name=form1 method=get onSubmit="FindThing(); return false;">
					<STRONG>Please select a search type and enter a search term: </STRONG>
					<select name=searchtype id=searchtype>
						<option selected value="COMPANY">Company</Option>
						<option value="PERSON">Person</Option>
						<option value="ADDRESS">Address</Option>
					</select>  
					<input name=searchterm size=20 value="Sharik" maxLength="100">
					<input type=submit value=Search id=submit1 name=submit1>
				</form>
			</td>
		</tr>
		
		<%if(isIE)
		{
		%>
		<tr height="100%">
			<td id="Applet" width="100%" valign=top>
				<APPLET id="VLVChart" name="VLVChart" archive="i2Viewer.jar" code="com.VDK.viewer.Chart.class" align="top" width="100%" height="93%">			
		<%
		}
		else
		{
		%>
			<tr><td id="Applet" width="800" height="380">
				<APPLET id="VLVChart" name="VLVChart" archive="i2Viewer.jar" code="com.VDK.viewer.Chart.class" align="top" width="800" height="380" VIEWASTEXT>			
		<%
		}
		%>
		      <!-- Modified Parameters -->
					<param name="page-background-color" value="#F0F0F0"/>
					<param name="service-identifier" value="SampleDeployment"/>
					<param name="single-right-click" value="true"/>

          <!-- Default Parameters -->
          <param name="animate-layout" value="true"/>
          <param name="animate-zoom" value="true"/>
          <param name="background-color" value="#ffffff"/>
          <param name="background-image" value=""/>
          <param name="background-image-stretch" value="true"/>
          <param name="border-color" value="#241A1A"/>
          <param name="button-delete" value="true"/>
          <param name="button-find" value="true"/>
          <param name="button-help" value="true"/>
          <param name="button-key-entity-emphasis" value="true"/>
          <param name="button-panning" value="true"/>
          <param name="button-reorganize" value="true"/>
          <param name="button-select-all" value="true"/>
          <param name="button-timebar" value="false"/>
          <param name="button-undelete" value="true"/>
          <param name="button-zoom-actual" value="false"/>
          <param name="button-zoom-area" value="true"/>
          <param name="button-zoom-fit" value="true"/>
          <param name="button-zoom-in" value="false"/>
          <param name="button-zoom-out" value="false"/>
          <param name="button-zoom-selection" value="true"/>
          <param name="default-tooltip" value="true"/>
          <param name="double-click-centering" value="true"/>
          <param name="encode-output" value="true"/>
          <param name="filter-mode" value="conceal"/>
          <param name="initial-waiting-message" value=""/>
          <param name="key-delete" value="true"/>
          <param name="label-truncate-length" value="25"/>
          <param name="layout-manager" value="Peacock2"/>
          <param name="override-clicks" value="false"/>
          <param name="override-keypress" value="false"/>
          <param name="raise-events" value="true"/>
          <param name="rounded-corners" value="true"/>
          <param name="select-on-expand" value="true"/>
          <param name="select-on-merge" value="true"/>
          <param name="show-labels-dragging" value="true"/>
          <param name="tag-line-text" value="Do more with this chart"/>
          <param name="target-separation" value="150"/>
          <param name="timebar-timezone" value=""/>
          <param name="toolbar-border-color" value="#241A1A"/>
          <param name="toolbar-color" value="#F0F0F0"/>
          <param name="tooltip-delay" value="200"/>
          <param name="undelete-on-import" value="true"/>
          <param name="vlx-file" value=""/>
          <param name="zoom-slider" value="true"/>

          <!-- Applet cannot be rendered -->
						<BLOCKQUOTE>
							<center>
								<a href="javascript:pophelp('Redistributable Help/Cannot See/CannotSee.html', '800', '600');">
									<font color="#0000ff">Unable to see the visualization above?</font>
								</a>
							</center>
						</BLOCKQUOTE>
				</APPLET>
			
			<%if(isIE)
			{
			%>
			</td><td valign="top">
			<table border=0 height="100%" cellpadding="0" cellspacing="2" id="tblExpandBar">
					<tr><td onclick="FilterClick(this,0);" onMouseDown="EDown(this)" onMouseUp="ENorm(this)" onMouseOver="EUp(this)" onMouseOut="ENorm(this)" class="selected filterButton"><a href="javascript:void(0)" onclick="NS4FilterClick(this,0);"><img src="VLImages/gifs/new/adult.png" width="22" height="22" alt="Person" border="0"></a></td></tr>
					<tr><td onclick="FilterClick(this,1);" onMouseDown="EDown(this)" onMouseUp="ENorm(this)" onMouseOver="EUp(this)" onMouseOut="ENorm(this)" class="selected filterButton"><a href="javascript:void(0)" onclick="NS4FilterClick(this,1);"><img src="VLImages/gifs/new/organ.png" width="22" height="22" alt="Company" border="0"></a></td></tr>
					<tr><td onclick="FilterClick(this,2);" onMouseDown="EDown(this)" onMouseUp="ENorm(this)" onMouseOver="EUp(this)" onMouseOut="ENorm(this)" class="selected filterButton"><a href="javascript:void(0)" onclick="NS4FilterClick(this,2);"><img src="VLImages/gifs/new/place.png" width="22" height="22" alt="Address" border="0"></a></td></tr>
			<%
			}
			else
			{
			%>
			</td><td width="50" height="400" valign="top" class="bordered" bgcolor="#f0f0f0">
			<table cellpadding="0" cellspacing="2" id="tblExpandBar">
					<tr><td onclick="FilterClick(this,0);" onMouseDown="EDown(this)" onMouseUp="ENorm(this)" onMouseOver="EUp(this)" onMouseOut="ENorm(this)" class="selected filterButton"><a href="javascript:void(0)" onclick="NS4FilterClick(this,0);"><img src="VLImages/gifs/new/adult.png" width="22" height="22" alt="Person" border="0"></a></td></tr>
					<tr><td onclick="FilterClick(this,1);" onMouseDown="EDown(this)" onMouseUp="ENorm(this)" onMouseOver="EUp(this)" onMouseOut="ENorm(this)" class="selected filterButton"><a href="javascript:void(0)" onclick="NS4FilterClick(this,1);"><img src="VLImages/gifs/new/organ.png" width="22" height="22" alt="Company" border="0"></a></td></tr>
					<tr><td onclick="FilterClick(this,2);" onMouseDown="EDown(this)" onMouseUp="ENorm(this)" onMouseOver="EUp(this)" onMouseOut="ENorm(this)" class="selected filterButton"><a href="javascript:void(0)" onclick="NS4FilterClick(this,2);"><img src="VLImages/gifs/new/place.png" width="22" height="22" alt="Address" border="0"></a></td></tr>
			<%
			}
			%>
					<tr height="100%"><td>&nbsp;</td></tr>
				</table>
			</td>
		</tr>
	</table>


	<form style="visibility:hidden;background:white" name="frmCopy"> <!-- This form used for copying data to clipboard -->
		<input type="hidden" name="clipCopy">
	</form>
<%}else{ %>	
			<b>This page is not authorized for you to view. Please login.</b>		
			 <br/>
			 <a href="login.jsp">Login</a>
		 <%} %>
</BODY>
</HTML>
