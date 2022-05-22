package utfpr.td.tsi.item.persistencia;

import utfpr.td.tsi.item.modelo.Item;

import java.util.List;

public interface ItemDAO {
    public Item cadastrar(Item item);

    public Item alterar(Item item);

    public void remover(int id);

    public Item carregarPeloId(int id);

    public List<Item> carregarItens();


 }
