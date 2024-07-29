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

    private Scene scene;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void hyperlinkDesenvolvido() throws URISyntaxException, IOException {
        String url = "https://github.com/gustavorieg";
        Desktop.getDesktop().browse(new URI(url));
    }

    @FXML
    protected void entrarTelaPrincipal(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/br/com/project/sistemagerenciamentoestoque/view/telaPrincipal.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gerenciamento de Estoque");
        stage.setScene(scene);

        telaPrincipalController controller = loader.getController();
        controller.setStage(this.stage);

        stage.show();
    }

    @FXML
    public void sairTelaDeEntrada(){
        this.stage.close();
    }

}
