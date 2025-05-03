package models;

public class Usuario {
    private String nome;
    private String email;
    private int id;
    private String telefone;
    private int livrosAlugados;

    //Getters
    public String getNome() {return nome;}
    public String getEmail() {return email;}
    public int getId() {return id;}
    public String getTelefone() {return telefone;}
    public int getLivrosAlugados() {return livrosAlugados;}

    //Construtor
    public Usuario(String nome, String email, int id, String telefone) {
        this.nome = nome;
        this.email = email;
        this.id = id;
        this.telefone = telefone;
        this.livrosAlugados = 0;
    }
    public Usuario(int id) {
        this.id = id;
    }

    //metodos para controle de livros
    public void adicionarLivros() {
        livrosAlugados++;
    }
    public void removerLivros() {
        if (livrosAlugados > 0) {
            livrosAlugados--;
        }
    }


}
