package co.edu.udec.sistemanomina.domain.model;

/**
 * Clase que representa al empleado asalariado.
 * Este tipo de empleado tiene un salario fijo mensual y puede recibir
 * beneficios
 * adicionales segun su antiguedad y politicas de la empresa.
 * 
 * Reglas de negocio:
 * - Salario fijo mensual.
 * - Bono del 10% del salario del salario si lleva mas de 5 años en la empresa.
 * - Bono de alimentacion de 1.000.000/mes cubierto por la empresa.
 */

public class EmpleadoAsalariado extends Empleado {

    private static final double PORCENTAJE_DEDUCCIONES = 0.04; // 4% del seguro social y pension.
    private static final double PORCENTAJE_ARL = 0.005; // 0.5% del ARL que corresponde a riesgo tipo 1.
    private static final double BONO_ANTIGUEDAD = 0.10; // 10% si lleva mas de 5 años de antiguedad.
    private static final double BONO_ALIMENTACION = 1_000_000; // $1.000.000 mensual.

    public EmpleadoAsalariado(int id, String nombre, String apellido, String cedula, double salarioBase,
            int aniosAntiguedad) {
        super(id, nombre, apellido, cedula, salarioBase, aniosAntiguedad);
    }

    @Override
    public double calcularSalarioBruto() {
        // Salario fijo mensual
        return getSalarioBase();
    }

    @Override
    public double calcularDeducciones() {
        double salarioBruto = calcularSalarioBruto();
        double deducciones = salarioBruto * (PORCENTAJE_DEDUCCIONES + PORCENTAJE_ARL);
        return deducciones;
    }

    @Override
    public double calcularBeneficios() {
        double beneficios = BONO_ALIMENTACION;

        // Bono adicional del 10% si lleva mas de 5 años
        if (getAniosAntiguedad() > 5) {
            beneficios += getSalarioBase() * BONO_ANTIGUEDAD;
        }

        return beneficios;
    }

}
