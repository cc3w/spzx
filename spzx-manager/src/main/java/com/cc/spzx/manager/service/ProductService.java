package com.cc.spzx.manager.service;

import com.cc.spzx.model.entity.product.Product;
import com.github.pagehelper.PageInfo;

public interface ProductService {
    /**
     * desc 根据条件查询并分页
     * date 2023/10/25
     * @author cc
     * @return Product>
     **/
    PageInfo<Product> findByPage(Integer page, Integer limit, Product product);

    /**
     * desc 保存所有商品所有信息
     * date 2023/10/25
     * @author cc
     * @return
     **/
    void save(Product product);

    /**
     * desc 根据id查询商品信息
     * date 2023/10/25
     * @author cc
     * @return Product
     **/
    Product getById(Long id);

    /**
     * desc 修改商品信息
     * date 2023/10/25
     * @author cc
     * @return
     **/
    void updateById(Product product);

    /**
     * desc 删除商品
     * date 2023/10/25
     * @author cc
     * @return
     **/
    void deleteById(Long id);

    /**
     * desc 审核商品
     * date 2023/10/25
     * @author cc
     * @return
     **/
    void updateAuditStatus(Long id, Integer auditStatus);

    /**
     * desc 上下架商品
     * date 2023/10/25
     * @author cc
     * @return
     **/

    void updateStatus(Long id, Integer status);
}
