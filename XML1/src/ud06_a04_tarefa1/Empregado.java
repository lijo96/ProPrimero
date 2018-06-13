/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ud06_a04_tarefa1;
public class Empregado {
    String nome;
    String dni;
    int idade;
    String departamento;
    
    Empregado(String n, String d, int i, String dep) {
        nome = n;
        dni = d;
        idade = i;
        departamento = dep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    @Override
    public String toString() {
        return "Empregado: \t"+nome+"\t"+dni+"\t"+idade+"\t"+departamento;
    }  
}
