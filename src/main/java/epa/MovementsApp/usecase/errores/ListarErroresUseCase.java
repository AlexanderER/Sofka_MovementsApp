package epa.MovementsApp.usecase.errores;

import epa.MovementsApp.drivenAdapters.repository.ErrorRepository;
import epa.MovementsApp.drivenAdapters.repository.MovimientoRepository;
import epa.MovementsApp.models.Errores;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
public class ListarErroresUseCase implements Supplier<Flux<Errores>>
{
    //------------------------------------------------------------------------- (Inyeccion de Dependencias)
    private ErrorRepository repositorio;

    public ListarErroresUseCase(ErrorRepository repositorio)
    {
        this.repositorio = repositorio;
    }


    //------------------------------------------------------------------------- (Implementación Uso de Caso)
    @Override
    public Flux<Errores> get()
    {
        return repositorio.findAll();
    }
}
