package visaotcc;

import Login.DadosDeSessao;
import controlador.AlunoDao;
import controlador.ResponsaveisDao;
import controlador.TurmaDao;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modelo.Aluno;
import modelo.Responsavel;
import modelo.Turma;
import visaotcc.GerenciarAluno;
import visaotcc.GerenciarResponsaveis;
import visaotcc.Gerenciarusuarios;
import visaotcc.Pagamento;
import visaotcc.Principal;
import visaotcc.Selecionar;
import visaotcc.TurmasPorAluno;

public class CadAluno extends javax.swing.JFrame {
     public Principal listarForm;
     public GerenciarAluno AlunoForm;
     public GerenciarResponsaveis ResponsaveisForm;
     public Gerenciarusuarios UsuariosForm;
     public TurmasPorAluno TurmasAlunoForm;
     public Pagamento PagamentoForm;
   
   // private static final int ANO_ATUAL = 2023; // Ano fixo
    
    public CadAluno() {
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/CC.png")).getImage());
        this.HabilitarCadastrar();
        
        this.setTitle(DadosDeSessao.nomeSistema);
       // this.preencherTabela("");
             
        // Gere um número de ID aleatório com 8 dígitos
        int id = gerarIDUnico();       
        // Preencha o campo de ID com o valor gerado
        jTextFieldMatricula.setText(String.valueOf(id));
        
        this.numerosRG();
        this.data();
        
    }
      
    private int gerarIDUnico() {
    int uniqueID;

    do {
        long currentTime = System.currentTimeMillis() / 1000; // Obtém o tempo atual em segundos

        // Utiliza SecureRandom para gerar um número aleatório mais seguro
        SecureRandom secureRandom = new SecureRandom();
        int randomSuffix = secureRandom.nextInt(Integer.MAX_VALUE); // Garante que o número não seja negativo

        uniqueID = (int) currentTime + randomSuffix;
    } while (uniqueID < 0);

    return uniqueID;
}
    
    private void numerosRG(){
        jTextFieldRG.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char character = e.getKeyChar();
            if (!Character.isDigit(character) && character != '.' && Character.isLetter(character)) {
                e.consume(); // Impede a digitação do caractere
                JOptionPane.showMessageDialog(jTextFieldRG, "Somente números são permitidos.");
            }
        }
    });
    }
private void data(){
    //Data de nacimento
        jFormattedTextFieldDatanasc.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                calcularIdadeEAtualizarCampo();
            }
        });
}
 private void calcularIdadeEAtualizarCampo() {
        String dataNascimentoStr = jFormattedTextFieldDatanasc.getText();
        
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

        jTextFieldIDResponsavel = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldMatricula = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jFormattedTextFieldDatanasc = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldIdade = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldResponsavelNome = new javax.swing.JTextField();
        jButtonLupa1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldRG = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextFieldCpf = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaObservacoes = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButtonCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTextFieldIDResponsavel.setEnabled(false);
        jTextFieldIDResponsavel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIDResponsavelFocusLost(evt);
            }
        });
        jTextFieldIDResponsavel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDResponsavelActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Nome:");

        jTextFieldNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("Matricula:");

        jTextFieldMatricula.setEditable(false);
        jTextFieldMatricula.setEnabled(false);
        jTextFieldMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMatriculaActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("Data Nascimento:");

        try {
            jFormattedTextFieldDatanasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Idade:");

        jTextFieldIdade.setEditable(false);
        jTextFieldIdade.setEnabled(false);
        jTextFieldIdade.setFocusable(false);
        jTextFieldIdade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdadeActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Responsável:");

        jTextFieldResponsavelNome.setEnabled(false);

        jButtonLupa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar-pequeno.png"))); // NOI18N
        jButtonLupa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLupa1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(jFormattedTextFieldDatanasc, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextFieldResponsavelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLupa1)))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(jTextFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonLupa1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTextFieldResponsavelNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(jFormattedTextFieldDatanasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19)
                        .addComponent(jTextFieldIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(104, 104, 104))
        );

        jTabbedPane1.addTab("Dados do Aluno", jPanel2);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("RG:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("CPF:");

        try {
            jFormattedTextFieldCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Documentos", jPanel1);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Obs:");

        jTextAreaObservacoes.setColumns(20);
        jTextAreaObservacoes.setRows(5);
        jScrollPane1.setViewportView(jTextAreaObservacoes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Observações", jPanel3);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("CADASTRO DE ALUNO");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/voltar.png"))); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonCadastrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonCadastrar.setForeground(new java.awt.Color(0, 153, 0));
        jButtonCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        jButtonCadastrar.setText("CADASTRAR");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldIDResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIDResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldIdadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdadeActionPerformed

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
              
            
        Integer matricula = Integer.parseInt(jTextFieldMatricula.getText());
        String nome = jTextFieldNome.getText();
        String cpf = jFormattedTextFieldCpf.getText();
        String rg = jTextFieldRG.getText();
        String idade = jTextFieldIdade.getText();      
        String datanascimento = jFormattedTextFieldDatanasc.getText();
        String idresponsavel = jTextFieldIDResponsavel.getText();
        String observacoes = jTextAreaObservacoes.getText();

        // Aplicando a conversão para um objeto java.util.Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        try {
        java.util.Date dataNascimento = dateFormat.parse(datanascimento);
        // Agora você tem a data no formato java.util.Date 
        
        String[] partesData = datanascimento.split("-"); 

        int dia = Integer.parseInt(partesData[0]);
        int mes = Integer.parseInt(partesData[1]);
        int ano = Integer.parseInt(partesData[2]);

        boolean isBissexto = (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0));

        // Validar dias para meses que têm apenas 30 ou 31 dias
        if ((dia == 31 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) ||
            (dia > 29 && mes == 2) || // Fev. não pode ter mais que 29 dias
            (dia == 29 && mes == 2 && !isBissexto) || dia > 31)
                 {
            JOptionPane.showMessageDialog(this, "Data inválida para o mês selecionado.");
            jFormattedTextFieldDatanasc.setForeground(Color.RED);
            return;
        }

          if(idresponsavel.equals("")){             
            JOptionPane.showMessageDialog(this, "informe o Resposável.");
            jTextFieldIDResponsavel.requestFocus();
            return;
        }   
         if(matricula.equals("")){
            JOptionPane.showMessageDialog(this, "informe o ID.");
            jTextFieldMatricula.requestFocus();
            return;
        }
        if(nome.equals("")){
            JOptionPane.showMessageDialog(this, "informe o nome.");
            jTextFieldNome.requestFocus();
            return;
        }
        // Adiciona a validação do mês aqui
        if (mes < 1 || mes > 12) {
            JOptionPane.showMessageDialog(this, "Mês inválido. Informe um mês entre 1 e 12.");
            jFormattedTextFieldDatanasc.setForeground(Color.RED);
            return;
    }
        
        try {
            AlunoDao dao = new AlunoDao();

            if (dao.rgJaExiste(rg)) {
                JOptionPane.showMessageDialog(this, "RG já existente no Banco.");
                jTextFieldRG.setForeground(Color.RED);

                return; 
            } 
              } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar aluno: " + e.getMessage());
}
        
         try {
            AlunoDao dao = new AlunoDao();
            if (cpf.equals("")) {
                JOptionPane.showMessageDialog(this, "Informe o CPF.");
                jFormattedTextFieldCpf.requestFocus();
                return;
                } 
                 if (!validarCPF(cpf)) {
                    JOptionPane.showMessageDialog(this, "CPF inválido.");
                    jFormattedTextFieldCpf.requestFocus();
                    return;
                }else if (dao.cpfJaExiste(cpf)) {
                JOptionPane.showMessageDialog(this, "CPF já existente no Banco.");
                jFormattedTextFieldCpf.setForeground(Color.RED);

                return; 
            } 
              } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar aluno: " + e.getMessage());
}
          try {
            AlunoDao dao = new AlunoDao();
                if (dao.MatriculaJaExiste(matricula)) {
                JOptionPane.showMessageDialog(this, "Matrícula já existente no Banco.");
                jFormattedTextFieldCpf.setForeground(Color.RED);

                return; 
            } 
              } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar aluno: " + e.getMessage());
}
          
        if (idade.equals("")) {
            JOptionPane.showMessageDialog(this, "Informe a Idade.");
            jTextFieldIdade.requestFocus();
            return;
        } else if (Integer.parseInt(idade) > 10 || (Integer.parseInt(idade) < 0)) {
            JOptionPane.showMessageDialog(this,"Idade Inválida");
            return;
}
              
         
           
              
        Aluno u = new Aluno( Integer.parseInt(idresponsavel), matricula, nome, idade, rg, cpf, dataNascimento, observacoes,  null);
        
        try {
        AlunoDao alunoDao = new AlunoDao();
        //TurmaDao turmaDao = new TurmaDao();

        
        alunoDao.inserir(u);

        AlunoForm.callback();
        JOptionPane.showMessageDialog(this, "Registro inserido.");
        this.dispose();
    } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Erro" + e.getMessage());
        }
    }catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
}
        
        
    }//GEN-LAST:event_jButtonCadastrarActionPerformed
       
    
    public void HabilitarCadastrar() {
    jButtonCadastrar.setEnabled(false); // Começa desativado

    // Crie um DocumentListener para monitorar os campos que você deseja
        DocumentListener documentListener = new DocumentListener() {
        private void checkFields() {
            String matriculaText = jTextFieldMatricula.getText();
            String nome = jTextFieldNome.getText();
            String cpf = jFormattedTextFieldCpf.getText();
            String datanascimento = jFormattedTextFieldDatanasc.getText();
            String idresponsavelText = jTextFieldIDResponsavel.getText();
            String idade =jTextFieldIdade.getText();

            // Verifique se algum dos campos obrigatórios está vazio ou inválido
            if (matriculaText.isEmpty() || nome.isEmpty() || idade.isEmpty() || cpf.equals("   .   .   -  ") ||
                    datanascimento.equals("  -  -    ") || idresponsavelText.isEmpty()) {
                jButtonCadastrar.setEnabled(false);
                return;
            }

            try {
                int matricula = Integer.parseInt(matriculaText);
                int idresponsavel = Integer.parseInt(idresponsavelText);

                // Verifique se os campos não são iguais a zero e se os campos adicionais estão preenchidos corretamente
                boolean dateValid = datanascimento.matches("\\d{2}-\\d{2}-\\d{4}");
                boolean cpfFilled = cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"); // Verifica se todos os dígitos do CPF foram preenchidos

                jButtonCadastrar.setEnabled(idresponsavel != 0 && dateValid && cpfFilled);
            } catch (NumberFormatException e) {
                // Em caso de erro na conversão, desative o botão
                jButtonCadastrar.setEnabled(false);
            }
        }

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
    jTextFieldMatricula.getDocument().addDocumentListener(documentListener);
    jTextFieldNome.getDocument().addDocumentListener(documentListener);
    jFormattedTextFieldCpf.getDocument().addDocumentListener(documentListener);
    jFormattedTextFieldDatanasc.getDocument().addDocumentListener(documentListener);
    jTextFieldIDResponsavel.getDocument().addDocumentListener(documentListener);
    jTextFieldIdade.getDocument().addDocumentListener(documentListener);
}



    
    
   public void mostrarResponsavel(Integer idTexto){
        try {
            Integer id = idTexto;

            ResponsaveisDao dao = new ResponsaveisDao();
            Responsavel obj = dao.getResponsavel(id);
            
            if (obj != null) {
                jTextFieldResponsavelNome.setText(obj.getNome());
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

    // Verifique se o primeiro caractere é um dígito válido
    char firstChar = rg.charAt(0);
    if (!Character.isDigit(firstChar) || firstChar == '0' || firstChar == '1') {
        return false;
    }

    // Verifique se o RG não é sequencial (números iguais)
    if (rg.matches("(\\d)\\1+")) {
        return false;
    }
    
    return true;
}

    private void jTextFieldNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeActionPerformed

    private void jTextFieldMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMatriculaActionPerformed

    private void jButtonLupa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLupa1ActionPerformed

        new Selecionar(
            jTextFieldIDResponsavel,
            jTextFieldResponsavelNome,
            "responsavel",
            "nome"
        ).setVisible(true);
    }//GEN-LAST:event_jButtonLupa1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       int resposta = JOptionPane.showConfirmDialog(this, "Deseja mesmo sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
        this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextFieldIDResponsavelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDResponsavelFocusLost
        /* String texto = jTextFieldIDResponsavel.getText();
        if (!texto.isEmpty()) {
            try {
                Integer id = Integer.valueOf(texto);
                this.mostrarResponsavel(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro, número não válido.");
                // Exibir uma mensagem de erro, por exemplo
            }
        } else {
            if(texto.equals("")){
                JOptionPane.showMessageDialog(this, "Informe o ID do responsável.");
                jTextFieldIDResponsavel.requestFocus();
                return;
            }
        }*/
    }//GEN-LAST:event_jTextFieldIDResponsavelFocusLost

    private void jTextFieldIDResponsavelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIDResponsavelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDResponsavelActionPerformed

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
            java.util.logging.Logger.getLogger(CadAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadAluno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonLupa1;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldDatanasc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextAreaObservacoes;
    private javax.swing.JTextField jTextFieldIDResponsavel;
    private javax.swing.JTextField jTextFieldIdade;
    private javax.swing.JTextField jTextFieldMatricula;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldRG;
    private javax.swing.JTextField jTextFieldResponsavelNome;
    // End of variables declaration//GEN-END:variables
}
