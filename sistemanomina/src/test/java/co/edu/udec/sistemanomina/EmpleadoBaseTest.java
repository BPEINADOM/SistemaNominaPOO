package co.edu.udec.sistemanomina;


import org.junit.Test;
import co.edu.udec.sistemanomina.domain.exception.SalarioInvalidoException;
import co.edu.udec.sistemanomina.domain.model.Empleado;

/**
 * Prueba la lógica de la clase base Empleado,
 * específicamente la validación de salario neto negativo.
 */
public class EmpleadoBaseTest {

    @Test(expected = SalarioInvalidoException.class)
    public void testSalarioNetoNegativoLanzaExcepcion() {
        
        // Creamos una subclase anónima solo para esta prueba
        Empleado empleadoPrueba = new Empleado(1, "Test", "User", "000", 1000, 1) {
            
            @Override
            public double calcularSalarioBruto() {
                // Salario bruto bajo
                return 500.0;
            }

            @Override
            public double calcularBeneficios() {
                // Sin beneficios
                return 0.0;
            }

            @Override
            public double calcularDeducciones() {
                // Deducciones altísimas (mayor que bruto + beneficios)
                return 1000.0; 
            }
        };

        // Esto debería calcular (500 + 0 - 1000) = -500
        // y lanzar la excepción SalarioInvalidoException
        empleadoPrueba.calcularSalarioNeto();
    }
}