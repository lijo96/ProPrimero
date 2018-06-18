/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author ApliWeb
 */
public class ConsultaBD {

    /**Programa que consulta da BD todos os alumnos cunha nota >=5
     * Mostra por consola o seu nome e nota
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
            sentenza = conexion.createStatement();
            consulta = "select * from alumnos where nota > 5";
            rs = sentenza.executeQuery(consulta);
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
