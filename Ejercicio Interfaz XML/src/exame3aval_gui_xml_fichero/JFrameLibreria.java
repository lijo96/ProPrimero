package exame3aval_gui_xml_fichero;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author crist
 */
public class JFrameLibreria extends javax.swing.JFrame {

    String[] xeneros = {"Ciencia Ficción", "Fantasía", "Histórico", "Informática", "Romántico", "Terror"};
    ArrayList<Libro> libros = new ArrayList<>();

    /**
     * Creates new form JFrameInmobiliaria
     */
    public JFrameLibreria() {
        initComponents();
        this.setLocationRelativeTo(null);
        for (int i = 0; i < xeneros.length; i++) {
            jComboBoxXenero.addItem(xeneros[i]);
        }
    }

    /**
     *
     * @param opcion Pode ter os seguintes valores: - 0: para procurar por
     * título. - 1: para procurar por xénero. - 2: para procurar por prezo. - 3:
     * para procurar todos os libros.
     * @param cadea O significado deste parámetro está relacionado co valor da
     * opción: - Para opción = 0: cadea é o patrón de procura do título. - Para
     * opción = 1: cadea é o xénero a procurar. - Para opción = 2: cadea que
     * corresponde aos valores 0, 1 e 2. * 0: para indicar que a o prezo debe
     * ser igual a. * 1: para indicar que o prezo debe ser menor que. * 2: para
     * indicar que o prezo debe ser maior que.
     * @param valor Este valor só se terá en conta para cando a opción sexa 2.
     * Neste caso representará ao prezo que se quere procurar.
     */
    void procurarLibros(int opcion, String cadea, float valor) {
        String nomeArquivo = "libros.xml";
        String titulo = null;
        String editor = null;
        float prezo = 0f;
        int pax = 0;
        String xenero = null;
        ArrayList<String> autores = new ArrayList<>();
        ArrayList<Integer> opinions = new ArrayList<>();
        boolean gardar = false;
        try {
            //Creación do fluxo de entrada ao documento XML a ler 
            FileInputStream in = new FileInputStream(nomeArquivo);
            //Creación dunha nova factoría
            XMLInputFactory factory = XMLInputFactory.newInstance();
            //Creación do iterador de eventos
            XMLEventReader reader = factory.createXMLEventReader(nomeArquivo, in);

            while (reader.hasNext()) {
                //Se queremos a listaxe completa vamos a querer gardar todos os libros no array
                if (opcion == 3) {
                    gardar = true;
                }
                XMLEvent evento = reader.nextEvent();
                String nomeEvento;

                switch (evento.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement inicio = evento.asStartElement();
                        nomeEvento = inicio.getName().getLocalPart();

                        //Comprobamos se o salario do empregado é superior á
                        //cantidade introducida polo usuairo
                        if (nomeEvento.equalsIgnoreCase("titulo")) {
                            titulo = reader.getElementText();
                            if (opcion == 0) {
                                if (titulo.toLowerCase().matches(cadea.toLowerCase() + ".*")) {
                                    gardar = true;
                                }
                            }

                        } else if (nomeEvento.equalsIgnoreCase("editor")) {
                            //Gardamos o valor do nome do empregado por se
                            //necesitamos imprimilo
                            editor = reader.getElementText();
                        } else if (nomeEvento.equalsIgnoreCase("precio")) {
                            //Gardamos o valor do nome do empregado por se
                            //necesitamos imprimilo
                            prezo = Float.valueOf(reader.getElementText());
                            if (opcion == 2) {
                                switch (cadea) {
                                    case "0":
                                        if (prezo == valor) {
                                            gardar = true;
                                        }
                                        break;
                                    case "1":
                                        if (prezo < valor) {
                                            gardar = true;
                                        }
                                        break;
                                    case "2":
                                        if (prezo > valor) {
                                            gardar = true;
                                        }
                                        break;
                                }
                            }
                        } else if (nomeEvento.equalsIgnoreCase("paginas")) {
                            //Gardamos o valor do nome do empregado por se
                            //necesitamos imprimilo
                            pax = Integer.valueOf(reader.getElementText());
                        } else if (nomeEvento.equalsIgnoreCase("genero")) {
                            //Gardamos o valor do nome do empregado por se
                            //necesitamos imprimilo
                            xenero = reader.getElementText();
                            if (opcion == 1) {
                                if (xenero.toLowerCase().matches(cadea.toLowerCase())) {
                                    gardar = true;
                                }
                            }
                        } else if (nomeEvento.equalsIgnoreCase("autor") && gardar) {
                            Attribute atNome = inicio.getAttributeByName(QName.valueOf("nombre"));
                            Attribute atApel = inicio.getAttributeByName(QName.valueOf("apellido"));
                            autores.add(atApel.getValue() + ", " + atNome.getValue());
                        } else if (nomeEvento.equalsIgnoreCase("opinion") && gardar) {
                            Attribute atEval = inicio.getAttributeByName(QName.valueOf("eval"));
                            opinions.add(Integer.valueOf(atEval.getValue()));
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        EndElement fin = evento.asEndElement();
                        nomeEvento = fin.getName().getLocalPart();
                        //Ao chegar ao final da lectura do elemento empregado
                        //e en caso de que detectáramos que o seu salario é
                        //superior á cantidade indicada, imprimimos o nome
                        if (nomeEvento.equalsIgnoreCase("libro")) {
                            if (gardar) {
                                Iterator it = opinions.iterator();
                                //Importante declarar total como float para que a 
                                //división que ven a continuación sexa con números
                                //decimais
                                int total = 0;
                                for (; it.hasNext();) {
                                    total += (Integer) it.next();
                                }

                                //Redondeamos a media ao enteiro más próximo
                                total = Math.round(total / (float) opinions.size());
                                libros.add(new Libro(titulo, editor, prezo, pax, xenero, autores, total));
                            }
                            autores = new ArrayList<>();
                            opinions = new ArrayList<>();
                            gardar = false;
                        }

                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: Problemas coa lectura do arquivo");
        } catch (XMLStreamException e) {
            System.out.println("ERRO: Problemas co XML");

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupPuntuacion = new javax.swing.ButtonGroup();
        jPanelFondo = new javax.swing.JPanel();
        jPanelNome = new javax.swing.JPanel();
        jLabelNome = new javax.swing.JLabel();
        jPanelDatos = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jTextFieldTitulo = new javax.swing.JTextField();
        jButtonProcurarT = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxXenero = new javax.swing.JComboBox<>();
        jButtonProcurarX = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxPrezo = new javax.swing.JComboBox<>();
        jTextFieldPrezo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButtonProcurarP = new javax.swing.JButton();
        jPanelOpcions = new javax.swing.JPanel();
        jButtonListaxe = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("InmoDeluxe");
        setBackground(new java.awt.Color(255, 255, 204));

        jPanelFondo.setBackground(new java.awt.Color(204, 0, 0));
        jPanelFondo.setPreferredSize(new java.awt.Dimension(653, 453));

        jPanelNome.setBackground(new java.awt.Color(255, 153, 153));
        jPanelNome.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelNome.setForeground(new java.awt.Color(204, 0, 0));

        jLabelNome.setFont(new java.awt.Font("Impact", 1, 48)); // NOI18N
        jLabelNome.setForeground(new java.awt.Color(204, 0, 0));
        jLabelNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNome.setText("Librería Serendipia");

        javax.swing.GroupLayout jPanelNomeLayout = new javax.swing.GroupLayout(jPanelNome);
        jPanelNome.setLayout(jPanelNomeLayout);
        jPanelNomeLayout.setHorizontalGroup(
            jPanelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNomeLayout.createSequentialGroup()
                .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelNomeLayout.setVerticalGroup(
            jPanelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelNome, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );

        jPanelDatos.setBackground(new java.awt.Color(255, 204, 204));
        jPanelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 0), null), "Procura de libros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanelDatos.setForeground(new java.awt.Color(255, 204, 204));

        jLabelTitulo.setText("Por título:");

        jTextFieldTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloActionPerformed(evt);
            }
        });

        jButtonProcurarT.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jButtonProcurarT.setForeground(new java.awt.Color(204, 0, 0));
        jButtonProcurarT.setText("Procurar");
        jButtonProcurarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProcurarTActionPerformed(evt);
            }
        });

        jLabel3.setText("Por xénero:");

        jComboBoxXenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxXeneroActionPerformed(evt);
            }
        });

        jButtonProcurarX.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jButtonProcurarX.setForeground(new java.awt.Color(204, 0, 0));
        jButtonProcurarX.setText("Procurar");
        jButtonProcurarX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProcurarXActionPerformed(evt);
            }
        });

        jLabel11.setText("Por prezo:");

        jComboBoxPrezo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Igual a", "Menor que", "Maior que" }));
        jComboBoxPrezo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPrezoActionPerformed(evt);
            }
        });

        jLabel1.setText("€");
        jLabel1.setToolTipText("");

        jButtonProcurarP.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jButtonProcurarP.setForeground(new java.awt.Color(204, 0, 0));
        jButtonProcurarP.setText("Procurar");
        jButtonProcurarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProcurarPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDatosLayout = new javax.swing.GroupLayout(jPanelDatos);
        jPanelDatos.setLayout(jPanelDatosLayout);
        jPanelDatosLayout.setHorizontalGroup(
            jPanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabelTitulo)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDatosLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jComboBoxXenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanelDatosLayout.createSequentialGroup()
                        .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelDatosLayout.createSequentialGroup()
                        .addComponent(jComboBoxPrezo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPrezo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)))
                .addGroup(jPanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonProcurarT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonProcurarX, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonProcurarP, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanelDatosLayout.setVerticalGroup(
            jPanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDatosLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTitulo)
                    .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonProcurarT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxXenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonProcurarX))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11)
                    .addComponent(jComboBoxPrezo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPrezo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonProcurarP)
                    .addComponent(jLabel1))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jPanelOpcions.setBackground(new java.awt.Color(255, 204, 204));
        jPanelOpcions.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 0), null), "Outras opcións", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanelOpcions.setForeground(new java.awt.Color(255, 204, 204));

        jButtonListaxe.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jButtonListaxe.setForeground(new java.awt.Color(204, 0, 0));
        jButtonListaxe.setText("Listaxe completa");
        jButtonListaxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListaxeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelOpcionsLayout = new javax.swing.GroupLayout(jPanelOpcions);
        jPanelOpcions.setLayout(jPanelOpcionsLayout);
        jPanelOpcionsLayout.setHorizontalGroup(
            jPanelOpcionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOpcionsLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jButtonListaxe)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelOpcionsLayout.setVerticalGroup(
            jPanelOpcionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOpcionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonListaxe)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonSair.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jButtonSair.setForeground(new java.awt.Color(204, 0, 0));
        jButtonSair.setText("Saír");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFondoLayout = new javax.swing.GroupLayout(jPanelFondo);
        jPanelFondo.setLayout(jPanelFondoLayout);
        jPanelFondoLayout.setHorizontalGroup(
            jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFondoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonSair, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelOpcions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelFondoLayout.setVerticalGroup(
            jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFondoLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanelNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelOpcions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSair)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloActionPerformed

    private void jComboBoxPrezoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPrezoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxPrezoActionPerformed

    private void jComboBoxXeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxXeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxXeneroActionPerformed

    private void jButtonProcurarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProcurarTActionPerformed
        libros.clear();
        procurarLibros(0, jTextFieldTitulo.getText(), 0);
        JDialogLibros dialogoLibros = new JDialogLibros(this, true);
        dialogoLibros.setLibros(this.libros);
        dialogoLibros.setVisible(true);
    }//GEN-LAST:event_jButtonProcurarTActionPerformed

    private void jButtonProcurarXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProcurarXActionPerformed
        libros.clear();
        procurarLibros(1, (String) jComboBoxXenero.getSelectedItem(), 0);
        JDialogLibros dialogoLibros = new JDialogLibros(this, true);
        dialogoLibros.setLibros(this.libros);
        dialogoLibros.setVisible(true);
    }//GEN-LAST:event_jButtonProcurarXActionPerformed

    private void jButtonProcurarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProcurarPActionPerformed
        libros.clear();
        procurarLibros(2, String.valueOf(jComboBoxPrezo.getSelectedIndex()), Float.valueOf(jTextFieldPrezo.getText()));
        JDialogLibros dialogoLibros = new JDialogLibros(this, true);
        dialogoLibros.setLibros(this.libros);
        dialogoLibros.setVisible(true);
    }//GEN-LAST:event_jButtonProcurarPActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButtonListaxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListaxeActionPerformed
        libros.clear();
        procurarLibros(3, null, 0);
        JDialogLibros dialogoLibros = new JDialogLibros(this, true);
        dialogoLibros.setLibros(this.libros);
        dialogoLibros.setVisible(true);
    }//GEN-LAST:event_jButtonListaxeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameLibreria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameLibreria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameLibreria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameLibreria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameLibreria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupPuntuacion;
    private javax.swing.JButton jButtonListaxe;
    private javax.swing.JButton jButtonProcurarP;
    private javax.swing.JButton jButtonProcurarT;
    private javax.swing.JButton jButtonProcurarX;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JComboBox<String> jComboBoxPrezo;
    private javax.swing.JComboBox<String> jComboBoxXenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelDatos;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JPanel jPanelNome;
    private javax.swing.JPanel jPanelOpcions;
    private javax.swing.JTextField jTextFieldPrezo;
    private javax.swing.JTextField jTextFieldTitulo;
    // End of variables declaration//GEN-END:variables
}
