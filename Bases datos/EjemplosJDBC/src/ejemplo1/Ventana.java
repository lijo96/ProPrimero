package ejemplo1;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

/**
 *
 * @author Ivan Salas Corrales <http://programandoointentandolo.com/>
 */
public class Ventana extends JFrame {

    private JTextField textCodigo;
    private JTextField textNombre;
    private JTextField textEmail;
    private JFormattedTextField textSaldo;
    private JLabel lbMensaje;

    public Ventana() {
        super("Cuentas");

        JLabel lbCodigo = new JLabel("Codigo:");
        JLabel lbNombre = new JLabel("Nombre:");
        JLabel lbEmail = new JLabel("E-mail:");
        JLabel lbSaldo = new JLabel("Saldo:");
        lbMensaje = new JLabel("");

        textCodigo = new JTextField();
        textNombre = new JTextField();
        textEmail = new JTextField();
        textSaldo = new JFormattedTextField(new Double(0));

        JButton btAgregar = new JButton("Añadir");
        CreaCuenta cuenta = new CreaCuenta(this);
        btAgregar.addActionListener(cuenta);

        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        c.add(lbCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        c.add(lbNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        c.add(lbEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        c.add(lbSaldo, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;

        gbc.gridx = 1;
        gbc.gridy = 0;
        c.add(textCodigo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        c.add(textNombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        c.add(textEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        c.add(textSaldo, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.ipadx = 0;

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        c.add(btAgregar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        c.add(lbMensaje, gbc);

        this.setBounds(300, 200, 400, 400);
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JTextField getTextCodigo() {
        return textCodigo;
    }

    public JTextField getTextEmail() {
        return textEmail;
    }

    public JTextField getTextNombre() {
        return textNombre;
    }

    public JFormattedTextField getTextSaldo() {
        return textSaldo;
    }

    public JLabel getLbMensaje() {
        return lbMensaje;
    }

    public static void main(String[] args) {
        // Carga el archivo de configuracion de log4J

        Ventana c = new Ventana();
    }
}