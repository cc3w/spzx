package com.cc.spzx.manager.service;

import com.cc.spzx.model.entity.product.ProductSpec;
import com.github.pagehelper.PageInfo;

public interface ProductSpecService {
    /**
     * desc 删除商品
     * date 2023/10/25
     * @author cc
     * @return
     **/

    void deleteById(Long id);

    /**
     * desc 更新商品
     * date 2023/10/25
     * @author cc
     * @return
     **/
    void updateById(ProductSpec productSpec);

    /**
     * desc 添加商品
     * date 2023/10/25
     * @author cc
     * @return
     **/
    void save(ProductSpec productSpec);

    /**
     * desc 查询商品列表
     * date 2023/10/25
     * @author cc
     * @return ProductSpec>
     **/
    PageInfo<ProductSpec> findByPage(Integer page, Integer limit, ProductSpec productSpec);
}
