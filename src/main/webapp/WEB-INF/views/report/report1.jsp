<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="page_title" value="Raport #1" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>
<table border="1">
    <tr>
        <th>Nazwisko i imię pracownika</th>
        <th>Suma przepracowanych godzin od ${param.from} do ${param.to}</th>
    </tr>
    <c:set var="total" value="${0}"/>
    <c:forEach var="row" items="${result}">
        <c:set var="total" value="${total + row['sumh']}"/>
        <tr>
            <td><a href="<c:url value="/employee?id=${row['id']}"/>">${row['surname']} ${row['first_name']}</a></td>
            <td>${row['sumh']}</td>
        </tr>
    </c:forEach>
</table>
<p>Razem godzin: ${total}</p>
<jsp:include page="../template/doc_footer.jsp"/>