package ejemplo1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JLabel;

/**
 *
 * @author Ivan Salas Corrales <http://programandoointentandolo.com/>
 */
public class CreaCuenta implements ActionListener {

    private Ventana v;
    private Connection cn;
    private PreparedStatement pstBuscarCodigo;
    private PreparedStatement pstInsertarCuenta;

    public CreaCuenta(Ventana v) {
        this.v = v;
        try {
            // Driver para conectar con MySQL
            Class.forName("com.mysql.jdbc.Driver");
            // Conexion con la base de datos
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuentas", "root", "abc123.");


            String sqlBusqueda = "SELECT codigo FROM cuentas WHERE codigo=?";
            pstBuscarCodigo = cn.prepareStatement(sqlBusqueda);

            String sqlNuevaCuenta = "INSERT INTO cuentas VALUES (?,?,?,?)";
            pstInsertarCuenta = cn.prepareStatement(sqlNuevaCuenta);

        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String codigo = v.getTextCodigo().getText();
        String nombre = v.getTextNombre().getText();
        String email = v.getTextEmail().getText();
        double saldo = (double) v.getTextSaldo().getValue();
        JLabel lbMensaje = v.getLbMensaje();

        try {
            pstBuscarCodigo.setString(1, codigo);
            
            // Si el codigo no esta en la bd se añade la cuenta
            ResultSet rs = pstBuscarCodigo.executeQuery();
            if (!rs.next()) {
                pstInsertarCuenta.setString(1, codigo);
                pstInsertarCuenta.setString(2, nombre);
                pstInsertarCuenta.setString(3, email);
                pstInsertarCuenta.setDouble(4, saldo);
                pstInsertarCuenta.executeUpdate();
                lbMensaje.setText("Cuenta agregada correctamente");

            } else {
                lbMensaje.setText("El codigo indicado ya esta en la base de datos");
            }
        } catch (SQLException ex) {
        }
    }
}