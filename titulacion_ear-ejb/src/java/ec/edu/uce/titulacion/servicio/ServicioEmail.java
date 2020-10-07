package ec.edu.uce.titulacion.servicio;

import java.util.Date;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

@Singleton
@LocalBean
public class ServicioEmail {

    //@Schedule(second = "0", minute = "0", hour = "8")
    //@Schedule(second = "*", minute = "*/1", hour = "*")
    public void BarrerPlan(){
        System.out.println(new Date());
    }
}
