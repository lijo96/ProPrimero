package ejemplo3;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ivan Salas Corrales <http://programandoointentandolo.com/>
 */
public class MovimientosCuenta extends JFrame {

    public MovimientosCuenta() {
        super("Movimientos de la cuenta");

        JLabel lbCuenta = new JLabel("Codigo de la cuenta: ");
        JTextField tfCuenta = new JTextField();
        JButton btMuestraMovimientos = new JButton("Mostrar movimientos");

        // Tabla
        DefaultTableModel dtm = new DefaultTableModel();
        String[] titulos = {"Tipo de movimiento", "Cantidad", "Fecha"};
        dtm.setColumnIdentifiers(titulos);


        JTable jtMovimientos = new JTable(dtm);
        JScrollPane scrollPane = new JScrollPane(jtMovimientos);


        btMuestraMovimientos.addActionListener(new MuestraMovimientos(dtm, tfCuenta));

        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(20, 20, 0, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        c.add(lbCuenta, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        c.add(tfCuenta, gbc);

        gbc.insets = new Insets(10, 20, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;

        c.add(btMuestraMovimientos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c.add(scrollPane, gbc);


        this.setVisible(true);
        this.setBounds(400, 100, 400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // Carga el archivo de configuracion de log4J

        MovimientosCuenta m = new MovimientosCuenta();
    }
}
