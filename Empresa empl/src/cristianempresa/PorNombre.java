/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristianempresa;

import java.util.Comparator;

/**
 *
 * @author crist
 */


public class PorNombre implements Comparator<Empregado> {

    
    public int compare(Empregado o1, Empregado o2) {
        int x = o1.getNombre().compareTo(o2.getNombre());
        if (x != 0) {
            
        }
        return x;

}
}