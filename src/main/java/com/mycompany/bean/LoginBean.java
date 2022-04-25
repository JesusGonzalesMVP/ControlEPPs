/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.entity.Usuario;
import com.mycompany.modelo.UsuarioFacadeLocal;
import java.awt.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author David Lache <davidclass1 at gmail.com>
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    private String nombreUsuario;
    private String password;

    private Usuario usuario;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        this.usuario = new Usuario();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;

        String ruta = "";
//        System.out.println("LoginBean 0:");
        this.usuario = usuarioFacade.login(this.usuario);
//        System.out.println("LoginBean 1: " + usuario);

        if (this.usuario != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario.getNombreusuario());

            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", this.usuario.getNombreusuario());
            // LocalHostControlEpps-1.0-SNAPSHOT/faces/Views/bienvenido.xhtm
            ruta = "/l";
            // Heroku
            // ruta = "/faces/Views/bienvenido.xhtml";
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de Acceso", "Usuario o Password es incorrecto");
            this.usuario = new Usuario();
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);
    }

    public String login() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        //boolean loggedIn = false;

        String ruta = "";
        try {
//            System.out.println("LoginBean 0:" + context);
            this.usuario = usuarioFacade.login(this.usuario);
//            System.out.println("LoginBean 1: " + usuario);

            if (this.usuario != null) {

                //Almacenar la sesion de JSF
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario);
                
                // LocalHost
//                    ruta = "/LorienJoyeria-1.0-SNAPSHOT/faces/Views/bienvenido.xhtml";
                // Heroku
                     ruta = "/Views/bienvenido?faces-redirect=true";

                //loggedIn = true;
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", this.usuario.getNombreusuario());

            } else {
                //loggedIn = false;
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de Acceso", "Usuario o Password es incorrecto");
                this.usuario = new Usuario();
            }
        } catch (Exception e) {
            //Mensaje de error
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error en la base de datos"));

        }

        return ruta;
    }

    //Metodo para cerra session
    public String cerrarSession() {
        this.nombreUsuario = null;
        this.password = null;

        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        httpSession.invalidate();//Para borrar la session;
        return "/login";
    }

    //Metodo para cerra session : ?faces-redirect=true
    public void cerrarSesion() {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    }

    //Metodo para ir a pagina principal
    public String irPrincipal() {

        this.nombreUsuario = null;
        this.password = null;
        //Localhost
        return "ControlEpps-1.0-SNAPSHOT/faces/Views/bienvenido.xhtml";

        //Heroku
        //return "/faces/Views/bienvenido.xhtml";
    }

}
