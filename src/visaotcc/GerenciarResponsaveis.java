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
import controlador.AlunoDao;
import controlador.ResponsaveisDao;
import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Aluno;
import modelo.Responsavel;

/**
 *
 * @author Caique
 */
public class GerenciarResponsaveis extends javax.swing.JFrame {
    public GerenciarResponsaveis ResponsaveisForm;
    public Principal listarForm;
    public Gerenciarusuarios UsuariosForm;
    public GerenciarAluno AlunoForm;
    public TurmasPorAluno TurmasAlunoForm;
    public Pagamento PagamentoForm;
    /**
     * Creates new form GerenciarResponsaveis
     */
    public GerenciarResponsaveis() {
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/CC.png")).getImage());
        this.tamanhotabela1();
        this.tamanhotabela2();
        this.setTitle(DadosDeSessao.nomeSistema /*+ " - " + DadosDeSessao.getUsuario().getNome()*/);
        
        this.preencherTabela2("");
        this.callback();
    }
private void tamanhotabela1(){
        jTableResponsaveisListar.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableResponsaveisListar.getColumnModel().getColumn(1).setPreferredWidth(129);
        jTableResponsaveisListar.getColumnModel().getColumn(2).setPreferredWidth(129);
        jTableResponsaveisListar.getColumnModel().getColumn(3).setPreferredWidth(129);
        jTableResponsaveisListar.getColumnModel().getColumn(4).setPreferredWidth(129);
}

private void tamanhotabela2(){
     jTableAlunos.getColumnModel().getColumn(0).setPreferredWidth(25);
     jTableAlunos.getColumnModel().getColumn(1).setPreferredWidth(98);
     
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableResponsaveisListar = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldPesquisa2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButtonExcluir1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAlunos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButtonPDF = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTableResponsaveisListar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "RG", "CPF", "TELEFONE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableResponsaveisListar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableResponsaveisListarMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableResponsaveisListar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Responsáveis", jPanel1);

        jLabel3.setText("Pesquisar por Nome:");

        jTextFieldPesquisa2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisa2KeyReleased(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar-pequeno.png"))); // NOI18N

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        jButton7.setText("Novo Responsável");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterar.png"))); // NOI18N
        jButton4.setText("Alterar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButtonExcluir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir.png"))); // NOI18N
        jButtonExcluir1.setText("Excluir");
        jButtonExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluir1ActionPerformed(evt);
            }
        });

        jTableAlunos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Idade", "Matricula"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableAlunos);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Alunos Por Responsável");

        jButtonPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/download-pequeno.jpg"))); // NOI18N
        jButtonPDF.setText("PDF");
        jButtonPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPDFActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Gerenciar Responsáveis");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonExcluir1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButtonPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPesquisa2, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextFieldPesquisa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldPesquisa2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisa2KeyReleased
        String campoPesquisa2 = jTextFieldPesquisa2.getText();
        this.preencherTabela2(campoPesquisa2);
    }//GEN-LAST:event_jTextFieldPesquisa2KeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        CadResponsavel uc = new CadResponsavel();
        uc.setVisible(true);
        uc.ResponsaveisForm = this;
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.alterarResponsaveis();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButtonExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluir1ActionPerformed
        int opcao = JOptionPane.showConfirmDialog(this, "Confirma exclusão?", "Excluir", JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            int linhaSelecionada = jTableResponsaveisListar.getSelectedRow();

            if (linhaSelecionada != -1) {
                Integer id = Integer.parseInt(
                    jTableResponsaveisListar.getModel().getValueAt(linhaSelecionada, 0).toString());

                try {
                    //Exclui do BD
                    ResponsaveisDao dao = new ResponsaveisDao();
                    dao.excluir(id);

                    //Remove linha da tabela
                    DefaultTableModel modelo = (DefaultTableModel) jTableResponsaveisListar.getModel();
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
    }//GEN-LAST:event_jButtonExcluir1ActionPerformed
public class IdadeRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Verifica se a coluna é a coluna de idade (substitua pelo índice correto)
        if (column == 2) {
            // Formata a idade para exibir "X anos"
            if (value != null) {
                setText(value + " ano(s)");
            }
        }

        return rendererComponent;
    }
}
    private void jTableResponsaveisListarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableResponsaveisListarMouseClicked

        if (evt.getClickCount() == 2) {
            System.out.println("Duplo clique detectado.");
            this.alterarResponsaveis();
        }
        
        if (evt.getClickCount() == 1) {
        int selectedRow = jTableResponsaveisListar.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel modeloTabela1 = (DefaultTableModel) jTableResponsaveisListar.getModel();
            int indiceID = encontrarIndiceColuna(modeloTabela1, "ID");

            if (indiceID != -1) {
                Integer responsavelId = Integer.parseInt(jTableResponsaveisListar.getValueAt(selectedRow, indiceID).toString());

                try {
                    AlunoDao alunoDao = new AlunoDao();
                    List<Aluno> alunosDoResponsavel = alunoDao.buscarAlunosPorResponsavel(responsavelId);

                    DefaultTableModel modeloAlunosResponsavel = (DefaultTableModel) jTableAlunos.getModel();
                    modeloAlunosResponsavel.setRowCount(0); // Limpa a tabela antes de preencher com novos dados

                    for (Aluno aluno : alunosDoResponsavel) {
                        modeloAlunosResponsavel.addRow(new Object[]{
                            aluno.getId(),
                            aluno.getNome(),
                            aluno.getIdade(),
                            aluno.getMatricula(),
                            // Outros atributos do aluno, se necessário
                        });
                    }
                    // Aplica o renderizador de idade à coluna de idade (substitua pelo índice correto)
                    int indiceColunaIdade = encontrarIndiceColuna(modeloAlunosResponsavel, "Idade");
                    if (indiceColunaIdade != -1) {
                        jTableAlunos.getColumnModel().getColumn(indiceColunaIdade).setCellRenderer(new IdadeRenderer());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erro ao buscar alunos do responsável: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Coluna 'ID' não encontrada na tabela.");
            }
        }
    
}       
        
        
        
        
    }//GEN-LAST:event_jTableResponsaveisListarMouseClicked
private int encontrarIndiceColuna(DefaultTableModel modelo, String Nome) {
    for (int i = 0; i < modelo.getColumnCount(); i++) {
        if (modelo.getColumnName(i).equals(Nome)) {
            return i; // Retorna o índice da coluna quando encontrar o nome desejado
        }
    }
    return -1; // Retorna -1 se o nome da coluna não for encontrado
}
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
        String textoCabecalho = "Relatório de Responsáveis";

        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(arquivoParaSalvar));
            documento.open();

            PdfPTable tabelaPDF = new PdfPTable(jTableResponsaveisListar.getColumnCount());

            PdfPCell celulaCabecalho = new PdfPCell(new Phrase(textoCabecalho));
            celulaCabecalho.setColspan(jTableResponsaveisListar.getColumnCount());
            celulaCabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabelaPDF.addCell(celulaCabecalho);

            // Adiciona cabeçalhos à tabela
            for (int i = 0; i < jTableResponsaveisListar.getColumnCount(); i++) {
                tabelaPDF.addCell(jTableResponsaveisListar.getColumnName(i));
            }

            // Adiciona dados da tabela ao PDF
            for (int rows = 0; rows < jTableResponsaveisListar.getRowCount(); rows++) {
                for (int cols = 0; cols < jTableResponsaveisListar.getColumnCount(); cols++) {
                    tabelaPDF.addCell(jTableResponsaveisListar.getModel().getValueAt(rows, cols).toString());
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
    
    private void preencherTabela2(String campoPesquisa2){
        
        DefaultTableModel modelo = (DefaultTableModel) jTableResponsaveisListar.getModel();
        modelo.setNumRows(0);
        try{
            ResponsaveisDao dao = new ResponsaveisDao();
            List<Responsavel> lista = dao.buscar(campoPesquisa2);
            
            for(Responsavel obj : lista){
                String[] linha = {
                obj.getId().toString(),
                obj.getNome().toString(),
                obj.getRg().toString(),
                obj.getCpf().toString(),
                obj.getTelefone().toString(),
                ""
                };
                modelo.addRow(linha);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar. " + ex.getMessage());
        }
    }
    private void alterarResponsaveis(){
        int linhaSelecionada = jTableResponsaveisListar.getSelectedRow();

        if (linhaSelecionada != -1) {
            Integer id = Integer.parseInt(
                    jTableResponsaveisListar.getModel().getValueAt(linhaSelecionada, 0).toString());

            AlterarResponsavel form = new AlterarResponsavel();
            form.setVisible(true);
            form.ResponsaveisForm = this;
            form.mostrarResponsavel(id.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro.");
        }
    }


    public void callback() {
                this.carregarTabelaResponsavel();
            }
    private void carregarTabelaResponsavel() {
        String campoPesquisa2 = jTextFieldPesquisa2.getText();
        this.preencherTabela2(campoPesquisa2);
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
            java.util.logging.Logger.getLogger(GerenciarResponsaveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciarResponsaveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciarResponsaveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciarResponsaveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciarResponsaveis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonExcluir1;
    private javax.swing.JButton jButtonPDF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAlunos;
    private javax.swing.JTable jTableResponsaveisListar;
    private javax.swing.JTextField jTextFieldPesquisa2;
    // End of variables declaration//GEN-END:variables
}
