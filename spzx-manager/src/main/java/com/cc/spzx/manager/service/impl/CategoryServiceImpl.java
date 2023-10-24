package com.cc.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.cc.spzx.common.exception.ccException;
import com.cc.spzx.manager.listener.ExcelListener;
import com.cc.spzx.manager.mapper.CategoryMapper;
import com.cc.spzx.manager.service.CategoryService;
import com.cc.spzx.model.entity.product.Category;
import com.cc.spzx.model.vo.common.ResultCodeEnum;
import com.cc.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findByParentId(Long parentId) {
        List<Category> categoryList = categoryMapper.findByParentId(parentId);

        if(!CollectionUtils.isEmpty(categoryList)) {
            categoryList.forEach(category -> {
                Integer count = categoryMapper.countByParentId(category.getId());
                if (count > 0) {
                    category.setHasChildren(true);
                } else {
                    category.setHasChildren(false);
                }
            });
        }

        return categoryList;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            //设置响应结果
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");

            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("分类数据", "UTF-8");
            //让你的文件以下载方式打开
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            List<Category> categoryList = categoryMapper.selectAll();
            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>();
            for(Category category : categoryList) {
                CategoryExcelVo excelVo = new CategoryExcelVo();
                BeanUtils.copyProperties(category, excelVo, CategoryExcelVo.class);
                categoryExcelVoList.add(excelVo);
            }

            //写出数据到浏览器
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet("分类数据").doWrite(categoryExcelVoList);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importData(MultipartFile file) {

        try {
            //定义一个监听器
            ExcelListener<CategoryExcelVo> categoryExcelVoExcelListener = new ExcelListener<>(categoryMapper);

            EasyExcel.read(file.getInputStream(), CategoryExcelVo.class, categoryExcelVoExcelListener).sheet().doRead();
        } catch (IOException e) {
            throw new ccException(ResultCodeEnum.DATA_ERROR);
        }
    }
}
