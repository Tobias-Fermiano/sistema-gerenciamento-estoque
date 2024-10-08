package br.com.project.sistemagerenciamentoestoque.model.database;

import java.sql.*;

public class Database {

    public Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistema_estoque", "postgres", "gustavorieg");
            return conn;
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + e.getMessage());
            return null;
        }
    }
}
