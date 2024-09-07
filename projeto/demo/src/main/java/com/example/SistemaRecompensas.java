package com.example;

public class SistemaRecompensas {
    
    private Usuario usuario;

    public SistemaRecompensas(Usuario usuario) {
        this.usuario = usuario;
    }

    public void adicionarCreditos(int valor) {
        usuario.adicionarCreditos(valor);
    }

    public void utilizarCreditos(int valor) {
        usuario.reduzirCreditos(valor);
    }

    public int verificarCreditos() {
        return usuario.getCreditos();
    }
}

