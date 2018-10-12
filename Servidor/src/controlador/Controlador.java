package controlador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.AlumnoDAO;
import dao.CursoDAO;
import dao.MateriaDAO;
import dao.ProfesorDAO;
import dto.AlumnoDTO;
import dto.CursoDTO;
import dto.MateriaDTO;
import dto.ProfesorDTO;
import excepciones.DatabaseException;
import negocio.Alumno;
import negocio.Curso;
import negocio.Materia;
import negocio.Profesor;

public class Controlador {
	private ArrayList<Materia> materias;
	private ArrayList<Profesor> profesores;
	private ArrayList<Alumno> alumnos;
	private ArrayList<Curso> cursos;
	private static Controlador instancia;
	private DateTimeFormatter dtf;  
	
	
	private Controlador (){
		materias = new ArrayList<Materia>();
		profesores = new ArrayList<Profesor>();
		alumnos = new ArrayList<Alumno>();
		cursos = new ArrayList<Curso>();
		dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
	}
	
	public static Controlador getControlador() {
		if (instancia == null) {
			instancia = new Controlador();
		}
		return instancia;
	}
	
	private Alumno buscarAlumno(int legajo) throws DatabaseException {
		System.out.println(dtf.format(LocalDateTime.now()) + " - Buscando alumno con legajo: " + String.valueOf(legajo));
		/*
		for (Alumno a : alumnos) {
			if (a.getLegajo() == legajo) {
				System.out.println(dtf.format(LocalDateTime.now()) + " - Alumno encontrado");
				return a;
			}
		}
		*/
		return AlumnoDAO.getInstancia().toNegocio(AlumnoDAO.getInstancia().getAlumnoByLegajo(legajo));
	}
	
	private Curso buscarCurso(int numero) throws DatabaseException {
		System.out.println(dtf.format(LocalDateTime.now()) + " - Buscando curso numero: " + String.valueOf(numero));
		/*
		for (Curso c : cursos) {
			if (c.getNumero() == numero) {
				System.out.println(dtf.format(LocalDateTime.now()) + " - Curso encontrado");
				return c;
			}
		}
		*/
		
		return CursoDAO.getInstancia().toNegocio(CursoDAO.getInstancia().getCursoByNumero(numero));
	}
	
	private Materia buscarMateria(String codigo) throws DatabaseException {
		System.out.println(dtf.format(LocalDateTime.now()) + " - Buscando materia con codigo: " + codigo);
		/*
		for (Materia m : materias) {
			if (m.getCodigo().equals(codigo)) {
				System.out.println(dtf.format(LocalDateTime.now()) + " - Materia encontrada");
				return m;
			}
		}
		*/
		return MateriaDAO.getInstancia().toNegocio(MateriaDAO.getInstancia().getMateriaByCodigo(codigo));
	}
	
	private Profesor buscarProfesor(int legajo) throws DatabaseException{
		System.out.println(dtf.format(LocalDateTime.now()) + " - Buscando profesor con legajo: " + String.valueOf(legajo));
		/*
		for (Profesor p : profesores) {
			if (p.getLegajo() == legajo) {
				System.out.println(dtf.format(LocalDateTime.now()) + " - Profesor encontrado");
				return p;
			}
		}*/
		return ProfesorDAO.getInstancia().toNegocio(ProfesorDAO.getInstancia().getProfesorByLegajo(legajo));
	}
	
	public void crearCurso(String codigo, int legajo, String dia, String turno, int maximo) throws DatabaseException {
		System.out.println(dtf.format(LocalDateTime.now()) + " - Creando nuevo curso");

		Curso c = new Curso(this.buscarMateria(codigo), this.buscarProfesor(legajo), dia, turno, maximo);
		c.setNumero(c.insert());
		cursos.add(c);
	}
	
	public void inscribirAlumno(int numero, int legajo)  throws DatabaseException  {
		System.out.println(dtf.format(LocalDateTime.now()) + " - Inscribiendo alumno en curso");
		Alumno a = this.buscarAlumno(legajo);
		Curso c = this.buscarCurso(numero);
		if (a.sePuedeInscribir(c.getMateria())) {
			c.anotarAlumno(this.buscarAlumno(legajo));
			c.update();
		}
	}
	
	public void reasignarDocente(int numero, int legajo)  throws DatabaseException {
		System.out.println(dtf.format(LocalDateTime.now()) + " - Reasignando docente");
		this.buscarCurso(numero).reasignarProfesor(this.buscarProfesor(legajo));
	}
	
	public ArrayList<AlumnoDTO> getAlumnos() {
		System.out.println(dtf.format(LocalDateTime.now()) + " - Generando listado de alumnos");
		ArrayList<AlumnoDTO> as = new ArrayList<AlumnoDTO>();
		for (Alumno a : AlumnoDAO.getInstancia().getAlumnos()) {
			as.add(a.toView());
		}
		return as;
	}

	public ArrayList<CursoDTO> getCursos() {
		System.out.println(dtf.format(LocalDateTime.now()) + " - Generando listado de cursos");
		ArrayList<CursoDTO> cs = new ArrayList<CursoDTO>();
		for (Curso c : CursoDAO.getInstancia().getCursos()) {
			cs.add(c.toView());
		}
		return cs;
	}
	
	public ArrayList<MateriaDTO> getMaterias() {
		System.out.println(dtf.format(LocalDateTime.now()) + " - Generando listado de materias");
		ArrayList<MateriaDTO> ms = new ArrayList<MateriaDTO>();
		for (Materia m : MateriaDAO.getInstancia().getMaterias()) {
			if (m.isHabilitada()) {
				ms.add(m.toView());				
			}
		}
		return ms;
	}
	
	public ArrayList<ProfesorDTO> getProfesores() {
		System.out.println(dtf.format(LocalDateTime.now()) + " - Generando listado de profesores");
		ArrayList<ProfesorDTO> ps = new ArrayList<ProfesorDTO>();
		for (Profesor p : ProfesorDAO.getInstancia().getProfesores()) {
			ps.add(p.toView());
		}
		System.out.println(dtf.format(LocalDateTime.now()) + " - Listado generado con " + ps.size() + " profesores");
		return ps;
	}  
	
	public void crearAlumno(String nombre) {
		Alumno a = new Alumno(nombre);
		a.setLegajo(a.insert());
		alumnos.add(a);
	}
	
	public void crearMateria(String codigo, String descripcion, boolean habilitada) {
		Materia m = new Materia(codigo, descripcion, habilitada);
		m.grabar();
		materias.add(m);
	}
	
	public void crearProfesor(String nombre, String calle, int numero, String codigoPostal, String localidad) {
		Profesor p = new Profesor(nombre, calle, numero, codigoPostal, localidad);
		p.setLegajo(p.insert());
		profesores.add(p);
	}
	
	public void agregarMateriaProfesor(int legajo, String codigo)  throws DatabaseException {
		Profesor p = this.buscarProfesor(legajo);
		Materia m = this.buscarMateria(codigo);
		p.agregarMateria(m);
		p.grabar();
	}
	
	public void eliminarMateriaProfesor(int legajo, String codigo)  throws DatabaseException {
		Profesor p = this.buscarProfesor(legajo);
		Materia m = this.buscarMateria(codigo);
		p.eliminarMateria(m);
		p.grabar();
	}
	
	public AlumnoDTO buscarAlumnoDTO(int legajo) throws DatabaseException {
		return AlumnoDAO.getInstancia().toNegocio(AlumnoDAO.getInstancia().getAlumnoByLegajo(legajo)).toView();
	}
}