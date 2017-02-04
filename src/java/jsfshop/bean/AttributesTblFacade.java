/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jsfshop.dao.AttributesTbl;

/**
 *
 * @author j2ee1
 */
@Stateless
public class AttributesTblFacade extends AbstractFacade<AttributesTbl> {
    @PersistenceContext(unitName = "jsfshopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AttributesTblFacade() {
        super(AttributesTbl.class);
    }
    
}
