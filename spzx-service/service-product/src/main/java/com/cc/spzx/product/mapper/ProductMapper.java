package com.cc.spzx.product.mapper;

import com.cc.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    Product getById(Long productId);
}
