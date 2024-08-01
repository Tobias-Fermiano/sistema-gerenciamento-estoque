package br.com.project.sistemagerenciamentoestoque.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class telaPrincipalController implements Initializable {

    @FXML
    private Button btnPrinpalProdutos;
    @FXML
    private Button btnPrinpalEstoque;
    @FXML
    private Button btnPrincipalSobre;
    @FXML
    private Button btnPrincipalSair;

    private Stage stage;
    private Scene scene;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }

    @FXML
    public void btnPrincipalSair(){
        this.stage.close();
    }

    @FXML
    public void telaProdutos() throws IOException {
        URL url = new File("src/main/java/br/com/project/sistemagerenciamentoestoque/view/produtos.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setTitle("Produtos");
        newStage.setResizable(false);
        newStage.setScene(scene);

        produtosController controller = loader.getController();
        controller.setStage(newStage);

        newStage.show();
        stage.close();
    }

    @FXML
    public void telaEstoque() throws IOException {
        URL url = new File("src/main/java/br/com/project/sistemagerenciamentoestoque/view/estoque.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setTitle("Estoque");
        newStage.setResizable(false);
        newStage.setScene(scene);

        estoqueController controller = loader.getController();
        controller.setStage(newStage);

        newStage.show();
        stage.close();
    }

}
