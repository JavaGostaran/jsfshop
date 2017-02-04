/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jsfshop.dao.FactorState;

/**
 *
 * @author j2ee1
 */
@Stateless
public class FactorStateFacade extends AbstractFacade<FactorState> {
    @PersistenceContext(unitName = "jsfshopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FactorStateFacade() {
        super(FactorState.class);
    }
    
}
