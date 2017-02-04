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
import javax.persistence.Lob;
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
@Table(name = "DiscountType")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "DiscountType.findAll", query = "SELECT d FROM DiscountType d"),
    @NamedQuery(name = "DiscountType.findByIdDiscountType", query = "SELECT d FROM DiscountType d WHERE d.idDiscountType = :idDiscountType")
})
public class DiscountType implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDiscountType")
    private Short idDiscountType;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "discountTitle")
    private String discountTitle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discountTypeidDiscountType")
    private List<ProductDiscountTbl> productDiscountTblList;

    public DiscountType()
    {
    }

    public DiscountType(Short idDiscountType)
    {
        this.idDiscountType = idDiscountType;
    }

    public DiscountType(Short idDiscountType, String discountTitle)
    {
        this.idDiscountType = idDiscountType;
        this.discountTitle = discountTitle;
    }

    public Short getIdDiscountType()
    {
        return idDiscountType;
    }

    public void setIdDiscountType(Short idDiscountType)
    {
        this.idDiscountType = idDiscountType;
    }

    public String getDiscountTitle()
    {
        return discountTitle;
    }

    public void setDiscountTitle(String discountTitle)
    {
        this.discountTitle = discountTitle;
    }

    @XmlTransient
    public List<ProductDiscountTbl> getProductDiscountTblList()
    {
        return productDiscountTblList;
    }

    public void setProductDiscountTblList(List<ProductDiscountTbl> productDiscountTblList)
    {
        this.productDiscountTblList = productDiscountTblList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idDiscountType != null ? idDiscountType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiscountType))
        {
            return false;
        }
        DiscountType other = (DiscountType) object;
        if ((this.idDiscountType == null && other.idDiscountType != null) || (this.idDiscountType != null && !this.idDiscountType.equals(other.idDiscountType)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.DiscountType[ idDiscountType=" + idDiscountType + " ]";
    }
    
}
