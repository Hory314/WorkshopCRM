<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="page_title" value="Raporty" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>
<form action="<c:url value="/reports"/>" method="post">
    <p><label>Data od: <input id="from" type="date" name="from" required></label></p>
    <p><label>Data do: <input id="to" type="date" name="to" required></label></p>
    <p><label>Typ raportu: <select id="type" name="type" required>
        <option value="1">Raport 1</option>
        <option value="2">Raport 2</option>
    </select></label></p>
    <p><input type="submit"></p>
</form>
<jsp:include page="../template/doc_footer.jsp"/>