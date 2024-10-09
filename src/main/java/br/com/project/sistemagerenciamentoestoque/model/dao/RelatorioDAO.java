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

public class RelatorioDAO {
    private Connection conn;

    public Connection getConnection(){
        return conn;
    }
    public void setConnection(Connection conn){
        this.conn = conn;
    }

    public int contarProdutos() {
        String sql = "SELECT COUNT(*) AS total FROM produtos";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public int contarUsuarios() {
        String sql = "SELECT COUNT(*) AS total FROM usuario";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public double somarValores() {
        String sql = "SELECT SUM(valor) AS total FROM produtos";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0.0;
    }

    public int contarEstoqueUltimos30Dias() {
        String sql = "SELECT COUNT(*) AS total FROM estoque WHERE data >= NOW() - INTERVAL '30 days'";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public int contarMovimentoEntrada() {
        String sql = "SELECT COUNT(*) AS total FROM estoque WHERE movimento = 'E'";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public int contarMovimentoSaida() {
        String sql = "SELECT COUNT(*) AS total FROM estoque WHERE movimento = 'S'";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public double somarQuantidadeEstoque() {
        String sql = "SELECT SUM(quantidade) AS total FROM estoque";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0.0;
    }
}
