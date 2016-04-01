<%@ include file="/WEB-INF/views/layout/header.jsp"%>


<div class="col-sm-offset-1 col-sm-10">
 
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

