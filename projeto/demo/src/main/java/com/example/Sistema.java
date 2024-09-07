package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Sistema {
    private int numPedido = 0;
    private CadastroUsuario cadastroUsuario = new CadastroUsuario();
    private Usuario usuarioAutenticado;
    private Prato[] pratosDisponiveis = {
        new Prato("Parmegiana", 100),
        new Prato("Lasanha", 100),
        new Prato("Arroz de forno", 100),
        new Prato("Strogonoff de Frango", 100),
        new Prato("Bife à cavalo", 100),
        new Prato("Macarrão à bolonhesa", 100),
        new Prato("Feijoada", 100)
    };
    private Estoque estoque = new Estoque();
    private GerenciadorAutenticacao gerenciadorAutenticacao = new GerenciadorAutenticacao(cadastroUsuario);
    private GerenciadorPedidos gerenciadorPedidos = new GerenciadorPedidos(pratosDisponiveis, estoque);
    private boolean contaCriada = false;

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            mostrarMenuInicial();
            try {
                int escolha = scanner.nextInt();
                scanner.nextLine();

                if (!contaCriada) {
                    if (escolha == 1) {
                        gerenciadorAutenticacao.criarConta(scanner);
                        contaCriada = true;
                    } else {
                        exibirErro("Opção inválida.");
                    }
                } else {
                    if (escolha == 1) {
                        if (gerenciadorAutenticacao.fazerLogin(scanner)) {
                            usuarioAutenticado = gerenciadorAutenticacao.getUsuarioAutenticado();
                            while (true) {
                                gerenciadorPedidos.realizarPedido(scanner, usuarioAutenticado, numPedido++);
                            }
                        }
                    } else if (escolha == 2) {
                        gerenciadorAutenticacao.recuperarSenha(scanner);
                    } else {
                        exibirErro("Opção inválida.");
                    }
                }
            } catch (InputMismatchException e) {
                exibirErro("Entrada inválida. Por favor, tente novamente.");
                scanner.nextLine();
            }
        }
    }

    private void mostrarMenuInicial() {
        System.out.println("\n======================================");

        if (!contaCriada) { 
            System.out.println(" Bem-vindo! Por favor, crie sua conta");
            System.out.println("======================================");
            System.out.println("1. Criar Conta");
        } else {
            System.out.println("Escolha uma opção:");
            System.out.println("==================================");
            System.out.println("1. Fazer Login");
            System.out.println("2. Recuperar senha");
        }

        System.out.println("--------------------------------------");
        System.out.print("Escolha uma opção: ");
    }

    private void exibirErro(String mensagem) {
        System.out.println("\n  ERRO: " + mensagem + "\n");
    }
}
