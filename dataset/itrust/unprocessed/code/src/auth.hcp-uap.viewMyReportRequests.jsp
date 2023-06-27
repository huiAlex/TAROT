<%@taglib uri="/WEB-INF/tags.tld" prefix="itrust" %>
<%@page errorPage="/auth/exceptionHandler.jsp" %>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="edu.ncsu.csc.itrust.beans.ReportRequestBean"%>
<%@page import="edu.ncsu.csc.itrust.action.ViewMyReportRequestsAction"%>
<%@page import="edu.ncsu.csc.itrust.beans.PersonnelBean"%>
<%@page import="edu.ncsu.csc.itrust.beans.PatientBean"%>
<%@page import="edu.ncsu.csc.itrust.dao.mysql.PatientDAO"%>
<%@page import="edu.ncsu.csc.itrust.dao.mysql.PersonnelDAO"%>

<%@include file="/global.jsp" %>

<%
pageTitle = "iTrust - View My Report Requests";
%>

<%@include file="/header.jsp" %>

<%
	PatientDAO patientDAO = new PatientDAO(prodDAO);
	PersonnelDAO personnelDAO = new PersonnelDAO(prodDAO);

ViewMyReportRequestsAction action = new ViewMyReportRequestsAction(prodDAO, loggedInMID.longValue());
String pidString = (String)session.getAttribute("pid");
if (request.getParameter("add") != null) {
	try {
		long patientMID = Long.parseLong(pidString);
		long id = action.addReportRequest(patientMID);
		loggingAction.logEvent(TransactionType.COMPREHENSIVE_REPORT_ADD, loggedInMID.longValue(), patientMID, "Report ID: "+id);
		%><span>Report Request Accepted</span><%
	} catch (Exception e) {
		%><span><%=StringEscapeUtils.escapeHtml(e.getMessage())%></span><%
	}
}
//else {
//	session.removeAttribute("pid");
//}

List<ReportRequestBean> list = action.getAllReportRequestsForRequester();
%>
<br /><br />
<input type="hidden" name="add" id="add" />
<table class="fTable" align="center">
	<tr>
		<th colspan="10">Report Requests</th>
	</tr>
	<tr class="subHeader">
    		<td>ID</td>
   			<td>Patient</td>
  			<td>Requested Date</td>
  			<td>Viewed Date</td>
  			<td>Status</td>
  			<td>Action</td>
  	</tr>
 <%
 		List<PatientBean> patients = new ArrayList<PatientBean>();
 		PatientBean patient;
 		PersonnelBean personnel;
 		int index = 0;
 		for (ReportRequestBean bean:list) { 
 			patient = patientDAO.getPatient(bean.getPatientMID());
 			patients.add(patient);
 %>
			<tr>
				<td ><%= StringEscapeUtils.escapeHtml("" + (bean.getID())) %></td>
				<td ><%= StringEscapeUtils.escapeHtml("" + (patient.getFullName())) %></td>
				<td ><%= StringEscapeUtils.escapeHtml("" + (bean.getRequestedDateString())) %></td>
				<td ><%= StringEscapeUtils.escapeHtml("" + (bean.getViewedDateString())) %></td>
				<td ><%= StringEscapeUtils.escapeHtml("" + (bean.getStatus())) %></td>
				<td ><a href="viewReport.jsp?patient=<%= StringEscapeUtils.escapeHtml("" + (index)) %>&requestID=<%= StringEscapeUtils.escapeHtml("" + (bean.getID())) %>">View</a></td>
			</tr>
<%			index ++;
		} 
		session.setAttribute("patients", patients);
%>
</table>
<br />
<a href="/iTrust/auth/getPatientID.jsp?forward=hcp-uap/viewMyReportRequests.jsp?add=1">Add a new Report Request</a>

<%@include file="/footer.jsp" %>
