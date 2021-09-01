package com.api.financeiro.services;

import java.util.List;
import java.util.Optional;

import com.api.financeiro.domain.Funcionario;
import com.api.financeiro.repositories.FuncionarioRepository;
import com.api.financeiro.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
   
   @Autowired
   private FuncionarioRepository repo;

   public Funcionario findById(Integer id){
      Optional<Funcionario> obj = repo.findById(id);
      return obj.orElseThrow(() -> 
         new ObjectNotFoundException("Objeto não encotrado id= " + id + ", Tipo "+ Funcionario.class.getName())
      );
   }
   public List<Funcionario> findAll(){
      return repo.findAll();
   }

   public Page<Funcionario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
      PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
      return repo.findAll(pageRequest);
   }

   public Funcionario login(String email, String senha){
      Optional<Funcionario> obj = repo.findByEmail(email);
      Funcionario funcionario = obj.orElseThrow(() -> new ObjectNotFoundException(
         "Objeto não encotrado id= " + email + ", Tipo "+ Funcionario.class.getName()
      ));

      if(funcionario.getSenha().equals(senha)){
         return funcionario;
      }
      else
         throw new ObjectNotFoundException("Objeto não encontrado! Email: "+ email + " Senha:"+ senha);
   }

   public Funcionario insert(Funcionario funcionario){
      funcionario.setId(null);
      return repo.save(funcionario);
   }

   public Funcionario update(Funcionario funcionario){
      findById(funcionario.getId());
      return repo.save(funcionario);
   }
   
   public void delete(Integer id){
      findById(id);
      repo.deleteById(id);

   }

}
