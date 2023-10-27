package com.cc.spzx.product.service.Impl;

import com.alibaba.fastjson.JSON;
import com.cc.spzx.model.dto.h5.ProductSkuDto;
import com.cc.spzx.model.entity.product.ProductDetails;
import com.cc.spzx.model.entity.product.ProductSku;
import com.cc.spzx.model.entity.product.Product;
import com.cc.spzx.model.vo.h5.ProductItemVo;
import com.cc.spzx.product.mapper.ProductDetailsMapper;
import com.cc.spzx.product.mapper.ProductMapper;
import com.cc.spzx.product.mapper.ProductSkuMapper;
import com.cc.spzx.product.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    @Override
    public ProductItemVo item(Long skuId) {
        ProductItemVo productItemVo = new ProductItemVo();
        //查询商品sku信息
        ProductSku productSku = productSkuMapper.getById(skuId);
        //设置商品sku
        productItemVo.setProductSku(productSku);

        Long productId = productSku.getProductId();

        //根据product_id查询商品信息
        Product product = productMapper.getById(productId);
        //设置商品
        productItemVo.setProduct(product);
            //设置商品规格
        productItemVo.setSpecValueList(JSON.parseArray(product.getSpecValue()));
            //设置轮播图
        productItemVo.setSliderUrlList(Arrays.asList(product.getSliderUrls().split(",")));


        //根据product_id查询details
        ProductDetails productDetails = productDetailsMapper.getById(productId);
        String[] split = productDetails.getImageUrls().split(",");
        List<String> list = Arrays.asList(split);
        //设置商品图片信息
        productItemVo.setDetailsImageUrlList(list);

        List<ProductSku> productSkuList = productSkuMapper.getByProductId(productId);
        HashMap<String, Object> skuSpecValueMap = new HashMap<String, Object>();

        productSkuList.forEach(item-> {
            skuSpecValueMap.put(item.getSkuSpec(), item.getId());
        });
        //设置商品规格和商品skuId对应
        productItemVo.setSkuSpecValueMap(skuSpecValueMap);

        return productItemVo;
    }

    @Override
    public PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto) {

        PageHelper.startPage(page, limit);

        List<ProductSku> productSkuList = productSkuMapper.findByPage(productSkuDto);

        return new PageInfo<>(productSkuList);
    }

    @Override
    public List<ProductSku> findProductSkuBySale() {

        List<ProductSku> productSkuList = productSkuMapper.findProductSkuBySale();

        return productSkuList;
    }
}
