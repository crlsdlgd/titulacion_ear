package ec.edu.uce.titulacion.controladores;

import ec.edu.uce.titulacion.entidades.Plan;
import ec.edu.uce.titulacion.dao.PlanDao;
import ec.edu.uce.titulacion.dao.UsuarioDao;
import ec.edu.uce.titulacion.entidades.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;

@Named(value = "controladorPlan")
//@SessionScoped
@RequestScoped

public class controladorPlan implements Serializable {

    private static final long serialversionuid = 1L;
    @EJB
    private UsuarioDao usuarioDao;

    @EJB
    private PlanDao planDao;
    
    private List<Plan> listaPlan;
    private List<Usuario> listUsuario;
    private List<Usuario> listIntegrantes;
    private Usuario usuario;
    private Plan plan;
    private String usuariosPlan;
    private String txt1;

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }
    
    public void cargarPlan() throws Exception{
        listaPlan=planDao.listarPlan();
    }

    public void listarUsuarioByPlan(Plan plan) throws Exception{
        listUsuario = usuarioDao.listarUserByPlan(plan);
    }

    public List<String> autoCompletarEstudiante(String query) throws Exception{
        List<String> lista = usuarioDao.autoCompletarEstudiante(query);
        return lista;
    }
    
    public controladorPlan() {
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListIntegrantes() {
        return listIntegrantes;
    }

    public void setListIntegrantes(List<Usuario> listIntegrantes) {
        this.listIntegrantes = listIntegrantes;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

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
    
}
