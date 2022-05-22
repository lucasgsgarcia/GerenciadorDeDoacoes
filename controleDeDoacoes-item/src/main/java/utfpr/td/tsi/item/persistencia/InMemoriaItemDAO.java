package utfpr.td.tsi.item.persistencia;

import utfpr.td.tsi.item.modelo.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class InMemoriaItemDAO implements ItemDAO {

    private List<Item> bancoDeItens = new ArrayList<>();

    @Override
    public Item cadastrar(Item item) {
        try{
            bancoDeItens.add(item);
            return item;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Item alterar(Item item) {
        try{
            bancoDeItens.add(retornaIndiceDoador(item), item);
            return item;
        }catch (RuntimeException re){
            return null;
        }

    }

    @Override
    public void remover(int id) {

        bancoDeItens.remove(retornaIndiceDoador(id));

    }


    @Override
    public Item carregarPeloId(int id) {

        return bancoDeItens.get(retornaIndiceDoador(id));

    }

    @Override
    public List<Item> carregarItens(){
        return bancoDeItens;
    }



    private int retornaIndiceDoador(Item item){

        for (Item item1 : bancoDeItens){
            if (item1.getId() == item.getId()){
                return bancoDeItens.indexOf(item1);
            }
        }
        throw new RuntimeException("Item não existe!");

    }

    private int retornaIndiceDoador(int idItem){

        for (Item item : bancoDeItens){
            if (item.getId() == idItem){
                return bancoDeItens.indexOf(item);
            }
        }
        throw new RuntimeException("Item não existe!");

    }


}
