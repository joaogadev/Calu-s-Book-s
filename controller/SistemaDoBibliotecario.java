package controller;

import java.util.Random;
import java.util.Scanner;
import models.Biblioteca;
import models.Usuario;

public class SistemaDoBibliotecario {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        Biblioteca biblio = new Biblioteca();
        Usuario user = null;

        System.out.println("=====================================");
        System.out.println("        BEM-VINDO À BIBLIOTECA        ");
        System.out.println("=====================================\n");

        System.out.println("Selecione uma opção abaixo:");
        System.out.println("1 - Já sou cadastrado");
        System.out.println("2 - Quero me cadastrar");
        System.out.println("0 - Sair");

        System.out.print("\nDigite sua escolha: ");
        int escolha = sc.nextInt();
        sc.nextLine();

        switch (escolha) {
            case 1:
                System.out.print("Digite o seu ID de cliente: ");
                int id = sc.nextInt();
                sc.nextLine();
                user = new Usuario(id);
                System.out.println("\n Acesso autorizado!");
                System.out.println("📘 Livros alugados por você: " + user.getLivrosAlugados());
                break;

            case 2:
                System.out.print("Digite seu nome: ");
                String nome = sc.nextLine();

                System.out.print("Digite seu e-mail: ");
                String email = sc.nextLine();

                System.out.print("Digite seu telefone: ");
                String telefone = sc.nextLine();

                int novoId = 1000000 + rd.nextInt(9000000);
                user = new Usuario(nome, email, novoId, telefone);

                System.out.println("\n Cadastro realizado com sucesso!");
                System.out.println("Seu ID de cliente é: " + novoId);
                System.out.println("📘 Livros alugados: " + user.getLivrosAlugados());
                break;

            case 0:
                System.out.println("\n Obrigado por visitar a Biblioteca. Até logo!");
                sc.close();
                return;

            default:
                System.out.println("\n Opção inválida. Encerrando o programa.");
                sc.close();
                return;
        }

        int opcao;
        do {
            System.out.println("\n=========== MENU PRINCIPAL ===========");
            System.out.println("Usuário: " + user.getNome() + " | ID: " + user.getId());
            System.out.println("--------------------------------------");
            System.out.println("1. Listar livros disponíveis");
            System.out.println("2. Alugar um livro");
            System.out.println("3. Devolver um livro");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    biblio.listarLivros();
                    break;
                case 2:
                    System.out.print("Digite o título do livro para alugar: ");
                    String tituloAluga = sc.nextLine();
                    boolean alugado = biblio.alugarLivro(tituloAluga, user);
                    if (alugado) {
                        System.out.println(" Livro alugado com sucesso!");
                    } else {
                        System.out.println(" Livro indisponível ou título inválido.");
                    }
                    break;
                case 3:
                    System.out.print("Digite o título do livro para devolver: ");
                    String tituloDev = sc.nextLine();
                    boolean devolvido = biblio.devolverLivro(tituloDev, user);
                    if (devolvido) {
                        System.out.println(" Livro devolvido com sucesso!");
                    } else {
                        System.out.println("  O livro não estava alugado por você.");
                    }
                    break;
                case 0:
                    System.out.println("\n Até logo, obrigado por utilizar nossa biblioteca!");
                    break;
                default:
                    System.out.println(" Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        sc.close();
    }
}