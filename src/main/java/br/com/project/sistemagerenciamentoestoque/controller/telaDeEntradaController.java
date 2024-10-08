package br.com.project.sistemagerenciamentoestoque.controller;

import br.com.project.sistemagerenciamentoestoque.model.dao.LoginDAO;
import br.com.project.sistemagerenciamentoestoque.model.database.Database;
import br.com.project.sistemagerenciamentoestoque.model.database.DatabaseFactory;
import br.com.project.sistemagerenciamentoestoque.model.domain.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class telaDeEntradaController implements Initializable {

    @FXML
    private Hyperlink hyperlinkDesenvolvido;
    @FXML
    private Button btnEntrar;
    @FXML
    private Button btnSair;
    @FXML
    private javafx.scene.control.TextField txtFieldUsuario;
    @FXML
    private javafx.scene.control.TextField txtFieldSenha;

    private Scene scene;
    private Stage stage;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final LoginDAO loginDAO = new LoginDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginDAO.setConnection(connection);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void showCadastroUserDialog() throws SQLException, IOException {
        loginController cadastro = new loginController();
        cadastro.setStage(stage);
        cadastro.showCadastroUserDialog();
    }

    @FXML
    public void hyperlinkDesenvolvido() throws URISyntaxException, IOException {
        String[] urls = {
            "https://github.com/CassioVSouza",
            "https://github.com/gustavorieg",
            "https://github.com/Tobias-Fermiano"
        };

        for (String url : urls){
            Desktop.getDesktop().browse(new URI(url));
        }
    }

    @FXML
    protected void validaUser() throws SQLException, URISyntaxException {
        Usuario usuario = new Usuario();

        try{
            if(!txtFieldUsuario.getText().isEmpty() && !txtFieldSenha.getText().isEmpty()) {
                usuario.setNome(txtFieldUsuario.getText());
                usuario.setSenha(txtFieldSenha.getText());

                if(loginDAO.verificaUsuario(usuario)) {
                    entrarTelaPrincipal(usuario);
                }
            }
        } catch(IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void entrarTelaPrincipal(Usuario usuario) throws IOException {
        String fxml = usuario.getPermissao() ? "src/main/java/br/com/project/sistemagerenciamentoestoque/view/telaPrincipal.fxml" : "src/main/java/br/com/project/sistemagerenciamentoestoque/view/telaPrincipalUser.fxml";

        try {
            URL url = new File(fxml).toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setTitle("Gerenciamento de Estoque");
            newStage.setResizable(false);

            telaPrincipalController controller = loader.getController();
            controller.setStage(newStage);
            newStage.show();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao carregar a tela: " + e.getMessage());
            alert.show();
        } finally {
            if (stage != null) {
                stage.close();
            }
        }
    }

    @FXML
    public void sairTelaDeEntrada(){
        if(stage != null){
            this.stage.close();
        }
    }

}
