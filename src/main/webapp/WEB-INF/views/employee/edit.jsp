<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="page_title" value="Pracownicy" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>

<form action="/employee/edit" method="post">
    <label>ID: <input type="text" value="${employee.id}" disabled></label><br>
    <label>Imię: <input type="text" name="first_name" value="${employee.firstName}"></label><br>
    <label>Nazwisko: <input type="text" name="surname" value="${employee.surname}"></label><br>
    <label>Adres: <input type="text" name="address" value="${employee.address}"></label><br>
    <label>Telefon: <input type="text" name="phone" value="${employee.phone}"></label><br>
    <label>Notatka: <input type="text" name="note" value="${employee.note}"></label><br>
    <label>Płaca na godzinę: <input type="number" step="0.01" name="pay" value="${employee.pay}"></label><br>
    <input type="submit">
</form>
<jsp:include page="../template/doc_footer.jsp"/>