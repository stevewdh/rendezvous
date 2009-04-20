<%@ include file="include.jsp" %>
<%@ include file="header.jsp" %>

<script type="text/javascript">
    $(document).ready(function(){
        $(".datepicker").datepicker({dateFormat:'dd/mm/yy'});
    });
    activeFieldsOn = function() {
        $("#activeFields")    .attr({style : "display:block"});
        $("#editActiveFields").attr({style : "display:none"});
    }
</script>

<h1>Edit a building</h1>

Building: <c:out value="${command.buildingName}" />

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

    <input name='command.buildingName' value='${command.buildingName}' type="text" size="32" maxlength="64"><br/>
    <input name='command.address1'     value='${command.address1}'     type="text" size="32" maxlength="64"><br/>
    <input name='command.address2'     value='${command.address2}'     type="text" size="32" maxlength="64"><br/>
    <input name='command.address3'     value='${command.address3}'     type="text" size="32" maxlength="64"><br/>
    <input name='command.town'         value='${command.town}'         type="text" size="32" maxlength="64"><br/>
    <input name='command.postcode'     value='${command.postcode}'     type="text" size="32" maxlength="64"><br/>
    	
     <select name='command.country.countryCode'>
        <option value=""></option>
        <c:forEach items="${countryCodes.codeList}" var="countryCode">
            <option value='${countryCode.countryCode}' <c:if test="${countryCode.countryCode == command.country.countryCode}">SELECTED</c:if> >${countryCode.countryName}</option>
        </c:forEach>
    </select>
    
    <br>
    <div id="editActiveFields">
        <a href="#" onclick="return activeFieldsOn()">Edit building active dates</a>
    </div>
    <div id="activeFields" style="display:none">
        <input name="command.activeFrom"   value='<fmt:formatDate value="${command.activeFrom}"  type="DATE" pattern="dd/MM/yyyy"/>'  type="text" class="datepicker"/><br/>
        <input name="command.activeUntil"  value='<fmt:formatDate value="${command.activeUntil}" type="DATE" pattern="dd/MM/yyyy"/>'  type="text" class="datepicker"/><br/>
    </div>
    
    <p>
        <input name="save" type="submit" value="Save"/>
        <input name="quit" type="button" value="Quit" onClick="javascript:window.location='listbuildings.htm'"/> <br/>
    </p>
</form>

<br/>
<u>List of floors</u>
<br/>
<c:forEach items="${command.floors}" var="floor">
    <a href="editfloor.htm?buildingid=${command.buildingId}&floorid=${floor.floorId}">${floor.floorName}</a>
    <br/>
</c:forEach>

<br/>
<a href="editfloor.htm?buildingid=${command.buildingId}">New Floor</a>

 
<%@ include file="footer.jsp" %>
