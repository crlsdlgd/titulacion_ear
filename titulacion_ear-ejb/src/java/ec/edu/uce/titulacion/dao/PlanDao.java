package ec.edu.uce.titulacion.dao;

import ec.edu.uce.titulacion.entidades.Plan;
import ec.edu.uce.titulacion.entidades.Usuario;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PlanDao {
    public void registrar(Plan plan) throws Exception;
    public List<Plan> listarPlan() throws Exception;
    public List<Plan> listarPlanByUser(Usuario usuario) throws Exception;

    public void guardarPlan(String tema, Date fecha, List<String> listIntegrantes, Usuario user) throws Exception;
    
}
