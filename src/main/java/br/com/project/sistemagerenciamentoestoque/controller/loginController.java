package br.com.project.sistemagerenciamentoestoque.controller;

import br.com.project.sistemagerenciamentoestoque.model.dao.EstoqueDAO;
import br.com.project.sistemagerenciamentoestoque.model.dao.LoginDAO;
import br.com.project.sistemagerenciamentoestoque.model.database.Database;
import br.com.project.sistemagerenciamentoestoque.model.database.DatabaseFactory;
import br.com.project.sistemagerenciamentoestoque.model.domain.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController extends ReflectiveOperationException implements Initializable {

    @FXML
    private TextField txtFieldNomeUser;
    @FXML
    private TextField txtFieldSenhaUser;
    @FXML
    private TextField txtFieldConfirmaSenha;
    @FXML
    private Button btnUserDialogCancelar;
    @FXML
    private Button btnUserDialogEnviar;
    @FXML
    private RadioButton btnUsuario;
    @FXML
    private RadioButton btnAdiministrador;
    @FXML
    private ToggleGroup grupoTipoUsuario;

    private Stage stagePrimario;
    private Usuario usuario;

    public void setStage(Stage stage) {
        this.stagePrimario = stage;
    }

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final LoginDAO loginDAO = new LoginDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginDAO.setConnection(connection);
    }

    @FXML
    public void showCadastroUserDialog() throws IOException, SQLException {
        Stage newStage = new Stage();
        URL url = new File("src/main/java/br/com/project/sistemagerenciamentoestoque/view/userDialog.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        newStage.setTitle("Adicionar usuário");
        loginController cadastro = loader.getController();
        cadastro.setStage(newStage);
        newStage.setScene(scene);
        newStage.show();
    }


    public void UserDialogCancelar(){
        if(stagePrimario != null){
            this.stagePrimario.close();
        }
    }

    public void UserDialogEnviar() throws SQLException, IOException {
            try {
                loginDAO.setConnection(connection);
                if (!txtFieldSenhaUser.getText().isEmpty() && !txtFieldNomeUser.getText().isEmpty() && !txtFieldConfirmaSenha.getText().isEmpty()) {
                    Usuario usuario = new Usuario();

                    if (btnUsuario.isSelected()) {
                        usuario.setPermissao(false);
                    } else {
                        usuario.setPermissao(true);
                    }

                    if (txtFieldSenhaUser.getText().equals(txtFieldConfirmaSenha.getText())) {
                        usuario.setNome(txtFieldNomeUser.getText());
                        usuario.setSenha(txtFieldSenhaUser.getText());

                        if (usuario.getPermissao().equals(true)) {
                            usuario.setPermissao(true);
                        } else {
                            usuario.setPermissao(false);
                        }

                        boolean cadastroRealizado = loginDAO.inserirUser(usuario);

                        if (cadastroRealizado) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setContentText("Cadastro realizado com sucesso!");
                            alert.show();
                            this.stagePrimario.close();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Falha ao realizar o cadastro. Tente novamente.");
                            alert.show();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("As senhas não são iguais, tente novamente!");
                        alert.show();
                    }
                }
            }catch (IllegalArgumentException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.show();
            }
    }
}
