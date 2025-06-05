package models;

import java.util.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import models.Livro;
import models.Usuario;

public class Aluguel {
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataAluguel;
    private LocalDate devolucaoAluguel;
    private LocalDate devolucaoPrevista;
    private String status;

    // Construtor padrão
    public Aluguel() {
        this.dataAluguel = LocalDate.now();
        this.devolucaoAluguel = null;
        this.status = "Alugado";
    }

    public Aluguel(Usuario usuario, Livro livro) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataAluguel = LocalDate.now();
        this.devolucaoAluguel = null;
        this.devolucaoPrevista = dataAluguel.plusDays(7);
    }

    // Construtor com parâmetros
    public Aluguel(LocalDate dataAluguel, LocalDate devolucaoAluguel, String status) {
        this.dataAluguel = dataAluguel;
        this.devolucaoAluguel = devolucaoAluguel;
        this.status = status;
    }
    /**
     * Calcula os dias de atraso (se houver) entre devolução e previsão
     */
    public long getDiasAtraso() {
        if (devolucaoAluguel == null) return 0;
        long atraso = ChronoUnit.DAYS.between(devolucaoPrevista, devolucaoAluguel);
        return atraso > 0 ? atraso : 0;
    }

    // Método que calcula multa por atraso
    public double calcularMulta() {
        return getDiasAtraso() * 2.0;
    }

    public LocalDate getDataAluguel() {
        return dataAluguel;
    }
    public Usuario getUsuario() {return usuario;}
    public Livro getLivro() {return livro;}
    public LocalDate getDevolucaoAluguel() {
        return devolucaoAluguel;
    }
    public String getStatus() {
        return status;
    }

    public void setDevolucaoAluguel(LocalDate devolucaoAluguel) {
        this.devolucaoAluguel = devolucaoAluguel;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void exibirDetalhes() {
        System.out.println("Data de aluguel: " + dataAluguel);
        System.out.println("Data de devolução: " + devolucaoAluguel);
        System.out.println("Status: " + status);
    }
}
