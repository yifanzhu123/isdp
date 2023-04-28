package edu.scau.mis.pos629;

import edu.scau.mis.pos629.service.IProductService;
import edu.scau.mis.pos629.domain.ProductDescription;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private IProductService productService;
    @Test
    void getProductBySn() {
        ProductDescription product=productService.getProductBySn("1001");
        System.out.println(product);
        //Assertions.assertEquals(2L,product.getProductId(),"sn查询product失败");
    }

    @Test
    void listAllProduct() {
        List<ProductDescription> products=productService.listAllProduct();
        System.out.println(products);
    }
}