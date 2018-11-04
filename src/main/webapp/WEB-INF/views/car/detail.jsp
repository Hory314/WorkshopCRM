<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="page_title" value="Klient" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>
<table border="1">
    <tr>
        <td>Marka</td>
        <td>${car.brand}</td>
    </tr>
    <tr>
        <td>Model</td>
        <td>${car.model}</td>
    </tr>
    <tr>
        <td>Rok produkcji</td>
        <td>${car.prodYear}</td>
    </tr>
    <tr>
        <td>Numer rejestracyjny</td>
        <td>${car.plateNumber}</td>
    </tr>
    <tr>
        <td>Data następnego przeglądu</td>
        <td>${car.nextInspection}</td>
    </tr>
    <tr>
        <td>Właściciel</td>
        <td><a href="<c:url value="/customer?id=${car.client.id}"/>">${car.client.surname} ${car.client.firstName}</a>
        </td>
    </tr>
</table>
<p><a href="<c:url value="/car/edit?id=${car.id}"/>">Edytuj</a></p>
<p><a href="<c:url value="/car/delete?id=${car.id}"/>">Usuń</a></p>
<hr>
<p>Historia napraw</p>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Data</th>
        <th>Rozpoczęcie naprawy</th>
        <th>Zakończenie naprawy</th>
        <th>Opis problemu</th>
        <th>Opis naprawy</th>
        <th>Status</th>
        <th>Koszt klienta</th>
        <th>Koszt części</th>
        <th>Cena za godzinę</th>
        <th>Przepracowane godziny</th>
        <th>Pracownik</th>
    </tr>
    <c:forEach var="repair" items="${repairs}">
        <tr>
            <td>
                <a href="<c:url value="/repair?id=${repair.id}"/>">${repair.id}</a>
            </td>
            <td>${repair.date}</td>
            <td>${repair.start}</td>
            <td>${repair.end}</td>
            <td>${repair.problemDesc}</td>
            <td>${repair.repairDesc}</td>
            <td>${repair.getStringStatus()}</td>
            <td>${repair.clientCost}</td>
            <td>${repair.partsCost}</td>
            <td>${repair.pay}</td>
            <td>${repair.workHours}</td>
            <td>
                <a href="<c:url value="/employee?id=${repair.employee.id}"/>">${repair.employee.firstName} ${repair.employee.surname}</a>
            </td>
        </tr>
    </c:forEach>
</table>
</table>
<jsp:include page="../template/doc_footer.jsp"/>