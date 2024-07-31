package br.com.project.sistemagerenciamentoestoque.controller;

import br.com.project.sistemagerenciamentoestoque.model.domain.Estoque;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }
}
