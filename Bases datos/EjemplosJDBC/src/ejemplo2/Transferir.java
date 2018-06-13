package ejemplo2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author Ivan Salas Corrales <http://programandoointentandolo.com/>
 */
public class Transferir implements ActionListener {

    private Transferencias t;

    public Transferir(Transferencias t) {
        this.t = t;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Connection cn = null;

        try {
            // Driver para conectar con MySQL
            Class.forName("com.mysql.jdbc.Driver");

            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuentas", "root", "abc123.");

            // Obtenemos la fecha actual
            Date date = new Date();
            Date fecha = new java.sql.Date(date.getTime());

            String sqlCuenta1 = "UPDATE cuentas SET saldo = saldo - "
                    + t.getTfCantidad().getValue() + " WHERE codigo = '" + t.getTfCuenta1().getText()
                    + "' AND saldo >= " + t.getTfCantidad().getValue();

            String sqlMovimiento1 = "INSERT INTO movimientos SET codigo = '"
                    + t.getTfCuenta1().getText() + "', tipo = '1', cantidad= 0-"
                    + t.getTfCantidad().getValue() + ", fecha= '" + fecha + "'";

            String sqlCuenta2 = "UPDATE cuentas SET saldo = saldo + "
                    + t.getTfCantidad().getValue() + " WHERE codigo = '" + t.getTfCuenta2().getText() + "'";

            String sqlMovimiento2 = "INSERT INTO movimientos SET codigo = '"
                    + t.getTfCuenta2().getText() + "', tipo = '1', cantidad= "
                    + t.getTfCantidad().getValue() + ", fecha= '" + fecha + "'";

            Statement st = cn.createStatement();

            // Indica que las operaciones no se validan automaticamente
            cn.setAutoCommit(false);

            // Si se hacen ambas actualizaciones se hace la transaccion y sino se deshace
            if ((st.executeUpdate(sqlCuenta1) != 0) && (st.executeUpdate(sqlCuenta2) != 0)
                  &&(st.executeUpdate(sqlMovimiento1) != 0) && (st.executeUpdate(sqlMovimiento2) != 0)) {
               
                // Ejecuta la transaccion
                cn.commit();
                t.setLbResultado("Transeferencia completada.");
            } else {
                // Deshace los cambios hechos dentro de la transaccion 
                cn.rollback();
                t.setLbResultado("Error: La transeferencia no se ha completado.");
            }

        } catch (SQLException ex) {
            try {
                // Deshace los cambios hechos dentro de la transaccion 
                cn.rollback();
                t.setLbResultado("Error: La transeferencia no se ha completado.");
            } catch (SQLException ex1) {
            }
        } catch (ClassNotFoundException ex) {
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
            }
        }
    }
}