package models;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livrosDisponiveis;

    public Biblioteca() {
        this.livrosDisponiveis = new ArrayList<>();

        livrosDisponiveis.add(new Livro("O Senhor dos Anéis", "Martins Fontes", 1954, "J.R.R. Tolkien", 3));
        livrosDisponiveis.add(new Livro("Dom Casmurro", "Editora X", 1899, "Machado de Assis", 4));
    }
    public List<Livro> getLivrosDisponiveis() {
        return livrosDisponiveis;
    }

    public void listarLivros() {
        for (Livro livro : livrosDisponiveis) {
            System.out.println("Título: " + livro.getTitulo() + " | Autor: " + livro.getAutor());
        }
    }
}
