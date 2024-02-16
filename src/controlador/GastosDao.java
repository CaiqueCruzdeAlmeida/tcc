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
import modelo.Gastos;
import modelo.Pagamentos;


/**
 *
 * @author Caique
 */
public class GastosDao {
    public int inserir(Gastos u) throws Exception {
    int retorno = 0;
    String sql = "INSERT INTO gastos (nomeproduto, valorpago, datacompra, formapagamento, parcelas, quantidade) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    Connection conexao = Conexao.getConexao();

    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        // Ajuste para usar um java.sql.Date no PreparedStatement
        java.sql.Date dataCompraSql = new java.sql.Date(u.getDatacompra().getTime());

        ps.setString(1, u.getNomeproduto());
        ps.setDouble(2, u.getValorpago());
        ps.setDate(3, dataCompraSql);
        ps.setString(4, u.getFormapagamento());
        ps.setString(5, u.getParcelas());
        ps.setInt(6, u.getQuantidade());
//        ps.setInt(7, u.getAnoreferencia());
//        ps.setString(8,u.getMesreferencia());
        

        retorno = ps.executeUpdate();
    }

    return retorno;
}
public void excluir(Integer codPagamentos ) throws Exception {
        String sql = "delete from gastos  where id = ?";

        Connection conexao = Conexao.getConexao();
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, codPagamentos );

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }
     
    public List<Gastos> buscar() throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "select * from gastos";

    List<Gastos> lista = new ArrayList<>();

    try (PreparedStatement ps = conexao.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Gastos u = new Gastos();
            u.setNomeproduto(rs.getString("nomeproduto"));
            u.setDatacompra(rs.getDate("datacompra"));
            u.setParcelas(rs.getString("parcelas"));
            u.setFormapagamento(rs.getString("formapagamento"));
            u.setValorpago(rs.getDouble("valorpago"));
            u.setQuantidade(rs.getInt("quantidade"));
//            u.setAnoreferencia(rs.getInt("anoreferencia"));
//            u.setMesreferencia(rs.getString("mesreferencia"));
            u.setId(rs.getInt("id"));

            // Recuperando a data do banco como java.sql.Date
            java.sql.Date dataPagamentoSql = rs.getDate("datacompra");
            // Convertendo para java.util.Date
            java.util.Date dataCompra = new java.util.Date(dataPagamentoSql.getTime());

            u.setDatacompra(dataCompra);
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
    
    public List<Gastos> buscar(String string) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "select * from gastos";

    List<Gastos> lista = new ArrayList<>();

    try (PreparedStatement ps = conexao.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Gastos u = new Gastos();
            u.setNomeproduto(rs.getString("nomeproduto"));
            u.setDatacompra(rs.getDate("datacompra"));
            u.setParcelas(rs.getString("parcelas"));
            u.setFormapagamento(rs.getString("formapagamento"));
            u.setValorpago(rs.getDouble("valorpago"));
            u.setId(rs.getInt("id"));

            // Recuperando a data do banco como java.sql.Date
            java.sql.Date dataPagamentoSql = rs.getDate("datacompra");
            // Convertendo para java.util.Date
            java.util.Date dataCompra = new java.util.Date(dataPagamentoSql.getTime());

            u.setDatacompra(dataCompra);
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
    public List<Gastos> buscarPorNome(String nome) throws Exception {
        Connection conexao = Conexao.getConexao();
        String sql = "SELECT * FROM gastos WHERE nomeproduto = ?";
        List<Gastos> lista = new ArrayList<>();

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Gastos gastos = new Gastos();
                    gastos.setId(rs.getInt("id"));
                    gastos.setNomeproduto(rs.getString("nomeproduto"));
                    gastos.setDatacompra(rs.getDate("datacompra"));
                    gastos.setParcelas(rs.getString("parcelas"));
                    gastos.setFormapagamento(rs.getString("formapagamento"));
                    gastos.setValorpago(rs.getDouble("valorpago"));
//                    gastos.setMesreferencia(rs.getString("mesreferencia"));
//                    gastos.setAnoreferencia(rs.getInt("anoreferencia"));
                    gastos.setQuantidade(rs.getInt("quantidade"));

                    lista.add(gastos);
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
    public Gastos getGastos(int id) throws Exception {
    Connection conexao = Conexao.getConexao();
    String sql = "select * from gastos where id = ?";

    Gastos obj = null;

    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        //parametros da Sql
        ps.setInt(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                obj = new Gastos();
//                obj.setAnoreferencia(rs.getInt("anoreferencia"));
                obj.setId(rs.getInt("id"));
                obj.setFormapagamento(rs.getString("formapagamento"));
//                obj.setMesreferencia(rs.getString("mesreferencia"));
//                obj.setAnoreferencia(rs.getInt("anoreferencia"));
                obj.setValorpago(rs.getDouble("valorpago"));
                obj.setParcelas(rs.getString("parcelas"));
                obj.setQuantidade(rs.getInt("quantidade"));
                obj.setNomeproduto(rs.getString("nomeproduto"));
                // Recuperando a data do banco como java.sql.Date
                java.sql.Date dataPagamentoSql = rs.getDate("datacompra");
                // Convertendo para java.util.Date
                java.util.Date dataCompra = new java.util.Date(dataPagamentoSql.getTime());

                obj.setDatacompra(dataCompra);
            }
        }

    } catch (Exception e) {
        throw e;
    }
    return obj;
}
    public int atualizar(Gastos u) throws Exception {
    int retorno;
    Connection conexao = Conexao.getConexao();       
         

    String sql = "UPDATE gastos SET  formapagamento = ?, valorpago = ?, parcelas = ?, quantidade = ?, nomeproduto = ?, datacompra = ? WHERE id = ?";


    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        // Convertendo de java.util.Date para java.sql.Date
        java.sql.Date datacompraSql = new java.sql.Date(u.getDatacompra().getTime());

        
        //ps.setInt(1, u.getAnoreferencia());
        ps.setString(1, u.getFormapagamento());
        //ps.setString(3, u.getMesreferencia());
        ps.setDouble(2, u.getValorpago());
        ps.setString(3,u.getParcelas());
        ps.setInt(4,u.getQuantidade());
        ps.setString(5,u.getNomeproduto());
        ps.setDate(6, datacompraSql);
        ps.setInt(7, u.getId());

        retorno = ps.executeUpdate();
    }

    return retorno;
}
}
