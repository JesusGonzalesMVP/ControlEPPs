/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.mycompany.entity.Detalleregistro;
import com.mycompany.entity.Epp;
import com.mycompany.entity.Operario;
import com.mycompany.entity.Registro;
import com.mycompany.modelo.DetalleregistroFacadeLocal;
import com.mycompany.modelo.EppFacadeLocal;
import com.mycompany.modelo.OperarioFacadeLocal;
import com.mycompany.modelo.RegistroFacadeLocal;
import com.mycompany.utilwar.ReporteRegistroEpp;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author David Lache <davidclass1 at gmail.com>
 */
@Named(value = "registroBean")
@ViewScoped
public class RegistroBean implements Serializable {

    @EJB
    private RegistroFacadeLocal registroFacade;

    @EJB
    private OperarioFacadeLocal operarioFacade;

    @EJB
    private EppFacadeLocal eppFacade;

    @EJB
    private DetalleregistroFacadeLocal detalleregistroFacade;

    @Inject //Version 2.2
    private LoginBean loginBean;

    @Inject
    private OperarioBean operarioBean;

    //Variables para Operario
    private Operario operario;
    private Integer codigoOperario;

    //Variables para Epp
    private Epp epp;
    private Integer codigoEpp;

    private List<Detalleregistro> listaDetalleregistro;

    private String eppSeleccionado;
    private String cantidadEpp;

    private Registro registro;

    private List<Registro> listaRegistros;

    private Long numeroRegistro;

    //variable para activar o desactivar los botones en la venta
    private boolean enabled;

    //Variable Recuperar fecha del sistema
    private String fechaSistema;

    /**
     * Creates a new instance of RegistroBean
     */
    public RegistroBean() {

//        System.out.println("RegistroBean 1: ");
        this.registro = new Registro();
//        this.registro.setNumerotrabajadores(operarioFacade.findAll().size());
        this.registro.setNombreproyectooperario("Click Para Editar");
        this.registro.setPlantaoperario("Click Para Editar");
        this.registro.setNombreproyecto("Click Para Editar");
        this.registro.setUbicacion("Click Para Editar");
        this.registro.setNumerotrabajadores(0);

        if (this.registro.getFecharegistro() == null) {
            //Obtenemos la fecha del sistema y le asignamos al registro
            java.util.Date fecharegistro = new Date();
            this.registro.setFecharegistro(fecharegistro);
        }

        this.operario = new Operario();
        this.listaDetalleregistro = new ArrayList<>();
//        System.out.println("RegistroBean 2: ");
    }

    //Obtener fecha formateada
    public String obtenerFechaFormateada() {

        String fecha = "";

        try {
            fecha = this.registro.getFecharegistro().toString();
            if (fecha != null) {
//                System.out.println("registroBean.obtenerFechaFormateada 1: ");
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//                System.out.println("registroBean.obtenerFechaFormateada 2: ");
                Date date = this.registro.getFecharegistro();
//                System.out.println("registroBean.obtenerFechaFormateada 3: ");
                fecha = dateFormat.format(date);
            }

        } catch (Exception e) {
            e.getMessage();
        }

        return fecha;

    }

    //Metodo para recuperar fecha del sistema
    public String getFechaSistema() {
        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        int horas = fecha.get(Calendar.HOUR_OF_DAY);
        int minutos = fecha.get(Calendar.MINUTE);
        int segundos = fecha.get(Calendar.SECOND);

        this.fechaSistema = formatearFecha(dia, (mes + 1), anio, horas, minutos, segundos);

//        this.fechaSistema = (dia + "/" + (mes+1) + "/" + anio) + " " 
//                           +(horas + ":" + minutos + ":" + segundos) ;
        return fechaSistema;
    }

    //Metodo que formatea la fecha dd/mm/YYYY HH:MM:SS
    public String formatearFecha(int dia, int mes, int anio,
            int horas, int minutos, int segundos) {

        String sDia, sMes, sAnio, sHoras, sMinutos, sSegundos;
        sDia = Integer.toString(dia);
        sMes = Integer.toString(mes);
        sAnio = Integer.toString(anio);
        sHoras = Integer.toString(horas);
        sMinutos = Integer.toString(minutos);
        sSegundos = Integer.toString(segundos);

        if (dia < 10) {
            sDia = "0" + dia;
        }
        if (mes < 10) {
            sMes = "0" + mes;
        }
        if (horas < 10) {
            sHoras = "0" + horas;
        }
        if (minutos < 10) {
            sMinutos = "0" + minutos;
        }
        if (segundos < 10) {
            sSegundos = "0" + segundos;
        }

        return (sDia + "/" + sMes + "/" + sAnio) + " "
                + (sHoras + ":" + sMinutos + ":" + sSegundos);
    }

    public void setFechaSistema(String fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public List<Registro> getListaRegistros() {

        try {
            //listaRegistros = registroFacade.findAll();
            listaRegistros = registroFacade.findAllRegistroOrderByFecha();
        } catch (Exception e) {
            e.getMessage();
        }
        return listaRegistros;
    }

    public void setListaRegistros(List<Registro> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public Epp getEpp() {
        return epp;
    }

    public void setEpp(Epp epp) {
        this.epp = epp;
    }

    public List<Detalleregistro> getListaDetalleregistro() {
        try {
            listaDetalleregistro = detalleregistroFacade.findAllDetalleRegistroOrderByFecha(this.registro.getCodregistro());

        } catch (Exception e) {
            e.getMessage();
        }
        return listaDetalleregistro;
    }

    public void setListaDetalleregistro(List<Detalleregistro> listaDetalleregistro) {
        this.listaDetalleregistro = listaDetalleregistro;
    }

    public String getCantidadEpp() {
        return cantidadEpp;
    }

    public void setCantidadEpp(String cantidadEpp) {
        this.cantidadEpp = cantidadEpp;
    }

    public String getEppSeleccionado() {
        return eppSeleccionado;
    }

    public void setEppSeleccionado(String eppSeleccionado) {
        this.eppSeleccionado = eppSeleccionado;
    }

    public Registro getRegistro() {
        return registro;
    }

    public Operario getOperario() {
        return operario;
    }

    public void setOperario(Operario operario) {
        this.operario = operario;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public String addRegistro() {
        registroFacade.create(registro);
        this.registro = new Registro();
        return "./../bienvenido";
    }

    public Long getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(Long numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    //Metodo para activar o desactivar los botones en la factura
    public boolean isEnabled() {
        return enabled;
    }

    public void enableButton() {
        enabled = true;
    }

    public void disableButton() {
        enabled = false;
    }

    //Metodo Para generar el numero de venta
    public void numeracionRegistro() {

        try {

            //Verificar si hay registros en la tabla factura de la BD
            this.numeroRegistro = Long.parseLong(Integer.toString(registroFacade.count()));
            this.registro.setNumerotrabajadores(operarioFacade.count());
            if (this.registro.getFecharegistro() == null) {
                //Obtenemos la fecha del sistema y le asignamos al registro
                java.util.Date fecharegistro = new Date();
                this.registro.setFecharegistro(fecharegistro);
            }

//            System.out.println("registroBean.numeracionRegistro 1 :" + numeroRegistro);
            if (this.numeroRegistro <= 0 || this.numeroRegistro == null) {
                this.numeroRegistro = Long.valueOf("1");
            } else {
                //Recuperamos el ultimo que exista en la tabla de la BD, en una nueva variable facturaAux, 
                //porque sino chancariamos la variable this.factura del FacturaBean
//                System.out.println("registroBean.numeracionRegistro 2 :");
                Registro registroAux = registroFacade.obtenerUltimoRegistro();
                this.numeroRegistro = Long.valueOf(registroAux.getNumeroregistro()) + 1;

                //Limpiar la variable totalVntaFactura
//                this.totalVentaFactura = new BigDecimal("0");
//                this.subTotalVenta = new BigDecimal("0");
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    //Metodo para limpiar Registro
    public void cancelarRegistro() {

        //Invocar al metodo para desactivar botones en la factura
        this.listaDetalleregistro = new ArrayList<>();
        this.disableButton();
    }

    // Metodo para agregar los datos del operaio por medio de la lista de operarios dialogOperarios
    public void agregarDatosOperario(Integer codOperario) {

        try {

//            ClienteDao clienteDao = new ClienteDaoImpl();
//            this.transaction = this.session.beginTransaction();
            //Obtener los datos del cliente en la variable objeto cliente segun el codigo del cliente
//            this.cliente = clienteDao.obtenerClientePorCodigo(this.session, codCliente);
            this.operario = operarioFacade.obtenerOperarioPorCodigo(codOperario);

//            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del Operario agregado"));

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    //Metodo para solicitar cantidad de epp a vender
    public void pedirCantidadEpp(Integer codEpp) {
        this.eppSeleccionado = codEpp.toString();
    }

    public void agregarDatosEpp2() throws Exception {
//        System.out.println("registroBean.agregarDatosEpp2 1 :" + this.cantidadEpp);
//        System.out.println("registroBean.agregarDatosEpp2 2 :" + this.eppSeleccionado);

        try {
            this.epp = eppFacade.obtenerEppPorCodigo(Integer.parseInt(this.eppSeleccionado));

//            System.out.println("registroBean.agregarDatosEpp2 3 :" + this.epp);
            if (!(this.cantidadEpp.matches("[0-9]*")) || this.cantidadEpp.equals("0") || this.cantidadEpp.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cantidad es incorrecta"));
                this.cantidadEpp = "";
            } else {
                this.registro.getDetalleregistroList().add(new Detalleregistro(this.registro,
                        this.epp,
                        Integer.parseInt(this.cantidadEpp),
                        false,
                        false,
                        null,
                        null
                ));
            }
        } catch (NumberFormatException e) {
            e.getMessage();
        }

    }

    // Metodo para agregar los datos del epp por medio del dialogEpp
    public void agregarDatosEpp() {

//        System.out.println("RegistroBean.agregarDatosEpp 0: " + listaDetalleregistro.size());
        try {
            this.epp = eppFacade.obtenerEppPorCodigo(Integer.parseInt(this.eppSeleccionado));

            if (!(this.cantidadEpp.matches("[0-9]*")) || this.cantidadEpp.equals("0") || this.cantidadEpp.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cantidad es incorrecta"));
                this.cantidadEpp = "";
            } else {

//                System.out.println("RegistroBean.agregarDatosEpp 1: " + epp);
//                System.out.println("RegistroBean.agregarDatosEpp 1.2: " + epp.getNombreepp());
//                System.out.println("RegistroBean.agregarDatosEpp 1.3: " + this.cantidadEpp);
//                System.out.println("RegistroBean.agregarDatosEpp 1.4: " + epp.getMotivoentrega());
//                System.out.println("RegistroBean.agregarDatosEpp 1.5: " + epp.getObservacion());
//                System.out.println("RegistroBean.agregarDatosEpp 1.6: " + epp.getTallaepp());
//                System.out.println("RegistroBean.agregarDatosEpp 1.7: " + epp.getFechaentrega());
//                System.out.println("RegistroBean.agregarDatosEpp 1.8: " + epp.getConformidad());
//                System.out.println("RegistroBean.agregarDatosEpp 1.9: " + this.listaDetalleregistro.size());
//
//                System.out.println("RegistroBean.agregarDatosEpp 1.10: ");
                /*
                Registro,
                Epp
                cantidadEpp,
                motivoEntrega,
                conformidad,
                fechaEntrega,
                observacion
                 */
                this.listaDetalleregistro.add(new Detalleregistro(null,
                        this.epp,
                        Integer.parseInt(this.cantidadEpp),
                        false,
                        false,
                        null,
                        null
                )
                );
//                System.out.println("RegistroBean.agregarDatosEpp 2: " + listaDetalleregistro.size());

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Epp agregado al detalle"));
//                System.out.println("RegistroBean.agregarDatosEpp 3: ");
                this.cantidadEpp = "";
//                System.out.println("RegistroBean.agregarDatosEpp 4: ");
            }
        } catch (Exception e) {
            e.getMessage();
//            System.out.println("registroBean 0: " + e.getMessage());
        }

    }

    //Metodos para editar la cantidad de producto en la tabla productoFactura
    public void onRowEdit(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Se Modificó la cantidad"));
    }

    public void onRowCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "No se realizó ningún cambio"));
    }

    //Metodo para quitar un epp del detalleRegistro en un registro nuevo
    public void quitarEppDetalleRegistro(Integer codEpp, Integer filaSeleccionada) {
        try {
            int i = 0;
//            System.out.println("registroBean.quitarEppDetalleRegistro 1: ");
            for (Detalleregistro item : this.listaDetalleregistro) {
                if (item.getCodepp().getCodepp().equals(codEpp) && filaSeleccionada.equals(i)) {
                    this.listaDetalleregistro.remove(i);
                    break;
                }
                i++;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informacion", "Se retiró el EPP del Detalle."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al retirar el EPP", e.getMessage()));
        }
    }
    
    //Metodo para quitar un epp del detalleRegistro en un registro Existente
    public void quitarEppDetalleRegistroM(Integer codEpp, Integer filaSeleccionada) {
        try {
            int i = 0;
//            System.out.println("registroBean.quitarEppDetalleRegistro 1: ");
            for (Detalleregistro item : this.registro.getDetalleregistroList()) {
                if (item.getCodepp().getCodepp().equals(codEpp) && filaSeleccionada.equals(i)) {
                    this.registro.getDetalleregistroList().remove(i);
                    break;
                }
                i++;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informacion", "Se retiró el EPP del Detalle."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al retirar el EPP", e.getMessage()));
        }
    }

    //Metodo para limpiar Registro
    public void limpiarRegistro() {
        registro = new Registro();
        this.operario = new Operario();
        this.listaDetalleregistro = new ArrayList<>();
        this.numeroRegistro = null;

        //Invocar al metodo para desactivar botones en la factura
        this.disableButton();

    }

    public void actualizarRegistro() {

//        System.out.println("registroBean.actualizarRegistro 1: ");
        try {
            registroFacade.edit(this.registro);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Se actualizó los datos del Registro Nº " + registro.getNumeroregistro()));

            this.limpiarRegistro();

        } catch (Exception e) {
            e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al actualizó los datos del Registro Nº" + registro.getNumeroregistro(), e.getMessage()));
        }

    }

    //Metodo para guardar un registro nuevo
    public void guardarRegistro() {

//        System.out.println("registroBean.guardarRegistro 0: " + loginBean.getUsuario());
        try {
//            System.out.println("registroBean.guardarRegistro 1: " + this.numeroRegistro.toString());
//            System.out.println("registroBean.guardarRegistro 2: " + this.operario);
//            System.out.println("registroBean.guardarRegistro 3: " + this.fechaSistema);

            this.registro.setNumeroregistro(this.numeroRegistro.toString());
            this.registro.setCodoperario(operario);
            this.registro.setCodusuario(loginBean.getUsuario());
            this.registro.setFormatoregistro("R-SGI-SSO-009");
//            System.out.println("registroBean.guardarRegistro 4: ");
            registroFacade.guardarRegistro(registro);
//            System.out.println("registroBean.guardarRegistro 5: ");

            //Recuperar el ultimo registro de la tabla registro
            this.registro = registroFacade.obtenerUltimoRegistro();

//            System.out.println("registroBean.guardarRegistro 6: " + registro.getCodregistro());

            for (Detalleregistro item : listaDetalleregistro) {
                this.epp = eppFacade.obtenerEppPorCodigo(item.getCodepp().getCodepp());
                item.setCodregistro(this.registro);
                item.setCodepp(this.epp);

//                System.out.println("registroBean.guardarRegistro 7: ");
                //Hacemos el insert en la tabla  detalleRegistro de la BD
                detalleregistroFacade.guardarRegistroDetalleregistro(item);
//                System.out.println("registroBean.guardarRegistro 8: ");
            }

            this.limpiarRegistro();

        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void verRegistro2() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

//        System.out.println("RegistroBean.verRegistro2 0: ");

        int cOperario = this.registro.getCodoperario().getCodoperario();
//        System.out.println("RegistroBean.verRegistro2 1: ");

        int cRegistro = this.registro.getCodregistro();
//        System.out.println("RegistroBean.verRegistro2 2: ");

        this.actualizarRegistro();

        ReporteRegistroEpp reporteRegistroEpp = new ReporteRegistroEpp();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        // Tnener en cuenta la ruta para internet
        String ruta = servletContext.getRealPath("/Reportes/registroEpp.jasper");

//        System.out.println("RegistroBean.verRegistro2 3: " + cOperario);
//        System.out.println("RegistroBean.verRegistro2 4: " + cRegistro);

        reporteRegistroEpp.getReporte(ruta, cOperario, cRegistro);
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void verRegistro() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

//        System.out.println("RegistroBean.verRegistro 1: ");

        int cOperario = this.operario.getCodoperario();
        int cRegistro = 0;
        try {
            Registro registroAux = registroFacade.obtenerUltimoRegistro();
            cRegistro = registroAux.getCodregistro() + 1;
        } catch (Exception e) {
            e.getMessage();
        }

        //Invocamos al metodo guardarRegistro, para almacenar el registro en las tablas correspondientes
        this.guardarRegistro();

        ReporteRegistroEpp reporteRegistroEpp = new ReporteRegistroEpp();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        // Tnener en cuenta la ruta para internet
        String ruta = servletContext.getRealPath("/Reportes/registroEpp.jasper");

//        System.out.println("RegistroBean.verRegistro 2: " + cOperario);
//        System.out.println("RegistroBean.verRegistro 3: " + cRegistro);

        reporteRegistroEpp.getReporte(ruta, cOperario, cRegistro);
        FacesContext.getCurrentInstance().responseComplete();
    }

}
