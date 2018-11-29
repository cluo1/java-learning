package cn.mariojd.fantasy.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Jared
 * @date 2018/11/13 16:27
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许跨域的接口
                .allowedOrigins("*")  // 允许跨域的请求源
                .allowedMethods("*")  // 允许跨域的请求方式
                .allowedHeaders("*") // 允许跨域的请求头
                .allowCredentials(true)  // 带cookie请求的时候需要开启，且allowedOrigins需要指定为具体的请求源
                .maxAge(60 * 60 * 24 * 7); // 设定options请求预检命令的缓存时长
    }

}