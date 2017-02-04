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
@Table(name = "ProductAttVarientSeryal")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ProductAttVarientSeryal.findAll", query = "SELECT p FROM ProductAttVarientSeryal p"),
    @NamedQuery(name = "ProductAttVarientSeryal.findByIdProductAttVarientSeryal", query = "SELECT p FROM ProductAttVarientSeryal p WHERE p.idProductAttVarientSeryal = :idProductAttVarientSeryal"),
    @NamedQuery(name = "ProductAttVarientSeryal.findByCategoryID", query = "SELECT p FROM ProductAttVarientSeryal p WHERE p.categoryID = :categoryID")
})
public class ProductAttVarientSeryal implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProductAttVarientSeryal")
    private Long idProductAttVarientSeryal;
    @Column(name = "categoryID")
    private BigInteger categoryID;
    @JoinColumn(name = "SerialTbl_idSerialTbl", referencedColumnName = "idSerialTbl")
    @ManyToOne(optional = false)
    private SerialTbl serialTblidSerialTbl;

    public ProductAttVarientSeryal()
    {
    }

    public ProductAttVarientSeryal(Long idProductAttVarientSeryal)
    {
        this.idProductAttVarientSeryal = idProductAttVarientSeryal;
    }

    public Long getIdProductAttVarientSeryal()
    {
        return idProductAttVarientSeryal;
    }

    public void setIdProductAttVarientSeryal(Long idProductAttVarientSeryal)
    {
        this.idProductAttVarientSeryal = idProductAttVarientSeryal;
    }

    public BigInteger getCategoryID()
    {
        return categoryID;
    }

    public void setCategoryID(BigInteger categoryID)
    {
        this.categoryID = categoryID;
    }

    public SerialTbl getSerialTblidSerialTbl()
    {
        return serialTblidSerialTbl;
    }

    public void setSerialTblidSerialTbl(SerialTbl serialTblidSerialTbl)
    {
        this.serialTblidSerialTbl = serialTblidSerialTbl;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idProductAttVarientSeryal != null ? idProductAttVarientSeryal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductAttVarientSeryal))
        {
            return false;
        }
        ProductAttVarientSeryal other = (ProductAttVarientSeryal) object;
        if ((this.idProductAttVarientSeryal == null && other.idProductAttVarientSeryal != null) || (this.idProductAttVarientSeryal != null && !this.idProductAttVarientSeryal.equals(other.idProductAttVarientSeryal)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.ProductAttVarientSeryal[ idProductAttVarientSeryal=" + idProductAttVarientSeryal + " ]";
    }
    
}
