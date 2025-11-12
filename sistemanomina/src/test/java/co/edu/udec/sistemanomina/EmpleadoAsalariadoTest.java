package co.edu.udec.sistemanomina;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import co.edu.udec.sistemanomina.domain.model.EmpleadoAsalariado;

public class EmpleadoAsalariadoTest {

    private final double DELTA = 0.01; // Margen de error para doubles

    @Test
    public void testCalcularSalarioBruto() {
        // El salario bruto es simplemente el salario base (fijo)
        EmpleadoAsalariado emp = new EmpleadoAsalariado(1, "Ana", "Ruiz", "111", 1500000, 3);
        assertEquals(1500000.0, emp.calcularSalarioBruto(), DELTA);
    }

    @Test
    public void testCalcularBeneficiosSinAntiguedad() {
        // Escenario: 3 años. Solo recibe Bono Alimentación.
        EmpleadoAsalariado emp = new EmpleadoAsalariado(1, "Ana", "Ruiz", "111", 1500000, 3);
        // Beneficio = 1.000.000 (Alimentación)
        assertEquals(1000000.0, emp.calcularBeneficios(), DELTA);
    }

    @Test
    public void testCalcularBeneficiosConAntiguedad() {
        // Escenario: 10 años (> 5). Recibe Alimentación + Bono 10%.
        EmpleadoAsalariado emp = new EmpleadoAsalariado(1, "Pedro", "Lara", "222", 2000000, 10);
        // Beneficio = 1.000.000 (Alim) + 10% de 2.000.000 (Bono)
        // 1.000.000 + 200.000 = 1.200.000
        assertEquals(1200000.0, emp.calcularBeneficios(), DELTA);
    }

    @Test
    public void testCalcularDeduccionesAsalariado() {
        // Escenario: Salario Bruto 1.500.000
        // Tu clase EmpleadoAsalariado calcula 4.5% (4% SS + 0.5% ARL)
        EmpleadoAsalariado emp = new EmpleadoAsalariado(1, "Ana", "Ruiz", "111", 1500000, 3);
        double deducciones = emp.calcularDeducciones();
        // 1.500.000 * 0.045 = 67.500
        assertEquals(67500.0, deducciones, DELTA);
    }

    @Test
    public void testCalcularSalarioNetoCompleto() {
        // Escenario: 10 años, Salario base 2.000.000
        EmpleadoAsalariado emp = new EmpleadoAsalariado(1, "Pedro", "Lara", "222", 2000000, 10);
        // Bruto = 2.000.000
        // Beneficios = 1.200.000 (Alim + Bono 10%)
        // Deducciones = 2.000.000 * 0.045 = 90.000
        // Neto = 2.000.000 + 1.200.000 - 90.000 = 3.110.000
        assertEquals(3110000.0, emp.calcularSalarioNeto(), DELTA);
    }
}