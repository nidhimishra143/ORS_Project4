<%@page import="com.raystec.proj4.controller.ChangePasswordCtl"%>
<%@page import="com.raystec.proj4.controller.ORSView"%>
<%@page import="com.raystec.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.raystec.proj4.util.Datautility"%>
<%@page import="com.raystec.proj4.util.ServletUtility"%>
<html>
<body>
	<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">

		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="com.raystec.proj4.bean.UserBean"
			scope="request"></jsp:useBean>


		<h1>Change Password</h1>

		<H2>
			<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
			</font>
		</H2>

		<input type="hidden" name="id" value="<%=bean.getId()%>">

		<table>

			<tr>
				<th>Old Password*</th>
				<td><input type="password" name="oldPassword"
					value=<%=Datautility
					.getString(request.getParameter("oldPassword") == null ? ""
							: Datautility.getString(request
									.getParameter("oldPassword")))%>><font
					color="red"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font></td>
			</tr>

			<tr>
				<th>New Password*</th>
				<td><input type="password" name="newPassword"
					value=<%=Datautility
					.getString(request.getParameter("newPassword") == null ? ""
							: Datautility.getString(request
									.getParameter("newPassword")))%>><font
					color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font></td>
			</tr>

			<tr>
				<th>Confirm Password*</th>
				<td><input type="password" name="confirmPassword"
					value=<%=Datautility.getString(request
					.getParameter("confirmPassword") == null ? "" : Datautility
					.getString(request.getParameter("confirmPassword")))%>><font
					color="red"> <%=ServletUtility
					.getErrorMessage("confirmPassword", request)%></font></td>
			</tr>

			<tr>
				<th></th>
				<td colspan="2"><input type="submit" name="operation"
					value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>"> &nbsp;
					<input type="submit" name="operation"
					value="<%=ChangePasswordCtl.OP_SAVE%>"> &nbsp;</td>
			</tr>

		</table>
		<H3>
			<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
			</font>
		</H3>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>