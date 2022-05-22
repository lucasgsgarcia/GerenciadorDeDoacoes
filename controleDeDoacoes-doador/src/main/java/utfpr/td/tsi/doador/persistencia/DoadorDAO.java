package utfpr.td.tsi.doador.persistencia;

import utfpr.td.tsi.doador.modelo.Doador;

import java.util.List;

public interface DoadorDAO {

    public Doador cadastrar(Doador doador);

    public Doador alterar(Doador doador);

    public void remover(int id);

    public Doador carregarPeloId(int id);

    public List<Doador> carregarDoadores();
}
