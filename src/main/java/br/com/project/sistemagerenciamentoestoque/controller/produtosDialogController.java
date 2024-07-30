package br.com.project.sistemagerenciamentoestoque.controller;

import br.com.project.sistemagerenciamentoestoque.model.dao.ProdutosDAO;
import br.com.project.sistemagerenciamentoestoque.model.domain.Produtos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class produtosDialogController implements Initializable {

    @FXML
    private TextField txtFieldProdutoDialogDescricao;
    @FXML
    private TextField txtFieldProdutoDialogValor;
    @FXML
    private Button btnProdutosDialogCancelar;
    @FXML
    private Button btnProdutosDialogEnviar;

    private Stage stage;
    private Produtos produto;

    public void initialize(URL location, ResourceBundle resources) {
        //
    }


    public void produtosDialogCancelar() {
        this.stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void produtosDialogEnviar(){
        produto.setValor(Double.parseDouble(txtFieldProdutoDialogValor.getText()));
        produto.setDescricao(txtFieldProdutoDialogDescricao.getText());
        this.stage.close();
    }
    public void setProduto(Produtos produtos){
        this.produto = produtos;
        this.txtFieldProdutoDialogDescricao.setText(produtos.getDescricao());
        this.txtFieldProdutoDialogValor.setText(String.valueOf(produtos.getValor()));
    }

}
