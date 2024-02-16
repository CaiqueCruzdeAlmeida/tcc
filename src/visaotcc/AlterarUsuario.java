/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visaotcc;

import Login.DadosDeSessao;
import controlador.AlunoDao;
import controlador.UsuarioDao;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modelo.Usuario;
import visaotcc.Gerenciarusuarios;
import visaotcc.Gerenciarusuarios;
import visaotcc.Principal;
import visaotcc.Principal;

/**
 *
 * @author caiqu
 */
public class AlterarUsuario extends javax.swing.JFrame {
    public Principal ListarForm;
    public Gerenciarusuarios UsuariosForm;
    /**
     * Creates new form ResponsavelAlterar
     */
    public AlterarUsuario() {
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/CC.png")).getImage());
        this.setTitle(DadosDeSessao.nomeSistema);
        this.HabilitarCadastrar();
        //getRootPane().setDefaultButton(jButton1);
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
    jTextField1Email.getDocument().addDocumentListener(documentListener);
    jTextFieldNome.getDocument().addDocumentListener(documentListener);
    jFormattedTextFieldTelefone.getDocument().addDocumentListener(documentListener);
    jPasswordFieldSenha.getDocument().addDocumentListener(documentListener);
    jPasswordFieldConfSenha.getDocument().addDocumentListener(documentListener);
    jTextFieldPalavra1.getDocument().addDocumentListener(documentListener);
    jTextFieldPalavra2.getDocument().addDocumentListener(documentListener);
    checkFields();
}
public void checkFields() {
    String cpf = jFormattedTextFieldCpf.getText();
    String email = jTextField1Email.getText();
    String telefone = jFormattedTextFieldTelefone.getText();
    String nome = jTextFieldNome.getText();
    String senha = new String(jPasswordFieldSenha.getPassword());
    String confSenha = new String(jPasswordFieldConfSenha.getPassword());
    String palavra1 = jTextFieldPalavra1.getText();
    String palavra2 = jTextFieldPalavra2.getText();

    // Verifique se algum dos campos obrigatórios está vazio ou inválido
    if (cpf.equals("   .   .   -  ") || telefone.equals("()     -    ") || email.isEmpty() || senha.isEmpty() || confSenha.isEmpty() || nome.isEmpty() || palavra1.isEmpty() || palavra2.isEmpty()) {
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

    // Verifique o telefone usando uma expressão regular básica
    if (!telefone.matches("\\(\\d{2}\\)\\s*\\d{4,5}-\\d{4}")) {
        jButton1.setEnabled(false);
        return;
    }

    // Outras verificações que você desejar realizar, como a validade do e-mail, por exemplo.

    // Agora, considere habilitar o botão apenas se as senhas coincidem
    jButton1.setEnabled(senha.equals(confSenha));
}


   public void mostrarUsuario(String idTexto){
       try{
           Integer id = Integer.parseInt(idTexto);
           
           UsuarioDao dao = new UsuarioDao();
           Usuario obj = dao.getUsuario(id);
           
           if (obj != null){
               //Preenche os dados do formulário
               jTextFieldID.setText(Integer.toString(obj.getId()));
               jTextFieldNome.setText(obj.getNome());
               jTextField1Email.setText(obj.getEmail());
               jPasswordFieldSenha.setText(obj.getSenha());
               jPasswordFieldConfSenha.setText(obj.getSenha());
               jFormattedTextFieldTelefone.setText(obj.getTelefone());
               jTextFieldRG.setText(obj.getRg());
               jFormattedTextFieldCpf.setText(obj.getCpf());
               jTextFieldPalavra1.setText(obj.getResposta1());
               jTextFieldPalavra2.setText(obj.getResposta2());
           }else{
               JOptionPane.showMessageDialog(this, "Registro não emcontrado");               
               jTextFieldID.setText("");
               jTextFieldNome.setText("");
               jTextField1Email.setText("");
               jPasswordFieldConfSenha.setText(obj.getSenha());
               jPasswordFieldSenha.setText(obj.getSenha());
               jFormattedTextFieldTelefone.setText(obj.getTelefone());
               jTextFieldRG.setText("");
               jFormattedTextFieldCpf.setText("");
               jTextFieldPalavra1.setText("");
               jTextFieldPalavra2.setText("");
               
               jTextFieldNome.requestFocus();
           }
           
       }catch(Exception e){
           JOptionPane.showMessageDialog(this, "Erro ao consultar registro");              
               jTextFieldID.setText("");   
               jTextFieldNome.setText("");
               jTextField1Email.setText("");
               jPasswordFieldConfSenha.setText("");
               jPasswordFieldSenha.setText("");
               jFormattedTextFieldTelefone.setText("");
               jTextFieldRG.setText("");
               jFormattedTextFieldCpf.setText("");
               jTextFieldPalavra1.setText("");
               jTextFieldPalavra2.setText("");
               jTextFieldNome.requestFocus();
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextFieldTelefone = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextFieldCpf = new javax.swing.JFormattedTextField();
        jTextFieldRG = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextField1Email = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jPasswordFieldConfSenha = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldPalavra1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldPalavra2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Nome:");

        jLabel3.setText("Telefone:");

        try {
            jFormattedTextFieldTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setText("ID");

        jTextFieldID.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNome))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 481, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados Gerais", jPanel2);

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
                .addGap(161, 161, 161)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Documentos", jPanel3);

        jLabel14.setText("Email:");

        jLabel8.setText("Senha:");

        jPasswordFieldSenha.setText("jPasswordField1");

        jLabel4.setText("Confirmar Senha:");

        jPasswordFieldConfSenha.setText("jPasswordField1");
        jPasswordFieldConfSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldConfSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1Email, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPasswordFieldConfSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(jPasswordFieldSenha))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel8)
                    .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordFieldConfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados Login", jPanel4);

        jLabel10.setText("Pergunta 1: Qual sua cor Favorita?");

        jLabel11.setText("Pergunta 2: Qual sua comida Favorita?");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPalavra1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPalavra2, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldPalavra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldPalavra2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Respostas Secretas", jPanel1);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterar.png"))); // NOI18N
        jButton1.setText("Alterar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/voltar.png"))); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Alterar Usuário");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordFieldConfSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldConfSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldConfSenhaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        Integer  id = Integer.parseInt(jTextFieldID.getText());
        String nome = jTextFieldNome.getText(); 
        String  telefone = jFormattedTextFieldTelefone.getText();
        String  rg = jTextFieldRG.getText();
        String  cpf = jFormattedTextFieldCpf.getText();
        String  email = jTextField1Email.getText();
        String senha = new String (jPasswordFieldSenha.getPassword());
        String confsenha = new String (jPasswordFieldConfSenha.getPassword());
        String resposta1 = jTextFieldPalavra1.getText();
        String resposta2 =jTextFieldPalavra2.getText();
        //String logradouro =jTextFieldLogradouro.getText();

        //validações
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
/*        if (rg.equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o RG.");
            jFormattedTextFieldRG.requestFocus();
            return;
        }*/

            /*try {
            AlunoDao dao = new AlunoDao();
            if (dao.rgJaExiste(rg)) {
                JOptionPane.showMessageDialog(this, "Já existe um aluno com o mesmo RG.");
                jTextFieldRG.setForeground(Color.RED);

                return; 
            } else if (!validarRG(rg)) {
            JOptionPane.showMessageDialog(this, "RG inválido.");
            jTextFieldRG.requestFocus();
            return;
        }
            /*} catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar aluno: " + e.getMessage());
}*/
        if (telefone.equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o Telefone.");
            jFormattedTextFieldTelefone.requestFocus();
            return;
        } else if (!ValidarTelefone(telefone)) {
            JOptionPane.showMessageDialog(this, "Informe o Telefone.");
            jFormattedTextFieldTelefone.requestFocus();
            return;
        }
         if (email.equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o Email.");
            jTextField1Email.requestFocus();
            return;
        } else if (!ValidarEmail(email)) {
            JOptionPane.showMessageDialog(this, "Informe o Email.");
            jTextField1Email.requestFocus();
            return;
        }
        if (senha == null || senha.trim().equals("")) {
        JOptionPane.showMessageDialog(this, "Informe a Senha");
        jPasswordFieldSenha.requestFocus();
        return;
        }

        if (confsenha == null || confsenha.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Informe a Senha.");
            jPasswordFieldConfSenha.requestFocus();
            return;
        }

        if (!senha.trim().equals(confsenha)) {
            JOptionPane.showMessageDialog(this, "As senhas não conferem.");
            jPasswordFieldSenha.setText("");
            jPasswordFieldConfSenha.setText("");
            jPasswordFieldSenha.requestFocus();
            return;
}
        
       /* if(logradouro.equals("")){
            JOptionPane.showConfirmDialog(this, "informe o Logradouro.");
            jTextFieldLogradouro.requestFocus();
            return;
        }*/

        Usuario u = new Usuario(id, nome, telefone, rg, cpf, senha, email, resposta1, resposta2, null);

        try {
            UsuarioDao dao = new UsuarioDao();
            dao.atualizar(u);
            UsuariosForm.callback();

            JOptionPane.showMessageDialog(this, "Registro atualizado.");
            this.dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       int resposta = JOptionPane.showConfirmDialog(this, "Deseja mesmo sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
        this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
    
    private static boolean ValidarTelefone(String telefone){
        String regex = "^\\(\\d{2}\\)\\s\\d{4}-\\d{4}$";
        
        Pattern parttern = Pattern.compile(regex);
        Matcher matcher = parttern.matcher(telefone);
        
        return matcher.matches();
    }
    
    private static boolean ValidarEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        
        Pattern parttern = Pattern.compile(regex);
        Matcher matcher = parttern.matcher(email);
        
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
            java.util.logging.Logger.getLogger(AlterarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlterarUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefone;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPasswordField jPasswordFieldConfSenha;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1Email;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldPalavra1;
    private javax.swing.JTextField jTextFieldPalavra2;
    private javax.swing.JTextField jTextFieldRG;
    // End of variables declaration//GEN-END:variables
}
