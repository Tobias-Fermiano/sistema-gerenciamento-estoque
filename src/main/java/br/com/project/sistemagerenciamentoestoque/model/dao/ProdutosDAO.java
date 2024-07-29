package br.com.project.sistemagerenciamentoestoque.model.dao;

import br.com.project.sistemagerenciamentoestoque.model.domain.Produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutosDAO {
    private Connection conn;

    public Connection getConnection(){
        return conn;
    }
    public void setConnection(Connection conn){
        this.conn = conn;
    }

    public boolean inserir(Produtos produto) throws SQLException {
        String sql = "INSERT INTO produtos(descricao, valor) VALUES(?,?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getValor());
            stmt.execute();
            return true;
        } catch (SQLException ex){
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Produtos> listar(){
        String sql = "SELECT * from produtos";
        List<Produtos> retorno = new ArrayList<>();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Produtos produtos = new Produtos();
                produtos.setId(rs.getInt("id"));
                produtos.setDescricao(rs.getString("descricao"));
                produtos.setValor(rs.getDouble("valor"));
                retorno.add(produtos);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retorno;
    }

    public Produtos findProduto() throws SQLException {
        String sql = "SELECT * FROM produtos ORDER BY id DESC LIMIT 1;";
        Produtos produto = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int id = rs.getInt("id");
                sql = "SELECT * FROM produtos WHERE id = ?";
                try (PreparedStatement stmt2 = conn.prepareStatement(sql)) {
                    stmt2.setInt(1, id);
                    try (ResultSet rs2 = stmt2.executeQuery()) {
                        if (rs2.next()) {
                            produto = new Produtos();
                            produto.setId(rs2.getInt("id"));
                            produto.setDescricao(rs2.getString("descricao"));
                            produto.setValor(rs2.getDouble("valor"));
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
                    throw ex;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return produto;
    }

    public boolean removerProduto(Produtos produto){
        String sql = "DELETE from produtos where id = (?)";
        try{
            PreparedStatement smtm = conn.prepareStatement(sql);
            smtm.setInt(1, produto.getId());
            smtm.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
