package com.cc.spzx.manager.service;

import com.cc.spzx.model.entity.base.ProductUnit;

import java.util.List;

public interface ProductUnitService {
    /**
     * desc 查询所有计量单位
     * date 2023/10/25
     * @author cc
     * @return ProductUnit>
     **/
    List<ProductUnit> findAll();

}
