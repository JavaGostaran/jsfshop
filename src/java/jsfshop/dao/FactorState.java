/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author j2ee1
 */
@Entity
@Table(name = "FactorState")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "FactorState.findAll", query = "SELECT f FROM FactorState f"),
    @NamedQuery(name = "FactorState.findByIdFactorState", query = "SELECT f FROM FactorState f WHERE f.idFactorState = :idFactorState"),
    @NamedQuery(name = "FactorState.findByTitleFactorState", query = "SELECT f FROM FactorState f WHERE f.titleFactorState = :titleFactorState")
})
public class FactorState implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFactorState")
    private Short idFactorState;
    @Size(max = 512)
    @Column(name = "titleFactorState")
    private String titleFactorState;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factorStateidFactorState")
    private List<FactorBankAttribute> factorBankAttributeList;

    public FactorState()
    {
    }

    public FactorState(Short idFactorState)
    {
        this.idFactorState = idFactorState;
    }

    public Short getIdFactorState()
    {
        return idFactorState;
    }

    public void setIdFactorState(Short idFactorState)
    {
        this.idFactorState = idFactorState;
    }

    public String getTitleFactorState()
    {
        return titleFactorState;
    }

    public void setTitleFactorState(String titleFactorState)
    {
        this.titleFactorState = titleFactorState;
    }

    @XmlTransient
    public List<FactorBankAttribute> getFactorBankAttributeList()
    {
        return factorBankAttributeList;
    }

    public void setFactorBankAttributeList(List<FactorBankAttribute> factorBankAttributeList)
    {
        this.factorBankAttributeList = factorBankAttributeList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idFactorState != null ? idFactorState.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactorState))
        {
            return false;
        }
        FactorState other = (FactorState) object;
        if ((this.idFactorState == null && other.idFactorState != null) || (this.idFactorState != null && !this.idFactorState.equals(other.idFactorState)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.FactorState[ idFactorState=" + idFactorState + " ]";
    }
    
}
