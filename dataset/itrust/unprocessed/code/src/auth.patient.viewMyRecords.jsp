<%@taglib uri="/WEB-INF/tags.tld" prefix="itrust"%>
<%@page errorPage="/auth/exceptionHandler.jsp"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="edu.ncsu.csc.itrust.action.EditPHRAction"%>
<%@page import="edu.ncsu.csc.itrust.beans.PatientBean"%>
<%@page import="edu.ncsu.csc.itrust.beans.PersonnelBean"%>
<%@page import="edu.ncsu.csc.itrust.beans.AllergyBean"%>
<%@page import="edu.ncsu.csc.itrust.beans.FamilyMemberBean"%>
<%@page import="edu.ncsu.csc.itrust.beans.HealthRecord"%>
<%@page import="edu.ncsu.csc.itrust.beans.OfficeVisitBean"%>
<%@page import="edu.ncsu.csc.itrust.beans.ProcedureBean"%>
<%@page import="edu.ncsu.csc.itrust.action.ViewMyRecordsAction"%>
<%@page import="edu.ncsu.csc.itrust.dao.mysql.PatientDAO"%>
<%@page import="edu.ncsu.csc.itrust.dao.mysql.PersonnelDAO"%>


<%@include file="/global.jsp"%>

<%
	pageTitle = "iTrust - View My Records";
%>

<%@include file="/header.jsp"%>

<%
session.removeAttribute("personnelList");
Long originalLoggedInMID = loggedInMID;

	String representee = request.getParameter("rep");
	boolean isRepresenting = false;
	if (representee != null && !"".equals(representee)) {
		int representeeIndex = Integer.parseInt(representee);
		List<PatientBean> representees = (List<PatientBean>) session.getAttribute("representees");
		if(representees != null) {
			loggedInMID = new Long("" + representees.get(representeeIndex).getMID());
//			session.removeAttribute("representees");
			isRepresenting = true;
//		loggedInMID = new Long(action.representPatient(representee));
%>
<span >You are currently viewing your representee's records</span><br />
<%
		}
	}
	
	PatientBean patient = new PatientDAO(prodDAO).getPatient(loggedInMID.longValue());
	DateFormat df = DateFormat.getDateInstance();
	ViewMyRecordsAction action = new ViewMyRecordsAction(prodDAO, loggedInMID.longValue());

	patient = action.getPatient();
	List<HealthRecord> records = action.getAllHealthRecords();
	List<OfficeVisitBean> officeVisits = action.getAllOfficeVisits();
	List<FamilyMemberBean> family = action.getFamilyHistory();
	List<AllergyBean> allergies = action.getAllergies();
	List<PatientBean> represented = action.getRepresented();
	
	loggingAction.logEvent(TransactionType.MEDICAL_RECORD_VIEW, originalLoggedInMID, patient.getMID(), "");
%> 

<%
if (request.getParameter("message") != null) {
%>
	<div class="iTrustMessage" style="font-size: 24px;" align=center>
		<%= StringEscapeUtils.escapeHtml("" + (request.getParameter("message") )) %>
	</div>
<%
}
%>
<br />
<table align=center>
	<tr> <td>
	<div style="float:left; margin-right:5px;">
		<table class="fTable" border=1 align="center">
			<tr>
				<th colspan="2" >Patient Information</th>
			</tr>
			<tr>
				<td class="subHeaderVertical">Name:</td>
				<td ><%= StringEscapeUtils.escapeHtml("" + (patient.getFullName())) %></td>
			</tr>
			<tr>
				<td  class="subHeaderVertical">Address:</td>
				<td >
					<%= StringEscapeUtils.escapeHtml("" + (patient.getStreetAddress1())) %><br />
					<%="".equals(patient.getStreetAddress2()) ? ""
						: patient.getStreetAddress2() + "<br />"%>
					<%= StringEscapeUtils.escapeHtml("" + (patient.getStreetAddress3())) %><br />
				</td>
			</tr>
			<tr>
				<td class="subHeaderVertical">Phone:</td>
				<td ><%= StringEscapeUtils.escapeHtml("" + (patient.getPhone())) %></td>
			</tr>
			<tr>
				<td class="subHeaderVertical">Email:</td>
				<td ><%= StringEscapeUtils.escapeHtml("" + (patient.getEmail())) %></td>
			</tr>
			<tr>
				<th colspan="2">
					Insurance Information
				</th>
			</tr>
			<tr>
				<td class="subHeaderVertical">
					Provider Name:
				</td>
				<td ><%= StringEscapeUtils.escapeHtml("" + (patient.getIcName())) %></td>
			</tr>
			<tr>
				<td class="subHeaderVertical">Address:</td>
				<td >
					<%= StringEscapeUtils.escapeHtml("" + (patient.getIcAddress1())) %><br />
					<%="".equals(patient.getIcAddress2()) ? "" : patient
						.getIcAddress2()
						+ "<br />"%>
					<%= StringEscapeUtils.escapeHtml("" + (patient.getIcAddress3())) %><br />
				</td>
			</tr>
			<tr>
				<td class="subHeaderVertical">Phone:</td>
				<td ><%= StringEscapeUtils.escapeHtml("" + (patient.getIcPhone())) %></td>
			</tr>
		</table>
	</div>
	<div style="float: left; margin-left: 5px;">
		<table class="fTable" border=1 align="center">
			<tr>
				<th>Office Visits</th>
				<th>Survey</th>
			</tr>
<%
	for (OfficeVisitBean ov : officeVisits) {
%>
			<tr>
				<td >
					<a href="viewOfficeVisit.jsp?ovID=<%= StringEscapeUtils.escapeHtml("" + (ov.getVisitID())) %><%=isRepresenting ? "&repMID=" + loggedInMID.longValue() : "" %>"><%= StringEscapeUtils.escapeHtml("" + (df.format(ov.getVisitDate()))) %></a></td>
<%
		if (action.isSurveyCompleted(ov.getVisitID())) {
%>
				<td>&nbsp;</td>
<%
		} else {
%>
				<td >
					<a href="survey.jsp?ovID=<%= StringEscapeUtils.escapeHtml("" + (ov.getVisitID())) %>&ovDate=<%= StringEscapeUtils.escapeHtml("" + (df.format(ov.getVisitDate()))) %>">
						Complete Visit Survey
					</a>
				</td>
<%
		}
	}
%>
			</tr>
			<tr>
				<td colspan=2 align=center>
					<a href="viewPrescriptionRecords.jsp?<%= StringEscapeUtils.escapeHtml("" + (isRepresenting ? "&rep=" + loggedInMID.longValue() : "" )) %>">
						Get Prescriptions
					</a>
				</td>
			</tr>
		</table>
	</div>
	</td> </tr>
</table>

<br />
<br />
<table class="fTable" align="center" >
	<tr>
		<th colspan="9">
			Family Medical History
		</th>
	</tr>
	<tr class="subHeader">
		<td>Name</td>
		<td>Relation</td>
		<td>High Blood Pressure</td>
		<td>High Cholesterol</td>
		<td>Diabetes</td>
		<td>Cancer</td>
		<td>Heart Disease</td>
		<td>Smoker</td>
		<td>Cause of Death</td>
	</tr>
	<%
		if (family.size() == 0) {
	%>
	<tr>
		<td colspan="9" style="text-align: center;">
			No Relations on record
		</td>
	</tr>
	<%
		} else {
			for (FamilyMemberBean member : family) {
	%>
	<tr>
		<td ><%= StringEscapeUtils.escapeHtml("" + (member.getFullName())) %></td>
		<td ><%= StringEscapeUtils.escapeHtml("" + (member.getRelation())) %></td>
		<td  align=center><%=action.doesFamilyMemberHaveHighBP(member) ? "x"
							: ""%></td>
		<td  align=center><%=action
									.doesFamilyMemberHaveHighCholesterol(member) ? "x"
							: ""%></td>
		<td  align=center><%=action
									.doesFamilyMemberHaveDiabetes(member) ? "x"
							: ""%></td>
		<td  align=center><%=action.doesFamilyMemberHaveCancer(member) ? "x"
							: ""%></td>
		<td  align=center><%=action
									.doesFamilyMemberHaveHeartDisease(member) ? "x"
							: ""%></td>
		<td  align=center><%=action.isFamilyMemberSmoker(member) ? "x"
					: ""%></td>
		<td ><%= StringEscapeUtils.escapeHtml("" + (action.getFamilyMemberCOD(member))) %></td>
	</tr>
	<%
			}
		}
	%>
</table>
<br />
<br />
<table align=center>
	<tr> <td>
	<div style="float:left; margin-right:5px;">
		<table class="fTable" align="center" >
			<tr>
				<th colspan="2">Allergies</th>
			</tr>
			<tr class="subHeader">
				<td>Allergy Description</td>
				<td>First Found</td>
			</tr>

<%
	if (allergies.size() == 0) {
%>
			<tr>
				<td colspan="2" >No Allergies on record</td>
			</tr>
<%
	} else {
		for (AllergyBean allergy : allergies) {
%>
			<tr>
				<td ><%= StringEscapeUtils.escapeHtml("" + (allergy.getDescription())) %></td>
				<td ><%= StringEscapeUtils.escapeHtml("" + (df.format(allergy.getFirstFound()))) %></td>
			</tr>
<%
		}
	}
%>
		</table>
	</div>
	
	<div style="float:left; margin-left:5px;">
		<table class="fTable">
			<tr>
				<th> Patients <%= StringEscapeUtils.escapeHtml("" + (patient.getFirstName())) %> Represents </th>
			</tr>
			<tr class="subHeader">
				<td>Patient</td>
			</tr>
<%
	if (represented.size() == 0) {
%>
			<tr>
				<td >
					<%= StringEscapeUtils.escapeHtml("" + (patient.getFirstName())) %> is not representing any patients
				</td>
			</tr>
<%
	} else {
		int index = 0;
		for (PatientBean p : represented) {
%>
			<tr>
				<td >
<%
	if(isRepresenting) {
%>
		<%= StringEscapeUtils.escapeHtml("" + (p.getFullName())) %>
<%
	} else {
%>
		<a href="viewMyRecords.jsp?rep=<%= StringEscapeUtils.escapeHtml("" + (index)) %>"><%= StringEscapeUtils.escapeHtml("" + (p.getFullName())) %></a>
<%
	}
%>
					
				</td>
			</tr>
<%
		index++;
		}
		if(!isRepresenting) {
			session.setAttribute("representees", represented);
		}
	}
%>
		</table>
	</div>
	</td></tr>
</table>
<br />
<br />
<table class="fTable" align="center">
	<tr>
		<th colspan="10">
			Basic Health History
		</th>
	</tr>
	<tr class="subHeader" >
		<td>Height</td>
		<td>Weight</td>
		<td>Smokes?</td>
		<td>Blood Pressure</td>
		<td>HDL</td>
		<td>LDL</td>
		<td>Triglycerides</td>
		<td>Total Cholesterol</td>
		<td>Last Recorded</td>
		<td>By Personnel</td>
	</tr>
<%
	if(records.size() > 0) {
		List<PersonnelBean> personnelList = new ArrayList<PersonnelBean>();
		int index = 0;
		for (HealthRecord hr : records) {
%>
	<tr>
		<td ><%= StringEscapeUtils.escapeHtml("" + (hr.getHeight())) %>in</td>
		<td ><%= StringEscapeUtils.escapeHtml("" + (hr.getWeight())) %>lbs</td>
		<td ><%= StringEscapeUtils.escapeHtml("" + (hr.isSmoker() ? "Y" : "N")) %></td>
		<td ><%= StringEscapeUtils.escapeHtml("" + (hr.getBloodPressure())) %> mmHg</td>
		<td ><%= StringEscapeUtils.escapeHtml("" + (hr.getCholesterolHDL())) %> mg/dL</td>
		<td ><%= StringEscapeUtils.escapeHtml("" + (hr.getCholesterolLDL())) %> mg/dL</td>
		<td ><%= StringEscapeUtils.escapeHtml("" + (hr.getCholesterolTri())) %> mg/dL</td>
		<td ><%= StringEscapeUtils.escapeHtml("" + (hr.getTotalCholesterol())) %> mg/dL</td>
		<td ><%= StringEscapeUtils.escapeHtml("" + (df.format(hr.getDateRecorded()))) %></td>
		<%
			PersonnelBean personnel = new PersonnelDAO(prodDAO).getPersonnel(hr.getPersonnelID());
		    if (personnel != null) {
		        personnelList.add(personnel);
		    }
		%>
		<td >
		  <% if (personnel != null) { %>
		  <a href="/iTrust/auth/viewPersonnel.jsp?personnel=<%= StringEscapeUtils.escapeHtml("" + (index)) %>">
		      <%= StringEscapeUtils.escapeHtml("" + (personnel.getFullName())) %>
		  </a>
		  <% } %>
		</td>
	</tr>
<%
			index++;
		}
		session.setAttribute("personnelList", personnelList);
	}
	else {
%>
		<tr>
			<td colspan=11 align=center>
				No Data
			</td>
		</tr>
<%		
	}
%>
</table>
<br />
<br />
<table class="fTable" align="center">
	<tr>
		<th colspan="4">Immunizations</th>
	</tr>
	<tr class="subHeader">
  		<td>CPT Code</th>
	 	<td>Description</th>
   		<td>Date Received</th>
   		<td>Adverse Event</th>
  	</tr>
<%
boolean hasNoData = true;
for (OfficeVisitBean ov: officeVisits) {
	List<ProcedureBean> ovProcs = action.getProcedures(ov.getVisitID());
	for (ProcedureBean proc: ovProcs) {
		if (null != proc.getAttribute() && proc.getAttribute().equals("immunization")) {
			hasNoData=false;
%>
	<tr>
		<td><%= StringEscapeUtils.escapeHtml("" + (proc.getCPTCode())) %></td>
		<td><%= StringEscapeUtils.escapeHtml("" + (proc.getDescription() )) %></td>
		<td><%= StringEscapeUtils.escapeHtml("" + (proc.getDate() )) %></td>
		<td>
		<%
			Date date = new Date();
			date.setYear(date.getYear()-1);
			if(proc.getDate().after(date)){
		%>
		<a href="reportAdverseEvent.jsp?presID=<%= StringEscapeUtils.escapeHtml("" + (proc.getDescription())) %>&HCPMID=<%= StringEscapeUtils.escapeHtml("" + (ov.getHcpID() )) %>&code=<%= StringEscapeUtils.escapeHtml("" + (proc.getCPTCode())) %>">Report</a>	
	
<%
		}%></td></tr><% }
	}
}
if(hasNoData) {
%>
	<tr>
		<td colspan=4 align=center>
			No Data
		</td>
	</tr>
<%
}
%>
</table>
<br />

<%@include file="/footer.jsp"%>
