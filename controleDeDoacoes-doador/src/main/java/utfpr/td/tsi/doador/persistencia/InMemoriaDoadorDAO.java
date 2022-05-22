package utfpr.td.tsi.doador.persistencia;

import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.stereotype.Component;
import utfpr.td.tsi.doador.modelo.Doador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoriaDoadorDAO implements DoadorDAO{

    private List<Doador> bancoDeDoadores = new ArrayList<Doador>();

    @Override
    public Doador cadastrar(Doador doador) {
        try{
            bancoDeDoadores.add(doador);
            return doador;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Doador alterar(Doador doador) {
        try{
            bancoDeDoadores.add(retornaIndiceDoador(doador), doador);
            return doador;
        }catch (RuntimeException re){
            return null;
        }

    }

    @Override
    public void remover(int id) {

        bancoDeDoadores.remove(retornaIndiceDoador(id));

    }

    @Override
    public Doador carregarPeloId(int id) {

        return bancoDeDoadores.get(retornaIndiceDoador(id));

    }

    @Override
    public List<Doador> carregarDoadores(){
        return bancoDeDoadores;
    }



    private int retornaIndiceDoador(Doador doadorBusca){

        for (Doador doadorInterno : bancoDeDoadores){
            if (doadorInterno.getId() == doadorBusca.getId()){
                return bancoDeDoadores.indexOf(doadorInterno);
            }
        }
        throw new RuntimeException("Doação não existe!");

    }

    private int retornaIndiceDoador(int idDoacao){

        for (Doador doadorInterno : bancoDeDoadores){
            if (doadorInterno.getId() == idDoacao){
                return bancoDeDoadores.indexOf(doadorInterno);
            }
        }
        throw new RuntimeException("Doação não existe!");

    }
/*
    private void validaDoacao(Doacao doacao){
        try {
            if (doacao.getId() < 0) {
                throw new RuntimeException("O id deve ser mairo que 0!");
            }
            if (doacao.getItemDoado() == null) {
                throw new RuntimeException("O item da doação deve ser informado!");
            }
            if (doacao.getDoador() == null) {
                throw new RuntimeException("O doador deve ser informado!");
            }
            if (doacao.getQuantidadeDoacao() <= 0) {
                throw new RuntimeException("A quantidade doada deve ser maior que 0!");
            }
            if (doacao.getDataDoacao().isBefore(LocalDate.now())) {
                throw new RuntimeException("A data da doação deve ser posterior a data atual!");
            }
        }catch (Exception exception){
            throw new RuntimeException("Erro nos dados da doação, verifique!");
        }
    }
*/
    private void cadastrarDoadoresIniciais(){

    }

}
