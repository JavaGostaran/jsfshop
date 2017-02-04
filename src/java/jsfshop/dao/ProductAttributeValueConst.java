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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author j2ee1
 */
@Entity
@Table(name = "ProductAttributeValueConst")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ProductAttributeValueConst.findAll", query = "SELECT p FROM ProductAttributeValueConst p"),
    @NamedQuery(name = "ProductAttributeValueConst.findByIdProductAttributeValueConst", query = "SELECT p FROM ProductAttributeValueConst p WHERE p.idProductAttributeValueConst = :idProductAttributeValueConst")
})
public class ProductAttributeValueConst implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProductAttributeValueConst")
    private Long idProductAttributeValueConst;
    @JoinColumn(name = "ProductTbl_idProduct", referencedColumnName = "idProduct")
    @ManyToOne(optional = false)
    private ProductTbl productTblidProduct;
    @JoinColumn(name = "AttributeValues_idAttributeValues", referencedColumnName = "idAttributeValues")
    @ManyToOne(optional = false)
    private AttributeValues attributeValuesidAttributeValues;

    public ProductAttributeValueConst()
    {
    }

    public ProductAttributeValueConst(Long idProductAttributeValueConst)
    {
        this.idProductAttributeValueConst = idProductAttributeValueConst;
    }

    public Long getIdProductAttributeValueConst()
    {
        return idProductAttributeValueConst;
    }

    public void setIdProductAttributeValueConst(Long idProductAttributeValueConst)
    {
        this.idProductAttributeValueConst = idProductAttributeValueConst;
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
        hash += (idProductAttributeValueConst != null ? idProductAttributeValueConst.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductAttributeValueConst))
        {
            return false;
        }
        ProductAttributeValueConst other = (ProductAttributeValueConst) object;
        if ((this.idProductAttributeValueConst == null && other.idProductAttributeValueConst != null) || (this.idProductAttributeValueConst != null && !this.idProductAttributeValueConst.equals(other.idProductAttributeValueConst)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.ProductAttributeValueConst[ idProductAttributeValueConst=" + idProductAttributeValueConst + " ]";
    }
    
}
