/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jsfshop.dao.ProductTbl;

/**
 *
 * @author j2ee1
 */
@Stateless
public class ProductTblFacade extends AbstractFacade<ProductTbl> {
    @PersistenceContext(unitName = "jsfshopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductTblFacade() {
        super(ProductTbl.class);
    }
    
}
