package ec.edu.uce.titulacion.controladores;

import ec.edu.uce.titulacion.dao.PlanUsuarioDao;
import ec.edu.uce.titulacion.dao.RolDao;
import ec.edu.uce.titulacion.entidades.Usuario;
import ec.edu.uce.titulacion.dao.UsuarioDao;
import ec.edu.uce.titulacion.entidades.Plan;
import ec.edu.uce.titulacion.entidades.PlanUsuario;
import ec.edu.uce.titulacion.entidades.Rol;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@Named(value = "controladorUsuario")
@SessionScoped
public class ControladorUsuario implements Serializable {

    @EJB
    private RolDao rolDao;

    @EJB
    private UsuarioDao usuarioDao;
    
    @EJB
    private PlanUsuarioDao planUsuarioDao;

    public static Usuario user;
    private Usuario usuarioPrecursor;
    private List<String> listaRolUser;
    private List<PlanUsuario> listaPostulantes;
    private String nick, password;
    private String rolSelect;
    private Rol usuarioPrecursorRol;


    public void login() throws Exception {
        Usuario usr = usuarioDao.buscarUsuarioLogin(nick, password);

        try {
            if (usr == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario y/o Contraseña incorrectos", null));
            } else {
                this.user = usr;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usr);
                this.listaRolUser = new ArrayList<String>();
                List<Rol> lista = rolDao.buscarRolByUser(user);

                for (int i = 0; i < lista.size(); i++) {
                    this.listaRolUser.add(lista.get(i).getRol());
                }
                this.setListaRolUser(listaRolUser);
                rolSelect = listaRolUser.get(0);

            }
        } catch (IOException e) {
        }
    }

    public void redireccionRol() {

        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            //contex.getExternalContext().getSessionMap().put("usuario", user);
            switch (rolSelect) {
                case "Estudiante":
                    contex.getExternalContext().redirect("homeEstudiante.xhtml");
                    break;
                case "Docente":
                    contex.getExternalContext().redirect("homeDocente.xhtml");
                    break;
                default:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se encuentra implementado tal rol", null));
            }
        } catch (Exception e) {
        }

    }

    public void verUsuarioPrecursor(Plan plan) throws Exception {
        setUsuarioPrecursor(usuarioDao.buscarUsuarioPrecursor(plan));
        setUsuarioPrecursorRol(rolDao.buscarRolByUser(getUsuarioPrecursor()).get(0));
        RequestContext.getCurrentInstance().execute("PF('wdlWare2').show();");
    }

    public void cerrarSession(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public void verPostulantes(Plan plan) throws Exception{
        setListaPostulantes(planUsuarioDao.listarPostulantesByPlan(plan));
        RequestContext.getCurrentInstance().execute("PF('wdlWare3').show();");
    }
    
    public void guardarProyecto()throws Exception{
        planUsuarioDao.guardarProyecto(getListaPostulantes());
    }
    
    public ControladorUsuario() {
    }
    
    public Usuario getUsuarioPrecursor() {
        return usuarioPrecursor;
    }

    public void setUsuarioPrecursor(Usuario usuarioPrecursor) {
        this.usuarioPrecursor = usuarioPrecursor;
    }

    public Rol getUsuarioPrecursorRol() {
        return usuarioPrecursorRol;
    }

    public void setUsuarioPrecursorRol(Rol usuarioPrecursorRol) {
        this.usuarioPrecursorRol = usuarioPrecursorRol;
    }
    
    public void setListaRolUser(List<String> listaRolUser) {
        this.listaRolUser = listaRolUser;
    }

    public List<String> getListaRolUser() throws Exception {

        return listaRolUser;
    }

    public String getRolSelect() {
        return rolSelect;
    }

    public void setRolSelect(String rolSelect) {
        this.rolSelect = rolSelect;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<PlanUsuario> getListaPostulantes() {
        return listaPostulantes;
    }

    public void setListaPostulantes(List<PlanUsuario> listaPostulantes) {
        this.listaPostulantes = listaPostulantes;
    }


}
