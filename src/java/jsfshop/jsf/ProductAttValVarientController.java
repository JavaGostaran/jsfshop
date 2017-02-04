package jsfshop.jsf;

import jsfshop.dao.ProductAttValVarient;
import jsfshop.jsf.util.JsfUtil;
import jsfshop.jsf.util.PaginationHelper;
import jsfshop.bean.ProductAttValVarientFacade;

import java.io.Serializable;
import java.math.BigInteger;
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

@ManagedBean(name = "productAttValVarientController")
@SessionScoped
public class ProductAttValVarientController implements Serializable
{

    private ProductAttValVarient current;
    private DataModel items = null;
    private DataModel catProductItems = null;
    @EJB
    private jsfshop.bean.ProductAttValVarientFacade ejbFacade;
    private PaginationHelper pagination;
    private PaginationHelper catProductPagination;
    private int selectedItemIndex;

    public ProductAttValVarientController()
    {
    }

    public ProductAttValVarient getSelected()
    {
        if (current == null)
        {
            current = new ProductAttValVarient();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProductAttValVarientFacade getFacade()
    {
        return ejbFacade;
    }

    public PaginationHelper getPagination()
    {
        if (pagination == null)
        {
            pagination = new PaginationHelper(10)
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

    public String prepareView()
    {
        current = (ProductAttValVarient) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate()
    {
        current = new ProductAttValVarient();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create()
    {
        try
        {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductAttValVarientCreated"));
            return prepareCreate();
        } catch (Exception e)
        {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit()
    {
        current = (ProductAttValVarient) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update()
    {
        try
        {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductAttValVarientUpdated"));
            return "View";
        } catch (Exception e)
        {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy()
    {
        current = (ProductAttValVarient) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductAttValVarientDeleted"));
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

    private void recreateModel()
    {
        items = null;
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

    public String previous()
    {
        getPagination().previousPage();
        recreateModel();
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

    @FacesConverter(forClass = ProductAttValVarient.class)
    public static class ProductAttValVarientControllerConverter implements Converter
    {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value)
        {
            if (value == null || value.length() == 0)
            {
                return null;
            }
            ProductAttValVarientController controller = (ProductAttValVarientController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "productAttValVarientController");
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
            if (object instanceof ProductAttValVarient)
            {
                ProductAttValVarient o = (ProductAttValVarient) object;
                return getStringKey(o.getIdProductAttValVarient());
            } else
            {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ProductAttValVarient.class.getName());
            }
        }
    }
    //================================================= added ====================================================================
    private PaginationHelper pagination2;
    private DataModel items2 = null;

    public PaginationHelper getProductAttVarPagination()
    {
        if (pagination2 == null)
        {
            pagination2 = new PaginationHelper(100)
            {
                @Override
                public int getItemsCount()
                {
                    return getFacade().count(4, "productTblidProduct", "idProduct");
                }

                @Override
                public DataModel createPageDataModel()
                {
                    return new ListDataModel(getFacade().AttributeVarientsGroupedOfProduct(new int[]
                            {
                                getPageFirstItem(), getPageFirstItem() + getPageSize()
                            }, 4, "productTblidProduct", "idProduct"));

                }
            };
        }
        return pagination2;
    }

    public DataModel getCountAttVarGroupedItemsOfProduct()
    {
        if (items2 == null)
        {
            //setIDProduct(4);
            items2 = getProductAttVarPagination().createPageDataModel();
        }
        return items2;
    }
    private PaginationHelper productAttVarientPagination;
    private DataModel productAttVarientItems = null;

    public PaginationHelper getProductAttVareintsPagination()
    {
        if (productAttVarientPagination == null)
        {
            productAttVarientPagination = new PaginationHelper(100)
            {
                @Override
                public int getItemsCount()
                {
                    return getFacade().count(4, "productTblidProduct", "idProduct");
                }

                @Override
                public DataModel createPageDataModel()
                {
                    return new ListDataModel(getFacade().findRange(new int[]
                            {
                                getPageFirstItem(), getPageFirstItem() + getPageSize()
                            }, 4, "productTblidProduct", "idProduct"));
                }
            };
        }
        return productAttVarientPagination;
    }

    public int SumOfProduct()
    {

        return (getFacade().sum(4, "productTblidProduct", "idProduct", "updateCount"));
    }

    public int Sum_Product(int productID)
    {

        return (getFacade().sum(productID, "productTblidProduct", "idProduct", "updateCount"));
    }
    
    public int Sum_ProductByCatID()
    {
        return (getFacade().sumByCatID(product_ID, cat_ID));
    }
    
    public BigInteger price_ProductByCatID()
    {
        return (getFacade().Price_ByCatID(product_ID, cat_ID).get(0).getUpdatePrice());
    }

    public DataModel getAttVarientItemsOfProduct()
    {
        if (productAttVarientItems == null)
        {
            setIDProduct(4);
            productAttVarientItems = getProductAttVarPagination().createPageDataModel();
        }
        return productAttVarientItems;
    }
    long product_ID;

    public void setIDProduct(long productID)
    {
        pagination = null;
        items = null;
        this.product_ID = productID;
    }
    
    //=========================================================== category pagination ==========================================
    
    public DataModel getCatProductItems()
    {
        if (catProductItems == null)
        {
            catProductItems = getCatProductPagination().createPageDataModel();
        }
        return catProductItems;
    }
    
    public PaginationHelper getCatProductPagination()
    {
        if (catProductPagination == null)
        {
            catProductPagination = new PaginationHelper(10)
            {
                @Override
                public int getItemsCount()
                {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel()
                {
                    return new ListDataModel(getFacade().catProductFindRange(new int[]
                            {
                                getPageFirstItem(), getPageFirstItem() + getPageSize()
                            },product_ID,cat_ID));
                }
            };
        }
        return catProductPagination;
    }
    
    
    long cat_ID;
    public void setCatID(long tempCatID)
    {
        catProductPagination = null;
        catProductItems = null;
        this.cat_ID = tempCatID;
    }
}
