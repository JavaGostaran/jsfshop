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
@Table(name = "ProductAttribute")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ProductAttribute.findAll", query = "SELECT p FROM ProductAttribute p"),
    @NamedQuery(name = "ProductAttribute.findByIdProductAttribute", query = "SELECT p FROM ProductAttribute p WHERE p.idProductAttribute = :idProductAttribute"),
    @NamedQuery(name = "ProductAttribute.findByIsSelectable", query = "SELECT p FROM ProductAttribute p WHERE p.isSelectable = :isSelectable")
})
public class ProductAttribute implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProductAttribute")
    private Long idProductAttribute;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isSelectable")
    private boolean isSelectable;
    @JoinColumn(name = "ProductTbl_idProduct", referencedColumnName = "idProduct")
    @ManyToOne(optional = false)
    private ProductTbl productTblidProduct;
    @JoinColumn(name = "AttributesTbl_idAttributes", referencedColumnName = "idAttributes")
    @ManyToOne(optional = false)
    private AttributesTbl attributesTblidAttributes;

    public ProductAttribute()
    {
    }

    public ProductAttribute(Long idProductAttribute)
    {
        this.idProductAttribute = idProductAttribute;
    }

    public ProductAttribute(Long idProductAttribute, boolean isSelectable)
    {
        this.idProductAttribute = idProductAttribute;
        this.isSelectable = isSelectable;
    }

    public Long getIdProductAttribute()
    {
        return idProductAttribute;
    }

    public void setIdProductAttribute(Long idProductAttribute)
    {
        this.idProductAttribute = idProductAttribute;
    }

    public boolean getIsSelectable()
    {
        return isSelectable;
    }

    public void setIsSelectable(boolean isSelectable)
    {
        this.isSelectable = isSelectable;
    }

    public ProductTbl getProductTblidProduct()
    {
        return productTblidProduct;
    }

    public void setProductTblidProduct(ProductTbl productTblidProduct)
    {
        this.productTblidProduct = productTblidProduct;
    }

    public AttributesTbl getAttributesTblidAttributes()
    {
        return attributesTblidAttributes;
    }

    public void setAttributesTblidAttributes(AttributesTbl attributesTblidAttributes)
    {
        this.attributesTblidAttributes = attributesTblidAttributes;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idProductAttribute != null ? idProductAttribute.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductAttribute))
        {
            return false;
        }
        ProductAttribute other = (ProductAttribute) object;
        if ((this.idProductAttribute == null && other.idProductAttribute != null) || (this.idProductAttribute != null && !this.idProductAttribute.equals(other.idProductAttribute)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.ProductAttribute[ idProductAttribute=" + idProductAttribute + " ]";
    }
    
}
