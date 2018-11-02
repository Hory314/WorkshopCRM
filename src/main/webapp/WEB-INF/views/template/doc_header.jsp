<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>${page_title}</title>
    <link href="/css/style.css" rel="stylesheet">
</head>

<body style="margin-top: 25px;">
<div style="position: fixed;
    background-color: aliceblue;
    top: 0;
    left: 0;">
    <a href="<c:url value="/"/>">Strona główna</a> |
    <a href="<c:url value="/repairs"/>">Naprawy</a> |
    <a href="<c:url value="/customers"/>">Klienci</a> |
    <a href="<c:url value="/employees"/>">Pracownicy</a> |
    <a href="<c:url value="/reports"/>">Raporty</a> |
    <a href="<c:url value="/logout"/>">Wyloguj się</a>
</div>