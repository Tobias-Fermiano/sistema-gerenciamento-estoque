package br.com.project.sistemagerenciamentoestoque.controller;

import br.com.project.sistemagerenciamentoestoque.model.dao.ProdutosDAO;
import br.com.project.sistemagerenciamentoestoque.model.database.Database;
import br.com.project.sistemagerenciamentoestoque.model.database.DatabaseFactory;
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
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class produtosController implements Initializable {

    @FXML
    private TableView<Produtos> tblViewProdutos;
    @FXML
    private TableColumn<Produtos, Integer> tblColumnCodigo;
    @FXML
    private TableColumn<Produtos, String> tblColumnDescricao;
    @FXML
    private TableColumn<Produtos, Double> tblColumnValor;
    @FXML
    private Button btnVoltarTelaPrincipal;
    @FXML
    private Button btnSalvarProdutos;
    @FXML
    private TextField txtFieldDescricao;
    @FXML
    private TextField txtFieldValor;
    @FXML
    private Button btnRemoverProduto;
    @FXML
    private Button btnEditarProduto;


    private List<Produtos> listProdutos;
    private ObservableList<Produtos> observableListProdutos;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ProdutosDAO produtosDAO = new ProdutosDAO();

    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtosDAO.setConnection(connection);
        try {
            carregarTableViewProdutos();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void carregarTableViewProdutos() throws SQLException {
        tblColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tblColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        listProdutos = produtosDAO.listar();

        observableListProdutos = FXCollections.observableArrayList(listProdutos);
        tblViewProdutos.setItems(observableListProdutos);
    }

    @FXML
    public void inserirProduto() throws SQLException {
        String descricao = txtFieldDescricao.getText();
        String valor = txtFieldValor.getText();
        if (descricao.isEmpty() && valor.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, preencha todos os campos antes de inserir!");
            alert.show();
        } else {
            Produtos produto = new Produtos();
            produto.setDescricao(descricao);
            produto.setValor(Double.parseDouble(valor));
            produtosDAO.inserir(produto);
        }
        carregarTableViewProdutos();
    }

    public void removerProduto() throws SQLException {
        Produtos produtos = tblViewProdutos.getSelectionModel().getSelectedItem();
        if (produtos != null) {
            produtosDAO.removerProduto(produtos);
            carregarTableViewProdutos();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um prduto na Tabela para conseguir remover!");
            alert.show();
        }
    }

    public void editarProdutoDialog() throws SQLException, IOException {
        Produtos produto = tblViewProdutos.getSelectionModel().getSelectedItem();

        if (produto != null) {
            if(showCadastrosClientesDialog(produto)){
                produtosDAO.editarProduto(produto);
                carregarTableViewProdutos();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione um produto para alterar!");
            alert.show();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void voltarTelaPrincipal() throws IOException {
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
    public boolean showCadastrosClientesDialog(Produtos produto) throws IOException {
        Stage newStage = new Stage();
        URL url = new File("src/main/java/br/com/project/sistemagerenciamentoestoque/view/produtosDialog.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Scene scene = new Scene(root);

        newStage.setScene(scene);
        newStage.setTitle("Editar produto");
        newStage.setScene(scene);

        produtosDialogController controller = loader.getController();
        controller.setStage(newStage);
        controller.setProduto(produto);

        newStage.showAndWait();

        return true;
    }

}
