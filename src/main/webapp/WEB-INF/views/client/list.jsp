<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="page_title" value="Klienci" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>


<table border="1">
    <tr>
        <th>ID</th>
        <th>Klient</th>
        <th>Data urodzenia</th>
        <th>Samochody</th>
    </tr>
    <c:forEach items="${clientsCarsMap}" var="entry">
        <tr>
            <td>${entry.key.id}</td>
            <td><a href="<c:url value="/client?id=${entry.key.id}"/>">${entry.key.surname} ${entry.key.firstName}</a>
            </td>
            <td>${entry.key.birthdate}</td>
            <td>
                <ul>
                    <c:forEach items="${entry.value}" var="car">
                        <li>${car.brand} ${car.model} [<a
                                href="<c:url value="/car?id=${car.id}"/>">${car.plateNumber}</a>]
                        </li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="../template/doc_footer.jsp"/>