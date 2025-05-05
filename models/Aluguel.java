package models;

import java.util.Date;

public class Aluguel {
    private Date dataAluguel;
    private Date devolucaoAluguel;
    private String status;

    public Aluguel(){
        this.dataAluguel = new Date();
        this.devolucaoAluguel = null;
        this.status = "Alugado";
    }
    public Date getDataAluguel(){return dataAluguel;}
    public Date getDevolucaoAluguel(){return devolucaoAluguel;}
    public String getStatus(){return status;}

    // Setters necessários para atualizar histórico
    public void setDevolucaoAluguel(Date devolucao) {
        this.devolucaoAluguel = devolucao;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public void exibirDetalhes() {
        System.out.println("Data de aluguel: "   + dataAluguel);
        System.out.println("Data de devolução: " + devolucaoAluguel);
        System.out.println("Status: "            + status);
    }

}
