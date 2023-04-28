package edu.scau.mis.pos629.mapper;

import edu.scau.mis.pos629.domain.ProductDescription;


import java.util.List;

public interface ProductMapper {

    public ProductDescription getProductBySn(String productSn);
    public List<ProductDescription> listAllProduct();
    public ProductDescription selectProductById(Long productId);
    /**
     * 查询产品列表
     * @param productDescription 查询参数
     * @return 对象集合
     */
    public List<ProductDescription> selectProductList(ProductDescription productDescription);

    /**
     * 新增产品
     * @param productDescription
     * @return 影响记录数
     */
    public int insertProduct(ProductDescription productDescription);
    /**
     * 修改产品
     * @param productDescription
     * @return 影响记录数
     */
    public int updateProduct(ProductDescription productDescription);
    /**
     * 删除产品
     * @param productId 主键
     * @return 影响记录数
     */
    public int deleteProductById(Long productId);
}
