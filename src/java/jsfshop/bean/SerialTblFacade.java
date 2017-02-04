/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jsfshop.dao.SerialTbl;

/**
 *
 * @author j2ee1
 */
@Stateless
public class SerialTblFacade extends AbstractFacade<SerialTbl> {
    @PersistenceContext(unitName = "jsfshopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SerialTblFacade() {
        super(SerialTbl.class);
    }
    
}
