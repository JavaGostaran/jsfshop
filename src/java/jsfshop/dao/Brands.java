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
@Table(name = "Brands")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Brands.findAll", query = "SELECT b FROM Brands b"),
    @NamedQuery(name = "Brands.findByIdBrands", query = "SELECT b FROM Brands b WHERE b.idBrands = :idBrands"),
    @NamedQuery(name = "Brands.findByTitleBrand", query = "SELECT b FROM Brands b WHERE b.titleBrand = :titleBrand")
})
public class Brands implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBrands")
    private Long idBrands;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "titleBrand")
    private String titleBrand;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brandsidBrands")
    private List<ProductTbl> productTblList;

    public Brands()
    {
    }

    public Brands(Long idBrands)
    {
        this.idBrands = idBrands;
    }

    public Brands(Long idBrands, String titleBrand)
    {
        this.idBrands = idBrands;
        this.titleBrand = titleBrand;
    }

    public Long getIdBrands()
    {
        return idBrands;
    }

    public void setIdBrands(Long idBrands)
    {
        this.idBrands = idBrands;
    }

    public String getTitleBrand()
    {
        return titleBrand;
    }

    public void setTitleBrand(String titleBrand)
    {
        this.titleBrand = titleBrand;
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
        hash += (idBrands != null ? idBrands.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Brands))
        {
            return false;
        }
        Brands other = (Brands) object;
        if ((this.idBrands == null && other.idBrands != null) || (this.idBrands != null && !this.idBrands.equals(other.idBrands)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.Brands[ idBrands=" + idBrands + " ]";
    }
    
}
