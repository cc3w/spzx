package com.cc.spzx.manager.config;

import com.cc.spzx.manager.interceptor.LoginAuthInterceptor;
import com.cc.spzx.manager.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    //登录拦截器，实现了HandlerInterceptor
    @Autowired
    LoginAuthInterceptor loginAuthInterceptor;

    @Autowired
    UserProperties userProperties;

    /**
     * desc 拦截请求
     * date 2023/10/20
     * @author cc
     * @return
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(userProperties.getNoAuthUrls());
//                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v3/**", "/doc.html/**");;
    }

    /**
     * desc 放行静态资源,如放行swagger
     * date 2023/10/20
     * @author cc
     * @return
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("doc.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * desc 解决跨域
     * date 2023/10/20
     * @author cc
     * @return
     **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 添加路径规则
                .allowCredentials(true)               // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")           // 允许请求来源的域规则
                .allowedMethods("*")
                .allowedHeaders("*") ;                // 允许所有的请求头
    }
}
