<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="com.hehaho.googleapp.demo.util.Constants" %>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Hello App Engine</title>
  </head>

  <body>
    <h1>Hello App Engine!</h1>
	
    <table>
      <tr>
        <td colspan="2" style="font-weight:bold;">Available Servlets:</td>        
      </tr>
      <tr>
        <td><a href="demo">Demo</a></td>
      </tr>
      <tr>
        <td><a href="print">PrintTask</a></td>
      </tr>
      <tr>
        <td><a href="fetch">FetchTest</a></td>
      </tr>
      <tr>
        <td><a href="jdoservice">JDOTest</a></td>
      </tr>
    </table>
    <p><% 
		if(request.getAttribute(Constants.DESTINATION_KEY) != null){
			%>
			<%=request.getAttribute(Constants.DESTINATION_KEY)%>		
		<%}
		%></p>
    <p><img src="http://code.google.com/appengine/images/appengine-silver-120x30.gif" 
alt="Powered by Google App Engine" /></p>
  </body>
</html>
