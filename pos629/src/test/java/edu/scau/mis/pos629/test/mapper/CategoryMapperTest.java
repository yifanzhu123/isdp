package edu.scau.mis.pos629.test.mapper;

import edu.scau.mis.pos629.domain.Category;
import edu.scau.mis.pos629.domain.ProductDescription;
import edu.scau.mis.pos629.mapper.CategoryMapper;
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
class CategoryMapperTest {
    @Autowired
    CategoryMapper categoryMapper;
    @Test
    void getCategoryById() {
        Category category = categoryMapper.getCategoryById(new Long(1));
        Assert.isTrue(category.getCategoryId()==1,"测试根据sn查询");
    }

    @Test
    void listAllCategory() {
        List<Category> list = categoryMapper.listAllCategory();
        Assert.isTrue(list.size()==4,"测试查询所有");
    }

    @Test
    void insertCategory() {
        Category category = new Category();
        category.setCategoryId(new Long(4));
        category.setCategoryName("零食");
        Assert.isTrue(categoryMapper.insertCategory(category)==1,"测试插入");
    }

    @Test
    void deleteCategoryById() {
        Category category = categoryMapper.getCategoryById(new Long(3));
        Assert.isTrue(categoryMapper.deleteCategoryById(category.getCategoryId())==1,"测试删除");
    }

    @Test
    void updateCategory() {
        Category category = categoryMapper.getCategoryById(new Long(1));
        category.setCategoryName("清洁用具");
        Assert.isTrue(categoryMapper.updateCategory(category)==1,"测试更新");
    }
}