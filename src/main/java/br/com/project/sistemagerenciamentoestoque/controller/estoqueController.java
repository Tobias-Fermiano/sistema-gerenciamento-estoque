package br.com.project.sistemagerenciamentoestoque.controller;

import br.com.project.sistemagerenciamentoestoque.model.domain.Estoque;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class estoqueController implements Initializable {

    @FXML
    private TableView<Estoque> tableViewEstoque;
    @FXML
    private TableColumn<Estoque, Integer> colCodEstoque;
    @FXML
    private TableColumn<Estoque, String> colDescricaoEstoque;
    @FXML
    private TableColumn<Estoque, Integer> colQuantidadeEstoque;
    @FXML
    private TableColumn<Estoque, Double> colValorTotalEstoque;
    @FXML
    private TableColumn<Estoque, Double> colValorUnitEstoque;
    @FXML
    private TextField txtFieldDescricao;
    @FXML
    private TextField txtFieldCodigo;
    @FXML
    private TextField txtFieldValorUnit;
    @FXML
    private Button btnEscolherProduto;
    @FXML
    private ChoiceBox<String> choiceBoxMovimento;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnRecarregarTableView;


    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setarMovimentosChoiceBox();
    }

    public void setarMovimentosChoiceBox(){
        choiceBoxMovimento.setItems(FXCollections.observableArrayList(
                "Entrada", "Saída"
        ));
    }

    public void estoqueInserir(){
        String movimento = choiceBoxMovimento.getValue();
        System.out.println(movimento);
    }

    public void escolherProduto() throws IOException {
        URL url = new File("src/main/java/br/com/project/sistemagerenciamentoestoque/view/estoqueProdutosDialog.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage newStage = new Stage();
        newStage.setTitle("Selecionar Produto");
        newStage.setResizable(false);
        newStage.setScene(scene);

        newStage.show();
    }

    public void voltarTelaPrincipal() throws IOException {
        URL url = new File("src/main/java/br/com/project/sistemagerenciamentoestoque/view/telaPrincipal.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage newStage = new Stage();
        newStage.setTitle("Tela Principal");
        newStage.setResizable(false);
        newStage.setScene(scene);

        telaPrincipalController controller = loader.getController();
        controller.setStage(newStage);

        newStage.show();
        stage.close();
    }

}
