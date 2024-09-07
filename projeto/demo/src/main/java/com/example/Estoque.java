package com.example;

import java.util.HashMap;

public class Estoque {
    private HashMap<String, Integer> itens = new HashMap<>();

    public Estoque() {
        itens.put("Arroz", 10);
        itens.put("Feijão", 10);
        itens.put("Macarrão", 20);
        itens.put("Salada", 10);
        itens.put("Filé de Frango", 15);
        itens.put("Batata Frita", 10);
    }

    public void mostrarItensDisponiveis() {
        int index = 1;
        for (String item : itens.keySet()) {
            System.out.println(index + ". " + item);
            index++;
        }
    }

    public String getItem(int index) {
        return (String) itens.keySet().toArray()[index];
    }

    public void reduzirEstoque(String item) {
        itens.put(item, itens.get(item) - 1);
    }
}

