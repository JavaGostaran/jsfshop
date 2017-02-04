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
@Table(name = "user_roles")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "UserRoles.findAll", query = "SELECT u FROM UserRoles u"),
    @NamedQuery(name = "UserRoles.findByUserRoleId", query = "SELECT u FROM UserRoles u WHERE u.userRoleId = :userRoleId"),
    @NamedQuery(name = "UserRoles.findByAuthority", query = "SELECT u FROM UserRoles u WHERE u.authority = :authority")
})
public class UserRoles implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_ROLE_ID")
    private Integer userRoleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "AUTHORITY")
    private String authority;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userrolesUSERROLEID")
    private List<User> userList;

    public UserRoles()
    {
    }

    public UserRoles(Integer userRoleId)
    {
        this.userRoleId = userRoleId;
    }

    public UserRoles(Integer userRoleId, String authority)
    {
        this.userRoleId = userRoleId;
        this.authority = authority;
    }

    public Integer getUserRoleId()
    {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId)
    {
        this.userRoleId = userRoleId;
    }

    public String getAuthority()
    {
        return authority;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }

    @XmlTransient
    public List<User> getUserList()
    {
        return userList;
    }

    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (userRoleId != null ? userRoleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRoles))
        {
            return false;
        }
        UserRoles other = (UserRoles) object;
        if ((this.userRoleId == null && other.userRoleId != null) || (this.userRoleId != null && !this.userRoleId.equals(other.userRoleId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.UserRoles[ userRoleId=" + userRoleId + " ]";
    }
    
}
