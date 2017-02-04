/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jsfshop.dao.UsersFavoritsTbl;

/**
 *
 * @author j2ee1
 */
@Stateless
public class UsersFavoritsTblFacade extends AbstractFacade<UsersFavoritsTbl> {
    @PersistenceContext(unitName = "jsfshopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFavoritsTblFacade() {
        super(UsersFavoritsTbl.class);
    }
    
}
