package Configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;


@Configuration
public class SwaggerConfig {

    /**
     * A Anotação @Bean indica que o método é um bean, ou seja, um objeto que pode ser
     * injetado em qualquer ponto da sua aplicação.
     */
	

	@Bean
	public OpenAPI springAttornatuslOpenAPI() {
		
        /** Cria um Objeto da Classe OpenAPI, que gera a documentação no Swagger utilizando 
         * a especificação OpenAPI.
         * 
         */
        return new OpenAPI()
				.info(new Info()
					.title("API Attornatus")
					.description("Teste Back-end")
					.version("v0.0.1") // Versão do projeto
					.contact(new Contact()
					.name("Fernando Brocco")
					.url("https://github.com/fh-brocco")
					.email("fh.brocco@gmail.com")))
				.externalDocs(new ExternalDocumentation()
					.description("Meu LinkedIn")
					.url("https://www.linkedin.com/in/fernando-brocco/"));
	}

    /**
     * A Classe OpenApiCustomiser permite personalizar o Swagger, baseado na 
     * Especificação OpenAPI. O Método abaixo, personaliza todas as mensagens 
     * HTTP Responses (Respostas das requisições) do Swagger.
     */
	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));

			}));
		};
	}

    /**
     * O Método createApiResponse() adiciona uma descrição (Mensagem), em cada Resposta HTTP.
     */
	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);

	}
}
