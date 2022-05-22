package utfpr.td.tsi.doacoes.persistencia.modelo;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;
import utfpr.td.tsi.doacoes.modelo.Doacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoriaDoacaoDAO implements DoacaoDAO
{

    private List<Doacao> bancoDeDoacoes = new ArrayList<Doacao>();

    @Override
    public Doacao cadastrar(Doacao doacao) {
        try{
            validaDoacao(doacao);
            bancoDeDoacoes.add(doacao);
            return doacao;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public Doacao alterar(Doacao doacao) {

        validaDoacao(doacao);
        try{
            bancoDeDoacoes.add(retornaIndiceDoacao(doacao), doacao);
        }catch (RuntimeException re){
            return null;
        }
        return doacao;
    }

    @Override
    public void remover(int id) {

        bancoDeDoacoes.remove(retornaIndiceDoacao(id));

    }

    @Override
    public Doacao carregarPeloId(int id) {

        return bancoDeDoacoes.get(retornaIndiceDoacao(id));

    }

    @Override
    public List<Doacao> carregarDoacoes(){
        return bancoDeDoacoes;
    }

    private int retornaIndiceDoacao(Doacao doacaoBusca){

        for (Doacao doacaoInterna : bancoDeDoacoes){
            if (doacaoInterna.getId() == doacaoBusca.getId()){
                return bancoDeDoacoes.indexOf(doacaoInterna);
            }
        }
        throw new RuntimeException("Doação não existe!");

    }

    private int retornaIndiceDoacao(int idDoacao){

        for (Doacao doacaoInterna : bancoDeDoacoes){
            if (doacaoInterna.getId() == idDoacao){
                return bancoDeDoacoes.indexOf(doacaoInterna);
            }
        }
        throw new RuntimeException("Doação não existe!");

    }

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

    private void cadastrarDoacoesIniciais(){
        //aqui colocar a instanciação dos objetos para a API.
    }

}
