package br.com.project.sistemagerenciamentoestoque.model.dao;
import br.com.project.sistemagerenciamentoestoque.model.domain.Usuario;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAO {

        private Connection connection;

        public void setConnection(Connection conn){
            this.connection = conn;
        }

        public Connection getConnection(){
            return connection;
        }

        public boolean inserirUser(Usuario usuario) throws SQLException {
            String sql = "INSERT INTO usuario(nome, senha, permissao) VALUES(?,?,?)";
            try{
                System.out.println(connection);
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getSenha());
                stmt.setBoolean(3, usuario.getPermissao());
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
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getSenha());
                stmt.execute();
                return true;
            } catch (SQLException ex){
                Logger.getLogger(br.com.project.sistemagerenciamentoestoque.model.dao.LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

        public boolean verificaUsuario(Usuario usuario) throws SQLException {
            String sql = "SELECT * FROM usuario WHERE nome = (?) and senha = (?)";

            try{
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getSenha());
                ResultSet rs = stmt.executeQuery();
                rs.next();
            }catch(SQLException ex){
                throw new RuntimeException(ex);
            }
            return true;
        }

        public List<Usuario> listarUsuarios() throws SQLException{
            String sql = "SELECT * from usuario";
            List<Usuario> users = new ArrayList<>();
            try{
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    Usuario usuarios = new Usuario("", "", false);
                    usuarios.setNome(rs.getString("nome"));
                    usuarios.setSenha(rs.getString("senha"));
                    usuarios.setPermissao(rs.getBoolean("permissao"));
                    users.add(usuarios);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return users;
        }
}
