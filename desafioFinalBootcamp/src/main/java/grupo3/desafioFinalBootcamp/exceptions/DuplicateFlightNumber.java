package grupo3.desafioFinalBootcamp.exceptions;

public class DuplicateFlightNumber extends Exception {
    public final String ERROR = "El número de vuelo ya se encuentra utilizado.";

    public DuplicateFlightNumber() {
        super();
    }
}
