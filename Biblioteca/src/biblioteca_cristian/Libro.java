/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca_cristian;

/**
 *
 * @author crist
 */
class Libro {
    
    private String Asignatura;
    private String Titulo;
    private String Editorial;
    private String Autor;
    private int ISBN;
    private int Npaginas;
    
   
    public Libro(String Asignatura, String Titulo, String Editorial, String Autor, int Npaginas, int ISBN) {
        this.Asignatura = Asignatura;
        this.Titulo = Titulo;
        this.Editorial = Editorial;
        this.Autor = Autor;
        this.ISBN = ISBN;
        this.Npaginas = Npaginas;
    }


    /**
     * @return the Asignatura
     */
    public String getAsignatura() {
        return Asignatura;
    }

    /**
     * @param Asignatura the Asignatura to set
     */
    public void setAsignatura(String Asignatura) {
        this.Asignatura = Asignatura;
    }

    /**
     * @return the Titulo
     */
    public String getTitulo() {
        return Titulo;
    }

    /**
     * @param Titulo the Titulo to set
     */
    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    /**
     * @return the Editorial
     */
    public String getEditorial() {
        return Editorial;
    }

    /**
     * @param Editorial the Editorial to set
     */
    public void setEditorial(String Editorial) {
        this.Editorial = Editorial;
    }

    /**
     * @return the Autor
     */
    public String getAutor() {
        return Autor;
    }

    /**
     * @param Autor the Autor to set
     */
    public void setAutor(String Autor) {
        this.Autor = Autor;
    }


    /**
     * @return the Npaginas
     */
    public int getNpaginas() {
        return Npaginas;
    }

    /**
     * @param Npaginas the Npaginas to set
     */
    public void setNpaginas(int Npaginas) {
        this.Npaginas = Npaginas;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "Libro{" + "Asignatura=" + Asignatura + ", Titulo=" + Titulo + ", Editorial=" + Editorial + ", Autor=" + Autor + ", ISBN=" + ISBN + ", Npaginas=" + Npaginas + '}';
    }
    
    
    
}
