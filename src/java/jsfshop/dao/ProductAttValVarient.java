/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.dao;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author j2ee1
 */
@Entity
@Table(name = "ProductAttValVarient")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ProductAttValVarient.findAll", query = "SELECT p FROM ProductAttValVarient p"),
    @NamedQuery(name = "ProductAttValVarient.findByIdProductAttValVarient", query = "SELECT p FROM ProductAttValVarient p WHERE p.idProductAttValVarient = :idProductAttValVarient"),
    @NamedQuery(name = "ProductAttValVarient.findByCategoryID", query = "SELECT p FROM ProductAttValVarient p WHERE p.categoryID = :categoryID"),
    @NamedQuery(name = "ProductAttValVarient.findByUpdatePrice", query = "SELECT p FROM ProductAttValVarient p WHERE p.updatePrice = :updatePrice"),
    @NamedQuery(name = "ProductAttValVarient.findByUpdateCount", query = "SELECT p FROM ProductAttValVarient p WHERE p.updateCount = :updateCount")
})
public class ProductAttValVarient implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProductAttValVarient")
    private Long idProductAttValVarient;
    @Column(name = "categoryID")
    private BigInteger categoryID;
    @Column(name = "updatePrice")
    private BigInteger updatePrice;
    @Column(name = "updateCount")
    private Integer updateCount;
    @JoinColumn(name = "ProductTbl_idProduct", referencedColumnName = "idProduct")
    @ManyToOne(optional = false)
    private ProductTbl productTblidProduct;
    @JoinColumn(name = "AttributeValues_idAttributeValues", referencedColumnName = "idAttributeValues")
    @ManyToOne(optional = false)
    private AttributeValues attributeValuesidAttributeValues;

    public ProductAttValVarient()
    {
    }

    public ProductAttValVarient(Long idProductAttValVarient)
    {
        this.idProductAttValVarient = idProductAttValVarient;
    }

    public Long getIdProductAttValVarient()
    {
        return idProductAttValVarient;
    }

    public void setIdProductAttValVarient(Long idProductAttValVarient)
    {
        this.idProductAttValVarient = idProductAttValVarient;
    }

    public BigInteger getCategoryID()
    {
        return categoryID;
    }

    public void setCategoryID(BigInteger categoryID)
    {
        this.categoryID = categoryID;
    }

    public BigInteger getUpdatePrice()
    {
        return updatePrice;
    }

    public void setUpdatePrice(BigInteger updatePrice)
    {
        this.updatePrice = updatePrice;
    }

    public Integer getUpdateCount()
    {
        return updateCount;
    }

    public void setUpdateCount(Integer updateCount)
    {
        this.updateCount = updateCount;
    }

    public ProductTbl getProductTblidProduct()
    {
        return productTblidProduct;
    }

    public void setProductTblidProduct(ProductTbl productTblidProduct)
    {
        this.productTblidProduct = productTblidProduct;
    }

    public AttributeValues getAttributeValuesidAttributeValues()
    {
        return attributeValuesidAttributeValues;
    }

    public void setAttributeValuesidAttributeValues(AttributeValues attributeValuesidAttributeValues)
    {
        this.attributeValuesidAttributeValues = attributeValuesidAttributeValues;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idProductAttValVarient != null ? idProductAttValVarient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductAttValVarient))
        {
            return false;
        }
        ProductAttValVarient other = (ProductAttValVarient) object;
        if ((this.idProductAttValVarient == null && other.idProductAttValVarient != null) || (this.idProductAttValVarient != null && !this.idProductAttValVarient.equals(other.idProductAttValVarient)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.ProductAttValVarient[ idProductAttValVarient=" + idProductAttValVarient + " ]";
    }
    
}
