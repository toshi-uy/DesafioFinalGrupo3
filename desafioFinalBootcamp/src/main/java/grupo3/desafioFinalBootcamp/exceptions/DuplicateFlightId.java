package grupo3.desafioFinalBootcamp.exceptions;

public class DuplicateFlightId extends Exception {
    public final String ERROR = "El Id del vuelo ya se encuentra utilizado.";

    public DuplicateFlightId() {
        super();
    }
}
