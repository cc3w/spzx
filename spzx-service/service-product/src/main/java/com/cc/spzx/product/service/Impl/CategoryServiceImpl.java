package com.cc.spzx.product.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.cc.spzx.model.entity.product.Category;
import com.cc.spzx.product.mapper.CategoryMapper;
import com.cc.spzx.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Cacheable(value = "category", key = "'all'")
    @Override
    public List<Category> findCategoryTree() {

        String categoryListJSON = redisTemplate.opsForValue().get("category:one");

        if(!StrUtil.isEmpty(categoryListJSON)) {
            List<Category> categoryList = JSON.parseArray(categoryListJSON, Category.class);
//            log.info("从Redis缓存中查询到了所有的一级分类数据");
            return categoryList;
        }

        List<Category> categoryList = categoryMapper.findCategoryTree();

        //拿到一级分类
        List<Category> oneCategoryList = categoryList.stream()
                .filter(item -> item.getParentId().longValue() == 0)
                .collect(Collectors.toList());

        //二级分类
        oneCategoryList.forEach(oneCategory ->{
            List<Category> twoCategoryList = categoryList.stream()
                    .filter(item -> item.getParentId() == oneCategory.getId())
                    .collect(Collectors.toList());

            oneCategory.setChildren(twoCategoryList);

            //三级分类
            twoCategoryList.forEach(twoCategory ->{
                List<Category> threeCategoryList = categoryList.stream()
                        .filter(item -> item.getParentId() == twoCategory.getId())
                        .collect(Collectors.toList());

                twoCategory.setChildren(threeCategoryList);
            });
        });

        redisTemplate.opsForValue().set("category:one", JSON.toJSONString(oneCategoryList), 3,
                TimeUnit.DAYS);

        return oneCategoryList;
    }

    @Override
    public List<Category> findOneCategory() {
        List<Category> categoryList = categoryMapper.findOneCategory();

       return categoryList;
    }
}
