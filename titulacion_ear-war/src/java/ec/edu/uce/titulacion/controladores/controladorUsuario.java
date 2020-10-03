package ec.edu.uce.titulacion.controladores;

import ec.edu.uce.titulacion.entidades.Usuario;
import ec.edu.uce.titulacion.dao.UsuarioDao;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "controladorUsuario")
@SessionScoped
public class controladorUsuario implements Serializable {

    @EJB
    private UsuarioDao usuarioDao;

    private Usuario user;
    private String nick, password;

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

    public void login() throws Exception{
        Usuario usr = usuarioDao.buscarUsuarioLogin(nick, password);
        
        try {
            if (usr == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario y/o Contraseña incorrectos", null));
            } else {
                user = usr;
                FacesContext contex = FacesContext.getCurrentInstance();
                contex.getExternalContext().redirect("homeDocente.xhtml");
            }
        } catch (IOException e) {
        }
    }
}
