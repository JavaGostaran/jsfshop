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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author j2ee1
 */
@Entity
@Table(name = "BankAttributes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "BankAttributes.findAll", query = "SELECT b FROM BankAttributes b"),
    @NamedQuery(name = "BankAttributes.findByIdBankAttributes", query = "SELECT b FROM BankAttributes b WHERE b.idBankAttributes = :idBankAttributes")
})
public class BankAttributes implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBankAttributes")
    private Long idBankAttributes;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "titleBankAttributes")
    private String titleBankAttributes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bankAttributesidBankAttributes")
    private List<FactorBankAttribute> factorBankAttributeList;
    @JoinColumn(name = "BankTbl_idBank", referencedColumnName = "idBank")
    @ManyToOne(optional = false)
    private BankTbl bankTblidBank;
    @JoinColumn(name = "BankAttributeTypeTbl_idBankAttributeType", referencedColumnName = "idBankAttributeType")
    @ManyToOne(optional = false)
    private BankAttributeTypeTbl bankAttributeTypeTblidBankAttributeType;

    public BankAttributes()
    {
    }

    public BankAttributes(Long idBankAttributes)
    {
        this.idBankAttributes = idBankAttributes;
    }

    public BankAttributes(Long idBankAttributes, String titleBankAttributes)
    {
        this.idBankAttributes = idBankAttributes;
        this.titleBankAttributes = titleBankAttributes;
    }

    public Long getIdBankAttributes()
    {
        return idBankAttributes;
    }

    public void setIdBankAttributes(Long idBankAttributes)
    {
        this.idBankAttributes = idBankAttributes;
    }

    public String getTitleBankAttributes()
    {
        return titleBankAttributes;
    }

    public void setTitleBankAttributes(String titleBankAttributes)
    {
        this.titleBankAttributes = titleBankAttributes;
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

    public BankTbl getBankTblidBank()
    {
        return bankTblidBank;
    }

    public void setBankTblidBank(BankTbl bankTblidBank)
    {
        this.bankTblidBank = bankTblidBank;
    }

    public BankAttributeTypeTbl getBankAttributeTypeTblidBankAttributeType()
    {
        return bankAttributeTypeTblidBankAttributeType;
    }

    public void setBankAttributeTypeTblidBankAttributeType(BankAttributeTypeTbl bankAttributeTypeTblidBankAttributeType)
    {
        this.bankAttributeTypeTblidBankAttributeType = bankAttributeTypeTblidBankAttributeType;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idBankAttributes != null ? idBankAttributes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BankAttributes))
        {
            return false;
        }
        BankAttributes other = (BankAttributes) object;
        if ((this.idBankAttributes == null && other.idBankAttributes != null) || (this.idBankAttributes != null && !this.idBankAttributes.equals(other.idBankAttributes)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.BankAttributes[ idBankAttributes=" + idBankAttributes + " ]";
    }
    
}
