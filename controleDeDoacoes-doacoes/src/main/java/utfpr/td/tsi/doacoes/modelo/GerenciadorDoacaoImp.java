package utfpr.td.tsi.doacoes.modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import utfpr.td.tsi.doacoes.persistencia.modelo.DoacaoDAO;

import java.util.List;

@Component
public class GerenciadorDoacaoImp implements GerenciadorDoacao{

    @Autowired
    private DoacaoDAO doacaoDAO;

    @Override
    public void cadastrar(Doacao doacao) {
        doacaoDAO.cadastrar(doacao);
    }

    @Override
    public void alterar(Doacao doacao) {
        doacaoDAO.alterar(doacao);
    }

    @Override
    public void remover(int id) {
        doacaoDAO.remover(id);
    }

    @Override
    public Doacao carregarPeloId(int id) {
        return doacaoDAO.carregarPeloId(id);
    }

    @Override
    public List<Doacao> carregarDoacoes(){
        return doacaoDAO.carregarDoacoes();
    }
}
