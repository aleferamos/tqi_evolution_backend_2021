package br.com.tqi.analiseemprestimo.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {


    @Bean
    public Docket documentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.tqi.analiseemprestimo.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("TQI EVOLUTION 2021")
                .description("desafio tqi back-end")
                .version("v1.0")
                .license("")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                        .contact(new Contact("Alefe", "https://github.com/aleferamos/tqi_evolution_backend_2021", "alefepdias@gmail.com"))
                        .build();
    }

    private List<ResponseMessage> responseMessageForGET(){
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("500 message")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(403)
                    .message("Forbidden!")
                    .build());
        }};
    }

}
