<%@ include file="include.jsp" %>
<%@ include file="header.jsp" %>

<script type="text/javascript">
    $(document).ready(function(){
        deleteRoomLink();
    });
    deleteRoom = function() {
        var deleted = $("#deleted").attr("value");
        if (deleted == "Y") {
            $("#deleted").attr("value", "N");
        }
        else {
            $("#deleted").attr("value", "Y");
        }
        deleteRoomLink();
    }
    deleteRoomLink = function() {
        var deleted = $("#deleted").attr("value");
        if (deleted == "Y") {
            $("#deleteRoomLink").text("Room will be deleted when you save - click to cancel the deletion");
        }
        else {
            $("#deleteRoomLink").text("Delete the room");
        }
    }
</script>

<h1>Edit a room</h1>

Room: <c:out value="${command.roomName}" />

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

    Room name:    <input name='command.roomName'     value='${command.roomName}'     type="text" size="32" maxlength="64"><br/>
    Room Code:    <input name='command.roomCode'     value='${command.roomCode}'     type="text" size="32" maxlength="64"><br/>
    Location:     <input name='command.location'     value='${command.location}'     type="text" size="32" maxlength="64"><br/>
    Capacity:     <input name='command.capacity'     value='${command.capacity}'     type="text" size="32" maxlength="64"><br/>
    Self Service: <input name='command.selfService'  value='${command.selfService}'  type="text" size="32" maxlength="64"><br/>
    
    <br/>
    <a id="deleteRoomLink" href="#" onclick="return deleteRoom()">Delete the room</a><br/>
    <div style="display:none">
        <input id="deleted" name="command.deleted" value="${command.deleted}" type="text"/>
    </div>
    <br/>
    
    <p>
        <input name="save" type="submit" value="Save"/>
        <input name="quit" type="button" value="Quit" onClick="javascript:window.location='editfloor.htm?floorid=${floorid}'"/> <br/>
    </p>
</form>

<%@ include file="footer.jsp" %>
