/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.entity.Operario;
import com.mycompany.modelo.OperarioFacadeLocal;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author David Lache <davidclass1 at gmail.com>
 */
@Named(value = "operarioBean")
@ViewScoped
public class OperarioBean implements Serializable {

    @EJB
    private OperarioFacadeLocal operarioFacade;

    private List<Operario> listaOperarios;

    private Operario operario;

    private Image image;
    private StreamedContent file;

    private StreamedContent imageStream;

    private String ruta;

    private static final String nombreOperarioBlanco = "operarioBlanco.jpg";

    // Contructor por defecto OperarioBean
    public OperarioBean() {
        //Inicializar las imagenes de la base de datos a la carpete resources/imagenes

    }

    // Getter and Setter listaOperarios
    public List<Operario> getListaOperarios() {
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        FileOutputStream fop = null;
        File fileImage;
        
        try {
            //listaOperarios = operarioFacade.findAll();
            listaOperarios = operarioFacade.findAllOperarioOrderByNombre();
            
            if(listaOperarios.size() > 0){
//                System.out.println("operarioBean.getListaOperarios 1: ");
                for (Operario item : listaOperarios) {
                    //Creando file
                    fileImage = new File(ec.getRealPath("/" + item.getRutaimagenoperario()));
                    fop = new FileOutputStream(fileImage);
                    
                    //si fileImage no existe, entons se creara
                    if(!fileImage.exists()){
                        fileImage.createNewFile();
                    }
                    fop.write(item.getImagenoperario());
                    fop.flush();
                    fop.close();
                }
            }
//            System.out.println("operarioBean.getListaOperarios 2: ");
        } catch (Exception e) {
            e.getMessage();
        }finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException ioe) {
                ioe.getMessage();
            }
        }
        
//        System.out.println("operarioBean.getListaOperarios 3: ");
        return listaOperarios;
    }

    public void setListaOperarios(List<Operario> listaOperarios) {
        this.listaOperarios = listaOperarios;
    }

    // Getter and Setter operario
    public Operario getOperario() {
        return operario;
    }

    public void setOperario(Operario operario) {
        this.operario = operario;
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
            ruta = "/resources/imagenes/operarios/" + nombreOperarioBlanco;
        }
//        System.out.println("operarioBean.getRuta 1: " + ruta);
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    // Getter and Setter imageStream
    public StreamedContent getImageStream() {

        imageStream = new DefaultStreamedContent();
        try {

//            System.out.println("operarioBean.getImageStream 1: " + operario);
            FacesContext context = FacesContext.getCurrentInstance();

            Integer codigoOper = new Integer((String) context.getExternalContext().getRequestParameterMap().get("codoperario"));
//            System.out.println("operarioBean.getImageStream 2: " + codigoOper);

            operario = operarioFacade.find(codigoOper);
//            System.out.println("operarioBean.getImageStream 3: " + operario.getDnioperaio());

            imageStream = new DefaultStreamedContent(new ByteArrayInputStream(operario.getImagenoperario()));
//            System.out.println("operarioBean.getImageStream 4: " + imageStream);
        } catch (NumberFormatException e) {
            e.getMessage();
        }

        return imageStream;
    }

    public void setImageStream(StreamedContent imageStream) {
        this.imageStream = imageStream;
    }

    //////////////////////////////////////////////
//    public StreamedContent getStreamedPhoto(byte[] data) {
//
//        System.out.println("operarioBean.getStreamedPhoto 1: ");
//        FacesContext context = FacesContext.getCurrentInstance();
//
//        Integer codigoOper = new Integer((String) context.getExternalContext().getRequestParameterMap().get("codoperario"));
//        System.out.println("operarioBean.getStreamedPhoto 2: " + codigoOper);
//
//        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
//            return new DefaultStreamedContent(FacesContext.getCurrentInstance()
//                    .getExternalContext()
//                    .getResourceAsStream("/static/img/default-image.png"));
//            // return new DefaultStreamedContent();
//        } else {
//            if (operario.getImagenoperario() != null) {
//                return new DefaultStreamedContent(
//                        new ByteArrayInputStream(operario.getImagenoperario()));
//            } else {
//                return new DefaultStreamedContent(FacesContext
//                        .getCurrentInstance().getExternalContext()
//                        .getResourceAsStream("/static/img/default-image.png"));
//            }
//        }
//    }
    //////////////////////////////////////////////
//    public void ver() {
//        System.out.println("operarioBean.ver 1: " + operario);
//        try {
//            //System.out.println("operarioBean.ver 2: " + Arrays.toString(operario.getImagenoperario()));
//            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//            System.out.println("operarioBean.ver 3: " + operario);
//            response.getOutputStream().write(operario.getImagenoperario());
//            System.out.println("operarioBean.ver 4: " + operario);
//            response.getOutputStream().close();
//
//            FacesContext.getCurrentInstance().responseComplete();
//
//        } catch (Exception e) {
//            e.getMessage();
//        }
//
//    }
    //Subida de imagen nuevo
    public void handleFileUploadNuevo(FileUploadEvent event) {
        try {

            //Contexto para Obtener la ruta
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

//            System.out.println("operarioBean.handleFileUploadNuevo 0 : " + event.getFile());

            String pathWebContent = ec.getRealPath("/");
//            System.out.println("operarioBean.handleFileUploadNuevo 1: " + pathWebContent);

//            System.out.println("operarioBean.handleFileUploadNuevo 2: " + event.getFile().getFileName());
            operario.setImagenoperario(event.getFile().getContents());

//            System.out.println("operarioBean.handleFileUploadNuevo 3: ");
            operario.setRutaimagenoperario("/resources/imagenes/operarios/" + event.getFile().getFileName());

//            System.out.println("operarioBean.handleFileUploadNuevo 4: " + operario.getRutaimagenoperario());

            setRuta(operario.getRutaimagenoperario());

            createFileImage(pathWebContent + ruta, event.getFile());

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

//            System.out.println("operarioBean.createFileImage 1: " + rutaImage);
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

//            System.out.println("operarioBean.handleFileUploadModificar 0 : " + event.getFile());

            String pathWebContent = ec.getRealPath("/");
//            System.out.println("operarioBean.handleFileUploadModificar 1: " + pathWebContent);

//            System.out.println("operarioBean.handleFileUploadModificar 2: " + event.getFile().getFileName());
            operario.setImagenoperario(event.getFile().getContents());

//            System.out.println("operarioBean.handleFileUploadModificar 3: " + operario.getRutaimagenoperario());
            operario.setRutaimagenoperario("/resources/imagenes/operarios/" + event.getFile().getFileName());

//            System.out.println("operarioBean.handleFileUploadModificar 4: " + operario.getRutaimagenoperario());

            setRuta(operario.getRutaimagenoperario());

            createFileImage(pathWebContent + ruta, event.getFile());

            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public void prepararNuevoOperario() {

        operario = new Operario();
        ruta = null;
    }

    public void nuevoOperario() {
        try {
            operarioFacade.create(operario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Se agregó nuevo Trabajador."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al agregar nuevo Trabajador.", e.getMessage()));
        }

    }

    public void modificarOperario() {
        try {
//            System.out.println("operarioBean.modificarOperario 1: " + operario.getRutaimagenoperario());
            operarioFacade.edit(operario);
            operario = new Operario();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Se actualizó los datos del trabajador."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al actualizar los datos del trabajador.", e.getMessage()));
        }

    }

    public void eliminarOperario() {
        try {
            operarioFacade.remove(operario);
            operario = new Operario();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Se eliminó los datos del trabajador."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al eliminar los datos del trabajador.", e.getMessage()));
        }

    }

    //#########################################################################
    /* Metodo que convierte una cadena de bytes en una imagen JPG
    * input:
    * bytes: array que contiene los binarios de la imagen
    * Output: la foto JPG en formato IMAGE
     */
    public Image ConvertirImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        return reader.read(0, param);
    }
//#########################################################################

}
