package com.cc.spzx.product.service.Impl;

import com.cc.spzx.model.entity.product.Brand;
import com.cc.spzx.product.mapper.BrandMapper;
import com.cc.spzx.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Cacheable(value = "brandList", unless="#result.size() == 0")
    @Override
    public List<Brand> findAll() {
        List<Brand> brandList = brandMapper.findAll();
        return brandList;
    }
}
