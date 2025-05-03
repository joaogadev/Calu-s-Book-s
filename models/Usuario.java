package models;

public class usuario {
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
    public usuario(String nome, String email, int id, String telefone, int livrosAlugados) {
        this.nome = nome;
        this.email = email;
        this.id = id;
        this.telefone = telefone;
        this.livrosAlugados = livrosAlugados;
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
