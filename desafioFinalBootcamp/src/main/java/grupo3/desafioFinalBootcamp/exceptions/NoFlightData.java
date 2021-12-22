package grupo3.desafioFinalBootcamp.exceptions;

public class NoFlightData extends Exception {
    public final String ERROR = "No se encontraron vuelos.";

    public NoFlightData() {
        super();
    }
}
