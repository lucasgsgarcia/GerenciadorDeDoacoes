package utfpr.td.tsi.item;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Item {

    private int id;

    private String tipo;

    private String descricao;

    private String situacao;

}
