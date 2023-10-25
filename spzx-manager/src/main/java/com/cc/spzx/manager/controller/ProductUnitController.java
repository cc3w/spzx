package com.cc.spzx.manager.controller;

import com.cc.spzx.manager.service.ProductUnitService;
import com.cc.spzx.model.entity.base.ProductUnit;
import com.cc.spzx.model.vo.common.Result;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "计量单位接口")
@RestController
@RequestMapping("/admin/product/productUnit")
public class ProductUnitController {

    @Autowired
    private ProductUnitService productUnitService;

    @GetMapping("/findAll")
    public Result findAll() {
        List<ProductUnit> productUnitList = productUnitService.findAll();
        return Result.build(productUnitList, ResultCodeEnum.SUCCESS);
    }

}
