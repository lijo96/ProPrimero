package ejemplo3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ivan Salas Corrales <http://programandoointentandolo.com/>
 */
public class MuestraMovimientos implements ActionListener {

    private DefaultTableModel dtm;
    private Connection cn;
    private JTextField tfCuenta;

    public MuestraMovimientos(DefaultTableModel dtm, JTextField tfCuenta) {
        this.dtm = dtm;
        this.tfCuenta = tfCuenta;
    }
    
   // 1.	Supongamos que tenemos una tabla (clientes) en una base de datos
//    (empresa), con los campos: dni, nombre y fecha_alta. Hacer un método que
//            reciba un parámetro String fecha  y muestre en la consola todos
//            los datos de los clientes dados de alta a partir de esa fecha (incluida).
//            Suponer el formato de fecha que más convenga
    
    public void muestraDatosCliente(String fecha) {
        try {
            // Obtiene la conexion
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuentas", "root", "abc123.");
            
            // Consulta suponiendo el formato de fecha habitual AAAA/MM/DD
            String sql = "SELECT * FROM clientes WHERE fecha_alta <=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, fecha);
            
            // Resultados de la consulta
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("dni") 
                        + " - " + rs.getString("nombre") 
                        + " - " + rs.getString("fecha_alta") 
                        + "\n");
            }
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(MuestraMovimientos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MuestraMovimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tipo;
        String cantidad;
        String fecha;

        // Se borra el contenido de la tabla
        int i = 0;
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(i);
        }

        try {
            // Driver para conectar con MySQL
            Class.forName("com.mysql.jdbc.Driver");

            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuentas", "root", "");


            String sqlBusqueda = "SELECT tm.tipo,  m.cantidad,  m.fecha FROM movimientos AS m INNER JOIN tipos_movimiento AS tm ON tm.id=m.tipo WHERE m.codigo=?";

            PreparedStatement psBusqueda = cn.prepareStatement(sqlBusqueda);

            psBusqueda.setString(1, tfCuenta.getText());
            ResultSet listaMovimientos = psBusqueda.executeQuery();

            // Se ponene en la tabla los valores obtenidos de la consulta    
            while (listaMovimientos.next()) {
                tipo = listaMovimientos.getString("tipo");
                cantidad = listaMovimientos.getString("cantidad");
                fecha = listaMovimientos.getString("fecha");
                dtm.addRow(new String[]{tipo, cantidad, fecha});
            }

            // Cierro el ResultSet y el Statement aunque al cerrar el Statement ya se cierra el ResulSet
            listaMovimientos.close();
            psBusqueda.close();


        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } finally {
            try {
                // Cierra la conexion con la base de datos
                cn.close();
            } catch (SQLException ex) {
            }
        }
    }
}