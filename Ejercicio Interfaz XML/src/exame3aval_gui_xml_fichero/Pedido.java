/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exame3aval_gui_xml_fichero;

/**
 *
 * @author crist
 */
public class Pedido {
    private Libro libro;
    private int unidades;

    public Pedido(Libro libro, int unidades) {
        this.libro = libro;
        this.unidades = unidades;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
    
    
    
}
