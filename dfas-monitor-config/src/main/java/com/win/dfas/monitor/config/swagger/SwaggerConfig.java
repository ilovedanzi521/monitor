package com.win.dfas.monitor.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2测试接口和api文档
 * @author wangyaoheng
 * @version 1.0.0 2019-08-07
 * @since 1.0.0 2019-08-07
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket createApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.win.dfas.monitor")).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API接口和文档").description("项目接口参数说明与测试指南").termsOfServiceUrl("http://localhost:8080/swagger-ui.html").contact("wangyh").version("0.1").build();
	}
}
