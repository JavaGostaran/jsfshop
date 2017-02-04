package jsfshop.jsf;

import jsfshop.dao.AttributeValues;
import jsfshop.jsf.util.JsfUtil;
import jsfshop.jsf.util.PaginationHelper;
import jsfshop.bean.AttributeValuesFacade;

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

@ManagedBean(name = "attributeValuesController")
@SessionScoped
public class AttributeValuesController implements Serializable
{

    private AttributeValues current;
    private DataModel items = null;
    private DataModel attributesItems = null;
    private DataModel attValuesOfProductItems = null;
    @EJB
    private jsfshop.bean.AttributeValuesFacade ejbFacade;
    private PaginationHelper pagination;
    private PaginationHelper attValuesOfProductPagination;
    private PaginationHelper attributesPagination;
    private int selectedItemIndex;

    public AttributeValuesController()
    {
    }

    public AttributeValues getSelected()
    {
        if (current == null)
        {
            current = new AttributeValues();
            selectedItemIndex = -1;
        }
        return current;
    }

    private AttributeValuesFacade getFacade()
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
        current = (AttributeValues) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate()
    {
        current = new AttributeValues();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create()
    {
        try
        {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AttributeValuesCreated"));
            return prepareCreate();
        } catch (Exception e)
        {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit()
    {
        current = (AttributeValues) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update()
    {
        try
        {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AttributeValuesUpdated"));
            return "View";
        } catch (Exception e)
        {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy()
    {
        current = (AttributeValues) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AttributeValuesDeleted"));
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

    @FacesConverter(forClass = AttributeValues.class)
    public static class AttributeValuesControllerConverter implements Converter
    {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value)
        {
            if (value == null || value.length() == 0)
            {
                return null;
            }
            AttributeValuesController controller = (AttributeValuesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "attributeValuesController");
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
            if (object instanceof AttributeValues)
            {
                AttributeValues o = (AttributeValues) object;
                return getStringKey(o.getIdAttributeValues());
            } else
            {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + AttributeValues.class.getName());
            }
        }
    }
    //========================================================= added functions ====================================================
    long AttibiuteValueID;
    
    public void setAttributeValueID(long tempID)
    {
        attributesPagination = null;
        attributesItems = null;
        this.AttibiuteValueID = tempID;
    }
    
    long product_ID;

    public void setIDProduct(long productID)
    {
        attributesPagination = null;
        attributesItems = null;
        this.product_ID = productID;
    }
   

    public DataModel getAttributesItems()
    {
        //if (attributesItems == null)
        {
            attributesItems = getAttributesPagination().createPageDataModel();
        }
        return attributesItems;
    }

    public PaginationHelper getAttributesPagination()
    {
        //if (attributesPagination == null)
        {
            attributesPagination = new PaginationHelper(10)
            {
                @Override
                public int getItemsCount()
                {
                    return getFacade().countAttributesOfProduct(product_ID,AttibiuteValueID);
                }

                @Override
                public DataModel createPageDataModel()
                {
                    return new ListDataModel(getFacade().findRangeAttributesOfProduct(new int[]
                            {
                                getPageFirstItem(), getPageFirstItem() + getPageSize()
                            },product_ID,AttibiuteValueID));
                }
            };
        }
        return attributesPagination; 
    }

    public DataModel getattValuesOfProductItems()
    {
        if (attValuesOfProductItems == null)
        {
            attValuesOfProductItems = getPagination().createPageDataModel();
        }
        return attValuesOfProductItems;
    }

    public PaginationHelper getAttValuesOfProductPagination()
    {
        if (attValuesOfProductPagination == null)
        {
            attValuesOfProductPagination = new PaginationHelper(10)
            {
                @Override
                public int getItemsCount()
                {
                    return getFacade().count(4,"productAttValVarientList","productTblidProduct","idProduct");
                }

                @Override
                public DataModel createPageDataModel()
                {
                    return new ListDataModel(getFacade().findRange(new int[]
                            {
                                getPageFirstItem(), getPageFirstItem() + getPageSize()
                            },4,"productAttValVarientList","productTblidProduct","idProduct"));
                }
            };
        }
        return attValuesOfProductPagination;


        //==============================================================================================================================
    }
}
