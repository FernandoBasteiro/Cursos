package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delegado.BusinessDelegate;
import dto.AlumnoDTO;
import excepciones.ComunicacionException;

public class Controlador extends HttpServlet {
	
	private static final long serialVersionUID = 1087702007634924546L;
	
	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
       {	

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
        	   ArrayList<AlumnoDTO> alumnos;
			try {
				BusinessDelegate bd = new BusinessDelegate();
				alumnos = bd.getAlumnos();
				request.setAttribute("alumnos", alumnos);
			} catch (ComunicacionException e) {
				e.printStackTrace();
			}
               jspPage = "/listarAlumnos.jsp";
           }
           
           
           dispatch(jspPage, request, response);
       }
   
       protected void dispatch(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
       {
           if (jsp != null)
           {
           	/*Envía el control al JSP que pasamos como parámetro, y con los 
           	 * request / response cargados con los parámetros */
               RequestDispatcher rd = request.getRequestDispatcher(jsp);
               rd.forward(request, response);
           }
       }

       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
       {
           doPost(request, response);
          
       }

}
