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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author j2ee1
 */
@Entity
@Table(name = "ProductsFactorTbl")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ProductsFactorTbl.findAll", query = "SELECT p FROM ProductsFactorTbl p"),
    @NamedQuery(name = "ProductsFactorTbl.findByProductsCount", query = "SELECT p FROM ProductsFactorTbl p WHERE p.productsCount = :productsCount"),
    @NamedQuery(name = "ProductsFactorTbl.findByProductsUnitPrice", query = "SELECT p FROM ProductsFactorTbl p WHERE p.productsUnitPrice = :productsUnitPrice"),
    @NamedQuery(name = "ProductsFactorTbl.findByProductsDiscountPercent", query = "SELECT p FROM ProductsFactorTbl p WHERE p.productsDiscountPercent = :productsDiscountPercent"),
    @NamedQuery(name = "ProductsFactorTbl.findByIdProductsFactor", query = "SELECT p FROM ProductsFactorTbl p WHERE p.idProductsFactor = :idProductsFactor")
})
public class ProductsFactorTbl implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "productsCount")
    private int productsCount;
    @Column(name = "productsUnitPrice")
    private BigInteger productsUnitPrice;
    @Column(name = "productsDiscountPercent")
    private Short productsDiscountPercent;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProductsFactor")
    private Long idProductsFactor;
    @JoinColumn(name = "ProductTbl_idProduct", referencedColumnName = "idProduct")
    @ManyToOne(optional = false)
    private ProductTbl productTblidProduct;
    @JoinColumn(name = "FactorsTbl_idFactorsTbl", referencedColumnName = "idFactorsTbl")
    @ManyToOne(optional = false)
    private FactorsTbl factorsTblidFactorsTbl;

    public ProductsFactorTbl()
    {
    }

    public ProductsFactorTbl(Long idProductsFactor)
    {
        this.idProductsFactor = idProductsFactor;
    }

    public ProductsFactorTbl(Long idProductsFactor, int productsCount)
    {
        this.idProductsFactor = idProductsFactor;
        this.productsCount = productsCount;
    }

    public int getProductsCount()
    {
        return productsCount;
    }

    public void setProductsCount(int productsCount)
    {
        this.productsCount = productsCount;
    }

    public BigInteger getProductsUnitPrice()
    {
        return productsUnitPrice;
    }

    public void setProductsUnitPrice(BigInteger productsUnitPrice)
    {
        this.productsUnitPrice = productsUnitPrice;
    }

    public Short getProductsDiscountPercent()
    {
        return productsDiscountPercent;
    }

    public void setProductsDiscountPercent(Short productsDiscountPercent)
    {
        this.productsDiscountPercent = productsDiscountPercent;
    }

    public Long getIdProductsFactor()
    {
        return idProductsFactor;
    }

    public void setIdProductsFactor(Long idProductsFactor)
    {
        this.idProductsFactor = idProductsFactor;
    }

    public ProductTbl getProductTblidProduct()
    {
        return productTblidProduct;
    }

    public void setProductTblidProduct(ProductTbl productTblidProduct)
    {
        this.productTblidProduct = productTblidProduct;
    }

    public FactorsTbl getFactorsTblidFactorsTbl()
    {
        return factorsTblidFactorsTbl;
    }

    public void setFactorsTblidFactorsTbl(FactorsTbl factorsTblidFactorsTbl)
    {
        this.factorsTblidFactorsTbl = factorsTblidFactorsTbl;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idProductsFactor != null ? idProductsFactor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductsFactorTbl))
        {
            return false;
        }
        ProductsFactorTbl other = (ProductsFactorTbl) object;
        if ((this.idProductsFactor == null && other.idProductsFactor != null) || (this.idProductsFactor != null && !this.idProductsFactor.equals(other.idProductsFactor)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.ProductsFactorTbl[ idProductsFactor=" + idProductsFactor + " ]";
    }
    
}
