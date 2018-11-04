<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="page_title" value="Pracownicy" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>
<%
    LocalDate nextYear = LocalDate.now().plusYears(1);
    System.out.println(nextYear);
%>


<form action="/car/add" method="post">
    <label>Marka: <input type="text" name="brand" required></label><br>
    <label>Model: <input type="text" name="model" required></label><br>
    <label>Rok produkcji: <input type="number" name="prod_year" required></label><br>
    <label>Numer rejestracyjny: <input type="text" name="plate_number" required></label><br>
    <label>Data następnego przeglądu: <input type="date" name="next_inspection" value="<%=nextYear%>"></label><br>
    <input type="hidden" name="cid" value="${param.cid}" required>
    <input type="submit">
</form>
<jsp:include page="../template/doc_footer.jsp"/>