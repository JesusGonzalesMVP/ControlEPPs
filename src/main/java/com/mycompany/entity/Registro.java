/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Entity
@Table(name = "registro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registro.findAll", query = "SELECT r FROM Registro r")
    , @NamedQuery(name = "Registro.findByCodregistro", query = "SELECT r FROM Registro r WHERE r.codregistro = :codregistro")
    , @NamedQuery(name = "Registro.findByNumeroregistro", query = "SELECT r FROM Registro r WHERE r.numeroregistro = :numeroregistro")
    , @NamedQuery(name = "Registro.findByFormatoregistro", query = "SELECT r FROM Registro r WHERE r.formatoregistro = :formatoregistro")
    , @NamedQuery(name = "Registro.findByNombreproyecto", query = "SELECT r FROM Registro r WHERE r.nombreproyecto = :nombreproyecto")
    , @NamedQuery(name = "Registro.findByNumerotrabajadores", query = "SELECT r FROM Registro r WHERE r.numerotrabajadores = :numerotrabajadores")
    , @NamedQuery(name = "Registro.findByUbicacion", query = "SELECT r FROM Registro r WHERE r.ubicacion = :ubicacion")
    , @NamedQuery(name = "Registro.findByRevisadoregistro", query = "SELECT r FROM Registro r WHERE r.revisadoregistro = :revisadoregistro")
    , @NamedQuery(name = "Registro.findByAprobadoregistro", query = "SELECT r FROM Registro r WHERE r.aprobadoregistro = :aprobadoregistro")
    , @NamedQuery(name = "Registro.findByEntregadoregistro", query = "SELECT r FROM Registro r WHERE r.entregadoregistro = :entregadoregistro")
    , @NamedQuery(name = "Registro.findByNombreproyectooperario", query = "SELECT r FROM Registro r WHERE r.nombreproyectooperario = :nombreproyectooperario")
    , @NamedQuery(name = "Registro.findByPlantaoperario", query = "SELECT r FROM Registro r WHERE r.plantaoperario = :plantaoperario")
    , @NamedQuery(name = "Registro.findByFecharegistro", query = "SELECT r FROM Registro r WHERE r.fecharegistro = :fecharegistro")})
public class Registro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codregistro")
    private Integer codregistro;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "numeroregistro")
    private String numeroregistro;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "formatoregistro")
    private String formatoregistro;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombreproyecto")
    private String nombreproyecto;
    
    @Column(name = "numerotrabajadores")
    private Integer numerotrabajadores;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "ubicacion")
    private String ubicacion;
    
    @Column(name = "revisadoregistro")
    private Boolean revisadoregistro;
    
    @Column(name = "aprobadoregistro")
    private Boolean aprobadoregistro;
    
    @Column(name = "entregadoregistro")
    private Boolean entregadoregistro;
    
    @Size(max = 30)
    @Column(name = "nombreproyectooperario")
    private String nombreproyectooperario;
    
    @Size(max = 30)
    @Column(name = "plantaoperario")
    private String plantaoperario;
    
    @Column(name = "fecharegistro")
    @Temporal(TemporalType.DATE)
    private Date fecharegistro;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codregistro", fetch = FetchType.LAZY)
    private List<Detalleregistro> detalleregistroList;
    
    @JoinColumn(name = "codoperario", referencedColumnName = "codoperario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Operario codoperario;
    
    @JoinColumn(name = "codusuario", referencedColumnName = "codusuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario codusuario;

    public Registro() {
    }

    public Registro(Integer codregistro) {
        this.codregistro = codregistro;
    }

    public Registro(Integer codregistro, String numeroregistro, String formatoregistro, String nombreproyecto, String ubicacion) {
        this.codregistro = codregistro;
        this.numeroregistro = numeroregistro;
        this.formatoregistro = formatoregistro;
        this.nombreproyecto = nombreproyecto;
        this.ubicacion = ubicacion;
    }

    public Integer getCodregistro() {
        return codregistro;
    }

    public void setCodregistro(Integer codregistro) {
        this.codregistro = codregistro;
    }

    public String getNumeroregistro() {
        return numeroregistro;
    }

    public void setNumeroregistro(String numeroregistro) {
        this.numeroregistro = numeroregistro;
    }

    public String getFormatoregistro() {
        return formatoregistro;
    }

    public void setFormatoregistro(String formatoregistro) {
        this.formatoregistro = formatoregistro;
    }

    public String getNombreproyecto() {
        return nombreproyecto;
    }

    public void setNombreproyecto(String nombreproyecto) {
        this.nombreproyecto = nombreproyecto;
    }

    public Integer getNumerotrabajadores() {
        return numerotrabajadores;
    }

    public void setNumerotrabajadores(Integer numerotrabajadores) {
        this.numerotrabajadores = numerotrabajadores;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Boolean getRevisadoregistro() {
        return revisadoregistro;
    }

    public void setRevisadoregistro(Boolean revisadoregistro) {
        this.revisadoregistro = revisadoregistro;
    }

    public Boolean getAprobadoregistro() {
        return aprobadoregistro;
    }

    public void setAprobadoregistro(Boolean aprobadoregistro) {
        this.aprobadoregistro = aprobadoregistro;
    }

    public Boolean getEntregadoregistro() {
        return entregadoregistro;
    }

    public void setEntregadoregistro(Boolean entregadoregistro) {
        this.entregadoregistro = entregadoregistro;
    }

    public String getNombreproyectooperario() {
        return nombreproyectooperario;
    }

    public void setNombreproyectooperario(String nombreproyectooperario) {
        this.nombreproyectooperario = nombreproyectooperario;
    }

    public String getPlantaoperario() {
        return plantaoperario;
    }

    public void setPlantaoperario(String plantaoperario) {
        this.plantaoperario = plantaoperario;
    }

    public Date getFecharegistro(){
        System.out.println("Fecha Registro: " + fecharegistro );
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    @XmlTransient
    @JsonIgnore
    public List<Detalleregistro> getDetalleregistroList() {
        return detalleregistroList;
    }

    public void setDetalleregistroList(List<Detalleregistro> detalleregistroList) {
        this.detalleregistroList = detalleregistroList;
    }

    public Operario getCodoperario() {
        return codoperario;
    }

    public void setCodoperario(Operario codoperario) {
        this.codoperario = codoperario;
    }

    public Usuario getCodusuario() {
        return codusuario;
    }

    public void setCodusuario(Usuario codusuario) {
        this.codusuario = codusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codregistro != null ? codregistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registro)) {
            return false;
        }
        Registro other = (Registro) object;
        if ((this.codregistro == null && other.codregistro != null) || (this.codregistro != null && !this.codregistro.equals(other.codregistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.Registro[ codregistro=" + codregistro + " ]";
    }

}
