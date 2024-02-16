package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caiqu
 */
public class Turma {
    private Integer idturma;
    private String nometurma;
    private String lotacaomaxima;
    private String quantidadematriculados;
    private String atividades;
    private Double valormensalidade;
    private String quantidadeturmas;
    private List<TurmaPorAluno> aluno = new ArrayList<>();

    public Turma(){
        
    }   
    /*public Turma(String nometurma, String lotacaomaxima, String quantidadematriculados, String atividades, int valormensalidade) {
        //this.idturma = idturma;
        this.nometurma = nometurma;
        this.lotacaomaxima = lotacaomaxima;
        this.quantidadematriculados = quantidadematriculados;
        this.atividades = atividades;
        this.valormensalidade = valormensalidade;
    }*/

    public Turma(Integer idturma, String nometurma, String quantidadematriculados, String atividades, Double valormensalidade, String quantidadeturmas, Object object) {
        this.idturma = idturma;
        this.nometurma = nometurma;
        this.lotacaomaxima = lotacaomaxima;
        this.quantidadematriculados = quantidadematriculados;
        this.atividades = atividades;
        this.valormensalidade = valormensalidade;
        this.quantidadeturmas = quantidadeturmas;
    }

    public Turma(Integer idturma, String nometurma, String quantidadematriculados, String atividades, Double valormensalidade, Object object) {
         this.idturma = idturma;
        this.nometurma = nometurma;
        this.lotacaomaxima = lotacaomaxima;
        this.quantidadematriculados = quantidadematriculados;
        this.atividades = atividades;
        this.valormensalidade = valormensalidade;
    }

    public String getQuantidadeturmas() {
        return quantidadeturmas;
    }

    public void setQuantidadeTurmas(String quantidadeturmas) {
        this.quantidadeturmas = quantidadeturmas;
    }

    public Integer getIdTurma() {
        return idturma;
    }

    public void setIdTurma(Integer idturma) {
        this.idturma = idturma;
    }

    public String getNometurma() {
        return nometurma;
    }

    public void setNometurma(String nometurma) {
        this.nometurma = nometurma;
    }

    public String getLotacaomaxima() {
        return lotacaomaxima;
    }

    public void setLotacaomaxima(String lotacaomaxima) {
        this.lotacaomaxima = lotacaomaxima;
    }

    public String getQuantidadematriculados() {
        return quantidadematriculados;
    }

    public void setQuantidadematriculados(String quantidadematriculados) {
        this.quantidadematriculados = quantidadematriculados;
    }

    public String getAtividades() {
        return atividades;
    }

    public void setAtividades(String atividades) {
        this.atividades = atividades;
    }

    public void setValormensalidade(Double valormensalidade) {
        this.valormensalidade = valormensalidade;
    }

    public Double getValormensalidade() {
        return valormensalidade;
    }

    
    
    
    
    
}
