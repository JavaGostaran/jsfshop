package jsfshop.jsf;

import antlr.collections.List;
import jsfshop.dao.ProductAttributeValueConst;
import jsfshop.jsf.util.JsfUtil;
import jsfshop.jsf.util.PaginationHelper;
import jsfshop.bean.ProductAttributeValueConstFacade;

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

@ManagedBean(name = "productAttributeValueConstController")
@SessionScoped
public class ProductAttributeValueConstController implements Serializable {

    private ProductAttributeValueConst current;
    private DataModel items = null;
    @EJB
    private jsfshop.bean.ProductAttributeValueConstFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ProductAttributeValueConstController() {
    }

    public ProductAttributeValueConst getSelected() {
        if (current == null) {
            current = new ProductAttributeValueConst();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProductAttributeValueConstFacade getFacade() {
        return ejbFacade;
    }
        long product_ID;
        public void setIDProduct(long productID)
        {
            pagination = null;
            items = null;
            this.product_ID = productID;
        }
        

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (ProductAttributeValueConst) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ProductAttributeValueConst();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductAttributeValueConstCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (ProductAttributeValueConst) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductAttributeValueConstUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
         
        public PaginationHelper getProductAttPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(100) {
                @Override
                public int getItemsCount() {
                    return getFacade().count(product_ID,"productTblidProduct","idProduct");
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()},product_ID,"productTblidProduct","idProduct"));
                }
            };
        }
        return pagination;
    }
    public String destroy() {
        current = (ProductAttributeValueConst) getItems().getRowData();
        
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductAttributeValueConstDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

   public DataModel getProductAttConstItems() {
        if (items == null) {
            items = getProductAttPagination().createPageDataModel();
        }
        return items;
    }
   
   
  public DataModel getProductAttConstItems(long dd) {
        if (items == null) {
            items = getProductAttPagination().createPageDataModel();
        }
        return items;
    }
    public DataModel getItems() {
        if (items == null) {
            setIDProduct(4);
            items = getPagination().createPageDataModel();
        }
        return items;
    }
    public DataModel getAttConstItemsOfProduct() {
        if (items == null) {
            setIDProduct(4);
            items = getProductAttPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

        public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }
    public String nextProAttConst() {
        getProductAttPagination().nextPage();
        recreateModel();
        return "List";
    }

        public String previousProAttConst() {
        getProductAttPagination().previousPage();
        recreateModel();
        return "List";
    }
    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = ProductAttributeValueConst.class)
    public static class ProductAttributeValueConstControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProductAttributeValueConstController controller = (ProductAttributeValueConstController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "productAttributeValueConstController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ProductAttributeValueConst) {
                ProductAttributeValueConst o = (ProductAttributeValueConst) object;
                return getStringKey(o.getIdProductAttributeValueConst());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ProductAttributeValueConst.class.getName());
            }
        }
    }
}
