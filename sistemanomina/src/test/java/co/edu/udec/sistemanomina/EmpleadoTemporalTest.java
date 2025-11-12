package co.edu.udec.sistemanomina;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import co.edu.udec.sistemanomina.domain.model.EmpleadoTemporal;

public class EmpleadoTemporalTest {

    private final double DELTA = 0.01;

    @Test
    public void testCalcularSalarioBruto() {
        // Escenario: Salario fijo (base) de 1.200.000
        EmpleadoTemporal emp = new EmpleadoTemporal(1, "Luis", "Diaz", "321", 1200000, 0, "2025-12-31");
        double salarioBruto = emp.calcularSalarioBruto();
        // Bruto = SalarioBase
        assertEquals(1200000.0, salarioBruto, DELTA);
    }

    @Test
    public void testCalcularBeneficios() {
        // Escenario: Empleado temporal no recibe beneficios
        EmpleadoTemporal emp = new EmpleadoTemporal(1, "Luis", "Diaz", "321", 1200000, 0, "2025-12-31");
        double beneficios = emp.calcularBeneficios();
        assertEquals(0.0, beneficios, DELTA);
    }

    @Test
    public void testCalcularDeducciones() {
        // Escenario: Bruto = 1.200.000
        // Deducción = 4% de 1.200.000
        EmpleadoTemporal emp = new EmpleadoTemporal(1, "Luis", "Diaz", "321", 1200000, 0, "2025-12-31");
        double deducciones = emp.calcularDeducciones();
        // 1200000 * 0.04 = 48000
        assertEquals(48000.0, deducciones, DELTA);
    }

    @Test
    public void testCalcularSalarioNeto() {
        // Escenario: Base 1.200.000
        EmpleadoTemporal emp = new EmpleadoTemporal(1, "Luis", "Diaz", "321", 1200000, 0, "2025-12-31");
        // Bruto = 1200000
        // Beneficios = 0
        // Deducciones = 48000
        // Neto = 1200000 + 0 - 48000 = 1152000
        double salarioNeto = emp.calcularSalarioNeto();
        assertEquals(1152000.0, salarioNeto, DELTA);
    }
}