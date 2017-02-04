package jsfshop.jsf;

import jsfshop.dao.ProductTbl;
import jsfshop.jsf.util.JsfUtil;
import jsfshop.jsf.util.PaginationHelper;
import jsfshop.bean.ProductTblFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.hibernate.validator.constraints.Length;

@ManagedBean(name = "productTblController")
@SessionScoped
public class ProductTblController implements Serializable
{

    private ProductTbl current;
    private DataModel items = null;
    private DataModel items2 = null;
    private DataModel feacherItems = null;
    private DataModel latestItems = null;
    @EJB
    private jsfshop.bean.ProductTblFacade ejbFacade;
    private PaginationHelper pagination;
    private PaginationHelper pagination2;
    private int selectedItemIndex;
    private PaginationHelper feacherPagination;
    private PaginationHelper latestPagination;

    public ProductTblController()
    {
    }

    public ProductTbl getSelected()
    {
//        current.getProductAttValVarientList().s
        if (current == null)
        {
            current = new ProductTbl();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProductTblFacade getFacade()
    {
        return ejbFacade;
    }
    long product_ID;

    public void setIDProduct(long productID)
    {
        pagination = null;
        items = null;
        this.product_ID = productID;
    }
    long topic_ID;

    public void setIDTopic(long topicID)
    {
        pagination2 = null;
        items2 = null;
        this.topic_ID = topicID;
    }

    public PaginationHelper getPagination()
    {
        if (pagination == null)
        {
            pagination = new PaginationHelper(4)
            {
                @Override
                public int getItemsCount()
                {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel()
                {
                    return new ListDataModel(getFacade().findRange(new int[]
                            {
                                getPageFirstItem(), getPageFirstItem() + getPageSize()
                            }));
                }
            };
        }
        return pagination;
    }

    public String prepareList()
    {
        recreateModel();
        return "List";
    }

    public String returnSummaryOfProduct()
    {
        String text = current.getDescription();
        if ((current.getDescription().length() > 255))
        {
            return current.getDescription().substring(0, 255).concat("....");
        } else
        {
            return current.getDescription().substring(0, (text.length())).concat("....");
        }

    }

    public String prepareView()
    {
        current = (ProductTbl) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "product_detail.xhtml";
    }

    public String prepareCategoryView()
    {
//        current = (ProductTbl) getItems().getRowData();
//        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "product_Categories";
    }

    public String prepareCreate()
    {
        current = new ProductTbl();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create()
    {
        try
        {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductTblCreated"));
            return prepareCreate();
        } catch (Exception e)
        {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit()
    {
        current = (ProductTbl) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update()
    {
        try
        {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductTblUpdated"));
            return "View";
        } catch (Exception e)
        {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy()
    {
        current = (ProductTbl) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView()
    {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0)
        {
            return "View";
        } else
        {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy()
    {
        try
        {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductTblDeleted"));
        } catch (Exception e)
        {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem()
    {
        int count = getFacade().count();
        if (selectedItemIndex >= count)
        {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count)
            {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0)
        {
            current = getFacade().findRange(new int[]
                    {
                        selectedItemIndex, selectedItemIndex + 1
                    }).get(0);
        }
    }

    public DataModel getItems()
    {
        if (items == null)
        {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    public DataModel getItems2()
    {
        if (items2 == null)
        {
            items2 = getSimilarProductPagination().createPageDataModel();
        }
        return items2;
    }

    private void recreateModel()
    {
        items = null;
    }

    private void recreateSimilarModel()
    {
        items2 = null;
    }

    private void recreatePagination()
    {
        pagination = null;
    }

    public String next()
    {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String nextSimilarPage()
    {
        getSimilarProductPagination().nextPage();
        recreateSimilarModel();
        return "List";
    }

    public String previous()
    {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public String previousSimilarPage()
    {
        getSimilarProductPagination().previousPage();
        recreateSimilarModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany()
    {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne()
    {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = ProductTbl.class)
    public static class ProductTblControllerConverter implements Converter
    {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value)
        {
            if (value == null || value.length() == 0)
            {
                return null;
            }
            ProductTblController controller = (ProductTblController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "productTblController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value)
        {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value)
        {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object)
        {
            if (object == null)
            {
                return null;
            }
            if (object instanceof ProductTbl)
            {
                ProductTbl o = (ProductTbl) object;
                return getStringKey(o.getIdProduct());
            } else
            {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ProductTbl.class.getName());
            }
        }
    }

    //===================================  added functions ====================================================================
    public PaginationHelper getProductAttPagination()
    {
        if (pagination == null)
        {
            pagination = new PaginationHelper(10)
            {
                @Override
                public int getItemsCount()
                {
                    return getFacade().count(product_ID, "idProductAttributeValueConst", "productTblidProduct");
                }

                @Override
                public DataModel createPageDataModel()
                {
                    return new ListDataModel(getFacade().findRange(new int[]
                            {
                                getPageFirstItem(), getPageFirstItem() + getPageSize()
                            }, product_ID, "productTblidProduct", "idProduct"));
                }
            };
        }
        return pagination;
    }

    public PaginationHelper getSimilarProductPagination()
    {
        if (pagination2 == null)
        {
            pagination2 = new PaginationHelper(3)
            {
                @Override
                public int getItemsCount()
                {
                    return getFacade().count(topic_ID, "topicsidTopics", "idTopics", "idProduct", product_ID);
                }

                @Override
                public DataModel createPageDataModel()
                {
                    return new ListDataModel(getFacade().findSimilarProductsRange(new int[]
                            {
                                getPageFirstItem(), getPageFirstItem() + getPageSize()
                            }, topic_ID, "topicsidTopics", "idTopics", "idProduct", product_ID));
                }
            };
        }
        return pagination2;
    }

    public String pageSimilar(int pageIndex)
    {
        getSimilarProductPagination().GetPage(pageIndex);
        recreateSimilarModel();
        return "List";
    }

//============================================================= feacheredPagination ====================================================
    private void recreateFeacherModel()
    {
        feacherItems = null;
    }

    public String prepareFeacherView()
    {
        current = (ProductTbl) getFeacherItems().getRowData();
        selectedItemIndex = feacherPagination.getPageFirstItem() + getFeacherItems().getRowIndex();
        return "product_detail.xhtml";
    }

    public DataModel getFeacherItems()
    {
        if (feacherItems == null)
        {
            feacherItems = getFeacherProductPagination().createPageDataModel();
        }
        return feacherItems;
    }

    public String pageFeacher(int pageIndex)
    {
        //current.getProductAttValVarientList().size();
        getSimilarProductPagination().GetPage(pageIndex);
        recreateFeacherModel();
        return "List";
    }

    public String previousFeacherPage()
    {
        if (getFeacherProductPagination().isHasPreviousPage())
        {
            getFeacherProductPagination().previousPage();
        } else
        {
            int pageIndexFeacher = (getFeacherProductPagination().getItemsCount() % 4);
            getFeacherProductPagination().GetPage((pageIndexFeacher == 0) ? getFeacherProductPagination().getItemsCount() / 4 : (getFeacherProductPagination().getItemsCount() / 4) - 1);
        }

        recreateFeacherModel();
        return "List";
    }

    public String nextFeacherPage()
    {
        
        if (getFeacherProductPagination().getPageLastItem() + getFeacherProductPagination().getPageSize() > getFeacherProductPagination().getItemsCount() && getFeacherProductPagination().getItemsCount() % getFeacherProductPagination().getPageSize() < 4)
        {
            getFeacherProductPagination().GetPage(0);
        } else
        {
            getFeacherProductPagination().nextPage();
        }
        recreateFeacherModel();
        return "List";
    }

    public PaginationHelper getFeacherProductPagination()
    {
        if (feacherPagination == null)
        {
            feacherPagination = new PaginationHelper(12)
            {
                @Override
                public int getItemsCount()
                {
                    return getFacade().count(true, "feacher");
                }

                @Override
                public DataModel createPageDataModel()
                {//getPageFirstItem() + getPageSize()

                    return new ListDataModel(getFacade().findRange(new int[]
                            {
                                getPageFirstItem(), getPageFirstItem() + getPageSize()
                            }, true, "feacher"));
                }
            };
        }
        return feacherPagination;
    }
    //========================================================= end feacherPagination =================================================

    //========================================================= latestPagination =======================================================
    private void recreateLatestModel()
    {
        latestItems = null;
    }

    public String prepareLatestView()
    {
        current = (ProductTbl) getLatestItems().getRowData();
        selectedItemIndex = latestPagination.getPageFirstItem() + getLatestItems().getRowIndex();
        return "product_detail.xhtml";
    }

    public DataModel getLatestItems()
    {
        if (latestItems == null)
        {
            latestItems = getLatestProductPagination().createPageDataModel();
        }
        return latestItems;
    }

    public String pageLatest(int pageIndex)
    {
        getLatestProductPagination().GetPage(pageIndex);
        recreateLatestModel();
        return "List";
    }

    public String previousLatestPage()
    {
        getLatestProductPagination().previousPage();
        recreateLatestModel();
        return "List";

    }

    public String nextLatestPage()
    {
        getLatestProductPagination().nextPage();
        recreateLatestModel();
        return "List";
    }

    public PaginationHelper getLatestProductPagination()
    {
        if (latestPagination == null)
        {
            latestPagination = new PaginationHelper(6)
            {
                @Override
                public int getItemsCount()
                {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel()
                {
                    return new ListDataModel(getFacade().findRange_Orderd(new int[]
                            {
                                getPageFirstItem(), getPageFirstItem() + getPageSize()
                            }, true, "registerDate"));
                }
            };
        }
        return latestPagination;
    }
    
    public int[] featuresGroupArray()
    {
        int[] fga = new int[getFeacherItems().getRowCount() / 4];
        for(int i = 0; i< fga.length; i++)
        {
            fga[i] = i*4;
        }
        return fga;
    }
    //========================================================= end latestPagination ==================================================
}
