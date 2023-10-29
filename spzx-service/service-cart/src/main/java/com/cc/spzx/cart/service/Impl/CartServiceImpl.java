package com.cc.spzx.cart.service.Impl;

import com.alibaba.fastjson2.JSON;
import com.cc.spzx.cart.service.CartService;
import com.cc.spzx.feign.product.ProductFeignClient;
import com.cc.spzx.model.entity.h5.CartInfo;
import com.cc.spzx.model.entity.product.ProductSku;
import com.cc.spzx.model.entity.user.UserInfo;
import com.cc.spzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ProductFeignClient productFeignClient;

    private UserInfo get() {
        String s = redisTemplate.opsForValue().get("user:spzx:cc695ead4d764c6f981f7f7a53da9bd7");
        UserInfo userInfo = JSON.parseObject(s, UserInfo.class);
        return userInfo;
    }

    @Override
    public List<CartInfo> getAllCkecked() {
//        Long userId = AuthContextUtil.getUserInfo().getId();

        Long userId = this.get().getId();

        String cartKey = getCartKey(userId);
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);       // 获取所有的购物项数据
        if(!CollectionUtils.isEmpty(objectList)) {
            List<CartInfo> cartInfoList = objectList.stream().map(cartInfoJSON -> JSON.parseObject(cartInfoJSON.toString(), CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                    .collect(Collectors.toList());
            return cartInfoList ;
        }
        return new ArrayList<>() ;
    }

    @Override
    public void clearCart() {

        //UserInfo userInfo = AuthContextUtil.getUserInfo();
        UserInfo userInfo = this.get();

        Long id = userInfo.getId();

        String cartKey = getCartKey(id);
        redisTemplate.delete(cartKey);
    }

    @Override
    public void allCheckCart(Integer isChecked) {

        // 获取当前登录的用户数据

        //UserInfo userInfo = AuthContextUtil.getUserInfo();

        UserInfo userInfo = this.get();

        Long id = userInfo.getId();

        String cartKey = getCartKey(id);

        // 获取所有的购物项数据
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        if(!CollectionUtils.isEmpty(objectList)) {
            objectList.stream().map(cartInfoJSON -> {
                CartInfo cartInfo = JSON.parseObject(cartInfoJSON.toString(), CartInfo.class);
                cartInfo.setIsChecked(isChecked);
                return cartInfo ;
            }).forEach(cartInfo -> redisTemplate.opsForHash().put(cartKey , String.valueOf(cartInfo.getSkuId()) , JSON.toJSONString(cartInfo)));

        }
    }

    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        // 获取当前登录的用户数据

//        UserInfo userInfo = AuthContextUtil.getUserInfo();

        UserInfo userInfo = this.get();

        Long id = userInfo.getId();


        String cartKey = this.getCartKey(id);

        Boolean hasKey = redisTemplate.opsForHash().hasKey(cartKey, String.valueOf(skuId));
        if(hasKey) {
            String cartInfoJSON = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId)).toString();
            CartInfo cartInfo = JSON.parseObject(cartInfoJSON, CartInfo.class);
            cartInfo.setIsChecked(isChecked);
            redisTemplate.opsForHash().put(cartKey , String.valueOf(skuId) , JSON.toJSONString(cartInfo));
        }
    }

    @Override
    public void deleteCart(Long skuId) {
        // 获取当前登录的用户数据

//        UserInfo userInfo = AuthContextUtil.getUserInfo();

        UserInfo userInfo = this.get();

        Long id = userInfo.getId();

        String cartKey = getCartKey(id);

        //获取缓存对象
        redisTemplate.opsForHash().delete(cartKey, String.valueOf(skuId));
    }

    @Override
    public List<CartInfo> getCartList() {

        //UserInfo userInfo = AuthContextUtil.getUserInfo();

        UserInfo userInfo = this.get();

        Long id = userInfo.getId();

        String cartKey = this.getCartKey(id);

        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);

        if(!CollectionUtils.isEmpty(objectList)) {
            List<CartInfo> cartInfoList = objectList.stream().map(object -> JSON.parseObject(object.toString(), CartInfo.class))
                    .sorted((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()))
                    .collect(Collectors.toList());
            return cartInfoList;
        }

        return new ArrayList<>();
    }

    @Override
    public void addToCart(Long skuId, Integer skuNum) {
        //根据ThreadLocal获取用户id，及传入参数构造hash的key
        System.out.println("购物车的时间" + new Date());

        //UserInfo userInfo = AuthContextUtil.getUserInfo();

        UserInfo userInfo = this.get();

        Long id = userInfo.getId();

        String cartKey = this.getCartKey(id);

        //拿着key去判断hash是否有值，如果有值就将skuNum加上之前的数量
        Object cartInfoObj = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId));

        CartInfo cartInfo = null;

        if(cartInfoObj != null) {
            cartInfo = JSON.parseObject(cartInfoObj.toString(), CartInfo.class);
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());
        } else {
            //没有就创建一个，需要远程调用数据库
            cartInfo = new CartInfo();

            ProductSku productSku = productFeignClient.getBySkuId(skuId);

            cartInfo.setUserId(id);
            cartInfo.setSkuId(skuId);
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());
        }

        redisTemplate.opsForHash().put(cartKey, String.valueOf(skuId), JSON.toJSONString(cartInfo));
        System.out.println("cartInfo的值---" + redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId)));
    }

    private String getCartKey(Long userId) {
        //定义key user:cart:userId
        return "user:cart:" + userId;
    }
}
