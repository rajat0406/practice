package com.extramarks.journeybuilder.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class CustomConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket version1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.extramarks.journeybuilder.controller"))
                .build()
                .enable(true)
                .groupName("Extramarks-HOAdmin-Version-1.0.0.0")
                .apiInfo(new ApiInfoBuilder().description("Extramarks Journey Builder APIs")
                        .title("Extramarks-Journey Builder-Version-1.0.0.0")
                        .version("1.0.0.0")
                        .build());
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        assert registry != null;
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setAmbiguityIgnored(true);
        return modelMapper;
    }
}
