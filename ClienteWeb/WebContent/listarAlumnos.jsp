<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.ArrayList"%>
<%@ page import= "dto.AlumnoDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alumnos</title>
</head>
<body>
<table border=1>
<tr><th colspan=2>Listado de Alumnos</th></tr>
<tr><th>Legajo</th><th>Nombre</th></tr>
<%
	AlumnoDTO alumno;
	ArrayList<AlumnoDTO> alumnos = (ArrayList<AlumnoDTO>)request.getAttribute("alumnos");
	for(Iterator<AlumnoDTO> i = alumnos.iterator(); i.hasNext();)
	{
		alumno = i.next();
%>
	<tr>
		<td>
		<a href="Controlador?action=verAlumno&legajo=<%=alumno.getLegajo()%>"><%=alumno.getLegajo()%></a>
		</td>
		<td><%=alumno.getNombre()%></td>
	</tr>
<% } %>
</table>
<a href="crearAlumno.jsp">Crear un nuevo alumno</a></br>
<a href="index.jsp">Volver al menu</a>

</body>
</html>