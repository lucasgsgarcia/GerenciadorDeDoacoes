package utfpr.td.tsi.item.modelo;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item extends RepresentationModel<Item> implements Serializable {

    private int id;

    private String tipo;

    private String descricao;

    private String situacao;

}
