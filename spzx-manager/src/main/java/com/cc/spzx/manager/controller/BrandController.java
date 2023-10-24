package com.cc.spzx.manager.controller;

import com.cc.spzx.manager.service.BrandService;
import com.cc.spzx.model.entity.product.Brand;
import com.cc.spzx.model.vo.common.Result;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "品牌接口")
@RestController
@RequestMapping(value="/admin/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Operation(summary = "删除品牌")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") Long id) {
        brandService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "更新品牌")
    @PutMapping("/updateById")
    public Result updateById(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "添加品牌")
    @PostMapping("/save")
    public Result save(@RequestBody Brand brand) {
        brandService.save(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "品牌列表")
    @GetMapping("/{page}/{limit}")
    public Result findByPage(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        PageInfo<Brand> pageInfo = brandService.findByPage(page, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

}
