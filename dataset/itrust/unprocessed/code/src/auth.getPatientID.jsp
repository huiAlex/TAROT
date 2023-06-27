<%@include file="/global.jsp" %>
<%@page import="java.util.List" %>
<%@page import="edu.ncsu.csc.itrust.action.SearchUsersAction" %>
<%@page import="edu.ncsu.csc.itrust.beans.PatientBean" %>
<%
pageTitle = "iTrust - Please Select a Patient";
%>

<%@include file="/header.jsp" %>

<%
	String uid_pid = request.getParameter("UID_PATIENTID");
	session.setAttribute("pid", uid_pid);
	if (null != uid_pid && !"".equals(uid_pid)) {
		response.sendRedirect(request.getParameter("forward"));
	}
	
	String firstName = request.getParameter("FIRST_NAME");
	String lastName = request.getParameter("LAST_NAME");
	if(firstName == null)
		firstName = "";
	if(lastName == null)
		lastName = "";
	

%>

<%@include file="/util/getUserFrame.jsp"%>
				
<form id="mainForm" action="getPatientID.jsp" method="post">
	<table>
		<tr>
			<td><b>Patient:</b></td>
			<td style="width: 150px; border: 1px solid Gray;">
				<input name="forward" type="hidden" value="<%= StringEscapeUtils.escapeHtml("" + (request.getParameter("forward") )) %>" />
				<input name="UID_PATIENTID" type="hidden" value="" />
				<input name="NAME_PATIENTID" type="text" readonly value="Select a Patient">
				</td>
			<td>
				<input type="button" onclick="getUser('PATIENTID');" value="Find User" />
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<input type="submit" id="selectPatientButton" value="Select Patient" />
			</td>
		</tr>
	</table>
</form>
<br/>
<table>
	<tr> </tr>
	<form id="userSearchForm" action="getPatientID.jsp?forward=<%= StringEscapeUtils.escapeHtml("" + ( request.getParameter("forward") )) %>" method="post">
		<tr>
				<td><b>First Name:</b></td>
				<td style="width: 150px; border: 1px solid Gray;">
					<input name="FIRST_NAME" type="text" value="<%= StringEscapeUtils.escapeHtml("" + ( firstName )) %>" />
				</td>
				<td><b>Last Name:</b></td>
				<td style="width: 150px; border: 1px solid Gray;">
					<input name="LAST_NAME" type="text" value="<%= StringEscapeUtils.escapeHtml("" + ( lastName )) %>" />
				</td>
				<td align="center" colspan="2">
					<input type="submit" value="User Search" />
				</td>
		</tr>
	</form>
</table>

<div id="UserSearch">
	<%
		if( (!"".equals(firstName)) || (!"".equals(lastName))){
			SearchUsersAction searchAction = new SearchUsersAction(prodDAO,loggedInMID.longValue());
			out.println("Searching for users named " + StringEscapeUtils.escapeHtml("" + firstName) + " " + StringEscapeUtils.escapeHtml("" + lastName) + "<br />");
			List<PatientBean> patients = searchAction.searchForPatientsWithName(firstName,lastName);
	%>
			<span class="searchResults">Found <%=StringEscapeUtils.escapeHtml("" + patients.size())%> Records</span>
			<table class="fancyTable" style="width:80%;">
				<tr>
					<th width="20%">MID</td>
					<th width="40%%">First Name</td>
					<th width="40%">Last Name</td>
				</tr>
	<%
		int index = 0;
		for(PatientBean p : patients){
	%>
			<form id="selectPatient<%= StringEscapeUtils.escapeHtml("" + ( String.valueOf(patients.size()) )) %>" action="getPatientID.jsp?forward=<%= StringEscapeUtils.escapeHtml("" + ( request.getParameter("forward") )) %>" method="post">
			<input type="hidden" name="UID_PATIENTID" value="<%= StringEscapeUtils.escapeHtml("" + ( p.getMID() )) %>" />
				<tr <%=(index%2 == 1)?"class=\"alt\"":"" %>>
					<td>
						<input type='submit' width='100%' value="<%=StringEscapeUtils.escapeHtml("" + p.getMID())%>" />
					</td>
					<td>
						<%=StringEscapeUtils.escapeHtml("" + p.getFirstName())%>
					</td>
					<td>
						<%=StringEscapeUtils.escapeHtml("" + p.getLastName())%>
					</td>
				</tr>
			</form>
	<%
		index++;
		}	
	}
	%>
	</table>
</div>

<%@include file="/footer.jsp" %>
