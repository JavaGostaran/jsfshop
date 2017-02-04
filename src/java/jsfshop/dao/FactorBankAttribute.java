/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author j2ee1
 */
@Entity
@Table(name = "FactorBankAttribute")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "FactorBankAttribute.findAll", query = "SELECT f FROM FactorBankAttribute f"),
    @NamedQuery(name = "FactorBankAttribute.findByIdFactorBankAttribute", query = "SELECT f FROM FactorBankAttribute f WHERE f.idFactorBankAttribute = :idFactorBankAttribute")
})
public class FactorBankAttribute implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFactorBankAttribute")
    private Long idFactorBankAttribute;
    @Lob
    @Size(max = 16777215)
    @Column(name = "factorBankAttributeValue")
    private String factorBankAttributeValue;
    @JoinColumn(name = "FactorsTbl_idFactorsTbl", referencedColumnName = "idFactorsTbl")
    @ManyToOne(optional = false)
    private FactorsTbl factorsTblidFactorsTbl;
    @JoinColumn(name = "FactorState_idFactorState", referencedColumnName = "idFactorState")
    @ManyToOne(optional = false)
    private FactorState factorStateidFactorState;
    @JoinColumn(name = "BankAttributes_idBankAttributes", referencedColumnName = "idBankAttributes")
    @ManyToOne(optional = false)
    private BankAttributes bankAttributesidBankAttributes;

    public FactorBankAttribute()
    {
    }

    public FactorBankAttribute(Long idFactorBankAttribute)
    {
        this.idFactorBankAttribute = idFactorBankAttribute;
    }

    public Long getIdFactorBankAttribute()
    {
        return idFactorBankAttribute;
    }

    public void setIdFactorBankAttribute(Long idFactorBankAttribute)
    {
        this.idFactorBankAttribute = idFactorBankAttribute;
    }

    public String getFactorBankAttributeValue()
    {
        return factorBankAttributeValue;
    }

    public void setFactorBankAttributeValue(String factorBankAttributeValue)
    {
        this.factorBankAttributeValue = factorBankAttributeValue;
    }

    public FactorsTbl getFactorsTblidFactorsTbl()
    {
        return factorsTblidFactorsTbl;
    }

    public void setFactorsTblidFactorsTbl(FactorsTbl factorsTblidFactorsTbl)
    {
        this.factorsTblidFactorsTbl = factorsTblidFactorsTbl;
    }

    public FactorState getFactorStateidFactorState()
    {
        return factorStateidFactorState;
    }

    public void setFactorStateidFactorState(FactorState factorStateidFactorState)
    {
        this.factorStateidFactorState = factorStateidFactorState;
    }

    public BankAttributes getBankAttributesidBankAttributes()
    {
        return bankAttributesidBankAttributes;
    }

    public void setBankAttributesidBankAttributes(BankAttributes bankAttributesidBankAttributes)
    {
        this.bankAttributesidBankAttributes = bankAttributesidBankAttributes;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idFactorBankAttribute != null ? idFactorBankAttribute.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactorBankAttribute))
        {
            return false;
        }
        FactorBankAttribute other = (FactorBankAttribute) object;
        if ((this.idFactorBankAttribute == null && other.idFactorBankAttribute != null) || (this.idFactorBankAttribute != null && !this.idFactorBankAttribute.equals(other.idFactorBankAttribute)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.FactorBankAttribute[ idFactorBankAttribute=" + idFactorBankAttribute + " ]";
    }
    
}
