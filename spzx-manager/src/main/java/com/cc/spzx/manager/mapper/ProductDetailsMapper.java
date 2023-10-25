package com.cc.spzx.manager.mapper;

import com.cc.spzx.model.entity.product.Product;
import com.cc.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.MatchesPattern;
import java.util.List;

@Mapper
public interface ProductDetailsMapper {


    void save(ProductDetails productDetails);

    ProductDetails getById(Long id);

    void updateById(ProductDetails details);

    void deleteById(Long id);

}
