package delegado;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import dto.AlumnoDTO;
import dto.CursoDTO;
import dto.MateriaDTO;
import dto.ProfesorDTO;
import excepciones.ComunicacionException;
import excepciones.DatabaseException;
import interfaces.InterfaceRemota;

public class BusinessDelegate {

	private InterfaceRemota ir;
	
	public BusinessDelegate() throws ComunicacionException{
		try {
			ir = (InterfaceRemota) Naming.lookup("//127.0.0.1/cursos");
		} catch (MalformedURLException e) {
			throw new ComunicacionException("La direccion especificada no es correcta");
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		} catch (NotBoundException e) {
			throw new ComunicacionException("El servidor no esta disponible");		
		}
	}
	
	public void inscribirAlumno(int numero, int legajo) throws ComunicacionException{
		try {
			ir.inscribirAlumno(numero, legajo);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		} catch (DatabaseException e) {
			throw new ComunicacionException("Error de base de datos" + e.toString());
		}
	}
	
	public void crearAlumno(String nombre) throws ComunicacionException{
		try {
			ir.crearAlumno(nombre);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	public void crearMateria(String codigo, String descripcion, boolean habilitada) throws ComunicacionException{
		try {
			ir.crearMateria(codigo, descripcion, habilitada);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	public void crearProfesor(String nombre, String calle, int numero, String codigoPostal,
			String localidad) throws ComunicacionException{
		try {
			ir.crearProfesor(nombre, calle, numero, codigoPostal, localidad);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}

	public ArrayList<AlumnoDTO> getAlumnos() throws ComunicacionException{
		try {
			return ir.getAlumnos();
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}

	public ArrayList<ProfesorDTO> getProfesores() throws ComunicacionException{
		try {
			return ir.getProfesores();
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}

	public ArrayList<MateriaDTO> getMaterias() throws ComunicacionException{
		try {
			return ir.getMaterias();
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}

	public void crearCurso(String codigo, int legajo, String dia, String turno, int maximo) throws ComunicacionException {
		try {
			ir.crearCurso(codigo, legajo, dia, turno, maximo);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		} catch (DatabaseException e) {
			throw new ComunicacionException("Error de base de datos" + e.toString());
		}
	}

	public ArrayList<CursoDTO> getCursos()  throws ComunicacionException{
		try {
			return ir.getCursos();
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public void agregarMateriaProfesor(Integer legajo, String codigo)  throws ComunicacionException{
		try {
			ir.agregarMateriaProfesor(legajo, codigo);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		} catch (DatabaseException e) {
			throw new ComunicacionException("Error de base de datos" + e.toString());
		}
	}
	
	public void eliminarMateriaProfesor(Integer legajo, String codigo)  throws ComunicacionException{
		try {
			ir.eliminarMateriaProfesor(legajo, codigo);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		} catch (DatabaseException e) {
			throw new ComunicacionException("Error de base de datos" + e.toString());
		}
	}

	public void reasignarDocente(Integer numero, Integer legajo)  throws ComunicacionException{
		try {
			ir.reasignarDocente(numero, legajo);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		} catch (DatabaseException e) {
			throw new ComunicacionException("Error de base de datos" + e.toString());
		}
	}
	
}
