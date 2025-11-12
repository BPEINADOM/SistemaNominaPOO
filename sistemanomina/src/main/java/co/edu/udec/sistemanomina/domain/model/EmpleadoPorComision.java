package co.edu.udec.sistemanomina.domain.model;

/**
 * Representa un empleado que gana por comisión sobre ventas,
 * además de un salario base.
 */
public class EmpleadoPorComision extends Empleado {

    private double ventasRealizadas;
    private double porcentajeComision; // Se debe ingresar como decimal (ej: 0.1 para 10%)

    public EmpleadoPorComision(int id, String nombre, String apellido, String cedula, double salarioBase, int aniosAntiguedad, double ventasRealizadas, double porcentajeComision) {
        super(id, nombre, apellido, cedula, salarioBase, aniosAntiguedad);
        
        if (ventasRealizadas < 0) { // [cite: 51]
            throw new IllegalArgumentException("Las ventas no pueden ser menores a $0.");
        }
        this.ventasRealizadas = ventasRealizadas;
        this.porcentajeComision = porcentajeComision;
    }

    public double getVentasRealizadas() {
        return ventasRealizadas;
    }

    public void setVentasRealizadas(double ventasRealizadas) {
         if (ventasRealizadas < 0) { // [cite: 51]
            throw new IllegalArgumentException("Las ventas no pueden ser menores a $0.");
        }
        this.ventasRealizadas = ventasRealizadas;
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(double porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }

    /**
     * Salario base + porcentaje de comisión sobre ventas. 
     */
    @Override
    public double calcularSalarioBruto() {
        return getSalarioBase() + (ventasRealizadas * porcentajeComision);
    }

    /**
     * Son empleados permanentes [cite: 41], reciben Bono Alimentación ($1.000.000)[cite: 42].
     * Bono adicional del 3% sobre ventas si superan $20.000.000. 
     */
    @Override
    public double calcularBeneficios() {
        double beneficios = 1000000.0; // Bono Alimentación [cite: 42]

        if (ventasRealizadas > 20000000.0) {
            beneficios += ventasRealizadas * 0.03; // Bono adicional 3% 
        }
        
        return beneficios;
    }

    /**
     * Deducciones obligatorias de Seguro Social y Pensión (4% del salario bruto). 
     */
    @Override
    public double calcularDeducciones() {
        return calcularSalarioBruto() * 0.04; // 
    }
}