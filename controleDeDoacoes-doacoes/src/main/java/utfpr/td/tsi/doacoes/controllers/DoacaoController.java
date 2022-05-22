package utfpr.td.tsi.doacoes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utfpr.td.tsi.doacoes.modelo.Doacao;
import utfpr.td.tsi.doacoes.persistencia.modelo.InMemoriaDoacaoDAO;
import utfpr.td.tsi.doador.controllers.DoadorController;
import utfpr.td.tsi.doador.persistencia.InMemoriaDoadorDAO;
import utfpr.td.tsi.item.controllers.ItemController;
import utfpr.td.tsi.item.persistencia.InMemoriaItemDAO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

    @Autowired
    InMemoriaDoacaoDAO inMemoriaDoacaoDAO;

    @Autowired
    InMemoriaItemDAO inMemoriaItemDAO;

    @Autowired
    InMemoriaDoadorDAO inMemoriaDoadorDAO;

    @PostMapping()
    public ResponseEntity<Doacao> criarDoacao(@RequestBody Doacao doacao){
        if (inMemoriaDoacaoDAO.cadastrar(doacao) == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            inMemoriaDoacaoDAO.cadastrar(doacao);
            inMemoriaItemDAO.cadastrar(doacao.getItemDoado());
            inMemoriaDoadorDAO.cadastrar(doacao.getDoador());
            return new ResponseEntity<Doacao>(doacao, HttpStatus.OK);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Doacao>> listarDoacoes(){
        List<Doacao> doacoesList = inMemoriaDoacaoDAO.carregarDoacoes();
        if(doacoesList.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            for(Doacao doacao : doacoesList){
                doacao.removeLinks();
                doacao.getDoador().removeLinks();
                doacao.getItemDoado().removeLinks();
                long id = doacao.getId();
                Optional<Doacao> doacaoO = Optional.ofNullable(inMemoriaDoacaoDAO.carregarPeloId((int) id));
                doacao.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DoacaoController.class).listarUmaDoacao((int) id)).withSelfRel());
                doacao.getDoador().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DoadorController.class).listarUmDoador(doacao.getDoador().getId())).withRel("Doador Responsável"));
                doacao.getItemDoado().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class).listarUmItem(doacao.getItemDoado().getId())).withSelfRel());
            }
            return new ResponseEntity<>(doacoesList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doacao> listarUmaDoacao(@PathVariable(value = "id") int id){
        Optional<Doacao> doacaoO = Optional.ofNullable(inMemoriaDoacaoDAO.carregarPeloId(id));
        if(!doacaoO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            doacaoO.get().removeLinks();
            doacaoO.get().getItemDoado().removeLinks();
            doacaoO.get().getDoador().removeLinks();
            doacaoO.get().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DoacaoController.class).listarDoacoes()).withRel("Lista de Doações"));
            doacaoO.get().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DoadorController.class).listarUmDoador(doacaoO.get().getDoador().getId())).withRel("Doador Responsável"));
            doacaoO.get().getItemDoado().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class).listarUmItem(doacaoO.get().getItemDoado().getId())).withSelfRel());
            return new ResponseEntity<Doacao>(doacaoO.get(), HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<Doacao> substituirDoacao(@RequestBody Doacao doacao){
        if (inMemoriaDoacaoDAO.alterar(doacao) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Optional<Doacao> doacaoO = Optional.ofNullable(inMemoriaDoacaoDAO.carregarPeloId(doacao.getId()));
            doacaoO.get().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DoacaoController.class).listarDoacoes()).withRel("Lista de Doações"));
            return new ResponseEntity<Doacao>(doacaoO.get(), HttpStatus.OK);
        }

    }

}
