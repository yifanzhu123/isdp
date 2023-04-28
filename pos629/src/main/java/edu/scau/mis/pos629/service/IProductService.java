package edu.scau.mis.pos629.service;

import edu.scau.mis.pos629.domain.ProductDescription;

import java.util.List;

public interface IProductService {
    /**
     * 根据SN找产品
     * @param productSn
     * @return
     */
    ProductDescription getProductBySn(String productSn);
    /**
     * 列出所有产品
     * @return
     */
    List<ProductDescription> listAllProduct();
    /**
     * 查询单个产品
     * @param productId 主键
     * @return 对象
     */
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
