package edu.scau.mis.pos629.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.mis.pos629.core.domain.AjaxResult;
import edu.scau.mis.pos629.domain.Category;
import edu.scau.mis.pos629.domain.ProductDescription;
import edu.scau.mis.pos629.service.ICategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private Category category;
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/{categoryId}")
    public Category getById(@PathVariable("categoryId") Long categoryId){
        return categoryService.getCategoryById(categoryId);
    }
    @GetMapping({"/listAll"})
    public AjaxResult ListAllCategory(){
        return AjaxResult.success(categoryService.listAllCategory());
    }

    @PostMapping
    @ApiOperation("新增产品")
    public AjaxResult add(@RequestBody Category category) {
        int rows = categoryService.insertCategory(category);
        return rows > 0 ? AjaxResult.success("添加类别成功") : AjaxResult.error("添加类别失败");
    }

    @PutMapping
    @ApiOperation("修改类别")
    public AjaxResult edit(@RequestBody Category category) {
        int rows = categoryService.updateCategory(category);
        return rows > 0 ? AjaxResult.success("修改类别成功") : AjaxResult.error("修改类别失败");
    }

    @DeleteMapping("/{categoryId}")
    @ApiOperation("删除产品")
    public AjaxResult remove(@PathVariable Long categoryId) {
        int rows = categoryService.deleteCategoryById(categoryId);
        return rows > 0 ? AjaxResult.success("删除类别成功") : AjaxResult.error("删除类别失败");
    }
    @GetMapping("/page")
    @ApiOperation("分页查询")
    public AjaxResult page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, Category category) {
        PageHelper.startPage(pageNum,pageSize);
        List<Category> list = categoryService.selectCategoryList(category);
        PageInfo<List> pageInfo = new PageInfo(list);

        return AjaxResult.success(pageInfo);
    }
}
