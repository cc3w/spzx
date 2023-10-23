package com.cc.spzx.manager.service;

import com.cc.spzx.model.entity.product.Category;

import java.util.List;

public interface CategoryService {

    /**
     * desc 根据parentId查找下级节点
     * date 2023/10/23
     * @author cc
     * @return Category>
     **/

    List<Category> findByParentId(Long parentId);
}
