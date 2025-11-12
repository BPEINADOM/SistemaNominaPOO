package co.edu.udec.sistemanomina.domain.model;

/**
 * Representa un empleado temporal con contrato por tiempo definido
 * y salario fijo mensual.
 */
public class EmpleadoTemporal extends Empleado {

    private String fechaFinContrato; // "Contrato por tiempo definido" 

    public EmpleadoTemporal(int id, String nombre, String apellido, String cedula, double salarioBase, int aniosAntiguedad, String fechaFinContrato) {
        super(id, nombre, apellido, cedula, salarioBase, aniosAntiguedad);
        this.fechaFinContrato = fechaFinContrato;
    }

    public String getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(String fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    /**
     * Salario fijo mensual. 
     * Se usa el salarioBase de la clase padre.
     */
    @Override
    public double calcularSalarioBruto() {
        return getSalarioBase();
    }

    /**
     * No aplican bonos ni beneficios adicionales. 
     */
    @Override
    public double calcularBeneficios() {
        return 0.0;
    }

    /**
     * Deducciones obligatorias de Seguro Social y Pensión (4% del salario bruto). 
     */
    @Override
    public double calcularDeducciones() {
        return calcularSalarioBruto() * 0.04; // 
    }
}