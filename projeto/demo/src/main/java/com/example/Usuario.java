package com.example;

public class Usuario {
    private long cpf;
    private String nome;
    private String email;
    private String senha;
    private int creditos;

    public Usuario(long cpf, String nome, String email, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.creditos = 0;
    }

    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }

    public int getCreditos() {
        return creditos;
    }

    public void adicionarCreditos(int creditos) {
        this.creditos += creditos;
    }

    public void reduzirCreditos(double creditos) {
        this.creditos -= creditos;
    }

    public long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String novaSenha){
        this.senha = novaSenha;
    }
}
