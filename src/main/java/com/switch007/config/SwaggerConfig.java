package com.switch007.config;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket testApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("test").genericModelSubstitutes(DeferredResult.class)
		// .genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false).forCodeGeneration(true).pathMapping("/")// base，最终调用接口后会和paths拼接在一起
				.select().paths(or(regex("/api/.*")))// 过滤的接口
				
				.build().apiInfo(testApiInfo());
	}

	private ApiInfo testApiInfo() {
		return new ApiInfoBuilder().title("Electronic Health Record(EHR) Platform API")// 大标题
				.description("EHR Platform's REST API, all the applications could access the Object model data via JSON.")// 详细描述
				.version("1.0")// 版本
				.termsOfServiceUrl("NO terms of service")
				.license("The Apache License, Version 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").build();
	}


}
