package edu.scau.mis.pos629.service;

import edu.scau.mis.pos629.domain.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long categoryId);
    List<Category>listAllCategory();
    int insertCategory(Category category);
    int deleteCategoryById(Long categoryId);
    int updateCategory(Category category);
    public List<Category> selectCategoryList(Category category);
}
