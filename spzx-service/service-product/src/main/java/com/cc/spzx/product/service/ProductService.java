package com.cc.spzx.product.service;

import com.cc.spzx.model.dto.h5.ProductSkuDto;
import com.cc.spzx.model.entity.product.ProductSku;
import com.cc.spzx.model.vo.h5.ProductItemVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductService {

    /**
     * desc 根据商品销量查询
     * date 2023/10/27
     * @author cc
     * @return ProductSku>
     **/

    List<ProductSku> findProductSkuBySale();

    /**
     * desc 条件查询
     * date 2023/10/27
     * @author cc
     * @return ProductSkuDto>
     **/

    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    /**
     * desc 商品详细信息
     * date 2023/10/27
     * @author cc
     * @return ProductItemVo
     **/

    ProductItemVo item(Long skuId);
}
