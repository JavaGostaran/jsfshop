/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author j2ee1
 */
@Entity
@Table(name = "tickets")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Tickets.findAll", query = "SELECT t FROM Tickets t"),
    @NamedQuery(name = "Tickets.findByIdtickets", query = "SELECT t FROM Tickets t WHERE t.idtickets = :idtickets"),
    @NamedQuery(name = "Tickets.findByTickNumber", query = "SELECT t FROM Tickets t WHERE t.tickNumber = :tickNumber"),
    @NamedQuery(name = "Tickets.findBySubj", query = "SELECT t FROM Tickets t WHERE t.subj = :subj"),
    @NamedQuery(name = "Tickets.findByPrioritiNum", query = "SELECT t FROM Tickets t WHERE t.prioritiNum = :prioritiNum"),
    @NamedQuery(name = "Tickets.findByDt", query = "SELECT t FROM Tickets t WHERE t.dt = :dt"),
    @NamedQuery(name = "Tickets.findByStatusAns", query = "SELECT t FROM Tickets t WHERE t.statusAns = :statusAns"),
    @NamedQuery(name = "Tickets.findByNt", query = "SELECT t FROM Tickets t WHERE t.nt = :nt")
})
public class Tickets implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtickets")
    private Integer idtickets;
    @Column(name = "tick_number")
    private Integer tickNumber;
    @Size(max = 255)
    @Column(name = "subj")
    private String subj;
    @Column(name = "prioriti_num")
    private Integer prioritiNum;
    @Lob
    @Size(max = 65535)
    @Column(name = "matn")
    private String matn;
    @Column(name = "dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dt;
    @Column(name = "status_ans")
    private Integer statusAns;
    @Column(name = "nt")
    private Integer nt;
    @JoinColumn(name = "user_USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private User userUSERID;

    public Tickets()
    {
    }

    public Tickets(Integer idtickets)
    {
        this.idtickets = idtickets;
    }

    public Integer getIdtickets()
    {
        return idtickets;
    }

    public void setIdtickets(Integer idtickets)
    {
        this.idtickets = idtickets;
    }

    public Integer getTickNumber()
    {
        return tickNumber;
    }

    public void setTickNumber(Integer tickNumber)
    {
        this.tickNumber = tickNumber;
    }

    public String getSubj()
    {
        return subj;
    }

    public void setSubj(String subj)
    {
        this.subj = subj;
    }

    public Integer getPrioritiNum()
    {
        return prioritiNum;
    }

    public void setPrioritiNum(Integer prioritiNum)
    {
        this.prioritiNum = prioritiNum;
    }

    public String getMatn()
    {
        return matn;
    }

    public void setMatn(String matn)
    {
        this.matn = matn;
    }

    public Date getDt()
    {
        return dt;
    }

    public void setDt(Date dt)
    {
        this.dt = dt;
    }

    public Integer getStatusAns()
    {
        return statusAns;
    }

    public void setStatusAns(Integer statusAns)
    {
        this.statusAns = statusAns;
    }

    public Integer getNt()
    {
        return nt;
    }

    public void setNt(Integer nt)
    {
        this.nt = nt;
    }

    public User getUserUSERID()
    {
        return userUSERID;
    }

    public void setUserUSERID(User userUSERID)
    {
        this.userUSERID = userUSERID;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idtickets != null ? idtickets.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tickets))
        {
            return false;
        }
        Tickets other = (Tickets) object;
        if ((this.idtickets == null && other.idtickets != null) || (this.idtickets != null && !this.idtickets.equals(other.idtickets)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.Tickets[ idtickets=" + idtickets + " ]";
    }
    
}
