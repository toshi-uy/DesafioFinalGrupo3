package grupo3.desafioFinalBootcamp.exceptions;

public class DuplicateReservation extends Exception {
    public final String ERROR = "La reserva de vuelo ya se encuentra en el sistema.";

    public DuplicateReservation() {
        super();
    }
}
