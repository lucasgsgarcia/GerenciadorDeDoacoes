package utfpr.td.tsi.item.modelo;

import java.util.List;

public interface GerenciadorItem {
    public Item cadastrar(Item item);

    public Item alterar(Item item);

    public void remover(int id);

    public Item carregarItem(int id);

    public List<Item> carregarItens();
}
