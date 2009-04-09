<%@ include file="include.jsp" %>
<%@ include file="header.jsp" %>

<h1>Building List...</h1>

<c:forEach items="${buildingsList}" var="building">
    <c:out value="${building.buildingId}" /> : 
    
    <a href='editbuilding.htm?buildingid=<c:out value="${building.buildingId}"/>'>
        <c:out value="${building.buildingName}" /><br>
    </a>
		
</c:forEach>

<br/>
<a href='editbuilding.htm'>Create a new building
</a>
    
    


<%@ include file="footer.jsp" %>
    
