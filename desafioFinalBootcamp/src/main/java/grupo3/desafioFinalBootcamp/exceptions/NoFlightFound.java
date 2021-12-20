package grupo3.desafioFinalBootcamp.exceptions;

public class NoFlightFound extends Exception {
    public final String ERROR = "No se encontraron hoteles con ese código.";

    public NoFlightFound() {
        super();
    }
}
