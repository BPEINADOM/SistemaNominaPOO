package co.edu.udec.sistemanomina.domain.model;

/**
 * Representa un empleado que cobra por horas trabajadas.
 * El salarioBase de la clase padre se interpreta como la tarifaPorHora.
 */
public class EmpleadoPorHoras extends Empleado {

    private double horasTrabajadas;
    private boolean aceptaFondoAhorro;
    private static final double LIMITE_HORAS_NORMALES = 40.0;
    private static final double TARIFA_HORA_EXTRA = 1.5;

    public EmpleadoPorHoras(int id, String nombre, String apellido, String cedula, double tarifaPorHora, int aniosAntiguedad, double horasTrabajadas, boolean aceptaFondoAhorro) {
        super(id, nombre, apellido, cedula, tarifaPorHora, aniosAntiguedad);
        
        if (horasTrabajadas < 0) { // [cite: 49]
            throw new IllegalArgumentException("Las horas trabajadas no pueden ser negativas.");
        }
        this.horasTrabajadas = horasTrabajadas;
        this.aceptaFondoAhorro = aceptaFondoAhorro;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(double horasTrabajadas) {
        if (horasTrabajadas < 0) { // [cite: 49]
            throw new IllegalArgumentException("Las horas trabajadas no pueden ser negativas.");
        }
        this.horasTrabajadas = horasTrabajadas;
    }

    public boolean isAceptaFondoAhorro() {
        return aceptaFondoAhorro;
    }

    public void setAceptaFondoAhorro(boolean aceptaFondoAhorro) {
        this.aceptaFondoAhorro = aceptaFonoAhorro;
    }

    /**
     * Calcula el salario bruto basado en horas normales y extras.
     * Horas extras (más de 40) se pagan a 1.5x la tarifa normal. [cite: 19]
     */
    @Override
    public double calcularSalarioBruto() {
        double tarifa = getSalarioBase(); // [cite: 18]
        if (horasTrabajadas <= LIMITE_HORAS_NORMALES) {
            return horasTrabajadas * tarifa;
        } else {
            double horasNormales = LIMITE_HORAS_NORMALES;
            double horasExtras = horasTrabajadas - LIMITE_HORAS_NORMALES;
            return (horasNormales * tarifa) + (horasExtras * tarifa * TARIFA_HORA_EXTRA); // [cite: 19]
        }
    }

    /**
     * Empleados por horas no reciben bonos.
     * Sin embargo, si tienen más de 1 año, pueden acceder a un fondo de ahorro (2% del salario). [cite: 43, 45]
     */
    @Override
    public double calcularBeneficios() {
        if (getAniosAntiguedad() > 1 && this.aceptaFondoAhorro) {
            // [cite: 45] (Asumiendo que es el 2% del salario bruto)
            return calcularSalarioBruto() * 0.02;
        }
        return 0.0; // 
    }

    /**
     * Deducciones obligatorias de Seguro Social y Pensión (4% del salario bruto). 
     * (Nota: ARL [cite: 37] no está especificado, solo se aplica el 4%).
     */
    @Override
    public double calcularDeducciones() {
        return calcularSalarioBruto() * 0.04; // 
    }
}