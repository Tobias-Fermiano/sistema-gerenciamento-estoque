package br.com.project.sistemagerenciamentoestoque.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class estoqueProdutosDialogController implements Initializable {

    @FXML
    private TextField txtFieldPesquisarProduto;
    @FXML
    private Button btnPesquisarProduto;
    @FXML
    private Label lblCodigo;
    @FXML
    private Label lblDescricao;
    @FXML
    private Label lblValor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }


}
