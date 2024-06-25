package com.example.demo.config;


import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * Swagger3配置文件 暂时未启动安全效验
 */
@Configuration
public class Swagger3Config implements WebMvcConfigurer {
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("简单优雅的restful风格")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).
                useDefaultResponseMessages(false)
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 路径抓取吧？
                .paths(PathSelectors.any())
                .build()
                // 权限管理
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("token", "token", io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER.toString());
        return Lists.newArrayList(apiKey);
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("token", authorizationScopes));
    }

    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .build()
        );
    }

//    @SuppressWarnings("unchecked")
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        try {
//            Field registrationsField = FieldUtils.getField(InterceptorRegistry.class, "registrations",true);
//            List<InterceptorRegistration> registrations = (List<InterceptorRegistration>) ReflectionUtils.getField(registrationsField, registry);
//            if (registrations != null) {
//                for (InterceptorRegistration interceptorRegistration : registrations) {
//                    interceptorRegistration
//                            .excludePathPatterns("/swagger**/**")
//                            .excludePathPatterns("/webjars/**")
//                            .excludePathPatterns("/v3/**")
//                            .excludePathPatterns("/doc.html");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
