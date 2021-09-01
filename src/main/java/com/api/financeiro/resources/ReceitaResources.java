package com.api.financeiro.resources;

import java.net.URI;
import java.util.List;

import com.api.financeiro.domain.Receita;
import com.api.financeiro.services.ReceitaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "receitas")
public class ReceitaResources {

   @Autowired
   private ReceitaService service;

   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Void> insert(@RequestBody Receita receita){
      receita.setId(null);
      Receita obj = service.insert(receita);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
      .buildAndExpand(obj.getId()).toUri();
      return ResponseEntity.created(uri).build();
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<Receita> findById(@PathVariable Integer id){
      Receita obj = service.findById(id);
      return ResponseEntity.ok().body(obj);
   }

   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<List<Receita>> list(){
      List<Receita> Receitas = service.findAll();
      return ResponseEntity.ok().body(Receitas);
   }

   @RequestMapping(value = "/page", method = RequestMethod.GET)
   public ResponseEntity<Page<Receita>> findPage( 
      @RequestParam(value = "page", defaultValue = "0") Integer page, 
      @RequestParam(value = "linesPerPage", defaultValue = "5" ) Integer linesPerPage, 
      @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
      @RequestParam(value = "direction", defaultValue = "DESC") String direction
   ){

      Page<Receita> receitas = service.findPage(page, linesPerPage, orderBy, direction);
      return ResponseEntity.ok().body(receitas);
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Receita receita){
      receita.setId(id);
      service.update(receita);

      return ResponseEntity.noContent().build();
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Void> delete(@PathVariable Integer id){
      service.delete(id);
      return ResponseEntity.noContent().build();
   }

}
