package com.cc.spzx.product.mapper;

import com.cc.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDetailsMapper {
    ProductDetails getById(Long productId);

}
