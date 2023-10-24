package com.cc.spzx.manager.controller;

import com.cc.spzx.manager.service.CategoryBrandService;
import com.cc.spzx.model.dto.product.CategoryBrandDto;
import com.cc.spzx.model.entity.product.CategoryBrand;
import com.cc.spzx.model.vo.common.Result;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "品牌分类接口")
@RestController
@RequestMapping("/admin/product/categoryBrand")
public class CategoryBrandController {

    @Autowired
    private CategoryBrandService categoryBrandService;

    @Operation(summary = "删除品牌分类")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") Long id) {
        categoryBrandService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改品牌分类")
    @PutMapping("/updateById")
    public Result updateById(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.updateById(categoryBrand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "添加分类品牌表单")
    @PostMapping("/save")
    public Result save(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.save(categoryBrand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "根据条件查询分页")
    @GetMapping("/{page}/{limit}")
    public Result findByPage(@PathVariable("page")Integer page,
                             @PathVariable("limit")Integer limit,
                              CategoryBrandDto categoryBrandDto) {
        PageInfo<CategoryBrand> categoryBrand = categoryBrandService.findByPage(page, limit, categoryBrandDto);
        return Result.build(categoryBrand, ResultCodeEnum.SUCCESS);
    }
}
