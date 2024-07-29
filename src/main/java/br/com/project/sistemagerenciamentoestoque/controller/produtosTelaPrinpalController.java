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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class produtosTelaPrinpalController implements Initializable {

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
        try{
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
        Produtos produto = new Produtos();
        produto.setDescricao(txtFieldDescricao.getText());
        produto.setValor(Double.parseDouble(txtFieldValor.getText()));
        produtosDAO.inserir(produto);

        Produtos produtos = produtosDAO.findProduto();

        observableListProdutos.add(produtos);
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

    public void removerProduto() throws SQLException {
        Produtos produtos = tblViewProdutos.getSelectionModel().getSelectedItem();
        if (produtos != null) {
            produtosDAO.removerProduto(produtos);
            carregarTableViewProdutos();
        } else {
            System.out.println("Ta errado amigão");
        }
    }

    public void editarProduto() throws SQLException {
        Produtos produtos = tblViewProdutos.getSelectionModel().getSelectedItem();
        if (produtos != null) {
            produtos.setDescricao(txtFieldDescricao.getText());
            produtos.setValor(Double.parseDouble(txtFieldValor.getText()));
        }
    }
}
