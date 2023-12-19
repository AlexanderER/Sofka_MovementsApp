package epa.MovementsApp.routes;

import epa.MovementsApp.models.dto.MovimientosDTO;
import epa.MovementsApp.usecase.movimientos.ListarMovimientosPaginadoUseCaseImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MovimientoRouter
{
    @Bean
    public RouterFunction<ServerResponse> listarMovimientosPaginadoRoute(ListarMovimientosPaginadoUseCaseImp useCase)
    {
        return route(GET("/movimientos/listar/{pagina}/{cantPorPagina}"),
                request -> {
                    Integer pagina       = Integer.valueOf(request.pathVariable("pagina"));
                    Integer tamanoPagina = Integer.valueOf(request.pathVariable("cantPorPagina"));

                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromPublisher(useCase.apply(pagina, tamanoPagina), MovimientosDTO.class))
                            .onErrorResume(throwable -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(throwable.getMessage()));
                }
        );
    }



}
