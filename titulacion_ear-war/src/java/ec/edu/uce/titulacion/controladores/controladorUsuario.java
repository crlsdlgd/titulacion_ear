package ec.edu.uce.titulacion.controladores;

import ec.edu.uce.titulacion.dao.RolDao;
import ec.edu.uce.titulacion.entidades.Usuario;
import ec.edu.uce.titulacion.dao.UsuarioDao;
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
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

@Named(value = "controladorUsuario")
@SessionScoped
public class controladorUsuario implements Serializable {

    @EJB
    private RolDao rolDao;

    @EJB
    private UsuarioDao usuarioDao;

    public static Usuario user;
    private List<String> listaRolUser;

    public void setListaRolUser(List<String> listaRolUser) {
        this.listaRolUser = listaRolUser;
    }
    private String nick, password;
    private String rolSelect;

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

    public controladorUsuario() {
    }

    public void login() throws Exception {
        Usuario usr = usuarioDao.buscarUsuarioLogin(nick, password);

        try {
            if (usr == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario y/o Contraseña incorrectos", null));
            } else {
                this.user = usr;

                this.listaRolUser = new ArrayList<String>();
                List<Rol> lista = rolDao.buscarRolByUser(user);

                for (int i=0;i<lista.size();i++) {
                    this.listaRolUser.add(lista.get(i).getRol());
                }
                this.setListaRolUser(listaRolUser);
                rolSelect=listaRolUser.get(0);
//                FacesContext contex = FacesContext.getCurrentInstance();
//                contex.getExternalContext().redirect("homeDocente.xhtml");
                //mostrarModal();

            }
        } catch (IOException e) {
        }
    }

    public void mostrarModal() {
        System.out.println("luego de mostrar modal Numero de roles. " + listaRolUser.size());
        System.out.println("Primer rol. " + listaRolUser.get(0));
        rolSelect = listaRolUser.get(0);
        RequestContext req = RequestContext.getCurrentInstance();
        req.execute("PF('wlgRol').show();");
    }

    public void redireccionRol() {
        System.out.println("Rol seleccionado: " + rolSelect);
        System.out.println("Primer rol. " + listaRolUser.get(0));
        
        switch (rolSelect) {
            case "Estudiante":
                System.out.println("Rol de Estudiante");
                break;
            case "Docente":
                System.out.println("Rol de Docente");
                break;
        }

    }
}
