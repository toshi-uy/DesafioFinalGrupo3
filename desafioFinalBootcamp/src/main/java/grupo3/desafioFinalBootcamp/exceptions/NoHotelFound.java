package grupo3.desafioFinalBootcamp.exceptions;

public class NoHotelFound extends Exception {
    public final String ERROR = "No se encontraron hoteles con ese código.";

    public NoHotelFound() {
        super();
    }
}
