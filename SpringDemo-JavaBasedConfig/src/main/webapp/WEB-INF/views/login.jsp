<%@ include file="/WEB-INF/pages/includes.jsp"%>
	
		<h1>Enter your credentials</h1>
		<form action="j_spring_security_check" method="POST">
			<table>
				<tr>
					<td>Username:</td>
					<td><input type="text" name="j_username" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="j_password" /></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="Login"/></td>
				</tr>
			</table>
		</form>
		<font color="red">
			<span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
		</font>
		
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
		