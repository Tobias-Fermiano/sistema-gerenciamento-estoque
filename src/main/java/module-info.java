module br.com.project.sistemagerenciamentoinventario {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.project.sistemagerenciamentoinventario to javafx.fxml;
    exports br.com.project.sistemagerenciamentoinventario;
}