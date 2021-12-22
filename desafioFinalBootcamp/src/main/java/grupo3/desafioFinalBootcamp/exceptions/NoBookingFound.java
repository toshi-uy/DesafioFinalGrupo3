package grupo3.desafioFinalBootcamp.exceptions;

public class NoBookingFound extends Exception {
    public final String ERROR = "No se encontraron reservas de hoteles con ese Id.";

    public NoBookingFound() {
        super();
    }
}
