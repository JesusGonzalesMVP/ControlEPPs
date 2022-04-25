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
import javax.persistence.Lob;
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
@Table(name = "epp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Epp.findAll", query = "SELECT e FROM Epp e")
    , @NamedQuery(name = "Epp.findByCodepp", query = "SELECT e FROM Epp e WHERE e.codepp = :codepp")
    , @NamedQuery(name = "Epp.findByNombreepp", query = "SELECT e FROM Epp e WHERE e.nombreepp = :nombreepp")
    , @NamedQuery(name = "Epp.findByStockactual", query = "SELECT e FROM Epp e WHERE e.stockactual = :stockactual")
    , @NamedQuery(name = "Epp.findByTallaepp", query = "SELECT e FROM Epp e WHERE e.tallaepp = :tallaepp")
    , @NamedQuery(name = "Epp.findByRutaimagenepp", query = "SELECT e FROM Epp e WHERE e.rutaimagenepp = :rutaimagenepp")
    , @NamedQuery(name = "Epp.findByMotivoentrega", query = "SELECT e FROM Epp e WHERE e.motivoentrega = :motivoentrega")
    , @NamedQuery(name = "Epp.findByFechaentrega", query = "SELECT e FROM Epp e WHERE e.fechaentrega = :fechaentrega")
    , @NamedQuery(name = "Epp.findByConformidad", query = "SELECT e FROM Epp e WHERE e.conformidad = :conformidad")
    , @NamedQuery(name = "Epp.findByObservacion", query = "SELECT e FROM Epp e WHERE e.observacion = :observacion")})
public class Epp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codepp")
    private Integer codepp;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 90)
    @Column(name = "nombreepp")
    private String nombreepp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stockactual")
    private int stockactual;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "tallaepp")
    private String tallaepp;
    //@Lob
    @Column(name = "imagenepp")
    private byte[] imagenepp;
    @Size(max = 100)
    @Column(name = "rutaimagenepp")
    private String rutaimagenepp;
    @Column(name = "motivoentrega")
    private Boolean motivoentrega;
    @Column(name = "fechaentrega")
    @Temporal(TemporalType.DATE)
    private Date fechaentrega;
    @Column(name = "conformidad")
    private Boolean conformidad;
    @Size(max = 60)
    @Column(name = "observacion")
    private String observacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codepp", fetch = FetchType.LAZY)
    private List<Detalleregistro> detalleregistroList;

    public Epp() {
    }

    public Epp(Integer codepp) {
        this.codepp = codepp;
    }

    public Epp(Integer codepp, String nombreepp, int stockactual, String tallaepp) {
        this.codepp = codepp;
        this.nombreepp = nombreepp;
        this.stockactual = stockactual;
        this.tallaepp = tallaepp;
    }

    public Integer getCodepp() {
        return codepp;
    }

    public void setCodepp(Integer codepp) {
        this.codepp = codepp;
    }

    public String getNombreepp() {
        return nombreepp;
    }

    public void setNombreepp(String nombreepp) {
        this.nombreepp = nombreepp;
    }

    public int getStockactual() {
        return stockactual;
    }

    public void setStockactual(int stockactual) {
        this.stockactual = stockactual;
    }

    public String getTallaepp() {
        return tallaepp;
    }

    public void setTallaepp(String tallaepp) {
        this.tallaepp = tallaepp;
    }

    public byte[] getImagenepp() {
        return imagenepp;
    }

    public void setImagenepp(byte[] imagenepp) {
        this.imagenepp = imagenepp;
    }

    public String getRutaimagenepp() {
        return rutaimagenepp;
    }

    public void setRutaimagenepp(String rutaimagenepp) {
        this.rutaimagenepp = rutaimagenepp;
    }

    public Boolean getMotivoentrega() {
        return motivoentrega;
    }

    public void setMotivoentrega(Boolean motivoentrega) {
        this.motivoentrega = motivoentrega;
    }

    public Date getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(Date fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public Boolean getConformidad() {
        return conformidad;
    }

    public void setConformidad(Boolean conformidad) {
        this.conformidad = conformidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @XmlTransient
    @JsonIgnore
    public List<Detalleregistro> getDetalleregistroList() {
        return detalleregistroList;
    }

    public void setDetalleregistroList(List<Detalleregistro> detalleregistroList) {
        this.detalleregistroList = detalleregistroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codepp != null ? codepp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Epp)) {
            return false;
        }
        Epp other = (Epp) object;
        if ((this.codepp == null && other.codepp != null) || (this.codepp != null && !this.codepp.equals(other.codepp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.Epp[ codepp=" + codepp + " ]";
    }

}
