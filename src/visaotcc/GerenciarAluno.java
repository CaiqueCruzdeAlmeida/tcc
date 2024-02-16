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
import controlador.AlunosPorTurmaDao;
import controlador.Conexao;
import controlador.ResponsaveisDao;
import controlador.TurmaDao;
import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Aluno;
import modelo.AlunosPorTurma;
import modelo.Responsavel;
import modelo.Turma;

/**
 *
 * @author Caique
 */
public class GerenciarAluno extends javax.swing.JFrame {
    public GerenciarAluno AlunoForm;
    public Principal listarForm;
    public GerenciarResponsaveis ResponsaveisForm;
    public Gerenciarusuarios UsuariosForm;
    public Pagamento PagamentoForm;
    /**
     * Creates new form GerenciarAluno
     */
    public GerenciarAluno() {
        initComponents();
        this.tamanhotabela();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/CC.png")).getImage());
        this.setTitle(DadosDeSessao.nomeSistema );
        
        
         this.preencherTabela("");
         this.callback();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableAlunoListar = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldPesquisa1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTurmas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButtonPDF = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTableAlunoListar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Matrícula", "Responsável", "Idade", "Obs"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAlunoListar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAlunoListarMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableAlunoListar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Alunos", jPanel1);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Pesquisar por Nome:");

        jTextFieldPesquisa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPesquisa1ActionPerformed(evt);
            }
        });
        jTextFieldPesquisa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisa1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisa1KeyReleased(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar-pequeno.png"))); // NOI18N

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        jButton6.setText("Novo Aluno");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterar.png"))); // NOI18N
        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir.png"))); // NOI18N
        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        jButton1.setText("Turma");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTableTurmas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Data início", "Data Fechamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableTurmas);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Turmas do Aluno");

        jButtonPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/download-pequeno.jpg"))); // NOI18N
        jButtonPDF.setText("PDF");
        jButtonPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPDFActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Gerenciar Aluno");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldPesquisa1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTabbedPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(127, 127, 127)
                                        .addComponent(jLabel1)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldPesquisa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPDF)
                        .addGap(7, 7, 7)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
private void tamanhotabela(){
        jTableAlunoListar.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableAlunoListar.getColumnModel().getColumn(1).setPreferredWidth(129);
        jTableAlunoListar.getColumnModel().getColumn(2).setPreferredWidth(129);
        jTableAlunoListar.getColumnModel().getColumn(3).setPreferredWidth(129);
        
        jTableTurmas.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableTurmas.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableTurmas.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTableTurmas.getColumnModel().getColumn(3).setPreferredWidth(100);
}

public class IdadeRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Verifica se a coluna é a coluna de idade (substitua pelo índice correto)
        if (column == 4) {
            // Formata a idade para exibir "X anos"
            if (value != null) {
                setText(value + " ano(s)");
            }
        }

        return rendererComponent;
    }
}
    private void jTableAlunoListarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAlunoListarMouseClicked
        if (evt.getClickCount() == 2) {
          //  System.out.println("Duplo clique detectado.");
            this.alterarAluno();
        }
        if (evt.getClickCount() == 1) {
        int selectedRow = jTableAlunoListar.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel modeloTabela1 = (DefaultTableModel) jTableAlunoListar.getModel();
            int indiceNomeTurma = encontrarIndiceColuna(modeloTabela1, "Nome");
            int indiceID = encontrarIndiceColuna(modeloTabela1, "ID");

            if (indiceID != -1) {
                //String nomeTurma = jTableAlunoListar.getValueAt(selectedRow, indiceNomeTurma).toString();
                Integer alunoId = Integer.parseInt(jTableAlunoListar.getValueAt(selectedRow, indiceID).toString());


                try {
                    AlunoDao alunoDao = new AlunoDao();
                    List<AlunosPorTurma> turmasDoAluno = alunoDao.buscarTurmasPorAluno(alunoId);
 

                    DefaultTableModel modeloAlunosTurma = (DefaultTableModel) jTableTurmas.getModel();
                    modeloAlunosTurma.setRowCount(0); // Limpa a tabela antes de preencher com novos dados
                    
                    SimpleDateFormat dateFormatOriginal = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat dateFormatDestino = new SimpleDateFormat("dd/MM/yyyy");

                    for (AlunosPorTurma alunoPorTurma : turmasDoAluno) {
                    modeloAlunosTurma.addRow(new Object[]{
                        alunoPorTurma.getId(),
                        alunoPorTurma.getNometurma(),
                        dateFormatDestino.format(alunoPorTurma.getDatainicio()),
                        dateFormatDestino.format(alunoPorTurma.getDatafechamento()),
                        // Outros atributos da turma, se necessário
                    });
                }
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erro ao buscar turmas do aluno: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Coluna 'Nome' não encontrada na tabela.");
            }
        }
}       
        
    }//GEN-LAST:event_jTableAlunoListarMouseClicked
private int encontrarIndiceColuna(DefaultTableModel modelo, String Nome) {
    for (int i = 0; i < modelo.getColumnCount(); i++) {
        if (modelo.getColumnName(i).equals(Nome)) {
            return i; // Retorna o índice da coluna quando encontrar o nome desejado
        }
    }
    return -1; // Retorna -1 se o nome da coluna não for encontrado
}
    private void jTextFieldPesquisa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPesquisa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPesquisa1ActionPerformed

    private void jTextFieldPesquisa1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisa1KeyReleased
        String campoPesquisa = jTextFieldPesquisa1.getText();

        this.preencherTabela(campoPesquisa);
    }//GEN-LAST:event_jTextFieldPesquisa1KeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        CadAluno uc = new CadAluno();
        uc.setVisible(true);
        uc.AlunoForm = this;
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        this.alterarAluno();
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed

        int opcao = JOptionPane.showConfirmDialog(this, "Confirma exclusão?", "Excluir", JOptionPane.YES_NO_OPTION);

            if (opcao == JOptionPane.YES_OPTION) {
                int linhaSelecionada = jTableAlunoListar.getSelectedRow();

                if (linhaSelecionada != -1) {
                    Integer idAluno = Integer.parseInt(jTableAlunoListar.getModel().getValueAt(linhaSelecionada, 0).toString());

                    try {
                        // Tenta obter o ID da turma do aluno
                        Integer idTurma = obterIdTurmaDoAluno(idAluno); // Método para obter o ID da turma do aluno
                        // Exclui o aluno e os registros correspondentes na tabela turmaporaluno
                        AlunoDao alunoDao = new AlunoDao();
                        alunoDao.excluirComDependencias(idAluno); // Método para excluir o aluno e suas dependências
                        listarForm.callback();

                        // Remove linha da tabela
                        DefaultTableModel modelo = (DefaultTableModel) jTableAlunoListar.getModel();
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
    }//GEN-LAST:event_jButtonExcluirActionPerformed
    public Integer obterIdTurmaDoAluno(Integer idAluno) throws Exception {
                Integer idTurma = null;
                String sql = "SELECT idturma FROM turmaporaluno WHERE id = ?";
                Connection conexao = null;
                PreparedStatement ps = null;
                ResultSet rs = null;

                try {
                    conexao = Conexao.getConexao();
                    ps = conexao.prepareStatement(sql);
                    ps.setInt(1, idAluno);

                    rs = ps.executeQuery();

                    if (rs.next()) {
                        idTurma = rs.getInt("idturma");
                    }
                } finally {
                    // Feche os recursos na ordem reversa da abertura para evitar exceções
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (conexao != null) {
                        conexao.close();
                    }
                }

                return idTurma;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Relacionamento_Turma_Aluno uc = new Relacionamento_Turma_Aluno();
        uc.setVisible(true);
        uc.AlunoForm = this;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPDFActionPerformed
      exportToPDF();
         //JOptionPane.showMessageDialog(null, "PDF created successfully!");
        
    }//GEN-LAST:event_jButtonPDFActionPerformed

    private void jTextFieldPesquisa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisa1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPesquisa1KeyPressed
    
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

            criarPDFComCabecalho(fileToSave); // Chama o método para criar o PDF com cabeçalho
            JOptionPane.showMessageDialog(null, "PDF created successfully!");
        }
}

    private void criarPDFComCabecalho(File arquivoParaSalvar) {
        String textoCabecalho = "Relatório de Alunos";

        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(arquivoParaSalvar));
            documento.open();

            PdfPTable tabelaPDF = new PdfPTable(jTableAlunoListar.getColumnCount());

            PdfPCell celulaCabecalho = new PdfPCell(new Phrase(textoCabecalho));
            celulaCabecalho.setColspan(jTableAlunoListar.getColumnCount());
            celulaCabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabelaPDF.addCell(celulaCabecalho);

            // Adiciona cabeçalhos à tabela
            for (int i = 0; i < jTableAlunoListar.getColumnCount(); i++) {
                tabelaPDF.addCell(jTableAlunoListar.getColumnName(i));
            }

            // Adiciona dados da tabela ao PDF
            for (int rows = 0; rows < jTableAlunoListar.getRowCount(); rows++) {
                for (int cols = 0; cols < jTableAlunoListar.getColumnCount(); cols++) {
                    tabelaPDF.addCell(jTableAlunoListar.getModel().getValueAt(rows, cols).toString());
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

    
    
    private void alterarAluno(){
        
        int linhaSelecionada = jTableAlunoListar.getSelectedRow();

        if (linhaSelecionada != -1) {
            Integer id = Integer.valueOf(
                    jTableAlunoListar.getModel().getValueAt(linhaSelecionada, 0).toString());

            AlunoAlterar form = new AlunoAlterar();
            form.setVisible(true);
            form.AlunoForm = this;
            form.mostrarAluno(id.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro.");
        }
        
    }


    
    private void preencherTabela(String campoPesquisa){
        
        DefaultTableModel modelo = (DefaultTableModel) jTableAlunoListar.getModel();
        modelo.setNumRows(0);
        try{
            AlunoDao dao = new AlunoDao();
            List<Aluno> lista = dao.buscar(campoPesquisa);
            
            for(Aluno obj : lista){
                ResponsaveisDao responsavelDao = new ResponsaveisDao();
                Responsavel r = responsavelDao.buscarPorId(obj.getIdresponsavel());
                
            // Formatando a data antes de inseri-la na tabela
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String dataNascimentoFormatada = dateFormat.format(obj.getDatanascimento());

                
                String[] linha = {
                obj.getId().toString(),
                obj.getNome(),
                obj.getMatricula().toString(),
                r.getNome(),
                obj.getIdade(),
                obj.getObservacoes(),
                //dataNascimentoFormatada,
                
                ""
                };
                modelo.addRow(linha);
            }
                    // Aplica o renderizador de idade à coluna de idade (substitua pelo índice correto)
                int indiceColunaIdade = encontrarIndiceColuna(modelo, "Idade");
                if (indiceColunaIdade != -1) {
                    jTableAlunoListar.getColumnModel().getColumn(indiceColunaIdade).setCellRenderer(new IdadeRenderer());
                }
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar. " + ex.getMessage());
        }
    }
    
    private void carregarTabelaAluno() {
            String campoPesquisa = jTextFieldPesquisa1.getText();
            this.preencherTabela(campoPesquisa);
        }
    
    public void callback() {
        this.carregarTabelaAluno();
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
            java.util.logging.Logger.getLogger(GerenciarAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciarAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciarAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciarAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciarAluno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonPDF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAlunoListar;
    private javax.swing.JTable jTableTurmas;
    private javax.swing.JTextField jTextFieldPesquisa1;
    // End of variables declaration//GEN-END:variables
}
