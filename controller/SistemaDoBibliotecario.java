package controller;

import models.Usuario;
import models.Biblioteca;
import java.util.Scanner;
import java.util.Random;

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
            System.out.print("Qual o nome do Cliente: ");
            String nome = sc.nextLine();
            System.out.print("Qual o email do Cliente: ");
            String email = sc.nextLine();
            System.out.print("Qual o telefone do Cliente: ");
            String telefone = sc.nextLine();
            Random rd = new Random();
            int id = 1000000 + rd.nextInt(9000000);
            Usuario user = new Usuario(nome, email, id, telefone);
            System.out.println("Livros Alugados: " + user.getLivrosAlugados());

        }
        System.out.println("Livros Disponíveis: ");
        Biblioteca biblio = new Biblioteca();
        biblio.listarLivros();



    }
}
