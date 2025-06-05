package controller;

import models.Livro;
import models.Usuario;
import models.Biblioteca;
import models.Bibliotecario;
import java.util.Scanner;
import java.util.Random;

public class SistemaDoBibliotecario {

    private Biblioteca biblio;

    public SistemaDoBibliotecario() {
        this.biblio = new Biblioteca();
    }

    /** Cria um novo usuário */
    public Usuario cadastrarUsuario(String nome, String email, String telefone, int id) {
        return new Usuario(nome, email, id, telefone);
    }

    /** Retorna um usuário já existente (por ID) */
    public Usuario recuperarUsuario(int id) {
        return new Usuario(id);
    }

    /** Tenta alugar um livro; retorna true se OK */
    public Livro alugar(String titulo, Usuario usuario) {
        return biblio.alugarLivro(titulo, usuario);
    }

    /** Tenta devolver um livro; retorna true se OK */
    public boolean devolver(String titulo, Usuario usuario) {
        return biblio.devolverLivro(titulo, usuario);
    }

    /** Consulta quantos exemplares restam de um título */
    public int getExemplares(String titulo) {
        return biblio.getLivrosDisponiveis().stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .map(l -> l.getExemplares())
                .orElse(0);
    }

    /** Método principal: interface de console */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaDoBibliotecario sys = new SistemaDoBibliotecario();
        Usuario user;

        System.out.print("O cliente já é cadastrado na Biblioteca? [sim/não] ");
        String resposta = sc.nextLine().trim().toLowerCase();

        if (resposta.equals("sim")) {
            System.out.print("Digite o seu id: ");
            int id = Integer.parseInt(sc.nextLine());
            user = sys.recuperarUsuario(id);
            System.out.println("Livros Alugados: " + user.getLivrosAlugados());
        } else {
            Bibliotecario bibliotecario = new Bibliotecario("João", 1, "senha123");
            System.out.print("Qual o nome do Cliente: ");
            String nome = sc.nextLine();
            System.out.print("Qual o email do Cliente: ");
            String email = sc.nextLine();
            System.out.print("Qual o telefone do Cliente: ");
            String telefone = sc.nextLine();

            user = bibliotecario.cadastrarCliente(nome, email, telefone);
            System.out.println("ID gerado: " + user.getId());
            System.out.println("Livros Alugados: " + user.getLivrosAlugados());
        }

        System.out.println("\n--- Menu ---");
        System.out.println("1. Listar livros disponíveis");
        System.out.println("2. Alugar livro");
        System.out.println("3. Devolver livro");
        System.out.println("0. Sair");

        int opcao;
        do {
            System.out.print("\nEscolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    sys.biblio.listarLivros();
                    break;
                case 2:
                    System.out.print("Digite o título do livro para alugar: ");
                    String tituloAluga = sc.nextLine();
                    sys.alugar(tituloAluga, user);
                    System.out.println("Exemplares restantes: " + sys.getExemplares(tituloAluga));
                    break;
                case 3:
                    System.out.print("Digite o título do livro para devolver: ");
                    String tituloDev = sc.nextLine();
                    sys.devolver(tituloDev, user);
                    System.out.println("Exemplares agora: " + sys.getExemplares(tituloDev));
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        sc.close();
    }
}
