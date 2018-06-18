/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author ApliWeb
 */
public class ConsultaModificacionBD {
   /**Programa que modifica a nota dos alumnos.
    * Os alumnos a modificar serán aqueles que teñan
    * unha nota >= 5.
    * A nota pasará a ser un 10% maior
    * Despois da modificación mostrarase por consola o nome
    * e a nova nota dos alumnos.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList <Alumno> alumnos = new ArrayList<>();
        Iterator it;
        Connection conexion = null;
        Statement sentenza = null;
        String consulta = null;
        ResultSet rs = null;
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/proba?useSSL=false", 
                    "root", 
                    "abc123.");
            sentenza = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            consulta = "select * from alumnos where nota >= 5";
            rs = sentenza.executeQuery(consulta);
            while (rs.next()) {
                float nota = rs.getFloat("nota") * 1.10f;
                if (nota > 10) {
                    nota = 10;
                }
                rs.updateFloat("nota", nota);
                rs.updateRow();
            }
            rs.beforeFirst();
            while (rs.next()) {
                System.out.println("Alumno: " + rs.getString(2) 
                        + ", " + rs.getFloat("nota"));
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
