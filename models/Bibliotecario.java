package models;
import java.util.Random;
import java.util.Scanner;

public class Bibliotecario {
    private int id;
    private String nome;
    private String senha;

public Bibliotecario(String nome, int id, String senha){
    this.nome = nome;
    this.id = id;
    this.senha = senha;

}
public void cadastrarCliente(){
        Scanner sc = new Scanner(System.in);
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
}

