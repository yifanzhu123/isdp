package edu.scau.mis.pos629.controller;

import edu.scau.mis.pos629.core.domain.AjaxResult;
import edu.scau.mis.pos629.domain.ProductDescription;
import edu.scau.mis.pos629.service.IProductService;
import edu.scau.mis.pos629.service.ISaleService;
import edu.scau.mis.pos629.vo.SaleItemVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("sale")
public class SaleController {
    @Autowired
    private ISaleService saleService;

    @Autowired
    private IProductService productService;

    /**
     * 开始一次新的销售
     */
    @GetMapping("/makeNewSale")
    public AjaxResult makeNewSale(){
        return AjaxResult.success(saleService.makeNewSale());
    }
    /**
     * 输入商品
     * @param itemSn 产品编号
     * @param quantity 数量
     * 代码有部分业务逻辑，不符合controller内聚设计要求。
     * 这样写的原因是因为代码来自OOAD案例的Register，
     * Register是门面控制器，本身是domian类，允许写少量业务逻辑。
     */
    @GetMapping("/enterItem")
    public AjaxResult enterItem(@RequestParam("itemSn") String itemSn, @RequestParam("quantity") int quantity){
        SaleItemVo saleItemVo = null;
        ProductDescription product = productService.getProductBySn(itemSn);
        if(product != null) {
            saleService.makeLineItem(product, quantity);
            saleItemVo = new SaleItemVo(itemSn, product.getProductName(), product.getPrice(), quantity);
            return AjaxResult.success(saleItemVo);
        }
        else{
            return AjaxResult.error("未找到商品");
        }

    }
    /**
     * 结束销售
     */
    @GetMapping("/endSale")
    public AjaxResult endSale(){
        return AjaxResult.success(saleService.endSale());
    }
    /**
     * 确认支付
     * @param cashTendered 支付金额
     */
    @GetMapping("/makePayment")
    public AjaxResult makePayment(@RequestParam("cashTendered") BigDecimal cashTendered){
        return AjaxResult.success("付款成功",saleService.makePayment(cashTendered));
    }

    @PostMapping("/changeQuantity")
    @ApiOperation("修改一行订单明细数量")
    public AjaxResult changeQuantity(@RequestBody SaleItemVo saleItemVo){
        saleService.changeQuantityOfSaleItem(saleItemVo.getItemSn(),saleItemVo.getQuantity());
        return AjaxResult.success("修改订单明细数量成功");
    }

    @GetMapping("/deleteSaleItem/{itemSn}")
    @ApiOperation("删除一行订单明细")
    public AjaxResult deleteSaleItem(@PathVariable("itemSn") String itemSn){
        saleService.deleteSaleItem(itemSn);
        return AjaxResult.success("删除订单明细成功");
    }
}
