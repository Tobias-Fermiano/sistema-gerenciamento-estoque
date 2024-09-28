package br.com.project.sistemagerenciamentoestoque.model.dao;

import br.com.project.sistemagerenciamentoestoque.model.domain.Estoque;
import br.com.project.sistemagerenciamentoestoque.model.domain.Produtos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;

public class EstoqueDAO {
    private Connection conn;

    public Connection getConnection() {
        return conn;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public boolean inserir(Produtos Produto, int quantidade, String movimento){
        String findProdutoEstoque = "SELECT * FROM estoque WHERE id_produto = ?";
        //Produtos produto = new Produtos();
        try{
            PreparedStatement stmtProdutoEstoque = conn.prepareStatement(findProdutoEstoque);
            stmtProdutoEstoque.setInt(1, Produto.getId());
            ResultSet rs = stmtProdutoEstoque.executeQuery();
            if (movimento.equals("E")){
                if (rs.next()){
                    String updateEntrada = "UPDATE estoque SET quantidade = quantidade + ?, movimento = ? WHERE id_produto = ?";
                    try{
                        PreparedStatement updateProdutoEstoque = conn.prepareStatement(updateEntrada);
                        updateProdutoEstoque.setInt(1, quantidade);
                        updateProdutoEstoque.setString(2, movimento);
                        updateProdutoEstoque.setInt(3, Produto.getId());
                        updateProdutoEstoque.executeUpdate();
                    }  catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    String insertProdutoEstoque = "INSERT INTO estoque(id_produto, quantidade, data, movimento) VALUES(?,?,?,?)";
                    Date dataAtual = Date.valueOf(LocalDate.now());
                    try{
                        PreparedStatement stmtInsertProdutoEstoque = conn.prepareStatement(insertProdutoEstoque);
                        stmtInsertProdutoEstoque.setInt(1, Produto.getId());
                        stmtInsertProdutoEstoque.setInt(2, quantidade);
                        stmtInsertProdutoEstoque.setDate(3, dataAtual);
                        stmtInsertProdutoEstoque.setString(4, movimento);
                        stmtInsertProdutoEstoque.execute();
                    }  catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                String updateSaida = "UPDATE estoque SET quantidade = quantidade - ?, movimento =  ? WHERE id_produto = ?";
                try{
                    PreparedStatement updateProdutoEstoque = conn.prepareStatement(updateSaida);
                    updateProdutoEstoque.setInt(1, quantidade);
                    updateProdutoEstoque.setString(2, movimento);
                    updateProdutoEstoque.setInt(3, Produto.getId());
                    updateProdutoEstoque.executeUpdate();
                }  catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public List<Estoque> listEstoque(){
        String sql = "SELECT * FROM estoque";
        List<Estoque> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String sql2 = "SELECT * FROM produtos WHERE id_produto = ?";
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                stmt2.setInt(1, rs.getInt("id_produto"));
                ResultSet rs2 = stmt2.executeQuery();
                Estoque estoque = null;
                while (rs2.next()) {
                    estoque = new Estoque();
                    estoque.setId(rs.getInt("id_produto"));
                    estoque.setDescricao(rs2.getString("descricao"));
                    estoque.setQuantidade(rs.getInt("quantidade"));
                    estoque.setValorUnit(rs2.getDouble("valor"));
                    estoque.setValorTotal(rs.getInt("quantidade")*(rs2.getDouble("valor")));
                    retorno.add(estoque);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retorno;
    }
}