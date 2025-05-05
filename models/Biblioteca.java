package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Biblioteca {
    private List<Livro> livrosDisponiveis;
    private List<Aluguel> historicoAlugueis;

    public Biblioteca() {
        this.livrosDisponiveis = new ArrayList<>();
        this.historicoAlugueis = new ArrayList<>();

        // Exemplo de inicialização com id fictício:
        livrosDisponiveis.add(new Livro("O Senhor dos Anéis", "Martins Fontes", 1954, "J.R.R. Tolkien", 3));
        livrosDisponiveis.add(new Livro("Dom Casmurro", "Editora X", 1899, "Machado de Assis", 4));
        livrosDisponiveis.add(new Livro("O Senhor dos Anéis", "Martins Fontes", 1954, "J.R.R. Tolkien", 3));
        livrosDisponiveis.add(new Livro("Dom Casmurro", "Editora X", 1899, "Machado de Assis", 4));
        livrosDisponiveis.add(new Livro("1984", "Companhia das Letras", 1949, "George Orwell", 5));
        livrosDisponiveis.add(new Livro("A Revolução dos Bichos", "Companhia das Letras", 1945, "George Orwell", 2));
        livrosDisponiveis.add(new Livro("Cem Anos de Solidão", "Record", 1967, "Gabriel García Márquez", 3));
        livrosDisponiveis.add(new Livro("O Pequeno Príncipe", "Agir", 1943, "Antoine de Saint-Exupéry", 6));
        livrosDisponiveis.add(new Livro("Harry Potter e a Pedra Filosofal", "Rocco", 1997, "J.K. Rowling", 7));
        livrosDisponiveis.add(new Livro("O Hobbit", "Martins Fontes", 1937, "J.R.R. Tolkien", 4));
        livrosDisponiveis.add(new Livro("A Menina que Roubava Livros", "Intrínseca", 2005, "Markus Zusak", 3));
        livrosDisponiveis.add(new Livro("O Código Da Vinci", "Sextante", 2003, "Dan Brown", 5));
        livrosDisponiveis.add(new Livro("Capitães da Areia", "Companhia das Letras", 1937, "Jorge Amado", 2));
        livrosDisponiveis.add(new Livro("A Metamorfose", "L&PM", 1915, "Franz Kafka", 3));
        livrosDisponiveis.add(new Livro("Orgulho e Preconceito", "Penguin", 1813, "Jane Austen", 4));
        livrosDisponiveis.add(new Livro("Morte e Vida Severina", "José Olympio", 1955, "João Cabral de Melo Neto", 2));
        livrosDisponiveis.add(new Livro("Grande Sertão: Veredas", "Nova Fronteira", 1956, "João Guimarães Rosa", 3));
    }
    public boolean alugarLivro(String titulo, Usuario usuario) {

        // 1) Verifica se o usuário ainda pode alugar mais
        if (!usuario.podeAlugarmais()) {
            System.out.println("Usuário já atingiu o limite de 5 livros.");
            return false;
        }
        // 2) Procura o livro pelo título
        Optional<Livro> opt = livrosDisponiveis.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();

        if (opt.isEmpty()) {
            System.out.println("Livro não encontrado.");
            return false;
        }

        Livro livro = opt.get();

        // 3) Verifica se há exemplares disponíveis
        if (!livro.podeAlugar()) {
            System.out.println("Não há exemplares disponíveis para este livro.");
            return false;
        }

        // 4) Efetiva o aluguel
        livro.decrementarExemplar();
        usuario.adicionarLivros();

        Aluguel aluguel = new Aluguel();
        historicoAlugueis.add(aluguel);

        System.out.println("Livro '" + livro.getTitulo() + "' alugado com sucesso!");
        aluguel.exibirDetalhes();
        return true;
    }

    public boolean devolverLivro(String titulo, Usuario usuario) {
        Optional<Livro> opt = livrosDisponiveis.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();

        if (opt.isEmpty()) {
            System.out.println("Livro não cadastrado nesta biblioteca.");
            return false;
        }

        Livro livro = opt.get();
        livro.incrementarExemplar();
        usuario.removerLivros();

        // Atualiza o histórico: marca o último registro ALUGADO como DEVOLVIDO
        for (int i = historicoAlugueis.size() - 1; i >= 0; i--) {
            Aluguel a = historicoAlugueis.get(i);
            if ("ALUGADO".equals(a.getStatus())) {
                a.setDevolucaoAluguel(new Date());
                a.setStatus("DEVOLVIDO");
                break;
            }
        }
        System.out.println("Livro '" + livro.getTitulo() + "' devolvido com sucesso!");
        return true;
    }
    public List<Livro> getLivrosDisponiveis() {
        return livrosDisponiveis;
    }
    public void listarLivros() {
        for (Livro livro : livrosDisponiveis) {
            System.out.println(
                    "Título: " + livro.getTitulo() +
                            " | Autor: " + livro.getAutor() +
                            " | Exemplares: " + livro.getExemplares()
            );
        }
    }
}
