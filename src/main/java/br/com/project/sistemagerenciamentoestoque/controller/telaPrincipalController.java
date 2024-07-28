package br.com.project.sistemagerenciamentoestoque.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }
}
