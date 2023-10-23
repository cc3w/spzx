package com.cc.spzx.manager.controller;

import com.cc.spzx.manager.service.CategoryService;
import com.cc.spzx.model.entity.product.Category;
import com.cc.spzx.model.vo.common.Result;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "分类接口")
@RestController
@RequestMapping("/admin/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "根据parentId获取下级节点")
    //这个parentId就是前端传过来当前节点的id
    @GetMapping("/findByParentId/{parentId}")
    public Result findByParentId(@PathVariable("parentId") Long parentId) {
        List<Category> categoryList = categoryService.findByParentId(parentId);
        return Result.build(categoryList, ResultCodeEnum.SUCCESS);
    }
}
