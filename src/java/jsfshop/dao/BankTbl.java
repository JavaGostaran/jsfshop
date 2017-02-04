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
@Table(name = "BankTbl")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "BankTbl.findAll", query = "SELECT b FROM BankTbl b"),
    @NamedQuery(name = "BankTbl.findByIdBank", query = "SELECT b FROM BankTbl b WHERE b.idBank = :idBank"),
    @NamedQuery(name = "BankTbl.findByTitleBank", query = "SELECT b FROM BankTbl b WHERE b.titleBank = :titleBank")
})
public class BankTbl implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBank")
    private Short idBank;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "titleBank")
    private String titleBank;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bankTblidBank")
    private List<BankAttributes> bankAttributesList;

    public BankTbl()
    {
    }

    public BankTbl(Short idBank)
    {
        this.idBank = idBank;
    }

    public BankTbl(Short idBank, String titleBank)
    {
        this.idBank = idBank;
        this.titleBank = titleBank;
    }

    public Short getIdBank()
    {
        return idBank;
    }

    public void setIdBank(Short idBank)
    {
        this.idBank = idBank;
    }

    public String getTitleBank()
    {
        return titleBank;
    }

    public void setTitleBank(String titleBank)
    {
        this.titleBank = titleBank;
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
        hash += (idBank != null ? idBank.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BankTbl))
        {
            return false;
        }
        BankTbl other = (BankTbl) object;
        if ((this.idBank == null && other.idBank != null) || (this.idBank != null && !this.idBank.equals(other.idBank)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.BankTbl[ idBank=" + idBank + " ]";
    }
    
}
