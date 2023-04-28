package edu.scau.mis.pos629.service;

import edu.scau.mis.pos629.domain.ProductDescription;
import edu.scau.mis.pos629.domain.Sale;

import java.math.BigDecimal;

public interface ISaleService {
    //开始新的销售
    Sale makeNewSale();

    /**
     * 实例化SaleItem
     * @param product 商品对象
     * @param quantity 数量
     */
    void makeLineItem(ProductDescription product,int quantity);

    /**
     * 借书订单商品录入
     * @return 总金额
     */
    BigDecimal endSale();

    /**
     * 订单支付
     * @param cashTendered 付款金额
     * @return 找零
     */
    BigDecimal makePayment(BigDecimal cashTendered);
    /**
    * 修改一行订单明细数量
    */
    void changeQuantityOfSaleItem(String itemSn, int quantity);
    /**
     * 删除一行订单明细
     */
    void deleteSaleItem(String itemSn);
}
