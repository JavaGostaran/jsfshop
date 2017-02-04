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
import javax.persistence.Lob;
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
@Table(name = "AttributeValues")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "AttributeValues.findAll", query = "SELECT a FROM AttributeValues a"),
    @NamedQuery(name = "AttributeValues.findByIdAttributeValues", query = "SELECT a FROM AttributeValues a WHERE a.idAttributeValues = :idAttributeValues"),
    @NamedQuery(name = "AttributeValues.findByIsBoolean", query = "SELECT a FROM AttributeValues a WHERE a.isBoolean = :isBoolean")
})
public class AttributeValues implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAttributeValues")
    private Long idAttributeValues;
    @Lob
    @Size(max = 65535)
    @Column(name = "descriptionAttributeValue")
    private String descriptionAttributeValue;
    @Column(name = "isBoolean")
    private Boolean isBoolean;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "titleValues")
    private String titleValues;
    @JoinColumn(name = "AttributesTbl_idAttributes", referencedColumnName = "idAttributes")
    @ManyToOne(optional = false)
    private AttributesTbl attributesTblidAttributes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attributeValuesidAttributeValues")
    private List<ProductAttributeValueConst> productAttributeValueConstList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attributeValuesidAttributeValues")
    private List<ProductAttValVarient> productAttValVarientList;

    public AttributeValues()
    {
       
    }

    public AttributeValues(Long idAttributeValues)
    {
        this.idAttributeValues = idAttributeValues;
    }

    public AttributeValues(Long idAttributeValues, String titleValues)
    {
        this.idAttributeValues = idAttributeValues;
        this.titleValues = titleValues;
    }

    public Long getIdAttributeValues()
    {
        return idAttributeValues;
    }

    public void setIdAttributeValues(Long idAttributeValues)
    {
        this.idAttributeValues = idAttributeValues;
    }

    public String getDescriptionAttributeValue()
    {
        return descriptionAttributeValue;
    }

    public void setDescriptionAttributeValue(String descriptionAttributeValue)
    {
        this.descriptionAttributeValue = descriptionAttributeValue;
    }

    public Boolean getIsBoolean()
    {
        return isBoolean;
    }

    public void setIsBoolean(Boolean isBoolean)
    {
        this.isBoolean = isBoolean;
    }

    public String getTitleValues()
    {
        return titleValues;
    }

    public void setTitleValues(String titleValues)
    {
        this.titleValues = titleValues;
    }

    public AttributesTbl getAttributesTblidAttributes()
    {
        return attributesTblidAttributes;
    }

    public void setAttributesTblidAttributes(AttributesTbl attributesTblidAttributes)
    {
        this.attributesTblidAttributes = attributesTblidAttributes;
    }

    @XmlTransient
    public List<ProductAttributeValueConst> getProductAttributeValueConstList()
    {
        return productAttributeValueConstList;
    }

    public void setProductAttributeValueConstList(List<ProductAttributeValueConst> productAttributeValueConstList)
    {
        this.productAttributeValueConstList = productAttributeValueConstList;
    }

    @XmlTransient
    public List<ProductAttValVarient> getProductAttValVarientList()
    {
        return productAttValVarientList;
    }

    public void setProductAttValVarientList(List<ProductAttValVarient> productAttValVarientList)
    {
        this.productAttValVarientList = productAttValVarientList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idAttributeValues != null ? idAttributeValues.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttributeValues))
        {
            return false;
        }
        AttributeValues other = (AttributeValues) object;
        if ((this.idAttributeValues == null && other.idAttributeValues != null) || (this.idAttributeValues != null && !this.idAttributeValues.equals(other.idAttributeValues)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.AttributeValues[ idAttributeValues=" + idAttributeValues + " ]";
    }
    
}
