/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visaotcc;

import Login.DadosDeSessao;
import Login.telaLogin;
import controlador.UsuarioDao;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modelo.Usuario;
import visaotcc.Gerenciarusuarios;
import visaotcc.Principal;
import visaotcc.Principal;


/**
 *
 * @author caiqu
 */
public class CadUsuario extends javax.swing.JFrame {
    public Principal listarForm;
    public Gerenciarusuarios UsuariosForm;
    public telaLogin loginForm;
    /**
     * Creates new form CadResponsavel
     */
    public CadUsuario() {                    
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/CC.png")).getImage());
        this.setTitle(DadosDeSessao.nomeSistema);
        
        this.HabilitarCadastrar();
        
        this.numerosRG();
        
        
        
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextFieldTelefone = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextFieldCpf = new javax.swing.JFormattedTextField();
        jTextFieldRG = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTextField1Email = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPasswordFieldConfSenha = new javax.swing.JPasswordField();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldPalavra1 = new javax.swing.JTextField();
        jTextFieldPalavra2 = new javax.swing.JTextField();
        jButtonCadastrar = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextFieldPergunta2 = new javax.swing.JTextField();
        jTextFieldPergunta1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Nome:");

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jFormattedTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 367, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNome, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)))
                .addContainerGap())
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
                    .addComponent(jLabel5)
                    .addComponent(jFormattedTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados Gerais", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("RG:");

        jLabel7.setText("CPF:");

        try {
            jFormattedTextFieldCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###-###.##")));
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
                .addComponent(jTextFieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
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

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setText("Email:");

        jLabel8.setText("Senha:");

        jPasswordFieldConfSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldConfSenhaActionPerformed(evt);
            }
        });

        jLabel4.setText("Confirmar Senha:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1Email, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPasswordFieldConfSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(jPasswordFieldSenha))
                .addContainerGap(169, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel8)
                    .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordFieldConfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados de Login", jPanel5);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Pergunta 1 : Qual sua Cor Favorita?");

        jLabel9.setText("Pergunta 2 : Qual sua Comida Favorita?");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldPalavra1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPalavra2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPalavra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldPalavra2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Perguntas Secretas", jPanel4);

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("CADASTRO DE USUARIO");

        jTextFieldPergunta2.setText("Qual sua comida favorita?");
        jTextFieldPergunta2.setEnabled(false);

        jTextFieldPergunta1.setText("Qual sua cor favorita?");
        jTextFieldPergunta1.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTextFieldPergunta1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jTextFieldPergunta2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldPergunta2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextFieldPergunta1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordFieldConfSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldConfSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldConfSenhaActionPerformed
public void HabilitarCadastrar() {
    jButtonCadastrar.setEnabled(false); // Começa desativado

    // Crie um método separado para verificar todos os campos
    Runnable checkFields = () -> {
        String nome = jTextFieldNome.getText();
        String telefone = jFormattedTextFieldTelefone.getText();
        //String rg = jTextFieldRG.getText();
        String cpf = jFormattedTextFieldCpf.getText();
        String email = jTextField1Email.getText();
        String senha = new String(jPasswordFieldSenha.getPassword());
        String confirmacao = new String(jPasswordFieldConfSenha.getPassword());
        String resposta1 = jTextFieldPalavra1.getText();
        String resposta2 = jTextFieldPalavra2.getText();
        String pergunta1 = jTextFieldPergunta1.getText();
        String pergunta2 = jTextFieldPergunta2.getText();

        // Verifica se todos os campos estão preenchidos corretamente
        boolean camposPreenchidos = !nome.isEmpty() && !telefone.equals("(   )     -    ")
                && /*!rg.isEmpty() &&*/ !cpf.equals("   .   .   -  ")
                && !email.isEmpty() && !senha.isEmpty() && !confirmacao.isEmpty() && !resposta1.isEmpty() && !resposta2.isEmpty() && !pergunta1.isEmpty() && !pergunta2.isEmpty();

        // Habilita o botão se todos os campos estiverem preenchidos
        jButtonCadastrar.setEnabled(camposPreenchidos);
    };

    // Adicione um FocusListener aos campos e chame o método checkFields
    FocusListener focusListener = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            checkFields.run(); // Verifica os campos quando ganham foco
        }

        @Override
        public void focusLost(FocusEvent e) {
            // Não é necessário fazer nada quando perdem o foco
        }
    };

    // Adicione o FocusListener aos campos
    jTextFieldNome.addFocusListener(focusListener);
    jFormattedTextFieldTelefone.addFocusListener(focusListener);
    //jTextFieldRG.addFocusListener(focusListener);
    jFormattedTextFieldCpf.addFocusListener(focusListener);
    jTextField1Email.addFocusListener(focusListener);
    jPasswordFieldSenha.addFocusListener(focusListener);
    jPasswordFieldConfSenha.addFocusListener(focusListener);
    jTextFieldPalavra1.addFocusListener(focusListener);
    jTextFieldPalavra2.addFocusListener(focusListener);
    jTextFieldPergunta1.addFocusListener(focusListener);
    jTextFieldPergunta2.addFocusListener(focusListener);

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
    jFormattedTextFieldTelefone.getDocument().addDocumentListener(documentListener);
    //jTextFieldRG.getDocument().addDocumentListener(documentListener);
    jFormattedTextFieldCpf.getDocument().addDocumentListener(documentListener);
    jTextField1Email.getDocument().addDocumentListener(documentListener);
    jPasswordFieldSenha.getDocument().addDocumentListener(documentListener);
    jPasswordFieldConfSenha.getDocument().addDocumentListener(documentListener);
    jTextFieldPalavra1.getDocument().addDocumentListener(documentListener);
    jTextFieldPalavra2.getDocument().addDocumentListener(documentListener);
    jTextFieldPergunta1.getDocument().addDocumentListener(documentListener);
    jTextFieldPergunta2.getDocument().addDocumentListener(documentListener);

    // Chame checkFields para verificar o estado inicial dos campos
    checkFields.run();
}





 
    
    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
       
        //Integer id = Integer.parseInt(jTextFieldId.getText());
        String nome = jTextFieldNome.getText();
        String telefone = jFormattedTextFieldTelefone.getText();
        String rg = jTextFieldRG.getText();
        String cpf = jFormattedTextFieldCpf.getText();
        String email = jTextField1Email.getText();
        String senha = new String(jPasswordFieldSenha.getPassword());
        String confirmacao = new String(jPasswordFieldConfSenha.getPassword());
        String resposta1 = jTextFieldPalavra1.getText();
        String resposta2 =jTextFieldPalavra2.getText();
        String pergunta1 =jTextFieldPergunta1.getText();
        String pergunta2 =jTextFieldPergunta2.getText();
        
        if (nome.equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o nome.");
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
        try {
        UsuarioDao dao = new UsuarioDao();
        /*if (rg.equals("")) {
        JOptionPane.showMessageDialog(this, "Informe o RG.");
        jFormattedTextFieldRG.requestFocus();
        return;
        } else if (!validarRG(rg)) {
            JOptionPane.showMessageDialog(this, "RG inválido.");
            jFormattedTextFieldRG.requestFocus();
            return;
        }*/if (dao.rgJaExiste(rg)) {
        JOptionPane.showMessageDialog(this, "RG já existente no Banco.");
        return; // Interrompe o cadastro caso o RG já exista
    } 
      } catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Erro ao cadastrar aluno: " + e.getMessage());
}
        try {
        UsuarioDao dao = new UsuarioDao();
        if (cpf.equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o CPF.");
            jFormattedTextFieldCpf.requestFocus();
            return;
        } else if (!validarCPF(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido.");
            jFormattedTextFieldCpf.requestFocus();
            return;
        }else if (dao.cpfJaExiste(cpf)) {
        JOptionPane.showMessageDialog(this, "CPF já existente no banco.");
        return; // Interrompe o cadastro caso o RG já exista
    } 
      } catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Erro ao cadastrar aluno: " + e.getMessage());
}
        
        if (email.equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o email.");
            jTextField1Email.requestFocus();
            return;
        }else if(!ValidarEmail(email)){
            JOptionPane.showMessageDialog(this, "Email invalido !");
            jTextField1Email.setForeground(Color.RED);
            jTextField1Email.requestFocus();
            return;
        }
        if (senha.equals("")) {
            JOptionPane.showMessageDialog(this, "Informe a senha.");
            jPasswordFieldSenha.requestFocus();
            return;
        }
        if (confirmacao.equals("")) {
            JOptionPane.showMessageDialog(this, "Informe a confirmação de senha.");
            jPasswordFieldConfSenha.requestFocus();
            return;
        }
        if (!senha.equals(confirmacao)) {
            JOptionPane.showMessageDialog(this, "As senhas não conferem.");
            jPasswordFieldSenha.setText("");
            jPasswordFieldConfSenha.setText("");
            jPasswordFieldSenha.requestFocus();
            return;
        }
         
        

        Usuario u = new Usuario(nome, telefone, rg, cpf, senha, email, resposta1, resposta2, pergunta1, pergunta2, null);
        
            try {
                UsuarioDao dao = new UsuarioDao();
                dao.inserir(u);
                UsuariosForm.callback();

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

    private static boolean ValidarEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        
        Pattern parttern = Pattern.compile(regex);
        Matcher matcher = parttern.matcher(email);
        
        return matcher.matches();
    }
    private static boolean ValidarTelefone(String telefone){
        String regex = "^\\(\\d{2}\\)\\s\\d{4}-\\d{4}$";
        
        Pattern parttern = Pattern.compile(regex);
        Matcher matcher = parttern.matcher(telefone);
        
        return matcher.matches();
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
    }return true;
}
    
    public static void main(String args[]) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadUsuario().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordFieldConfSenha;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1Email;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldPalavra1;
    private javax.swing.JTextField jTextFieldPalavra2;
    private javax.swing.JTextField jTextFieldPergunta1;
    private javax.swing.JTextField jTextFieldPergunta2;
    private javax.swing.JTextField jTextFieldRG;
    // End of variables declaration//GEN-END:variables
}