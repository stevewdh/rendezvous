<%@ include file="include.jsp" %>
<%@ include file="header.jsp" %>

<h1>Hello - time to sign-in...</h1>

<form method="post">

    <spring:bind path="command.*">
        <c:if test="${not empty status.errorMessages}">
            <c:forEach var="error" items="${status.errorMessages}">
                <font color="red"><c:out value="${error}" escapeXml="false" /> </font>
                <br />
            </c:forEach>
        </c:if>
    </spring:bind>
    <c:if test="${not empty message}">
        <font color="green"><c:out value="${message}" /></font>
        <c:set var="message" value="" scope="session" />
    </c:if>
	
	Username: <input name='username' type="text"     size="32" maxlength="64"><br/>
	Password: <input name='password' type="password" size="32" maxlength="64"><br/>
	
    <p>
        <input name="signin"   type="submit" value="Sign-In"/>
    </p>
</form>

<%@ include file="footer.jsp" %>
