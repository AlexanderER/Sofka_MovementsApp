package epa.MovementsApp.drivenAdapters.repository;

import epa.MovementsApp.models.Movimientos;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovimientoRepository extends ReactiveMongoRepository<Movimientos, String>
{
}
