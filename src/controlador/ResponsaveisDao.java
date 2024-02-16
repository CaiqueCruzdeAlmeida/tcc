package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Responsavel;
import modelo.Turma;

public class ResponsaveisDao {

    public int inserir(Responsavel u) throws Exception {
        int retorno;
        String sql = "insert into responsavel(nome, rg, cpf, telefone, cep, numero, rua, cidade, estado, genero)"
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conexao = Conexao.getConexao();
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getRg());
            ps.setString(3, u.getCpf());
            ps.setString(4, u.getTelefone());
            ps.setString(5, u.getCep());
            ps.setString(6, u.getNumero());
            ps.setString(7, u.getRua());
            ps.setString(8, u.getCidade());
            ps.setString(9, u.getEstado());
            ps.setString(10, u.getGenero());
            //ps.setString(10, u.getLogradouro());

            retorno = ps.executeUpdate();
        }
        return retorno;
    }

    public List<Responsavel> buscar(String nome) throws Exception {
        Connection conexao = Conexao.getConexao();
        String sql = "select * from responsavel";

        if (!nome.equals("")) {
            sql += " where nome like ?";
        }

        sql += " order by nome";

        List<Responsavel> lista = new ArrayList<>();
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {

            if (!nome.equals("")) {
                ps.setString(1, "%" + nome + "%");
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Responsavel u = new Responsavel();
                    u.setId(rs.getInt("id"));
                    u.setNome(rs.getString("nome"));
                    u.setGenero(rs.getString("genero"));
                    u.setRg(rs.getString("rg"));
                    u.setCpf(rs.getString("cpf"));
                    u.setTelefone(rs.getString("telefone"));

                    lista.add(u);
                }
            }

        } catch (Exception e) {
            throw e;
        }

        return lista;
    }

    public Responsavel buscarPorId(int id) throws Exception {
        Responsavel responsavel = null;

        String sql = "SELECT * FROM responsavel WHERE id = ?";
        Connection conexao = Conexao.getConexao(); 
        
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    responsavel = new Responsavel();
                    responsavel.setId(rs.getInt("id"));
                    responsavel.setNome(rs.getString("nome"));
                    responsavel.setCpf(rs.getString("cpf"));
                    responsavel.setRg(rs.getString("rg"));
                    responsavel.setNumero(rs.getString("numero"));
                    responsavel.setRua(rs.getString("rua"));
                    responsavel.setGenero(rs.getString("genero"));
                    responsavel.setEstado(rs.getString("estado"));
                    responsavel.setCep(rs.getString("cep"));
                    responsavel.setTelefone(rs.getString("telefone"));
                    responsavel.setCidade(rs.getString("cidade"));

                }
            }
        }

        return responsavel;
    }

    public int excluir(String nome) throws Exception {
        int retorno;

        Connection conexao = Conexao.getConexao();
        String sql = "DELETE FROM responsavel WHERE nome = ?"; // Correção da consulta SQL

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, nome);

            retorno = ps.executeUpdate();
        }
        return retorno;
    }

    public void excluir(Integer id) throws Exception {
        String sql = "delete from responsavel where id = ?";

        Connection conexao = Conexao.getConexao();
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    public Responsavel getResponsavel(int id) throws Exception {
        Connection conexao = Conexao.getConexao();
        String sql = "select * from responsavel where id= ?";

        Responsavel obj = null;

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            //parametros da Sql
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    obj = new Responsavel();
                    obj.setId(rs.getInt("id"));
                    obj.setNome(rs.getString("nome"));
                    obj.setGenero(rs.getString("genero"));
                    obj.setRg(rs.getString("rg"));
                    obj.setCpf(rs.getString("cpf"));
                    obj.setEstado(rs.getString("estado"));
                    obj.setNumero(rs.getString("numero"));
                    obj.setRua(rs.getString("rua"));
                    obj.setTelefone(rs.getString("telefone"));
                    obj.setCep(rs.getString("cep"));
                    obj.setCidade(rs.getString("cidade"));

                }
            }

        } catch (Exception e) {
            throw e;
        }
        return obj;
    }

    public int atualizar(Responsavel u) throws Exception {
        int retorno;
        String sql = "update responsavel set nome = ?, rg = ?, cpf = ?, numero = ? , rua = ?, telefone = ?, cep = ?, cidade = ?, estado = ?, genero = ? where id = ?";

        Connection conexao = Conexao.getConexao();
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(10, u.getGenero());
            ps.setString(2, u.getRg());
            ps.setString(3, u.getCpf());
            ps.setString(7, u.getCep());
            ps.setString(4, u.getNumero());
            ps.setString(5, u.getRua());
            ps.setString(6, u.getTelefone());
            ps.setInt(11, u.getId());
            ps.setString(8, u.getCidade());
            ps.setString(9, u.getEstado());

            retorno = ps.executeUpdate();
        }
        return retorno;
    }

    public Responsavel buscarResponsavel(int id) throws Exception {
        Connection conexao = Conexao.getConexao();
        String sql = "select nome from responsavel where id = ?";

        Responsavel obj = null;

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    obj = new Responsavel();
                    obj.setId(rs.getInt("id"));
                }
            }

        } catch (Exception e) {
            throw e;
        }
        return obj;

    }

    public boolean rgJaExiste(String rg) throws Exception {
    // Verifica se o RG não é nulo e não está vazio
    if (rg != null) {
        rg = rg.trim(); // Remove espaços em branco extras no início e no fim
        
        if (!rg.isEmpty()) {
            java.sql.Connection conexao = Conexao.getConexao();
            String sql = "SELECT COUNT(*) FROM responsavel WHERE rg = ?";
            
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, rg);
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    int countAluno = rs.getInt(1);
                    
                    // Verificar na tabela usuario
                    String sqlUsuario = "SELECT COUNT(*) FROM aluno WHERE rg = ?";
                    try (PreparedStatement psUsuario = conexao.prepareStatement(sqlUsuario)) {
                        psUsuario.setString(1, rg);
                        try (ResultSet rsUsuario = psUsuario.executeQuery()) {
                            rsUsuario.next();
                            int countUsuario = rsUsuario.getInt(1);
                            
                            // Verificar na tabela responsavel
                            String sqlResponsavel = "SELECT COUNT(*) FROM usuario WHERE rg = ?";
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
    String sql = "SELECT COUNT(*) FROM responsavel WHERE cpf = ?";
    
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setString(1, cpf);
        try (ResultSet rs = ps.executeQuery()) {
            rs.next();
            int countAluno = rs.getInt(1);
            
            // Verificar na tabela usuario
            String sqlUsuario = "SELECT COUNT(*) FROM usuario  WHERE cpf = ?";
            try (PreparedStatement psUsuario = conexao.prepareStatement(sqlUsuario)) {
                psUsuario.setString(1, cpf);
                try (ResultSet rsUsuario = psUsuario.executeQuery()) {
                    rsUsuario.next();
                    int countUsuario = rsUsuario.getInt(1);
                    
                    // Verificar na tabela responsavel
                    String sqlResponsavel = "SELECT COUNT(*) FROM aluno WHERE cpf = ?";
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


    public int obterIdResponsavelPeloNome(String nomeResponsavel) throws Exception {
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
}
    public int obterIdTurma(String nomeTurma) throws Exception {
    Connection conexao = Conexao.getConexao();
    int idResponsavel = -1; // Valor padrão para indicar que o ID não foi encontrado
    
    String sql = "SELECT id FROM turma WHERE nome = ?";

    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setString(1, nomeTurma);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            idResponsavel = rs.getInt("id");
        }
    }
    
    return idResponsavel;
}
    public int obterIdRespPeloNome(String nomeResponsavel) throws Exception {
        Connection conexao = Conexao.getConexao();
        int idResp = -1; // Valor padrão para indicar que o ID não foi encontrado

        String sql = "SELECT id FROM responsavel WHERE nome = ?";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, nomeResponsavel);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idResp = rs.getInt("id");
            }
        }

        return idResp;
}
    
     
}
