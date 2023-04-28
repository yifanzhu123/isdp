package edu.scau.mis.pos629.mapper;

import edu.scau.mis.pos629.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

public interface CategoryMapper {
    //根据id得到category

    public Category getCategoryById(Long categoryId);
    //列出所有category
    public List<Category> listAllCategory();
    public int insertCategory(Category category);
    public int deleteCategoryById(Long categoryId);
    public int updateCategory(Category category);
    public List<Category> selectCategoryList(Category category);
}
