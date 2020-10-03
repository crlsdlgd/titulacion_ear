package ec.edu.uce.titulacion.controladores;

import ec.edu.uce.titulacion.entidades.Plan;
import ec.edu.uce.titulacion.dao.PlanDao;
import ec.edu.uce.titulacion.dao.UsuarioDao;
import ec.edu.uce.titulacion.entidades.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@Named(value = "controladorPlan")
@SessionScoped
public class controladorPlan implements Serializable {

    @EJB
    private UsuarioDao usuarioDao;

    @EJB
    private PlanDao planDao;
    
    private List<Plan> listaPlan;
    private List<Usuario> listUsuario;

    public PlanDao getPlanDao() {
        return planDao;
    }

    public void setPlanDao(PlanDao planDao) {
        this.planDao = planDao;
    }

    public List<Usuario> getListUsuario() {
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }
    
    private String usuariosPlan;

    public String getUsuariosPlan() {
        return usuariosPlan;
    }

    public void setUsuariosPlan(String usuariosPlan) {
        this.usuariosPlan = usuariosPlan;
    }
    

    public List<Plan> getListaPlan() {
        return listaPlan;
    }

    public void setListaPlan(List<Plan> listaPlan) {
        this.listaPlan = listaPlan;
    }

    public controladorPlan() {
    }

    public void cargarPlan() throws Exception{
        listaPlan=planDao.listarPlan();
    }

    public void listarUsuarioByPlan(Plan plan) throws Exception{
        listUsuario = usuarioDao.listarUserByPlan(plan);
    }
    
}
