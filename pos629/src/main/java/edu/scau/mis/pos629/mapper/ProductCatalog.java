package edu.scau.mis.pos629.mapper;

import edu.scau.mis.pos629.domain.Category;
import edu.scau.mis.pos629.domain.ProductDescription;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Repository
public class ProductCatalog {
    private final HashMap<String, ProductDescription> products = new HashMap<>();
    private final HashMap<String,Category> categories = new HashMap<>();
    public ProductCatalog(){
        Category category1=new Category(1L,"文具");
        Category category2=new Category(2L,"日用品");
        ProductDescription product1 = new ProductDescription(1L,"1001",category1,"钢笔",new BigDecimal("65.00"));
        ProductDescription product2 = new ProductDescription(2L,"1002",category1,"日记本",new BigDecimal("20.00"));
        ProductDescription product3 = new ProductDescription(3L,"1003",category1,"铅笔盒 ",new BigDecimal("45.00"));
        ProductDescription product4 = new ProductDescription(4L,"1004",category2,"毛巾",new BigDecimal("10.00"));
        ProductDescription product5 = new ProductDescription(5L,"1005",category2,"香波",new BigDecimal("80.00"));
        ProductDescription product6 = new ProductDescription(6L,"1006",category2,"拖鞋",new BigDecimal("30.00"));
        products.put(product1.getProductSn(),product1);
        products.put(product2.getProductSn(),product2);
        products.put(product3.getProductSn(),product3);
        products.put(product4.getProductSn(),product4);
        products.put(product5.getProductSn(),product5);
        products.put(product6.getProductSn(),product6);
        categories.put(category1.getCategoryId().toString(),category1);
        categories.put(category2.getCategoryId().toString(),category2);
    }
    //根据SN得到product
    public ProductDescription getProductBySn(String productSn){
        return products.get(productSn);
    }
    //列出所有product
    public List<ProductDescription> listAllProduct(){
        List<ProductDescription> pds=new ArrayList<>();
        pds.addAll(products.values());
        return pds;
    }
    //根据id得到category
    public Category getCategoryById(Long categoryId){
        return categories.get(categoryId.toString());
    }
    //列出所有category
    public List<Category> listAllCategory(){
        List<Category> categoryList=new ArrayList<>();
        categoryList.addAll(categories.values());
        return categoryList;
    }
}

