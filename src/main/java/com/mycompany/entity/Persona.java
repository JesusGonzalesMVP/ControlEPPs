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
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
    , @NamedQuery(name = "Persona.findByCodpersona", query = "SELECT p FROM Persona p WHERE p.codpersona = :codpersona")
    , @NamedQuery(name = "Persona.findByNombrespersona", query = "SELECT p FROM Persona p WHERE p.nombrespersona = :nombrespersona")
    , @NamedQuery(name = "Persona.findByApellidospersona", query = "SELECT p FROM Persona p WHERE p.apellidospersona = :apellidospersona")
    , @NamedQuery(name = "Persona.findByDnipersona", query = "SELECT p FROM Persona p WHERE p.dnipersona = :dnipersona")
    , @NamedQuery(name = "Persona.findByCelularpersona", query = "SELECT p FROM Persona p WHERE p.celularpersona = :celularpersona")
    , @NamedQuery(name = "Persona.findByDireccionpersona", query = "SELECT p FROM Persona p WHERE p.direccionpersona = :direccionpersona")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codpersona")
    private Integer codpersona;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombrespersona")
    private String nombrespersona;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "apellidospersona")
    private String apellidospersona;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "dnipersona")
    private String dnipersona;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "celularpersona")
    private String celularpersona;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "direccionpersona")
    private String direccionpersona;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codpersona", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;

    public Persona() {
    }

    public Persona(Integer codpersona) {
        this.codpersona = codpersona;
    }

    public Persona(Integer codpersona, String nombrespersona, String apellidospersona, String dnipersona, String celularpersona, String direccionpersona) {
        this.codpersona = codpersona;
        this.nombrespersona = nombrespersona;
        this.apellidospersona = apellidospersona;
        this.dnipersona = dnipersona;
        this.celularpersona = celularpersona;
        this.direccionpersona = direccionpersona;
    }

    public Integer getCodpersona() {
        return codpersona;
    }

    public void setCodpersona(Integer codpersona) {
        this.codpersona = codpersona;
    }

    public String getNombrespersona() {
        return nombrespersona;
    }

    public void setNombrespersona(String nombrespersona) {
        this.nombrespersona = nombrespersona;
    }

    public String getApellidospersona() {
        return apellidospersona;
    }

    public void setApellidospersona(String apellidospersona) {
        this.apellidospersona = apellidospersona;
    }

    public String getDnipersona() {
        return dnipersona;
    }

    public void setDnipersona(String dnipersona) {
        this.dnipersona = dnipersona;
    }

    public String getCelularpersona() {
        return celularpersona;
    }

    public void setCelularpersona(String celularpersona) {
        this.celularpersona = celularpersona;
    }

    public String getDireccionpersona() {
        return direccionpersona;
    }

    public void setDireccionpersona(String direccionpersona) {
        this.direccionpersona = direccionpersona;
    }

    @XmlTransient
    @JsonIgnore
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codpersona != null ? codpersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.codpersona == null && other.codpersona != null) || (this.codpersona != null && !this.codpersona.equals(other.codpersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.Persona[ codpersona=" + codpersona + " ]";
    }

}
