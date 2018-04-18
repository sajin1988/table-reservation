package com.orderbird.techchallenge.tablereservation.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Service API configuration.
 */
@Configuration
@EnableSwagger2
class ApiConfig {

    @Bean
    public Filter corsFilter() {
        return new Filter() {

            @Override
            public void init(FilterConfig filterConfig) throws ServletException {
                // Nothing to do
            }

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                HttpServletResponse response = (HttpServletResponse) servletResponse;

                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS, DELETE");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Headers", "validation-requested-with, Content-Type, api_key, Authorization");

                filterChain.doFilter(servletRequest, response);
            }

            @Override
            public void destroy() {
                // Nothing to do
            }
        };
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build()
                .apiInfo(apiInfo());
    }

    private Predicate<String> paths() {
        return or(
                regex("/table.*"),
                regex("/api.*"));
    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Table Reservation",
                "Table reservation is an application to help the restaurant owner manage tables",
                "v1",
                "",
                "Sajin Das N",
                "Further documentations",
                ""
        );
    }
}
