import Entidades.Alumno;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import java.util.*;

import com.db4o.query.Query;

public class Db4oEjemplo {

    public static void main (String [] args) {
        System.out.println("Hola");
        ObjectContainer db = Db4oEmbedded.openFile("personas.db4o");

        //Entidades.Alumno alumno = new Entidades.Alumno("Humberto", 18, "DAM");
        //Entidades.Alumno alumno2 = new Entidades.Alumno("Jairo", 21, "DAM");
        //Entidades.Alumno alumno3 = new Entidades.Alumno("Garonda", 22, "DAM");

        //db.store(alumno);
        //db.store(alumno2);
        //db.store(alumno3);

        //ObjectSet<Entidades.Alumno> result = db.queryByExample(Entidades.Alumno.class);
        Query query = db.query();
        query.constrain(Alumno.class);
        query.descend("nombre").constrain("Jairo");
        //Entidades.Alumno alumnoConstrain = new Entidades.Alumno("Humberto", 0 , null);
       //query.constrain(alumnoConstrain);
        List<Alumno> alumnos = query.execute();

        for (Alumno a : alumnos) {
            db.delete(a);
        }

        //Entidades.Alumno alumno1 = alumnos.get(0);
        //alumno1.setEdad(22);
        //db.store(alumno1);

        Query query2 = db.query();
        List<Alumno> alumnos2 = query2.execute();

        for (Alumno a : alumnos2) {
            System.out.println(a.toString());
        }

        db.close();
    }
}
