package controlador;
import controlador.Conexao;
import controlador.TurmaDao;
import modelo.Aluno;
import java.sql.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import modelo.AlunosPorTurma;
import modelo.Turma;

public class AlunoDao {
     
   public int inserir(Aluno u) throws Exception {
    int retorno = 0;

        String sql = "INSERT INTO aluno (matricula, nome, rg, cpf, idade, datanascimento, idresponsavel, observacoes) "
                +  "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conexao = Conexao.getConexao();
        

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            // Ajuste para usar um java.sql.Date no PreparedStatement
            java.sql.Date dataNascimentosql = new java.sql.Date(u.getDatanascimento().getTime());
            
            ps.setInt(1, u.getMatricula());
            ps.setString(2, u.getNome());
            ps.setString(3, u.getRg());
            ps.setString(4, u.getCpf());
            ps.setString(5, u.getIdade());
            ps.setDate(6, dataNascimentosql);
            ps.setInt(7, u.getIdresponsavel());
            ps.setString(8, u.getObservacoes());
            

            retorno = ps.executeUpdate();

        }

    return retorno;
}

   
    public List<Aluno> buscar(String nome) throws Exception{
        Connection conexao = Conexao.getConexao();
        String sql = "select * from aluno";
        
        if(!nome.equals("")){
            sql += " where nome like ?";
        }
        
        sql += " order by nome";
        
        List<Aluno> lista = new ArrayList<>();
        try(PreparedStatement ps = conexao.prepareStatement(sql)){
            
            if(!nome.equals("")){
                ps.setString(1, "%" + nome + "%");
            }
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Aluno u = new Aluno();
                    u.setId(rs.getInt("id"));
                    u.setNome(rs.getString("nome"));
                    u.setRg(rs.getString("rg"));
                    u.setCpf(rs.getString("cpf"));
                    u.setDatacadastro(rs.getString("datacadastro"));
                    u.setIdade(rs.getString("idade"));                   
                    u.setMatricula(rs.getInt("matricula"));
                    u.setIdresponsavel(rs.getInt("idresponsavel"));
                    u.setObservacoes(rs.getString("observacoes"));
                
                    
                    // Recuperando a data do banco como java.sql.Date
                    java.sql.Date dataNascimentosql = rs.getDate("datanascimento");
                    // Convertendo para java.util.Date
                    java.util.Date dataPagamento = new java.util.Date(dataNascimentosql.getTime());

                    u.setDatanascimento(dataPagamento);

                            lista.add(u);
                        }
                    }

                }catch (Exception e){
                    throw e;
                }
        
        
        return lista;
    }
    

    public List<Aluno> buscarPorID(String nome) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "SELECT * FROM aluno WHERE idturma = 1"; // Inicia a consulta com a condição para idturma
    
    if (!nome.equals("")) {
        sql += " AND nome LIKE ?";
    }
    
    sql += " ORDER BY nome"; // Adiciona a ordenação
    
    List<Aluno> lista = new ArrayList<>();
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        
        if (!nome.equals("")) {
            ps.setString(1, "%" + nome + "%");
        }
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Aluno u = new Aluno();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setRg(rs.getString("rg"));
                u.setCpf(rs.getString("cpf"));
                u.setDatacadastro(rs.getString("datacadastro"));
                u.setIdade(rs.getString("idade"));               
                u.setMatricula(rs.getInt("matricula"));
                u.setIdturma(rs.getInt("idturma"));
                u.setIdresponsavel(rs.getInt("idresponsavel"));
                
                // Recuperando a data do banco como java.sql.Date
                    java.sql.Date dataNascimentosql = rs.getDate("datanascimento");
                    // Convertendo para java.util.Date
                    java.util.Date dataPagamento = new java.util.Date(dataNascimentosql.getTime());

                    u.setDatanascimento(dataPagamento);
                
                lista.add(u);
            }
        }
    } catch (Exception e) {
        throw e;
    }
    
    return lista;
}

    
    public boolean rgJaExiste(String rg) throws Exception {
    // Verifica se o RG não é nulo e não está vazio
    if (rg != null) {
        rg = rg.trim(); // Remove espaços em branco extras no início e no fim
        
        if (!rg.isEmpty()) {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT COUNT(*) FROM aluno WHERE rg = ?";
            
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, rg);
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    int countAluno = rs.getInt(1);
                    
                    // Verificar na tabela usuario
                    String sqlUsuario = "SELECT COUNT(*) FROM usuario WHERE rg = ?";
                    try (PreparedStatement psUsuario = conexao.prepareStatement(sqlUsuario)) {
                        psUsuario.setString(1, rg);
                        try (ResultSet rsUsuario = psUsuario.executeQuery()) {
                            rsUsuario.next();
                            int countUsuario = rsUsuario.getInt(1);
                            
                            // Verificar na tabela responsavel
                            String sqlResponsavel = "SELECT COUNT(*) FROM responsavel WHERE rg = ?";
                            try (PreparedStatement psResponsavel = conexao.prepareStatement(sqlResponsavel)) {
                                psResponsavel.setString(1, rg);
                                try (ResultSet rsResponsavel = psResponsavel.executeQuery()) {
                                    rsResponsavel.next();
                                    int countResponsavel = rsResponsavel.getInt(1);
                                    
                                    // Verifica se o RG existe em pelo menos uma das tabelas
                                    return (countAluno > 0 || countUsuario > 0 || countResponsavel > 0);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            // Se o RG estiver vazio, retorna false
            return false;
        }
    }
    // Se o RG for nulo, retorna false
    return false;
}



    public boolean cpfJaExiste(String cpf) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "SELECT COUNT(*) FROM aluno WHERE cpf = ?";
    
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setString(1, cpf);
        try (ResultSet rs = ps.executeQuery()) {
            rs.next();
            int countAluno = rs.getInt(1);
            
            // Verificar na tabela usuario
            String sqlUsuario = "SELECT COUNT(*) FROM usuario WHERE cpf = ?";
            try (PreparedStatement psUsuario = conexao.prepareStatement(sqlUsuario)) {
                psUsuario.setString(1, cpf);
                try (ResultSet rsUsuario = psUsuario.executeQuery()) {
                    rsUsuario.next();
                    int countUsuario = rsUsuario.getInt(1);
                    
                    // Verificar na tabela responsavel
                    String sqlResponsavel = "SELECT COUNT(*) FROM responsavel WHERE cpf = ?";
                    try (PreparedStatement psResponsavel = conexao.prepareStatement(sqlResponsavel)) {
                        psResponsavel.setString(1, cpf);
                        try (ResultSet rsResponsavel = psResponsavel.executeQuery()) {
                            rsResponsavel.next();
                            int countResponsavel = rsResponsavel.getInt(1);
                            
                            // Verifica se o CPF existe em pelo menos uma das tabelas
                            return (countAluno > 0 || countUsuario > 0 || countResponsavel > 0);
                        }
                    }
                }
            }
        }
    }
}
 public boolean MatriculaJaExiste(Integer matricula) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "SELECT COUNT(*) FROM aluno WHERE matricula = ?";
    
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setInt(1, matricula);
        try (ResultSet rs = ps.executeQuery()) {
            rs.next();
            int countAluno = rs.getInt(1);
            
           
           }     
        }
       return false;
    
}
    
    public Aluno buscarPorId(int id) throws Exception {
        Aluno aluno = null;

        String sql = "SELECT * FROM aluno WHERE id = ?";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    aluno = new Aluno();
                    aluno.setId(rs.getInt("id"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setCpf(rs.getString("cpf"));
                    aluno.setRg(rs.getString("rg"));
                    aluno.setIdade(rs.getString("idade"));
                    aluno.setDatacadastro(rs.getString("datacadastro"));
                    aluno.setIdresponsavel(rs.getInt("idresponsavel"));
                    aluno.setObservacoes(rs.getString("observacoes"));

                    // Recuperando a data do banco como java.sql.Date
                    java.sql.Date dataNascimentosql = rs.getDate("datanascimento");
                    // Convertendo para java.util.Date
                    java.util.Date dataNascimento = new java.util.Date(dataNascimentosql.getTime());                   
                    
                    aluno.setDatanascimento(dataNascimento);

                }
            }
        }

        return aluno;
    }
  
    
   public void excluirComDependencias(Integer idAluno) throws Exception {
    Connection conexao = Conexao.getConexao();

    try {
        String nomeAluno = null;
        String nomeTurma = null;

        // Buscar nome do aluno e nome da turma associada ao idAluno na tabela turmaporaluno
        String selectDadosAluno = "SELECT aluno.nome AS nomealuno, turma.nometurma FROM aluno "
                + "LEFT JOIN turmaporaluno ON aluno.id = turmaporaluno.idaluno "
                + "LEFT JOIN turma ON turmaporaluno.idturma = turma.id "
                + "WHERE aluno.id = ?";
        try (PreparedStatement psSelectDadosAluno = conexao.prepareStatement(selectDadosAluno)) {
            psSelectDadosAluno.setInt(1, idAluno);
            ResultSet rs = psSelectDadosAluno.executeQuery();

            if (rs.next()) {
                nomeAluno = rs.getString("nomealuno");
                nomeTurma = rs.getString("nometurma");
            }
        }

        if (nomeAluno != null) {
            // Excluir registros relacionados na tabela pagamentos
            String deletePagamentos = "DELETE FROM pagamentos WHERE nomealuno = ?";
            try (PreparedStatement psPagamentos = conexao.prepareStatement(deletePagamentos)) {
                psPagamentos.setString(1, nomeAluno);
                psPagamentos.executeUpdate();
            }

            // Excluir registros relacionados na tabela turmaporaluno
            String deleteTurmaPorAluno = "DELETE FROM turmaporaluno WHERE idaluno = ?";
            try (PreparedStatement psTurmaPorAluno = conexao.prepareStatement(deleteTurmaPorAluno)) {
                psTurmaPorAluno.setInt(1, idAluno);
                psTurmaPorAluno.executeUpdate();
            }

            // Excluir o aluno após ter excluído registros relacionados
            String deleteAluno = "DELETE FROM aluno WHERE id = ?";
            try (PreparedStatement psAluno = conexao.prepareStatement(deleteAluno)) {
                psAluno.setInt(1, idAluno);
                psAluno.executeUpdate();
            }

            // Após excluir o aluno, diminuir a quantidade de matriculados na turma (se houver)
            if (nomeTurma != null) {
                TurmaDao turmaDao = new TurmaDao();
                turmaDao.diminuirMatriculadosNaTurma2(nomeTurma);
            }
        }
    } catch (Exception e) {
        throw e;
    } finally {
        // Fechar conexão, etc.
    }
}







     
     public Integer obterIdTurmaDoAluno(Integer idAluno) throws Exception {
            Integer idTurma = null;
            String sql = "SELECT idturma FROM turmaporaluno WHERE id = ?";
            Connection conexao = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                conexao = Conexao.getConexao();
                ps = conexao.prepareStatement(sql);
                ps.setInt(1, idAluno);

                rs = ps.executeQuery();

                if (rs.next()) {
                    idTurma = rs.getInt("idturma");
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

            return idTurma;
}

    
    public Aluno getAluno(int id) throws Exception{
        Connection conexao = Conexao.getConexao();
        String sql = "select * from aluno where id= ?";
        
        Aluno obj = null;
        
        try(PreparedStatement ps = conexao.prepareStatement(sql)){
          //parametros da Sql
            ps.setInt(1, id);
            
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    obj = new Aluno();
                    obj.setId(rs.getInt("id"));
                    obj.setNome(rs.getString("nome"));
                    obj.setRg(rs.getString("rg"));
                    obj.setCpf(rs.getString("cpf"));
                    obj.setDatacadastro(rs.getString("datacadastro"));
                    obj.setIdade(rs.getString("idade"));
                    obj.setMatricula(rs.getInt("matricula"));
                    obj.setIdresponsavel(rs.getInt("idresponsavel"));
                    obj.setObservacoes(rs.getString("observacoes"));
                    // Recuperando a data do banco como java.sql.Date
                    java.sql.Date dataNascimentosql = rs.getDate("datanascimento");
                    // Convertendo para java.util.Date
                    java.util.Date dataPagamento = new java.util.Date(dataNascimentosql.getTime());

                    obj.setDatanascimento(dataPagamento);
                    
                    
                }
            }
            
            
        }catch(Exception e){
            throw e;
        }
        return obj;
    }

    /*public int obterIdResponsavelPeloNome(String nomeResponsavel) throws Exception {
    Connection conexao = Conexao.getConexao();
    int idResponsavel = -1; // Valor padrão para indicar que o ID não foi encontrado
    
    String sql = "SELECT id FROM responsavel WHERE nome = ?";

    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setString(1, nomeResponsavel);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            idResponsavel = rs.getInt("id");
        }
    }
    
    return idResponsavel;
}*/

    
    public int atualizar(Aluno u) throws Exception {
            int retorno;
            Connection conexao = Conexao.getConexao();
            String sql = "UPDATE aluno SET nome = ?, rg = ?, cpf = ?, idade = ?, datanascimento = ?, matricula = ?, idresponsavel = ?, observacoes = ? WHERE id = ?";

            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                
                // Convertendo de java.util.Date para java.sql.Date
                java.sql.Date dataNascimentosql = new java.sql.Date(u.getDatanascimento().getTime());
                
                ps.setString(1, u.getNome());
                ps.setString(2, u.getRg());
                ps.setString(3, u.getCpf());
                ps.setString(4, u.getIdade());
                ps.setDate(5, dataNascimentosql);
                ps.setInt(6, u.getMatricula());
               // ps.setInt(7, u.getIdturma());
                ps.setInt(7, u.getIdresponsavel());
                ps.setString(8,u.getObservacoes());
                ps.setInt(9, u.getId());

                retorno = ps.executeUpdate();
            }

//            // Atualizando a quantidade de alunos nas turmas se houve mudança de turma
//            if (retorno > 0) {
//                Turma turmaDepoisAtualizacao = obterTurmaDoAluno(u.getId()); // Obtendo a turma do aluno após a atualização
//
//                if (turmaAntesAtualizacao.getIdTurma()!= turmaDepoisAtualizacao.getIdTurma()) {
//                    atualizarQuantidadeAlunosNaTurma(turmaAntesAtualizacao, -1); // Subtrai um aluno da turma original
//                    atualizarQuantidadeAlunosNaTurma(turmaDepoisAtualizacao, 1); // Adiciona um aluno na nova turma
//                } else {
//                    atualizarQuantidadeAlunosNaTurma(turmaDepoisAtualizacao, 0); // Apenas atualiza a quantidade na turma atual
//                }
//            }

            return retorno;
}
    
    public void atualizarQuantidadeAlunosNaTurma(Turma turma, int alteracao) throws Exception {
        Connection conexao = Conexao.getConexao();
        String sql = "UPDATE turma SET quantidadematriculados = quantidadematriculados + ? WHERE id = ?"; // Substitua 'id' pelo nome da coluna que identifica unicamente a turma no banco

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, alteracao);
            ps.setInt(2, turma.getIdTurma());
            ps.executeUpdate();
        }
    }

     public Turma obterTurmaDoAluno(int alunoId) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "SELECT t.id, t.nometurma, t.quantidadematriculados FROM turma t JOIN aluno a ON t.id = a.idturma WHERE a.id = ?";
    
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setInt(1, alunoId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int idTurma = rs.getInt("id");
            String nomeTurma = rs.getString("nometurma");
            int quantidadeMatriculados = rs.getInt("quantidadematriculados");

            // Criando e retornando uma instância de Turma com os dados obtidos do banco
            Turma turma = new Turma();
            turma.setIdTurma(idTurma);
            turma.setNometurma(nomeTurma);
            turma.setQuantidadematriculados(String.valueOf(quantidadeMatriculados)); 
            return turma;
        }
    }

    
    return null;
}
    
  public List<Turma> obterTurmasDoAluno(int alunoId) throws Exception {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT t.id, t.nometurma, t.quantidadematriculados " +
                         "FROM turma t " +
                         "JOIN turmaporaluno tpa ON t.id = tpa.idturma " +
                         "WHERE tpa.idaluno = ?";

            List<Turma> turmas = new ArrayList<>();

            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setInt(1, alunoId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int idTurma = rs.getInt("id");
                    String nomeTurma = rs.getString("nometurma");
                    int quantidadeMatriculados = rs.getInt("quantidadematriculados");

                    // Criando instâncias de Turma com os dados obtidos do banco
                    Turma turma = new Turma();
                    turma.setIdTurma(idTurma);
                    turma.setNometurma(nomeTurma);
                    turma.setQuantidadematriculados(String.valueOf(quantidadeMatriculados)); 

                    turmas.add(turma);
                }
            }

            return turmas;
}



    public List<Aluno> buscar(Integer id) throws Exception {
          Connection conexao = Conexao.getConexao();
        String sql = "select * from aluno";
        
        if(!id.equals("")){
            sql += " where nome like ?";
        }
        
        sql += " order by nome";
        
        List<Aluno> lista = new ArrayList<>();
        try(PreparedStatement ps = conexao.prepareStatement(sql)){
            
            if(!id.equals("")){
                ps.setString(1, "%" + id + "%");
            }
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Aluno u = new Aluno();
                    u.setId(rs.getInt("id"));
                    u.setNome(rs.getString("nome"));
                    u.setRg(rs.getString("rg"));
                    u.setCpf(rs.getString("cpf"));
                    u.setDatacadastro(rs.getString("datacadastro"));
                    u.setIdade(rs.getString("idade"));                   
                    u.setMatricula(rs.getInt("matricula"));
                    u.setIdturma(rs.getInt("idturma"));
                    u.setIdresponsavel(rs.getInt("idresponsavel"));
                
                    
                    // Recuperando a data do banco como java.sql.Date
                    java.sql.Date dataNascimentosql = rs.getDate("datapagamento");
                    // Convertendo para java.util.Date
                    java.util.Date dataNascimento = new java.util.Date(dataNascimentosql.getTime());

                    u.setDatanascimento(dataNascimento);

                        lista.add(u);
                }
            }
            
        }catch (Exception e){
            throw e;
        }
        
        
        return lista;
        
        
    }
   
    public int obterIdAlunoPeloNome(String nomeResponsavel) throws Exception {
        Connection conexao = Conexao.getConexao();
        int idAluno = -1; // Valor padrão para indicar que o ID não foi encontrado

        String sql = "SELECT id FROM aluno WHERE nome = ?";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, nomeResponsavel);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idAluno = rs.getInt("id");
            }
        }

        return idAluno;
}
   public List<AlunosPorTurma> buscarTurmasPorAluno(int idAluno) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "SELECT t.*, tpa.datainicio, tpa.datafechamento " +
                 "FROM turma t " +
                 "INNER JOIN turmaporaluno tpa ON t.id = tpa.idturma " +
                 "INNER JOIN aluno a ON tpa.idaluno = a.id " +
                 "WHERE a.id = ?";

    List<AlunosPorTurma> lista = new ArrayList<>();
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setInt(1, idAluno);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AlunosPorTurma alunoPorTurma = new AlunosPorTurma();
                alunoPorTurma.setId(rs.getInt("id"));
                alunoPorTurma.setNometurma(rs.getString("nometurma"));
                alunoPorTurma.setDatainicio(rs.getDate("datainicio"));
                alunoPorTurma.setDatafechamento(rs.getDate("datafechamento"));

                lista.add(alunoPorTurma);
            }
        }
    } catch (Exception e) {
        throw e;
    }

    return lista;
}





   public List<Aluno> buscarAlunosPorResponsavel(int idResponsavel) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "SELECT * FROM aluno WHERE idresponsavel = ?";

    List<Aluno> lista = new ArrayList<>();
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setInt(1, idResponsavel);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getString("idade"));
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
}