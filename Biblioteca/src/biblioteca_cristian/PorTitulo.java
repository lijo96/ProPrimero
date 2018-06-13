/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca_cristian;

import java.util.Comparator;

/**
 *
 * @author crist
 */
public class PorTitulo implements Comparator<Libro> {

    @Override
    public int compare(Libro o1, Libro o2) {
        int x = o1.getTitulo().compareTo(o2.getTitulo());
        if (x != 0) {
        }
       
        return x;
    }
}

