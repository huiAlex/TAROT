<%@taglib prefix="itrust" uri="/WEB-INF/tags.tld"%>
<%@page errorPage="/auth/exceptionHandler.jsp"%>

<%@page import="edu.ncsu.csc.itrust.enums.Role"%>
<%@page import="edu.ncsu.csc.itrust.beans.PersonnelBean"%>
<%@page import="edu.ncsu.csc.itrust.BeanBuilder"%>
<%@page import="edu.ncsu.csc.itrust.exception.FormValidationException"%>
<%@page import="edu.ncsu.csc.itrust.action.AddUAPAction"%>

<%@include file="/global.jsp" %>

<%
pageTitle = "iTrust - Add UAP";
%>

<%@include file="/header.jsp" %>

<%
	boolean formIsFilled = request.getParameter("formIsFilled") != null
	&& request.getParameter("formIsFilled").equals("true");

	if (formIsFilled) {
		
		//This page is not actually a "page", it just adds a user and forwards.
		PersonnelBean p = new BeanBuilder<PersonnelBean>().build(request.getParameterMap(), new PersonnelBean());
		p.setRole(Role.UAP);
		try{
			long newMID = new AddUAPAction(DAOFactory.getProductionInstance(), loggedInMID).add(p);
			session.setAttribute("mid", Long.toString(newMID));
			String fullname;
			String password;
			password = p.getPassword();
			fullname = p.getFullName();
			loggingAction.logEvent(TransactionType.UAP_CREATE, loggedInMID.longValue(), newMID, "");
%>
	<div align=center>
		<span class="iTrustMessage">New UAP <%= StringEscapeUtils.escapeHtml("" + (fullname)) %> succesfully added!</span>
		<br />
		<br />
		<table class="fTable">
			<tr>
				<th colspan=2>New UAP Information</th>
			</tr>
			<tr>
				<td class="subHeaderVertical">MID:</td>
				<td><%= StringEscapeUtils.escapeHtml("" + (newMID)) %></td>
			</tr>
			<tr>
				<td class="subHeaderVertical">Temporary Password:</td>
				<td><%= StringEscapeUtils.escapeHtml("" + (password)) %></td>
			</tr>
		</table>
		<br />Please get this information to <b><%= StringEscapeUtils.escapeHtml("" + (fullname)) %></b>! 
		<p>
			<a href = "/iTrust/auth/staff/editPersonnel.jsp">Continue to personnel information.</a>
		</p>
	</div>
<%
		} catch(FormValidationException e){
%>
	<div align=center>
		<span class="iTrustError"><%=StringEscapeUtils.escapeHtml(e.getMessage()) %></span>
	</div>
<%
		}
	}
%>


<div align=center>
<form action="addUAP.jsp" method="post"><input type="hidden"
	name="formIsFilled" value="true"> <br />
<br />
<div style="width: 50%; text-align:left;">Please enter in the name of the new
UAP, with a valid email address. If the user does not have an email
address, use the hospital's email address, [insert pre-defined email],
to recover the password.</div>
<br />
<br />
<table class="fTable">
	<tr>
		<th colspan=2>UAP Information</th>
	</tr>
	<tr>
		<td class="subHeaderVertical">First name:</td>
		<td><input type="text" name="firstName"></td>
	</tr>
	<tr>
		<td class="subHeaderVertical">Last Name:</td>
		<td><input type="text" name="lastName">
	</tr>
	<tr>
		<td class="subHeaderVertical">Email:</td>
		<td><input type="text" name="email"></td>
	</tr>
</table>
<br />
<input type="submit" style="font-size: 16pt; font-weight: bold;" value="Add personnel">
</form>
<br />
</div>

<%@include file="/footer.jsp" %>
