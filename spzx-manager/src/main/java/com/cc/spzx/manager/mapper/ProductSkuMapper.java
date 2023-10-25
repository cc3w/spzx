package com.cc.spzx.manager.mapper;

import com.cc.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSkuMapper {

    void save(ProductSku productSku);

    List<ProductSku> getById(Long id);

    void updateById(ProductSku productSku);

    void deleteById(Long id);

}
