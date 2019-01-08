<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="page_title" value="Logowanie" scope="request"/>
<jsp:include page="template/doc_header.jsp"/>
${login_info}
<form action="<c:url value="/login" />" method="post">
    <p><input type="text" name="login" placeholder="Login" required> (admin)</p>
    <p><input type="password" name="password" placeholder="Hasło" required> (coderslab)</p>
    <p><label><input type="checkbox" name="remember" checked> Zapamiętaj mnie</label></p>
    <p><input type="submit" value="Zaloguj"></p>
</form>
<jsp:include page="template/doc_footer.jsp"/>