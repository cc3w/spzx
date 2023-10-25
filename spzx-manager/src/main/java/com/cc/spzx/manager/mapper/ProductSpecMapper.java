package com.cc.spzx.manager.mapper;

import com.cc.spzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSpecMapper {
    void deleteById(Long id);

    void updateById(ProductSpec productSpec);

    void save(ProductSpec productSpec);

    List<ProductSpec> findeByPage();

    List<ProductSpec> findAll();

}
