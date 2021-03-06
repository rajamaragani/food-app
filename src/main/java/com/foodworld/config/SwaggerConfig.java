/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani

 ******************************************************************************/

package com.foodworld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.foodworld.controller")).paths(PathSelectors.any())
                .build().apiInfo(metaData());
    }

    @SuppressWarnings("deprecation")
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo("Food World REST API", "Food World REST API to store and retrive the data", "1.0",
                "Terms of service", "", "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}
