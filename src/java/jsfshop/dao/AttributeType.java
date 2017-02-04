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
@Table(name = "AttributeType")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "AttributeType.findAll", query = "SELECT a FROM AttributeType a"),
    @NamedQuery(name = "AttributeType.findByIdAttributeType", query = "SELECT a FROM AttributeType a WHERE a.idAttributeType = :idAttributeType"),
    @NamedQuery(name = "AttributeType.findByTitleAttributeType", query = "SELECT a FROM AttributeType a WHERE a.titleAttributeType = :titleAttributeType")
})
public class AttributeType implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAttributeType")
    private Short idAttributeType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "titleAttributeType")
    private String titleAttributeType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attributeTypeidAttributeType")
    private List<AttributesTbl> attributesTblList;

    public AttributeType()
    {
    }

    public AttributeType(Short idAttributeType)
    {
        this.idAttributeType = idAttributeType;
    }

    public AttributeType(Short idAttributeType, String titleAttributeType)
    {
        this.idAttributeType = idAttributeType;
        this.titleAttributeType = titleAttributeType;
    }

    public Short getIdAttributeType()
    {
        return idAttributeType;
    }

    public void setIdAttributeType(Short idAttributeType)
    {
        this.idAttributeType = idAttributeType;
    }

    public String getTitleAttributeType()
    {
        return titleAttributeType;
    }

    public void setTitleAttributeType(String titleAttributeType)
    {
        this.titleAttributeType = titleAttributeType;
    }

    @XmlTransient
    public List<AttributesTbl> getAttributesTblList()
    {
        return attributesTblList;
    }

    public void setAttributesTblList(List<AttributesTbl> attributesTblList)
    {
        this.attributesTblList = attributesTblList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idAttributeType != null ? idAttributeType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttributeType))
        {
            return false;
        }
        AttributeType other = (AttributeType) object;
        if ((this.idAttributeType == null && other.idAttributeType != null) || (this.idAttributeType != null && !this.idAttributeType.equals(other.idAttributeType)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.AttributeType[ idAttributeType=" + idAttributeType + " ]";
    }
    
}
