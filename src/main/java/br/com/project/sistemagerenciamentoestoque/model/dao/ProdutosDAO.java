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
                produtos.setId(rs.getInt("id_produto"));
                produtos.setDescricao(rs.getString("descricao"));
                produtos.setValor(rs.getDouble("valor"));
                retorno.add(produtos);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retorno;
    }

    public boolean removerProduto(Produtos produto){
        String sql = "DELETE from produtos where id_produto = (?)";
        try{
            PreparedStatement smtm = conn.prepareStatement(sql);
            smtm.setInt(1, produto.getId());
            smtm.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public boolean editarProduto(Produtos produto){
        String sql = "UPDATE produtos SET descricao = ?, valor = ? WHERE id_produto = ?";
        try{
            PreparedStatement smtm = conn.prepareStatement(sql);
            smtm.setString(1, produto.getDescricao());
            smtm.setDouble(2, produto.getValor());
            smtm.setInt(3, produto.getId());
            smtm.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produtos> likeProduto(String descricao){
        String sql = "SELECT * FROM produtos WHERE descricao LIKE ?";
        descricao = "%"+descricao+"%";
        Produtos produto = new Produtos();
        List<Produtos> retorno = new ArrayList<>();
        try{
            PreparedStatement smtm = conn.prepareStatement(sql);
            smtm.setString(1, descricao);
            ResultSet rs = smtm.executeQuery();
            while(rs.next()){
                Produtos produtos = new Produtos();
                produtos.setId(rs.getInt("id_produto"));
                produtos.setDescricao(rs.getString("descricao"));
                produtos.setValor(rs.getDouble("valor"));
                retorno.add(produtos);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retorno;
    }
}
