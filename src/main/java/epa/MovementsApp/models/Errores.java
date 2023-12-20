package epa.MovementsApp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("Error")
public class Errores
{
    //--------------------------------------------------- (Variables)
    @Id
    private String id;
    private String mensaje;


    //--------------------------------------------------- (Constructor)
    public Errores()
    {
    }

    public Errores(String id, String mensaje)
    {
        this.id = id;
        this.mensaje = mensaje;
    }


    //--------------------------------------------------- (Setter y Getter)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
