package com.api.financeiro.resources;

import java.net.URI;
import java.util.List;

import com.api.financeiro.domain.Funcionario;
import com.api.financeiro.services.FuncionarioService;

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
@RequestMapping(value = "funcionarios")
public class FuncionarioResources {

   @Autowired
   private FuncionarioService service;

   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Void> insert(@RequestBody Funcionario funcionario){
      Funcionario obj = service.insert(funcionario);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
      .buildAndExpand(obj.getId()).toUri();
      return ResponseEntity.created(uri).build();
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<Funcionario> findById(@PathVariable Integer id){
      Funcionario obj = service.findById(id);
      return ResponseEntity.ok().body(obj);
   }

   @RequestMapping(value = "/page", method = RequestMethod.GET)
   public ResponseEntity<Page<Funcionario>> findPage( 
      @RequestParam(value = "page", defaultValue = "0") Integer page, 
      @RequestParam(value = "linesPerPage", defaultValue = "5" ) Integer linesPerPage, 
      @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
      @RequestParam(value = "direction", defaultValue = "DESC") String direction
   ){

      Page<Funcionario> funcionarios = service.findPage(page, linesPerPage, orderBy, direction);
      return ResponseEntity.ok().body(funcionarios);
   }

   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<List<Funcionario>> list(){
      List<Funcionario> funcionarios = service.findAll();
      return ResponseEntity.ok().body(funcionarios);
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Funcionario funcionario){
      funcionario.setId(id);
      service.update(funcionario);

      return ResponseEntity.noContent().build();
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Void> delete(@PathVariable Integer id){
      service.delete(id);
      return ResponseEntity.noContent().build();
   }

}
