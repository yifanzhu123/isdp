package edu.scau.mis.pos629.view;


import edu.scau.mis.pos629.domain.ProductDescription;
import edu.scau.mis.pos629.mapper.ProductCatalog;
import edu.scau.mis.pos629.service.ISaleService;
import edu.scau.mis.pos629.vo.SaleItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
/**
 * 记账本
 * GRASP:控制器
 */
@Component
public class Register {
    @Autowired
    private ISaleService saleService;

    @Autowired
    private ProductCatalog productCatalog;

    public void makeNewSale(){
        saleService.makeNewSale();
    }

    public SaleItemVo enterItem(String itemSn, int quantity){
        SaleItemVo saleItemVo = null;
        ProductDescription product=productCatalog.getProductBySn(itemSn);
        if(product!=null){
            saleService.makeLineItem(product,quantity);
            saleItemVo=new SaleItemVo(itemSn,product.getProductName(),product.getPrice(),quantity);
        }
        return saleItemVo;
    }

    public BigDecimal endSale(){
        return saleService.endSale();
    }

    public  BigDecimal makePayment(BigDecimal cashTendered){
        return saleService.makePayment(cashTendered);
    }
}
