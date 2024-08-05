package br.com.project.sistemagerenciamentoestoque.model.dao;

import br.com.project.sistemagerenciamentoestoque.model.domain.Estoque;
import br.com.project.sistemagerenciamentoestoque.model.domain.Produtos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstoqueDAO {
    private Connection conn;

    public Connection getConnection() {
        return conn;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public List<Estoque> listEstoque(){
        String sql = "select * from estoque";
        List<Estoque> retorno = new ArrayList<>();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String sqlProduto = "select * from produto where id = ?";
                Produtos produto = new Produtos();
                try{
                    PreparedStatement stmtProduto = conn.prepareStatement(sqlProduto);
                    stmtProduto.setInt(1, rs.getInt("id"));
                    ResultSet rsProduto = stmtProduto.executeQuery();
                    rsProduto.next();
                    produto.setId(rsProduto.getInt("id"));
                    produto.setDescricao(rsProduto.getString("descricao"));
                    produto.setValor(rsProduto.getDouble("valor"));
                } catch (SQLException e){
                    throw new RuntimeException(e);
                }
                Estoque estoque = new Estoque();
                estoque.setId(produto.getId());
                estoque.setDescricao(produto.getDescricao());
                estoque.setQuantidade(rs.getInt("quantidade"));
                estoque.setValorTotal(rs.getInt("quantidade") * produto.getValor());
                estoque.setValorUnit(produto.getValor());
                retorno.add(estoque);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retorno;
    }
}