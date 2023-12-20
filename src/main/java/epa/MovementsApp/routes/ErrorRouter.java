package epa.MovementsApp.routes;

import epa.MovementsApp.models.Errores;
import epa.MovementsApp.usecase.errores.ListarErroresUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ErrorRouter
{
    @Bean
    public RouterFunction<ServerResponse> listarErroresRoute(ListarErroresUseCase useCase){
        return route(GET("/errores/listar"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), Errores.class)));
    }

}
