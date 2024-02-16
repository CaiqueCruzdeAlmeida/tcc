package modelo;

/**
 *
 * @author caiqu
 */
public class GerencMatricula {
    
    private String datainicio;
    private String datafechamento;
    private String datavencimento;
    private Integer idaluno;
    private String numeromaricula;
    private Integer valormensalidade;
    private Double desconto;

    public GerencMatricula(String datainicio, String datafechamento, String datavencimento, Integer idaluno, String numeromaricula, Integer valormensalidade, Double desconto) {
        this.datainicio = datainicio;
        this.datafechamento = datafechamento;
        this.datavencimento = datavencimento;
        this.idaluno = idaluno;
        this.numeromaricula = numeromaricula;
        this.valormensalidade = valormensalidade;
        this.desconto = desconto;
    }

    public String getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(String datainicio) {
        this.datainicio = datainicio;
    }

    public String getDatafechamento() {
        return datafechamento;
    }

    public void setDatafechamento(String datafechamento) {
        this.datafechamento = datafechamento;
    }

    public String getDatavencimento() {
        return datavencimento;
    }

    public void setDatavencimento(String datavencimento) {
        this.datavencimento = datavencimento;
    }

    public Integer getIdaluno() {
        return idaluno;
    }

    public void setIdaluno(Integer idaluno) {
        this.idaluno = idaluno;
    }

    public String getNumeromaricula() {
        return numeromaricula;
    }

    public void setNumeromaricula(String numeromaricula) {
        this.numeromaricula = numeromaricula;
    }

    public Integer getValormensalidade() {
        return valormensalidade;
    }

    public void setValormensalidade(Integer valormensalidade) {
        this.valormensalidade = valormensalidade;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    
    
}
