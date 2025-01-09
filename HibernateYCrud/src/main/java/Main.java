import Entidad.Profesor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

    public static void main (String [] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Profesor profesor = new Profesor("08", "Adan", "Aviles");
        session.persist(profesor);
        Profesor pl = (Profesor) session.get(Profesor.class, "08");
        System.out.println(profesor.getCiudad());
        profesor.setCiudad("Salinas");
        session.merge(pl);

        pl = session.find(Profesor.class, "08");
        System.out.println(pl);
        transaction.commit();
        session.close();
        sessionFactory.close();


    }
}
