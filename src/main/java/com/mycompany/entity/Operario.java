/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.entity;

import java.io.Serializable;
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
@Table(name = "operario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operario.findAll", query = "SELECT o FROM Operario o")
    , @NamedQuery(name = "Operario.findByCodoperario", query = "SELECT o FROM Operario o WHERE o.codoperario = :codoperario")
    , @NamedQuery(name = "Operario.findByNombresoperario", query = "SELECT o FROM Operario o WHERE o.nombresoperario = :nombresoperario")
    , @NamedQuery(name = "Operario.findByApellidosoperario", query = "SELECT o FROM Operario o WHERE o.apellidosoperario = :apellidosoperario")
    , @NamedQuery(name = "Operario.findByDnioperaio", query = "SELECT o FROM Operario o WHERE o.dnioperaio = :dnioperaio")
    , @NamedQuery(name = "Operario.findByPuestooperario", query = "SELECT o FROM Operario o WHERE o.puestooperario = :puestooperario")
    , @NamedQuery(name = "Operario.findByRutaimagenoperario", query = "SELECT o FROM Operario o WHERE o.rutaimagenoperario = :rutaimagenoperario")
    , @NamedQuery(name = "Operario.findByAreaoperario", query = "SELECT o FROM Operario o WHERE o.areaoperario = :areaoperario")
    , @NamedQuery(name = "Operario.findByPlantaoperario", query = "SELECT o FROM Operario o WHERE o.plantaoperario = :plantaoperario")})
public class Operario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codoperario")
    private Integer codoperario;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombresoperario")
    private String nombresoperario;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "apellidosoperario")
    private String apellidosoperario;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "dnioperaio")
    private String dnioperaio;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "puestooperario")
    private String puestooperario;

    //@Lob
    @Column(name = "imagenoperario")
    private byte[] imagenoperario;

    @Size(max = 60)
    @Column(name = "rutaimagenoperario")
    private String rutaimagenoperario;

    @Size(max = 20)
    @Column(name = "areaoperario")
    private String areaoperario;

    @Size(max = 20)
    @Column(name = "plantaoperario")
    private String plantaoperario;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codoperario", fetch = FetchType.LAZY)
    private List<Registro> registroList;

    public Operario() {
    }

    public Operario(Integer codoperario) {
        this.codoperario = codoperario;
    }

    public Operario(Integer codoperario, String nombresoperario, String apellidosoperario, String dnioperaio, String puestooperario) {
        this.codoperario = codoperario;
        this.nombresoperario = nombresoperario;
        this.apellidosoperario = apellidosoperario;
        this.dnioperaio = dnioperaio;
        this.puestooperario = puestooperario;
    }

    public Integer getCodoperario() {
        return codoperario;
    }

    public void setCodoperario(Integer codoperario) {
        this.codoperario = codoperario;
    }

    public String getNombresoperario() {
        return nombresoperario;
    }

    public void setNombresoperario(String nombresoperario) {
        this.nombresoperario = nombresoperario;
    }

    public String getApellidosoperario() {
        return apellidosoperario;
    }

    public void setApellidosoperario(String apellidosoperario) {
        this.apellidosoperario = apellidosoperario;
    }

    public String getDnioperaio() {
        return dnioperaio;
    }

    public void setDnioperaio(String dnioperaio) {
        this.dnioperaio = dnioperaio;
    }

    public String getPuestooperario() {
        return puestooperario;
    }

    public void setPuestooperario(String puestooperario) {
        this.puestooperario = puestooperario;
    }

    public byte[] getImagenoperario() {
        return imagenoperario;
    }

    public void setImagenoperario(byte[] imagenoperario) {
        this.imagenoperario = imagenoperario;
    }

    public String getRutaimagenoperario() {
        return rutaimagenoperario;
    }

    public void setRutaimagenoperario(String rutaimagenoperario) {
        this.rutaimagenoperario = rutaimagenoperario;
    }

    public String getAreaoperario() {
        return areaoperario;
    }

    public void setAreaoperario(String areaoperario) {
        this.areaoperario = areaoperario;
    }

    public String getPlantaoperario() {
        return plantaoperario;
    }

    public void setPlantaoperario(String plantaoperario) {
        this.plantaoperario = plantaoperario;
    }

    @XmlTransient
    @JsonIgnore
    public List<Registro> getRegistroList() {
        return registroList;
    }

    public void setRegistroList(List<Registro> registroList) {
        this.registroList = registroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codoperario != null ? codoperario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operario)) {
            return false;
        }
        Operario other = (Operario) object;
        if ((this.codoperario == null && other.codoperario != null) || (this.codoperario != null && !this.codoperario.equals(other.codoperario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.Operario[ codoperario=" + codoperario + " ]";
    }

}
