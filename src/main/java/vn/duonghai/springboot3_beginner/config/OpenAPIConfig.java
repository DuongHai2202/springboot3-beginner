package vn.duonghai.springboot3_beginner.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public GroupedOpenApi groupedOpenApi(@Value("${openapi.service.api-docs}") String apiDocs) {
        return GroupedOpenApi.builder()
                .group(apiDocs)
                .packagesToScan("vn.duonghai.springboot3_beginner.controller")
                .build();
    }

    @Bean
    public OpenAPI openAPI(@Value("${openapi.service.title}") String title,
                           @Value("${openapi.service.serverUrl}") String serverUrl,
                           @Value("${openapi.service.serverName}") String serverName,
                           @Value("${openapi.service.version}") String version) {
        return new OpenAPI()
                .servers(List.of(new Server().url(serverUrl).description(serverName)))
                .info(new Info().title(title)
                        .version(version)
                        .description("API Documents"));
    }
}
