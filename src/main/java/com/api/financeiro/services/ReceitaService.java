package com.api.financeiro.services;

import java.util.List;
import java.util.Optional;

import com.api.financeiro.domain.Receita;
import com.api.financeiro.repositories.ReceitaRepository;
import com.api.financeiro.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {
   
   @Autowired
   private ReceitaRepository repo;

   public Receita findById(Integer id){
      Optional<Receita> obj = repo.findById(id);
      return obj.orElseThrow(() -> 
         new ObjectNotFoundException("Objeto não encotrado id= " + id + ", Tipo "+ Receita.class.getName())
      );
   }
   public List<Receita> findAll(){
      return repo.findAll();
   }

   public Page<Receita> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
      PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
      return repo.findAll(pageRequest);
   }

   public Receita insert(Receita receita){
      receita.setId(null);
      return repo.save(receita);
   }

   public Receita update(Receita receita){
      findById(receita.getId());
      return repo.save(receita);
   }
   
   public void delete(Integer id){
      findById(id);
      repo.deleteById(id);

   }

}
