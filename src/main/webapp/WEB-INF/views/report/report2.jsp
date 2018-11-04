<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="page_title" value="Raport #1" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>
<table border="1">
    <tr>
        <th>ID naprawy</th>
        <th>Zyski od ${param.from} do ${param.to}</th>
    </tr>
    <c:set var="total" value="${0.0}"/>
    <c:forEach var="row" items="${result}">
        <c:set var="total" value="${total + row['profit']}"/>
        <tr>
            <td>(<a href="<c:url value="/repair?id=${row['id']}"/>">${row['id']}</a>) ${row['date']}</td>
            <td>${row['profit']}</td>
        </tr>
    </c:forEach>
</table>
<p>Razem zysk: ${total}</p>
<jsp:include page="../template/doc_footer.jsp"/>