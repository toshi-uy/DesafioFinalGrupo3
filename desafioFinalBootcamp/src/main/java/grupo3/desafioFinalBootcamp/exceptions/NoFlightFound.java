package grupo3.desafioFinalBootcamp.exceptions;

public class NoFlightFound extends Exception {
    public final String ERROR = "No se encontraron vuelos con ese número.";
    public NoFlightFound() {
        super();
    }
}
