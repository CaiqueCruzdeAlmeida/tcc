/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visaotcc;

import Login.DadosDeSessao;
import controlador.GastosDao;
import controlador.PagamentoDao;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modelo.Gastos;
import modelo.Pagamentos;

/**
 *
 * @author Caique
 */
public class AlterarGastos extends javax.swing.JFrame {

    AddCompraProduto Compraform;

    /**
     * Creates new form AlterarGastos
     */
    public AlterarGastos() {
        initComponents();
        this.itemlistener();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/CC.png")).getImage());
        this.setTitle(DadosDeSessao.nomeSistema);
        this.HabilitarCadastrar();
    }
    
    public void HabilitarCadastrar() {
    jButton1.setEnabled(false); // Começa desativado

    // Crie um DocumentListener para monitorar os campos de texto
    DocumentListener documentListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            checkFields();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            checkFields();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            checkFields();
        }
    };

    // Adicione o DocumentListener aos campos relevantes
    jTextFieldProduto.getDocument().addDocumentListener(documentListener);
    jTextFieldValor.getDocument().addDocumentListener(documentListener);
    jFormattedTextFieldDataDeCompra.getDocument().addDocumentListener(documentListener);
    jTextFieldQuantidade.getDocument().addDocumentListener(documentListener);
//    jTextFieldAnoReferencia.getDocument().addDocumentListener(documentListener);

    // Adicione um ActionListener para monitorar as ComboBoxes
    ActionListener comboBoxListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            checkFields(); // Quando as ComboBoxes forem alteradas, chame checkFields() para atualizar a habilitação do botão
        }
    };
    jComboBoxFormaPagamento.addActionListener(comboBoxListener);
    //jComboBoxProdutoCadastrado.addActionListener(comboBoxListener);
    jComboBoxParcelas.addActionListener(comboBoxListener);
//    jComboBoxMês.addActionListener(comboBoxListener);
    // Verifique os campos uma vez para garantir que o botão esteja corretamente habilitado ou desabilitado inicialmente
    checkFields();
}
   public void checkFields() {
    String nomeproduto = jTextFieldProduto.getText().trim();
   // String produtocadastrado = (String) jComboBoxProdutoCadastrado.getSelectedItem();
    String formapagamento = (String) jComboBoxFormaPagamento.getSelectedItem();
    String Parcelas = (String) jComboBoxParcelas.getSelectedItem();
    String valor = jTextFieldValor.getText().trim();
    String datacompra = jFormattedTextFieldDataDeCompra.getText().trim();
    String quantidade = jTextFieldQuantidade.getText();
//    String anoreferencia = jTextFieldAnoReferencia.getText();
//    String mesreferencia = (String)jComboBoxMês.getSelectedItem();

    
        
        // Verifique se pelo menos um dos campos obrigatórios está preenchido
        if (nomeproduto.isEmpty()
                || "  -  -    ".equals(datacompra)
                || valor.isEmpty()
                || quantidade.isEmpty()
//                || anoreferencia.isEmpty()
                || "-- Selecione --".equals(formapagamento)
//                || "-- Selecione --".equals(mesreferencia)
                || "-- Selecione --".equals(Parcelas)) {
            jButton1.setEnabled(false);
        } else {
            jButton1.setEnabled(true);
        }
    

    try {
        // Faça verificações adicionais se necessário

    } catch (NumberFormatException e) {
        // Em caso de erro na conversão, desative o botão
        jButton1.setEnabled(false);
    }
} 
    
    
    private void itemlistener(){
     // Adicione um ItemListener ao jComboBoxFormaPagamento
        jComboBoxFormaPagamento.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Atualize as opções da jComboBoxParcelas com base na forma de pagamento selecionada
                atualizarParcelas();
            }
        });
}
// Método para atualizar as opções da jComboBoxParcelas com base na forma de pagamento selecionada
    private void atualizarParcelas() {
        String formaPagamento = (String) jComboBoxFormaPagamento.getSelectedItem();

        // Limpe as opções existentes na jComboBoxParcelas
        jComboBoxParcelas.removeAllItems();

        // Adicione as opções relevantes com base na forma de pagamento selecionada
        if ("Crédito".equals(formaPagamento)) {
            for (int i = 1; i <= 12; i++) {
                jComboBoxParcelas.addItem(String.valueOf(i) + "x");
            }
        } else {
            // Se for Débito, Pix ou Dinheiro, adicione apenas a opção 1x
            jComboBoxParcelas.addItem("1x");
        }
    }
public void mostrarGastos(String idTexto){
    try {
        Integer id = Integer.parseInt(idTexto);
        
        GastosDao dao = new GastosDao();
        Gastos obj = dao.getGastos(id);
        
        if (obj != null) {
            // Formatar a data para exibição no formato desejado
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            
            // Preenche os dados do formulário
            jTextFieldID.setText(obj.getId().toString());
            jTextFieldValor.setText(obj.getValorpago() != null ? obj.getValorpago().toString() : "");
//            jTextFieldAnoReferencia.setText(obj.getAnoreferencia() != null ? obj.getAnoreferencia().toString() : "");
            jTextFieldProduto.setText(obj.getNomeproduto());
            jTextFieldQuantidade.setText(obj.getQuantidade() != null ? obj.getQuantidade().toString() : "");
//            jComboBoxMês.setSelectedItem(obj.getMesreferencia());
            jComboBoxFormaPagamento.setSelectedItem(obj.getFormapagamento());
            jComboBoxParcelas.setSelectedItem(obj.getParcelas());
            
            // Tratamento para a data de compra
            if (obj.getDatacompra() != null) {
                String dataPagamentoFormatada = dateFormat.format(obj.getDatacompra());
                jFormattedTextFieldDataDeCompra.setText(dataPagamentoFormatada);
            } else {
                jFormattedTextFieldDataDeCompra.setText("");
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Registro não encontrado");
            jTextFieldID.setText("");
            jTextFieldValor.setText("");
            jTextFieldProduto.setText("");
            jFormattedTextFieldDataDeCompra.setText("    -  -  ");
//            jTextFieldAnoReferencia.setText("");
            jTextFieldQuantidade.setText("");
//            jComboBoxMês.setToolTipText("-- Selecione --");
            jComboBoxFormaPagamento.setToolTipText("-- Selecione --");
            jComboBoxParcelas.setToolTipText("-- Selecione --");
        }
        
    } catch(Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao consultar registro");
        jTextFieldID.setText("");
        jTextFieldValor.setText("");
        jTextFieldProduto.setText("");
        jFormattedTextFieldDataDeCompra.setText("    -  -  ");
//        jTextFieldAnoReferencia.setText("");
        jTextFieldQuantidade.setText("");
//        jComboBoxMês.setToolTipText("-- Selecione --");
        jComboBoxFormaPagamento.setToolTipText("-- Selecione --");
        jComboBoxParcelas.setToolTipText("-- Selecione --");
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

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldProduto = new javax.swing.JTextField();
        jTextFieldQuantidade = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldValor = new javax.swing.JTextField();
        jFormattedTextFieldDataDeCompra = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxFormaPagamento = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxParcelas = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jTextFieldID = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("ALTERAR DADOS DO GASTO CADASTRADO");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Produto:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Quantidade:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Valor:");

        try {
            jFormattedTextFieldDataDeCompra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Data de Compra:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Forma de Pagamento:");

        jComboBoxFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Selecione --", "Crédito", "Débito", "Pix", "Dinheiro" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Parcelas:");

        jComboBoxParcelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Selecione --", "1x", "2x", "3x", "4x", "5x", "6x", "7x", "8x", "9x", "10x", "11x", "12x" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFieldDataDeCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jFormattedTextFieldDataDeCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jComboBoxParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados", jPanel1);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterar.png"))); // NOI18N
        jButton1.setText("Alterar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextFieldID.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       try {
        Double valorpago = Double.valueOf(jTextFieldValor.getText().replaceAll(",", "."));
        String datapagamentoText = jFormattedTextFieldDataDeCompra.getText();
        String nomeproduto = jTextFieldProduto.getText();
        String datacompra = jFormattedTextFieldDataDeCompra.getText();
        String formapagamento = (String)jComboBoxFormaPagamento.getSelectedItem();
        String parcelas = (String)jComboBoxParcelas.getSelectedItem();
        Integer quantidade = Integer.parseInt(jTextFieldQuantidade.getText());
//        Integer anoreferencia = Integer.parseInt(jTextFieldAnoReferencia.getText());
//        String mesreferencia = (String)jComboBoxMês.getSelectedItem();     
        Integer id = Integer.parseInt(jTextFieldID.getText());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dataCompra = null;
        if (!datacompra.isEmpty()) {
            dataCompra = dateFormat.parse(datacompra);
        }
         try {
        
        // Agora você tem a data no formato java.util.Date (dataPagamento)

        
        
        String[] partesData = datacompra.split("-"); // Agora dividindo a data pelo separador "-"

        int dia = Integer.parseInt(partesData[0]);
        int mes = Integer.parseInt(partesData[1]);
        int ano= Integer.parseInt(partesData[2]);

        boolean isBissexto = (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0));

        // Validar dias para meses que têm apenas 30 ou 31 dias
        if ((dia == 31 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) ||
            (dia > 29 && mes == 2) || // Fev. não pode ter mais que 29 dias
            (dia == 29 && mes == 2 && !isBissexto)) {
            JOptionPane.showMessageDialog(this, "Data inválida para o mês selecionado.");
            jFormattedTextFieldDataDeCompra.setForeground(Color.RED);
            return;
        }
        
        Gastos u = new Gastos(nomeproduto, valorpago, dataCompra, formapagamento, parcelas, quantidade, /*anoreferencia, mesreferencia,*/ id, null);
        
        GastosDao dao = new GastosDao();
        dao.atualizar(u);
        Compraform.callback();

        JOptionPane.showMessageDialog(this, "Registro atualizado.");
        dispose();
    } catch (ParseException | NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
    }catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Erro" + e.getMessage());
        }
        } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
    }

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AlterarGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlterarGastos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxFormaPagamento;
    private javax.swing.JComboBox<String> jComboBoxParcelas;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataDeCompra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldProduto;
    private javax.swing.JTextField jTextFieldQuantidade;
    private javax.swing.JTextField jTextFieldValor;
    // End of variables declaration//GEN-END:variables
}
