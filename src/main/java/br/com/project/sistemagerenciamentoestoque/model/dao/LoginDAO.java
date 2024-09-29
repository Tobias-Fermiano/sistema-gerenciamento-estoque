package br.com.project.sistemagerenciamentoestoque.model.dao;

import br.com.project.sistemagerenciamentoestoque.model.domain.Produtos;
import br.com.project.sistemagerenciamentoestoque.model.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAO {

        private Connection conn;

        public Connection getConnection(){
            return conn;
        }
        public void setConnection(Connection conn){
            this.conn = conn;
        }

        public boolean inserirUser(Usuario usuario) throws SQLException {
            String sql = "INSERT INTO usuario(nome, senha) VALUES(?,?)";
            try{
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getSenha());
                stmt.execute();
                return true;
            } catch (SQLException ex){
                Logger.getLogger(br.com.project.sistemagerenciamentoestoque.model.dao.LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

        public boolean updateUser(Usuario usuario) throws SQLException {
            String sql = "UPDATE usuario set nome = ?, senha = ? where id = (?)";
            try{
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getSenha());
                stmt.execute();
                return true;
            } catch (SQLException ex){
                Logger.getLogger(br.com.project.sistemagerenciamentoestoque.model.dao.LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

        public List<Usuario> listarUsuarios() throws SQLException{
            String sql = "SELECT * from usuario";
            List<Usuario> users = new ArrayList<>();
            try{
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    Usuario usuarios = new Usuario("", "");
                    usuarios.setNome(rs.getString("nome"));
                    usuarios.setSenha(rs.getString("senha"));
                    users.add(usuarios);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return users;
        }
}
