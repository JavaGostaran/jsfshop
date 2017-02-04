/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jsfshop.dao.AttributeValues;

/**
 *
 * @author j2ee1
 */
@Stateless
public class AttributeValuesFacade extends AbstractFacade<AttributeValues> {
    @PersistenceContext(unitName = "jsfshopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AttributeValuesFacade() {
        super(AttributeValues.class);
    }
    
}
