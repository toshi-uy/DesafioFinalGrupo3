package grupo3.desafioFinalBootcamp.exceptions;

public class NoReservationFound extends Exception {
    public final String ERROR = "No se encontraron reservas de vuelos con ese Id.";

    public NoReservationFound() {
        super();
    }
}
