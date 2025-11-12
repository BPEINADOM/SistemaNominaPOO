package co.edu.udec.sistemanomina;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import co.edu.udec.sistemanomina.domain.model.EmpleadoPorComision;

public class EmpleadoPorComisionTest {

    private final double DELTA = 0.01;

    @Test
    public void testCalcularSalarioBruto() {
        // Escenario: Base 1.000.000 + 10% de 50.000.000 en ventas
        EmpleadoPorComision emp = new EmpleadoPorComision(1, "Carlos", "Velez", "789", 1000000, 6, 50000000, 0.10);
        double salarioBruto = emp.calcularSalarioBruto();
        // 1000000 + (50000000 * 0.10) = 1000000 + 5000000 = 6000000
        assertEquals(6000000.0, salarioBruto, DELTA);
    }

    @Test
    public void testCalcularBeneficiosConBonoVentasAltas() {
        // Escenario: Ventas > 20M ($50.000.000)
        // Beneficio = 1.000.000 (Alimentación) + 3% de ventas (50M * 0.03 = 1.500.000)
        EmpleadoPorComision emp = new EmpleadoPorComision(1, "Carlos", "Velez", "789", 1000000, 6, 50000000, 0.10);
        double beneficios = emp.calcularBeneficios();
        // 1000000 + 1500000 = 2500000
        assertEquals(2500000.0, beneficios, DELTA);
    }

    @Test
    public void testCalcularBeneficiosSinBonoVentas() {
        // Escenario: Ventas <= 20M ($15.000.000)
        // Beneficio = 1.000.000 (Alimentación)
        EmpleadoPorComision emp = new EmpleadoPorComision(1, "Laura", "Marin", "101", 1000000, 3, 15000000, 0.10);
        double beneficios = emp.calcularBeneficios();
        assertEquals(1000000.0, beneficios, DELTA);
    }

    @Test
    public void testCalcularDeducciones() {
        // Escenario: Bruto = 6.000.000
        // Deducción = 4% de 6.000.000
        EmpleadoPorComision emp = new EmpleadoPorComision(1, "Carlos", "Velez", "789", 1000000, 6, 50000000, 0.10);
        double deducciones = emp.calcularDeducciones();
        // 6000000 * 0.04 = 240000
        assertEquals(240000.0, deducciones, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorConVentasNegativas() {
        // Prueba la validación en el constructor
        new EmpleadoPorComision(1, "Error", "User", "000", 1000000, 1, -1000, 0.10);
    }
}