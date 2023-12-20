package epa.MovementsApp.routes;

import epa.MovementsApp.models.TipoMovimiento;
import epa.MovementsApp.models.dto.MovimientosDTO;
import epa.MovementsApp.usecase.movimientos.ListarMovimientosPaginadoUseCaseImp;
import epa.MovementsApp.usecase.movimientos.ListarMovimientosPorProductoImp;
import epa.MovementsApp.usecase.movimientos.ListarMovimientosPorTipoImp;
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

    @Bean
    public RouterFunction<ServerResponse> listarMovimientosPorProductoRoute(ListarMovimientosPorProductoImp useCase)
    {
        return route(GET("/movimientos/listarporproducto/{idProducto}"),
                request -> {
                    String idProducto = request.pathVariable("idProducto");

                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromPublisher(useCase.apply(idProducto), MovimientosDTO.class))
                            .onErrorResume(throwable -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(throwable.getMessage()));
                }
        );
    }

    @Bean
    public RouterFunction<ServerResponse> listarMovimientosVentasDetalleRoute(ListarMovimientosPorTipoImp useCase)
    {
        return route(GET("/movimientos/listarventadetalle"),
                request ->
                {
                    String tipo = TipoMovimiento.VENTA_AL_DETALLE.toString();

                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromPublisher(useCase.apply(tipo), MovimientosDTO.class))
                            .onErrorResume(throwable -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(throwable.getMessage()));
                }
        );
    }

    @Bean
    public RouterFunction<ServerResponse> listarMovimientosVentasPorMayorRoute(ListarMovimientosPorTipoImp useCase)
    {
        return route(GET("/movimientos/listarventapormayor"),
                request ->
                {
                    String tipo = TipoMovimiento.VENTA_AL_POR_MAYOR.toString();

                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromPublisher(useCase.apply(tipo), MovimientosDTO.class))
                            .onErrorResume(throwable -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(throwable.getMessage()));
                }
        );
    }

}
