package com.cc.spzx.product.service;

import com.cc.spzx.model.entity.product.Brand;

import java.util.List;

public interface BrandService {
    /**
     * desc 查询所有品牌
     * date 2023/10/27
     * @author cc
     * @return Brand>
     **/
    List<Brand> findAll();

}
