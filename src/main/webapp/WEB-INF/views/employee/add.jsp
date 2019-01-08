<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="page_title" value="Pracownicy" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>

<form action="<c:url value="/employee/add"/>" method="post">
    <label>ID: <input type="text" disabled></label><br>
    <label>Imię: <input type="text" name="first_name"></label><br>
    <label>Nazwisko: <input type="text" name="surname"></label><br>
    <label>Adres: <input type="text" name="address"></label><br>
    <label>Telefon: <input type="text" name="phone"></label><br>
    <label>Notatka: <input type="text" name="note"></label><br>
    <label>Płaca na godzinę: <input type="number" step="0.01" name="pay"></label><br>
    <input type="submit">
</form>
<jsp:include page="../template/doc_footer.jsp"/>