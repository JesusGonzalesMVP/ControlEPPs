/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Entity
@Table(name = "detalleregistro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleregistro.findAll", query = "SELECT d FROM Detalleregistro d")
    , @NamedQuery(name = "Detalleregistro.findByCoddetalleregistro", query = "SELECT d FROM Detalleregistro d WHERE d.coddetalleregistro = :coddetalleregistro")
    , @NamedQuery(name = "Detalleregistro.findByCantidadentregaepp", query = "SELECT d FROM Detalleregistro d WHERE d.cantidadentregaepp = :cantidadentregaepp")
    , @NamedQuery(name = "Detalleregistro.findByObservacion", query = "SELECT d FROM Detalleregistro d WHERE d.observacion = :observacion")
    , @NamedQuery(name = "Detalleregistro.findByNombreepp", query = "SELECT d FROM Detalleregistro d WHERE d.nombreepp = :nombreepp")
    , @NamedQuery(name = "Detalleregistro.findByTallaepp", query = "SELECT d FROM Detalleregistro d WHERE d.tallaepp = :tallaepp")
    , @NamedQuery(name = "Detalleregistro.findByFechaentregaepp", query = "SELECT d FROM Detalleregistro d WHERE d.fechaentregaepp = :fechaentregaepp")
    , @NamedQuery(name = "Detalleregistro.findByConformidad", query = "SELECT d FROM Detalleregistro d WHERE d.conformidad = :conformidad")
    , @NamedQuery(name = "Detalleregistro.findByMotivoentrega", query = "SELECT d FROM Detalleregistro d WHERE d.motivoentrega = :motivoentrega")})
public class Detalleregistro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coddetalleregistro")
    private Integer coddetalleregistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadentregaepp")
    private int cantidadentregaepp;
    @Size(max = 60)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 90)
    @Column(name = "nombreepp")
    private String nombreepp;
    @Size(max = 5)
    @Column(name = "tallaepp")
    private String tallaepp;
    @Column(name = "fechaentregaepp")
    @Temporal(TemporalType.DATE)
    private Date fechaentregaepp;
    @Column(name = "conformidad")
    private Boolean conformidad;
    @Column(name = "motivoentrega")
    private Boolean motivoentrega;
    @JoinColumn(name = "codepp", referencedColumnName = "codepp")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Epp codepp;
    @JoinColumn(name = "codregistro", referencedColumnName = "codregistro")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Registro codregistro;

    public Detalleregistro() {
    }

    public Detalleregistro(Integer coddetalleregistro) {
        this.coddetalleregistro = coddetalleregistro;
    }

    public Detalleregistro(Integer coddetalleregistro, int cantidadentregaepp) {
        this.coddetalleregistro = coddetalleregistro;
        this.cantidadentregaepp = cantidadentregaepp;
    }
    
    public Detalleregistro(Registro registro, 
                           Epp epp,                            
                           int cantidadEntrega, 
                           boolean motivoentrega,
                           boolean conformidad,
                           Date fechaEntrega,
                           String observacionEpp
                           ){
        this.codregistro = registro;
        this.codepp = epp;
        this.nombreepp = epp.getNombreepp();
        this.tallaepp = epp.getTallaepp();
        this.cantidadentregaepp = cantidadEntrega;
        this.motivoentrega = motivoentrega;
        this.fechaentregaepp = fechaEntrega;
        this.conformidad = conformidad;
        this.observacion = observacionEpp;
        }

    public Integer getCoddetalleregistro() {
        return coddetalleregistro;
    }

    public void setCoddetalleregistro(Integer coddetalleregistro) {
        this.coddetalleregistro = coddetalleregistro;
    }

    public int getCantidadentregaepp() {
        return cantidadentregaepp;
    }

    public void setCantidadentregaepp(int cantidadentregaepp) {
        this.cantidadentregaepp = cantidadentregaepp;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNombreepp() {
        return nombreepp;
    }

    public void setNombreepp(String nombreepp) {
        this.nombreepp = nombreepp;
    }

    public String getTallaepp() {
        return tallaepp;
    }

    public void setTallaepp(String tallaepp) {
        this.tallaepp = tallaepp;
    }

    public Date getFechaentregaepp() {
        return fechaentregaepp;
    }

    public void setFechaentregaepp(Date fechaentregaepp) {
        this.fechaentregaepp = fechaentregaepp;
    }

    public Boolean getConformidad() {
        return conformidad;
    }

    public void setConformidad(Boolean conformidad) {
        this.conformidad = conformidad;
    }

    public Boolean getMotivoentrega() {
        return motivoentrega;
    }

    public void setMotivoentrega(Boolean motivoentrega) {
        this.motivoentrega = motivoentrega;
    }

    public Epp getCodepp() {
        return codepp;
    }

    public void setCodepp(Epp codepp) {
        this.codepp = codepp;
    }

    public Registro getCodregistro() {
        return codregistro;
    }

    public void setCodregistro(Registro codregistro) {
        this.codregistro = codregistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coddetalleregistro != null ? coddetalleregistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleregistro)) {
            return false;
        }
        Detalleregistro other = (Detalleregistro) object;
        if ((this.coddetalleregistro == null && other.coddetalleregistro != null) || (this.coddetalleregistro != null && !this.coddetalleregistro.equals(other.coddetalleregistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.Detalleregistro[ coddetalleregistro=" + coddetalleregistro + " ]";
    }

}
