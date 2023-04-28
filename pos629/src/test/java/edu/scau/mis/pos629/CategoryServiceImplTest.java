package edu.scau.mis.pos629;

import edu.scau.mis.pos629.domain.ProductDescription;
import edu.scau.mis.pos629.service.ICategoryService;
import edu.scau.mis.pos629.domain.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CategoryServiceImplTest {
    @Autowired
    ICategoryService categoryService;
    @Test
    void getCategoryById() {
        Category category=categoryService.getCategoryById(1L);
        System.out.println(category);
    }

    @Test
    void listAllCategory() {
        List<Category> categories=categoryService.listAllCategory();
        System.out.println(categories);
    }
}