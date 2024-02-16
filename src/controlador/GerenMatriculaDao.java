package controlador;

import com.sun.jdi.connect.spi.Connection;
import modelo.GerencMatricula;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author caiqu
 */
public class GerenMatriculaDao {
    
    public int inserir (GerencMatricula u) throws Exception{
        int retorno;
        String sql = "insert into matricula(idaluno, datainicio, datafechamento, datavencimento, numeromatricula, valormensalidade, desconto)"
                   + "values (?, ?, ?, ?, ?, ?,?)";
        
        java.sql.Connection conexao = Conexao.getConexao();
        try(PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setInt(1, u.getIdaluno());
            ps.setString(2, u.getDatainicio());
            ps.setString(3, u.getDatafechamento());
            ps.setString(4, u.getDatavencimento());
            ps.setString(5, u.getNumeromaricula());
            ps.setInt(6, u.getValormensalidade());
            ps.setDouble(7, u.getDesconto());
            
            
            retorno = ps.executeUpdate();
        }
        return retorno;
    }
    
    
}
