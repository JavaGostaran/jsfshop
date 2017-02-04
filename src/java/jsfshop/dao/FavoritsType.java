/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "FavoritsType")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "FavoritsType.findAll", query = "SELECT f FROM FavoritsType f"),
    @NamedQuery(name = "FavoritsType.findByIdFavoritsType", query = "SELECT f FROM FavoritsType f WHERE f.idFavoritsType = :idFavoritsType"),
    @NamedQuery(name = "FavoritsType.findByTitleFavoritsType", query = "SELECT f FROM FavoritsType f WHERE f.titleFavoritsType = :titleFavoritsType")
})
public class FavoritsType implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFavoritsType")
    private Short idFavoritsType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "titleFavoritsType")
    private String titleFavoritsType;
    @OneToMany(mappedBy = "favoritsTypeidFavoritsType")
    private List<UsersFavoritsTbl> usersFavoritsTblList;

    public FavoritsType()
    {
    }

    public FavoritsType(Short idFavoritsType)
    {
        this.idFavoritsType = idFavoritsType;
    }

    public FavoritsType(Short idFavoritsType, String titleFavoritsType)
    {
        this.idFavoritsType = idFavoritsType;
        this.titleFavoritsType = titleFavoritsType;
    }

    public Short getIdFavoritsType()
    {
        return idFavoritsType;
    }

    public void setIdFavoritsType(Short idFavoritsType)
    {
        this.idFavoritsType = idFavoritsType;
    }

    public String getTitleFavoritsType()
    {
        return titleFavoritsType;
    }

    public void setTitleFavoritsType(String titleFavoritsType)
    {
        this.titleFavoritsType = titleFavoritsType;
    }

    @XmlTransient
    public List<UsersFavoritsTbl> getUsersFavoritsTblList()
    {
        return usersFavoritsTblList;
    }

    public void setUsersFavoritsTblList(List<UsersFavoritsTbl> usersFavoritsTblList)
    {
        this.usersFavoritsTblList = usersFavoritsTblList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idFavoritsType != null ? idFavoritsType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FavoritsType))
        {
            return false;
        }
        FavoritsType other = (FavoritsType) object;
        if ((this.idFavoritsType == null && other.idFavoritsType != null) || (this.idFavoritsType != null && !this.idFavoritsType.equals(other.idFavoritsType)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.FavoritsType[ idFavoritsType=" + idFavoritsType + " ]";
    }
    
}
