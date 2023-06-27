<%@taglib uri="/WEB-INF/tags.tld" prefix="itrust"%>
<%@page errorPage="/auth/exceptionHandler.jsp"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="edu.ncsu.csc.itrust.beans.PatientBean"%>
<%@page import="edu.ncsu.csc.itrust.beans.PatientVisitBean"%>
<%@page import="edu.ncsu.csc.itrust.action.ViewPatientOfficeVisitHistoryAction"%>

<%@include file="/global.jsp" %>

<%
pageTitle = "iTrust - View All Patients";
%>

<%@include file="/header.jsp" %>

<%
ViewPatientOfficeVisitHistoryAction action = new ViewPatientOfficeVisitHistoryAction(prodDAO, loggedInMID.longValue());
List<PatientVisitBean> patientVisits = action.getPatients();
loggingAction.logEvent(TransactionType.PATIENT_LIST_VIEW, loggedInMID, 0, "");

%>
<br />

<form action="viewReport.jsp" method="post" name="myform">
<table class="fTable" align="center">
	
	<thead>
	<tr>
		<th colspan="3">Past Patients</th>
	</tr>

	<tr class="subHeader">
		<th>Patient</th>
		<th>Address</th>
		<th>Last Visit</th>

	</tr>
	</thead>
	<tbody>
	<%
		List<PatientBean> patients = new ArrayList<PatientBean>();
		int index = 0;
		for (PatientVisitBean bean : patientVisits) {
			patients.add(bean.getPatient());
	%>
	<tr>
		<td >
			<a href="editPHR.jsp?patient=<%= StringEscapeUtils.escapeHtml("" + (index)) %>">
		
		
			<%= StringEscapeUtils.escapeHtml("" + (bean.getPatientName())) %>	
		
		
			</a>
			</td>
		<td ><%= StringEscapeUtils.escapeHtml("" + (bean.getAddress1() +" " +bean.getAddress2())) %></td>
		<td ><%= StringEscapeUtils.escapeHtml("" + (bean.getLastOVDateM() +"/" +bean.getLastOVDateD() +"/" +bean.getLastOVDateY())) %></td>
	</tr>
	<%
			index ++;
		}
		session.setAttribute("patients", patients);
	%>
	</tbody>
</table>
</form>
<br />
<br />

<%@include file="/footer.jsp" %>
