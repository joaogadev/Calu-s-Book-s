package models;

import java.util.Date;

public class Aluguel {
    private Date dataAluguel;
    private Date devolucaoAluguel;
    private String status;

    public Aluguel(Date dataAluguel, Date devolucaoAluguel, String status){
        this.dataAluguel = dataAluguel;
        this.devolucaoAluguel = devolucaoAluguel;
        this.status = status;
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


}
