/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jsfshop.dao.BankAttributes;

/**
 *
 * @author j2ee1
 */
@Stateless
public class BankAttributesFacade extends AbstractFacade<BankAttributes> {
    @PersistenceContext(unitName = "jsfshopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BankAttributesFacade() {
        super(BankAttributes.class);
    }
    
}
