/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfshop.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author j2ee1
 */
@Entity
@Table(name = "ProductTbl")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ProductTbl.findAll", query = "SELECT p FROM ProductTbl p"),
    @NamedQuery(name = "ProductTbl.findByIdProduct", query = "SELECT p FROM ProductTbl p WHERE p.idProduct = :idProduct"),
    @NamedQuery(name = "ProductTbl.findByRegisterDate", query = "SELECT p FROM ProductTbl p WHERE p.registerDate = :registerDate"),
    @NamedQuery(name = "ProductTbl.findByIsSuggest", query = "SELECT p FROM ProductTbl p WHERE p.isSuggest = :isSuggest"),
    @NamedQuery(name = "ProductTbl.findByFeacher", query = "SELECT p FROM ProductTbl p WHERE p.feacher = :feacher"),
    @NamedQuery(name = "ProductTbl.findByBestSeller", query = "SELECT p FROM ProductTbl p WHERE p.bestSeller = :bestSeller")
})
public class ProductTbl implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProduct")
    private Long idProduct;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "titleProduct")
    private String titleProduct;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registerDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isSuggest")
    private boolean isSuggest;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "feacher")
    private Boolean feacher;
    @Column(name = "bestSeller")
    private Boolean bestSeller;
    @Lob
    @Size(max = 16777215)
    @Column(name = "summaryProduct")
    private String summaryProduct;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productTblidProduct")
    private List<ProductDiscountTbl> productDiscountTblList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productYblidProduct")
    private List<ImageProduct> imageProductList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productTblidProduct")
    private List<ProductAttribute> productAttributeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productTblidProduct")
    private List<ProductsFactorTbl> productsFactorTblList;
    @JoinColumn(name = "Topics_idTopics", referencedColumnName = "idTopics")
    @ManyToOne(optional = false)
    private Topics topicsidTopics;
    @JoinColumn(name = "Brands_idBrands", referencedColumnName = "idBrands")
    @ManyToOne(optional = false)
    private Brands brandsidBrands;
    @OneToMany(mappedBy = "productTblidProduct")
    private List<ProductTbl> productTblList;
    @JoinColumn(name = "ProductTbl_idProduct", referencedColumnName = "idProduct")
    @ManyToOne
    private ProductTbl productTblidProduct;
    @JoinColumn(name = "GarantyType_idGarantyType", referencedColumnName = "idGarantyType")
    @ManyToOne(optional = false)
    private GarantyType garantyTypeidGarantyType;
    @OneToMany(mappedBy = "productTblidProduct")
    private List<UsersFavoritsTbl> usersFavoritsTblList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productTblidProduct")
    private List<ProductAttributeValueConst> productAttributeValueConstList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productTblidProduct")
    private List<ProductAttValVarient> productAttValVarientList;

    public ProductTbl()
    {
    }

    public ProductTbl(Long idProduct)
    {
        this.idProduct = idProduct;
    }

    public ProductTbl(Long idProduct, String titleProduct, Date registerDate, boolean isSuggest)
    {
        this.idProduct = idProduct;
        this.titleProduct = titleProduct;
        this.registerDate = registerDate;
        this.isSuggest = isSuggest;
    }

    public Long getIdProduct()
    {
        return idProduct;
    }

    public void setIdProduct(Long idProduct)
    {
        this.idProduct = idProduct;
    }

    public String getTitleProduct()
    {
        return titleProduct;
    }

    public void setTitleProduct(String titleProduct)
    {
        this.titleProduct = titleProduct;
    }

    public Date getRegisterDate()
    {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate)
    {
        this.registerDate = registerDate;
    }

    public boolean getIsSuggest()
    {
        return isSuggest;
    }

    public void setIsSuggest(boolean isSuggest)
    {
        this.isSuggest = isSuggest;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Boolean getFeacher()
    {
        return feacher;
    }

    public void setFeacher(Boolean feacher)
    {
        this.feacher = feacher;
    }

    public Boolean getBestSeller()
    {
        return bestSeller;
    }

    public void setBestSeller(Boolean bestSeller)
    {
        this.bestSeller = bestSeller;
    }

    public String getSummaryProduct()
    {
        return summaryProduct;
    }

    public void setSummaryProduct(String summaryProduct)
    {
        this.summaryProduct = summaryProduct;
    }

    @XmlTransient
    public List<ProductDiscountTbl> getProductDiscountTblList()
    {
        return productDiscountTblList;
    }

    public void setProductDiscountTblList(List<ProductDiscountTbl> productDiscountTblList)
    {
        this.productDiscountTblList = productDiscountTblList;
    }

    @XmlTransient
    public List<ImageProduct> getImageProductList()
    {
        return imageProductList;
    }

    public void setImageProductList(List<ImageProduct> imageProductList)
    {
        this.imageProductList = imageProductList;
    }

    @XmlTransient
    public List<ProductAttribute> getProductAttributeList()
    {
        return productAttributeList;
    }

    public void setProductAttributeList(List<ProductAttribute> productAttributeList)
    {
        this.productAttributeList = productAttributeList;
    }

    @XmlTransient
    public List<ProductsFactorTbl> getProductsFactorTblList()
    {
        return productsFactorTblList;
    }

    public void setProductsFactorTblList(List<ProductsFactorTbl> productsFactorTblList)
    {
        this.productsFactorTblList = productsFactorTblList;
    }

    public Topics getTopicsidTopics()
    {
        return topicsidTopics;
    }

    public void setTopicsidTopics(Topics topicsidTopics)
    {
        this.topicsidTopics = topicsidTopics;
    }

    public Brands getBrandsidBrands()
    {
        return brandsidBrands;
    }

    public void setBrandsidBrands(Brands brandsidBrands)
    {
        this.brandsidBrands = brandsidBrands;
    }

    @XmlTransient
    public List<ProductTbl> getProductTblList()
    {
        return productTblList;
    }

    public void setProductTblList(List<ProductTbl> productTblList)
    {
        this.productTblList = productTblList;
    }

    public ProductTbl getProductTblidProduct()
    {
        return productTblidProduct;
    }

    public void setProductTblidProduct(ProductTbl productTblidProduct)
    {
        this.productTblidProduct = productTblidProduct;
    }

    public GarantyType getGarantyTypeidGarantyType()
    {
        return garantyTypeidGarantyType;
    }

    public void setGarantyTypeidGarantyType(GarantyType garantyTypeidGarantyType)
    {
        this.garantyTypeidGarantyType = garantyTypeidGarantyType;
    }

    @XmlTransient
    public List<UsersFavoritsTbl> getUsersFavoritsTblList()
    {
        return usersFavoritsTblList;
    }

    public void setUsersFavoritsTblList(List<UsersFavoritsTbl> usersFavoritsTblList)
    {
        this.usersFavoritsTblList = usersFavoritsTblList;
    }

    @XmlTransient
    public List<ProductAttributeValueConst> getProductAttributeValueConstList()
    {
        return productAttributeValueConstList;
    }

    public void setProductAttributeValueConstList(List<ProductAttributeValueConst> productAttributeValueConstList)
    {
        this.productAttributeValueConstList = productAttributeValueConstList;
    }

    @XmlTransient
    public List<ProductAttValVarient> getProductAttValVarientList()
    {
        return productAttValVarientList;
    }

    public void setProductAttValVarientList(List<ProductAttValVarient> productAttValVarientList)
    {
        this.productAttValVarientList = productAttValVarientList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idProduct != null ? idProduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductTbl))
        {
            return false;
        }
        ProductTbl other = (ProductTbl) object;
        if ((this.idProduct == null && other.idProduct != null) || (this.idProduct != null && !this.idProduct.equals(other.idProduct)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jsfshop.dao.ProductTbl[ idProduct=" + idProduct + " ]";
    }
    
}
