package models;

import java.util.Random;
import java.util.Scanner;
import models.Usuario;

public class Bibliotecario {
    private int id;
    private String nome;
    private String senha;

    public Bibliotecario(String nome, int id, String senha){
        this.nome = nome;
        this.id = id;
        this.senha = senha;
    }
    public Usuario cadastrarCliente(String nome, String email, String telefone){
        int id = 1_000_000 + new Random().nextInt(9_000_000);
        return new Usuario(nome, email, id, telefone);   }
    }


