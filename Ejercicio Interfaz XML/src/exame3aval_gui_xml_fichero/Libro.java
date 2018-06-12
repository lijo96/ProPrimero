/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exame3aval_gui_xml_fichero;

import java.util.ArrayList;

/**
 *
 * @author crist
 */
public class Libro {

    private String titulo;
    private String editor;
    private float prezo;
    private int pax;
    private String xenero;
    private ArrayList<String> autores;
    private int puntuacion;

    public Libro(String titulo, String editor, float prezo, int pax, String xenero, ArrayList<String> autores, int puntuacion) {
        this.titulo = titulo;
        this.editor = editor;
        this.prezo = prezo;
        this.pax = pax;
        this.xenero = xenero;
        this.autores = autores;
        this.puntuacion = puntuacion;
    }
    
       /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the editor
     */
    public String getEditor() {
        return editor;
    }

    /**
     * @param editor the editor to set
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }

    /**
     * @return the prezo
     */
    public float getPrezo() {
        return prezo;
    }

    /**
     * @param prezo the prezo to set
     */
    public void setPrezo(float prezo) {
        this.prezo = prezo;
    }

    /**
     * @return the pax
     */
    public int getPax() {
        return pax;
    }

    /**
     * @param pax the pax to set
     */
    public void setPax(int pax) {
        this.pax = pax;
    }

    /**
     * @return the xenero
     */
    public String getXenero() {
        return xenero;
    }

    /**
     * @param xenero the xenero to set
     */
    public void setXenero(String xenero) {
        this.xenero = xenero;
    }

    /**
     * @return the autores
     */
    public ArrayList<String> getAutores() {
        return autores;
    }

    /**
     * @param autores the autores to set
     */
    public void setAutores(ArrayList<String> autores) {
        this.autores = autores;
    }

    /**
     * @return the puntuacion
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * @param puntuacion the puntuacion to set
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
}
