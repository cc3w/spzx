package com.cc.spzx.manager.service;

import com.cc.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageInfo;

public interface BrandService {
    /**
     * desc 显示品牌列表
     * date 2023/10/24
     * @author cc
     * @return Brand>
     **/

    PageInfo<Brand> findByPage(Integer page, Integer limit);

    /**
     * desc 添加品牌
     * date 2023/10/24
     * @author cc
     * @return
     **/

    void save(Brand brand);

    /**
     * desc 更新品牌
     * date 2023/10/24
     * @author cc
     * @return
     **/

    void updateById(Brand brand);

    /**
     * desc 删除品牌
     * date 2023/10/24
     * @author cc
     * @return
     **/
    void deleteById(Long id);

}
