<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="page_title" value="Nowa naprawa" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>
${info}
<form action="/repair/edit" method="post">
    <label>Data dodania: <input type="text" value="${repair.date}" disabled></label><br>
    <label>Rozpoczęcie naprawy: <input type="date" name="start" value="${repair.start}"></label><br>
    <label>Zakończenie naprawy: <input type="date" name="end" value="${repair.end}"></label><br>
    <label>Opis problemu:
        <textarea name="problem_desc">${repair.problemDesc}</textarea>
    </label><br>
    <label>Opis naprawy:
        <textarea name="repair_desc">${repair.repairDesc}</textarea>
    </label><br>
    Status:<br>
    <label>
        <input type="radio" name="status" value="accepted" <c:if test="${repair.status eq 'ACCEPTED'}">checked</c:if>>
        Przyjęty
    </label><br>
    <label>
        <input type="radio" name="status" value="cost_approved"
               <c:if test="${repair.status eq 'COST_APPROVED'}">checked</c:if>> Zatwierdzone koszty naprawy
    </label><br>
    <label>
        <input type="radio" name="status" value="in_repair" <c:if test="${repair.status eq 'IN_REPAIR'}">checked</c:if>>
        W naprawie
    </label><br>
    <label>
        <input type="radio" name="status" value="ready" <c:if test="${repair.status eq 'READY'}">checked</c:if>> Gotowy
        do odbioru
    </label><br>
    <label>
        <input type="radio" name="status" value="canceled" <c:if test="${repair.status eq 'CANCELED'}">checked</c:if>>
        Rezygnacja
    </label><br>
    <label>Koszt klienta: <input type="number" step="0.01" name="client_cost" value="${repair.clientCost}"></label><br>
    <label>Koszt części: <input type="number" step="0.01" name="parts_cost" value="${repair.partsCost}"></label><br>
    <label>Płaca na h: <input type="number" step="0.01" value="${repair.pay}" disabled></label><br>
    <label>Przepracowane godziny: <input type="number" name="work_hours" step="0.01"
                                         value="${repair.workHours}"></label><br>

    <label>Pracownik:
        <select name="employee_id" required>
            <c:forEach var="employee" items="${employees}">
                <option value="${employee.id}"
                        <c:if test="${employee.id eq repair.employee.id}">selected</c:if>>${employee.surname} ${employee.firstName}</option>
            </c:forEach>
        </select>
    </label><br>

    <label>Klient i samochód:
        <select name="car_id" required>
            <c:forEach var="car" items="${cars}">
                <option value="${car.id}"
                        <c:if test="${car.id eq repair.car.id}">selected</c:if>>${car.client.surname} ${car.client.firstName}
                    [${car.plateNumber}]
                </option>
            </c:forEach>
        </select>

    </label>
    <p><input type="submit"></p>
</form>

<jsp:include page="../template/doc_footer.jsp"/>