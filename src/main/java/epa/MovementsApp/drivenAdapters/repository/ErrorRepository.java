package epa.MovementsApp.drivenAdapters.repository;

import epa.MovementsApp.models.Errores;
import epa.MovementsApp.models.Movimientos;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ErrorRepository extends ReactiveMongoRepository<Errores, String>
{
}
