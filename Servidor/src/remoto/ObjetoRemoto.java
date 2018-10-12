package remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import controlador.Controlador;
import dto.AlumnoDTO;
import dto.CursoDTO;
import dto.MateriaDTO;
import dto.ProfesorDTO;
import excepciones.DatabaseException;
import interfaces.InterfaceRemota;

public class ObjetoRemoto extends UnicastRemoteObject implements InterfaceRemota {

	private static final long serialVersionUID = 7432516509801597745L;

	public ObjetoRemoto() throws RemoteException {}

	@Override
	public void inscribirAlumno(int numero, int legajo) throws RemoteException, DatabaseException {
		Controlador.getControlador().inscribirAlumno(numero, legajo);
	}
	
	@Override
	public void crearAlumno(String nombre) throws RemoteException {
		Controlador.getControlador().crearAlumno(nombre);		
	}

	@Override
	public void crearMateria(String codigo, String descripcion, boolean habilitada) throws RemoteException {
		Controlador.getControlador().crearMateria(codigo, descripcion, habilitada);
		
	}

	@Override
	public void crearProfesor(String nombre, String calle, int numero, String codigoPostal,
			String localidad) throws RemoteException {
		Controlador.getControlador().crearProfesor(nombre, calle, numero, codigoPostal, localidad);
	}

	@Override
	public ArrayList<AlumnoDTO> getAlumnos() throws RemoteException {
		return Controlador.getControlador().getAlumnos();
	}

	@Override
	public ArrayList<ProfesorDTO> getProfesores() throws RemoteException {
		return Controlador.getControlador().getProfesores();
	}

	@Override
	public ArrayList<MateriaDTO> getMaterias() throws RemoteException {
		return Controlador.getControlador().getMaterias();
	}

	@Override
	public void crearCurso(String codigo, int legajo, String dia, String turno, int maximo) throws RemoteException, DatabaseException {
		Controlador.getControlador().crearCurso(codigo, legajo, dia, turno, maximo);
	}

	@Override
	public ArrayList<CursoDTO> getCursos() throws RemoteException {
		return Controlador.getControlador().getCursos();
	}
	
	@Override
	public void agregarMateriaProfesor(Integer legajo, String codigo) throws RemoteException, DatabaseException {
		Controlador.getControlador().agregarMateriaProfesor(legajo, codigo);
	}
	
	@Override
	public void eliminarMateriaProfesor(Integer legajo, String codigo) throws RemoteException, DatabaseException {
		Controlador.getControlador().eliminarMateriaProfesor(legajo, codigo);
	}

	@Override
	public void reasignarDocente(Integer numero, Integer legajo) throws RemoteException, DatabaseException {
		Controlador.getControlador().reasignarDocente(numero, legajo);		
	}

}