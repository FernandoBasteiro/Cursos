package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controlador.HibernateUtil;
import entities.AlumnoEntity;
import entities.CursoEntity;
import excepciones.DatabaseException;
import negocio.Alumno;
import negocio.Curso;

public class AlumnoDAO {
	private static AlumnoDAO instancia;
	
	private AlumnoDAO() { }
	
	public static AlumnoDAO getInstancia() {
		if (instancia == null) {
			instancia = new AlumnoDAO();
		}
		return instancia;
	}
	
	public AlumnoEntity getAlumnoByLegajo(Integer legajo) throws DatabaseException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		AlumnoEntity ae = (AlumnoEntity) session.createQuery("from AlumnoEntity where id = ?")
					.setParameter(0, legajo)
					.uniqueResult();
		if(ae != null)
			return ae;
		else 
			throw new DatabaseException("El alumno solicitado no existe");
	}
	
	public Alumno toNegocio(AlumnoEntity alumno) {
		Alumno a = new Alumno(alumno.getLegajo(), alumno.getNombre()); 
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		for (CursoEntity ce : alumno.getCursos()) {
			cursos.add(CursoDAO.getInstancia().toNegocioAlum(ce));
		}
		a.setCursos(cursos);
		return a;
	}
	
	public Integer insert(Alumno alumno){
		AlumnoEntity ae = new AlumnoEntity(alumno.getLegajo(), alumno.getNombre());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int legajo = (int)session.save(ae);
		session.getTransaction().commit();
		session.close();
		return legajo;
	}
	
	public void update(Alumno alumno){
		AlumnoEntity ae = new AlumnoEntity(alumno.getLegajo(), alumno.getNombre());
		ArrayList<CursoEntity> cursos = new ArrayList<CursoEntity>();
		try {
			for (Curso c : alumno.getCursos()) {
				CursoEntity curso = CursoDAO.getInstancia().getCursoByNumero(c.getNumero());
				if (curso != null) {
					curso = new CursoEntity(c.getNumero(), c.getDia(),c.getTurno(),c.getMaximo());
				}
				cursos.add(curso);
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		ae.setCursos(cursos);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ae);
		session.getTransaction().commit();
		session.close();
	}
	public Alumno toNegocioCurso(AlumnoEntity ae) {
		Alumno a = new Alumno(ae.getLegajo(), ae.getNombre()); 
		return a;
	}
	
	public ArrayList<Alumno> getAlumnos() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<AlumnoEntity> aes = (ArrayList<AlumnoEntity>) session.createQuery("from AlumnoEntity").list();
		ArrayList<Alumno> as = new ArrayList<Alumno>();
		for (AlumnoEntity ae : aes) {
			Alumno a = this.toNegocio(ae);
			as.add(a);
		}
		return as;
	}
}
