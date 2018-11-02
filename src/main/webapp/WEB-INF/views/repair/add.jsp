<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="page_title" value="Nowa naprawa" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>
${info}
<form action="/repair/add" method="post">
    <%
        LocalDate todayDate = LocalDateTime.now().toLocalDate();
    %>
    <label>Rozpoczęcie naprawy: <input type="date" name="start" value="<%=todayDate%>"></label><br>
    <label>Opis problemu:
        <textarea name="problem_desc" required></textarea>
    </label><br>
    <label>Pracownik:
        <select name="emoloyee_id" required>
            <c:forEach var="employee" items="${employees}">
                <option value="${employee.id}">${employee.surname} ${employee.firstName}</option>
            </c:forEach>
        </select>
    </label><br>

    <label>Klient i samochód:
        <select name="car_id" required>
            <c:forEach var="car" items="${cars}">
                <option value="${car.id}">${car.client.surname} ${car.client.firstName} [${car.plateNumber}]</option>
            </c:forEach>
        </select>
    </label> (<a href="<c:url value="/customer/add"/>" target="_blank">Dodaj klienta</a>)
    <p><input type="submit"></p>
</form>

<jsp:include page="../template/doc_footer.jsp"/>