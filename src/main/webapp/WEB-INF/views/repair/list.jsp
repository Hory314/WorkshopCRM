<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="page_title" value="Naprawy w toku" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>
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
        <th>Samochód</th>
    </tr>
    <c:forEach var="repair" items="${repairs}">
        <tr>
            <td>${repair.id}</td>
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
            <td>${repair.employee.firstName} ${repair.employee.surname}</td>
            <td>${repair.car.brand} ${repair.car.model}<br>[${repair.car.plateNumber}]</td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="../template/doc_footer.jsp"/>