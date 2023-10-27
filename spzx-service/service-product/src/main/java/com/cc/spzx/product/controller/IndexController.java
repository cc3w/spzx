package com.cc.spzx.product.controller;

import com.cc.spzx.model.entity.product.Category;
import com.cc.spzx.model.entity.product.Product;
import com.cc.spzx.model.entity.product.ProductSku;
import com.cc.spzx.model.vo.common.Result;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import com.cc.spzx.model.vo.h5.IndexVo;
import com.cc.spzx.product.service.CategoryService;
import com.cc.spzx.product.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "首页接口管理")
@RestController
@RequestMapping("/api/product/index")
//@CrossOrigin
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;


    @Operation(summary = "获取首页数据")
    @GetMapping
    public Result findData() {
        //查询一级品牌
        List<Category> categoryList = categoryService.findOneCategory();
        //查询热销商品
        List<ProductSku> productSkuList = productService.findProductSkuBySale();

        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);

        return Result.build(indexVo, ResultCodeEnum.SUCCESS);
    }
}
