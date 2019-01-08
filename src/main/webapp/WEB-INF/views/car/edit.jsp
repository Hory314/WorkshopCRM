<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="page_title" value="Pracownicy" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>

<form action="<c:url value="/car/edit"/>" method="post">
    <label>Marka: <input type="text" name="brand" value="${car.brand}" required></label><br>
    <label>Model: <input type="text" name="model" value="${car.model}" required></label><br>
    <label>Rok produkcji: <input type="number" name="prod_year" value="${car.prodYear}" required></label><br>
    <label>Numer rejestracyjny: <input type="text" name="plate_number" value="${car.plateNumber}" required></label><br>
    <label>Data następnego przeglądu: <input type="date" name="next_inspection" value="${car.nextInspection}"></label><br>
    <input type="hidden" name="cid" value="${param.cid}" required>
    <input type="submit">
</form>
<jsp:include page="../template/doc_footer.jsp"/>