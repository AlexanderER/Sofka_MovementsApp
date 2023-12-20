package epa.MovementsApp.drivenAdapters.repository;

import epa.MovementsApp.models.Movimientos;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MovimientoRepository extends ReactiveMongoRepository<Movimientos, String>
{
    Flux<Movimientos> findAllByIdProducto(String idProducto);

    Flux<Movimientos> findAllByTipo(String idProducto);
}
