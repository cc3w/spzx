package com.cc.spzx.manager.mapper;

import com.cc.spzx.model.entity.base.ProductUnit;
import com.cc.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> findByPage(Product product);


    void save(Product product);

    Product getById(Long id);

    void updateById(Product product);

    void deleteById(Long id);



}
