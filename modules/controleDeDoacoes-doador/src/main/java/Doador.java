package utfpr.td.tsi.doador;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Doador {

    private int id;

    private String nome;

    private LocalDate dataDeNascimeto;

    private String telefone;

    private String email;

    private String endereco;

    private String cpfOuCnpj;

}