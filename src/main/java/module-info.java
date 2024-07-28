module br.com.project.sistemagerenciamentoinventario {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens br.com.project.sistemagerenciamentoestoque to javafx.fxml;
    exports br.com.project.sistemagerenciamentoestoque;
    exports br.com.project.sistemagerenciamentoestoque.controller;
    opens br.com.project.sistemagerenciamentoestoque.controller to javafx.fxml;
}