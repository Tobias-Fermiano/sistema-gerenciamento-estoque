package br.com.project.sistemagerenciamentoestoque.model.database;

import java.sql.*;

public class Database {

    public Connection conectar() {
        try {
            //conexão original do rieg "jdbc:postgresql://localhost:5432/sistema_estoque", "postgres", "gustavorieg"
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistema_estoque", "postgres", "12345");
            return conn;
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + e.getMessage());
            return null;
        }
    }
}
