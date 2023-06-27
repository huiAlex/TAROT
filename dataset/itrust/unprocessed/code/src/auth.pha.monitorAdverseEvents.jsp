<%@page errorPage="/auth/exceptionHandler.jsp" %>

<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="edu.ncsu.csc.itrust.action.MonitorAdverseEventAction"%>
<%@page import="edu.ncsu.csc.itrust.beans.AdverseEventBean"%>
<%@page import="edu.ncsu.csc.itrust.exception.FormValidationException"%>

<%@include file="/global.jsp" %>

<%
pageTitle = "iTrust - Monitor Adverse Events";
%>

<%@include file="/header.jsp" %>

<script>
function showBarChart(id) {
	id = id.substring(5, id.indexOf("button"));
	window.open("barchart.jsp?id=" + id, "barchart", "width=300, height=300, resizable=yes");
}
</script>

<%
MonitorAdverseEventAction action = new MonitorAdverseEventAction(prodDAO, loggedInMID.longValue());
List<AdverseEventBean> events = null;
String prescriptionImmunization = "";

if(request.getParameter("prescriptions") != null) {
	try{
		events = action.getReports(true, request.getParameter("startDate"), request.getParameter("endDate"));
		prescriptionImmunization = "Prescription";
		loggingAction.logEvent(TransactionType.ADVERSE_EVENT_VIEW, loggedInMID, 0, "Prescription");
	} catch(FormValidationException e){
		%>
		<div align=center>
			<span class="iTrustError"><%=StringEscapeUtils.escapeHtml(e.getMessage()) %></span>
		</div>
		<%
	}
} else if(request.getParameter("immunizations") != null) {
	try{
		events = action.getReports(false, request.getParameter("startDate"), request.getParameter("endDate"));
		prescriptionImmunization = "Immunization";
		loggingAction.logEvent(TransactionType.ADVERSE_EVENT_VIEW, loggedInMID, 0, "Immunization");
	} catch(FormValidationException e){
		%>
		<div align=center>
			<span class="iTrustError"><%=StringEscapeUtils.escapeHtml(e.getMessage()) %></span>
		</div>
		<%
	}
}


if(prescriptionImmunization != "") {
	session.setAttribute("events", events);
	session.setAttribute("prescriptionImmunization", prescriptionImmunization);
%>
<form action="adverseEventDetails.jsp" method="post">
<input type="hidden" name="eventNumber" value="" />
	<table class="fTable" align="center">
	<tr class="header">
		<th><%= StringEscapeUtils.escapeHtml("" + (prescriptionImmunization )) %></th>
		<th>Reporter</th>
		<th>Date</th>
		<th>Details</th>
		<th><%= StringEscapeUtils.escapeHtml("" + (prescriptionImmunization )) %> Reports By Month</th>
	</tr>
		<%
		int i = 0;
		int codeCount = 0;
		String currentCode = "";
		if(events.size() > 0){
			currentCode = events.get(0).getCode();
		} else {
			%>
			<tr><td colspan="5" align="center">No adverse events reported.</td></tr>
			<%
		}
		
		for(AdverseEventBean event: events){

			if(!"removed".equals(event.getStatus())){
				%>
				<tr><td>
				<%
				if(currentCode.equals(event.getCode())) {
					codeCount++;
				} else {
					%>
					<script>
					document.getElementById("chart<%= StringEscapeUtils.escapeHtml("" + (currentCode )) %>").rowSpan = <%= StringEscapeUtils.escapeHtml("" + (codeCount)) %>;
					</script>
					<%
					codeCount = 1;
					currentCode = event.getCode();
				}
				%>
				
				<%= StringEscapeUtils.escapeHtml("" + (event.getDrug() )) %>(<%= StringEscapeUtils.escapeHtml("" + (event.getCode() )) %>)</td>
				<% 
				try { 
				%>
					<td><%= StringEscapeUtils.escapeHtml("" + (action.getName(Long.parseLong(event.getMID())) )) %></td>
				<%
				} catch(NumberFormatException e) {
					%>
					<td>Invalid Patient ID</td>
					<%
				}
				%>
				<td><%= StringEscapeUtils.escapeHtml("" + (event.getDate() )) %></td>
				<td>
				<a href="adverseEventDetails.jsp?eventNumber=<%= StringEscapeUtils.escapeHtml("" + (i )) %>">Get Details</a></td>
				<%
				if(codeCount == 1) {
					%>
					<td id="chart<%= StringEscapeUtils.escapeHtml("" + (currentCode )) %>" rowspan="1">
					<a href="adverseEventChart.jsp?code=<%= StringEscapeUtils.escapeHtml("" + (currentCode )) %>">View Chart</a>
					</td>
					<%
				}
				
				%>
				</tr>
			<%
			}
			i++;
		}
		%>	
	</table>
	<script>
		document.getElementById("chart<%= StringEscapeUtils.escapeHtml("" + (currentCode )) %>").rowSpan = "<%=codeCount%>";
	</script>
</form>
	<%
	
} else {

String startDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
String endDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
%>
<form action="monitorAdverseEvents.jsp" method="post">
<table class="fTable" align="center">
	<tr class="subHeader">
		<td>Start Date:</td>
		<td>
			<input name="startDate" value="<%= StringEscapeUtils.escapeHtml("" + (startDate)) %>" size="10">
			<input type=button value="Select Date"">
		</td>
		<td>End Date:</td>
		<td>
			<input name="endDate" value="<%= StringEscapeUtils.escapeHtml("" + (endDate)) %>" size = "10">
			<input type=button value="Select Date">
		</td>
	</tr>
</table>
<br />
<br />
<div align="center">
<input type="submit" value="Prescriptions" name="prescriptions" />
<input type="submit" value="Immunizations" name="immunizations" />
</div>
<br />
</form>

<%
}
%>
<%@include file="/footer.jsp" %>
