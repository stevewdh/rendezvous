<%@ include file="include.jsp" %>
<%@ include file="header.jsp" %>

<script type="text/javascript">
    toggleInactiveBuildings = function() {
        d = $("#inactiveBuildings");
        if (d.css("display") == "none") {
            d.css("display", "block");
        }
        else {
            d.css("display", "none");
        }
    }
</script>

<h1>Building List</h1>

<c:forEach items="${buildingList.buildingList}" var="building">
    <c:if test="${building.isActive}">
        <c:out value="${building.buildingId}" /> : 
        <a href='editbuilding.htm?buildingid=<c:out value="${building.buildingId}"/>'>
            ${building.buildingName}<br>
        </a>
    </c:if>
</c:forEach>

<br/>
<a href='editbuilding.htm'>Create a new building
</a>
    
<br>
<br>
<a href="#" onclick="return toggleInactiveBuildings()">Show/Hide Inactive Buildings</a>
<br>
<div id="inactiveBuildings" style="display:none">
    <h1>Inactive Buildings</h1>
    <c:forEach items="${buildingList.buildingList}" var="building">
        <c:if test="${building.isActive == false}">
            <c:out value="${building.buildingId}" /> : 
            <a href='editbuilding.htm?buildingid=<c:out value="${building.buildingId}"/>'>
                ${building.buildingName}<br>
            </a>
        </c:if>
    </c:forEach>
</div>


<%@ include file="footer.jsp" %>
    
