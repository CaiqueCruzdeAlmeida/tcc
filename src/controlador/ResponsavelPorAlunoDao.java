
package controlador;

import com.sun.jdi.connect.spi.Connection;
import controlador.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Aluno;
import modelo.ResponsavelPorAluno;
/**
 *
 * @author Caique
 */
public class ResponsavelPorAlunoDao {
    
    
    public int inserir(int idAluno, int idResponsavel) throws Exception {
    int retorno = 0;

        String sql = "INSERT INTO responsavel_aluno (idaluno, idresponsavel) "
                +  "VALUES (?, ?)";
        java.sql.Connection conexao = Conexao.getConexao();

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, idAluno);
            ps.setInt(2, idResponsavel);

            retorno = ps.executeUpdate();

        }

    return retorno;
}
    

    public void removerAlunoDoResponsavel(int idAluno, int idResponsavel) throws Exception {
    // Verifica se o aluno está matriculado na turma antes de remover
    String sqlCheck = "SELECT * FROM responsavel_aluno WHERE idaluno = ? AND idresponsavel = ?";
    String sqlDelete = "DELETE FROM responsavel_aluno WHERE idaluno = ? AND idresponsavel = ?";

    java.sql.Connection conexao = Conexao.getConexao();
    try (PreparedStatement psCheck = conexao.prepareStatement(sqlCheck);
         PreparedStatement psDelete = conexao.prepareStatement(sqlDelete)) {
        psCheck.setInt(1, idAluno);
        psCheck.setInt(2, idResponsavel);

        
        try (ResultSet rs = psCheck.executeQuery()) {
            if (rs.next()) {
                
                psDelete.setInt(1, idAluno);
                psDelete.setInt(2, idResponsavel);
                int retorno = psDelete.executeUpdate();

                
            } else {
                throw new Exception("O aluno não está vinculado a este responsavel.");
            }
        }
    }
}
    
    
    
}
