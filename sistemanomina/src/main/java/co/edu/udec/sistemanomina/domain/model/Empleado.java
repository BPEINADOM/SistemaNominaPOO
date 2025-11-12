package co.edu.udec.sistemanomina.domain.model;

import co.edu.udec.sistemanomina.domain.exception.SalarioInvalidoException;

/**
 * Clase abstracta que representa un empleado base.
 * Define los atributos y metodos para los tipos de empleados.
 */

public abstract class Empleado {
    private int id;
    private String nombre;
    private String apellido;
    private String cedula;
    private double salarioBase;
    private int aniosAntiguedad;

    public Empleado(int id, String nombre, String apellido, String cedula, double salarioBase, int aniosAntiguedad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.salarioBase = salarioBase;
        this.aniosAntiguedad = aniosAntiguedad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public int getAniosAntiguedad() {
        return aniosAntiguedad;
    }

    public void setAniosAntiguedad(int aniosAntiguedad) {
        this.aniosAntiguedad = aniosAntiguedad;
    }

    // Metodos que deben implementar las subclases de empleados
    public abstract double calcularSalarioBruto();

    public abstract double calcularDeducciones();

    public abstract double calcularBeneficios();

    /**
     * Calcula el salario neto total.
     * Regla de negocio: El salario neto no puede ser negativo.
     */
    public double calcularSalarioNeto() {
        double neto = calcularSalarioBruto() + calcularBeneficios() - calcularDeducciones();

        if (neto < 0) {
            throw new SalarioInvalidoException("El salario no puede ser negativo.");
        }

        return neto;
    }

}
