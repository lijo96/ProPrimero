package ejemplo2;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

/**
 *
 * @author Ivan Salas Corrales <http://programandoointentandolo.com/>
 */
public class Transferencias extends JFrame {

    private JLabel lbResultado;
    private JTextField tfCuenta1;
    private JTextField tfCuenta2;
    private JFormattedTextField tfCantidad;

    public Transferencias() {
        super("Transferencias");
        JLabel lbCuenta1 = new JLabel("Codigo cuenta 1:");
        JLabel lbCuenta2 = new JLabel("Codigo cuenta 2:");
        JLabel lbCantidad = new JLabel("Cantidad a transferir: ");
        lbResultado = new JLabel("Transferencia...");

        tfCuenta1 = new JTextField();
        tfCuenta2 = new JTextField();
        tfCantidad = new JFormattedTextField(new Float(0));

        JButton btTransferir = new JButton("Transferir");
        btTransferir.addActionListener(new Transferir(this));

        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(20, 20, 0, 20);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        c.add(lbCuenta1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        c.add(lbCuenta2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        c.add(lbCantidad, gbc);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 200;
        c.add(tfCuenta1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        c.add(tfCuenta2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        c.add(tfCantidad, gbc);
        gbc.ipadx = 0;

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        c.add(btTransferir, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        c.add(lbResultado, gbc);

        this.setBounds(400, 100, 400, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setLbResultado(String resultado) {
        this.lbResultado.setText(resultado);
    }

    public JFormattedTextField getTfCantidad() {
        return tfCantidad;
    }

    public JTextField getTfCuenta1() {
        return tfCuenta1;
    }

    public JTextField getTfCuenta2() {
        return tfCuenta2;
    }

    public static void main(String[] args) {
        // Carga el archivo de configuracion de log4J

        Transferencias t = new Transferencias();
    }
}