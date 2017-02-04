/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author j2ee1
 */
@Entity
@Table(name = "SerialTbl")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "SerialTbl.findAll", query = "SELECT s FROM SerialTbl s"),
    @NamedQuery(name = "SerialTbl.findByIdSerialTbl", query = "SELECT s FROM SerialTbl s WHERE s.idSerialTbl = :idSerialTbl"),
    @NamedQuery(name = "SerialTbl.findBySerialCode", query = "SELECT s FROM SerialTbl s WHERE s.serialCode = :serialCode"),
    @NamedQuery(name = "SerialTbl.findByRegisterDateSerial", query = "SELECT s FROM SerialTbl s WHERE s.registerDateSerial = :registerDateSerial"),
    @NamedQuery(name = "SerialTbl.findByPriceSerial", query = "SELECT s FROM SerialTbl s WHERE s.priceSerial = :priceSerial")
})
public class SerialTbl implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSerialTbl")
    private Long idSerialTbl;
    @Size(max = 256)
    @Column(name = "SerialCode")
    private String serialCode;
    @Column(name = "registerDateSerial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDateSerial;
    @Column(name = "priceSerial")
    private BigInteger priceSerial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serialTblidSerialTbl")
    private List<ProductAttVarientSeryal> productAttVarientSeryalList;

    public SerialTbl()
    {
    }

    public SerialTbl(Long idSerialTbl)
    {
        this.idSerialTbl = idSerialTbl;
    }

    public Long getIdSerialTbl()
    {
        return idSerialTbl;
    }

    public void setIdSerialTbl(Long idSerialTbl)
    {
        this.idSerialTbl = idSerialTbl;
    }

    public String getSerialCode()
    {
        return serialCode;
    }

    public void setSerialCode(String serialCode)
    {
        this.serialCode = serialCode;
    }

    public Date getRegisterDateSerial()
    {
        return registerDateSerial;
    }

    public void setRegisterDateSerial(Date registerDateSerial)
    {
        this.registerDateSerial = registerDateSerial;
    }

    public BigInteger getPriceSerial()
    {
        return priceSerial;
    }

    public void setPriceSerial(BigInteger priceSerial)
    {
        this.priceSerial = priceSerial;
    }

    @XmlTransient
    public List<ProductAttVarientSeryal> getProductAttVarientSeryalList()
    {
        return productAttVarientSeryalList;
    }

    public void setProductAttVarientSeryalList(List<ProductAttVarientSeryal> productAttVarientSeryalList)
    {
        this.productAttVarientSeryalList = productAttVarientSeryalList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idSerialTbl != null ? idSerialTbl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SerialTbl))
        {
            return false;
        }
        SerialTbl other = (SerialTbl) object;
        if ((this.idSerialTbl == null && other.idSerialTbl != null) || (this.idSerialTbl != null && !this.idSerialTbl.equals(other.idSerialTbl)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.SerialTbl[ idSerialTbl=" + idSerialTbl + " ]";
    }
    
}
