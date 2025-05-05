package models;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Aluguel {
    private Date dataAluguel;
    private Date devolucaoAluguel;
    private String status;

    // Construtor padrão
    public Aluguel() {
        this.dataAluguel = new Date();
        this.devolucaoAluguel = null;
        this.status = "Alugado";
    }

    // Construtor com parâmetros
    public Aluguel(Date dataAluguel, Date devolucaoAluguel, String status) {
        this.dataAluguel = dataAluguel;
        this.devolucaoAluguel = devolucaoAluguel;
        this.status = status;
    }

    // Método que calcula multa por atraso
    public void calcularMulta() {
        Date hoje = new Date();

        if (hoje.after(devolucaoAluguel)) {
            long diferencaMillis = hoje.getTime() - devolucaoAluguel.getTime();
            long diasAtraso = TimeUnit.DAYS.convert(diferencaMillis, TimeUnit.MILLISECONDS);
            double valorMulta = diasAtraso * 5.0;
            System.out.println("Multa por atraso: R$ " + valorMulta);
        } else {
            System.out.println("Nenhuma multa. A devolução está no prazo.");
        }
    }

    public Date getDataAluguel() {
        return dataAluguel;
    }

    public Date getDevolucaoAluguel() {
        return devolucaoAluguel;
    }

    public String getStatus() {
        return status;
    }

    public void setDevolucaoAluguel(Date devolucao) {
        this.devolucaoAluguel = devolucao;
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
