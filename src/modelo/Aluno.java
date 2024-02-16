package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.TurmaPorAluno;

/**
 *
 * @author caiqu
 */
public class Aluno {

    private Integer id;
    private String nome;
    private String rg;
    private String cpf;
    private String datacadastro;
    private String idade;
    private Date datanascimento;
    private Integer matricula;
    private Integer idturma;
    private Integer idresponsavel;
    private String observacoes;
    private List<TurmaPorAluno> turmas = new ArrayList<>();
    
    
    
    public Aluno() {

    }

    public Aluno(Integer idresponsavel, Integer matricula, String nome, String idade, String rg, String cpf, Date datanascimento, String observacoes, Object object) {
        //this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.idade = idade;
        this.datanascimento = datanascimento;
        this.matricula = matricula;
        this.idturma = idturma;
        this.idresponsavel = idresponsavel;
        this.observacoes = observacoes;
    }

    public Aluno(Integer id, Integer idresponsavel, Integer matricula, String nome, String idade, String rg, String cpf, Date datanascimento, String observacoes, Object object) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.idade = idade;
        this.datanascimento = datanascimento;
        this.matricula = matricula;
        this.idturma = idturma;
        this.idresponsavel = idresponsavel;
        this.observacoes = observacoes;
    }

    public List<TurmaPorAluno> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<TurmaPorAluno> turmas) {
        this.turmas = turmas;
    }

   

   

    public Integer getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /*public void setGenero(String genero) {
        this.genero = genero;
    }*/
    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDatacadastro(String datacadastro) {
        this.datacadastro = datacadastro;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getNome() {
        return nome;
    }

    /*public String getGenero() {
        return genero;
    }*/
    public String getRg() {
        return rg;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDatacadastro() {
        return datacadastro;
    }

    public String getIdade() {
        return idade;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public Integer getIdturma() {
        return idturma;
    }

    public void setIdturma(Integer idturma) {
        this.idturma = idturma;
    }

    public Integer getIdresponsavel() {
        return idresponsavel;
    }

    public void setIdresponsavel(Integer idresponsavel) {
        this.idresponsavel = idresponsavel;
    }

    public int getIdTurma() {
         return idturma;
    }
 public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
   

}
