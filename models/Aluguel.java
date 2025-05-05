package models;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Aluguel {
    private int id;
    private Date dataAluguel;
    private Date devolucaoAluguel;
    private String status;

    public void calcularMulta(){
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
}
