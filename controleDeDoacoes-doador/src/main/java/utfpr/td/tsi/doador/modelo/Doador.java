package utfpr.td.tsi.doador.modelo;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Doador extends RepresentationModel<Doador> implements Serializable {

    private int id;

    private String nome;

    private LocalDate dataDeNascimeto;

    private String telefone;

    private String email;

    private String endereco;

    private String cpfOuCnpj;

}