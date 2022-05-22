package utfpr.td.tsi.doacoes.persistencia.modelo;

import utfpr.td.tsi.doacoes.modelo.Doacao;

import java.util.List;

public interface DoacaoDAO {

    public Doacao cadastrar(Doacao doacao);

    public Doacao alterar(Doacao doacao);

    public void remover(int id);

    public Doacao carregarPeloId(int id);

    public List<Doacao> carregarDoacoes();

}
