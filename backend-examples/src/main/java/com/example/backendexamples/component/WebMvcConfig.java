package com.example.backendexamples.component;

import com.example.backendexamples.interceptor.AdminInterCeptor;
import com.example.backendexamples.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*实现WebMvcConfigurer的接口*/
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    /*注入login组件和Admin组件*/
    private final LoginInterceptor loginInterceptor;
    private final AdminInterCeptor adminInterCeptor;

    /*添加拦截器方法*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*通过注册器注册*/
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login");
        registry.addInterceptor(adminInterCeptor)
                .addPathPatterns("/admin/**");
    }
}
