package com.cc.spzx.manager.test;

import com.alibaba.excel.EasyExcel;
import com.cc.spzx.model.vo.product.CategoryExcelVo;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelTest {

    public static void main(String[] args) {

        read();
        //write();
    }

    public static void read() {
        //定义excel文件位置
        String fileName = "D://1.xlsx";
        //调用方法
        ExcelListener<CategoryExcelVo> categoryExcelVoExcelListener = new ExcelListener<>();

        EasyExcel.read(fileName, CategoryExcelVo.class, categoryExcelVoExcelListener)
                .sheet().doRead();

        List<CategoryExcelVo> data = categoryExcelVoExcelListener.getData();
        System.out.println("data = " + data);
    }

    public static void write() {

        List<CategoryExcelVo> list = new ArrayList<>();
        list.add(new CategoryExcelVo(1L , "数码办公" , "",0L, 1, 1)) ;
        list.add(new CategoryExcelVo(11L , "华为手机" , "",1L, 1, 2)) ;

        EasyExcel.write("D://2.xlsx", CategoryExcelVo.class).sheet("分类数据").doWrite(list);
    }
}
