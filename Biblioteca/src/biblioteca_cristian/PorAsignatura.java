¡ * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca_cristian;

import java.util.Comparator;

/**
 *
 * @author crist
 */
public class PorAsignatura implements Comparator<Libro> {

    @Override
     public int compare(Libro o1, Libro o2) {
       //  return o1.getAsignatura().compareTo(o2.getAsignatura());
        int x = o1.getAsignatura().compareTo(o2.getAsignatura());
        if (x != 0) {
        }
       
        return x;
    }

}

