package com.cc.spzx.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.cc.spzx.manager.mapper.CategoryMapper;
import com.cc.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.ReadListener;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public class ExcelListener<T> extends AnalysisEventListener<T> {

    private CategoryMapper categoryMapper;

    /**
     每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public ExcelListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    // 每解析一行数据就会调用一次该方法
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        CategoryExcelVo data = (CategoryExcelVo)t;
        cachedDataList.add(data);
        if(cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            //存储完成清理 list;
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    private void saveData() {
        categoryMapper.saveData(cachedDataList);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }

    public List<T> getData() {
        return cachedDataList;
    }
}
