package epa.MovementsApp.usecase.movimientos;

import epa.MovementsApp.models.dto.MovimientosDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@FunctionalInterface
public interface ListarMovimientosPorString
{
    Flux<MovimientosDTO> apply(String valorBuscado);
}
