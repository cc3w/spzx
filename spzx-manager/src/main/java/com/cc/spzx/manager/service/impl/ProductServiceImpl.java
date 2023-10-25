package com.cc.spzx.manager.service.impl;

import com.cc.spzx.manager.mapper.ProductDetailsMapper;
import com.cc.spzx.manager.mapper.ProductMapper;
import com.cc.spzx.manager.mapper.ProductSkuMapper;
import com.cc.spzx.manager.mapper.ProductSpecMapper;
import com.cc.spzx.manager.service.ProductDetailsService;
import com.cc.spzx.manager.service.ProductService;
import com.cc.spzx.model.entity.product.Product;
import com.cc.spzx.model.entity.product.ProductDetails;
import com.cc.spzx.model.entity.product.ProductSku;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    @Override
    public PageInfo<Product> findByPage(Integer page, Integer limit, Product product) {
        PageHelper.startPage(page, limit);
        List<Product> productList = productMapper.findByPage(product);
        return new PageInfo<>(productList);
    }

    @Override
    public void updateById(Product product) {
        productMapper.updateById(product);

        //修改product_sku
        List<ProductSku> productSkuList = product.getProductSkuList();
        productSkuList.forEach(productSku -> {
            productSkuMapper.updateById(productSku);
        });

        //修改product_details
        ProductDetails details = productDetailsMapper.getById(product.getId());
        details.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.updateById(details);

    }

    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);
        if(auditStatus == 1) {
            product.setAuditStatus(1);
            product.setAuditMessage("审批通过");
        } else {
            product.setAuditStatus(-1);
            product.setAuditMessage("审批不通过");
        }
        productMapper.updateById(product);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        product.setStatus(status);
        productMapper.updateById(product);
    }

    @Override
    public void deleteById(Long id) {
        //删除product
        productMapper.deleteById(id);

        //删除product_sku
        productSkuMapper.deleteById(id);

        //删除product_details
        productDetailsMapper.deleteById(id);
    }

    @Override
    public Product getById(Long id) {
        //查询product
        Product product = productMapper.getById(id);

        //查询product_sku
        List<ProductSku> productSkuList = productSkuMapper.getById(id);
        product.setProductSkuList(productSkuList);

        //查询product_details
        ProductDetails productDetails = productDetailsMapper.getById(id);

        product.setDetailsImageUrls(productDetails.getImageUrls());

        return product;
    }

    @Override
    public void save(Product product) {
        //保存在product表中
        product.setStatus(0);
        product.setAuditStatus(0);
        productMapper.save(product);

        //将sku保存在product_sku表中
        List<ProductSku> productSkuList = product.getProductSkuList();
        for(int i = 0; i < productSkuList.size(); i ++) {
            ProductSku productSku = productSkuList.get(i);

            // 构建skuCode
            productSku.setSkuCode(product.getId() + "_" + i);

            //设置商品id
            productSku.setProductId(product.getId());

            productSku.setSkuName(product.getName() + productSku.getSkuSpec());
            // 设置销量
            productSku.setSaleNum(0);
            //设置线上状态的初始值
            productSku.setStatus(0);

            productSkuMapper.save(productSku);
        }

        //轮播图保存在product_details
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.save(productDetails);
    }
}
