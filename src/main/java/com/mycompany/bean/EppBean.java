/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.entity.Epp;
import com.mycompany.modelo.EppFacadeLocal;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author David Lache <davidclass1 at gmail.com>
 */
@Named(value = "eppBean")
@ViewScoped
public class EppBean implements Serializable {

    @EJB
    private EppFacadeLocal eppFacade;

    private List<Epp> listaEpps;

    private Epp epp;

    private Image image;
    private StreamedContent file;

    private StreamedContent imageStream;

    private String ruta;

    private static final String nombreEppBlanco = "eppBlanco.jpg";

    public EppBean() {
        //Inicializar las imagenes de la base de datos a la carpete resources/imagenes
    }

    // Getter and Setter listaEpps
    public List<Epp> getListaEpps() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        FileOutputStream fop = null;
        File fileImage;

        try {
            listaEpps = eppFacade.findAllEppOrderByNombre();
            //System.out.println("eppBean.getListaEpps 0: " + listaEpps.size());

            //Si hay imagenes en la base de datos descargarlas a la carpeta Reources/imagenes
            if (listaEpps.size() > 0) {
                //System.out.println("eppBean.getListaEpps 1: ");
                for (Epp item : listaEpps) {
                    
                    //System.out.println("eppBean.getListaEpps 1: " + ec.getRealPath("/"));
                    //Creando file
                    fileImage = new File(ec.getRealPath("/") + item.getRutaimagenepp());
                    fop = new FileOutputStream(fileImage);

                    //si fileImage no existe, entons se creara
                    if (!fileImage.exists()) {
                        fileImage.createNewFile();
                    }
                    fop.write(item.getImagenepp());
                    fop.flush();
                    fop.close();
                    
                    if (item.getStockactual() <= 2) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Revisar stock de " + item.getNombreepp()));
                    }
                }
            }
            //System.out.println("eppBean.getListaEpps 2: ");
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException ioe) {
                ioe.getMessage();
            }
        }
        //System.out.println("eppBean.getListaEpps 3: ");
        return listaEpps;
    }

    public void setListaEpps(List<Epp> listaEpps) {
        this.listaEpps = listaEpps;
    }

    // Getter and Setter epp
    public Epp getEpp() {
        return epp;
    }

    public void setEpp(Epp epp) {
        this.epp = epp;
    }

    // Getter and Setter image
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    // Getter and Setter file
    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    // Getter and Setter ruta
    public String getRuta() {
        if (ruta == null) {
            ruta = "/resources/imagenes/epps/" + nombreEppBlanco;
        }
        System.out.println("eppBean.getRuta 1: " + ruta);
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    // Getter and Setter imageStream
    public StreamedContent getImageStream() {

        imageStream = new DefaultStreamedContent();
        try {

            //System.out.println("eppBean.getImageStream 1: " + epp);
            FacesContext context = FacesContext.getCurrentInstance();

            Integer codigoEpp = new Integer((String) context.getExternalContext().getRequestParameterMap().get("codoperario"));
            //System.out.println("eppBean.getImageStream 2: " + codigoEpp);

            epp = eppFacade.find(codigoEpp);
            //System.out.println("eppBean.getImageStream 3: " + epp.getNombreepp());

            imageStream = new DefaultStreamedContent(new ByteArrayInputStream(epp.getImagenepp()));
            //System.out.println("eppBean.getImageStream 4: " + imageStream);
        } catch (NumberFormatException e) {
            e.getMessage();
        }

        return imageStream;
    }

    public void setImageStream(StreamedContent imageStream) {
        this.imageStream = imageStream;
    }

    //Subida de imagen nuevo
    public void handleFileUploadNuevo(FileUploadEvent event) {
        try {

            //Contexto para Obtener la ruta
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

//            System.out.println("eppBean.handleFileUploadNuevo 0 : " + event.getFile());

            String pathWebContent = ec.getRealPath("/");
//            System.out.println("eppBean.handleFileUploadNuevo 1: " + pathWebContent);

//            System.out.println("eppBean.handleFileUploadNuevo 2: " + event.getFile().getFileName());
            epp.setImagenepp(event.getFile().getContents());

//            System.out.println("eppBean.handleFileUploadNuevo 3: ");
            epp.setRutaimagenepp("/resources/imagenes/epps/" + event.getFile().getFileName());

//            System.out.println("eppBean.handleFileUploadNuevo 4: " + epp.getRutaimagenepp());

            this.setRuta(epp.getRutaimagenepp());

            this.createFileImage(pathWebContent + ruta, event.getFile());

            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    //Create imagen en directorio
    public void createFileImage(String rutaImage, UploadedFile file) {

        BufferedImage imagen = null;
        try {

            System.out.println("eppBean.createFileImage 1: " + rutaImage);
            imagen = ImageIO.read(file.getInputstream());

            ImageIO.write(imagen, "jpg", new File(rutaImage));

        } catch (IOException e) {
            e.getMessage();
        }
    }

    //Subida de imagen modificada
    public void handleFileUploadModificar(FileUploadEvent event) {
        try {

            //Contexto para Obtener la ruta
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

//            System.out.println("eppBean.handleFileUploadModificar 0 : " + event.getFile());

            String pathWebContent = ec.getRealPath("/");
//            System.out.println("eppBean.handleFileUploadModificar 1: " + pathWebContent);

//            System.out.println("eppBean.handleFileUploadModificar 2: " + event.getFile().getFileName());
            epp.setImagenepp(event.getFile().getContents());

//            System.out.println("eppBean.handleFileUploadModificar 3: " + epp.getRutaimagenepp());
            epp.setRutaimagenepp("/resources/imagenes/epps/" + event.getFile().getFileName());

//            System.out.println("eppBean.handleFileUploadModificar 4: " + epp.getRutaimagenepp());

            this.setRuta(epp.getRutaimagenepp());

            createFileImage(pathWebContent + ruta, event.getFile());

            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public void prepararNuevoEpp() {

        epp = new Epp();
        ruta = null;
    }

    public void nuevoEpp() {
        try {
            eppFacade.create(epp);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Se agregó nuevo EPP."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al agregar nuevo EPP.", e.getMessage()));
        }

    }

    public void modificarEpp() {
        try {
//            System.out.println("eppBean.modificarOperario 1: " + epp.getRutaimagenepp());
            eppFacade.edit(epp);
            epp = new Epp();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Se modificó los datos del EPP."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al modificar los datos del EPP", e.getMessage()));
        }

    }

    public void eliminarEpp() {
        try {
            eppFacade.remove(epp);
            epp = new Epp();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Se eliminó el EPP."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al eliminar los datos del EPP", e.getMessage()));
        }

    }
}
