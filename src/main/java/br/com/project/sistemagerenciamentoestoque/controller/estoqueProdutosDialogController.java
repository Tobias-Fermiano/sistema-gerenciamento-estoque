package br.com.project.sistemagerenciamentoestoque.controller;

import br.com.project.sistemagerenciamentoestoque.model.dao.ProdutosDAO;
import br.com.project.sistemagerenciamentoestoque.model.database.Database;
import br.com.project.sistemagerenciamentoestoque.model.database.DatabaseFactory;
import br.com.project.sistemagerenciamentoestoque.model.domain.Produtos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class estoqueProdutosDialogController implements Initializable {

    @FXML
    private TableView<Produtos> tblViewProdutos;
    @FXML
    private TableColumn<Produtos, Integer> tblColumnCodigo;
    @FXML
    private TableColumn<Produtos, String> tblColumnDescricao;
    @FXML
    private TableColumn<Produtos, Boolean> tblColumnValor;
    @FXML
    private TextField txtFieldPesquisarProduto;
    @FXML
    private Button btnPesquisarProduto;
    @FXML
    private Button btnEscolherProduto;
    @FXML
    private Label lblCodigo;
    @FXML
    private Label lblDescricao;
    @FXML
    private Label lblValor;

    private List<Produtos> listProdutos;
    private ObservableList<Produtos> observableListProdutos;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ProdutosDAO produtosDAO = new ProdutosDAO();

    private Stage stage;
    private Produtos produto;

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        produtosDAO.setConnection(connection);

        carregarTableViewProdutos();

        tblViewProdutos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewProdutos(newValue)
        );
    }

    public void selecionarItemTableViewProdutos(Produtos produtos){
        if(produtos != null){
            lblCodigo.setText(Integer.toString(produtos.getId()));
            lblDescricao.setText(produtos.getDescricao());
            lblValor.setText(Double.toString(produtos.getValor()));
        }
    }
    @FXML
    public void carregarTableViewProdutos() {

        tblColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tblColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        String fieldPesquisa = txtFieldPesquisarProduto.getText();
        if (fieldPesquisa != null){
            listProdutos = produtosDAO.likeProduto(fieldPesquisa);
        } else {
            listProdutos = produtosDAO.listar();
        }

        observableListProdutos = FXCollections.observableArrayList(listProdutos);
        tblViewProdutos.setItems(observableListProdutos);
    }

    @FXML
    public void pesquisarProduto(){
        System.out.println(txtFieldPesquisarProduto.getText());
        carregarTableViewProdutos();
    }

    public void escolherProdutoDialog(){
        Produtos produtos = new Produtos();
        produtos.setId(Integer.parseInt(lblCodigo.getText()));
        produtos.setDescricao(lblDescricao.getText());
        produtos.setValor(Double.parseDouble(lblValor.getText()));
        setProduto(produtos);

        this.stage.close();
    }

}
