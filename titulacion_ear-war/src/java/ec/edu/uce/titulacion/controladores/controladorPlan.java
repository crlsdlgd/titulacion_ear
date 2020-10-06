package ec.edu.uce.titulacion.controladores;

import ec.edu.uce.titulacion.entidades.Plan;
import ec.edu.uce.titulacion.dao.PlanDao;
import ec.edu.uce.titulacion.dao.UsuarioDao;
import ec.edu.uce.titulacion.entidades.Usuario;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@Named(value = "controladorPlan")
@RequestScoped

public class controladorPlan implements Serializable {

    private static final long serialversionuid = 1L;
    @EJB
    private UsuarioDao usuarioDao;

    @EJB
    private PlanDao planDao;

    private List<Plan> listaPlan;
    private List<Usuario> listUsuario;
    private List<String> listIntegrantes;
    private Usuario usuario;
    private Plan plan;
    private String usuariosPlan;
    private String txtEstudiante,txtTema;
    private Date txtFecha; 

    public void cargarPlan() throws Exception {
        listaPlan = planDao.listarPlan();
    }

    public void listarUsuarioByPlan(Plan plan) throws Exception {
        listUsuario = usuarioDao.listarUserByPlan(plan);
    }

    public List<String> autoCompletarEstudiante(String query) throws Exception {
        List<String> lista = usuarioDao.autoCompletarEstudiante(query);
        return lista;
    }

    public void anadirEstudiante() {
        listIntegrantes.add(txtEstudiante);
        System.out.println("la lista de integrantes tiene: "+listIntegrantes.size()+", "+listIntegrantes.get(0));
    }

    public void quitarEstudiante(String txt) {
        listIntegrantes.remove(txt);
        System.out.println("la lista de integrantes tiene: "+listIntegrantes.size()+", "+listIntegrantes.get(0));
    }

    public void guardarPlan() throws Exception{
        System.out.println("esto va: "+txtTema+" "+txtFecha.getTime()+" "+listIntegrantes.size());
        //planDao.guardarPlan(txtTema,new java.sql.Date(txtFecha.getTime()),listIntegrantes);
    }
    
    @PostConstruct
    public void init(){
        listIntegrantes = new ArrayList<>();
    }
    
    public controladorPlan() {
    }

    
    public Date getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(Date txtFecha) {
        this.txtFecha = txtFecha;
    }

    public String getTxtTema() {
        return txtTema;
    }

    public void setTxtTema(String txtTema) {
        this.txtTema = txtTema;
    }

    public String getTxtEstudiante() {
        return txtEstudiante;
    }

    public void setTxtEstudiante(String txtEstudiante) {
        this.txtEstudiante = txtEstudiante;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<String> getListIntegrantes() {
        return listIntegrantes;
    }

    public void setListIntegrantes(List<String> listIntegrantes) {
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
