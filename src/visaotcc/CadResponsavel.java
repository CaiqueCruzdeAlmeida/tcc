/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visaotcc;

import Login.DadosDeSessao;
import controlador.ResponsaveisDao;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modelo.Responsavel;

/**
 *
 * @author caiqu
 */
public class CadResponsavel extends javax.swing.JFrame {
    public Principal listarForm;
    public GerenciarAluno AlunoForm;
    public GerenciarResponsaveis ResponsaveisForm;
    public Gerenciarusuarios UsuariosForm;
    public TurmasPorAluno TurmasAlunoForm;
    public Pagamento PagamentoForm;
    /**
     * Creates new form CadResponsavel
     */
    public CadResponsavel() {
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/CC.png")).getImage());
        this.setTitle(DadosDeSessao.nomeSistema);
           int id = gerarIDUnico();
           // Preencha o campo de ID com o valor gerado
           //jTextFieldID.setText(String.valueOf(id));
      this.HabilitarCadastrar();
    this.validarnumeros();
   }
    private void validarnumeros(){
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
      jTextFieldCep.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char character = e.getKeyChar();
            if (!Character.isDigit(character) && character != '.' && Character.isLetter(character)) {
                e.consume(); // Impede a digitação do caractere
                JOptionPane.showMessageDialog(jTextFieldCep, "Somente números são permitidos.");
            }
        }
    });
        jTextFieldNumero.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char character = e.getKeyChar();
            if (!Character.isDigit(character) && character != '.' && Character.isLetter(character)) {
                e.consume(); // Impede a digitação do caractere
                JOptionPane.showMessageDialog(jTextFieldNumero, "Somente números são permitidos.");
            }
        }
    });
    }
    public void HabilitarCadastrar() {
    jButtonCadastrar.setEnabled(false); // Começa desativado

    // Crie um ActionListener para monitorar as seleções nos JComboBoxes
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Não faz nada aqui, pois não queremos que os JComboBoxes influenciem a ativação do botão
        }
    };

    // Adicione o ActionListener aos JComboBoxes
    jComboBoxEstado.addActionListener(actionListener);
    jComboBoxGenero.addActionListener(actionListener);

    // Crie um método separado para verificar os campos
    Runnable checkFields = () -> {
        String nome = jTextFieldNome.getText();
        String cpf = jFormattedTextFieldCpf.getText();
        String telefone = jFormattedTextFieldTelefone.getText();
        String cep = jTextFieldCep.getText();
        String numero = jTextFieldNumero.getText();
        String rua = jTextFieldRua.getText();
        String cidade = jTextFieldCidade.getText();
        String estado = (String) jComboBoxEstado.getSelectedItem();
        String genero = (String) jComboBoxGenero.getSelectedItem();

        // Verifica se todos os campos estão preenchidos corretamente
        boolean camposPreenchidos = !nome.isEmpty() && cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")
                && telefone.matches("\\(\\d{2}\\)\\s+\\d{4}-\\d{4}") // Verifica se todos os dígitos do telefone foram preenchidos
                && !cep.equals("     -   ") && !numero.isEmpty()
                && !rua.isEmpty() && !cidade.isEmpty()
                && estado != null && genero != null;

        // Habilita o botão se todos os campos estiverem preenchidos
        jButtonCadastrar.setEnabled(camposPreenchidos);
    };

    // Adicione um FocusListener aos JComboBoxes e chame o método checkFields
    FocusListener focusListener = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            checkFields.run(); // Verifica os campos quando os ComboBoxes ganham foco
        }

        @Override
        public void focusLost(FocusEvent e) {
            // Não é necessário fazer nada quando perdem o foco
        }
    };

    // Adicione o FocusListener aos JComboBoxes
    jComboBoxEstado.addFocusListener(focusListener);
    jComboBoxGenero.addFocusListener(focusListener);

    // Adicione o DocumentListener aos campos relevantes
    DocumentListener documentListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            checkFields.run();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            checkFields.run();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            checkFields.run();
        }
    };

    jTextFieldNome.getDocument().addDocumentListener(documentListener);
    jFormattedTextFieldCpf.getDocument().addDocumentListener(documentListener);
    jFormattedTextFieldTelefone.getDocument().addDocumentListener(documentListener);
    jTextFieldCep.getDocument().addDocumentListener(documentListener);
    jTextFieldNumero.getDocument().addDocumentListener(documentListener);
    jTextFieldRua.getDocument().addDocumentListener(documentListener);
    jTextFieldCidade.getDocument().addDocumentListener(documentListener);
}





    private int gerarIDUnico() {
        // Defina o limite inferior e superior para o intervalo desejado (8 dígitos)
        int limiteInferior = 10000000;
        int limiteSuperior = 99999999;

        // Crie uma instância de Random
        Random random = new Random();

        // Gere um número aleatório dentro do intervalo desejado
        return limiteInferior + random.nextInt(limiteSuperior - limiteInferior + 1);
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
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxGenero = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextFieldTelefone = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextFieldCpf = new javax.swing.JFormattedTextField();
        jTextFieldRG = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldCep = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldRua = new javax.swing.JTextField();
        jTextFieldNumero = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldCidade = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox<>();
        jButtonCadastrar = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("CADASTRO DE RESPONSAVEL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Nome:");

        jLabel4.setText("Gênero:");

        jComboBoxGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Masculino", "Feminino" }));

        jLabel5.setText("Telefone:");

        try {
            jFormattedTextFieldTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(1, 1, 1)
                        .addComponent(jComboBoxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldNome)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jFormattedTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados Gerais", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("RG:");

        jLabel7.setText("CPF:");

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
                .addGap(38, 38, 38)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Documentos", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setText("CEP:");

        jLabel9.setText("Rua:");

        jLabel11.setText("N°");

        jLabel12.setText("Cidade:");

        jLabel13.setText("Estado:");

        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "São Paulo", "Paraná" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCidade)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Endereço", jPanel4);

        jButtonCadastrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonCadastrar.setForeground(new java.awt.Color(0, 153, 0));
        jButtonCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        jButtonCadastrar.setText("CADASTRAR");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/voltar.png"))); // NOI18N
        jButton8.setText("CANCELAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton8)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCadastrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrar)
                    .addComponent(jButton8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        
        
        String nome = jTextFieldNome.getText();
        String cpf = jFormattedTextFieldCpf.getText();
        String rg = jTextFieldRG.getText();
        String telefone = jFormattedTextFieldTelefone.getText();
        String cep = jTextFieldCep.getText();
        String numero = jTextFieldNumero.getText();
        String rua = jTextFieldRua.getText();
        String cidade = jTextFieldCidade.getText();
        //String logradouro = jTextFieldLogradouro.getText();
        String estado = (String) jComboBoxEstado.getSelectedItem();
        String genero = (String) jComboBoxGenero.getSelectedItem();
        
        //validações
         
         if(nome.equals("")){
            JOptionPane.showMessageDialog(this, "informe o nome.");
            jTextFieldNome.requestFocus();
            return;
        }
         if(telefone.equals("")){
            JOptionPane.showMessageDialog(this, "informe o Telefone.");
            jFormattedTextFieldTelefone.requestFocus();
            return;
        }else if(!ValidarTelefone(telefone)){
            JOptionPane.showMessageDialog(this, "Telefone invalido !");
            jFormattedTextFieldTelefone.setForeground(Color.RED);
            jFormattedTextFieldTelefone.requestFocus();
            return;
        }
         if(cep.equals("")){
            JOptionPane.showMessageDialog(this, "informe o CEP.");
            jTextFieldCep.requestFocus();
            return;
        }
         if(numero.equals("")){
            JOptionPane.showMessageDialog(this, "informe o Número da casa.");
            jTextFieldNumero.requestFocus();
            return;
        }
         if(rua.equals("")){
            JOptionPane.showMessageDialog(this, "informe a Rua.");
            jTextFieldRua.requestFocus();
            return;
        }
         if(estado.equals("Selecione")){
            JOptionPane.showMessageDialog(this, "informe o Estado.");
            jComboBoxEstado.requestFocus();
            return;
        }
         if(genero.equals("Selecione")){
            JOptionPane.showMessageDialog(this, "informe o Genero.");
            jComboBoxGenero.requestFocus();
            return;
        }
         
         try {
        ResponsaveisDao dao = new ResponsaveisDao();
         /*if (rg.equals("")) {
        JOptionPane.showMessageDialog(this, "Informe o RG.");
        jFormattedTextFieldRG.requestFocus();
        return;
        } else if (!validarRG(rg)) {
            JOptionPane.showMessageDialog(this, "RG inválido.");
            jFormattedTextFieldRG.requestFocus();
            return;
        } */if (dao.rgJaExiste(rg)) {
        JOptionPane.showMessageDialog(this, "RG já existente no Banco.");
        return; // Interrompe o cadastro caso o RG já exista
    } 
      } catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Erro ao cadastrar aluno: " + e.getMessage());
}
         try {
        ResponsaveisDao dao = new ResponsaveisDao();
         if (cpf.equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o CPF.");
            jFormattedTextFieldCpf.requestFocus();
            return;
        } else if (!validarCPF(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido.");
            jFormattedTextFieldCpf.requestFocus();
            return;
        }else if (dao.cpfJaExiste(cpf)) {
        JOptionPane.showMessageDialog(this, "CPF já existente no Banco..");
        return; // Interrompe o cadastro caso o RG já exista
    } 
      } catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Erro ao cadastrar aluno: " + e.getMessage());
}
         
         Responsavel u = new Responsavel(nome, cpf, rg, telefone, cep, numero, rua, cidade, estado, genero, null);
        
            try {
                ResponsaveisDao dao = new ResponsaveisDao();
                dao.inserir(u);
                ResponsaveisForm.callback();
                
                JOptionPane.showMessageDialog(this, "registro inserido.");
                this.dispose();
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, "Erro" + e.getMessage());
        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja mesmo sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_jButton8ActionPerformed
  
    private static boolean ValidarTelefone(String telefone){
        String regex = "^\\(\\d{2}\\)\\s\\d{4}-\\d{4}$";
        
        Pattern parttern = Pattern.compile(regex);
        Matcher matcher = parttern.matcher(telefone);
        
        return matcher.matches();
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

    return true;
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
            java.util.logging.Logger.getLogger(CadResponsavel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadResponsavel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadResponsavel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadResponsavel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadResponsavel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JComboBox<String> jComboBoxEstado;
    private javax.swing.JComboBox<String> jComboBoxGenero;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldCep;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextFieldRG;
    private javax.swing.JTextField jTextFieldRua;
    // End of variables declaration//GEN-END:variables
}
