package modelo;

import java.util.Date;

/**
 *
 * @author Caique
 */
public class Gastos {
    
    
    private Integer id;
    private String nomeproduto;
    private Double valorpago;
    private Date datacompra;
    private String formapagamento;
    private String parcelas;
    private String produtocadastrado;
    private Integer anoreferencia;
    private Integer quantidade;
    private String mesreferencia;

     public Gastos(String nomeproduto, Double valorpago, Date datacompra, String formapagamento, String parcelas, Integer quantidade/*, Integer anoreferencia, String mesreferencia*/) {
        this.datacompra = datacompra;
        this.nomeproduto = nomeproduto;
        this.valorpago = valorpago;
        this.parcelas = parcelas;
        this.formapagamento = formapagamento;
        this.quantidade = quantidade;
//        this.anoreferencia = anoreferencia;
//        this.mesreferencia = mesreferencia;
       
    }

    public Gastos() {
        
    }

    public Gastos(String nomeproduto, Double valorpago, Date datacompra, String formapagamento, String parcelas, Integer quantidade/*, Integer anoreferencia, String mesreferencia*/, Integer id, Object object) {
        this.datacompra = datacompra;
        this.nomeproduto = nomeproduto;
        this.valorpago = valorpago;
        this.parcelas = parcelas;
        this.formapagamento = formapagamento;
        this.quantidade = quantidade;
        this.anoreferencia = anoreferencia;
        this.mesreferencia = mesreferencia;
        this.id = id;
    }

    public Integer getAnoreferencia() {
        return anoreferencia;
    }

    public void setAnoreferencia(Integer anoreferencia) {
        this.anoreferencia = anoreferencia;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getMesreferencia() {
        return mesreferencia;
    }

    public void setMesreferencia(String mesreferencia) {
        this.mesreferencia = mesreferencia;
    }

    public String getProdutocadastrado() {
        return produtocadastrado;
    }

    public void setProdutocadastrado(String produtocadastrado) {
        this.produtocadastrado = produtocadastrado;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeproduto() {
        return nomeproduto;
    }

    public void setNomeproduto(String nomeproduto) {
        this.nomeproduto = nomeproduto;
    }

    public Double getValorpago() {
        return valorpago;
    }

    public void setValorpago(Double valorpago) {
        this.valorpago = valorpago;
    }

    public Date getDatacompra() {
        return datacompra;
    }

    public void setDatacompra(Date datacompra) {
        this.datacompra = datacompra;
    }

    public String getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(String formapagamento) {
        this.formapagamento = formapagamento;
    }

    public String getParcelas() {
        return parcelas;
    }

    public void setParcelas(String parcelas) {
        this.parcelas = parcelas;
    }
    
    
    
}
