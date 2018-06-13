/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristianempresa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author crist
 */
public class CristianEmpresa {
    
    
static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Empregado> empregados = new ArrayList<>();

        int x;
        for (;;) {
            do {
                System.out.println("\n Empresa: ");
                System.out.println("1. Listar empleados alfabeticamente ");
                System.out.println("2. Listar empleados por departamento y dentro de este alfabético ");
                System.out.println("3. Añadir Empleado");
                System.out.println("4. Borrar empleado");
                System.out.println("5. Salir");
                System.out.println("Elige una opción: ");

                x = sc.nextInt();
            } while (x < 1 || x > 5);

            sc.nextLine();
            switch (x) {
                case 1:
                    listEmpl(empregados);
                    break;
                case 2:
                    listEmplDep(empregados);
                    break;
                case 3:
                    addEmpl(empregados);
                    break;
                case 4:
                    suprEmpl(empregados);
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }

    private static void listEmpl(ArrayList<Empregado> empregados) {
         System.out.println("Empleados ordenados por orden alfabético");
        
      
                Collections.sort(empregados, new PorNombre());
                System.out.println("\nPor Nombre: ");
                listEmpl(empregados);
                System.out.println("");
               
        
    }
    

    private static void listEmplDep(ArrayList<Empregado> empregados) {
      
    }

    private static void addEmpl(ArrayList<Empregado> empregados) {
         String nombre, DNI, departamento;
         int edad;
         
         System.out.println("Nombre:");
         nombre = sc.nextLine();
         System.out.println("DNI: ");
         DNI = sc.nextLine();
         System.out.println("Departamento: ");
         departamento = sc.nextLine();
         System.out.println("Edad:");
         edad = Integer.parseInt(sc.nextLine());
         
         empregados.add(new Empregado(nombre, DNI, departamento, edad));
    }

    private static void suprEmpl(ArrayList<Empregado> empregados) {
        System.out.println("Introduce DNI");
        String DNI = sc.nextLine();
        

        int pos = Collections.binarySearch(empregados,
                new Empregado(null, DNI, null, 0), new PorNombre());

        if (pos < 1) {
            System.out.println("\nNo se ha encontrado un empleado que coincida con esos datos");
        } else {
            System.out.println("Se ha eliminado el empleado " + empregados.get(pos));
            empregados.remove(pos);
        }
    }


}
