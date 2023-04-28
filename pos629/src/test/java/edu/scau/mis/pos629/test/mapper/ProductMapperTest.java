package edu.scau.mis.pos629.test.mapper;

import edu.scau.mis.pos629.domain.ProductDescription;
import edu.scau.mis.pos629.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)  // 是否回滚，设为false即会更新数据库，设为true则不会更新。
class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    void getProductBySn() {
        ProductDescription p = productMapper.getProductBySn("1001");
        Assert.isTrue(p.getProductId()==1,"测试根据sn查询");
    }

    @Test
    void listAllProduct() {
        List<ProductDescription> list = productMapper.listAllProduct();
        Assert.isTrue(list.size()==6,"测试查询所有");
    }

    @Test
    void selectProductById() {
        ProductDescription p = productMapper.selectProductById(1L);
        Assert.hasText("铅笔","测试ID查询对象");
    }

    @Test
    void selectProductList() {
        ProductDescription param = new ProductDescription();
        param.setProductName("笔");
        List<ProductDescription> list = productMapper.selectProductList(param);
        Assert.hasLength("2","测试查询list");
    }

    @Test
    void insertProduct() {
        ProductDescription p = new ProductDescription();
        p.setProductName("牙膏");
        p.setProductSn("1007");
        p.setPrice(new BigDecimal("15.00"));
        Assert.isTrue(productMapper.insertProduct(p)==1,"测试插入");
    }

    @Test
    void updateProduct() {
        ProductDescription p = productMapper.getProductBySn("1007");
        p.setProductName("牙刷");
        Assert.isTrue(productMapper.updateProduct(p)==1,"测试更新");
    }

    @Test
    void deleteProductById() {
        ProductDescription p = productMapper.getProductBySn("1007");
        Assert.isTrue(productMapper.deleteProductById(p.getProductId())==1,"测试删除");
    }
}
