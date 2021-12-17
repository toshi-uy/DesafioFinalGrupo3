package grupo3.desafioFinalBootcamp.exceptions;

public class DuplicateHotelCode extends Exception {
    public final String ERROR = "El Codigo del hotel ya se encuentra utilizado.";
    public DuplicateHotelCode() {
        super();
    }
}
