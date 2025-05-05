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

    //Construtor para novo usuário
    public Usuario(String nome, String email, int id, String telefone) {
        this.nome = nome;
        this.email = email;
        this.id = id;
        this.telefone = telefone;
        this.livrosAlugados = 0;
    }

    public Usuario(int id) {
        this.id = id;
        this.livrosAlugados = 0;
    }
    //metodos para controle de livros
    public boolean podeAlugarmais(){
        return livrosAlugados < 5;
    }
    public void adicionarLivros() {
        if(podeAlugarmais()){
            livrosAlugados++;
        } else{
            throw new IllegalStateException("Limite de 5 livros atingido");
        }
    }
    public void removerLivros() {
        if (livrosAlugados > 0) {
            livrosAlugados--;
        }
    }


}
