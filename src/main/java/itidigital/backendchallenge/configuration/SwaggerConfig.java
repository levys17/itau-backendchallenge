package itidigital.backendchallenge.configuration;

import com.fasterxml.classmate.TypeResolver;
import itidigital.backendchallenge.exceptionhandler.ExceptionDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        var typeResolver = new TypeResolver();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.POST, globalPostResponse())
                .additionalModels(typeResolver.resolve(ExceptionDetail.class))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Backend Challenge -  Itaú Unibanco")
                .description("Rest API for Itaú Backend Challenge.")
                .contact(new Contact("Levy Sales", "https://www.linkedin.com/in/levy-sales-836aaa88/", "levyceesar@gmail.com"))
                .version("1")
                .build();
    }

    private List<Response> globalPostResponse() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Error processing validation rule(s).")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getExceptionDetailModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                        .description("Resource not found")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getExceptionDetailModelReference())
                        .build()
        );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

    private Consumer<RepresentationBuilder> getExceptionDetailModelReference() {
        return r -> r.model(m -> m.name("Exceptions Details")
                .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                        q -> q.name("Exceptions Details").namespace("itidigital.backendchallenge.exceptionhandler")))));
    }
}