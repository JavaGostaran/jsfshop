/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jsfshop.dao.GarantyType;

/**
 *
 * @author j2ee1
 */
@Stateless
public class GarantyTypeFacade extends AbstractFacade<GarantyType> {
    @PersistenceContext(unitName = "jsfshopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GarantyTypeFacade() {
        super(GarantyType.class);
    }
    
}
