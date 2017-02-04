/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.bean;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

/**
 *
 * @author mnm
 */
public abstract class AbstractFacade<T>
{

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass)
    {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity)
    {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        getEntityManager().refresh(entity);
    }

    public T create2(T entity)
    {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        getEntityManager().refresh(entity);
        return entity;
    }

    public void edit(T entity)
    {
        getEntityManager().merge(entity);
        getEntityManager().flush();
        getEntityManager().refresh(entity);
    }

    public void remove(T entity)
    {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id)
    {
        return getEntityManager().find(entityClass, id);
    }

    public T find(String username)
    {
        TypedQuery<T> qu = getEntityManager().createNamedQuery("User.findByUsername", entityClass);
        qu.setParameter("username", username);
        return qu.getSingleResult();
    }

    public List<T> findAll()
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    public List<T> findRange_Orderd(int[] range , boolean ortherType , String ColomnName)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.orderBy(getEntityManager().getCriteriaBuilder().desc(c.get(ColomnName)));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<T> findRange(int[] range, int h)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get("hotelHasRoomsPK").get("hotelIdhotel"), h));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<T> findRange(int[] range, int h, String column)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get(column), h));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<T> findRange(int[] range, long id, String col, String ids)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);

        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get(col).get(ids), id));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<T> findRange(int[] range, boolean f)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get("khareji"), f));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    public List<T> findRange(int[] range, boolean f,String columnName)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get(columnName), f));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    public List<T> findRange(int[] range ,long id, boolean f,String columnName)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(getEntityManager().getCriteriaBuilder().and
                            (
                                getEntityManager().getCriteriaBuilder().equal(c.get(columnName), f) ,
                                getEntityManager().getCriteriaBuilder().equal(c.get("productTblidProduct").get("idProduct"), id)
                            ));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    //
    public List<T> findRangeAttributesOfProduct(int[] range ,long product_ID,long Attibute_ID)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        //cq.where(getEntityManager().getCriteriaBuilder().equal(c.get("idAttributeValues"), 5));
        cq.where(getEntityManager().getCriteriaBuilder().and
                            (
                                getEntityManager().getCriteriaBuilder().equal(c.get("attributesTblidAttributes").get("idAttributes"), Attibute_ID) ,
                                getEntityManager().getCriteriaBuilder().equal(c.get("productAttValVarientList").get("productTblidProduct").get("idProduct"), product_ID)
                            ));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    public int countAttributesOfProduct(long product_ID,long Attibute_ID)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
          cq.where(getEntityManager().getCriteriaBuilder().and
                            (
                                getEntityManager().getCriteriaBuilder().equal(rt.get("attributesTblidAttributes").get("idAttributes"), Attibute_ID) ,
                                getEntityManager().getCriteriaBuilder().equal(rt.get("productAttValVarientList").get("productTblidProduct").get("idProduct"), product_ID)
                            ));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<T> findRange_d(int[] range, boolean d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get("deletedH"), d));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<T> findRange_t(int[] range, boolean d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get("taiid"), d));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<T> findRange_p(int[] range, boolean d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get("pardakht"), d));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<T> findRange_f(int[] range, boolean d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get("finalcer"), d));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<T> findRange_bartar(int[] range, boolean d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get("bartar"), d));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<T> findRange_ofer(int[] range, boolean d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get("ofer"), d));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<T> findRange_vije(int[] range, boolean d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get("vije"), d));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<T> findRange_search(int[] range, String d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get("city"), d));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<T> findRange_d(int[] range, long id1, String joinCols1, String col1, boolean id2, String col2)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(getEntityManager().getCriteriaBuilder().and(getEntityManager().getCriteriaBuilder().equal(c.get(joinCols1).get(col1), id1),
                getEntityManager().getCriteriaBuilder().equal(c.get(col2), id2)));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count()
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public int count(int h)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get("hotelHasRoomsPK").get("hotelIdhotel"), h));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
        
    public int count(long id, String col, String ids,String currentColomn,long currentID)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().and
                            (
                                getEntityManager().getCriteriaBuilder().equal(rt.get(col).get(ids), id) ,
                                getEntityManager().getCriteriaBuilder().notEqual(rt.get(currentColomn), currentID)
                            ));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
         
    public int count(long id, String col, String ids)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get(col).get(ids), id));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public int count(int id, String column)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get(column), id));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public int count(boolean f)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get("khareji"), f));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public int count(boolean f,String columnName)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get(columnName), f));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public int count_d(boolean d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get("deletedH"), d));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public int count_t(boolean d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get("taiid"), d));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public int count_p(boolean d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get("pardakht"), d));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public int count_f(boolean d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get("finalcer"), d));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public int count_ofer(boolean d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get("ofer"), d));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public int count_bartar(boolean d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get("bartar"), d));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public int count_vije(boolean d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get("vije"), d));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public int count_search(String d)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get("city"), d));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public int count_d(long id1, String joinCols1, String col1, boolean id2, String col2)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().and(getEntityManager().getCriteriaBuilder().equal(rt.get(joinCols1).get(col1), id1),
                getEntityManager().getCriteriaBuilder().equal(rt.get(col2), id2)));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    //=======================================================================
    public int count(int id, String joinCol1, String joinCol2, String targetCol)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get(joinCol1).get(joinCol2).get(targetCol), id));
        //cq.where(rt)

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    // ======================================================================= totalSumOfProduct =============

    public int sum(int id, String joinCol1, String targetCol, String sumColomn)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get(joinCol1).get(targetCol), id));
        Expression<Integer> exp1 = rt.get(sumColomn);

        cq.select(getEntityManager().getCriteriaBuilder().sum(exp1));

        //cq.where(rt)

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        if((Integer) q.getSingleResult() == null)
        {
            return 0;
        }
        return ((Integer) q.getSingleResult()).intValue();

    }

    
    //======================================== sumOfProductByCat ======================================================
    
    public int sumByCatID(long productId , long catId)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        
        cq.where(getEntityManager().getCriteriaBuilder().and
                            (
                                getEntityManager().getCriteriaBuilder().equal(rt.get("productTblidProduct").get("idProduct"), productId) ,
                                getEntityManager().getCriteriaBuilder().equal(rt.get("categoryID"), catId)
                            ));
        Expression<Integer> exp1 = rt.get("updateCount");

        cq.select(getEntityManager().getCriteriaBuilder().sum(exp1));

        //cq.where(rt)

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        if((Integer) q.getSingleResult() == null)
        {
            return 0;
        }
        return ((Integer) q.getSingleResult()).intValue();

    }
    
    //==================================================================================================================
    
    
      //======================================== PriceOfProductByCat ======================================================
    
    public List<T> Price_ByCatID(long productId , long catId)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        
        cq.where(getEntityManager().getCriteriaBuilder().and
                            (
                                getEntityManager().getCriteriaBuilder().equal(rt.get("productTblidProduct").get("idProduct"), productId) ,
                                getEntityManager().getCriteriaBuilder().equal(rt.get("categoryID"), catId)
                            ));
        
//        Expression<Integer> exp1 = rt.get("updatePrice");
//
//        cq.select(getEntityManager().getCriteriaBuilder().sum(exp1));

        //cq.where(rt)

        javax.persistence.Query q = getEntityManager().createQuery(cq);

        return q.getResultList();    
    }
    
    //==================================================================================================================
    
    
    //============================================ added productAttValVarientQueryWithGroupBy =========================
    
    public List<T> AttributeVarientsGroupedOfProduct(int[] range, long productId, String col, String ids)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.multiselect(c);
        Expression<Integer> exp1 = c.get("categoryID");
        
        cq.groupBy(exp1);
        //cq.multiselect(exp1,getEntityManager().getCriteriaBuilder().equal(c.get(col).get(ids), productId));        
        
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get(col).get(ids), productId));
        //cq.where(getEntityManager().getCriteriaBuilder().equal(c.get("categoryID"),1));
        //cq.orderBy(getEntityManager().getCriteriaBuilder().desc(c.get("updateCount")));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    //==================================================================================================================
    
    //================================================= category FindRang ======================================
    
       public List<T> catProductFindRange(int[] range, long productId,long categoryId)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.multiselect(c);
                
        cq.where(getEntityManager().getCriteriaBuilder().and
                            (
                                getEntityManager().getCriteriaBuilder().equal(c.get("productTblidProduct").get("idProduct"), productId) ,
                                getEntityManager().getCriteriaBuilder().equal(c.get("categoryID"), categoryId)
                            ));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    //===========================================================================================================
    //============================================================
    public List<T> findRange(int[] range, int id, String joinCol1, String joinCol2, String targetCol)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get(joinCol1).get(joinCol2).get(targetCol), id));
        //cq.orderBy(getEntityManager().getCriteriaBuilder().desc(c.get("updateCount")));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    //============================================================
    //============================================================= similar Products =====================================
    public int GetCountOfOneProperty(int[] range, int culomnId, String columnName, String j)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get(columnName), culomnId));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<T> findSimilarProductsRange(int[] range, long id, String col, String ids,String currentColomn,long currentID)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);

        cq.where(getEntityManager().getCriteriaBuilder().and
                            (
                                getEntityManager().getCriteriaBuilder().equal(c.get(col).get(ids), id) ,
                                getEntityManager().getCriteriaBuilder().notEqual(c.get(currentColomn), currentID)
                            ));
        //cq.orderBy(getEntityManager().getCriteriaBuilder().desc(c.get("bestSeller")));
        //cq.groupBy((c.get("bestSeller")));
        
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int countSimilarProduct(long id, String col, String ids)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        cq.where(getEntityManager().getCriteriaBuilder().equal(rt.get(col).get(ids), id));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}