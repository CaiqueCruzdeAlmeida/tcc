package modelo;

/**
 *
 * @author Caique
 */
public class CompraVenda {
    private String operacao;
    private String formapagamento;
    private Double desconto;
    private Integer cliente;

    public CompraVenda(String operacao, String formapagamento, Double desconto, Integer cliente) {
        this.operacao = operacao;
        this.formapagamento = formapagamento;
        this.desconto = desconto;
        this.cliente = cliente;
    }

    
    
    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(String formapagamento) {
        this.formapagamento = formapagamento;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }
    
    
}
