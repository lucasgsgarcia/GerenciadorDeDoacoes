package utfpr.td.tsi.item.modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utfpr.td.tsi.item.persistencia.InMemoriaItemDAO;

import java.util.List;

@Component
public class GerenciadorItemImp implements GerenciadorItem {

    @Autowired
    private InMemoriaItemDAO itemDAO;

    @Override
    public Item cadastrar(Item item){
       return itemDAO.cadastrar(item);
    }

    @Override
    public Item alterar(Item item){
        return itemDAO.alterar(item);
    }

    @Override
    public void remover(int id){
        itemDAO.remover(id);
    }

    @Override
    public Item carregarItem(int id){
        return itemDAO.carregarPeloId(id);
    }

   @Override
    public List<Item> carregarItens(){
         return itemDAO.carregarItens();
    }


}
