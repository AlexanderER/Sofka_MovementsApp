package epa.MovementsApp.usecase.movimientos;

import epa.MovementsApp.drivenAdapters.repository.MovimientoRepository;
import epa.MovementsApp.models.Movimientos;
import epa.MovementsApp.models.dto.MovimientosDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ListarMovimientosPorProductoImp implements ListarMovimientosPorString
{
    //------------------------------------------------------------------------- (Inyeccion de Dependencias)
    private MovimientoRepository repositorio;

    public ListarMovimientosPorProductoImp(MovimientoRepository repositorio)
    {
        this.repositorio = repositorio;
    }

    //------------------------------------------------------------------------- (Implementación Uso de Caso)
    @Override
    public Flux<MovimientosDTO> apply(String valorBuscado)
    {
        return repositorio.findAllByIdProducto(valorBuscado)
                .map(this::getProductoDTO);
    }


    //------------------------------------------------------------------------- (Casteo)
    private MovimientosDTO getProductoDTO(Movimientos movimientoModel)
    {
        return new MovimientosDTO(  movimientoModel.getId(),
                movimientoModel.getFecha(),
                movimientoModel.getIdProducto(),
                movimientoModel.getTipo(),
                movimientoModel.getCantidad(),
                movimientoModel.getExistenciaInicial(),
                movimientoModel.getExistenciaFinal(),
                movimientoModel.getCosto(),
                movimientoModel.getPrecio()
        );
    }
}
