/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jsfshop.dao.ProductAttribute;

/**
 *
 * @author j2ee1
 */
@Stateless
public class ProductAttributeFacade extends AbstractFacade<ProductAttribute> {
    @PersistenceContext(unitName = "jsfshopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductAttributeFacade() {
        super(ProductAttribute.class);
    }
    
}
