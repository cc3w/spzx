package com.cc.spzx.manager.service;

import com.cc.spzx.model.dto.product.CategoryBrandDto;
import com.cc.spzx.model.entity.product.Brand;
import com.cc.spzx.model.entity.product.CategoryBrand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CategoryBrandService {
    /**
     * desc 根据条件查询并分页
     * date 2023/10/24
     * @author cc
     * @return CategoryBrandDto>
     **/
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    /**
     * desc 添加分类品牌表单
     * date 2023/10/24
     * @author cc
     * @return
     **/

    void save(CategoryBrand categoryBrand);

    /**
     * desc 修改品牌分类
     * date 2023/10/24
     * @author cc
     * @return
     **/
    void updateById(CategoryBrand categoryBrand);

    /**
     * desc 删除品牌分类菜单
     * date 2023/10/24
     * @author cc
     * @return
     **/

    void deleteById(Long id);

    /**
     * desc 根据分类查询所有品牌
     * date 2023/10/25
     * @author cc
     * @return
     **/

    List<Brand> findBrandByCategoryId(Long categoryId);
}
