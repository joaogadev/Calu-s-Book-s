package models;

public class Livro {
    private String titulo;
    private String editora;
    private int anoPublicacao;
    private String autor;
    private int exemplares;

    public Livro(String titulo, String editora, int anoPublicacao, String autor, int exemplares) {
        this.titulo = titulo;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;
        this.exemplares = exemplares;
    }
    public String getTitulo() {return titulo;}
    public String getEditora() {return editora;}
    public int getAnoPublicacao() {return anoPublicacao;}
    public String getAutor() {return autor;}
    public int getExemplares() {return exemplares;}
}
