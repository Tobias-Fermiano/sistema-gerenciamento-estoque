package br.com.project.sistemagerenciamentoestoque;

import br.com.project.sistemagerenciamentoestoque.controller.telaDeEntradaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        URL url = new File("src/main/java/br/com/project/sistemagerenciamentoestoque/view/telaDeEntrada.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);

        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Gerenciamento de Estoque");
        stage.setScene(scene);

        telaDeEntradaController controller = loader.getController();
        controller.setStage(stage);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}