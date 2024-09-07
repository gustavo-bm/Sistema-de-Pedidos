package com.example;

import java.util.ArrayList;

public class Prato {
    private String nome;
    private int valorCreditos;
    private ArrayList<String> itens = new ArrayList<>();

    public Prato(String nome, int valorCreditos) {
        this.nome = nome;
        this.valorCreditos = valorCreditos;
    }

    public String getNome() {
        return nome;
    }

    public int getValorCreditos() {
        return valorCreditos;
    }

    public void adicionarItem(String item) {
        itens.add(item);
    }

    public void removerItem(String item) {
        itens.remove(item);
    }

    public ArrayList<String> getItens() {
        return itens;
    }

}
