/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visaotcc;

import Login.DadosDeSessao;
import Login.telaLogin;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controlador.UsuarioDao;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;

/**
 *
 * @author Caique
 */
public class Gerenciarusuarios extends javax.swing.JFrame {
    public Gerenciarusuarios UsuariosForm;
    public GerenciarAluno AlunoForm;
    public Principal listarForm;
    public GerenciarResponsaveis ResponsaveisForm;
    
    public TurmasPorAluno TurmasAlunoForm;
    public Pagamento PagamentoForm;
    /**
     * Creates new form Gerenciarusuarios
     */
    public Gerenciarusuarios() {
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/CC.png")).getImage());
        jTableUsuariosListar.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableUsuariosListar.getColumnModel().getColumn(1).setPreferredWidth(129);
        jTableUsuariosListar.getColumnModel().getColumn(2).setPreferredWidth(129);
        jTableUsuariosListar.getColumnModel().getColumn(3).setPreferredWidth(129);
        jTableUsuariosListar.getColumnModel().getColumn(4).setPreferredWidth(129);
        
         this.setTitle(DadosDeSessao.nomeSistema /*+ " - " + DadosDeSessao.getUsuario().getNome()*/);
         //jLabel5.setText(DadosDeSessao.getUsuario().getNome());
        
        this.callback();
        
         this.preencherTabela3("");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableUsuariosListar = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldPesquisa3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButtonExcluir2 = new javax.swing.JButton();
        jButtonPDF = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTableUsuariosListar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Email", "RG", "CPF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableUsuariosListar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableUsuariosListarMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableUsuariosListar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Usuários", jPanel1);

        jLabel4.setText("Pesquisar por Nome:");

        jTextFieldPesquisa3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisa3KeyReleased(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar-pequeno.png"))); // NOI18N

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        jButton8.setText("Novo Usuário");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterar.png"))); // NOI18N
        jButton5.setText("Alterar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButtonExcluir2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir.png"))); // NOI18N
        jButtonExcluir2.setText("Excluir");
        jButtonExcluir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluir2ActionPerformed(evt);
            }
        });

        jButtonPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/download-pequeno.jpg"))); // NOI18N
        jButtonPDF.setText("PDF");
        jButtonPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPDFActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Gerenciar Usuários");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonExcluir2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPesquisa3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jTextFieldPesquisa3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonExcluir2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
private void preencherTabela3(String campoPesquisa3){
        
        DefaultTableModel modelo = (DefaultTableModel) jTableUsuariosListar.getModel();
        modelo.setNumRows(0);
        try{
            UsuarioDao dao = new UsuarioDao();
            List<Usuario> lista = dao.buscar(campoPesquisa3);
            
            for(Usuario obj : lista){
                String[] linha = {
                obj.getId().toString(),
                obj.getNome().toString(),
                obj.getEmail().toString(),
                obj.getRg().toString(),
                obj.getCpf().toString(),
                
                ""
                };
                modelo.addRow(linha);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar. " + ex.getMessage());
        }
    }
private void alterarUsuarios(){
        int linhaSelecionada = jTableUsuariosListar.getSelectedRow();

        if (linhaSelecionada != -1) {
            Integer id = Integer.parseInt(
                    jTableUsuariosListar.getModel().getValueAt(linhaSelecionada, 0).toString());

            AlterarUsuario form = new AlterarUsuario();
            form.setVisible(true);
            form.UsuariosForm = this;
            form.mostrarUsuario(id.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro.");
        }
    }
    private void jTableUsuariosListarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUsuariosListarMouseClicked

        if (evt.getClickCount() == 2) {
            System.out.println("Duplo clique detectado.");
            this.alterarUsuarios();
        }
    }//GEN-LAST:event_jTableUsuariosListarMouseClicked

    private void jTextFieldPesquisa3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisa3KeyReleased
        String campoPesquisa3 = jTextFieldPesquisa3.getText();

        this.preencherTabela3(campoPesquisa3);
    }//GEN-LAST:event_jTextFieldPesquisa3KeyReleased

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        CadUsuario uc = new CadUsuario();
        uc.setVisible(true);
        uc.UsuariosForm = this;
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.alterarUsuarios();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButtonExcluir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluir2ActionPerformed

        int opcao = JOptionPane.showConfirmDialog(this, "Confirma exclusão?", "Excluir", JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            int linhaSelecionada = jTableUsuariosListar.getSelectedRow();

            if (linhaSelecionada != -1) {
                Integer id = Integer.parseInt(
                    jTableUsuariosListar.getModel().getValueAt(linhaSelecionada, 0).toString());

                try {
                    //Exclui do BD
                    UsuarioDao dao = new UsuarioDao();
                    dao.excluir(id);

                    //Remove linha da tabela
                    DefaultTableModel modelo = (DefaultTableModel) jTableUsuariosListar.getModel();
                    modelo.removeRow(linhaSelecionada);

                    JOptionPane.showMessageDialog(this, "Registro excluído com sucesso.");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erro ao excluir registro." + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um registro.");
            }
        }
    }//GEN-LAST:event_jButtonExcluir2ActionPerformed

    private void jButtonPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPDFActionPerformed
         exportToPDF();
        //JOptionPane.showMessageDialog(null, "PDF created successfully!");
    }//GEN-LAST:event_jButtonPDFActionPerformed
    private void exportToPDF() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Specify a file to save");

    int userSelection = fileChooser.showSaveDialog(this);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();

        // Verifica se o nome do arquivo contém a extensão .pdf, adiciona se não estiver presente
        String filePath = fileToSave.getAbsolutePath();
        if (!filePath.toLowerCase().endsWith(".pdf")) {
            fileToSave = new File(filePath + ".pdf");
        }

        criarPDFComCabecalho(fileToSave);
        JOptionPane.showMessageDialog(null, "PDF created successfully!");
    }
}
    private void criarPDFComCabecalho(File arquivoParaSalvar) {
        String textoCabecalho = "Relatório de Alunos";

        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(arquivoParaSalvar));
            documento.open();

            PdfPTable tabelaPDF = new PdfPTable(jTableUsuariosListar.getColumnCount());

            PdfPCell celulaCabecalho = new PdfPCell(new Phrase(textoCabecalho));
            celulaCabecalho.setColspan(jTableUsuariosListar.getColumnCount());
            celulaCabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabelaPDF.addCell(celulaCabecalho);

            // Adiciona cabeçalhos à tabela
            for (int i = 0; i < jTableUsuariosListar.getColumnCount(); i++) {
                tabelaPDF.addCell(jTableUsuariosListar.getColumnName(i));
            }

            // Adiciona dados da tabela ao PDF
            for (int rows = 0; rows < jTableUsuariosListar.getRowCount(); rows++) {
                for (int cols = 0; cols < jTableUsuariosListar.getColumnCount(); cols++) {
                    tabelaPDF.addCell(jTableUsuariosListar.getModel().getValueAt(rows, cols).toString());
                }
            }

            documento.add(tabelaPDF);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error creating PDF: " + e.getMessage());
        } finally {
            if (documento != null && documento.isOpen()) {
                documento.close();
            }
        }
}
    
    public void callback() {
        this.carregarTabelaUsuario();
    }
    private void carregarTabelaUsuario() {
        String campoPesquisa3 = jTextFieldPesquisa3.getText();
        this.preencherTabela3(campoPesquisa3);
    }
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
            java.util.logging.Logger.getLogger(Gerenciarusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gerenciarusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gerenciarusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gerenciarusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gerenciarusuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonExcluir2;
    private javax.swing.JButton jButtonPDF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableUsuariosListar;
    private javax.swing.JTextField jTextFieldPesquisa3;
    // End of variables declaration//GEN-END:variables
}
