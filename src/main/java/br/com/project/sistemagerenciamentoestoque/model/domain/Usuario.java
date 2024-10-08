package br.com.project.sistemagerenciamentoestoque.model.domain;

public class Usuario {

    private String nome;
    private String senha;
    private Boolean permissao;

    public Usuario(String nome, String senha, Boolean permissao){
        this.nome = nome;
        this.senha = senha;
        this.permissao = permissao;
    }

    public Usuario(){}

    public Boolean getPermissao() {
        return permissao;
    }

    public void setPermissao(Boolean permissao) {
        this.permissao = permissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
