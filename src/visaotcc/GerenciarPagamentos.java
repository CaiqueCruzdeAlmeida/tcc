/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
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
import controlador.Conexao;
import controlador.PagamentoDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.Aluno;
import modelo.AlunosPorTurma;
import modelo.Pagamentos;

/**
 *
 * @author Caique
 */
public class GerenciarPagamentos extends javax.swing.JFrame {
     private ArrayList<Integer> ano = new ArrayList();
     private ArrayList<String> anoDesc = new ArrayList();
     
     public GerenciarPagamentos gerenciarpagamentosForm;
     
     public Pagamento PagamentoForm;
     public AlterarPagamentos alterarpgForm;
     public Principal listarForm;

    /**
     * Creates new form GerenciarPAgamentos
     */
    public GerenciarPagamentos() {
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/CC.png")).getImage());
        this.setTitle(DadosDeSessao.nomeSistema);
        
        this.preencherTabela();
        this.mesref();
        this.carregaComboBoxAno();
        this.configuraFiltroPorAno();
        
        this.callback();
       
    }
   private void preencherTabela() {
    DefaultTableModel modelo = (DefaultTableModel) jTablePagamentosListar.getModel();
    modelo.setNumRows(0);
    
    try {
        PagamentoDao dao = new PagamentoDao();
        List<Pagamentos> lista = dao.buscar();
        
          // Ordena a lista pelo ano e mês de referência antes de preencher a tabela
        lista.sort(Comparator.comparingInt(Pagamentos::getAnoreferencia).reversed()
                             .thenComparing(Pagamentos::getMesderefenencia, Comparator.comparingInt(Arrays.asList("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro")::indexOf)));
        
        for (Pagamentos obj : lista) {
//            String nomeAluno = dao.buscarNomeAlunoPorIdTurma(obj.getTurmadoaluno());
//            String nomeTurma = dao.buscarNomeTurmaPorIdTurma(obj.getTurmadoaluno());

            // Formatando a data antes de inseri-la na tabela
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dataPagamentoFormatada = dateFormat.format(obj.getDatapagamento());

            String[] linha = {
                obj.getId().toString(),
                obj.getNomealuno(),
                //nomeAluno, // Nome do aluno
                dataPagamentoFormatada, // Data formatada como string
                obj.getValorpago().toString(),
                obj.getTurmadoaluno(),
//                nomeTurma, // Nome da turma do aluno                              
                obj.getMesderefenencia().toString(),
                obj.getAnoreferencia().toString(),
                obj.getFormapagamento()
            };
            modelo.addRow(linha);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao Pesquisar. " + ex.getMessage());
    }
}
   private int getIndexFromMonthName(String monthName, String[] monthsOrder) {
    for (int i = 0; i < monthsOrder.length; i++) {
        if (monthsOrder[i].equalsIgnoreCase(monthName)) {
            return i;
        }
    }
    return -1; // Caso o mês não seja encontrado (situação de erro)
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

                criarPDFComCabecalho(fileToSave); // Chama o método para criar o PDF com cabeçalho
                JOptionPane.showMessageDialog(null, "PDF created successfully!");
            }
    }

    private void criarPDFComCabecalho(File arquivoParaSalvar) {
        String textoCabecalho = "Relatório de Pagamentos";

        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(arquivoParaSalvar));
            documento.open();

            PdfPTable tabelaPDF = new PdfPTable(jTablePagamentosListar.getColumnCount());

            PdfPCell celulaCabecalho = new PdfPCell(new Phrase(textoCabecalho));
            celulaCabecalho.setColspan(jTablePagamentosListar.getColumnCount());
            celulaCabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabelaPDF.addCell(celulaCabecalho);

            // Adiciona cabeçalhos à tabela
            for (int i = 0; i < jTablePagamentosListar.getColumnCount(); i++) {
                tabelaPDF.addCell(jTablePagamentosListar.getColumnName(i));
            }

            // Adiciona dados da tabela ao PDF
            for (int rows = 0; rows < jTablePagamentosListar.getRowCount(); rows++) {
                for (int cols = 0; cols < jTablePagamentosListar.getColumnCount(); cols++) {
                    tabelaPDF.addCell(jTablePagamentosListar.getModel().getValueAt(rows, cols).toString());
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


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePagamentosListar = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButtonPDF = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxAno = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jTextFieldBusca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTablePagamentosListar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Aluno", "Data Pgt", "Valor Pago", "Turma", "Mês", "Ano Referência", "Forma pgt"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePagamentosListar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePagamentosListarMouseClicked(evt);
            }
        });
        jTablePagamentosListar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTablePagamentosListarKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePagamentosListar);

        jTabbedPane1.addTab("Pagamentos efetuados", jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Gerenciar Mensalidades Recebidas");

        jButtonPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/download-pequeno.jpg"))); // NOI18N
        jButtonPDF.setText("PDF");
        jButtonPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPDFActionPerformed(evt);
            }
        });

        jButton1.setText("Alterar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Excluir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Filtrar por Ano:");

        jComboBoxAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Selecione --" }));
        jComboBoxAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAnoActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        jButton3.setText("Novo Pagamento");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextFieldBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Pesquisar Por Nome:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxAno, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldBusca)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jComboBoxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonPDF)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPDFActionPerformed
        exportToPDF();
    }//GEN-LAST:event_jButtonPDFActionPerformed

    private void jTablePagamentosListarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePagamentosListarKeyPressed
         
    }//GEN-LAST:event_jTablePagamentosListarKeyPressed

    private void jTablePagamentosListarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePagamentosListarMouseClicked
        if (evt.getClickCount() == 2) {
            //System.out.println("Duplo clique detectado.");
            this.AlterarPagamentos();
        }
    }//GEN-LAST:event_jTablePagamentosListarMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.AlterarPagamentos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        int opcao = JOptionPane.showConfirmDialog(this, "Confirma exclusão?", "Excluir", JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            int linhaSelecionada = jTablePagamentosListar.getSelectedRow();

            if (linhaSelecionada != -1) {
                Integer codPagamentos = Integer.parseInt(
                    jTablePagamentosListar.getModel().getValueAt(linhaSelecionada, 0).toString());

                try {
                    //Exclui do BD
                    PagamentoDao dao = new PagamentoDao();
                    dao.excluir(codPagamentos);

                    //Remove linha da tabela
                    DefaultTableModel modelo = (DefaultTableModel) jTablePagamentosListar.getModel();
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
    }//GEN-LAST:event_jButton2ActionPerformed
private TableRowSorter<DefaultTableModel> sorter; // Declare o sorter como um atributo da classe

private void mesref() {
    DefaultTableModel tableModel = (DefaultTableModel) jTablePagamentosListar.getModel();
    sorter = new TableRowSorter<>(tableModel); // Inicialize o sorter aqui
    jTablePagamentosListar.setRowSorter(sorter);
}

private void configuraFiltroPorAno() {
    jComboBoxAno.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedYear = (String) jComboBoxAno.getSelectedItem();

            if ("-- Selecione --".equals(selectedYear)) {
                // Se "-- Selecione --" for selecionado, remova o filtro
                ((TableRowSorter<DefaultTableModel>) jTablePagamentosListar.getRowSorter()).setRowFilter(null);
            } else {
                // Se um ano for selecionado, aplique o filtro pelo ano na coluna 6 (índice 5)
                TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) jTablePagamentosListar.getRowSorter();
                RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(selectedYear, 6); 
                sorter.setRowFilter(filter);
            }
        }
    });
}

// Configuração do filtro para combobox de mês

private void carregaComboBoxAno() {
    try {
        PagamentoDao dao = new PagamentoDao();
        List<Pagamentos> lista = dao.buscar(""); // Supondo que o método buscar retorne os pagamentos
        
        Set<Integer> anosSet = new HashSet<>(); // Usando um Set para armazenar anos únicos
        
        for (Pagamentos pagamento : lista) {
            int anoReferencia = pagamento.getAnoreferencia();
            anosSet.add(anoReferencia);
        }

        List<String> anosListString = new ArrayList<>();
        anosListString.add("-- Selecione --"); // Adicionando o item inicial
        
        for (Integer ano : anosSet) {
            anosListString.add(String.valueOf(ano)); // Convertendo int para String
        }

        Collections.sort(anosListString); // Classificando os anos em ordem crescente
        
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for (String ano : anosListString) {
            comboBoxModel.addElement(ano);
        }
        
        jComboBoxAno.setModel(comboBoxModel);

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao carregar combobox de anos: " + ex);
    }
}

    private void jComboBoxAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAnoActionPerformed
        String nomeAluno = (String) jComboBoxAno.getSelectedItem();

        if (nomeAluno != null && !nomeAluno.isEmpty()) {
            try {
                // Verifica se "Todos" foi selecionado
                if ("-- Selecione --".equals(nomeAluno)) {
                    // Lógica para tratar o caso "Todos"
                    // Por exemplo, pode ser necessário exibir todos os alunos na tabela
                } else {
                    // Lógica para tratar o caso em que um aluno específico é selecionado
                    int selectedIndex = jComboBoxAno.getSelectedIndex();
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao obter o ID do Aluno.");
            }
    }

    }//GEN-LAST:event_jComboBoxAnoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Pagamento uc = new Pagamento();
        uc.setVisible(true);
        uc.gerenciarpagamentosForm = this;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextFieldBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscaKeyReleased
    String nomeAluno = jTextFieldBusca.getText().toLowerCase();

        DefaultTableModel modelo = (DefaultTableModel) jTablePagamentosListar.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        jTablePagamentosListar.setRowSorter(sorter);

        RowFilter<DefaultTableModel, Object> rowFilter = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(RowFilter.Entry<? extends DefaultTableModel, ? extends Object> entry) {
                // Verifica se o valor da célula na coluna do nome do aluno contém o texto procurado (ignorando maiúsculas e minúsculas)
                String nomeAlunoNaTabela = entry.getStringValue(1).toLowerCase(); // Assumindo que o nome do aluno está na coluna 1 (índice 0)
                return nomeAlunoNaTabela.contains(nomeAluno);
            }
        };

        sorter.setRowFilter(rowFilter);        
        
    }//GEN-LAST:event_jTextFieldBuscaKeyReleased
    
    
    private void AlterarPagamentos(){
            int linhaSelecionada = jTablePagamentosListar.getSelectedRow();

        if (linhaSelecionada != -1) {
            String id = jTablePagamentosListar.getModel().getValueAt(linhaSelecionada, 0).toString();

            AlterarPagamentos form = new AlterarPagamentos();
            form.setVisible(true);
            form.gerenciarpagamentosForm = this;
            form.mostrarPagamentos(id);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro.");
        }
    }
    public void callback() {
        this.preencherTabela();
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
            java.util.logging.Logger.getLogger(GerenciarPagamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciarPagamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciarPagamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciarPagamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciarPagamentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonPDF;
    private javax.swing.JComboBox<String> jComboBoxAno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTablePagamentosListar;
    private javax.swing.JTextField jTextFieldBusca;
    // End of variables declaration//GEN-END:variables
}
