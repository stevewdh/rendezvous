<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>Rendezvous Meeting Application</title>
    <link href="includes/rendezvous.css" rel="stylesheet" type="text/css">
    
    <!-- JQuery -->
    <link type="text/css" href="js/jquery-ui-1.7.1/css/smoothness/jquery-ui-1.7.1.custom.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-ui-1.7.1/js/jquery-1.3.2.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.7.1/js/jquery-ui-1.7.1.custom.min.js"></script>
    
</head>
<body>

<c:if test="${user != null}">
    <a href="homepage.htm">Home</a><br/>
</c:if>

<!-- Create the menu along the top of the page -->
<c:if test="${user.isAdminUser}">
    <a href="adminfunctions.htm">Administration Functions Page</a><br/>
</c:if>

<c:choose>
  <c:when test="${user == null}">
    <a href="signin.htm">Sign In</a>
  </c:when>
  <c:otherwise>
    <a href="signout.htm">Sign Out</a>
  </c:otherwise>
</c:choose>

<br/>

