package br.com.project.sistemagerenciamentoestoque.controller;

import br.com.project.sistemagerenciamentoestoque.model.dao.LoginDAO;
import br.com.project.sistemagerenciamentoestoque.model.domain.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private javafx.scene.control.TextField txtFieldNomeUser;
    @FXML
    private javafx.scene.control.TextField txtFieldSenhaUser;
    @FXML
    private Button btnUserDialogCancelar;
    @FXML
    private Button btnUserDialogEnviar;

    private Stage stage;
    private Usuario usuario;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void showCadastroUserDialog() throws IOException, SQLException {
        Stage newStage = new Stage();
        URL url = new File("src/main/java/br/com/project/sistemagerenciamentoestoque/view/userDialog.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        newStage.setScene(scene);
        newStage.setTitle("Editar usuário");
        newStage.setScene(scene);
        cadastrosUserDialog(usuario);
        newStage.showAndWait();
        loginController cadastro = loader.getController();
        cadastro.setStage(stage); // Passar a referência do stage
    }

    public void cadastrosUserDialog(Usuario usuario) throws SQLException, IOException {

        try{
            if (!txtFieldSenhaUser.getText().isEmpty() && !txtFieldNomeUser.getText().isEmpty()) {
                usuario.setNome(txtFieldNomeUser.getText());
                usuario.setSenha(txtFieldSenhaUser.getText());
                LoginDAO cadastro = new LoginDAO();
                cadastro.inserirUser(usuario);
            }
        }catch (ExceptionInInitializerError e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Preencha todos os campos necessários!");
            alert.show();
            System.out.println(e.getMessage());
        }catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    public void UserDialogCancelar() {
        this.stage.close();
    }

    public void UserDialogEnviar(){
        usuario.setNome(txtFieldNomeUser.getText());
        usuario.setSenha(txtFieldSenhaUser.getText());
        this.stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

//    public void setUsuario(Usuario usuario){
//        this.usuario = usuario;
//        this.txtFieldNomeUser.setText(usuario.getNome());
//        this.txtFieldSenhaUser.setText(usuario.getSenha());
//    }

}
