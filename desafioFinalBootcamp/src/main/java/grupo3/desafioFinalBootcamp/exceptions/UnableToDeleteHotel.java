package grupo3.desafioFinalBootcamp.exceptions;

public class UnableToDeleteHotel extends Exception {
    public final String ERROR = "No se puede dar de baja el hotel, existe una reserva activa para dicho hotel.";

    public UnableToDeleteHotel() {
        super();
    }
}
