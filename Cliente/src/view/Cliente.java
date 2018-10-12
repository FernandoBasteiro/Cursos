package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import delegado.BusinessDelegate;
import dto.AlumnoDTO;
import dto.CursoDTO;
import dto.MateriaDTO;
import dto.ProfesorDTO;
import excepciones.ComunicacionException;

public class Cliente {
	private BusinessDelegate bd;
	public static void main(String[] args) {

		try {
			BusinessDelegate bd = new BusinessDelegate();
			Cliente c = new Cliente(bd);
			//c.inicializar();
			c.mostrarMenu();
			
		} catch (ComunicacionException e) {
			System.out.println(e);
		}

		
	}
	
	private Cliente (BusinessDelegate bd) {
		this.bd = bd;
	}
	
	public void clearScreen(){
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException ei) {
			ei.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void inicializar() {
		try {
			bd.crearAlumno("Armando Artigas");
			bd.crearAlumno("Beatriz Bonifacio");
			bd.crearAlumno("Carlos Comaleras");
			bd.crearAlumno("Dennise Donato");
			bd.crearAlumno("Esteban Etchenique");
			bd.crearAlumno("Fatima Fondacaro");
			bd.crearAlumno("Gabriel Gorriti");
			bd.crearAlumno("Helena Hernandez");
			bd.crearAlumno("Ignacio Isaguirre");
			bd.crearMateria("BD2", "Base de Datos II", true);
			bd.crearMateria("P2", "Programacion II", true);
			bd.crearMateria("SIP1", "Seminario de Integracion Profesional I", false);
			bd.crearMateria("TyR2", "Teleinformatica y Redes II", true);
			bd.crearProfesor("Anibal Freijo", "Falsa", 123, "1400", "CABA");
			bd.crearProfesor("Andres Mutti", "Falsa", 123, "1400", "CABA");
			bd.crearProfesor("Claudio Godio", "Falsa", 123, "1400", "CABA");
			bd.crearProfesor("Julio Cancela", "Falsa", 123, "1400", "CABA");
			bd.crearCurso("BD2", 1, "Lunes", "Noche", 13);
			bd.agregarMateriaProfesor(1, "TyR2");
			bd.agregarMateriaProfesor(2, "TyR2");
			bd.agregarMateriaProfesor(3, "BD2");
			bd.agregarMateriaProfesor(4, "BD2");
			bd.agregarMateriaProfesor(3, "P2");
			bd.inscribirAlumno(1, 3);
			bd.reasignarDocente(1,1);
		} catch (ComunicacionException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	public void mostrarMenu(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Opciones:");
		System.out.println("1 - Ver alumno");
		System.out.println("2 - Listar profesores");
		System.out.println("3 - Listar materias");
		System.out.println("4 - Listar cursos");
		System.out.println("5 - Crear curso");
		System.out.print("Opcion: ");
		try {
			String opcion = reader.readLine();
			switch(opcion) {
			case "1": {
				this.verAlumno();
				break;
			}
			case "2": {
				this.listarProfesores();
				break;
			}
			case "3": {
				this.listarMaterias();
				break;
			}
			case "4": {
				this.listarCursos();
				break;
			}
			case "5": {
				this.crearCurso();
				break;
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void verAlumno() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<AlumnoDTO> av = bd.getAlumnos();
		this.clearScreen();
		for (int i=0; i<av.size();i++) {
			System.out.println(String.valueOf(i + 1) + " - " + av.get(i).getNombre() + " (" + av.get(i).getLegajo() + ")");
		}
		System.out.println("0 - Salir");
		System.out.print("Seleccione alumno: ");
		int opcion1 = Integer.valueOf(reader.readLine());
		if (opcion1 < av.size() && opcion1 > 0) {
			this.clearScreen();
			System.out.println("Nombre: " + av.get(opcion1 - 1).getNombre());
			System.out.println("Legajo: " + String.valueOf(av.get(opcion1 - 1).getLegajo()));
			System.out.println("Opciones:");
			System.out.println("1 - Ver cursos");
			System.out.println("2 - Inscribir en curso");
			System.out.println("0 - Volver al menu principal");
			System.out.print("Seleccione una opcion: ");
			int opcion2 = Integer.valueOf(reader.readLine());
			if (opcion2 == 1) {
				this.clearScreen();
				System.out.println("Cursos en los que esta inscripto el alumno " + av.get(opcion1 - 1).getNombre());
				for (CursoDTO cv : av.get(opcion1 - 1).getCursos()) {
					System.out.println(cv.getMateria().getDescripcion());
					System.out.println("    Dia: " + cv.getDia() + "  -  Turno: " + cv.getTurno());
				}
			}
			else if (opcion2 == 2) {
				this.clearScreen();
				System.out.println("Seleccione la materia: ");
				//TODO Seguir aca
			}

		}
		//this.pause();
		this.clearScreen();
		this.mostrarMenu();
	}
	private void listarProfesores() throws Exception {
		ArrayList<ProfesorDTO> pv = bd.getProfesores();
		this.clearScreen();
		for (ProfesorDTO p : pv) {
			System.out.println(p.getNombre() + " (" + p.getLegajo() + ") - " + p.getCalle() + " " + p.getNumero() + " " + p.getLocalidad() + " (" + p.getCodigoPostal() + ")" );
		}
		System.out.println();
		//this.pause();
		this.clearScreen();
		this.mostrarMenu();
	}
	
	private void listarMaterias() throws Exception {
		ArrayList<MateriaDTO> mv = bd.getMaterias();
		this.clearScreen();
		for (MateriaDTO m : mv) {
			System.out.println(m.getDescripcion());
		}
		System.out.println();
		//this.pause();
		this.clearScreen();
		this.mostrarMenu();
	}
	
	private void listarCursos() throws Exception {
		ArrayList<CursoDTO> cv = bd.getCursos();
		this.clearScreen();
		for (CursoDTO c : cv) {
			System.out.println(c.getMateria().getDescripcion() + " - " + c.getNumero() + " - " + c.getDia() + " " + c.getTurno());
		}
		System.out.println();
		//this.pause();
		this.clearScreen();
		this.mostrarMenu();
	}
	
	private void crearCurso() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<MateriaDTO> mv = bd.getMaterias();
		ArrayList<ProfesorDTO> pv = bd.getProfesores();
		this.clearScreen();
		System.out.println("Seleccione un profesor de la lista: ");
		for (int i = 0; i<pv.size(); i++) {
			System.out.println(String.valueOf(i + 1) + " - " + pv.get(i).getNombre() + " (" + pv.get(i).getLegajo() + ")");
		}
		System.out.print("Opcion: ");
		int opcion1 = Integer.valueOf(reader.readLine());
		
		this.clearScreen();
		System.out.println("Seleccione una materia de la lista: ");
		for (int i = 0; i<mv.size(); i++) {
			System.out.println(String.valueOf(i + 1) + " - " + mv.get(i).getDescripcion() + " (" + mv.get(i).getCodigo() + ")");
		}
		System.out.print("Opcion: ");
		int opcion2 = Integer.valueOf(reader.readLine());
		
		this.clearScreen();
		System.out.print("Día en el que se va a dictar el curso: ");
		String dia = reader.readLine();
		System.out.print("Turno en el que se va a dictar el curso: ");
		String turno = reader.readLine();
		System.out.print("Cantidad maxima de alumnos para el curso: ");
		int maximo = Integer.valueOf(reader.readLine());
		
		bd.crearCurso(mv.get(opcion2 - 1).getCodigo(), pv.get(opcion1).getLegajo(), dia, turno, maximo);
		System.out.println();
		//this.pause();
		this.clearScreen();
		this.mostrarMenu();
	}
}
