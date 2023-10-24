package com.cc.spzx.manager.service.impl;

import com.cc.spzx.manager.mapper.CategoryBrandMapper;
import com.cc.spzx.manager.service.CategoryBrandService;
import com.cc.spzx.manager.service.CategoryService;
import com.cc.spzx.model.dto.product.CategoryBrandDto;
import com.cc.spzx.model.entity.product.CategoryBrand;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto) {
        PageHelper.startPage(page, limit);

        List<CategoryBrand> categoryBrandList = categoryBrandMapper.findByPage(categoryBrandDto);

        return new PageInfo<>(categoryBrandList);
    }

    @Override
    public void updateById(CategoryBrand categoryBrand) {
        categoryBrandMapper.updateById(categoryBrand);
    }

    @Override
    public void deleteById(Long id) {
        categoryBrandMapper.deleteById(id);
    }

    @Override
    public void save( CategoryBrand categoryBrand) {
        categoryBrandMapper.save(categoryBrand);
    }
}
