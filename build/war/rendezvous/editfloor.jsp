<%@ include file="include.jsp" %>
<%@ include file="header.jsp" %>

<script type="text/javascript">
    $(document).ready(function(){
        deleteFloorLink();
    });
    deleteFloor = function() {
        var deleted = $("#deleted").attr("value");
        if (deleted == "Y") {
            $("#deleted").attr("value", "N");
        }
        else {
            $("#deleted").attr("value", "Y");
        }
        deleteFloorLink();
    }
    deleteFloorLink = function() {
        var deleted = $("#deleted").attr("value");
        if (deleted == "Y") {
            $("#deleteFloorLink").text("Floor will be deleted when you save - click to cancel the deletion");
        }
        else {
            $("#deleteFloorLink").text("Delete the floor");
        }
    }
</script>

<h1>Edit a building floor</h1>

Floor: ${command.floorName}

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
	
	
    Floor Name: <input name='command.floorName'    value='${command.floorName}' type="text" size="32" maxlength="64"><br/>
    Display Order: <input name='command.displayOrder' value='${command.displayOrder}' type="text" size="32" maxlength="64"><br/>
    
    <br/>
    <a id="deleteFloorLink" href="#" onclick="return deleteFloor()">Delete the floor</a><br/>
    <div style="display:none">
        <input id="deleted" name="command.deleted" value="${command.deleted}" type="text"/>
    </div>
    <br/>
    
    <p>
        <input name="save" type="submit" value="Save"/>
        <input name="quit" type="button" value="Quit" onClick="javascript:window.location='editbuilding.htm?buildingid=${buildingid}'"/> <br/>
    </p>
</form>

<br/>
<u>List of rooms</u>
<br/>
<c:forEach items="${command.rooms}" var="room">
    <a href="editroom.htm?roomid=${room.roomId}&floorid=${command.floorId}">${room.roomCode} - ${room.roomName}</a>
    <br/>
</c:forEach>

<br/>
<a href="editroom.htm?floorid=${command.floorId}">New Room</a>

<%@ include file="footer.jsp" %>
