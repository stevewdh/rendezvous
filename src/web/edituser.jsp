<%@ include file="include.jsp" %>
<%@ include file="header.jsp" %>

<h1>Edit a user</h1>

User: <c:out value="${command.username}" />

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

    <input name='command.username'     value='${command.username}'     type="text" size="32" maxlength="64"><br/>
    <input name='command.description'  value='${command.description}'  type="text" size="32" maxlength="64"><br/>
    <input name='command.firstName'    value='${command.firstName}'    type="text" size="32" maxlength="64"><br/>
    <input name='command.surname'      value='${command.surname}'      type="text" size="32" maxlength="64"><br/>
    <input name='command.telephone'    value='${command.telephone}'    type="text" size="32" maxlength="64"><br/>
    <input name='command.mobile'       value='${command.mobile}'       type="text" size="32" maxlength="64"><br/>
    <input name='command.email'        value='${command.email}'        type="text" size="32" maxlength="64"><br/>
    
    <select name='command.userPriviledgeCode'>
        <c:forEach items="${priviledgeCodes.codeList}" var="code">
            <option value='${code.userPriviledgeCode}' <c:if test="${code.userPriviledgeCode == command.userPriviledgeCode}">SELECTED</c:if> >${code.description}</option>
        </c:forEach>
    </select>
    
    <br/>
    <select name='command.status'>
        <c:forEach items="${statusCodes.codeList}" var="code">
            <option value='${code.status}' <c:if test="${code.status == command.status}">SELECTED</c:if> >${code.description}</option>
        </c:forEach>
    </select>
    
    <p>
        <input name="save" type="submit" value="Save"/>
        <input name="quit" type="button" value="Quit" onClick="javascript:window.location='listusers.htm'"/> <br/>
    </p>
</form>

<%@ include file="footer.jsp" %>
