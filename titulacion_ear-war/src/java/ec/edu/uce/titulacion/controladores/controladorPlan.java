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
import javax.enterprise.context.SessionScoped;

@Named(value = "controladorPlan")
@SessionScoped
public class controladorPlan implements Serializable {

    private static final long serialversionuid = 1L;
    @EJB
    private UsuarioDao usuarioDao;

    @EJB
    private PlanDao planDao;

    private List<Plan> listaPlan;
    private List<Usuario> listUsuario;
    private List<String> listIntegrantes;
    private Plan plan;
    private String txtEstudiante, txtTema,txtDetalle;
    private Date txtFecha;
    
    
    public String getTxtDetalle() {
        return txtDetalle;
    }

    public void setTxtDetalle(String txtDetalle) {
        this.txtDetalle = txtDetalle;
    }
    
    public void cargarPlanesAprobados() throws Exception {
        listaPlan = planDao.listarPlanesAprobados();
    }
    
    public void cargarPlanesNoAprobados() throws Exception {
        listaPlan = planDao.listarPlanesNoAprobados();
    }

    public void listarUsuarioByPlan(Plan plan) throws Exception {
        listUsuario = usuarioDao.listarUserByPlan(plan);
    }

    public List<String> autoCompletarEstudiante(String query) throws Exception {
        List<String> lista = usuarioDao.autoCompletarEstudiante(query);
        return lista;
    }

    public void anadirEstudiante() throws Exception{
        boolean flag = false;
        if (usuarioDao.existeEstudiante(txtEstudiante)) {
            for (int i = 0; i < listIntegrantes.size(); i++) {
                if (txtEstudiante.equals(listIntegrantes.get(i))) {
                    flag = true;
                    i = listIntegrantes.size();
                }
            }
        }else{
            flag=true;
        }
        if (!flag) {
            listIntegrantes.add(txtEstudiante);
        }

    }

    
    public void quitarEstudiante(String txt) {
        listIntegrantes.remove(txt);
    }

    public void guardarPlan() throws Exception {

        planDao.guardarPlan(txtTema, new java.sql.Date(txtFecha.getTime()), listIntegrantes, controladorUsuario.user);
    }

    public void guardarPropuestaPlan() throws Exception{
        planDao.guardarPropuestaPlan(txtTema, txtDetalle, controladorUsuario.user);
    }
    
    @PostConstruct
    public void init() {
        listIntegrantes = new ArrayList<String>();
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


    public List<Plan> getListaPlan() {
        return listaPlan;
    }

    public void setListaPlan(List<Plan> listaPlan) {
        this.listaPlan = listaPlan;
    }

}
