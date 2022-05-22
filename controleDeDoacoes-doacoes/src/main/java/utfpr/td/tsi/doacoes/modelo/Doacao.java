package utfpr.td.tsi.doacoes.modelo;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import utfpr.td.tsi.doador.modelo.Doador;
import utfpr.td.tsi.item.modelo.Item;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Doacao extends RepresentationModel<Doacao> implements Serializable {

    private int id;

    private Item itemDoado;

    private Doador doador;

    private LocalDate dataDoacao;

    private double quantidadeDoacao;

}
