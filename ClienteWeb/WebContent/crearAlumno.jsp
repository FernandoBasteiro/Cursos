<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Creacion de alumno</title>
</head>
<body>
<% 	String creado = (String)request.getAttribute("alumnoCreado");
	Integer error = (Integer)request.getAttribute("error");
	if (creado != null) { %> 
	<p>El alumno <%=creado%> fue creado correctamente.</p>
	<% } 
	if (error != null) { %>
	<p>Ocurrio un error al crear el alumno</p>
	<% } %>
	
<form action=WebClient?action=crearAlumno method="POST">
<input type=hidden name="action" value="crearAlumno">
<table><tr><td><p>Nombre: </p></td><td><input type=text name=nombre></td><td><input type=submit value="Crear"></td>
</table>
</form>
<a href="index.jsp">Volver al menu</a>
</body>
</html>
