package br.com.project.sistemagerenciamentoestoque.model.domain;

public class Estoque {

    private int id;
    private String descricao;
    private int quantidade;
    private double valorUnit;
    private double valorTotal;

    public Estoque(int id, String descricao, int quantidade, double valorUnit, double valorTotal) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.valorTotal = valorTotal;
    }

    public Estoque(){

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

    public double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(double valorUnit) {
        this.valorUnit = valorUnit;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
