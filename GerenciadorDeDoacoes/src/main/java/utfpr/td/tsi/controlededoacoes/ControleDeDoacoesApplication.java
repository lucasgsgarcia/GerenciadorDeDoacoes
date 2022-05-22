package utfpr.td.tsi.controlededoacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan("utfpr.td.tsi.doacoes")
public class ControleDeDoacoesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControleDeDoacoesApplication.class, args);
    }

    @PostConstruct
    public void init(){

    }

}
