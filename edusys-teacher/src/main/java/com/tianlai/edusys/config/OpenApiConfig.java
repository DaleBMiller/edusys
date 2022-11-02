package com.tianlai.edusys.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI eduOpenConfig() {
          return new OpenAPI().info(
                  new Info()
                          .title("Education Api")
                          .description("教务系统API")
                          .version("v0.0.1")
                          .license(
                                  new License()
                                          .name("Apache 2.0")
                                          .url("http://www.dalegamezone.top")
                          )
          ).externalDocs(new ExternalDocumentation()
                  .description("APIS")
                  .url("none"));
    }
}
