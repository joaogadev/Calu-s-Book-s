package view;

import controller.Main;
import java.awt.*;
import javax.swing.*;
import models.Usuario;

public class BibliotecaFrontEnd extends JFrame {
    private final Main sistema;              // Controlador principal (lógica da aplicação)
    private Usuario usuarioAtual;            // Usuário atualmente logado

    public BibliotecaFrontEnd(Main sistema) {
        this.sistema = sistema;

        // Configuração básica da janela
        setTitle("Sistema da Biblioteca");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);        // Centraliza a janela na tela

        // Painel principal com layout em grade
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Criação dos botões
        JButton btnLogin = new JButton("Login");
        JButton btnListar = new JButton("Listar Livros");
        JButton btnAlugar = new JButton("Alugar Livro");
        JButton btnDevolver = new JButton("Devolver Livro");

        // Ação do botão de login
        btnLogin.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Digite seu ID de usuário:");
            try {
                int id = Integer.parseInt(idStr);
                usuarioAtual = sistema.recuperarUsuario(id);  // Recupera o usuário
                Main.salvarLogin(id);                         // Salva login no sistema
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        });

        // Ação para listar os livros disponíveis
        btnListar.addActionListener(e -> sistema.listarLivros());

        // Ação para alugar um livro (requer login)
        btnAlugar.addActionListener(e -> {
            if (usuarioAtual == null) {
                JOptionPane.showMessageDialog(this, "Faça login primeiro.");
                return;
            }
            String titulo = JOptionPane.showInputDialog(this, "Digite o título para alugar:");
            if (sistema.alugar(titulo, usuarioAtual) != null) {
                JOptionPane.showMessageDialog(this, "Livro alugado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(this, "Livro não disponível.");
            }
        });

        // Ação para devolver um livro (requer login)
        btnDevolver.addActionListener(e -> {
            if (usuarioAtual == null) {
                JOptionPane.showMessageDialog(this, "Faça login primeiro.");
                return;
            }
            String titulo = JOptionPane.showInputDialog(this, "Digite o título para devolver:");
            if (sistema.devolver(titulo, usuarioAtual)) {
                JOptionPane.showMessageDialog(this, "Livro devolvido com sucesso.");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao devolver o livro.");
            }
        });

        // Adiciona botões ao painel e exibe a janela
        panel.add(btnLogin);
        panel.add(btnListar);
        panel.add(btnAlugar);
        panel.add(btnDevolver);
        add(panel);
        setVisible(true);
    }

    // Ponto de entrada da aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BibliotecaFrontEnd(new Main()));
    }
}
