package com.cc.spzx.manager.service.impl;

import com.cc.spzx.manager.mapper.CategoryMapper;
import com.cc.spzx.manager.service.CategoryService;
import com.cc.spzx.model.entity.product.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findByParentId(Long parentId) {
        List<Category> categoryList = categoryMapper.findByParentId(parentId);

        if(!CollectionUtils.isEmpty(categoryList)) {
            categoryList.forEach(category -> {
                Integer count = categoryMapper.countByParentId(category.getId());
                if (count > 0) {
                    category.setHasChildren(true);
                } else {
                    category.setHasChildren(false);
                }
            });
        }

        return categoryList;
    }
}
