<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="page_title" value="Naprawy" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>
<p><a href="<c:url value="/repair/add"/>">Dodaj nową naprawę</a></p>
<table border="1">
    <tr>
        <td>ID</td>
        <td>${repair.id}</td>
    </tr>
    <tr>
        <td>Data</td>
        <td>${repair.date}</td>
    </tr>
    <tr>
        <td>Rozpoczęcie naprawy</td>
        <td>${repair.start}</td>
    </tr>
    <tr>
        <td>Zakończenie naprawy</td>
        <td>${repair.end}</td>
    </tr>
    <tr>
        <td>Opis problemu</td>
        <td>${repair.problemDesc}</td>
    </tr>
    <tr>
        <td>Opis naprawy</td>
        <td>${repair.repairDesc}</td>
    </tr>
    <tr>
        <td>Status</td>
        <td>${repair.getStringStatus()}</td>
    </tr>
    <tr>
        <td>Koszt klienta</td>
        <td>${repair.clientCost}</td>
    </tr>
    <tr>
        <td>Koszt części</td>
        <td>${repair.partsCost}</td>
    </tr>
    <tr>
        <td>Cena za godzinę</td>
        <td>${repair.pay}</td>
    </tr>
    <tr>
        <td>Przepracowane godziny</td>
        <td>${repair.workHours}</td>
    </tr>
    <tr>
        <td>Pracownik</td>
        <td>
            <a href="<c:url value="/employee?id=${repair.employee.id}"/>">${repair.employee.firstName} ${repair.employee.surname}</a>
        </td>
    </tr>
    <tr>
        <td>Samochód</td>
        <td>${repair.car.brand} ${repair.car.model}<br>[<a
                href="<c:url value="/car?id=${repair.car.id}"/>">${repair.car.plateNumber}</a>]
        </td>
    </tr>
</table>
<p><a href="<c:url value="/repair/edit?id=${repair.id}"/>">Edytuj</a></p>
<p><a href="<c:url value="/repair/delete?id=${repair.id}"/>">Usuń</a></p>
<jsp:include page="../template/doc_footer.jsp"/>