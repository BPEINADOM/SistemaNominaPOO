package co.edu.udec.sistemanomina;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import co.edu.udec.sistemanomina.domain.model.EmpleadoPorHoras;

public class EmpleadoPorHorasTest {

    private final double DELTA = 0.01;

    @Test
    public void testCalcularSalarioBrutoHorasNormales() {
        // Escenario: 30 horas a $10.000/hora
        EmpleadoPorHoras emp = new EmpleadoPorHoras(1, "Juan", "Perez", "123", 10000, 2, 30, true);
        double salarioBruto = emp.calcularSalarioBruto();
        // 30 * 10000 = 300000
        assertEquals(300000.0, salarioBruto, DELTA);
    }

    @Test
    public void testCalcularSalarioBrutoConHorasExtras() {
        // Escenario: 45 horas a $10.000/hora
        // 40 horas normales (400.000) + 5 horas extra a 1.5x (5 * 15.000 = 75.000)
        EmpleadoPorHoras emp = new EmpleadoPorHoras(1, "Ana", "Gomez", "456", 10000, 3, 45, true);
        double salarioBruto = emp.calcularSalarioBruto();
        // 400000 + 75000 = 475000
        assertEquals(475000.0, salarioBruto, DELTA);
    }

    @Test
    public void testCalcularBeneficiosFondoAhorro() {
        // Escenario: > 1 año y acepta fondo. Bruto = 300.000
        // Beneficio = 2% del bruto
        EmpleadoPorHoras emp = new EmpleadoPorHoras(1, "Juan", "Perez", "123", 10000, 2, 30, true);
        double beneficios = emp.calcularBeneficios();
        // 300000 * 0.02 = 6000
        assertEquals(6000.0, beneficios, DELTA);
    }

    @Test
    public void testCalcularBeneficiosSinFondoPorAntiguedad() {
        // Escenario: < 1 año (0 años)
        EmpleadoPorHoras emp = new EmpleadoPorHoras(1, "Juan", "Perez", "123", 10000, 0, 30, true);
        double beneficios = emp.calcularBeneficios();
        assertEquals(0.0, beneficios, DELTA);
    }

    @Test
    public void testCalcularBeneficiosSinFondoPorNoAceptar() {
        // Escenario: > 1 año pero no acepta fondo
        EmpleadoPorHoras emp = new EmpleadoPorHoras(1, "Juan", "Perez", "123", 10000, 3, 30, false);
        double beneficios = emp.calcularBeneficios();
        assertEquals(0.0, beneficios, DELTA);
    }

    @Test
    public void testCalcularDeducciones() {
        // Escenario: Bruto = 300.000
        // Deducción = 4% del bruto
        EmpleadoPorHoras emp = new EmpleadoPorHoras(1, "Juan", "Perez", "123", 10000, 2, 30, true);
        double deducciones = emp.calcularDeducciones();
        // 300000 * 0.04 = 12000
        assertEquals(12000.0, deducciones, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorConHorasNegativas() {
        // Prueba la validación en el constructor
        new EmpleadoPorHoras(1, "Error", "User", "000", 10000, 1, -10, false);
    }
}