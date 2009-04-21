<%@ include file="include.jsp" %>
<%@ include file="header.jsp" %>

<script type="text/javascript">
    toggleInactiveUsers = function() {
        d = $("#inactiveUsers");
        if (d.css("display") == "none") {
            d.css("display", "block");
        }
        else {
            d.css("display", "none");
        }
    }
</script>

<h1>User List</h1>

<c:forEach items="${userList.userList}" var="user">
    <c:if test="${user.status != 'D'}">
        <c:out value="${user.userId}" /> : 
        <a href='edituser.htm?userid=<c:out value="${user.userId}"/>'>${user.username} (${user.firstName} ${user.surname})
            <c:if test="${user.status == 'N'}">[NEW USER]</c:if>
        </a><br>
    </c:if>
</c:forEach>

<br/>
<a href='edituser.htm'>Create a new user</a>

<br>
<br>
<a href="#" onclick="return toggleInactiveUsers()">Show/Hide Inactive Users</a>
<br>
<div id="inactiveUsers" style="display:none">
    <h1>Inactive Users</h1>
    <c:forEach items="${userList.userList}" var="user">
        <c:if test="${user.status == 'D'}">
            <c:out value="${user.userId}" /> : 
            <a href='edituser.htm?userid=<c:out value="${user.userId}"/>'>${user.username} (${user.firstName} ${user.surname})</a><br>
        </c:if>
    </c:forEach>
</div>



<%@ include file="footer.jsp" %>
