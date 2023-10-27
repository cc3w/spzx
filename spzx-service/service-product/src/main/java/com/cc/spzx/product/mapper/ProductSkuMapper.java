package com.cc.spzx.product.mapper;

import com.cc.spzx.model.dto.h5.ProductSkuDto;
import com.cc.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSkuMapper {

    List<ProductSku> findProductSkuBySale();

    List<ProductSku> findByPage(ProductSkuDto productSkuDto);

    ProductSku getById(Long skuId);

    List<ProductSku> getByProductId(Long productId);

}
