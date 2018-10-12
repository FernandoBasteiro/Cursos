package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controlador.HibernateUtil;
import entities.AlumnoEntity;
import entities.CursoEntity;
import entities.DireccionEntity;
import entities.MateriaEntity;
import entities.ProfesorEntity;
import excepciones.DatabaseException;
import negocio.Alumno;
import negocio.Curso;

public class CursoDAO {
	private static CursoDAO instancia;
	
	private CursoDAO() { }
	
	public static CursoDAO getInstancia() {
		if (instancia == null) {
			instancia = new CursoDAO();
		}
		return instancia;
	}
	
	public CursoEntity getCursoByNumero(Integer numero) throws DatabaseException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		CursoEntity ce = (CursoEntity) session.createQuery("from CursoEntity where id = ?")
					.setParameter(0, numero)
					.uniqueResult();
		if(ce != null)
			return ce;
		else 
			throw new DatabaseException("El curso solicitado no existe");
	}
	
	public Curso toNegocio(CursoEntity ce) {
		Curso c = new Curso(ce.getNumero(), ce.getDia(), ce.getTurno(), ce.getMaximo()); 
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		for (AlumnoEntity ae : ce.getAlumnos()) {
			alumnos.add(AlumnoDAO.getInstancia().toNegocioCurso(ae));
		}
		c.setAlumnos(alumnos);
		c.setMateria(MateriaDAO.getInstancia().toNegocio(ce.getMateria()));
		c.setProfesor(ProfesorDAO.getInstancia().toNegocioCurso(ce.getProfesor()));
		return c;
	}
	
	public Curso toNegocioAlum(CursoEntity ce) {
		Curso c = new Curso(ce.getNumero(), ce.getDia(), ce.getTurno(), ce.getMaximo());
		c.setMateria(MateriaDAO.getInstancia().toNegocio(ce.getMateria()));
		c.setProfesor(ProfesorDAO.getInstancia().toNegocioCurso(ce.getProfesor()));
		return c;
	}
	
	public Integer insert(Curso curso){
		CursoEntity ce = new CursoEntity(curso.getNumero(), curso.getDia(),curso.getTurno(), curso.getMaximo());
		MateriaEntity materia = new MateriaEntity(curso.getMateria().getCodigo(), curso.getMateria().getDescripcion(), curso.getMateria().isHabilitada());
		ce.setMateria(materia);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Integer numero = (Integer)session.save(ce);
		session.getTransaction().commit();
		session.close();
		return numero;
	}
	
	public void update(Curso curso){
		CursoEntity ce = new CursoEntity(curso.getNumero(), curso.getDia(),curso.getTurno(), curso.getMaximo());
		ArrayList<AlumnoEntity> alumnos = new ArrayList<AlumnoEntity>();
		try {
			for (Alumno a : curso.getAlumnos()) {
				AlumnoEntity alumno = AlumnoDAO.getInstancia().getAlumnoByLegajo(a.getLegajo());
				if (alumno != null) {
					alumno = new AlumnoEntity(a.getLegajo(), a.getNombre());
				}
				alumnos.add(alumno);
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		ce.setAlumnos(alumnos);
		if (curso.getProfesor() != null) {
			DireccionEntity direccion = new DireccionEntity(curso.getProfesor().getDireccion().getCalle(), curso.getProfesor().getDireccion().getNumero(), curso.getProfesor().getDireccion().getLocalidad(), curso.getProfesor().getDireccion().getCodigoPostal());
			ProfesorEntity prof = new ProfesorEntity(curso.getProfesor().getLegajo(), curso.getProfesor().getNombre(), direccion);
			ce.setProfesor(prof);
		}
		MateriaEntity materia = new MateriaEntity(curso.getMateria().getCodigo(), curso.getMateria().getDescripcion(), curso.getMateria().isHabilitada());
		ce.setMateria(materia);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ce);
		session.getTransaction().commit();
		session.close();
	}

	public Curso toNegocioProf(CursoEntity ce) {
		Curso c = new Curso(ce.getNumero(), ce.getDia(), ce.getTurno(), ce.getMaximo()); 
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		for (AlumnoEntity ae : ce.getAlumnos()) {
			alumnos.add(AlumnoDAO.getInstancia().toNegocioCurso(ae));
		}
		c.setAlumnos(alumnos);
		c.setMateria(MateriaDAO.getInstancia().toNegocio(ce.getMateria()));
		return c;
	}
	
	public ArrayList<Curso> getCursos() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<CursoEntity> ces = (ArrayList<CursoEntity>) session.createQuery("from CursoEntity").list();
		ArrayList<Curso> cs = new ArrayList<Curso>();
		for (CursoEntity ce : ces) {
			Curso c = this.toNegocio(ce);
			cs.add(c);
		}
		return cs;
	}
}
