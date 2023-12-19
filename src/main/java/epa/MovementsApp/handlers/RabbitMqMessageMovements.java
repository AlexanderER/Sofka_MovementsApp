package epa.MovementsApp.handlers;

import com.google.gson.Gson;
import epa.MovementsApp.drivenAdapters.config.RabbitConfig;
import epa.MovementsApp.drivenAdapters.repository.MovimientoRepository;
import epa.MovementsApp.models.Movimientos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.Receiver;

@Component
public class RabbitMqMessageMovements implements CommandLineRunner
{
    private static final Logger log = LoggerFactory.getLogger(RabbitMqMessageMovements.class);

    @Autowired
    private Receiver receiver;

    @Autowired
    private MovimientoRepository repositorio;

    @Autowired
    private Gson gson;

    public void receiveMovement()
    {
        receiver.consumeAutoAck(RabbitConfig.QUEUE_NAME_MOVEMENTS)
                .map(message ->
                {
                    String mensaje = new String(message.getBody());

                    log.info("{}Consumiendo Movimiento: " + mensaje + "{}", "\u001B[31m", "\u001B[0m");

                    Movimientos movimiento = gson.fromJson(mensaje, Movimientos.class);

                    return repositorio.save(movimiento)
                            .doOnError(error -> Mono.error(new RuntimeException("[Movimiento] Error al guardar el movimiento.", error)))
                            .subscribe();
                }).subscribe();
    }

    @Override
    public void run(String... args) throws Exception
    {
        this.receiveMovement();
    }
}
