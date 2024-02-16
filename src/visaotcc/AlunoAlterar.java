package visaotcc;

import Login.DadosDeSessao;
import controlador.AlunoDao;
import controlador.ResponsaveisDao;
import controlador.TurmaDao;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modelo.Aluno;
import modelo.Responsavel;
import modelo.Turma;
import visaotcc.GerenciarAluno;
import visaotcc.Principal;

/**
 *
 * @author caiqu
 */
public class AlunoAlterar extends javax.swing.JFrame {
   
     public Principal ListarForm;
     GerenciarAluno AlunoForm; 
     private ArrayList<Integer> id = new ArrayList();
     private ArrayList<String> Desc = new ArrayList();
     
     private ArrayList<Integer> idresponsavel = new ArrayList();
     private ArrayList<String> Descresponsavel = new ArrayList();
    /*
    private int proximoNumeroMatricula = 1; // Inicialize o contador
    private static final int ANO_ATUAL = 2023; // Ano fixo
   */
    
    
    public AlunoAlterar() {
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/CC.png")).getImage());
        this.setTitle(DadosDeSessao.nomeSistema);
       // carregaComboBoxTurma();
        carregaComboBoxResponsavel();
     
        this.calculareatualizar();
        
        this.HabilitarCadastrar();
        
    }
    
    private void calculareatualizar(){
        jFormattedTextFieldDataNasc.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                calcularIdadeEAtualizarCampo();
            }
        });
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
    
    jFormattedTextFieldCpf.getDocument().addDocumentListener(documentListener);
    jTextFieldIdade.getDocument().addDocumentListener(documentListener);
    jTextFieldIDResponsavel.getDocument().addDocumentListener(documentListener);
    jTextFieldNome.getDocument().addDocumentListener(documentListener);
    jFormattedTextFieldDataNasc.getDocument().addDocumentListener(documentListener);

   
    checkFields();
}
public void checkFields() {
    String cpf = jFormattedTextFieldCpf.getText();
    String idade = jTextFieldIdade.getText();
    String responsavel = jTextFieldIDResponsavel.getText();
    String data = jFormattedTextFieldDataNasc.getText();
    String nome = jTextFieldNome.getText();

    // Verifique se algum dos campos obrigatórios está vazio ou inválido
    if (cpf.equals("   .   .   -  ") || idade.isEmpty() || responsavel.isEmpty() || data.equals("  -  -    ") || nome.isEmpty()){
        jButton1.setEnabled(false);
        return;
    }

    // Remova os espaços em branco e os caracteres de formatação do CPF
    String cpfSemFormato = cpf.replaceAll("[^0-9]", "");
   

    // Verifique se o CPF sem formatação possui 11 dígitos
    if (cpfSemFormato.length() != 11) {
        jButton1.setEnabled(false);
        return;
    }

    try {
        // Tente converter o CPF para long
        long cpfLong = Long.parseLong(cpfSemFormato);

        // Verifique a validade da data
        boolean dateValid = data.matches("\\d{2}-\\d{2}-\\d{4}");
        // Verifique se a data está completamente preenchida (10 caracteres)
        if (dateValid && data.length() == 10) {
            jButton1.setEnabled(!idade.isEmpty() && !responsavel.isEmpty());
        } else {
            jButton1.setEnabled(false);
        }
    } catch (NumberFormatException e) {
        // Em caso de erro na conversão, desative o botão
        jButton1.setEnabled(false);
    }
}

    public void carregarDadosAluno(Integer id) {
        // Aqui você implementa a lógica para carregar os dados do aluno com base no ID
        // e preencher os campos da tela AlunoAlterar
        try {
            AlunoDao dao = new AlunoDao();
            Aluno aluno = dao.buscarPorId(id);
           
            if (aluno != null) {
                // Formatar a data para exibição no formato desejado
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String dataNascimentoFormatada = dateFormat.format(aluno.getDatanascimento());
                
            jTextFieldID.setText(String.valueOf(aluno.getId()));
            jTextFieldNome.setText(aluno.getNome());
            jFormattedTextFieldCpf.setText(aluno.getCpf());
            jTextFieldRG.setText(aluno.getRg());
            jTextFieldIdade.setText(aluno.getIdade());
            jTextFieldMatricula.setText(String.valueOf(aluno.getMatricula()));
//          jTextFieldIDTurma.setText(String.valueOf(aluno.getIdturma()));
            jTextFieldIDResponsavel.setText(String.valueOf(aluno.getIdresponsavel()));
            jTextFieldDataCad.setText(aluno.getDatacadastro());
            jFormattedTextFieldDataNasc.setText(dataNascimentoFormatada);
            jTextAreaObservacoes.setText(aluno.getObservacoes());
            
            // Preencha os demais campos com as informações correspondentes
        } else {
               JOptionPane.showMessageDialog(this, "Registro não encontrado");
               jTextFieldID.setText("");
               jTextFieldNome.setText("");
               jTextFieldIdade.setText("");
               jTextFieldRG.setText("");
               jFormattedTextFieldCpf.setText("");
               jTextFieldDataCad.setText("");
               jFormattedTextFieldDataNasc.setText("  -  -    ");
               jTextFieldMatricula.setText("");
              // jTextFieldIDTurma.setText("");
               jTextFieldIDResponsavel.setText("");
               jTextAreaObservacoes.setText("");
               jTextFieldID.requestFocus();
                
                
            }
              }catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados do aluno.");
        }
    }
    
    

    
    private void carregaComboBoxResponsavel(){
        try{
            ResponsaveisDao dao = new ResponsaveisDao();
            List<Responsavel> lista = dao.buscar("");

            //Atribui o primeiro grupo(0 - selecione)
            this.idresponsavel.add(0);
            this.Descresponsavel.add("-- Selecione --");

            //Atribui os grupos que estão cadastrados no banco
            for(Responsavel responsavel : lista){
                this.idresponsavel.add(responsavel.getId());
                this.Descresponsavel.add(responsavel.getNome());
            }
            
            //Carrega no combobox a lista de grupos 
            jComboBoxResponsavel.setModel(new javax.swing.DefaultComboBoxModel<>(Descresponsavel.toArray(new String[0])));
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar combobox 'grupo de usuario': "+ex);
        }
    }
    private void calcularIdadeEAtualizarCampo() {
        String dataNascimentoStr = jFormattedTextFieldDataNasc.getText();
        
        try {
            // Converta a string da data de nascimento em LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);

            // Calcule a diferença entre a data de nascimento e a data atual
            Period periodo = Period.between(dataNascimento, LocalDate.now());

            // Obtenha o valor da idade
            int idade = periodo.getYears();

            // Preencha o campo de idade com o valor calculado
            jTextFieldIdade.setText(String.valueOf(idade));
        } catch (DateTimeParseException ex) {
            // Se a conversão falhar ou a data estiver inválida, não faça nada
        }
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldDataCad = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldIdade = new javax.swing.JTextField();
        jTextFieldIDResponsavel = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxResponsavel = new javax.swing.JComboBox<>();
        jFormattedTextFieldDataNasc = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldRG = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldMatricula = new javax.swing.JTextField();
        jFormattedTextFieldCpf = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaObservacoes = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterar.png"))); // NOI18N
        jButton1.setText("Alterar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Nome:");

        jTextFieldNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("Id Aluno: ");

        jTextFieldID.setEditable(false);
        jTextFieldID.setEnabled(false);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("Data Nascimento:");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Data Cadastro:");

        jTextFieldDataCad.setEnabled(false);
        jTextFieldDataCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDataCadActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Idade:");

        jTextFieldIdade.setEditable(false);
        jTextFieldIdade.setEnabled(false);
        jTextFieldIdade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdadeActionPerformed(evt);
            }
        });

        jTextFieldIDResponsavel.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Responsável:");

        jComboBoxResponsavel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Selecione --" }));
        jComboBoxResponsavel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBoxResponsavelFocusLost(evt);
            }
        });
        jComboBoxResponsavel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxResponsavelActionPerformed(evt);
            }
        });

        try {
            jFormattedTextFieldDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextFieldIDResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldDataCad))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jFormattedTextFieldDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(44, 44, 44)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldIdade)
                            .addComponent(jComboBoxResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBoxResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jTextFieldIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jFormattedTextFieldDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextFieldDataCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jTextFieldIDResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Dados", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("RG:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("CPF:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Matricula:");

        jTextFieldMatricula.setEditable(false);
        jTextFieldMatricula.setEnabled(false);

        try {
            jFormattedTextFieldCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(23, 23, 23)
                        .addComponent(jTextFieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Documentos", jPanel3);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Obs:");

        jTextAreaObservacoes.setColumns(20);
        jTextAreaObservacoes.setRows(5);
        jScrollPane1.setViewportView(jTextAreaObservacoes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Observações", jPanel1);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/voltar.png"))); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Alterar Aluno");
        jLabel5.setAutoscrolls(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 15, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeActionPerformed

    private void jTextFieldIdadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdadeActionPerformed

    private void jTextFieldDataCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDataCadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDataCadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
        Integer  id = Integer.parseInt(jTextFieldID.getText());
        String nome = jTextFieldNome.getText();
        String  idade = jTextFieldIdade.getText();
        String  rg = jTextFieldRG.getText();
        String  cpf = jFormattedTextFieldCpf.getText();
        String  datanascimento = jFormattedTextFieldDataNasc.getText();
        Integer matricula = Integer.parseInt(jTextFieldMatricula.getText());
        Integer  idresponsavel = Integer.parseInt(jTextFieldIDResponsavel.getText());
        String observacoes = jTextAreaObservacoes.getText();
        //Integer  idturma = Integer.parseInt(jTextFieldIDTurma.getText());
        
        // Aplicando a conversão para um objeto java.util.Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        java.util.Date dataNascimento = dateFormat.parse(datanascimento);
        //validações
        //Integer idturma = Integer.parseInt(jTextFieldIDTurma.getText());
        String[] partesData = datanascimento.split("-");    
        
        int dia = Integer.parseInt(partesData[0]);
        int mes = Integer.parseInt(partesData[1]);
        int ano = Integer.parseInt(partesData[2]);
        // Verifica se o dia é 29, o mês é 02 (fevereiro) e se é um ano bissexto
        boolean isBissexto = (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0));
        
        if (dia == 29 && mes == 2 && !isBissexto) {
        JOptionPane.showMessageDialog(this, "Ano não é bissexto. Fevereiro não tem dia 29 neste ano.");
        jFormattedTextFieldDataNasc.setForeground(Color.RED);
        return; // Encerra a execução do código, pois a data é inválida
    }
        if ((dia == 30 || dia == 31) && mes == 2) {
            JOptionPane.showMessageDialog(this, "Data inválida: Fevereiro não tem dia 30 ou 31.");
            jFormattedTextFieldDataNasc.setForeground(Color.RED);
            return; 
        }
        if(dataNascimento.equals("  -  -    ")){
            JOptionPane.showMessageDialog(this, "informe a data");
            return;
        }
         if(id.equals("")){
            JOptionPane.showMessageDialog(this, "informe o ID.");
            jTextFieldID.requestFocus();
            return;
        }
        if(nome.equals("")){
            JOptionPane.showMessageDialog(this, "informe o nome.");
            jTextFieldNome.requestFocus();
            return;
        }
        if (cpf.equals("")) {
        JOptionPane.showMessageDialog(this, "Informe o CPF.");
        jFormattedTextFieldCpf.requestFocus();
        return;
        } else if (!validarCPF(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido.");
            jFormattedTextFieldCpf.requestFocus();
            return;
        }
        /*if (rg.equals("")) {
        JOptionPane.showMessageDialog(this, "Informe o RG.");
        jTextFieldRG.requestFocus();
        return;
        }if (!validarRG(rg)) {
            JOptionPane.showMessageDialog(this, "RG inválido.");
            jTextFieldRG.requestFocus();
            return;
        }*/
        if(idade.equals("")){
            JOptionPane.showMessageDialog(this, "informe a Idade.");
            jTextFieldIdade.requestFocus();
            return;
        }else if (Integer.parseInt(idade) > 10 || (Integer.parseInt(idade) < 0)) {
            JOptionPane.showMessageDialog(this,"Idade Inválida");
            return;
}
        
        if(datanascimento.equals("  /  /    ")){
            JOptionPane.showMessageDialog(this, "informe a Data de Nascimento.");
            jFormattedTextFieldDataNasc.requestFocus();
            return;
        }
        if(matricula.equals("")){
            JOptionPane.showMessageDialog(this, "informe a Matricula.");
            jTextFieldMatricula.requestFocus();
            return;
        }
//        if (idturma == null || idturma.equals("")) {
//            JOptionPane.showMessageDialog(this, "Informe a turma.");
//            jTextFieldIDTurma.requestFocus();
//            return;
//        }

        if (idresponsavel == null || idresponsavel.equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o responsável.");
            jTextFieldIDResponsavel.requestFocus();
            return;
        }

        
        Aluno u = new Aluno(id, idresponsavel, matricula, nome, idade, rg, cpf, dataNascimento, observacoes,  null);
        
        try {
            AlunoDao dao = new AlunoDao();
            dao.atualizar(u);
//            ListarForm.callback();
            AlunoForm.callback();
            
            JOptionPane.showMessageDialog(this, "Registro atualizado.");
            dispose();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Erro" + e.getMessage());
        }
    } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja mesmo sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBoxResponsavelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxResponsavelFocusLost
           
    }//GEN-LAST:event_jComboBoxResponsavelFocusLost

    private void jComboBoxResponsavelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxResponsavelActionPerformed
        String nomeResponsavel = (String) jComboBoxResponsavel.getSelectedItem();

    if (nomeResponsavel != null && !nomeResponsavel.isEmpty()) {
        try {
            ResponsaveisDao dao = new ResponsaveisDao();
            Integer idResponsavel = dao.obterIdResponsavelPeloNome(nomeResponsavel);

            jTextFieldIDResponsavel.setText(String.valueOf(idResponsavel));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao obter o ID do responsável.");
        }
      }        
    }//GEN-LAST:event_jComboBoxResponsavelActionPerformed
   



     public void mostrarNomeResponsavel(Integer idTexto){
        try {
            Integer nome = idTexto;

            ResponsaveisDao dao = new ResponsaveisDao();
            Responsavel obj = dao.getResponsavel(nome);
            
            if (obj != null) {
                jTextFieldIDResponsavel.setText(obj.getNome());
            } else {
                JOptionPane.showMessageDialog(this, "Registro não encontrado.");
            }
        }catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao consultar registro.");
            jTextFieldIDResponsavel.requestFocus();
        }
    }

    
    private boolean validarCPF(String cpf) {
    // Remova caracteres não numéricos do CPF
    cpf = cpf.replaceAll("[^0-9]", "");

    // Verifique se o CPF tem 11 dígitos
    if (cpf.length() != 11) {
        return false;
    }

    // Verifique se todos os dígitos são iguais (CPF inválido, mas não detectável por outros métodos)
    if (cpf.matches("(\\d)\\1{10}")) {
        return false;
    }

    // Cálculo dos dígitos verificadores
    int[] pesosPrimeiroDigito = {10, 9, 8, 7, 6, 5, 4, 3, 2};
    int[] pesosSegundoDigito = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    int soma = 0;
    for (int i = 0; i < 9; i++) {
        soma += Integer.parseInt(cpf.substring(i, i + 1)) * pesosPrimeiroDigito[i];
    }

    int primeiroDigitoVerificador = 11 - (soma % 11);
    if (primeiroDigitoVerificador >= 10) {
        primeiroDigitoVerificador = 0;
    }

    soma = 0;
    for (int i = 0; i < 10; i++) {
        soma += Integer.parseInt(cpf.substring(i, i + 1)) * pesosSegundoDigito[i];
    }

    int segundoDigitoVerificador = 11 - (soma % 11);
    if (segundoDigitoVerificador >= 10) {
        segundoDigitoVerificador = 0;
    }

    // Verifique se os dígitos verificadores estão corretos
    return cpf.endsWith(String.valueOf(primeiroDigitoVerificador) + segundoDigitoVerificador);
}
    
    private boolean validarRG(String rg) {
    // Remova caracteres não numéricos do RG
    rg = rg.replaceAll("[^0-9]", "");

    // Verifique se o RG tem pelo menos um número válido de dígitos
    if (rg.length() < 4) {
        return false;
    }

    // Verifique se o RG não é sequencial (números iguais)
    if (rg.matches("(\\d)\\1+")) {
        return false;
    }

    // Adicione outras verificações específicas do seu país/região, se necessário

    return true;
}
    
    
    
    public void mostrarAluno(String idTexto){
       try{
           Integer id = Integer.parseInt(idTexto);
           
           AlunoDao dao = new AlunoDao();
           Aluno obj = dao.getAluno(id);
           
               ResponsaveisDao responsavelDao = new ResponsaveisDao();
               Responsavel r = responsavelDao.buscarPorId(obj.getIdresponsavel());
               
//               TurmaDao turmaDao = new TurmaDao();
//               Turma t = turmaDao.buscarPorId(obj.getIdturma());
           
           if (obj != null){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String dataNascimentoFormatada = dateFormat.format(obj.getDatanascimento());           
               
               //Preenche os dados do formulário
               jTextFieldID.setText(Integer.toString(obj.getId()));
               jTextFieldNome.setText(obj.getNome());
               jTextFieldIdade.setText(obj.getIdade());
               jTextFieldRG.setText(obj.getRg());
               jFormattedTextFieldCpf.setText(obj.getCpf());
               jTextFieldDataCad.setText(obj.getDatacadastro());
               jFormattedTextFieldDataNasc.setText(dataNascimentoFormatada);
               jTextFieldMatricula.setText(Integer.toString(obj.getMatricula()));
               jTextFieldIDResponsavel.setText(Integer.toString( r.getId()));
               jTextAreaObservacoes.setText(obj.getObservacoes());
               jComboBoxResponsavel.setSelectedItem(r.getNome());
               
               
           }else{
               
               JOptionPane.showMessageDialog(this, "Registro não encontrado");
               jTextFieldID.setText("");
               jTextFieldNome.setText("");
               jTextFieldIdade.setText("");
               jTextFieldRG.setText("");
               jFormattedTextFieldCpf.setText("");
               jTextFieldDataCad.setText("");
               jFormattedTextFieldDataNasc.setText("");
               jTextFieldMatricula.setText("");
               jTextFieldIDResponsavel.setText("");
               jTextAreaObservacoes.setText("");
               jTextFieldID.requestFocus();
           }
           
       }catch(Exception e){
           e.printStackTrace();
           JOptionPane.showMessageDialog(this, "Erro ao consultar registro");
               jTextFieldID.setText("");
               jTextFieldNome.setText("");
               jTextFieldIdade.setText("");
               jTextFieldRG.setText("");
               jFormattedTextFieldCpf.setText("");
               jTextFieldDataCad.setText("");
               jFormattedTextFieldDataNasc.setText("");
               jTextFieldMatricula.setText("");
               jTextFieldIDResponsavel.setText("");
               jTextAreaObservacoes.setText("");
               jTextFieldID.requestFocus();
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
            java.util.logging.Logger.getLogger(AlunoAlterar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlunoAlterar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlunoAlterar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlunoAlterar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlunoAlterar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxResponsavel;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataNasc;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextAreaObservacoes;
    private javax.swing.JTextField jTextFieldDataCad;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldIDResponsavel;
    private javax.swing.JTextField jTextFieldIdade;
    private javax.swing.JTextField jTextFieldMatricula;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldRG;
    // End of variables declaration//GEN-END:variables
}
