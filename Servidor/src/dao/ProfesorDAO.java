package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import controlador.HibernateUtil;
import entities.CursoEntity;
import entities.DireccionEntity;
import entities.MateriaEntity;
import entities.ProfesorEntity;
import negocio.Curso;
import negocio.Materia;
import negocio.Profesor;

public class ProfesorDAO {
	private static ProfesorDAO instancia;
	
	private ProfesorDAO() {}
	
	public static ProfesorDAO getInstancia() {
		if (instancia == null) {
			instancia = new ProfesorDAO();
		}
		return instancia;
	}
	
	public int insert(Profesor profesor) {
		DireccionEntity de = new DireccionEntity(profesor.getDireccion().getCalle(), profesor.getDireccion().getNumero(), profesor.getDireccion().getCodigoPostal(), profesor.getDireccion().getLocalidad());
		ProfesorEntity pe = new ProfesorEntity(profesor.getNombre(), de);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		int legajo = (int) session.save(pe);
		t.commit();
		session.close();
		return legajo;
	}
	
	public void update (Profesor profesor) {
		DireccionEntity de = new DireccionEntity(profesor.getDireccion().getCalle(), profesor.getDireccion().getNumero(), profesor.getDireccion().getCodigoPostal(), profesor.getDireccion().getLocalidad());
		ProfesorEntity pe = new ProfesorEntity(profesor.getLegajo(), profesor.getNombre(), de);
		ArrayList<MateriaEntity> mes = new ArrayList<MateriaEntity>();
		for (Materia m : profesor.getMaterias()) {
			MateriaEntity me = new MateriaEntity(m.getCodigo(), m.getDescripcion(), m.isHabilitada());
			mes.add(me);
		}
		pe.setMaterias(mes);
		ArrayList<CursoEntity> ces = new ArrayList<CursoEntity>();
		for (Curso c : profesor.getCursos()) {
			CursoEntity ce = new CursoEntity(c.getNumero(),c.getDia(),c.getTurno(),c.getMaximo());
			ces.add(ce);
		}
		pe.setCursos(ces);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		session.saveOrUpdate(pe);
		t.commit();
		session.close();
	}
	
	public Profesor toNegocio(ProfesorEntity pe) {
		Profesor p = new Profesor(pe.getLegajo(), pe.getNombre(), pe.getDireccion().getCalle(), pe.getDireccion().getNumero(), pe.getDireccion().getCodigoPostal(), pe.getDireccion().getLocalidad());
		ArrayList<Materia> materias = new ArrayList<Materia>();
		for (MateriaEntity m : pe.getMaterias()) {
			materias.add(MateriaDAO.getInstancia().toNegocio(m));
		}
		p.setMaterias(materias);
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		for (CursoEntity c : pe.getCursos()) {
			cursos.add(CursoDAO.getInstancia().toNegocioProf(c));
		}
		p.setCursos(cursos);
		return p;
	}
	
	public ProfesorEntity getProfesorByLegajo(Integer legajo) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ProfesorEntity pe = (ProfesorEntity) session.createQuery("from ProfesorEntity where legajo = ?")
				.setParameter(0, legajo)
				.uniqueResult();
		return pe;
	}
	
	public ArrayList<Profesor> getProfesores() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<ProfesorEntity> pes = (ArrayList<ProfesorEntity>) session.createQuery("from ProfesorEntity").list();
		ArrayList<Profesor> ps = new ArrayList<Profesor>();
		for (ProfesorEntity pe : pes) {
			Profesor p = this.toNegocio(pe);
			ps.add(p);
		}
		return ps;
	}

	public Profesor toNegocioCurso(ProfesorEntity pe) {
		if (pe != null) {
			Profesor p = new Profesor(pe.getLegajo(), pe.getNombre(), pe.getDireccion().getCalle(), pe.getDireccion().getNumero(), pe.getDireccion().getCodigoPostal(), pe.getDireccion().getLocalidad());
			ArrayList<Materia> materias = new ArrayList<Materia>();
			for (MateriaEntity m : pe.getMaterias()) {
				materias.add(MateriaDAO.getInstancia().toNegocio(m));
			}
			p.setMaterias(materias);
			return p;
		}
		else return null;
	}
}
