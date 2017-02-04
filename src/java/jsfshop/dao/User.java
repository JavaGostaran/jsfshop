/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.dao;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author j2ee1
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserid", query = "SELECT u FROM User u WHERE u.userid = :userid"),
    @NamedQuery(name = "User.findByFname", query = "SELECT u FROM User u WHERE u.fname = :fname"),
    @NamedQuery(name = "User.findByLname", query = "SELECT u FROM User u WHERE u.lname = :lname"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone"),
    @NamedQuery(name = "User.findByPosition", query = "SELECT u FROM User u WHERE u.position = :position"),
    @NamedQuery(name = "User.findByEnabled", query = "SELECT u FROM User u WHERE u.enabled = :enabled"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByDt", query = "SELECT u FROM User u WHERE u.dt = :dt"),
    @NamedQuery(name = "User.findByHash", query = "SELECT u FROM User u WHERE u.hash = :hash"),
    @NamedQuery(name = "User.findByYer", query = "SELECT u FROM User u WHERE u.yer = :yer"),
    @NamedQuery(name = "User.findByWebsite", query = "SELECT u FROM User u WHERE u.website = :website")
})
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USERID")
    private Integer userid;
    @Size(max = 255)
    @Column(name = "fname")
    private String fname;
    @Size(max = 255)
    @Column(name = "lname")
    private String lname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "phone")
    private String phone;
    @Size(max = 255)
    @Column(name = "position")
    private String position;
    @Column(name = "enabled")
    private Short enabled;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Column(name = "dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dt;
    @Size(max = 255)
    @Column(name = "hash")
    private String hash;
    @Size(max = 45)
    @Column(name = "yer")
    private String yer;
    @Size(max = 255)
    @Column(name = "website")
    private String website;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userUSERID")
    private List<Tickets> ticketsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userUSERID")
    private List<UsersFavoritsTbl> usersFavoritsTblList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userUSERID")
    private List<FactorsTbl> factorsTblList;
    @JoinColumn(name = "user_roles_USER_ROLE_ID", referencedColumnName = "USER_ROLE_ID")
    @ManyToOne(optional = false)
    private UserRoles userrolesUSERROLEID;

    public User()
    {
    }

    public User(Integer userid)
    {
        this.userid = userid;
    }

    public User(Integer userid, String username)
    {
        this.userid = userid;
        this.username = username;
    }

    public Integer getUserid()
    {
        return userid;
    }

    public void setUserid(Integer userid)
    {
        this.userid = userid;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public Short getEnabled()
    {
        return enabled;
    }

    public void setEnabled(Short enabled)
    {
        this.enabled = enabled;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Date getDt()
    {
        return dt;
    }

    public void setDt(Date dt)
    {
        this.dt = dt;
    }

    public String getHash()
    {
        return hash;
    }

    public void setHash(String hash)
    {
        this.hash = hash;
    }

    public String getYer()
    {
        return yer;
    }

    public void setYer(String yer)
    {
        this.yer = yer;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    @XmlTransient
    public List<Tickets> getTicketsList()
    {
        return ticketsList;
    }

    public void setTicketsList(List<Tickets> ticketsList)
    {
        this.ticketsList = ticketsList;
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

    @XmlTransient
    public List<FactorsTbl> getFactorsTblList()
    {
        return factorsTblList;
    }

    public void setFactorsTblList(List<FactorsTbl> factorsTblList)
    {
        this.factorsTblList = factorsTblList;
    }

    public UserRoles getUserrolesUSERROLEID()
    {
        return userrolesUSERROLEID;
    }

    public void setUserrolesUSERROLEID(UserRoles userrolesUSERROLEID)
    {
        this.userrolesUSERROLEID = userrolesUSERROLEID;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User))
        {
            return false;
        }
        User other = (User) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.User[ userid=" + userid + " ]";
    }
    
}
