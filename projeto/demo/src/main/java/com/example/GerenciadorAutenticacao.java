package com.example;

import java.util.Scanner;

public class GerenciadorAutenticacao {
    private CadastroUsuario cadastroUsuario;
    private Usuario usuarioAutenticado;

    public GerenciadorAutenticacao(CadastroUsuario cadastroUsuario) {
        this.cadastroUsuario = cadastroUsuario;
    }

    public void criarConta(Scanner scanner) {
        long cpf = Validador.validarCpf(scanner);
        scanner.nextLine(); 

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        String email = Validador.validarEmail(scanner);
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        cadastroUsuario.cadastrar(cpf, nome, email, senha);
        System.out.println("\n\nConta criada com sucesso!");
    }
    

    public boolean fazerLogin(Scanner scanner) {
        System.out.println("\n\n======================================");
        System.out.println(" Informe seus dados para fazer login.");
        System.out.println("======================================\n");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        boolean loginSucesso = cadastroUsuario.login(email, senha);
        if (loginSucesso) {
            usuarioAutenticado = cadastroUsuario.getUsuario(email);
        } else {
            System.out.println("Falha no login. Verifique suas credenciais.");
        }
        return loginSucesso;
    }

    public void recuperarSenha(Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CPF: ");
        long cpf = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Nova Senha: ");
        String novaSenha = scanner.nextLine();
        cadastroUsuario.recuperarSenha(email, cpf, novaSenha);
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
}
