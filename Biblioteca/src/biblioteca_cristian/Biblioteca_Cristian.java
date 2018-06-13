/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca_cristian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author crist
 */
public class Biblioteca_Cristian {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Libro> libros = new ArrayList<>();

        int x;
        for (;;) {

            do {
                System.out.println("\n Menú Biblioteca: ");
                System.out.println("1. Añadir libro");
                System.out.println("2. Modificar libro");
                System.out.println("3. Eliminar libro");
                System.out.println("4. Buscar Libro");
                System.out.println("5. Salir");
                System.out.println("Elige una opción: ");

                x = sc.nextInt();
            } while (x < 1 || x > 5);

            sc.nextLine();
            switch (x) {
                case 1:
                    introducirLibro(libros);
                    break;
                case 2:
                    modificarLibro(libros);
                    break;
                case 3:
                    eliminarLibro(libros);
                    break;
                case 4:
                    buscarLibro(libros);
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }

    private static void introducirLibro(ArrayList<Libro> libros) {
        String Asignatura, Titulo, Editorial, Autor;
        int  Npaginas, ISBN;
        
        System.out.println("Asignatura: ");
        Asignatura =sc.nextLine();
        System.out.println("Titulo: ");
        Titulo =sc.nextLine();
        System.out.println("Editorial: ");
        Editorial =sc.nextLine();
        System.out.println("Autor: ");
        Autor =sc.nextLine();
        System.out.println("Numero de paginas: ");
        Npaginas = Integer.parseInt(sc.nextLine());
        System.out.println("ISBN:  ");
        ISBN = Integer.parseInt(sc.nextLine());
        libros.add(new Libro( Asignatura, Titulo, Editorial, Autor, Npaginas, ISBN));
    }

    private static void modificarLibro(ArrayList<Libro> libros) {
        String Asignatura, Titulo, Editorial, Autor;
        int  Npaginas, ISBN;
        
        System.out.println("Introduce la asignatura");
        Asignatura = sc.nextLine();

        int pos = Collections.binarySearch(libros,
                new Libro(Asignatura, null,null, null, 0, 0), new PorAsignatura());

        if (pos < 1) {
            System.out.println("\nNo se ha encontrado un libro con esos datos");
        } else {
            
        System.out.println("Asignatura: ");
        Asignatura =sc.nextLine();
        System.out.println("Titulo: ");
        Titulo =sc.nextLine();
        System.out.println("Editorial: ");
        Editorial =sc.nextLine();
        System.out.println("Autor: ");
        Autor =sc.nextLine();
        System.out.println("Numero de paginas: ");
        Npaginas = Integer.parseInt(sc.nextLine());
        System.out.println("ISBN:  ");
        ISBN = Integer.parseInt(sc.nextLine());
            System.out.println("Se ha modificado el libro " + libros.get(pos));
            libros.remove(pos);
            libros.add(new Libro( Asignatura, Titulo, Editorial, Autor, Npaginas, ISBN));
        }
    }
    
    private static void eliminarLibro(ArrayList<Libro> libros) {
        System.out.println("Introduce la asignatura");
        String Asignatura = sc.next();

        int pos = Collections.binarySearch(libros,
                new Libro(Asignatura, null,null, null, 0, 0), new PorAsignatura());

        if (pos < 1) {
            System.out.println("\nNo se ha encontrado un libro con esos datos");
        } else {
            System.out.println("Se ha eliminado el libro " + libros.get(pos));
            libros.remove(pos);
        }
    }    

    private static void buscarLibro(ArrayList<Libro> libros) {
        
       System.out.println("Introduce titulo" );
        String Titulo = sc.next() ;
       
       int pos = Collections.binarySearch(libros,
                new Libro(null, Titulo, null, null, 0, 0), new PorTitulo());

        if (pos < 0) {
            System.out.println("\nNo se ha encontrado ningún libro con ese titulo");
        } else {
            System.out.println("\nLista de libros: " + libros.get(pos));
        }
    }

}
