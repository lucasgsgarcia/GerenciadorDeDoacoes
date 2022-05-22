package utfpr.td.tsi.doador.modelo;

import java.util.List;

public interface GerenciadorDoador {

    public Doador cadastrar(Doador doador);

    public Doador alterar(Doador doador);

    public void remover(int id);

    public Doador carregarPeloId(int id);

    public List<Doador> carregarDoadores();

}
