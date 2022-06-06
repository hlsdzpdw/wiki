package cn.ll.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 映射的请求地址，这样写是表示针对所有的请求地址
                .allowedOriginPatterns("*") // 允许来源
                .allowedHeaders(CorsConfiguration.ALL) //表示通过的请求头
                .allowedMethods(CorsConfiguration.ALL) // 表示可行的请求方法
                .allowCredentials(true) // 凭证
                .maxAge(3600); // 1小时内不需要再预检（发OPTIONS请求）
    }

}