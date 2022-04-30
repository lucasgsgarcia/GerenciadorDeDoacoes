package utfpr.td.tsi.doador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoriaGerenciadorDoador {

    private List<Doador> bancoDeDoadores = new ArrayList<>();

    public void cadastrar(Doador doador){
        if((LocalDate.now().getYear() -18) > doador.getDataDeNascimeto().getYear()){
            throw new RuntimeException("Doador menor de idade!");
        }
    }
}
