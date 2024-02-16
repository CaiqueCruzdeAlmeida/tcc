
package modelo;

/**
 *
 * @author Caique
 */
public class ResponsavelPorAluno {
    
    private int id;
    private int idaluno;
    private int idresponsavel;

    public ResponsavelPorAluno(int idaluno, int idresponsavel) {
        
        this.idaluno = idaluno;
        this.idresponsavel = idresponsavel;
    }
     
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdaluno() {
        return idaluno;
    }

    public void setIdaluno(int idaluno) {
        this.idaluno = idaluno;
    }

    public int getIdresponsavel() {
        return idresponsavel;
    }

    public void setIdresponsavel(int idresponsavel) {
        this.idresponsavel = idresponsavel;
    }
    
    
    
    
}
