/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristianempresa;

/**
 *
 * @author crist
 */
public class Empregado {
    
    private String nombre;
    private String DNI;
    private String departamento;
    private int edad;

    public Empregado(String nombre, String DNI, String departamento, int edad) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.departamento = departamento;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Empregado{" + "nombre=" + nombre + ", DNI=" + DNI + ", departamento=" + departamento + ", edad=" + edad + '}';
    }
    
    
}
