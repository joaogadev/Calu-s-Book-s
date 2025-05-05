package models;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Aluguel {
    private Date dataAluguel;
    private Date devolucaoAluguel;
    private String status;

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

    public Aluguel(Date dataAluguel, Date devolucaoAluguel, String status) {
        this.dataAluguel = dataAluguel;
        this.devolucaoAluguel = devolucaoAluguel;
        this.status = status;
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

    // Setters necessários para atualizar histórico
    public void setDevolucaoAluguel(Date devolucao) {
        this.devolucaoAluguel = devolucao;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}