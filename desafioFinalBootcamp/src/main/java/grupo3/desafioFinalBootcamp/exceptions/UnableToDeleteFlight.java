package grupo3.desafioFinalBootcamp.exceptions;

public class UnableToDeleteFlight extends Exception {
    public final String ERROR = "No se puede dar de baja el vuelo, existe una reserva activa para dicho vuelo.";

    public UnableToDeleteFlight() {
        super();
    }
}
