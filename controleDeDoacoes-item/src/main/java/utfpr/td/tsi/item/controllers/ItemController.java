package utfpr.td.tsi.item.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utfpr.td.tsi.item.modelo.Item;
import utfpr.td.tsi.item.persistencia.InMemoriaItemDAO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    InMemoriaItemDAO inMemoriaItemDAO;

    @GetMapping()
    public ResponseEntity<List<Item>> listarItems(){
        List<Item> doadoresList = inMemoriaItemDAO.carregarItens();
        if(doadoresList.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            for(Item item : doadoresList){
                item.removeLinks();
                long id = item.getId();
                item.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class).listarUmItem((int) id)).withSelfRel());
            }
            return new ResponseEntity<>(doadoresList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> listarUmItem(@PathVariable(value = "id") int id){
        Optional<Item> item = Optional.ofNullable(inMemoriaItemDAO.carregarPeloId(id));
        if(!item.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            item.get().removeLinks();
            item.get().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class).listarItems()).withRel("Lista de Items"));
            return new ResponseEntity<Item>(item.get(), HttpStatus.OK);
        }
    }

}
