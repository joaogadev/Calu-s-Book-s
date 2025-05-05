package controller;

import java.util.Scanner;
import models.Biblioteca;
import models.Bibliotecario;
import models.Usuario;

public class SistemaDoBibliotecario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("O cliente já é cadastrado na Biblioteca?[sim/não]");
        String resposta = sc.nextLine().toLowerCase();
        if (resposta.equalsIgnoreCase("sim")) {
            System.out.print("Digite o seu id: ");
            int id = sc.nextInt();
            Usuario user = new Usuario(id);
            System.out.println("Livros Alugados: " + user.getLivrosAlugados());

        } else {
            Bibliotecario bibliotecario = new Bibliotecario("João", 1, "senha123");
            bibliotecario.cadastrarCliente();

        }
        System.out.println("Livros Disponíveis: ");
        Biblioteca biblio = new Biblioteca();
        biblio.listarLivros();



    }
}
