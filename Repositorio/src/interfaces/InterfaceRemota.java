package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import dto.AlumnoDTO;
import dto.CursoDTO;
import dto.MateriaDTO;
import dto.ProfesorDTO;
import excepciones.DatabaseException;

public interface InterfaceRemota extends Remote {

	public void inscribirAlumno(int numero, int legajo) throws RemoteException, DatabaseException;

	public void crearAlumno(String nombre) throws RemoteException;

	public void crearMateria(String codigo, String descripcion, boolean habilitada) throws RemoteException;
	
	public void crearProfesor(String nombre, String calle, int numero, String codigoPostal, String localidad) throws RemoteException;

	public ArrayList<AlumnoDTO> getAlumnos() throws RemoteException;

	public ArrayList<ProfesorDTO> getProfesores() throws RemoteException;

	public ArrayList<MateriaDTO> getMaterias() throws RemoteException;

	public void crearCurso(String codigo, int legajo, String dia, String turno, int maximo) throws RemoteException, DatabaseException;

	public ArrayList<CursoDTO> getCursos() throws RemoteException;

	void agregarMateriaProfesor(Integer legajo, String codigo) throws RemoteException, DatabaseException;

	void eliminarMateriaProfesor(Integer legajo, String codigo) throws RemoteException, DatabaseException;

	public void reasignarDocente(Integer numero, Integer legajo) throws RemoteException, DatabaseException;	
}
