package utfpr.td.tsi.item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoriaGerenciadorItem {

    private List<Item> bancoDeItens = new ArrayList<>();

    public void cadastrar(Item item){
        bancoDeItens.add(item);
    }
}
