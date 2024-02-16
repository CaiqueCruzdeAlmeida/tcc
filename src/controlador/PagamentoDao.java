/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Aluno;
import modelo.AlunosPorTurma;
import modelo.Pagamentos;
import visaotcc.Pagamento;


/**
 *
 * @author Caique
 */
public class PagamentoDao {
    
     public int inserir(Pagamentos u) throws Exception {
        int retorno = 0;
        String sql = "INSERT INTO pagamentos (datapagamento, valorpago, mesreferencia, anoreferencia, nometurma, formadepagamento, nomealuno) "
                +  "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conexao = Conexao.getConexao();

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            // Ajuste para usar um java.sql.Date no PreparedStatement
            java.sql.Date dataPagamentoSql = new java.sql.Date(u.getDatapagamento().getTime());

            ps.setDate(1, dataPagamentoSql);
            ps.setDouble(2, u.getValorpago());
            ps.setString(3, u.getMesderefenencia());
            ps.setInt(4, u.getAnoreferencia());
            ps.setString(5, u.getTurmadoaluno());
            ps.setString(6, u.getFormapagamento());
            ps.setString(7, u.getNomealuno());

            retorno = ps.executeUpdate();

        }

        return retorno;
}
    
     
     public void excluir(Integer codPagamentos ) throws Exception {
        String sql = "delete from pagamentos  where id = ?";

        Connection conexao = Conexao.getConexao();
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, codPagamentos );

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }
     
     
  public List<Pagamentos> buscar() throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "select * from pagamentos";

    List<Pagamentos> lista = new ArrayList<>();

    try (PreparedStatement ps = conexao.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Pagamentos u = new Pagamentos();
            u.setAnoreferencia(rs.getInt("anoreferencia"));
            u.setFormapagamento(rs.getString("formadepagamento"));
            u.setMesderefenencia(rs.getString("mesreferencia"));
            u.setTurmadoaluno(rs.getString("nometurma"));
            u.setValorpago(rs.getDouble("valorpago"));
            u.setId(rs.getInt("id"));
            u.setNomealuno(rs.getString("nomealuno"));

            // Recuperando a data do banco como java.sql.Date
            java.sql.Date dataPagamentoSql = rs.getDate("datapagamento");
            // Convertendo para java.util.Date
            java.util.Date dataPagamento = new java.util.Date(dataPagamentoSql.getTime());

            u.setDatapagamento(dataPagamento);
            lista.add(u);
        }
    } catch (Exception e) {
        throw e;
    } finally {
        // Certifique-se de fechar a conexão após o uso
        if (conexao != null) {
            conexao.close();
        }
    }

    return lista;
}

   public List<Pagamentos> buscar2() throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "select * from pagamentos";

    List<Pagamentos> lista = new ArrayList<>();

    try (PreparedStatement ps = conexao.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Pagamentos u = new Pagamentos();
            u.setAnoreferencia(rs.getInt("anoreferencia"));
            u.setFormapagamento(rs.getString("formadepagamento"));
            u.setMesderefenencia(rs.getString("mesreferencia"));
            u.setTurmadoaluno(rs.getString("nometurma"));
            u.setValorpago(rs.getDouble("valorpago"));
            u.setNomealuno(rs.getString("nomealuno"));

            // Recuperando a data do banco como java.sql.Date
            java.sql.Date dataPagamentoSql = rs.getDate("datapagamento");
            // Convertendo para java.util.Date
            java.util.Date dataPagamento = new java.util.Date(dataPagamentoSql.getTime());

            u.setDatapagamento(dataPagamento);
            lista.add(u);
        }
    } catch (Exception e) {
        throw e;
    } finally {
        // Certifique-se de fechar a conexão após o uso
        if (conexao != null) {
            conexao.close();
        }
    }

    return lista;
}


   public String buscarNomeAlunoPorIdTurma(int idTurmaPorAluno) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "SELECT aluno.nome " +
                 "FROM aluno " +
                 "INNER JOIN turmaporaluno ON aluno.id = turmaporaluno.idaluno " +
                 "WHERE turmaporaluno.id = ?";

    String nomeAluno = "";

    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setInt(1, idTurmaPorAluno);

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
public String buscarNomeTurmaPorIdTurma(int idTurmaPorAluno) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "SELECT turma.nometurma " +
                 "FROM turma " +
                 "INNER JOIN turmaporaluno ON turma.id = turmaporaluno.idturma " +
                 "WHERE turmaporaluno.id = ?";

    String nomeTurma = "";

    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setInt(1, idTurmaPorAluno);

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

public Pagamentos getPagamentos(int id) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "select * from pagamentos where id = ?";

    Pagamentos obj = null;

    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        //parametros da Sql
        ps.setInt(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                obj = new Pagamentos();
                obj.setAnoreferencia(rs.getInt("anoreferencia"));
                obj.setId(rs.getInt("id"));
                obj.setFormapagamento(rs.getString("formadepagamento"));
                obj.setMesderefenencia(rs.getString("mesreferencia"));
                obj.setTurmadoaluno(rs.getString("nometurma"));
                obj.setValorpago(rs.getDouble("valorpago"));
                obj.setNomealuno(rs.getString("nomealuno"));
                // Recuperando a data do banco como java.sql.Date
                java.sql.Date dataPagamentoSql = rs.getDate("datapagamento");
                // Convertendo para java.util.Date
                java.util.Date dataPagamento = new java.util.Date(dataPagamentoSql.getTime());

                obj.setDatapagamento(dataPagamento);
            }
        }

    } catch (Exception e) {
        throw e;
    }
    return obj;
}

    public Pagamentos buscarPorId(int id) throws Exception {
    Pagamentos pagamentos = null;

    String sql = "SELECT * FROM pagamentos WHERE codPagamentos = ?";
    Connection conexao = Conexao.getConexao(); 
    
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setInt(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                pagamentos = new Pagamentos();
                pagamentos.setId(rs.getInt("codPagamentos"));
                pagamentos.setAnoreferencia(rs.getInt("anoreferencia"));
                pagamentos.setId(rs.getInt("id"));
                pagamentos.setFormapagamento(rs.getString("formapagamento"));
                pagamentos.setMesderefenencia(rs.getString("mesreferencia"));
                pagamentos.setTurmadoaluno(rs.getString("nometurma"));
                pagamentos.setValorpago(rs.getDouble("valorpago"));
                pagamentos.setNomealuno(rs.getString("nomealuno"));

                // Recuperando a data do banco como java.sql.Date
                java.sql.Date dataPagamentoSql = rs.getDate("datapagamento");
                // Convertendo para java.util.Date
                java.util.Date dataPagamento = new java.util.Date(dataPagamentoSql.getTime());

                pagamentos.setDatapagamento(dataPagamento);
            }
        }
    }

    return pagamentos;
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

    public List<Pagamentos> buscar(String string) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "select * from pagamentos";

    List<Pagamentos> lista = new ArrayList<>();

    try (PreparedStatement ps = conexao.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Pagamentos u = new Pagamentos();
            u.setAnoreferencia(rs.getInt("anoreferencia"));
            u.setFormapagamento(rs.getString("formadepagamento"));
            u.setMesderefenencia(rs.getString("mesreferencia"));
            u.setTurmadoaluno(rs.getString("nometurma"));
            u.setValorpago(rs.getDouble("valorpago"));
            u.setId(rs.getInt("id"));
            u.setNomealuno(rs.getString("nomealuno"));

            // Recuperando a data do banco como java.sql.Date
            java.sql.Date dataPagamentoSql = rs.getDate("datapagamento");
            // Convertendo para java.util.Date
            java.util.Date dataPagamento = new java.util.Date(dataPagamentoSql.getTime());

            u.setDatapagamento(dataPagamento);
            lista.add(u);
        }
    } catch (Exception e) {
        throw e;
    } finally {
        // Certifique-se de fechar a conexão após o uso
        if (conexao != null) {
            conexao.close();
        }
    }

    return lista;
}
    public List<Pagamentos> buscarPorAno(String string, int anoReferencia) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "SELECT * FROM pagamentos WHERE anoreferencia = ?";

    List<Pagamentos> lista = new ArrayList<>();

    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setInt(1, anoReferencia);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Pagamentos u = new Pagamentos();
                u.setAnoreferencia(rs.getInt("anoreferencia"));
                u.setFormapagamento(rs.getString("formadepagamento"));
                u.setMesderefenencia(rs.getString("mesreferencia"));
                u.setTurmadoaluno(rs.getString("nometurma"));
                u.setValorpago(rs.getDouble("valorpago"));
                u.setId(rs.getInt("id"));
                u.setNomealuno(rs.getString("nomealuno"));

                // Recuperando a data do banco como java.sql.Date
                java.sql.Date dataPagamentoSql = rs.getDate("datapagamento");
                // Convertendo para java.util.Date
                java.util.Date dataPagamento = new java.util.Date(dataPagamentoSql.getTime());

                u.setDatapagamento(dataPagamento);
                lista.add(u);
            }
        }
    } catch (Exception e) {
        throw e;
    } finally {
        // Certifique-se de fechar a conexão após o uso
        if (conexao != null) {
            conexao.close();
        }
    }

    return lista;
}


    public int atualizar(Pagamentos u) throws Exception {
    int retorno;
    Connection conexao = Conexao.getConexao();       
         

    String sql = "UPDATE pagamentos SET datapagamento = ?, valorpago = ?, mesreferencia = ?, anoreferencia = ?, nometurma = ?, formadepagamento = ?, nomealuno = ? WHERE id = ?";


    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        // Convertendo de java.util.Date para java.sql.Date
        java.sql.Date dataPagamentoSql = new java.sql.Date(u.getDatapagamento().getTime());

        ps.setDate(1, dataPagamentoSql);
        ps.setDouble(2, u.getValorpago());
        ps.setString(3, u.getMesderefenencia());
        ps.setInt(4, u.getAnoreferencia());
        ps.setString(5, u.getTurmadoaluno());
        ps.setString(6, u.getFormapagamento());
        ps.setString(7,u.getNomealuno());
        ps.setInt(8, u.getId());

        retorno = ps.executeUpdate();
    }

    return retorno;
}
public List<Pagamentos> buscarPagamentosPorTurma(int idTurma) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "SELECT p.* FROM pagamentos p " +
                 "JOIN turmaporaluno tpa ON p.turmasdoaluno_id = tpa.id " +
                 "WHERE tpa.idturma = ?";

    List<Pagamentos> lista = new ArrayList<>();
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setInt(1, idTurma);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Pagamentos pagamento = new Pagamentos();
                pagamento.setMesderefenencia(rs.getString("mesreferencia"));
                pagamento.setAnoreferencia(rs.getInt("anoreferencia"));
                // Defina outros atributos do pagamento conforme sua estrutura de dados

                lista.add(pagamento);
            }
        }
    } catch (Exception e) {
        throw e;
    }

    return lista;
}

    
}
