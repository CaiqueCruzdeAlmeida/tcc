package modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Caique
 */
public class AlunosPorTurma {
    private Date datainicio;
    private Date datafechamento;
    private int idturma;
    private int idaluno;
    private Integer id;
    private String nometurma;

    public AlunosPorTurma(int id, Date dataInicio, Date dataTermino, int idAluno, int idTurma) {
        this.datafechamento = datafechamento;
        this.idturma = idturma;
        this.idaluno = idaluno;
        this.datainicio = datainicio;
        this.id = id;
    }

    public AlunosPorTurma() {
       
    }

    public AlunosPorTurma(Date datafechamento, Integer idturma, Integer idaluno, Date datainicio, Integer idalunosporturma) {
        this.datafechamento = datafechamento;
        this.idturma = idturma;
        this.idaluno = idaluno;
        this.datainicio = datainicio;
        this.id = id;
    }

    public AlunosPorTurma(Integer idturma, Integer idaluno) {
        this.datafechamento = datafechamento;
        this.idturma = idturma;
        this.idaluno = idaluno;
        this.datainicio = datainicio;
        this.id = id;
    }

    public String getNometurma() {
        return nometurma;
    }

    public void setNometurma(String nometurma) {
        this.nometurma = nometurma;
    }
    
    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    } 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    public Date getDatafechamento() {
        return datafechamento;
    }

    public void setDatafechamento(Date datafechamento) {
        this.datafechamento = datafechamento;
    }

    public int getIdturma() {
        return idturma;
    }

    public void setIdturma(int idturma) {
        this.idturma = idturma;
    }

    public int getIdaluno() {
        return idaluno;
    }

    public void setIdaluno(int idaluno) {
        this.idaluno = idaluno;
    }
    
    
    
}
