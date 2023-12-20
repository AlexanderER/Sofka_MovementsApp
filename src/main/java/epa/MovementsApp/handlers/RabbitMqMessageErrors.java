package epa.MovementsApp.handlers;

import com.google.gson.Gson;
import epa.MovementsApp.drivenAdapters.config.RabbitConfig;
import epa.MovementsApp.drivenAdapters.repository.ErrorRepository;
import epa.MovementsApp.drivenAdapters.repository.MovimientoRepository;
import epa.MovementsApp.models.Errores;
import epa.MovementsApp.models.Movimientos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.Receiver;

@Component
public class RabbitMqMessageErrors implements CommandLineRunner
{
    private static final Logger log = LoggerFactory.getLogger(RabbitMqMessageErrors.class);

    @Autowired
    private Receiver receiver;

    @Autowired
    private ErrorRepository repositorio;

    public void receiveError()
    {
        receiver.consumeAutoAck(RabbitConfig.QUEUE_NAME_ERRORS)
                .map(message ->
                {
                    String mensaje = new String(message.getBody());

                    log.info("{}Consumiendo Error: " + mensaje + "{}", "\u001B[31m", "\u001B[0m");

                    Errores miError = new Errores();
                    miError.setMensaje(mensaje);

                    return repositorio.save(miError)
                            .doOnError(error -> Mono.error(new RuntimeException("[Error] Error al guardar el log de error.", error)))
                            .subscribe();
                }).subscribe();
    }

    @Override
    public void run(String... args) throws Exception
    {
        this.receiveError();
    }
}
