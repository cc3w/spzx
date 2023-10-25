package com.cc.spzx.manager.controller;

import com.cc.spzx.manager.service.ProductService;
import com.cc.spzx.model.entity.product.Product;
import com.cc.spzx.model.vo.common.Result;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "商品接口")
@RestController
@RequestMapping("/admin/product/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "上下架商品")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        productService.updateStatus(id, status);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "审核商品")
    @GetMapping("/updateAuditStatus/{id}/{auditStatus}")
    public Result updateAuditStatus(@PathVariable("id") Long id,
                                    @PathVariable("auditStatus") Integer auditStatus) {
        productService.updateAuditStatus(id, auditStatus);

        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除商品")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改商品")
    @PutMapping("/updateById")
    public Result updateById(@RequestBody Product product) {
        productService.updateById(product);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "根据id查找商品信息")
    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable("id") Long id) {
        Product product = productService.getById(id);
        return Result.build(product, ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "添加商品")
    @PostMapping("/save")
    public Result save(@RequestBody Product product) {
        productService.save(product);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "条件查询分页")
    @GetMapping("/{page}/{limit}")
    public Result findByPage(@PathVariable("page") Integer page,
                             @PathVariable("limit") Integer limit,
                             Product product) {
        PageInfo<Product> pageInfo = productService.findByPage(page, limit, product);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

}
