/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author ApliWeb
 */
public class AccesoBD {

    /**Programa que crea a táboa alumnos se non existe
     * e inserta novos alumnos na táboa
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList <Alumno> alumnos = new ArrayList<>();
        Iterator it;
        Connection conexion = null;
        Statement sentenza = null;
        alumnos.add(new Alumno("Ana", "11111111G", 15, 5.6f, false));
        alumnos.add(new Alumno("Pablo", "22222222G", 18, 7.6f, true));
        alumnos.add(new Alumno("Diego", "33333333G", 15, 5.6f, true));
        alumnos.add(new Alumno("Paula", "44444444G", 16, 4.6f, false));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/proba?useSSL=false", 
                    "root", 
                    "abc123.");
            sentenza = conexion.createStatement();
            int rdo = sentenza.executeUpdate("create table if not exists alumnos ("
                    + "dni char(9),"
                    + "nome varchar(20),"
                    + "idade tinyint,"
                    + "nota decimal(4,2),"
                    + "repite bool,"
                    + "constraint clave_pri primary key (dni)"
                    + ")");
            System.out.println("Valor rdo: " + rdo);
            it = alumnos.iterator();
            for (;it.hasNext();) {
                Alumno al = (Alumno) it.next();
                sentenza.executeUpdate("insert into alumnos "
                    + "values ('" + al.getDni()+ "', '" 
                        + al.getNome() + "', " + al.getIdade() 
                        + ", " + al.getNota() + ", " + al.isRepite() + ")");
            }
       } catch (ClassNotFoundException ex) {
           System.out.println("Erro ao rexistrar o driver");
       } catch (SQLException SQLex) {
           System.out.println("Problemas conexión BD");
           System.out.println(SQLex.getMessage());
       } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException SQLe) {
                    System.out.println("Erro ao pechar a conexión");
                }
            }
            
            if (sentenza != null) {
                try {
                    sentenza.close();
                } catch (SQLException SQLe) {
                    System.out.println("Erro ao pechar a sentenza");
                }
            }
        }
    }
    
}
