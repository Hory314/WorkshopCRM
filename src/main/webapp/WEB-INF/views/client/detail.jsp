<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="page_title" value="Klient" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>
<table border="1">
    <tr>
        <td>Imię</td>
        <td>${client.firstName}</td>
    </tr>
    <tr>
        <td>Nazwisko</td>
        <td>${client.surname}</td>
    </tr>
    <tr>
        <td>Data urodzenia</td>
        <td>${client.birthdate}</td>
    </tr>
</table>
<p><a href="<c:url value="/customer/edit?id=${client.id}"/>">Edytuj</a></p>
<p><a href="<c:url value="/customer/delete?id=${client.id}"/>">Usuń</a></p>
<hr>
<p>Pojazdy tego klienta</p>
<p><a href="<c:url value="/car/add?cid=${client.id}"/>">Dodaj nowy pojazd</a></p>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Marka</th>
        <th>Model</th>
        <th>Rok produkcji</th>
        <th>Numer rejestracyjny</th>
        <th>Data następnego przeglądu</th>
    </tr>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>
                <a href="<c:url value="/car?id=${car.id}"/>">${car.id}</a><br>
                <a href="<c:url value="/car/edit?id=${car.id}&cid=${client.id}"/>">Edytuj</a><br>
                <a href="<c:url value="/car/delete?id=${car.id}&cid=${client.id}"/>">Usuń</a>
            </td>
            <td>${car.brand}</td>
            <td>${car.model}</td>
            <td>${car.prodYear}</td>
            <td>${car.plateNumber}</td>
            <td>${car.nextInspection}</td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="../template/doc_footer.jsp"/>