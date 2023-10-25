package com.cc.spzx.manager.service.impl;

import com.cc.spzx.manager.mapper.ProductMapper;
import com.cc.spzx.manager.mapper.ProductUnitMapper;
import com.cc.spzx.manager.service.ProductUnitService;
import com.cc.spzx.model.entity.base.ProductUnit;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUnitServiceImpl implements ProductUnitService {

    @Autowired
    private ProductUnitMapper productUnitMapper;

    @Override
    public List<ProductUnit> findAll() {
        List<ProductUnit> productUnitList = productUnitMapper.findAll();
        return productUnitList;
    }
}
