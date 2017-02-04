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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author j2ee1
 */
@Entity
@Table(name = "BankAttributeTypeTbl")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "BankAttributeTypeTbl.findAll", query = "SELECT b FROM BankAttributeTypeTbl b"),
    @NamedQuery(name = "BankAttributeTypeTbl.findByIdBankAttributeType", query = "SELECT b FROM BankAttributeTypeTbl b WHERE b.idBankAttributeType = :idBankAttributeType"),
    @NamedQuery(name = "BankAttributeTypeTbl.findByTitleBankAttributeType", query = "SELECT b FROM BankAttributeTypeTbl b WHERE b.titleBankAttributeType = :titleBankAttributeType")
})
public class BankAttributeTypeTbl implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBankAttributeType")
    private Short idBankAttributeType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "titleBankAttributeType")
    private String titleBankAttributeType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bankAttributeTypeTblidBankAttributeType")
    private List<BankAttributes> bankAttributesList;

    public BankAttributeTypeTbl()
    {
    }

    public BankAttributeTypeTbl(Short idBankAttributeType)
    {
        this.idBankAttributeType = idBankAttributeType;
    }

    public BankAttributeTypeTbl(Short idBankAttributeType, String titleBankAttributeType)
    {
        this.idBankAttributeType = idBankAttributeType;
        this.titleBankAttributeType = titleBankAttributeType;
    }

    public Short getIdBankAttributeType()
    {
        return idBankAttributeType;
    }

    public void setIdBankAttributeType(Short idBankAttributeType)
    {
        this.idBankAttributeType = idBankAttributeType;
    }

    public String getTitleBankAttributeType()
    {
        return titleBankAttributeType;
    }

    public void setTitleBankAttributeType(String titleBankAttributeType)
    {
        this.titleBankAttributeType = titleBankAttributeType;
    }

    @XmlTransient
    public List<BankAttributes> getBankAttributesList()
    {
        return bankAttributesList;
    }

    public void setBankAttributesList(List<BankAttributes> bankAttributesList)
    {
        this.bankAttributesList = bankAttributesList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idBankAttributeType != null ? idBankAttributeType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BankAttributeTypeTbl))
        {
            return false;
        }
        BankAttributeTypeTbl other = (BankAttributeTypeTbl) object;
        if ((this.idBankAttributeType == null && other.idBankAttributeType != null) || (this.idBankAttributeType != null && !this.idBankAttributeType.equals(other.idBankAttributeType)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.BankAttributeTypeTbl[ idBankAttributeType=" + idBankAttributeType + " ]";
    }
    
}
