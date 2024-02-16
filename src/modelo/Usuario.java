
package modelo;

/**
 *
 * @author caiqu
 */
public class Usuario {
    private Integer id;
    private String nome;
    private String telefone;
    private String rg;
    private String cpf;
    private String senha;
    private String email;
    private String pergunta1;
    private String pergunta2;
    private String resposta1;
    private String resposta2;
    private String respostaCorreta;

   public Usuario(){
       
   }

    public Usuario(String nome, String telefone, String rg, String cpf, String senha, String email, String resposta1, String resposta2, String pergunta1, String pergunta2, Object object) {
        //this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.rg = rg;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
        this.resposta1 = resposta1;
        this.resposta2 = resposta2;
        this.pergunta1 = pergunta1;
        this.pergunta2 = pergunta2;
    }
    public Usuario(Integer id, String nome, String telefone, String rg, String cpf, String senha, String email, String resposta1, String resposta2,  Object object) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.rg = rg;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email; 
        this.resposta1 = resposta1;
        this.resposta2 = resposta2;
    }

    public Usuario(String senha, Object object) {
        this.senha = senha;
    }
    
    public String getRespostaCorreta() {
        return respostaCorreta;
    }

    public void setRespostaCorreta(String respostaCorreta) {
        this.respostaCorreta = respostaCorreta;
    }
    
    public Integer getId() {
        return id;
    }

    public String getResposta1() {
        return resposta1;
    }

    public void setResposta1(String resposta1) {
        this.resposta1 = resposta1;
    }

    public String getResposta2() {
        return resposta2;
    }

    public void setResposta2(String resposta2) {
        this.resposta2 = resposta2;
    }

    public String getPergunta1() {
        return pergunta1;
    }

    public void setPergunta1(String Pergunta1) {
        this.pergunta1 = Pergunta1;
    }

    public String getPergunta2() {
        return pergunta2;
    }

    public void setPergunta2(String Pergunta2) {
        this.pergunta2 = Pergunta2;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
