/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guntel
 */
@Entity
@Table(name = "T_OSOBA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TOsoba.findAll", query = "SELECT t FROM TOsoba t")
    , @NamedQuery(name = "TOsoba.findById", query = "SELECT t FROM TOsoba t WHERE t.id = :id")
    , @NamedQuery(name = "TOsoba.findByMeno", query = "SELECT t FROM TOsoba t WHERE t.meno = :meno")
    , @NamedQuery(name = "TOsoba.findByNarodena", query = "SELECT t FROM TOsoba t WHERE t.narodena = :narodena")
    , @NamedQuery(name = "TOsoba.setTucko", query = "SELECT t FROM TOsoba t WHERE t.vaha is null")
    , @NamedQuery(name = "TOsoba.findByVaha", query = "SELECT t FROM TOsoba t WHERE t.vaha = :vaha")})
public class TOsoba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "MENO")
    private String meno;
    @Column(name = "NARODENA")
    @Temporal(TemporalType.DATE)
    private Date narodena;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VAHA")
    private Double vaha;

    public TOsoba() {
    }

    public TOsoba(Long id, String meno) {
        this.id = id;
        this.meno = meno;
    }

    
    
    public TOsoba(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public Date getNarodena() {
        return narodena;
    }

    public void setNarodena(Date narodena) {
        this.narodena = narodena;
    }

    public Double getVaha() {
        return vaha;
    }

    public void setVaha(Double vaha) {
        this.vaha = vaha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TOsoba)) {
            return false;
        }
        TOsoba other = (TOsoba) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TOsoba{" + "id=" + id + ", meno=" + meno + ", narodena=" + narodena + ", vaha=" + vaha + '}';
    }

    
    
}
