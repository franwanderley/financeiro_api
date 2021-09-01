package com.api.financeiro.resources;

import com.api.financeiro.domain.Funcionario;
import com.api.financeiro.services.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "login")
public class LoginResources {
   @Autowired
   private FuncionarioService service;

   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Funcionario> login(@RequestBody Funcionario funcionario){
      funcionario = service.login(funcionario.getEmail(), funcionario.getSenha());
      return ResponseEntity.ok().body(funcionario);
   }
}
