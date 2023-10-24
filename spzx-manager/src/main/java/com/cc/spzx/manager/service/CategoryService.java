package com.cc.spzx.manager.service;

import com.cc.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

    /**
     * desc 根据parentId查找下级节点
     * date 2023/10/23
     * @author cc
     * @return Category>
     **/
    List<Category> findByParentId(Long parentId);

    /**
     * desc 导出excel表
     * date 2023/10/24
     * @author cc
     * @return
     **/
    void exportData(HttpServletResponse response);

    /**
     * desc 导入excel表
     * date 2023/10/24
     * @author cc
     * @return
     **/
    void importData(MultipartFile file);
}
