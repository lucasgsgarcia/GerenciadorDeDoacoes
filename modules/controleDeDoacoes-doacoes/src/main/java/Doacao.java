package utfpr.td.tsi.doacoes;

import lombok.*;
import utfpr.td.tsi.doador.Doador;
import utfpr.td.tsi.item.Item;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Doacao {

    private int id;

    private Item itemDoado;

    private Doador doador;

    private LocalDate dataDoacao;

    private double quantidadeDoacao;

}
