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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author j2ee1
 */
@Entity
@Table(name = "ImageProduct")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ImageProduct.findAll", query = "SELECT i FROM ImageProduct i"),
    @NamedQuery(name = "ImageProduct.findByIdImageProduct", query = "SELECT i FROM ImageProduct i WHERE i.idImageProduct = :idImageProduct"),
    @NamedQuery(name = "ImageProduct.findByImageUrl", query = "SELECT i FROM ImageProduct i WHERE i.imageUrl = :imageUrl"),
    @NamedQuery(name = "ImageProduct.findByIsActive", query = "SELECT i FROM ImageProduct i WHERE i.isActive = :isActive"),
    @NamedQuery(name = "ImageProduct.findBySmallImage", query = "SELECT i FROM ImageProduct i WHERE i.smallImage = :smallImage")
})
public class ImageProduct implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idImageProduct")
    private Long idImageProduct;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ImageUrl")
    private String imageUrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isActive")
    private boolean isActive;
    @Size(max = 45)
    @Column(name = "smallImage")
    private String smallImage;
    @JoinColumn(name = "ProductYbl_idProduct", referencedColumnName = "idProduct")
    @ManyToOne(optional = false)
    private ProductTbl productYblidProduct;

    public ImageProduct()
    {
    }

    public ImageProduct(Long idImageProduct)
    {
        this.idImageProduct = idImageProduct;
    }

    public ImageProduct(Long idImageProduct, String imageUrl, boolean isActive)
    {
        this.idImageProduct = idImageProduct;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
    }

    public Long getIdImageProduct()
    {
        return idImageProduct;
    }

    public void setIdImageProduct(Long idImageProduct)
    {
        this.idImageProduct = idImageProduct;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public boolean getIsActive()
    {
        return isActive;
    }

    public void setIsActive(boolean isActive)
    {
        this.isActive = isActive;
    }

    public String getSmallImage()
    {
        return smallImage;
    }

    public void setSmallImage(String smallImage)
    {
        this.smallImage = smallImage;
    }

    public ProductTbl getProductYblidProduct()
    {
        return productYblidProduct;
    }

    public void setProductYblidProduct(ProductTbl productYblidProduct)
    {
        this.productYblidProduct = productYblidProduct;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idImageProduct != null ? idImageProduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImageProduct))
        {
            return false;
        }
        ImageProduct other = (ImageProduct) object;
        if ((this.idImageProduct == null && other.idImageProduct != null) || (this.idImageProduct != null && !this.idImageProduct.equals(other.idImageProduct)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.ImageProduct[ idImageProduct=" + idImageProduct + " ]";
    }
    
}
