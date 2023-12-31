package com.cc.spzx.manager.service.impl;

import com.cc.spzx.manager.mapper.BrandMapper;
import com.cc.spzx.manager.service.BrandService;
import com.cc.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;


    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Brand> brandList = brandMapper.findByPage();
        return new PageInfo<>(brandList);
    }

    @Override
    public void save(Brand brand) {
        brandMapper.save(brand);
    }

    @Override
    public List<Brand> findAll() {
        List<Brand> brandList = brandMapper.findByPage();
        return brandList;
    }

    @Override
    public void deleteById(Long id) {
        brandMapper.deleteById(id);
    }

    @Override
    public void updateById(Brand brand) {
        brandMapper.updateById(brand);
    }
}
