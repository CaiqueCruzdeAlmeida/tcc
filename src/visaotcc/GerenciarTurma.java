package visaotcc;

import Login.DadosDeSessao;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controlador.AlunoDao;
import controlador.AlunosPorTurmaDao;
import controlador.PagamentoDao;
import controlador.ResponsaveisDao;
import controlador.TurmaDao;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Aluno;
import modelo.AlunosPorTurma;
import modelo.Pagamentos;
import modelo.Responsavel;
import modelo.Turma;

/**
 *
 * @author caiqu
 */
public class GerenciarTurma extends javax.swing.JFrame {
    public GerenciarTurma gerenciarTurmaForm;
    public Relacionamento_Turma_Aluno relaionamentoForm;  
    public Principal listarForm;
    public GerenciarAluno AlunoForm;
    public GerenciarResponsaveis ResponsaveisForm;
    public Gerenciarusuarios UsuariosForm;
    public TurmasPorAluno TurmasAlunoForm;
    public Pagamento PagamentoForm;
    public CadastrarTurma CadturmaForm;
    
    /**
     * Creates new form GerenciarMtrTrm
     */
    public GerenciarTurma() { 
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/CC.png")).getImage());
        this.setTitle(DadosDeSessao.nomeSistema);
        
        this.tamanhotabelas();
        
        this.preencherTabela("");
       // this.preencherTabela2("");
       this.callback();
    }

    private void tamanhotabelas(){
        jTableTurmas.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableTurmas.getColumnModel().getColumn(1).setPreferredWidth(145);
        jTableTurmas.getColumnModel().getColumn(2).setPreferredWidth(145);
        
        
        jTableAlunos1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTableAlunos1.getColumnModel().getColumn(1).setPreferredWidth(190);
        jTableAlunos1.getColumnModel().getColumn(2).setPreferredWidth(190);
        jTableAlunos1.getColumnModel().getColumn(3).setPreferredWidth(250);
        jTableAlunos1.getColumnModel().getColumn(4).setPreferredWidth(250);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTurmas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPesquisa = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButtonExcluir2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableAlunos1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButtonPDF = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTableTurmas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Qntd. Matriculados", "Mensalidade $"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableTurmas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTurmasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableTurmas);

        jLabel2.setText("Pesquisar:");

        jTextFieldPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisaKeyReleased(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        jButton6.setText("Nova Turma");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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

        jTableAlunos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Matricula", "Inicio", "Termino"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAlunos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAlunos1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableAlunos1);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Alunos por Turma");

        jButtonPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/download-pequeno.jpg"))); // NOI18N
        jButtonPDF.setText("PDF");
        jButtonPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPDFActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/download-pequeno.jpg"))); // NOI18N
        jButton1.setText("PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPesquisa))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonExcluir2, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                    .addComponent(jButtonPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(72, 72, 72)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonExcluir2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Turma", jPanel3);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Gerenciar Turma");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public void callback() {
        this.carregarTabelaTurma();
    }
    private void jButtonExcluir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluir2ActionPerformed

        int opcao = JOptionPane.showConfirmDialog(this, "Confirma exclusão?", "Excluir", JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            int linhaSelecionada = jTableTurmas.getSelectedRow();

            if (linhaSelecionada != -1) {
                Integer idturma = Integer.parseInt(
                    jTableTurmas.getModel().getValueAt(linhaSelecionada, 0).toString());

                // Verifica se o valor da terceira coluna é maior que zero
                int valorTerceiraColuna = Integer.parseInt(
                    jTableTurmas.getModel().getValueAt(linhaSelecionada, 2).toString());

                if (valorTerceiraColuna > 0) {
                    JOptionPane.showMessageDialog(this, "Não é possível excluir a turma pois há alunos matriculados.");
                } else {
                    try {
                        //Exclui do BD
                        TurmaDao dao = new TurmaDao();
                        dao.excluir(idturma);

                        //Remove linha da tabela
                        DefaultTableModel modelo = (DefaultTableModel) jTableTurmas.getModel();
                        modelo.removeRow(linhaSelecionada);

                        JOptionPane.showMessageDialog(this, "Registro excluído com sucesso.");
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Erro ao excluir registro." + e.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um registro.");
            }
        }
    }//GEN-LAST:event_jButtonExcluir2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.alterarTurma();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        CadastrarTurma uc = new CadastrarTurma();
        uc.setVisible(true);
        uc.gerenciarTurmaForm = this;
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextFieldPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisaKeyReleased
        String campoPesquisa = jTextFieldPesquisa.getText();

        this.preencherTabela(campoPesquisa);
    }//GEN-LAST:event_jTextFieldPesquisaKeyReleased

    private void jTableTurmasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTurmasMouseClicked
        if (evt.getClickCount() == 2) {
            System.out.println("Duplo clique detectado.");
            this.alterarTurma();
        }
        if (evt.getClickCount() == 1) {
        int selectedRow = jTableTurmas.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel modeloTabela1 = (DefaultTableModel) jTableTurmas.getModel();
            int indiceNomeTurma = encontrarIndiceColuna(modeloTabela1, "Nome");

            if (indiceNomeTurma != -1) {
                String nomeTurma = jTableTurmas.getValueAt(selectedRow, indiceNomeTurma).toString();

                try {
                    TurmaDao turmaDao = new TurmaDao();
                    List<Object[]> alunosTurmaInfo = turmaDao.buscarInformacoesAlunoPorTurma(nomeTurma);

                    DefaultTableModel modeloAlunosTurma = (DefaultTableModel) jTableAlunos1.getModel();
                    modeloAlunosTurma.setColumnIdentifiers(new Object[]{"ID", "Nome", "Matrícula", "Data de Início", "Data de Fechamento"});
                    modeloAlunosTurma.setRowCount(0); // Limpa a tabela antes de preencher com novos dados

                    SimpleDateFormat dateFormatOriginal = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat dateFormatDestino = new SimpleDateFormat("dd/MM/yyyy");

                    for (Object[] alunoInfo : alunosTurmaInfo) {
                        String dataInicioStr = (String) alunoInfo[3];
                        String dataFechamentoStr = (String) alunoInfo[4];

                        // Converter as strings de data para objetos Date
                        Date dataInicio = dateFormatOriginal.parse(dataInicioStr);
                        Date dataFechamento = dateFormatOriginal.parse(dataFechamentoStr);

                        // Formatar as datas no formato desejado
                        String dataInicioFormatada = dateFormatDestino.format(dataInicio);
                        String dataFechamentoFormatada = dateFormatDestino.format(dataFechamento);

                        // Substituir os valores originais pelos valores formatados
                        alunoInfo[3] = dataInicioFormatada;
                        alunoInfo[4] = dataFechamentoFormatada;

                        modeloAlunosTurma.addRow(alunoInfo);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erro ao buscar alunos da turma: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Coluna 'Nome' não encontrada na tabela.");
            }
        }
    }
    }//GEN-LAST:event_jTableTurmasMouseClicked

    private void jButtonPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPDFActionPerformed
        exportToPDF();
        //JOptionPane.showMessageDialog(null, "PDF created successfully!");
    }//GEN-LAST:event_jButtonPDFActionPerformed

    private void jTableAlunos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAlunos1MouseClicked
       
//        if (evt.getClickCount() == 2) {
//        this.abrirTelaPagamentos();
//    }
        
    }//GEN-LAST:event_jTableAlunos1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        exportToPDF2();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void abrirTelaPagamentos() {
    int selectedRow = jTableTurmas.getSelectedRow();
    if (selectedRow != -1) {
        DefaultTableModel modeloTurmas = (DefaultTableModel) jTableTurmas.getModel();
        int idTurma = Integer.parseInt(modeloTurmas.getValueAt(selectedRow, 0).toString()); // Assumindo que a coluna 0 contém o ID da turma

        try {
            PagamentoDao pagamentoDao = new PagamentoDao();
            List<Pagamentos> pagamentos = pagamentoDao.buscarPagamentosPorTurma(idTurma);

            // Obtém uma instância da tela MesesPagos e a preenche com os pagamentos
            MesesPagos telaMesesPagos = new MesesPagos();
            telaMesesPagos.preencherTabela(pagamentos);
            telaMesesPagos.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar pagamentos: " + ex.getMessage());
        }
    }
}
    
    
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
private void exportToPDF2() {
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

        criarPDFComCabecalho2(fileToSave);
        JOptionPane.showMessageDialog(null, "PDF created successfully!");
    }
}
    private void criarPDFComCabecalho(File arquivoParaSalvar) {
        String textoCabecalho = "Relatório de Turmas";

        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(arquivoParaSalvar));
            documento.open();

            PdfPTable tabelaPDF = new PdfPTable(jTableTurmas.getColumnCount());

            PdfPCell celulaCabecalho = new PdfPCell(new Phrase(textoCabecalho));
            celulaCabecalho.setColspan(jTableTurmas.getColumnCount());
            celulaCabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabelaPDF.addCell(celulaCabecalho);

            // Adiciona cabeçalhos à tabela
            for (int i = 0; i < jTableTurmas.getColumnCount(); i++) {
                tabelaPDF.addCell(jTableTurmas.getColumnName(i));
            }

            // Adiciona dados da tabela ao PDF
            for (int rows = 0; rows < jTableTurmas.getRowCount(); rows++) {
                for (int cols = 0; cols < jTableTurmas.getColumnCount(); cols++) {
                    tabelaPDF.addCell(jTableTurmas.getModel().getValueAt(rows, cols).toString());
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

    private void criarPDFComCabecalho2(File arquivoParaSalvar) {
    Document documento = new Document();
    try {
        PdfWriter.getInstance(documento, new FileOutputStream(arquivoParaSalvar));
        documento.open();

        PdfPTable tabelaPDF = new PdfPTable(jTableAlunos1.getColumnCount());

        // Adiciona o texto "Relatório da turma [Nome da Turma]" ao cabeçalho
        String nomeTurma = jTableTurmas.getValueAt(0, 1).toString(); // Supondo que o índice 01 contém o nome da turma
        String textoCabecalho = "Relatório da turma " + nomeTurma;
        PdfPCell celulaCabecalho = new PdfPCell(new Phrase(textoCabecalho));
        celulaCabecalho.setColspan(jTableAlunos1.getColumnCount());
        celulaCabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabelaPDF.addCell(celulaCabecalho);

        // Adiciona cabeçalhos à tabela
        for (int i = 0; i < jTableAlunos1.getColumnCount(); i++) {
            tabelaPDF.addCell(jTableAlunos1.getColumnName(i));
        }

        // Adiciona dados da tabela ao PDF
        for (int rows = 0; rows < jTableAlunos1.getRowCount(); rows++) {
            for (int cols = 0; cols < jTableAlunos1.getColumnCount(); cols++) {
                tabelaPDF.addCell(jTableAlunos1.getModel().getValueAt(rows, cols).toString());
            }
        }

        documento.add(tabelaPDF);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro ao criar PDF: " + e.getMessage());
    } finally {
        if (documento != null && documento.isOpen()) {
            documento.close();
        }
    }
}

    
    private int encontrarIndiceColuna(DefaultTableModel modelo, String Nome) {
    for (int i = 0; i < modelo.getColumnCount(); i++) {
        if (modelo.getColumnName(i).equals(Nome)) {
            return i; // Retorna o índice da coluna quando encontrar o nome desejado
        }
    }
    return -1; // Retorna -1 se o nome da coluna não for encontrado
}


    private void alterarTurma(){
        int linhaSelecionada = jTableTurmas.getSelectedRow();

        if (linhaSelecionada != -1) {
            Integer idturma = Integer.parseInt(
                    jTableTurmas.getModel().getValueAt(linhaSelecionada, 0).toString());

            AlterarTurma form = new AlterarTurma();
            form.setVisible(true);
            form.gerenciarForm = this;
            form.mostrarTurma(idturma.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro.");
        }
    }
    private void preencherTabela(String campoPesquisa) {
    DefaultTableModel modelo = (DefaultTableModel) jTableTurmas.getModel();
    modelo.setNumRows(0);
    try {
        TurmaDao dao = new TurmaDao();
        List<Turma> lista = dao.buscarPorNome(campoPesquisa); // Alteração aqui

        for (Turma obj : lista) {
            String[] linha = {
                obj.getIdTurma().toString(),
                obj.getNometurma().toString(),
                obj.getQuantidadematriculados().toString(),
                obj.getValormensalidade().toString(),
                ""
            };
            modelo.addRow(linha);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao Pesquisar. " + ex.getMessage());
    }
}
     private void preencherTabela2(String campoPesquisa) {
    DefaultTableModel modelo = (DefaultTableModel) jTableAlunos1.getModel();
    modelo.setColumnIdentifiers(new Object[]{"ID", "Nome", "Matrícula", "Data de Início", "Data de Fechamento"});
    modelo.setNumRows(0); // Limpa a tabela para preenchimento limpo

    try {
        if (campoPesquisa.isEmpty()) {
             return;
        }

        TurmaDao dao = new TurmaDao();
        List<Object[]> lista = dao.buscarInformacoesAlunoPorTurma(campoPesquisa);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (Object[] alunoInfo : lista) {
            // Supondo que a data de início está na posição 3 e a data de fechamento está na posição 4 do array alunoInfo
            String dataInicioStr = (String) alunoInfo[3];
            String dataFechamentoStr = (String) alunoInfo[4];

            // Converter as strings de data para o formato desejado
            Date dataInicio = dateFormat.parse(dataInicioStr);
            Date dataFechamento = dateFormat.parse(dataFechamentoStr);

            // Formatando as datas no formato dd-MM-yyyy
            String dataInicioFormatada = dateFormat.format(dataInicio);
            String dataFechamentoFormatada = dateFormat.format(dataFechamento);

            // Substituir os valores originais pelas datas formatadas no array alunoInfo
            alunoInfo[3] = dataInicioFormatada;
            alunoInfo[4] = dataFechamentoFormatada;

            modelo.addRow(alunoInfo);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao Pesquisar. " + ex.getMessage());
    }
}


     
    private void carregarTabelaTurma() {
        String campoPesquisa = jTextFieldPesquisa.getText();
        this.preencherTabela(campoPesquisa);
    }
    
    
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
            java.util.logging.Logger.getLogger(GerenciarTurma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciarTurma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciarTurma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciarTurma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciarTurma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButtonExcluir2;
    private javax.swing.JButton jButtonPDF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAlunos1;
    private javax.swing.JTable jTableTurmas;
    private javax.swing.JTextField jTextFieldPesquisa;
    // End of variables declaration//GEN-END:variables
}
