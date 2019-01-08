<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="page_title" value="Pracownicy" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>

<form action="<c:url value="/customer/edit"/>" method="post">
    <label>Imię: <input type="text" name="first_name" value="${client.firstName}" required></label><br>
    <label>Nazwisko: <input type="text" name="surname" value="${client.surname}" required></label><br>
    <label>Data urodzenia: <input type="date" name="birthdate" value="${client.birthdate}" required></label><br>
    <input type="submit">
</form>
<jsp:include page="../template/doc_footer.jsp"/>