package com.example;

import java.util.HashMap;

public class CadastroUsuario {
    private HashMap<String, Usuario> usuarios = new HashMap<>();

    public void cadastrar(long cpf, String nome, String email, String senha) {
        Usuario usuario = new Usuario(cpf, nome, email, senha);
        usuarios.put(email, usuario);
    }

    public boolean login(String email, String senha) {
        Usuario usuario = usuarios.get(email);
        if (usuario != null && usuario.verificarSenha(senha)) {
            System.out.println("\n\nLogin bem-sucedido!");
            return true;
        }
        System.out.println("Email ou senha inválidos.");
        return false;
    }

    public Usuario getUsuario(String email) {
        return usuarios.get(email);
    }

    public boolean recuperarSenha(String email, long cpf, String novaSenha) {
        Usuario usuario = usuarios.get(email);
        if (usuario != null && usuario.getCpf() == cpf) {
            usuario.setSenha(novaSenha);
            System.out.println("Senha alterada com sucesso!");
            return true;
        }
        System.out.println("Dados incorretos. Não foi possível alterar a senha.");
        return false;
    }

    public boolean temUsuariosCadastrados() {
        return !usuarios.isEmpty();
    }
}

