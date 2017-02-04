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
@Table(name = "UsersFavoritsTbl")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "UsersFavoritsTbl.findAll", query = "SELECT u FROM UsersFavoritsTbl u"),
    @NamedQuery(name = "UsersFavoritsTbl.findById", query = "SELECT u FROM UsersFavoritsTbl u WHERE u.id = :id")
})
public class UsersFavoritsTbl implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "user_USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private User userUSERID;
    @JoinColumn(name = "ProductTbl_idProduct", referencedColumnName = "idProduct")
    @ManyToOne
    private ProductTbl productTblidProduct;
    @JoinColumn(name = "FavoritsType_idFavoritsType", referencedColumnName = "idFavoritsType")
    @ManyToOne
    private FavoritsType favoritsTypeidFavoritsType;

    public UsersFavoritsTbl()
    {
    }

    public UsersFavoritsTbl(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public User getUserUSERID()
    {
        return userUSERID;
    }

    public void setUserUSERID(User userUSERID)
    {
        this.userUSERID = userUSERID;
    }

    public ProductTbl getProductTblidProduct()
    {
        return productTblidProduct;
    }

    public void setProductTblidProduct(ProductTbl productTblidProduct)
    {
        this.productTblidProduct = productTblidProduct;
    }

    public FavoritsType getFavoritsTypeidFavoritsType()
    {
        return favoritsTypeidFavoritsType;
    }

    public void setFavoritsTypeidFavoritsType(FavoritsType favoritsTypeidFavoritsType)
    {
        this.favoritsTypeidFavoritsType = favoritsTypeidFavoritsType;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersFavoritsTbl))
        {
            return false;
        }
        UsersFavoritsTbl other = (UsersFavoritsTbl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.UsersFavoritsTbl[ id=" + id + " ]";
    }
    
}
