package co.edu.udec.sistemanomina.domain.exception;

/**
 * Excepcion para errores relacionados con el salario.
 * Cumple la validacion de que el salario neto no puede ser negativo.
 */

public class SalarioInvalidoException extends RuntimeException {
    public SalarioInvalidoException(String mensaje) {
        super(mensaje);
    }
}
