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
@Table(name = "Topics")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Topics.findAll", query = "SELECT t FROM Topics t"),
    @NamedQuery(name = "Topics.findByIdTopics", query = "SELECT t FROM Topics t WHERE t.idTopics = :idTopics"),
    @NamedQuery(name = "Topics.findByTitleTopics", query = "SELECT t FROM Topics t WHERE t.titleTopics = :titleTopics"),
    @NamedQuery(name = "Topics.findByRank", query = "SELECT t FROM Topics t WHERE t.rank = :rank")
})
public class Topics implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTopics")
    private Long idTopics;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "titleTopics")
    private String titleTopics;
    @Column(name = "rank")
    private Short rank;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topicsidTopics")
    private List<ProductTbl> productTblList;
    @OneToMany(mappedBy = "topicsidTopics")
    private List<Topics> topicsList;
    @JoinColumn(name = "Topics_idTopics", referencedColumnName = "idTopics")
    @ManyToOne
    private Topics topicsidTopics;

    public Topics()
    {
    }

    public Topics(Long idTopics)
    {
        this.idTopics = idTopics;
    }

    public Topics(Long idTopics, String titleTopics)
    {
        this.idTopics = idTopics;
        this.titleTopics = titleTopics;
    }

    public Long getIdTopics()
    {
        return idTopics;
    }

    public void setIdTopics(Long idTopics)
    {
        this.idTopics = idTopics;
    }

    public String getTitleTopics()
    {
        return titleTopics;
    }

    public void setTitleTopics(String titleTopics)
    {
        this.titleTopics = titleTopics;
    }

    public Short getRank()
    {
        return rank;
    }

    public void setRank(Short rank)
    {
        this.rank = rank;
    }

    @XmlTransient
    public List<ProductTbl> getProductTblList()
    {
        return productTblList;
    }

    public void setProductTblList(List<ProductTbl> productTblList)
    {
        this.productTblList = productTblList;
    }

    @XmlTransient
    public List<Topics> getTopicsList()
    {
        return topicsList;
    }

    public void setTopicsList(List<Topics> topicsList)
    {
        this.topicsList = topicsList;
    }

    public Topics getTopicsidTopics()
    {
        return topicsidTopics;
    }

    public void setTopicsidTopics(Topics topicsidTopics)
    {
        this.topicsidTopics = topicsidTopics;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idTopics != null ? idTopics.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topics))
        {
            return false;
        }
        Topics other = (Topics) object;
        if ((this.idTopics == null && other.idTopics != null) || (this.idTopics != null && !this.idTopics.equals(other.idTopics)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.Topics[ idTopics=" + idTopics + " ]";
    }
    
}
