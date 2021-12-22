package grupo3.desafioFinalBootcamp.exceptions;

public class MissingParameters extends Exception {
    public final String ERROR = "Por favor ingrese los 3 parametros requeridos";

    public MissingParameters() {
        super();
    }
}
