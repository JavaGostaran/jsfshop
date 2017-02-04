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
@Table(name = "GarantyType")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "GarantyType.findAll", query = "SELECT g FROM GarantyType g"),
    @NamedQuery(name = "GarantyType.findByIdGarantyType", query = "SELECT g FROM GarantyType g WHERE g.idGarantyType = :idGarantyType"),
    @NamedQuery(name = "GarantyType.findByTitleGarantyType", query = "SELECT g FROM GarantyType g WHERE g.titleGarantyType = :titleGarantyType")
})
public class GarantyType implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGarantyType")
    private Long idGarantyType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "titleGarantyType")
    private String titleGarantyType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "garantyTypeidGarantyType")
    private List<ProductTbl> productTblList;

    public GarantyType()
    {
    }

    public GarantyType(Long idGarantyType)
    {
        this.idGarantyType = idGarantyType;
    }

    public GarantyType(Long idGarantyType, String titleGarantyType)
    {
        this.idGarantyType = idGarantyType;
        this.titleGarantyType = titleGarantyType;
    }

    public Long getIdGarantyType()
    {
        return idGarantyType;
    }

    public void setIdGarantyType(Long idGarantyType)
    {
        this.idGarantyType = idGarantyType;
    }

    public String getTitleGarantyType()
    {
        return titleGarantyType;
    }

    public void setTitleGarantyType(String titleGarantyType)
    {
        this.titleGarantyType = titleGarantyType;
    }

    @XmlTransient
    public List<ProductTbl> getProductTblList()
    {
        return productTblList;
    }

    public void setProductTblList(List<ProductTbl> productTblList)
    {
        this.productTblList = productTblList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idGarantyType != null ? idGarantyType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GarantyType))
        {
            return false;
        }
        GarantyType other = (GarantyType) object;
        if ((this.idGarantyType == null && other.idGarantyType != null) || (this.idGarantyType != null && !this.idGarantyType.equals(other.idGarantyType)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.GarantyType[ idGarantyType=" + idGarantyType + " ]";
    }
    
}
