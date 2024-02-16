
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.AlunosPorTurma;
import modelo.Responsavel;
import modelo.Turma;
import modelo.TurmaPorAluno;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;



/**
 *
 * @author Caique
 */
public class AlunosPorTurmaDao {
    
    public int inserir(AlunosPorTurma u, Integer idTurma) throws Exception {
    if (idTurma <= 0) {
        throw new Exception("ID de turma inválido.");
    }

    TurmaDao turmaDao = new TurmaDao();
    Turma turma = turmaDao.buscarPorId(idTurma);

    int idAluno = u.getIdaluno();

    // Verifica se o aluno já está matriculado na turma antes de inserir
    String sqlCheck = "SELECT * FROM turmaporaluno WHERE idaluno = ? AND idturma = ?";
    try (Connection conexao = Conexao.getConexao();
         PreparedStatement psCheck = conexao.prepareStatement(sqlCheck)) {

        psCheck.setInt(1, idAluno);
        psCheck.setInt(2, idTurma);

        try (ResultSet rs = psCheck.executeQuery()) {
            if (rs.next()) {
                throw new Exception("O aluno já está matriculado nesta turma.");
            } else {
                // Se o aluno não estiver matriculado, insere na turma
                String sqlInsert = "INSERT INTO turmaporaluno (datafechamento, idturma, idaluno, datainicio) VALUES (?, ?, ?, ?)";
                int retorno;

                try (PreparedStatement ps = conexao.prepareStatement(sqlInsert)) {
                    // Ajuste para usar um java.sql.Date no PreparedStatement
                    java.sql.Date dataIniciosql = new java.sql.Date(u.getDatainicio().getTime());
                    java.sql.Date dataFechamento = new java.sql.Date(u.getDatafechamento().getTime());
                    
                    ps.setDate(1, dataFechamento);
                    ps.setInt(2, u.getIdturma());
                    ps.setInt(3, u.getIdaluno());
                    ps.setDate(4, dataIniciosql);

                    retorno = ps.executeUpdate();
                    if (retorno > 0) {
                        // Atualiza o número de matriculados na turma
                        turmaDao.atualizarMatriculadosNaTurma(idTurma);
                    } else {
                        throw new Exception("Falha ao inserir aluno na turma.");
                    }
                }
                return retorno;
            }
        }
    }
}

     
   public void removerAlunoDaTurma(int idAluno, int idTurma) throws Exception {
    // Verifica se o aluno está matriculado na turma antes de remover
    String sqlCheck = "SELECT * FROM turmaporaluno WHERE idaluno = ? AND idturma = ?";
    String sqlDelete = "DELETE FROM turmaporaluno WHERE idaluno = ? AND idturma = ?";

    Connection conexao = Conexao.getConexao();
    try (PreparedStatement psCheck = conexao.prepareStatement(sqlCheck);
         PreparedStatement psDelete = conexao.prepareStatement(sqlDelete)) {
        psCheck.setInt(1, idAluno);
        psCheck.setInt(2, idTurma);

        // Verifica se há uma matrícula do aluno na turma
        try (ResultSet rs = psCheck.executeQuery()) {
            if (rs.next()) {
                // Remova o aluno da turma se estiver matriculado
                psDelete.setInt(1, idAluno);
                psDelete.setInt(2, idTurma);
                int retorno = psDelete.executeUpdate();

                if (retorno > 0) {
                    // Chama o método para diminuir o número de matriculados na turma
                    TurmaDao turmaDao = new TurmaDao();
                    turmaDao.diminuirMatriculadosNaTurma(idTurma);
                } else {
                    throw new Exception("Falha ao remover aluno da turma.");
                }
            } else {
                throw new Exception("O aluno não está matriculado nesta turma.");
            }
        }
    }
}
public List<AlunosPorTurma> buscar() throws Exception {
    return buscar("");
}
   
   public List<AlunosPorTurma> buscar(String nome) throws Exception{
        Connection conexao = Conexao.getConexao();
        String sql = "select * from turmaporaluno";
        
        if(!nome.equals("")){
            sql += " where id like ?";
        }
        
        sql += " order by id";
        
        List<AlunosPorTurma> lista = new ArrayList<>();
        try(PreparedStatement ps = conexao.prepareStatement(sql)){
            
            if(!nome.equals("")){
                ps.setString(1, "%" + nome + "%");
            }
            
            try(var rs = ps.executeQuery()){
                while(rs.next()){
                    AlunosPorTurma u = new AlunosPorTurma();                                  
                    u.setIdaluno(rs.getInt("idaluno"));
                    u.setIdturma(rs.getInt("idturma"));
                    u.setId(rs.getInt("id"));
                    
                    // Recuperando a data do banco como java.sql.Date
                    java.sql.Date dataIniciosql = rs.getDate("datainicio");
                    java.sql.Date dataFechamentosql = rs.getDate("datafechamento");
                    // Convertendo para java.util.Date
                    java.util.Date dataInicio = new java.util.Date(dataIniciosql.getTime());
                    java.util.Date dataFecheamento = new java.util.Date(dataFechamentosql.getTime());

                    u.setDatainicio(dataInicio);
                    u.setDatafechamento(dataFecheamento);

                    
                    lista.add(u);
                }
            }
            
        }catch (Exception e){
            throw e;
        }
        
        
        return lista;
    }

   

public String buscarNomeTurmaPorId(int idTurma) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "SELECT turma.nometurma FROM turma INNER JOIN turmaporaluno ON turma.id = turmaporaluno.idturma WHERE turmaporaluno.idturma = ?";

    String nomeTurma = "";

    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setInt(1, idTurma);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                nomeTurma = rs.getString("nometurma");
            }
        }
    } catch (Exception e) {
        throw e;
    } finally {
        if (conexao != null) {
            conexao.close();
        }
    }

    return nomeTurma;
}

public String buscarNomeAlunoPorId(int idAluno) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "SELECT aluno.nome FROM aluno INNER JOIN turmaporaluno ON aluno.id = turmaporaluno.idaluno WHERE turmaporaluno.idaluno = ?";

    String nomeAluno = "";

    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setInt(1, idAluno);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                nomeAluno = rs.getString("nome");
            }
        }
    } catch (Exception e) {
        throw e;
    } finally {
        if (conexao != null) {
            conexao.close();
        }
    }

    return nomeAluno;
}
public List<AlunosPorTurma> buscarPorIdAluno(int idAluno) {
        List<AlunosPorTurma> listaAlunos = new ArrayList<>();
        
        String sql = "SELECT * FROM turmaporaluno WHERE idaluno = ?";
        
        try (Connection conexao = Conexao.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            
            ps.setInt(1, idAluno);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Popule a lista de AlunosPorTurma com os dados do ResultSet
                    AlunosPorTurma aluno = new AlunosPorTurma();                                   
                    aluno.setIdaluno(rs.getInt("idaluno"));
                    aluno.setIdturma(rs.getInt("idturma"));
                    aluno.setId(rs.getInt("id"));

                    // Recuperando a data do banco como java.sql.Date
                    java.sql.Date dataIniciosql = rs.getDate("datainicio");
                    java.sql.Date dataFechamentosql = rs.getDate("datafechamento");
                    // Convertendo para java.util.Date
                    java.util.Date dataInicio = new java.util.Date(dataIniciosql.getTime());
                    java.util.Date dataFecheamento = new java.util.Date(dataFechamentosql.getTime());

                    aluno.setDatainicio(dataInicio);
                    aluno.setDatafechamento(dataFecheamento);
                    
                    
                    listaAlunos.add(aluno);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Tratamento de exceções, como lançar ou tratar o erro
        }
        
        return listaAlunos;
    }


  public AlunosPorTurma buscarPorId(int idAlunosPorTurma) throws Exception {
        AlunosPorTurma aluno = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = Conexao.getConexao(); // Obtendo a conexão usando o Singleton

            String query = "SELECT id, datainicio, datafechamento, idaluno, idturma FROM turmaporaluno WHERE id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idAlunosPorTurma);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Extrair os dados do ResultSet e criar um objeto AlunosPorTurma
                int id = rs.getInt("id");
                java.sql.Date dataInicio = rs.getDate("datainicio");
                java.sql.Date dataFechamento = rs.getDate("datafechamento");
                int idAluno = rs.getInt("idaluno");
                int idTurma = rs.getInt("idturma");

                aluno = new AlunosPorTurma();
                aluno.setId(id);
                
                // Converter java.sql.Date para LocalDate
                LocalDate localDateInicio = dataInicio.toLocalDate();
                LocalDate localDateFechamento = dataFechamento.toLocalDate();

                // Converter LocalDate para Date manualmente
                Date dateInicio = new Date(localDateInicio.atStartOfDay().toEpochSecond(java.time.ZoneOffset.UTC) * 1000);
                Date dateFechamento = new Date(localDateFechamento.atStartOfDay().toEpochSecond(java.time.ZoneOffset.UTC) * 1000);

                aluno.setDatainicio(dateInicio);
                aluno.setDatafechamento(dateFechamento);

                aluno.setIdaluno(idAluno);
                aluno.setIdturma(idTurma);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com exceções SQL
        } finally {
            // Fechar conexão, statement e result set
            // Coloque as chamadas para fechar stmt, rs e a conexão aqui
        }

        return aluno;
    }

public int buscarIdTurmaPorIdAluno(int idAluno) throws SQLException, Exception {
        int idTurmaPorAluno = -1; // Valor padrão para indicar não encontrado

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = Conexao.getConexao(); // Obter a conexão com o banco de dados
            String query = "SELECT id FROM turmaporaluno WHERE idaluno = ?";
            statement = conexao.prepareStatement(query);
            statement.setInt(1, idAluno);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idTurmaPorAluno = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar id de turma por id de aluno: " + e.getMessage());
        } finally {
            // Fechar conexões e recursos
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (conexao != null) conexao.close();
        }

        return idTurmaPorAluno;
    }

    public List<TurmaPorAluno> buscarDetalhesPorIdAluno(int idAluno) throws SQLException, Exception {
        List<TurmaPorAluno> detalhesAluno = new ArrayList<>();

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = Conexao.getConexao(); // Obter a conexão com o banco de dados
            String query = "SELECT * FROM turmaporaluno WHERE idaluno = ?";
            statement = conexao.prepareStatement(query);
            statement.setInt(1, idAluno);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Construir o objeto TurmaPorAluno com os detalhes
                TurmaPorAluno detalhe = new TurmaPorAluno();
                detalhe.setId(resultSet.getInt("id"));
                detalhe.setDatainicio(resultSet.getString("datainicio"));
                detalhe.setDatafechamento(resultSet.getString("datafechamento"));
                detalhe.setIdaluno(resultSet.getInt("idaluno"));
                detalhe.setIdturma(resultSet.getInt("idturma"));

                detalhesAluno.add(detalhe);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar detalhes do aluno por id: " + e.getMessage());
        } finally {
            // Fechar conexões e recursos
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (conexao != null) conexao.close();
        }

        return detalhesAluno;
    }
    
    public List<TurmaPorAluno> buscarDetalhesPorIdTurmaPorAluno(int idTurmaPorAluno) throws Exception {
    List<TurmaPorAluno> detalhesAluno = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = Conexao.getConexao(); // Obtém a conexão com o banco de dados

        String query = "SELECT id, datainicio, datafechamento, idaluno, idturma FROM turmaporaluno WHERE id = ?";
        stmt = conn.prepareStatement(query);
        stmt.setInt(1, idTurmaPorAluno);

        rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            LocalDate dataInicio = rs.getDate("datainicio").toLocalDate();
            LocalDate dataFechamento = rs.getDate("datafechamento").toLocalDate();
            int idAluno = rs.getInt("idaluno");
            int idTurma = rs.getInt("idturma");

            TurmaPorAluno detalhe = new TurmaPorAluno(id, dataInicio.toString(), dataFechamento.toString(), idAluno, idTurma);
            detalhesAluno.add(detalhe);
        }
    } catch (Exception e) {
        throw e;
    } finally {
        // Fechar as conexões e recursos
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    }

    return detalhesAluno;
}
public AlunosPorTurma turmaPorAluno(int id) throws Exception {
        Connection conexao = Conexao.getConexao();
        String sql = "select * from turmaporaluno where id = ?";

        AlunosPorTurma obj = null;

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            //parametros da Sql
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    obj = new AlunosPorTurma();
                    obj.setId(rs.getInt("id"));
                    obj.setIdaluno(rs.getInt("idaluno"));
                    obj.setIdturma(rs.getInt("idturma"));
                    
                    // Recuperando a data do banco como java.sql.Date
                    java.sql.Date dataIniciosql = rs.getDate("datainicio");
                    java.sql.Date dataFechamentosql = rs.getDate("datafechamento");
                    
                    // Convertendo para java.util.Date
                    java.util.Date dataInicio = new java.util.Date(dataIniciosql.getTime());
                    java.util.Date dataFechamento = new java.util.Date(dataFechamentosql.getTime());

                    obj.setDatainicio(dataInicio);
                    obj.setDatainicio(dataFechamento);

                }
            }

        } catch (Exception e) {
            throw e;
        }
        return obj;
    }
    
public List<AlunosPorTurma> turmasPorAluno1(int idAluno) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "SELECT * FROM turmaporaluno WHERE idaluno = ?";

    List<AlunosPorTurma> turmasDoAluno = new ArrayList<>();

    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        // Parâmetros da SQL
        ps.setInt(1, idAluno);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AlunosPorTurma obj = new AlunosPorTurma();
                obj.setId(rs.getInt("id"));
                obj.setIdaluno(rs.getInt("idaluno"));
                obj.setIdturma(rs.getInt("idturma"));

                // Recuperando as datas do banco como java.sql.Date
                java.sql.Date dataIniciosql = rs.getDate("datainicio");
                java.sql.Date dataFechamentosql = rs.getDate("datafechamento");

                // Convertendo para java.util.Date
                java.util.Date dataInicio = new java.util.Date(dataIniciosql.getTime());
                java.util.Date dataFechamento = new java.util.Date(dataFechamentosql.getTime());

                obj.setDatainicio(dataInicio);
                obj.setDatafechamento(dataFechamento);

                // Adicionando o objeto à lista
                turmasDoAluno.add(obj);
            }

            // Mensagem de depuração para verificar o número de turmas encontradas
            System.out.println("Número de turmas encontradas: " + turmasDoAluno.size());

            // Mensagens de depuração dentro do loop para verificar os valores de idaluno e idturma
            for (AlunosPorTurma alunoTurma : turmasDoAluno) {
                System.out.println("ID do aluno associado: " + alunoTurma.getIdaluno());
                System.out.println("ID da turma associada: " + alunoTurma.getIdturma());

                // Restante do código...
            }
        }

    } catch (Exception e) {
        throw e;
    }
    return turmasDoAluno;
}



    
    
}
