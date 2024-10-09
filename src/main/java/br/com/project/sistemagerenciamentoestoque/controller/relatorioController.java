package br.com.project.sistemagerenciamentoestoque.controller;

import br.com.project.sistemagerenciamentoestoque.model.dao.RelatorioDAO;
import br.com.project.sistemagerenciamentoestoque.model.database.Database;
import br.com.project.sistemagerenciamentoestoque.model.database.DatabaseFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.text.LabelView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class relatorioController implements Initializable {

    @FXML
    private Label ValorTotalProdutos;
    @FXML
    private Label ProdutosInseridos;
    @FXML
    private Label ProdutosCadastrados;
    @FXML
    private Label MovimentacaoEntrada;
    @FXML
    private Label MovimentacaoTotal;
    @FXML
    private Label MovimentacaoSaida;
    @FXML
    private Label ProdutosEstoque;
    @FXML
    private Button btnGerar;
    @FXML
    private Label UsuariosCadastrados;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final RelatorioDAO relatorioDao = new RelatorioDAO();

    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        relatorioDao.setConnection(connection);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void GerarRelatorio() {
        var quantidadeProdutos = relatorioDao.contarProdutos();
        var quantidadeUsuarios = relatorioDao.contarUsuarios();
        var valorTotalProdutos = relatorioDao.somarValores();
        var produtosNosUltimos30Dias = relatorioDao.contarEstoqueUltimos30Dias();
        var movimentacaoEntrada = relatorioDao.contarMovimentoEntrada();
        var movimentacaoSaida = relatorioDao.contarMovimentoSaida();
        var movimentacaoTotal = movimentacaoEntrada + movimentacaoSaida;
        var produtosEmEstoque = relatorioDao.somarQuantidadeEstoque();

        ProdutosEstoque.setText(String.valueOf(produtosEmEstoque));
        MovimentacaoTotal.setText(String.valueOf(movimentacaoTotal));
        MovimentacaoEntrada.setText(String.valueOf(movimentacaoEntrada));
        MovimentacaoSaida.setText(String.valueOf(movimentacaoSaida));
        ValorTotalProdutos.setText(String.valueOf(valorTotalProdutos));
        ProdutosInseridos.setText(String.valueOf(produtosNosUltimos30Dias));
        UsuariosCadastrados.setText(String.valueOf(quantidadeUsuarios));
        ProdutosCadastrados.setText(String.valueOf(quantidadeProdutos));
    }

    @FXML
    public void voltarTelaPrincipal() throws IOException {
        URL url = new File("src/main/java/br/com/project/sistemagerenciamentoestoque/view/telaPrincipal.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gerenciamento de Estoque");

        telaPrincipalController controller = loader.getController();
        controller.setStage(this.stage);

        stage.show();
    }
}