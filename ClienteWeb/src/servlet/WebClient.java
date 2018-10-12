package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delegado.BusinessDelegate;
import dto.AlumnoDTO;
import excepciones.ComunicacionException;

/**
 * Servlet implementation class WebClient
 */
@WebServlet("/WebClient")
public class WebClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WebClient() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String jspPage = "/index.jsp";

		if ((action == null) || (action.length() < 1))
		{
			action = "default";
		}

		if ("default".equals(action))
		{
			jspPage = "/index.jsp";
		}
		else if ("listarAlumnos".equals(action))
		{
			ArrayList<AlumnoDTO> alumnos = null;
			try {
				BusinessDelegate bd = new BusinessDelegate();
				alumnos = bd.getAlumnos();
				request.setAttribute("alumnos", alumnos);
			} catch (ComunicacionException e) {
				e.printStackTrace();
			}
			jspPage = "/listarAlumnos.jsp";
		}
		else if ("crearAlumno".equals(action))
		{
			try {
				BusinessDelegate bd = new BusinessDelegate();
				String nombre = (String)request.getParameter("nombre");
				bd.crearAlumno(nombre);
				request.setAttribute("alumnoCreado", nombre);
				
			} catch (ComunicacionException e) {
				request.setAttribute("error", 1);
			}
			jspPage = "/crearAlumno.jsp";
		}
		else if ("verAlumno".equals(action))
		{
			try {
				BusinessDelegate bd = new BusinessDelegate();
				String legajo = (String)request.getParameter("nombre");
				//request.setAttribute("alumnoCreado", nombre);
				
			} catch (ComunicacionException e) {
				request.setAttribute("error", 1);
			}
			jspPage = "/crearAlumno.jsp";
		}
		if (jspPage != null) {
			RequestDispatcher rd = request.getRequestDispatcher(jspPage);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
