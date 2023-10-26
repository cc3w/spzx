package com.cc.spzx.manager;

import com.cc.spzx.manager.properties.MinioProperties;
import com.cc.spzx.manager.properties.UserProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//用来扫描其他包下的配置类，比如说swagger的KnifejConfig配置类
@ComponentScan(basePackages = {"com.cc.spzx"})
//扫描自定义的配置类
@EnableConfigurationProperties({UserProperties.class, MinioProperties.class})
@EnableScheduling
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
