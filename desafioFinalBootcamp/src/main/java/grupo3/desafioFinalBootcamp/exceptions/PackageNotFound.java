package grupo3.desafioFinalBootcamp.exceptions;

public class PackageNotFound extends Exception {
    public final String ERROR = "No se encontraron paquetes de reserva con ese Id.";

    public PackageNotFound() {
        super();
    }
}
