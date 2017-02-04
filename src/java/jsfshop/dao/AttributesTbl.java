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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "AttributesTbl")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "AttributesTbl.findAll", query = "SELECT a FROM AttributesTbl a"),
    @NamedQuery(name = "AttributesTbl.findByIdAttributes", query = "SELECT a FROM AttributesTbl a WHERE a.idAttributes = :idAttributes"),
    @NamedQuery(name = "AttributesTbl.findByTitleAttributes", query = "SELECT a FROM AttributesTbl a WHERE a.titleAttributes = :titleAttributes")
})
public class AttributesTbl implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAttributes")
    private Long idAttributes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "titleAttributes")
    private String titleAttributes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attributesTblidAttributes")
    private List<AttributeValues> attributeValuesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attributesTblidAttributes")
    private List<ProductAttribute> productAttributeList;
    @JoinColumn(name = "AttributeType_idAttributeType", referencedColumnName = "idAttributeType")
    @ManyToOne(optional = false)
    private AttributeType attributeTypeidAttributeType;

    public AttributesTbl()
    {
    }

    public AttributesTbl(Long idAttributes)
    {
        this.idAttributes = idAttributes;
    }

    public AttributesTbl(Long idAttributes, String titleAttributes)
    {
        this.idAttributes = idAttributes;
        this.titleAttributes = titleAttributes;
    }

    public Long getIdAttributes()
    {
        return idAttributes;
    }

    public void setIdAttributes(Long idAttributes)
    {
        this.idAttributes = idAttributes;
    }

    public String getTitleAttributes()
    {
        return titleAttributes;
    }

    public void setTitleAttributes(String titleAttributes)
    {
        this.titleAttributes = titleAttributes;
    }

    @XmlTransient
    public List<AttributeValues> getAttributeValuesList()
    {
        return attributeValuesList;
    }

    public void setAttributeValuesList(List<AttributeValues> attributeValuesList)
    {
        this.attributeValuesList = attributeValuesList;
    }

    @XmlTransient
    public List<ProductAttribute> getProductAttributeList()
    {
        return productAttributeList;
    }

    public void setProductAttributeList(List<ProductAttribute> productAttributeList)
    {
        this.productAttributeList = productAttributeList;
    }

    public AttributeType getAttributeTypeidAttributeType()
    {
        return attributeTypeidAttributeType;
    }

    public void setAttributeTypeidAttributeType(AttributeType attributeTypeidAttributeType)
    {
        this.attributeTypeidAttributeType = attributeTypeidAttributeType;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idAttributes != null ? idAttributes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttributesTbl))
        {
            return false;
        }
        AttributesTbl other = (AttributesTbl) object;
        if ((this.idAttributes == null && other.idAttributes != null) || (this.idAttributes != null && !this.idAttributes.equals(other.idAttributes)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.AttributesTbl[ idAttributes=" + idAttributes + " ]";
    }
    
}
