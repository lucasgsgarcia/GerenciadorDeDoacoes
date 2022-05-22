package utfpr.td.tsi.doacoes.modelo;

import java.util.List;

public interface GerenciadorDoacao {

    public void cadastrar(Doacao doacao);

    public void alterar(Doacao doacao);

    public void remover(int id);

    public Doacao carregarPeloId(int id);

    public List<Doacao> carregarDoacoes();
}
