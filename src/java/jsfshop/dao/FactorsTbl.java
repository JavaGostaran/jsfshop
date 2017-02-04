/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.dao;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author j2ee1
 */
@Entity
@Table(name = "FactorsTbl")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "FactorsTbl.findAll", query = "SELECT f FROM FactorsTbl f"),
    @NamedQuery(name = "FactorsTbl.findByIdFactorsTbl", query = "SELECT f FROM FactorsTbl f WHERE f.idFactorsTbl = :idFactorsTbl"),
    @NamedQuery(name = "FactorsTbl.findByRegisterDate", query = "SELECT f FROM FactorsTbl f WHERE f.registerDate = :registerDate"),
    @NamedQuery(name = "FactorsTbl.findByFactorPostalCode", query = "SELECT f FROM FactorsTbl f WHERE f.factorPostalCode = :factorPostalCode"),
    @NamedQuery(name = "FactorsTbl.findByFactorNameAndFamily", query = "SELECT f FROM FactorsTbl f WHERE f.factorNameAndFamily = :factorNameAndFamily"),
    @NamedQuery(name = "FactorsTbl.findByPostCost", query = "SELECT f FROM FactorsTbl f WHERE f.postCost = :postCost"),
    @NamedQuery(name = "FactorsTbl.findByEmphericalCost", query = "SELECT f FROM FactorsTbl f WHERE f.emphericalCost = :emphericalCost"),
    @NamedQuery(name = "FactorsTbl.findByIsSolld", query = "SELECT f FROM FactorsTbl f WHERE f.isSolld = :isSolld")
})
public class FactorsTbl implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFactorsTbl")
    private Long idFactorsTbl;
    @Column(name = "registerDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    @Size(max = 45)
    @Column(name = "factorPostalCode")
    private String factorPostalCode;
    @Lob
    @Size(max = 16777215)
    @Column(name = "factorAddress")
    private String factorAddress;
    @Size(max = 512)
    @Column(name = "factorNameAndFamily")
    private String factorNameAndFamily;
    @Column(name = "postCost")
    private Integer postCost;
    @Column(name = "emphericalCost")
    private Integer emphericalCost;
    @Lob
    @Size(max = 65535)
    @Column(name = "descriptionFactor")
    private String descriptionFactor;
    @Column(name = "isSolld")
    private Boolean isSolld;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factorsTblidFactorsTbl")
    private List<FactorBankAttribute> factorBankAttributeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factorsTblidFactorsTbl")
    private List<ProductsFactorTbl> productsFactorTblList;
    @JoinColumn(name = "user_USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private User userUSERID;

    public FactorsTbl()
    {
    }

    public FactorsTbl(Long idFactorsTbl)
    {
        this.idFactorsTbl = idFactorsTbl;
    }

    public Long getIdFactorsTbl()
    {
        return idFactorsTbl;
    }

    public void setIdFactorsTbl(Long idFactorsTbl)
    {
        this.idFactorsTbl = idFactorsTbl;
    }

    public Date getRegisterDate()
    {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate)
    {
        this.registerDate = registerDate;
    }

    public String getFactorPostalCode()
    {
        return factorPostalCode;
    }

    public void setFactorPostalCode(String factorPostalCode)
    {
        this.factorPostalCode = factorPostalCode;
    }

    public String getFactorAddress()
    {
        return factorAddress;
    }

    public void setFactorAddress(String factorAddress)
    {
        this.factorAddress = factorAddress;
    }

    public String getFactorNameAndFamily()
    {
        return factorNameAndFamily;
    }

    public void setFactorNameAndFamily(String factorNameAndFamily)
    {
        this.factorNameAndFamily = factorNameAndFamily;
    }

    public Integer getPostCost()
    {
        return postCost;
    }

    public void setPostCost(Integer postCost)
    {
        this.postCost = postCost;
    }

    public Integer getEmphericalCost()
    {
        return emphericalCost;
    }

    public void setEmphericalCost(Integer emphericalCost)
    {
        this.emphericalCost = emphericalCost;
    }

    public String getDescriptionFactor()
    {
        return descriptionFactor;
    }

    public void setDescriptionFactor(String descriptionFactor)
    {
        this.descriptionFactor = descriptionFactor;
    }

    public Boolean getIsSolld()
    {
        return isSolld;
    }

    public void setIsSolld(Boolean isSolld)
    {
        this.isSolld = isSolld;
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

    @XmlTransient
    public List<ProductsFactorTbl> getProductsFactorTblList()
    {
        return productsFactorTblList;
    }

    public void setProductsFactorTblList(List<ProductsFactorTbl> productsFactorTblList)
    {
        this.productsFactorTblList = productsFactorTblList;
    }

    public User getUserUSERID()
    {
        return userUSERID;
    }

    public void setUserUSERID(User userUSERID)
    {
        this.userUSERID = userUSERID;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idFactorsTbl != null ? idFactorsTbl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactorsTbl))
        {
            return false;
        }
        FactorsTbl other = (FactorsTbl) object;
        if ((this.idFactorsTbl == null && other.idFactorsTbl != null) || (this.idFactorsTbl != null && !this.idFactorsTbl.equals(other.idFactorsTbl)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.FactorsTbl[ idFactorsTbl=" + idFactorsTbl + " ]";
    }
    
}
