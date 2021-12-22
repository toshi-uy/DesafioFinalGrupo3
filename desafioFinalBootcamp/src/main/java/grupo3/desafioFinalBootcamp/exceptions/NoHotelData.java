package grupo3.desafioFinalBootcamp.exceptions;

public class NoHotelData extends Exception {
    public final String ERROR = "No se encontraron hoteles.";

    public NoHotelData() {
        super();
    }
}
