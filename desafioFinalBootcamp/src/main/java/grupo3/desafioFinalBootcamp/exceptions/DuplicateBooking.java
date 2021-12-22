package grupo3.desafioFinalBootcamp.exceptions;

public class DuplicateBooking extends Exception {
    public final String ERROR = "La reserva de hotel ya se encuentra en el sistema.";

    public DuplicateBooking() {
        super();
    }
}
