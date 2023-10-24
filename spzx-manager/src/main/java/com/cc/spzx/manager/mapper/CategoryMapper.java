package com.cc.spzx.manager.mapper;

import com.cc.spzx.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> findByParentId(Long parentId);

    Integer countByParentId(Long parentId);

    List<Category> selectAll();

    void saveData(List cachedDataList);

}
