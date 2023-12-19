package epa.MovementsApp.usecase.movimientos;

import epa.MovementsApp.models.dto.MovimientosDTO;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface ListarMovimientosPaginadoUseCase
{
    Flux<MovimientosDTO> apply(int pagina, int tamanoPagina);
}
