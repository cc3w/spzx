package com.cc.spzx.product.service;

import com.cc.spzx.model.entity.product.Category;

import java.util.List;

public interface CategoryService {
    /**
     * desc 获取首页数据
     * date 2023/10/27
     * @author cc
     * @return Category> 
     **/
    List<Category> findOneCategory();


    /**
     * desc 获取分类树形数据
     * date 2023/10/27
     * @author cc
     * @return Category>
     **/

    List<Category> findCategoryTree();

}
