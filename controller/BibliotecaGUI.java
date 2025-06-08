package controller;

import javax.swing.*;

public class BibliotecaGUI {
    public static void main(String[] args) {
        // Cria a janela principal
        JFrame frame = new JFrame("Sistema da Biblioteca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Painel principal com os componentes
        JPanel panel = new JPanel();
        frame.add(panel);

        // Mensagem de boas-vindas
        JLabel label = new JLabel("Bem-vindo à Biblioteca!");
        panel.add(label);

        // Botões de ação
        JButton listarButton = new JButton("Listar livros");
        panel.add(listarButton);

        JButton alugarButton = new JButton("Alugar livro");
        panel.add(alugarButton);

        JButton devolverButton = new JButton("Devolver livro");
        panel.add(devolverButton);

        // Exibe a janela
        frame.setVisible(true);
    }
}
