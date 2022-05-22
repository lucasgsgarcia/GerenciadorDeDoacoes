package utfpr.td.tsi.doador.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utfpr.td.tsi.doador.modelo.Doador;
import utfpr.td.tsi.doador.persistencia.InMemoriaDoadorDAO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doadores")
public class DoadorController {

    @Autowired
    InMemoriaDoadorDAO inMemoriaDoadorDao;


    public void criarDoador(Doador doador) {
        if (inMemoriaDoadorDao.cadastrar(doador) == null) {
            inMemoriaDoadorDao.cadastrar(doador);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Doador>> listarDoadores(){
        List<Doador> doadoresList = inMemoriaDoadorDao.carregarDoadores();
        if(doadoresList.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            for(Doador doador : doadoresList){
                doador.removeLinks();
                long id = doador.getId();
                doador.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DoadorController.class).listarUmDoador((int) id)).withSelfRel());
            }
            return new ResponseEntity<>(doadoresList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doador> listarUmDoador(@PathVariable(value = "id") int id){
        Optional<Doador> doador = Optional.ofNullable(inMemoriaDoadorDao.carregarPeloId(id));
        if(!doador.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            doador.get().removeLinks();
            doador.get().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DoadorController.class).listarDoadores()).withRel("Lista de Doadores"));
            return new ResponseEntity<Doador>(doador.get(), HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<Doador> substituirDoador(@RequestBody Doador doador){
        if (inMemoriaDoadorDao.alterar(doador) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Optional<Doador> doadorO = Optional.ofNullable(inMemoriaDoadorDao.carregarPeloId(doador.getId()));
            doadorO.get().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DoadorController.class).listarDoadores()).withRel("Lista de Doadores"));
            return new ResponseEntity<Doador>(doadorO.get(), HttpStatus.OK);
        }

    }
}
