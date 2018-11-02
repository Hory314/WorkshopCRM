<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="page_title" value="Pracownicy" scope="request"/>
<jsp:include page="../template/doc_header.jsp"/>


<table border="1">
    <tr>
        <th>ID</th>
        <th>Nazwisko i imię</th>
        <th>Adres</th>
        <th>Telefon</th>
        <th>Notatka</th>
        <th>Stawka</th>
        <th>Prowadzone naprawy</th>
    </tr>
    <c:forEach items="${employeesRepairsMap}" var="entry">
        <tr>
            <td>${entry.key.id}</td>
            <td><a href="<c:url value="/employee?id=${entry.key.id}"/>">${entry.key.surname} ${entry.key.firstName}</a>
            </td>
            <td>${entry.key.address}</td>
            <td>${entry.key.phone}</td>
            <td>${entry.key.note}</td>
            <td>${entry.key.pay}</td>
            <td>
                <ul>
                    <c:forEach items="${entry.value}" var="repair">
                        <li>(<a href="<c:url value="/repair?id=${repair.id}"/>">${repair.id}</a>) [<a href="<c:url value="/car?id=${repair.car.id}"/>">${repair.car.plateNumber}</a>]
                        </li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="../template/doc_footer.jsp"/>