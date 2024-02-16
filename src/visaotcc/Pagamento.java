/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visaotcc;

import Login.DadosDeSessao;
import Login.telaLogin;
import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import controlador.AlunoDao;
import controlador.AlunosPorTurmaDao;
import controlador.PagamentoDao;
import controlador.TurmaDao;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelo.Aluno;
import modelo.AlunosPorTurma;
import modelo.Pagamentos;
import modelo.Turma;
import modelo.TurmaPorAluno;

/**
 *
 * @author Caique
 */
public class Pagamento extends javax.swing.JFrame {
    
     private ArrayList<Integer> id = new ArrayList();
     private ArrayList<String> Desc = new ArrayList();
     private List<Integer> idTurmaPorAluno = new ArrayList<>();
     public Pagamento PagamentoForm;
     
     public GerenciarPagamentos gerenciarpagamentosForm;
     Principal listarForm;
    /**
     * Creates new form Pagamento
     */
    public Pagamento() {
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/CC.png")).getImage());
        this.setTitle(DadosDeSessao.nomeSistema);
        this.Valornumeros();
        this.Anonumero();
        this.carregaComboBoxAlunos();
//        this.preenchertabela("");
//        this.carregarTabelaAluno();
        this.HabilitarCadastrar();
//        this.digitar();
//        this.buscaporid();
        
    }

//    public void buscaporid() {
//    jTextFieldPesquisa.addKeyListener(new KeyAdapter() {
//        @Override
//        public void keyTyped(KeyEvent e) {
//            char character = e.getKeyChar();
//            if (!Character.isDigit(character) && character != '.' && Character.isLetter(character)) {
//                e.consume(); // Impede a digitação do caractere
//                JOptionPane.showMessageDialog(jTextFieldPesquisa, "A busca deve ser feita pelo número do ID da turma referente.");
//            }
//        }
//    });
//}
    
    public void Valornumeros() {
    jTextFieldValorPago.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char character = e.getKeyChar();
            if (!Character.isDigit(character) && character != '.' && Character.isLetter(character)) {
                e.consume(); // Impede a digitação do caractere
                JOptionPane.showMessageDialog(jTextFieldValorPago, "Somente números são permitidos.");
            }
        }
    });
}

 public void Anonumero(){
     jTextFieldAnoRefencia.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char character = e.getKeyChar();
            if (!Character.isDigit(character) && character != '.' && Character.isLetter(character)) {
                e.consume(); // Impede a digitação do caractere
                JOptionPane.showMessageDialog(jTextFieldAnoRefencia, "Somente números são permitidos.");
            }
        }
    });
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldValorPago = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxFormaPagamento = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jFormattedTextFieldDataPagamento = new javax.swing.JFormattedTextField();
        jTextFieldIDAluno = new javax.swing.JTextField();
        jComboBoxMês = new javax.swing.JComboBox<>();
        jTextFieldAnoRefencia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAluno = new javax.swing.JTable();
        jComboBoxTurma = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jComboBoxAlunos = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Data do Pagamento:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Valor Pago:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Mês referência:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Ano referência:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Matrícula Referente:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Formas de Pagamento:");

        jComboBoxFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Selecione --", "Pix", "Crédito", "Débito", "Dinheiro" }));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        try {
            jFormattedTextFieldDataPagamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jTextFieldIDAluno.setEnabled(false);

        jComboBoxMês.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Selecione --", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));

        jTableAluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data inicio", "Data Fechamento", "Turma"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableAluno);

        jComboBoxTurma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Selecione --" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldIDAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextFieldDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(34, 34, 34)
                                        .addComponent(jTextFieldAnoRefencia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(34, 34, 34)
                                        .addComponent(jComboBoxMês, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxFormaPagamento, 0, 165, Short.MAX_VALUE)
                                    .addComponent(jComboBoxTurma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxMês, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jTextFieldAnoRefencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIDAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        jTabbedPane1.addTab("Informações do Pagamento", jPanel1);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Realizar Pagamentos");

        jComboBoxAlunos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Selecione --" }));
        jComboBoxAlunos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAlunosActionPerformed(evt);
            }
        });

        jLabel9.setText("Selecione o Aluno:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxAlunosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAlunosActionPerformed
    String nomeAluno = (String) jComboBoxAlunos.getSelectedItem();

    if (nomeAluno != null && !nomeAluno.isEmpty()) {
        try {
            // Encontrar o ID do aluno selecionado
            int idAluno = encontraIdAlunoPeloNome(nomeAluno);

            System.out.println("ID do Aluno selecionado: " + idAluno);

            // Atualiza a tabela com base no ID do aluno selecionado
            preenchertabela(idAluno);

            // Carrega todas as turmas do aluno no jComboBox1
            carregarTurmasDoAluno(idAluno);

            // Atualiza o JTextFieldIDAluno com o ID do aluno
            jTextFieldIDAluno.setText(String.valueOf(idAluno));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao obter o ID do Aluno.");
        }
    }
    }//GEN-LAST:event_jComboBoxAlunosActionPerformed
private int encontraIdAlunoPeloNome(String nomeAluno) throws Exception {
    for (int i = 0; i < Desc.size(); i++) {
        if (nomeAluno.equals(Desc.get(i))) {
            return id.get(i);
        }
    }
    throw new Exception("ID do aluno não encontrado para o nome: " + nomeAluno);
}
  private void carregarTurmasDoAluno(Integer idAluno) {
    try {
        // Limpa o conteúdo atual do jComboBox1
        jComboBoxTurma.removeAllItems();

        System.out.println("Carregando turmas para o aluno com ID: " + idAluno);

        // Busca as turmas associadas ao aluno com o idAluno
        AlunosPorTurmaDao alunosPorTurmaDao = new AlunosPorTurmaDao();
        List<AlunosPorTurma> turmasDoAluno = alunosPorTurmaDao.turmasPorAluno1(idAluno);

        System.out.println("Número de turmas encontradas: " + turmasDoAluno.size());

        // Popula o jComboBox1 com as turmas encontradas
        for (AlunosPorTurma alunoTurma : turmasDoAluno) {
            System.out.println("ID da turma associada: " + alunoTurma.getIdturma());

            TurmaDao turmaDao = new TurmaDao();
            Turma turma = turmaDao.buscarPorId(alunoTurma.getIdturma());

            if (turma != null) {
                System.out.println("Adicionando turma ao jComboBox1: " + turma.getNometurma());
                jComboBoxTurma.addItem(turma.getNometurma());
            } else {
                System.out.println("Turma não encontrada para o ID: " + alunoTurma.getIdturma());
            }
        }

        // Atualiza a interface gráfica
        jComboBoxTurma.revalidate();
        jComboBoxTurma.repaint();
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao carregar combobox de turmas: " + ex);
    }
}  

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    Double valorpago = Double.valueOf(jTextFieldValorPago.getText().replaceAll(",", "."));
    String datapagamento = jFormattedTextFieldDataPagamento.getText();
    String mesderefenencia = (String)jComboBoxMês.getSelectedItem();
    Integer anoreferencia = Integer.parseInt(jTextFieldAnoRefencia.getText());
    String turmadoaluno = (String)jComboBoxTurma.getSelectedItem();
    String nomealuno = (String)jComboBoxAlunos.getSelectedItem();
    String formapagamento = (String)jComboBoxFormaPagamento.getSelectedItem();

    // Aplicando a conversão para um objeto java.util.Date
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    try {
        java.util.Date dataPagamento = dateFormat.parse(datapagamento);
        // Agora você tem a data no formato java.util.Date (dataPagamento)

        
        
        String[] partesData = datapagamento.split("-"); // Agora dividindo a data pelo separador "-"

        int dia = Integer.parseInt(partesData[0]);
        int mes = Integer.parseInt(partesData[1]);
        int ano= Integer.parseInt(partesData[2]);

        boolean isBissexto = (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0));

        // Validar dias para meses que têm apenas 30 ou 31 dias
        if ((dia == 31 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) ||
            (dia > 29 && mes == 2) || // Fev. não pode ter mais que 29 dias
            (dia == 29 && mes == 2 && !isBissexto)) {
            JOptionPane.showMessageDialog(this, "Data inválida para o mês selecionado.");
            jFormattedTextFieldDataPagamento.setForeground(Color.RED);
            return;
        }

        Pagamentos u = new Pagamentos(dataPagamento, mesderefenencia, anoreferencia, valorpago, formapagamento, turmadoaluno, nomealuno, null);

        try {
            PagamentoDao dao = new PagamentoDao();
            dao.inserir(u);
            gerenciarpagamentosForm.callback();

            JOptionPane.showMessageDialog(this, "registro inserido.");
            this.dispose();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Erro" + e.getMessage());
        }
    } catch (ParseException e) {
        e.printStackTrace(); // Ou trate a exceção de acordo com a lógica do seu programa
        JOptionPane.showMessageDialog(this, "Formato de data inválido.");
        jFormattedTextFieldDataPagamento.setForeground(Color.RED);
        return;
    }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed
//        private void digitar(){
//            // Dentro do seu construtor ou inicialização
//        jTextFieldPesquisa.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                carregarTabelaAluno();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                carregarTabelaAluno();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                
//            }
//        });
//}
    private Map<String, List<Integer>> alunosMap = new HashMap<>();
    private Map<String, Integer> contadorPorAluno = new HashMap<>();
    
  private void carregaComboBoxAlunos() {
   try {
        AlunosPorTurmaDao dao = new AlunosPorTurmaDao();
        List<AlunosPorTurma> lista = dao.buscar("");

        // Limpar arrays antes de preenchê-los
        this.id.clear();
        this.Desc.clear();

        this.id.add(0);
        this.Desc.add("-- Selecione --");

        // Utilizar um HashSet para garantir que cada aluno apareça apenas uma vez
        Set<String> alunosSet = new HashSet<>();

        for (AlunosPorTurma alunoTurma : lista) {
            int idAluno = alunoTurma.getIdaluno();

            AlunoDao alunoDao = new AlunoDao();
            Aluno aluno = alunoDao.buscarPorId(idAluno);

            if (aluno != null && !alunosSet.contains(aluno.getNome())) {
                String nomeAluno = aluno.getNome();
                alunosSet.add(nomeAluno);

                this.id.add(aluno.getId());  // Usar o ID do aluno
                this.Desc.add(nomeAluno);
            }
        }
        
        jComboBoxAlunos.setModel(new javax.swing.DefaultComboBoxModel<>(Desc.toArray(new String[0])));
        
        // Adicionar o ouvinte de evento ao JComboBox
        jComboBoxAlunos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém o índice selecionado no JComboBox
                int selectedIndex = jComboBoxAlunos.getSelectedIndex();
                
                // Se o índice for maior que 0, um aluno foi selecionado (considerando que o primeiro item é "-- Selecione --")
                if (selectedIndex > 0) {
                    int idAlunoSelecionado = id.get(selectedIndex);
                    preenchertabela(idAlunoSelecionado);
                }
            }
        });

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao carregar combobox de alunos: " + ex);
    }
}


    private void preenchertabela(int idAluno) {
    DefaultTableModel modelo = (DefaultTableModel) jTableAluno.getModel();
    modelo.setNumRows(0);
    try {
        AlunosPorTurmaDao dao = new AlunosPorTurmaDao();
        
        // Modifique o método buscar para aceitar o ID do aluno como int
        List<AlunosPorTurma> lista = dao.buscarPorIdAluno(idAluno);

        for (AlunosPorTurma obj : lista) {
            // Restante do código...
            TurmaDao turmaDao = new TurmaDao();
            Turma t = turmaDao.buscarPorId(obj.getIdturma());
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dataInicioFormatada = dateFormat.format(obj.getDatainicio());
            String dataFechamentoFormatada = dateFormat.format(obj.getDatafechamento());
            
            String[] linha = {
                obj.getId().toString(),
                dataInicioFormatada,
                dataFechamentoFormatada,
                t.getNometurma(),
                ""
            };
            modelo.addRow(linha);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao Pesquisar. " + ex.getMessage());
    }
}

//  private void carregarTabelaAluno() {
//            String campoPesquisa = jTextFieldPesquisa.getText();
//            this.preenchertabela(campoPesquisa);
//        }

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
    jTextFieldIDAluno.getDocument().addDocumentListener(documentListener);
    jTextFieldAnoRefencia.getDocument().addDocumentListener(documentListener);
    jTextFieldValorPago.getDocument().addDocumentListener(documentListener);
    jFormattedTextFieldDataPagamento.getDocument().addDocumentListener(documentListener);

    // Adicione um ActionListener para monitorar as ComboBoxes
    ActionListener comboBoxListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            checkFields(); // Quando as ComboBoxes forem alteradas, chame checkFields() para atualizar a habilitação do botão
        }
    };
    jComboBoxFormaPagamento.addActionListener(comboBoxListener);
    jComboBoxMês.addActionListener(comboBoxListener);

    // Verifique os campos uma vez para garantir que o botão esteja corretamente habilitado ou desabilitado inicialmente
    checkFields();
}

// Função para verificar os campos
public void checkFields() {
    String id = jTextFieldIDAluno.getText();
    String ano = jTextFieldAnoRefencia.getText();
    String valor = jTextFieldValorPago.getText();
    String data = jFormattedTextFieldDataPagamento.getText();

    // Verifique se algum dos campos obrigatórios está vazio ou inválido
    if (id.isEmpty() || ano.isEmpty() || data.equals("  -  -    ") || valor.isEmpty()) {
        jButton1.setEnabled(false);
        return;
    }

    try {
        int id2 = Integer.parseInt(id);
        int valor2 = Integer.parseInt(valor);
        int ano2 = Integer.parseInt(ano);
        String data2 = data;

        // Verifique se os campos não são iguais a zero e se os campos adicionais estão preenchidos corretamente
        jButton1.setEnabled(id2 != 0 && !data2.equals("    -  -  ") && jComboBoxFormaPagamento.getSelectedIndex() != 0 && jComboBoxMês.getSelectedIndex() != 0);
    } catch (NumberFormatException e) {
        // Em caso de erro na conversão, desative o botão
        jButton1.setEnabled(false);
    }
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
            java.util.logging.Logger.getLogger(Pagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pagamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxAlunos;
    private javax.swing.JComboBox<String> jComboBoxFormaPagamento;
    private javax.swing.JComboBox<String> jComboBoxMês;
    private javax.swing.JComboBox<String> jComboBoxTurma;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataPagamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAluno;
    private javax.swing.JTextField jTextFieldAnoRefencia;
    private javax.swing.JTextField jTextFieldIDAluno;
    private javax.swing.JTextField jTextFieldValorPago;
    // End of variables declaration//GEN-END:variables

    
}
