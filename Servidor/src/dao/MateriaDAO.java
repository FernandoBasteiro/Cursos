package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import controlador.HibernateUtil;
import entities.MateriaEntity;
import negocio.Materia;

public class MateriaDAO {
	private static MateriaDAO instancia;
	
	private MateriaDAO() {	}
	
	public static MateriaDAO getInstancia() {
		if (instancia == null) {
			instancia = new MateriaDAO();
		}
		return instancia;
	}
	
	public MateriaEntity getMateriaByCodigo(String codigo) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		MateriaEntity me = (MateriaEntity) session.createQuery("from MateriaEntity where codigo = ?")
				.setParameter(0, codigo)
				.uniqueResult();
		return me;
	}
	
	public void grabar(Materia materia) {
		MateriaEntity me = new MateriaEntity(materia.getCodigo(), materia.getDescripcion(), materia.isHabilitada());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		session.saveOrUpdate(me);
		t.commit();
		session.close();
	}
	
	public Materia toNegocio(MateriaEntity me) {
		Materia m = new Materia(me.getCodigo(), me.getDescripcion(), me.isHabilitada());
		return m;
	}
	
	public ArrayList<Materia> getMaterias() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<MateriaEntity> mes = (ArrayList<MateriaEntity>) session.createQuery("from MateriaEntity").list();
		ArrayList<Materia> ms = new ArrayList<Materia>();
		for (MateriaEntity me : mes) {
			Materia m = this.toNegocio(me);
			ms.add(m);
		}
		return ms;
	}
}
