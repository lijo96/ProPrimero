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
import java.util.Scanner;

/**
 *
 * @author ApliWeb
 */
public class ConsultaOrdeadaBD {
   /**Programa que mostra por consola o nome e nota de todos os alumnos.
    * Os datos serán mostrados ascendente ou descendentemente polo nome
    * en base á opción que elixa o usuario.
    * Proponse dúas versións:
    *    - A primeira versión fai a consulta segundo se queira ter os datos
    * ordeados ascendentemente ou descendentemente.
    *     - A segunda versión fai a consulta de todos os datos xa ordeados polo nome, 
    * e logo fai o recorrido do ResultSet avanzando desde o primeiro rexistro ata 
    * o último (usando next()) ou ben o recorrido desde o último rexistro ata 
    * o primeiro (usando previous()), segundo o usuario indique que a ordenación 
    * é ascendente ou descendente.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList <Alumno> alumnos = new ArrayList<>();
        Iterator it;
        Connection conexion = null;
        Statement sentenza = null;
        String consulta = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        byte valor;
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/proba?useSSL=false", 
                    "root", 
                    "abc123.");
            sentenza = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            consulta = "select * from alumnos order by nome";
            
            System.out.print("Datos ordeados ascendente [0] ou descendentemente [1]: ");
            valor = sc.nextByte();
            // OPCIÓN 1: Realizar a consulta segundo corresponda a ordenación
            /*if (valor == 1) {
                consulta += " desc";
            }
            rs = sentenza.executeQuery(consulta);
            while (rs.next()) {
                System.out.println("Alumno: " + rs.getString(2) 
                        + ", " + rs.getFloat("nota"));
            }*/
            
            //OPCIÓN 2: Realizar a consulta e logo navegar polos rexistros segundo
            //corresponda á ordenación (cara adiante se é ASC ou cara atrás se é DESC
            rs = sentenza.executeQuery(consulta);
            if (valor == 1) {
                rs.afterLast();
                while (rs.previous()) {
                    System.out.println("Alumno: " + rs.getString(2)
                            + ", " + rs.getFloat("nota"));
                }
            } else {
                while (rs.next()) {
                    System.out.println("Alumno: " + rs.getString(2)
                            + ", " + rs.getFloat("nota"));
                }
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
