/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Caique
 */
public class Pagamentos {
    private Date datapagamento;
    private String mesderefenencia;
    private Integer anoreferencia;
    private Double valorpago;
    private String formapagamento;
    private String turmadoaluno;
    private String nomealuno;
    //private Integer codpagamentos;
    private Integer id;
    

    public Pagamentos(String datapagamento, String mesderefenencia, int anoreferencia, double valorpago, String formapagamento/*, Integer turmadoaluno*/) {
       
    }

    public Pagamentos(Date datapagamento, String mesderefenencia, Integer anoreferencia, Double valorpago, String formapagamento, String turmadoaluno, String nomealuno, Object object) {
        this.datapagamento = datapagamento;
        this.mesderefenencia = mesderefenencia;
        this.anoreferencia = anoreferencia;
        this.valorpago = valorpago;
        this.formapagamento = formapagamento;
        this.turmadoaluno = turmadoaluno;
        this.nomealuno = nomealuno;
    }

    public Pagamentos() {
        
    }

    public Pagamentos(Double valorpago, Date datapagamento, String mesderefenencia, Integer anoreferencia, String turmadoaluno, String formapagamento, String nomealuno, Integer id,  Object object) {
        
        this.datapagamento = datapagamento;
        this.mesderefenencia = mesderefenencia;
        this.anoreferencia = anoreferencia;
        this.valorpago = valorpago;
        this.formapagamento = formapagamento;
        this.turmadoaluno = turmadoaluno;
        this.nomealuno = nomealuno;
        this.id = id;
        
    }

   

    public String getNomealuno() {
        return nomealuno;
    }

    public void setNomealuno(String nomealuno) {
        this.nomealuno = nomealuno;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getCodpagamentos() {
//        return codpagamentos;
//    }
//
//    public void setCodpagamentos(Integer codpagamentos) {
//        this.codpagamentos = codpagamentos;
//    } 
    
    public Date getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(Date datapagamento) {
        this.datapagamento = datapagamento;
    }

    public String getMesderefenencia() {
        return mesderefenencia;
    }

    public void setMesderefenencia(String mesderefenencia) {
        this.mesderefenencia = mesderefenencia;
    }

    public Integer getAnoreferencia() {
        return anoreferencia;
    }

    public void setAnoreferencia(Integer anoreferencia) {
        this.anoreferencia = anoreferencia;
    }

    public Double getValorpago() {
        return valorpago;
    }

    public void setValorpago(Double valorpago) {
        this.valorpago = valorpago;
    }

    public String getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(String formapagamento) {
        this.formapagamento = formapagamento;
    }

    public String getTurmadoaluno() {
        return turmadoaluno;
    }

    public void setTurmadoaluno(String turmadoaluno) {
        this.turmadoaluno = turmadoaluno;
    }    
    
}
