<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import= "dto.AlumnoDTO"%>
    <%@ page import= "dto.CursoDTO"%>
    <% AlumnoDTO alumno = (AlumnoDTO)request.getAttribute("alumno"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Informacion de <%=alumno.getNombre()%></title>
</head>
<body>
<table>
	<tr><th colspan=3>Cursos a los que esta anotado <%=alumno.getNombre() %>(<%=alumno.getLegajo() %>)</th></tr>
	<tr><th>Materia</th><th>Dia</th><th>Turno</th></tr>
	<% for (CursoDTO c : alumno.getCursos()) { %>
	<tr><td><%=c.getMateria().getDescripcion() %></td><td><%=c.getDia() %></td><td><%=c.getTurno() %></td></tr>
	<% } %>
</table>
</body>
</html>