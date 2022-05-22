package utfpr.td.tsi.controlededoacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import utfpr.td.tsi.doacoes.modelo.Doacao;
import utfpr.td.tsi.doacoes.modelo.GerenciadorDoacao;
import utfpr.td.tsi.doacoes.modelo.GerenciadorDoacaoImp;
import utfpr.td.tsi.doador.modelo.Doador;
import utfpr.td.tsi.doador.modelo.GerenciadorDoador;
import utfpr.td.tsi.item.modelo.GerenciadorItem;
import utfpr.td.tsi.item.modelo.Item;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
@ComponentScan("utfpr.td.tsi")
public class ControleDeDoacoesApplication {

    @Autowired
    private GerenciadorDoacao gerenciadorDoacao;
    
    @Autowired
    private GerenciadorItem gerenciadorItem;

    @Autowired
    private GerenciadorDoador gerenciadorDoador;

    public static void main(String[] args) {
        SpringApplication.run(ControleDeDoacoesApplication.class, args);
    }

    @PostConstruct
    public void init(){

        Item item1 = new Item(1, "Roupa", "Moleton TSI", "Semi-novo");
        Item item2 = new Item(2, "Comida", "Saco de arroz", "vence em 2025");
        Item item3 = new Item(3, "Comida", "Pacote de miojo", "Vence em 2023");

        gerenciadorItem.cadastrar(item1);
        gerenciadorItem.cadastrar(item2);
        gerenciadorItem.cadastrar(item3);

        Doador doador1 = new Doador( 1, "Lucas Daniel Batista", LocalDate.of(
                2001, 10, 20), "45 999999999", "lucasBatista@teste.com.br", "Av Estados Unidos", "52365526526" );
        Doador doador2 = new Doador( 2, "Lucas Garcia", LocalDate.of(
                2000, 4, 24), "47 888888888", "lucasGarcia@Uol.com", "Rua Curitiba", "52632184500" );
        Doador doador3 = new Doador( 2, "Lucas Garcia", LocalDate.of(
                2000, 4, 24), "46 777777777", "JoaoGarcia@Uol.com", "Rua Salvador", "52632184500" );

        gerenciadorDoador.cadastrar(doador1);
        gerenciadorDoador.cadastrar(doador2);
        gerenciadorDoador.cadastrar(doador3);

        gerenciadorDoacao.cadastrar(new Doacao(1,item1, doador1, LocalDate.now(), 2));
        gerenciadorDoacao.cadastrar(new Doacao(2,item2, doador2, LocalDate.now(), 5));
        gerenciadorDoacao.cadastrar(new Doacao(3,item3, doador3, LocalDate.now(), 80));
    }

}
