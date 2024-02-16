package controlador;

import com.sun.jdi.connect.spi.Connection;
import controlador.Conexao;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import modelo.Responsavel;
import java.sql.SQLException;

public class UsuarioDao {
    
    public int inserir(Usuario u) throws Exception {
        int retorno;

        String sql = "insert into usuario (nome, telefone, rg, cpf, email, senha, resposta1, resposta2, pergunta1, pergunta2)"
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        java.sql.Connection conexao = Conexao.getConexao();
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(5, u.getEmail());
            // Criptografando a senha antes de inserir no banco de dados
            String senhaCriptografada = criptografarSenha(u.getSenha());
            ps.setString(6, senhaCriptografada);
            ps.setString(2, u.getTelefone());
            ps.setString(3, u.getRg());
            ps.setString(4, u.getCpf());
            ps.setString(7, u.getResposta1());
            ps.setString(8, u.getResposta2());
            ps.setString(9, u.getPergunta1());
            ps.setString(10, u.getPergunta2());

            retorno = ps.executeUpdate();
        }

        return retorno;
    }
    private String criptografarSenha(String senha) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(senha.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    
}
public boolean autenticar(String email, String senha) throws Exception {
    Usuario usuario = getUsuarioByEmail(email);
    
    if (usuario != null) {
        String senhaCriptografada = criptografarSenha(senha);

        // Comparação entre a senha criptografada do banco e a senha fornecida
        return senhaCriptografada.equals(usuario.getSenha());
    }
    return false;
}

    public List<Usuario> buscar(String nome) throws Exception {
        java.sql.Connection conexao = Conexao.getConexao();
        String sql = "select * from usuario ";

        if (!nome.equals("")) {
            sql += " where id like ?";
        }

        sql += " order by id";

        List<Usuario> lista = new ArrayList<>();

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            if (!nome.equals("")) {
                ps.setString(1, "%" + nome + "%");
            }
            
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNome(rs.getString("nome"));
                    u.setEmail(rs.getString("email"));
                    u.setRg(rs.getString("rg"));
                    u.setCpf(rs.getString("cpf"));
                    lista.add(u);
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return lista;
    }
    
    public Usuario getUsuarioByEmail(String email) throws Exception {
    java.sql.Connection conexao = Conexao.getConexao();
    String sql = "SELECT * FROM usuario WHERE email = ?";
    
    Usuario obj = null;
    
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setString(1, email);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                obj = new Usuario();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
            }
        }
    } catch (Exception e) {
        throw e;
    }
    return obj;
}

    
    
    
    
    
    public void excluir(Integer id) throws Exception {
        String sql = "delete from usuario where id = ?";

        java.sql.Connection conexao = Conexao.getConexao();
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }
    
     public Usuario getUsuario(Integer id) throws Exception{
        java.sql.Connection conexao = Conexao.getConexao();
        String sql = "select * from usuario where id= ?";
        
        Usuario obj = null;
        
        try(PreparedStatement ps = conexao.prepareStatement(sql)){
          //parametros da Sql
            ps.setInt(1, id);
            
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    obj = new Usuario();
                    obj.setId(rs.getInt("id"));
                    obj.setNome(rs.getString("nome"));
                    obj.setEmail(rs.getString("email"));
                    obj.setSenha(rs.getString("senha"));
                    obj.setTelefone(rs.getString("telefone"));
                    obj.setRg(rs.getString("rg"));
                    obj.setCpf(rs.getString("cpf"));
                    obj.setResposta1(rs.getString("resposta1"));
                    obj.setResposta2(rs.getString("resposta2"));
                   
                  
                    obj.setTelefone(rs.getString("telefone"));
                   
                                     
                }
            }
            
            
        }catch(Exception e){
            throw e;
        }
        return obj;
    }
    
    




    public Usuario getUsuario(String email, String senha) throws Exception {
            java.sql.Connection conexao = Conexao.getConexao();
        String sql = "select * from usuario where email= ?";
        
        Usuario obj = null;
        
        try(PreparedStatement ps = conexao.prepareStatement(sql)){
          //parametros da Sql
            ps.setString(1, email);
            
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    obj = new Usuario();
                    
                    obj.setEmail(rs.getString("email"));
                    obj.setSenha(rs.getString("senha"));
                                     
                                     
                }
            }
            
            
        }catch(Exception e){
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
            String sql = "SELECT COUNT(*) FROM usuario WHERE rg = ?";
            
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
    java.sql.Connection conexao = Conexao.getConexao();
    String sql = "SELECT COUNT(*) FROM usuario WHERE cpf = ?";
    
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setString(1, cpf);
        try (ResultSet rs = ps.executeQuery()) {
            rs.next();
            int countAluno = rs.getInt(1);
            
            // Verificar na tabela usuario
            String sqlUsuario = "SELECT COUNT(*) FROM responsavel  WHERE cpf = ?";
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
 
    public Usuario consultarPerguntaSecreta(String nomeUsuario) {
    Usuario usuario = null;
    String sql = "SELECT pergunta1, resposta1, pergunta2, resposta2 FROM usuario WHERE nome = ?";

    try (java.sql.Connection conexao = Conexao.getConexao();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, nomeUsuario);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                usuario = new Usuario();
                // Obter informações do banco de dados
                usuario.setPergunta1(rs.getString("pergunta1"));
                usuario.setPergunta2(rs.getString("pergunta2"));
                usuario.setRespostaCorreta(rs.getString("resposta1"));  // Assumindo que resposta1 é a correta
            }
        }
    } catch (Exception e) {
        // Trate exceções adequadas aqui
        e.printStackTrace();
    }

    return usuario;
}


    
    


public String consultarRespostaCorreta(String nomeUsuario) {
    String respostaCorreta = null;
    String sql = "SELECT resposta1, resposta2 FROM usuario WHERE nome = ?";
    
    try (java.sql.Connection conexao = Conexao.getConexao();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {
        
        stmt.setString(1, nomeUsuario);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Escolhe aleatoriamente entre resposta1 e resposta2
                Random random = new Random();
                boolean escolherResposta1 = random.nextBoolean();
                
                if (escolherResposta1) {
                    respostaCorreta = rs.getString("resposta1");
                } else {
                    respostaCorreta = rs.getString("resposta2");
                }
            }
        }
    } catch (Exception e) {
        // Trate exceções adequadas aqui
        e.printStackTrace();
    }

    return respostaCorreta;
}
    public String consultarResposta1(String nomeUsuario) {
    String resposta1 = null;
    String sql = "SELECT resposta1 FROM usuario WHERE nome = ?";

    try (java.sql.Connection conexao = Conexao.getConexao();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, nomeUsuario);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                resposta1 = rs.getString("resposta1");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return resposta1;
}

public String consultarResposta2(String nomeUsuario) {
    String resposta2 = null;
    String sql = "SELECT resposta2 FROM usuario WHERE nome = ?";

    try (java.sql.Connection conexao = Conexao.getConexao();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, nomeUsuario);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                resposta2 = rs.getString("resposta2");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return resposta2;
}
public String consultarSenhaUsuario(String nomeUsuario) {
    String senhaUsuario = null;
    String sql = "SELECT senha FROM usuario WHERE nome = ?";

    try (java.sql.Connection conexao = Conexao.getConexao();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, nomeUsuario);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                senhaUsuario = rs.getString("senha");
            }
        }
    } catch (Exception e) {
        // Trate exceções adequadas aqui
        e.printStackTrace();
    }

    return senhaUsuario;
}
public Usuario getUsuarioByNome(String nome) throws Exception {
    java.sql.Connection conexao = Conexao.getConexao();
    String sql = "SELECT * FROM usuario WHERE nome = ?";

    Usuario obj = null;

    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        // Parâmetros da SQL
        ps.setString(1, nome);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                obj = new Usuario();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setPergunta1(rs.getString("pergunta1"));
                obj.setResposta1(rs.getString("resposta1"));
                obj.setPergunta2(rs.getString("pergunta2"));
                obj.setResposta2(rs.getString("resposta2"));
            }
        }

    } catch (Exception e) {
        throw e;
    }
    return obj;
}
public String consultarPergunta1(String nomeUsuario) {
    String pergunta1 = null;
    String sql = "SELECT pergunta1 FROM usuario WHERE nome = ?";

    try (java.sql.Connection conexao = Conexao.getConexao();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, nomeUsuario);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                pergunta1 = rs.getString("pergunta1");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return pergunta1;
}

public String consultarPergunta2(String nomeUsuario) {
    String pergunta2 = null;
    String sql = "SELECT pergunta2 FROM usuario WHERE nome = ?";

    try (java.sql.Connection conexao = Conexao.getConexao();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, nomeUsuario);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                pergunta2 = rs.getString("pergunta2");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return pergunta2;
}

public Usuario getUsuarioPeloNome(String nomeUsuario) throws SQLException, Exception {
    Usuario usuario = null;
    String sql = "SELECT * FROM usuario WHERE nome = ?";

    try (java.sql.Connection conexao = Conexao.getConexao();
         PreparedStatement ps = conexao.prepareStatement(sql)) {

        ps.setString(1, nomeUsuario);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                // Adicione os demais campos conforme necessário
            }
        }
    } catch (SQLException e) {
        // Trate a exceção de maneira adequada (pode imprimir uma mensagem, logar, ou relançar a exceção, se necessário)
        throw new Exception("Erro ao obter usuário pelo nome", e);
    }

    return usuario;
}

public int atualizar2(Usuario u, String nomeUsuario) throws Exception {
    int retorno;
    String sql = "UPDATE usuario SET senha = ? WHERE nome = ?";

    try (java.sql.Connection conexao = Conexao.getConexao();
         PreparedStatement ps = conexao.prepareStatement(sql)) {

        // Obtém o usuário atual do banco de dados
        //Usuario usuarioAtual = getUsuarioPeloNome(nomeUsuario);

        // Verifica se a senha foi alterada
        String senhaCriptografada = u.getSenha();
        //if (!senhaCriptografada.equals(usuarioAtual.getSenha())) {
            senhaCriptografada = criptografarSenha(u.getSenha());
        //}

        ps.setString(1, senhaCriptografada);
        ps.setString(2, nomeUsuario); // Adicione o nome do usuário à cláusula WHERE

        retorno = ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
        throw new Exception("Erro ao atualizar usuário", e);
    }

    return retorno;
}

public int atualizar(Usuario u) throws Exception {
    int retorno;
    String sql = "UPDATE usuario SET nome = ?, rg = ?, cpf = ?, email = ?, telefone = ?, senha = ?, resposta1 = ?, resposta2 = ? WHERE id = ?";

    java.sql.Connection conexao = Conexao.getConexao();
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setString(1, u.getNome());
        ps.setString(2, u.getRg());
        ps.setString(3, u.getCpf());
        ps.setString(4, u.getEmail());
        ps.setString(5, u.getTelefone());
        ps.setString(7, u.getResposta1());
        ps.setString(8, u.getResposta2());

        // Obtém o usuário atual do banco de dados
        Usuario usuarioAtual = getUsuario(u.getId());
        
        // Verifica se a senha foi alterada
        String senhaCriptografada = u.getSenha();
        if (!senhaCriptografada.equals(usuarioAtual.getSenha())) {
            senhaCriptografada = criptografarSenha(u.getSenha());
        }

        ps.setString(6, senhaCriptografada);
        ps.setInt(9, u.getId());

        retorno = ps.executeUpdate();
    }
    return retorno;
}

}
