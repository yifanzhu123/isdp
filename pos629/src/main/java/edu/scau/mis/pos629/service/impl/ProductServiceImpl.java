package edu.scau.mis.pos629.service.impl;

import edu.scau.mis.pos629.mapper.ProductCatalog;
import edu.scau.mis.pos629.mapper.ProductMapper;
import edu.scau.mis.pos629.service.IProductService;
import edu.scau.mis.pos629.domain.ProductDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public ProductDescription getProductBySn(String productSn) {
        return productMapper.getProductBySn(productSn);
    }

    @Override
    public List<ProductDescription> listAllProduct() {
        return productMapper.listAllProduct();
    }

    @Override
    public ProductDescription selectProductById(Long productId) {
        return productMapper.selectProductById(productId);
    }

    @Override
    public List<ProductDescription> selectProductList(ProductDescription productDescription) {
        return productMapper.selectProductList(productDescription);
    }

    @Override
    public int insertProduct(ProductDescription productDescription) {
        productDescription.setCreateTime(new Date());
        return productMapper.insertProduct(productDescription);
    }

    @Override
    public int updateProduct(ProductDescription productDescription) {
        productDescription.setUpdateTime(new Date());
        return productMapper.updateProduct(productDescription);
    }

    @Override
    public int deleteProductById(Long productId) {
        return productMapper.deleteProductById(productId);
    }
}
