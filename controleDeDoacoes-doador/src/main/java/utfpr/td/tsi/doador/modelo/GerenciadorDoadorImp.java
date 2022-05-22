package utfpr.td.tsi.doador.modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utfpr.td.tsi.doador.persistencia.DoadorDAO;

import java.util.List;

@Component
public class GerenciadorDoadorImp implements GerenciadorDoador{


    @Autowired
    private DoadorDAO doadorDAO;

    @Override
    public Doador cadastrar(Doador doador) {
        return doadorDAO.cadastrar(doador);
    }

    @Override
    public Doador alterar(Doador doador) {
        return doadorDAO.alterar(doador);
    }

    @Override
    public void remover(int id) {
        doadorDAO.remover(id);
    }

    @Override
    public Doador carregarPeloId(int id) {
        return doadorDAO.carregarPeloId(id);
    }

    @Override
    public List<Doador> carregarDoadores(){
        return doadorDAO.carregarDoadores();
    }
}
