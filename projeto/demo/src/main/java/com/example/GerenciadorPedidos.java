package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GerenciadorPedidos {
    private Prato[] pratosDisponiveis;
    private Estoque estoque;

    public GerenciadorPedidos(Prato[] pratosDisponiveis, Estoque estoque) {
        this.pratosDisponiveis = pratosDisponiveis;
        this.estoque = estoque;
    }

    public void realizarPedido(Scanner scanner, Usuario usuarioAutenticado, int numPedido) {
        int tipoPedido = selecionarTipoPedido(scanner);
        LocalDateTime dataHoraAgendada = agendarPedido(scanner, tipoPedido);

        Prato pratoEscolhido = escolherOuMontarPrato(scanner);

        boolean usarCreditos = perguntarSeUsaCreditos(scanner, usuarioAutenticado);
        Pedido pedido = new Pedido(pratoEscolhido);

        if (usarCreditos) {
            double descontoAplicado = pedido.aplicarDesconto(usuarioAutenticado.getCreditos());
            usuarioAutenticado.reduzirCreditos(descontoAplicado);
        }

        finalizarPedido(usuarioAutenticado, numPedido, tipoPedido, dataHoraAgendada, pedido);
    }

    private int selecionarTipoPedido(Scanner scanner) {
        while (true) {
            System.out.println("\n==================================");
            System.out.println(" Como deseja fazer seu pedido? ");
            System.out.println("==================================");
            System.out.println("1. Pedido com Entrega Agendada");
            System.out.println("2. Pedido sem Fila");
            System.out.print("Escolha uma opção: ");

            int tipoPedido = lerOpcaoNumerica(scanner);
            if (tipoPedido == 1 || tipoPedido == 2) {
                return tipoPedido;
            }
            System.out.println("Opção inválida. Por favor, escolha 1 ou 2.");
        }
    }

    private LocalDateTime agendarPedido(Scanner scanner, int tipoPedido) {
        return (tipoPedido == 1) ? Agendador.agendar(scanner) : null;
    }

    private Prato escolherOuMontarPrato(Scanner scanner) {
        while (true) {
            System.out.println("\n==================================");
            System.out.println(" Qual opção melhor te atende?");
            System.out.println("==================================");
            System.out.println("1. Escolher Prato");
            System.out.println("--------------------------------------");
            exibirPratosDisponiveis(false);
            System.out.println("--------------------------------------");
            System.out.println("2. Montar seu próprio Prato");
            System.out.println("--------------------------------------");
            System.out.print("Escolha uma opção: ");

            int escolhaPrato = lerOpcaoNumerica(scanner);
            if (escolhaPrato == 1) {
                return escolherPrato(scanner);
            } else if (escolhaPrato == 2) {
                return montarPrato(scanner);
            }
            System.out.println("Opção inválida. Por favor, escolha 1 ou 2.");
        }
    }

    private Prato escolherPrato(Scanner scanner) {
        exibirPratosDisponiveis(true);
        int escolha = lerOpcaoNumerica(scanner);
        if (escolha >= 1 && escolha <= pratosDisponiveis.length) {
            return pratosDisponiveis[escolha - 1];
        }
        System.out.println("Escolha inválida. Selecione um prato válido.");
        return escolherPrato(scanner);
    }

    private Prato montarPrato(Scanner scanner) {
        Prato prato = new Prato("Prato Personalizado", 80);
        boolean adicionando = true;

        while (adicionando) {
            exibirItensEstoque();
            System.out.print("Escolha um item para adicionar (ou 0 para finalizar): ");
            int escolha = lerOpcaoNumerica(scanner);
            if (escolha == 0) {
                adicionando = false;
            } else {
                String item = estoque.getItem(escolha - 1);
                prato.adicionarItem(item);
                estoque.reduzirEstoque(item);
            }
        }
        return prato;
    }

    private boolean perguntarSeUsaCreditos(Scanner scanner, Usuario usuario) {
        System.out.print("Deseja usar créditos? Você tem " + usuario.getCreditos() + " créditos (S/N): ");
        String resposta = scanner.nextLine();
        return resposta.equalsIgnoreCase("S");
    }

    private void finalizarPedido(Usuario usuario, int numPedido, int tipoPedido, LocalDateTime dataHoraAgendada,
            Pedido pedido) {
        if (tipoPedido == 1 && dataHoraAgendada != null) {
            System.out.println("Pedido agendado com sucesso para "
                    + dataHoraAgendada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "!" 
                    + " Obrigado pela compra, " + usuario.getNome() + "! Atente-se à data e horário de entrega.");
        } else {
            System.out.println("Pedido realizado com sucesso para " + usuario.getNome() + "! Número do pedido: " + numPedido);
        }

        System.out.println("Você ganhou " + pedido.getCreditosGanhados() + " créditos.");
        usuario.adicionarCreditos(pedido.getCreditosGanhados());
    }

    private void exibirPratosDisponiveis(boolean comIndices) {
        if (comIndices) {
            System.out.println("\nPratos disponíveis:");
            for (int i = 0; i < pratosDisponiveis.length; i++) {
                System.out.println((i + 1) + ". " + pratosDisponiveis[i].getNome());
            }
        } else {
            System.out.println("\nPratos disponíveis:");
            for (int i = 0; i < pratosDisponiveis.length; i++) {
                System.out.println("    " + pratosDisponiveis[i].getNome());
            }
        }
    }

    private void exibirItensEstoque() {
        System.out.println("\nItens disponíveis:");
        estoque.mostrarItensDisponiveis();
    }

    private int lerOpcaoNumerica(Scanner scanner) {
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
            scanner.nextLine();
            return -1;
        }
    }
}
