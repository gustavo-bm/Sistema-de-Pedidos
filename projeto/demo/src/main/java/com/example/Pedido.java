package com.example;

public class Pedido {
    private Prato prato;
    private int creditosGanhados;
    
    public Pedido(Prato prato) {
        this.prato = prato;
        this.creditosGanhados = prato.getValorCreditos();
    }

    public int aplicarDesconto(int credito) {
        if (credito >= 400){
            System.out.println("Você tem direito a 40% de desconto!");
            return 400;
        } else if (credito >= 300){
            System.out.println("Você tem direito a 30% de desconto!");
            return 300;
        }else if(credito >= 200){
            System.out.println("Você tem direito a 20% de desconto!");
            return 200;
        }else if(credito >= 100){
            System.out.println("Você tem direito a 10% de desconto!");
            return 100;
        }        
        System.out.println("Crédito insuficiente para obter desconto!");
        return 0;
    }

    public int getCreditosGanhados() {
        return creditosGanhados;
    }
}


