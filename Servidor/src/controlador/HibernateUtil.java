package controlador;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entities.AlumnoEntity;
import entities.CursoEntity;
import entities.DireccionEntity;
import entities.MateriaEntity;
import entities.ProfesorEntity;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
        	 config.addAnnotatedClass(CursoEntity.class);
             config.addAnnotatedClass(AlumnoEntity.class);
             config.addAnnotatedClass(DireccionEntity.class);
             config.addAnnotatedClass(MateriaEntity.class);
             config.addAnnotatedClass(ProfesorEntity.class);
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}