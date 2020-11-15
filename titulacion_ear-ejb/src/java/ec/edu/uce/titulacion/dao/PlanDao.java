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
    public List<Plan> listarPlanesAprobados() throws Exception;
    public List<Plan> listarPlanByUser(Usuario usuario) throws Exception;

    public void guardarPlan(String tema, Date fecha, List<String> listIntegrantes, Usuario user) throws Exception;

    public void guardarPropuestaPlan(String txtTema, String txtDetalle, Usuario user) throws Exception;

    public List<Plan> listarPlanesNoAprobadosNiPostulados(Usuario user) throws Exception;

    public void postular(Plan plan, Usuario user)throws Exception;

    public List<Plan> cargarMisPostulaciones(Usuario user)throws Exception;
    public List<Plan> cargarMisProyectos(Usuario user)throws Exception;

    public void listoRevision(Plan plan)throws Exception;
}
