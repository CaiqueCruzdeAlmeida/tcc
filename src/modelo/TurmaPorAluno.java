package modelo;

/**
 *
 * @author Caique
 */
public class TurmaPorAluno {
    
    private String datainicio;
    private String datafechamento;
    private int idturma;
    private int idaluno;
    private Integer id;

    public TurmaPorAluno(int id, String dataInicio, String dataTermino, int idAluno, int idTurma) {
        this.datafechamento = datafechamento;
        this.idturma = idturma;
        this.idaluno = idaluno;
        this.datainicio = datainicio;
        this.id = id;
    }

    public TurmaPorAluno() {
       
    }
    
    public String getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(String datainicio) {
        this.datainicio = datainicio;
    } 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    public String getDatafechamento() {
        return datafechamento;
    }

    public void setDatafechamento(String datafechamento) {
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
