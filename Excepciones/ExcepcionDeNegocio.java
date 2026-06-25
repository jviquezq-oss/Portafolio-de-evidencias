package Excepciones;

public class ExcepcionDeNegocio extends RuntimeException {
    public ExcepcionDeNegocio(String message) {
        super(message);
    }
}
