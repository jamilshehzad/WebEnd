function EUp(objTD) 
{
	//Make the calling Filter Button raised (unless disabled)
	if(objTD.className.search(/disabled/) > -1)
		return;
		
	var strClassName = "";
	if(objTD.className.search(/selected/) > -1)
		strClassName = "selected ";							
		strClassName += "filterButtonUp";
		objTD.className = strClassName;
}
function ENorm(objTD) 
{
	//Make the calling Filter Button normal (unless disabled)
	if(objTD.className.search(/disabled/) > -1)
		return;
		
	var strClassName = "";
	if(objTD.className.search(/selected/) > -1)
		strClassName = "selected ";						
		strClassName += "filterButton";
		objTD.className = strClassName;
}
function EDown(objTD) 
{
	//Make the calling Filter Button recessed (unless disabled)
	if(objTD.className.search(/disabled/) > -1) 
		return;
		
	var strClassName = "";
	if(objTD.className.search(/selected/) > -1)
		strClassName = "selected ";							
		strClassName += "filterButtonDown";
		objTD.className = strClassName;			  
}
function ShowSelectState(objTD, state) 
{
	if (!netscape4)
	{
	//Make the calling Filter Button Selected or Unselected (unless disabled)
		if(objTD.className.search(/disabled/) > -1)
			return;
		var strClassName = "";
		if(state)
			strClassName = "selected ";						
		strClassName += "filterButton";
		objTD.className = strClassName;
	}
}

function FilterClick(objTD, id) 
{
	if(objChart==null)
		return;

	objChart.showWaiting(true);
	
	//Perform Filter Button Filtering (unless disabled)
	if (!netscape4)
	{
		if(objTD.className.search(/disabled/) > -1) return;
	}

	 
	var catType = arrTypeNames[id];
	var filter = objChart.createTypeFilter(catType);//This will either create a type filter or if it has laready been
							//generated
	ShowSelectState(objTD,objChart.isFilterOn(filter)); //Indicate state on Filter Button
	objChart.filter(filter,!objChart.isFilterOn(filter));
	objChart.reorganize();
	objChart.showWaiting(false);
}


function NS4FilterClick(objTD, id) {
		if (!netscape4) return;
		FilterClick(objTD, id);
	}
//This array holds the values of the VLVF Property "EntityType" which must be on each end, to indicate
//which filter button it responds to.
var arrTypeNames = new Array(
    "Person",
    "Company",
    "Address"
    );
