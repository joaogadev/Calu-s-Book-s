package controller;

import models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Main {

    private Biblioteca biblio;

    public Main() {
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
    /** Método para gerar relatório em txt */
    public static void gerarRelatorio(List<Aluguel> alugueis) /*Recebe uma Lista de Alugueis como Parámetro*/{
        try (FileWriter fw = new FileWriter("relatorio.txt", true)) {
            for (Aluguel aluguel : alugueis) {
                boolean foiDevolvido = aluguel.getDevolucaoAluguel() == null;
                String status = foiDevolvido ? "Aluguel" : "Devolução";//  Operdor ternário para identificar o status
                fw.write("\n\nRelatório \n" +
                        "---------------------------------------------------------------- \n" +
                        "Tipo: " + status + "\n" +
                        "ID do Usuário: " + aluguel.getUsuario().getId() + "\n" +
                        "Livro: " + aluguel.getLivro().getTitulo() + "\n" +
                        "Data de Aluguel: " + aluguel.getDataAluguel() + "\n");
                if (!foiDevolvido) {
                    fw.write("Data de Devolução: " + aluguel.getDevolucaoAluguel() + "\n");
                } else {
                    fw.write("Data de Devolução: " + aluguel.getDataAluguel().plusDays(7) + "\n");
                }
                fw.write("-------------------------------------------------------------");
            }
            System.out.println("Relatório Gerada com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao gerar relatorio!" + e.getMessage());
        }

    }

    public static void salvarLogin(int id) {
        try (FileWriter fw = new FileWriter("usuarioSalvos.txt", true)) {
            fw.write("\n\nId do usuário: " + id + "\n" +
                    "------------------------------------- \n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar login!" + e.getMessage());
        }
    }

    /** Método principal: interface de console */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Aluguel> alugueis = new ArrayList<>();
        Main sys = new Main();
        Usuario user;
        Livro book = null;

        System.out.print("O cliente já é cadastrado na Biblioteca? [sim/não] ");
        String resposta = sc.nextLine().trim().toLowerCase();

        if (resposta.equals("sim")) {
            System.out.print("Digite o seu id: ");
            int id = Integer.parseInt(sc.nextLine());
            user = sys.recuperarUsuario(id);
            salvarLogin(id);
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
            salvarLogin(user.getId());
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
                    Livro livroAlugado = sys.alugar(tituloAluga, user);
                    if (livroAlugado != null) {
                        Aluguel novoAluguel = new Aluguel(user, livroAlugado);
                        alugueis.add(novoAluguel);
                        gerarRelatorio(alugueis);
                        System.out.println("Aluguel Realizado com sucesso!");
                        System.out.println("Exemplares restantes: " + sys.getExemplares(tituloAluga));
                    } else {
                        System.out.println("Não foi possível alugar o livro. Verifique a disponibilidade.");
                    }
                    break;
                case 3:
                    System.out.print("Digite o título do livro para devolver: ");
                    String tituloDev = sc.nextLine();
                    boolean encontrarAluguel = false;
                    for (Aluguel aluguel : alugueis) {
                        if (aluguel.getUsuario().equals(user) &&
                                aluguel.getLivro().getTitulo().equalsIgnoreCase(tituloDev) &&
                                aluguel.getDevolucaoAluguel() == null) {

                            aluguel.setDevolucaoAluguel(LocalDate.now()); // agora funciona certinho
                            sys.devolver(tituloDev, user);
                            gerarRelatorio(alugueis);

                            System.out.println("✅ Devolução registrada com sucesso!");
                            System.out.println("📚 Exemplares agora: " + sys.getExemplares(tituloDev));
                            encontrarAluguel = true;
                            break;
                        }
                    }
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
