package br.com.project.sistemagerenciamentoestoque.model.domain;

public class Produtos {

    private int id;
    private String descricao;
    private double valor;
    private String movimento;

    public Produtos(int id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Produtos() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    private void setMovimento(){
        this.movimento = movimento;
    }

    public String getMovimento() {
        return movimento;
    }

}
