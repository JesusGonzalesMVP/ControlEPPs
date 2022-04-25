/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.entity.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author David Lache <davidclass1 at gmail.com>
 */
@Named(value = "plantillaBean")
@ViewScoped
public class PlantillaBean implements Serializable{

    /**
     * Creates a new instance of PlantillaBean
     */
    public PlantillaBean() {
    }
    
    public void verificarSesion(){
        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) contex.getExternalContext().getSessionMap().get("usuario");
            
            if(us == null)
            {
                contex.getExternalContext().redirect("./../../permisos.xhtml");
            }
        } catch (IOException e) {
            //Log para guardar algun registro de un error
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", e.getMessage()));
        }
    }
    
}
