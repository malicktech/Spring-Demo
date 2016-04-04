<%-- <%@ include file="/WEB-INF/pages/includes.jsp"%> --%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<head>
<title>Student Management</title>
</head>
<body>

	<%-- <a href="${pageContext.request.contextPath}/shop/create.html">Create a new shop</a><br/> --%>
	<%-- <a href="${pageContext.request.contextPath}/shop/list.html">View all shops</a><br/> --%>

	Language:
	<a href="./?language=en">English</a> |
	<a href="./?language=tl">Filipino</a> |
	<a href="./?language=fr">French</a>
	<br />
	<br />
	<spring:message code="user.logged" />
	:
	<sec:authentication property="name" />
	<sec:authentication property="authorities" />
	<br />
	<a href="logout"><spring:message code="user.logout" /></a>

	<h1>
		<spring:message code="student.title" />
	</h1>
	<sec:authorize access="hasRole('Admin')">
		<form:form action="student.do" method="POST" commandName="student">
			<table>
				<tr>
					<td><spring:message code="student.id" /></td>
					<td><form:input path="studentId" /></td>
				</tr>
				<tr>
					<td><spring:message code="student.firstname" /></td>
					<td><form:input path="firstname" /></td>
				</tr>
				<tr>
					<td><spring:message code="student.lastname" /></td>
					<td><form:input path="lastname" /></td>
				</tr>
				<tr>
					<td><spring:message code="student.yearLevel" /></td>
					<td><form:input path="yearLevel" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" name="action" value="Add" />
						<input type="submit" name="action" value="Edit" /> <input
						type="submit" name="action" value="Delete" /> <input
						type="submit" name="action" value="Search" /></td>
				</tr>
			</table>
		</form:form>
	</sec:authorize>

	<br>
	<table border="1">
		<th><spring:message code="student.id" /></th>
		<th><spring:message code="student.firstname" /></th>
		<th><spring:message code="student.lastname" /></th>
		<th><spring:message code="student.yearLevel" /></th>
		<c:forEach items="${studentList}" var="student">
			<tr>
				<td>${student.studentId}</td>
				<td>${student.firstname}</td>
				<td>${student.lastname}</td>
				<td>${student.yearLevel}</td>
			</tr>
		</c:forEach>
	</table>

	<div class="col-sm-offset-1 col-sm-10">

		<!--  LIST PERSON -->
		<legend>
			<spring:message code="table.person.title" />
		</legend>
		<div>
			<table id="dataTable" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th><spring:message code="table.person.id" /></th>
						<th><spring:message code="table.person.firstName" /></th>
						<th><spring:message code="table.person.LastName" /></th>
					</tr>
				<thead>
				<tbody>
					<c:forEach var="p" items="${usersList}">
						<tr>
							<td>${p.id}</td>
							<td>${p.firstname}</td>
							<td>${p.lastname}</td>
						<tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>