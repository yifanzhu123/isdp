package edu.scau.mis.pos629.service.impl;

import edu.scau.mis.pos629.mapper.CategoryMapper;

import edu.scau.mis.pos629.service.ICategoryService;
import edu.scau.mis.pos629.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryMapper.getCategoryById(categoryId);
    }

    @Override
    public List<Category> listAllCategory() {

        return categoryMapper.listAllCategory();
    }

    @Override
    public int insertCategory(Category category) {
        category.setCreateTime(new Date());
        return categoryMapper.insertCategory(category);
    }

    @Override
    public int deleteCategoryById(Long categoryId) {

        return categoryMapper.deleteCategoryById(categoryId);
    }

    @Override
    public int updateCategory(Category category) {
        category.setUpdateTime(new Date());
        return categoryMapper.updateCategory(category);
    }

    @Override
    public List<Category> selectCategoryList(Category category) {
        return  categoryMapper.selectCategoryList(category);
    }
}
