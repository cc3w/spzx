package com.cc.spzx.manager.service.impl;

import com.cc.spzx.manager.mapper.ProductSpecMapper;
import com.cc.spzx.manager.service.ProductSpecService;
import com.cc.spzx.model.entity.product.ProductSpec;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSpecServiceImpl implements ProductSpecService {

    @Autowired
    ProductSpecMapper productSpecMapper;

    @Override
    public void deleteById(Long id) {
        productSpecMapper.deleteById(id);
    }

    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.updateById(productSpec);
    }

    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec);
    }

    @Override
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit, ProductSpec productSpec) {
        PageHelper.startPage(page, limit);
        List<ProductSpec> productSpecList = productSpecMapper.findeByPage();
        return new PageInfo<>(productSpecList);
    }
}
