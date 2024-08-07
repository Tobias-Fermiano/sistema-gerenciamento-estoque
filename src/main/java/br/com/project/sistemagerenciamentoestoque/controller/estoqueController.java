package br.com.project.sistemagerenciamentoestoque.controller;

import br.com.project.sistemagerenciamentoestoque.model.dao.EstoqueDAO;
import br.com.project.sistemagerenciamentoestoque.model.dao.ProdutosDAO;
import br.com.project.sistemagerenciamentoestoque.model.database.Database;
import br.com.project.sistemagerenciamentoestoque.model.database.DatabaseFactory;
import br.com.project.sistemagerenciamentoestoque.model.domain.Estoque;
import br.com.project.sistemagerenciamentoestoque.model.domain.Produtos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
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
    private TextField txtFeildQtdProduto;
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

    private List<Estoque> listEstoque;
    private ObservableList<Estoque> observableListEstoque;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final EstoqueDAO estoqueDAO = new EstoqueDAO();

    private Stage stage;
    private Produtos produto;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setProduto(Produtos produto){
        this.produto = produto;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estoqueDAO.setConnection(connection);
        setarMovimentosChoiceBox();
        carregaTableViewEstoque();
    }

    @FXML
    public void carregaTableViewEstoque(){
        colCodEstoque.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescricaoEstoque.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colQuantidadeEstoque.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colValorTotalEstoque.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        colValorUnitEstoque.setCellValueFactory(new PropertyValueFactory<>("valorUnit"));

        listEstoque = estoqueDAO.listEstoque();

        observableListEstoque = FXCollections.observableArrayList(listEstoque);
        tableViewEstoque.setItems(observableListEstoque);
    }

    @FXML
    public void setarMovimentosChoiceBox(){
        choiceBoxMovimento.setItems(FXCollections.observableArrayList(
                "Entrada", "Saída"
        ));
    }

    @FXML
    public void estoqueInserir(){
        String movimento = choiceBoxMovimento.getValue();
        if (movimento != null && txtFieldDescricao.getText() != "" && txtFeildQtdProduto.getText() != "") {
            if(movimento == "Entrada"){
                movimento = "E";
                estoqueDAO.inserir(this.produto, Integer.parseInt(txtFeildQtdProduto.getText()));
            } else{
                movimento = "S";
                estoqueDAO.inserir(this.produto, Integer.parseInt(txtFeildQtdProduto.getText()));
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, preencha todos os campos antes de inserir!");
            alert.show();
        }
    }

    @FXML
    public void escolherProduto() throws IOException {
        URL url = new File("src/main/java/br/com/project/sistemagerenciamentoestoque/view/estoqueProdutosDialog.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage newStage = new Stage();
        newStage.setTitle("Selecionar Produto");
        newStage.setResizable(false);
        newStage.setScene(scene);

        estoqueProdutosDialogController controller = loader.getController();
        controller.setStage(newStage);

        newStage.showAndWait();

        Produtos produto = controller.getProduto();
        if (produto != null){
            setProduto(produto);
            txtFieldCodigo.setText(Integer.toString(produto.getId()));
            txtFieldDescricao.setText(produto.getDescricao());
            txtFieldValorUnit.setText(Double.toString(produto.getValor()));
        }
    }

    @FXML
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

    @FXML
    public void limparTela(){
        txtFieldCodigo.setText("");
        txtFieldDescricao.setText("");
        txtFieldValorUnit.setText("");
        txtFeildQtdProduto.setText("");
        choiceBoxMovimento.setValue("");
    }

    public void btnRecarregarTableView(){
       //
    }
}
