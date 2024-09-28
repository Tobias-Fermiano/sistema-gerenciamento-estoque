package br.com.project.sistemagerenciamentoestoque.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class telaDeEntradaController implements Initializable {

    @FXML
    private Hyperlink hyperlinkDesenvolvido;
    @FXML
    private Button btnEntrar;
    @FXML
    private Button btnSair;
    @FXML
    private TextField textFieldUsuario;
    @FXML
    private TextField textFieldSenha;

    private Scene scene;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void validaUser(TextField textFieldUsuario, TextField textFieldSenha) {
        this.textFieldUsuario = textFieldUsuario;
        this.textFieldSenha = textFieldSenha;

        //após isto, fazer uma chamada para uma classe DAO.
    }

    @FXML
    public void hyperlinkDesenvolvido() throws URISyntaxException, IOException {
        String url1 = "https://github.com/CassioVSouza";
        String url2 = "https://github.com/gustavorieg";
        String url3 = "https://github.com/Tobias-Fermiano";

        Desktop.getDesktop().browse(new URI(url1));
        Desktop.getDesktop().browse(new URI(url2));
        Desktop.getDesktop().browse(new URI(url3));
    }

    @FXML
    protected void entrarTelaPrincipal(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/br/com/project/sistemagerenciamentoestoque/view/telaPrincipal.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("Gerenciamento de Estoque");
        newStage.setResizable(false);
        newStage.setScene(scene);

        telaPrincipalController controller = loader.getController();
        controller.setStage(newStage);

        newStage.show();
        stage.close();
    }

    @FXML
    public void sairTelaDeEntrada(){
        this.stage.close();
    }

}
