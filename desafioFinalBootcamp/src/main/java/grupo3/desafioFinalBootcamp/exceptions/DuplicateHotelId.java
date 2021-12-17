package grupo3.desafioFinalBootcamp.exceptions;

public class DuplicateHotelId extends Exception {
    public final String ERROR = "El Id del hotel ya se encuentra utilizado.";
    public DuplicateHotelId() {
        super();
    }
}
