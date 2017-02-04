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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author j2ee1
 */
@Entity
@Table(name = "ProductDiscountTbl")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ProductDiscountTbl.findAll", query = "SELECT p FROM ProductDiscountTbl p"),
    @NamedQuery(name = "ProductDiscountTbl.findByIdProductDiscount", query = "SELECT p FROM ProductDiscountTbl p WHERE p.idProductDiscount = :idProductDiscount"),
    @NamedQuery(name = "ProductDiscountTbl.findByDiscountValue", query = "SELECT p FROM ProductDiscountTbl p WHERE p.discountValue = :discountValue"),
    @NamedQuery(name = "ProductDiscountTbl.findByIsActive", query = "SELECT p FROM ProductDiscountTbl p WHERE p.isActive = :isActive")
})
public class ProductDiscountTbl implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProductDiscount")
    private Integer idProductDiscount;
    @Column(name = "discountValue")
    private Short discountValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isActive")
    private boolean isActive;
    @JoinColumn(name = "ProductTbl_idProduct", referencedColumnName = "idProduct")
    @ManyToOne(optional = false)
    private ProductTbl productTblidProduct;
    @JoinColumn(name = "DiscountType_idDiscountType", referencedColumnName = "idDiscountType")
    @ManyToOne(optional = false)
    private DiscountType discountTypeidDiscountType;

    public ProductDiscountTbl()
    {
    }

    public ProductDiscountTbl(Integer idProductDiscount)
    {
        this.idProductDiscount = idProductDiscount;
    }

    public ProductDiscountTbl(Integer idProductDiscount, boolean isActive)
    {
        this.idProductDiscount = idProductDiscount;
        this.isActive = isActive;
    }

    public Integer getIdProductDiscount()
    {
        return idProductDiscount;
    }

    public void setIdProductDiscount(Integer idProductDiscount)
    {
        this.idProductDiscount = idProductDiscount;
    }

    public Short getDiscountValue()
    {
        return discountValue;
    }

    public void setDiscountValue(Short discountValue)
    {
        this.discountValue = discountValue;
    }

    public boolean getIsActive()
    {
        return isActive;
    }

    public void setIsActive(boolean isActive)
    {
        this.isActive = isActive;
    }

    public ProductTbl getProductTblidProduct()
    {
        return productTblidProduct;
    }

    public void setProductTblidProduct(ProductTbl productTblidProduct)
    {
        this.productTblidProduct = productTblidProduct;
    }

    public DiscountType getDiscountTypeidDiscountType()
    {
        return discountTypeidDiscountType;
    }

    public void setDiscountTypeidDiscountType(DiscountType discountTypeidDiscountType)
    {
        this.discountTypeidDiscountType = discountTypeidDiscountType;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idProductDiscount != null ? idProductDiscount.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductDiscountTbl))
        {
            return false;
        }
        ProductDiscountTbl other = (ProductDiscountTbl) object;
        if ((this.idProductDiscount == null && other.idProductDiscount != null) || (this.idProductDiscount != null && !this.idProductDiscount.equals(other.idProductDiscount)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.ProductDiscountTbl[ idProductDiscount=" + idProductDiscount + " ]";
    }
    
}
