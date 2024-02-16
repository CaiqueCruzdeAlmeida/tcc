package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import modelo.Aluno;
import modelo.Turma;

/**
 *
 * @author caiqu
 */
public class TurmaDao {
    
    public int inserir (Turma u) throws Exception{
        int retorno;
        String sql = "insert into turma(nometurma, quantidadematriculados, atividades, valormensalidade)"
                   + "values (?, ?, ?, ?)";
        
        Connection conexao = Conexao.getConexao();
        try(PreparedStatement ps = conexao.prepareStatement(sql)){
            //ps.setInt(1, u.getCodturma());
            ps.setString(1, u.getNometurma());
            //ps.setString(2, u.getLotacaomaxima());
            ps.setString(2, u.getQuantidadematriculados());
            ps.setString(3, u.getAtividades());
            ps.setDouble(4, u.getValormensalidade());
                        
            
            retorno = ps.executeUpdate();
        }
        return retorno;
    }
    public List<Turma> buscarPorNome(String nome) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "SELECT * FROM turma";

    if (!nome.equals("")) {
        sql += " WHERE nometurma LIKE ?";
    }

    sql += " ORDER BY id";

    List<Turma> lista = new ArrayList<>();
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {

        if (!nome.equals("")) {
            ps.setString(1, "%" + nome + "%");
        }

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Turma u = new Turma();
                u.setIdTurma(rs.getInt("id"));
                u.setNometurma(rs.getString("nometurma"));
                u.setQuantidadematriculados(rs.getString("quantidadematriculados"));
                u.setValormensalidade(rs.getDouble("valormensalidade"));

                lista.add(u);
            }
        }

    } catch (Exception e) {
        throw e;
    }

    return lista;
}

     public List<Turma> buscar(String nome) throws Exception{
        Connection conexao = Conexao.getConexao();
        String sql = "select * from turma";
        
        if(!nome.equals("")){
            sql += " where id like ?";
        }
        
        sql += " order by id";
        
        List<Turma> lista = new ArrayList<>();
        try(PreparedStatement ps = conexao.prepareStatement(sql)){
            
            if(!nome.equals("")){
                ps.setString(1, "%" + nome + "%");
            }
            
            try(var rs = ps.executeQuery()){
                while(rs.next()){
                    Turma u = new Turma();
                    u.setIdTurma(rs.getInt("id"));
                    u.setNometurma(rs.getString("nometurma"));
                    //u.setLotacaomaxima(rs.getString("lotacaomaxima"));
                    u.setQuantidadematriculados(rs.getString("quantidadematriculados"));
                    u.setValormensalidade(rs.getDouble("valormensalidade"));


                    
                    lista.add(u);
                }
            }
            
        }catch (Exception e){
            throw e;
        }
        
        
        return lista;
    }
     
     public List<Aluno> buscarAlunosPorTurma(String nomeTurma) throws Exception {
        Connection conexao = Conexao.getConexao();
        String sql = "SELECT a.* FROM aluno a " +
                     "INNER JOIN turmaporaluno tpa ON a.id = tpa.idaluno " +
                     "INNER JOIN turma t ON tpa.idturma = t.id " +
                     "WHERE t.nometurma LIKE ?";

        List<Aluno> lista = new ArrayList<>();
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, "%" + nomeTurma + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setId(rs.getInt("id"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setMatricula(rs.getInt("matricula"));
                    // Define outros atributos do aluno conforme sua estrutura de dados

                    lista.add(aluno);
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return lista;
}
        public List<Object[]> buscarInformacoesAlunoPorTurma(String nomeTurma) throws Exception {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT a.id, a.nome, a.matricula, tpa.datainicio, tpa.datafechamento " +
                         "FROM aluno a " +
                         "INNER JOIN turmaporaluno tpa ON a.id = tpa.idaluno " +
                         "INNER JOIN turma t ON tpa.idturma = t.id " +
                         "WHERE t.nometurma LIKE ?";

            List<Object[]> lista = new ArrayList<>();
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, "%" + nomeTurma + "%");

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Object[] alunoInfo = new Object[5];
                        alunoInfo[0] = rs.getInt("id");
                        alunoInfo[1] = rs.getString("nome");
                        alunoInfo[2] = rs.getInt("matricula");
                        alunoInfo[3] = rs.getString("datainicio");
                        alunoInfo[4] = rs.getString("datafechamento");

                        lista.add(alunoInfo);
                    }
                }
            } catch (Exception e) {
                throw e;
            }

    return lista;
}


     public void excluir(Integer id) throws Exception {
        String sql = "delete from turma where id = ?";

        java.sql.Connection conexao = Conexao.getConexao();
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }
     public int atualizar(Turma u) throws Exception{
        int retorno;
        String sql = "update turma set nometurma = ?, quantidadematriculados = ?, atividades = ? , valormensalidade = ? where id = ?";
        
        java.sql.Connection conexao = Conexao.getConexao();
        try(PreparedStatement ps = conexao.prepareStatement(sql)){
           
            ps.setString(1, u.getNometurma());
            //ps.setString(2, u.getLotacaomaxima());
            ps.setString(2, u.getQuantidadematriculados());
            ps.setString(3, u.getAtividades());
            ps.setDouble(4, u.getValormensalidade());
            ps.setInt(5, u.getIdTurma());
            
            
            
            
            retorno = ps.executeUpdate();
        }
        return retorno;
    }
     public Turma getTurma(int id) throws Exception{
        java.sql.Connection conexao = Conexao.getConexao();
        String sql = "select * from turma where id = ?";
        
        Turma obj = null;
        
        try(PreparedStatement ps = conexao.prepareStatement(sql)){
          //parametros da Sql
            ps.setInt(1, id);
            
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    obj = new Turma();
                    obj.setIdTurma(rs.getInt("id"));
                    obj.setNometurma(rs.getString("nometurma"));
                    //obj.setLotacaomaxima(rs.getString("lotacaomaxima"));
                    obj.setQuantidadematriculados(rs.getString("quantidadematriculados"));
                    obj.setAtividades(rs.getString("atividades"));
                    obj.setValormensalidade(rs.getDouble("valormensalidade"));
                                                                              
                                   
                }
            }
            
            
        }catch(Exception e){
            throw e;
        }
        return obj;
    }
     
     public Turma buscarPorId(int id) throws Exception {
        Turma turma = null;

        String sql = "SELECT * FROM turma WHERE id = ?";
        Connection conexao = Conexao.getConexao(); 
        
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    turma = new Turma();
                    turma.setIdTurma(rs.getInt("id"));
                    turma.setAtividades(rs.getString("atividades"));
                    //turma.setLotacaomaxima(rs.getString("lotacaomaxima"));
                    turma.setNometurma(rs.getString("nometurma"));
                    turma.setQuantidadematriculados(rs.getString("quantidadematriculados"));
                    turma.setValormensalidade(rs.getDouble("valormensalidade"));
                   

                }
            }
        }

        return turma;
    }
     public void atualizarMatriculadosNaTurma(Integer idTurma) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "UPDATE turma SET quantidadematriculados = quantidadematriculados + 1 WHERE id = ?"; // Substitua 'id' pelo nome da coluna que identifica unicamente a turma no banco
    
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setInt(1, idTurma); // Usando o ID da turma fornecido
        ps.executeUpdate();
    }
}

     public void diminuirMatriculadosNaTurma(Integer id) throws Exception {
        Connection conexao = null;
        PreparedStatement ps = null;

        try {
            conexao = Conexao.getConexao();
            String sql = "UPDATE turma SET quantidadematriculados = quantidadematriculados - 1 WHERE id = ?";

            ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            // Feche a conexão e os recursos PreparedStatement no bloco finally
            if (ps != null) {
                ps.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        }
}
     

    public int quantidadeAlunosMatriculadosNaTurma(int idTurma) throws Exception {
            int quantidade = 0;
            Connection conexao = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                conexao = Conexao.getConexao();
                String sql = "SELECT COUNT(*) AS quantidade FROM aluno WHERE idturma = ?";
                ps = conexao.prepareStatement(sql);
                ps.setInt(1, idTurma);

                rs = ps.executeQuery();

                if (rs.next()) {
                    quantidade = rs.getInt("quantidade");
                }
            } finally {
                // Feche os recursos na ordem reversa da abertura para evitar exceções
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            }

            return quantidade;
}
    public int obterIdTurmaPeloNome(String nomeResponsavel) throws Exception {
            Connection conexao = Conexao.getConexao();
            int idTurma = -1; // Valor padrão para indicar que o ID não foi encontrado

            String sql = "SELECT id FROM turma WHERE nometurma = ?";

            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, nomeResponsavel);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    idTurma = rs.getInt("id");
                }
            }

            return idTurma;
}

   public void diminuirMatriculadosNaTurma2(String nomeTurma) throws Exception {
    Connection conexao = null;
    PreparedStatement ps = null;

    try {
        conexao = Conexao.getConexao();
        String sql = "UPDATE turma SET quantidadematriculados = quantidadematriculados - 1 WHERE nometurma = ?";

        ps = conexao.prepareStatement(sql);
        ps.setString(1, nomeTurma);
        ps.executeUpdate();
    } catch (Exception e) {
        throw e;
    } finally {
        // Feche a conexão e os recursos PreparedStatement no bloco finally
        if (ps != null) {
            ps.close();
        }
        if (conexao != null) {
            conexao.close();
        }
    }
}


    

}
