package com.api.financeiro.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import com.api.financeiro.domain.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Funcionario implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String nome;
   private String email;
   private String senha;
   private String cargo;
   private Integer nivel_de_acesso;
   @JsonIgnore
   @OneToMany(mappedBy= "funcionario")
   private List<Receita> receitas = new ArrayList<>();
   
   public Funcionario(Integer id, String nome, String email, String senha, String cargo, TipoUsuario tipoUsuario) {
      this.id = id;
      this.nome = nome;
      this.email = email;
      this.senha = senha;
      this.cargo = cargo;
      this.nivel_de_acesso = tipoUsuario.getCod();
   }
   public List<Receita> getReceitas() {
      return receitas;
   }
   public void setReceitas(List<Receita> receitas) {
      this.receitas = receitas;
   }
   public Funcionario() {
   }
   
   public Integer getId() {
      return id;
   }
   public TipoUsuario getNivel_de_acesso() {
      return TipoUsuario.toEnum(nivel_de_acesso);
   }
   public void setNivel_de_acesso(TipoUsuario tipoUsuario) {
      this.nivel_de_acesso = tipoUsuario.getCod();
   }
   public String getCargo() {
      return cargo;
   }
   public void setCargo(String cargo) {
      this.cargo = cargo;
   }
   public String getSenha() {
      return senha;
   }
   public void setSenha(String senha) {
      this.senha = senha;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getNome() {
      return nome;
   }
   public void setNome(String nome) {
      this.nome = nome;
   }
   public void setId(Integer id) {
      this.id = id;
   }

   @Override
   public int hashCode(){
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }
   @Override
   public boolean equals(Object obj){
      if(this == obj)
         return true;
      if(obj == null)
         return false;
      if(getClass() == obj.getClass())
         return false;
      Funcionario other = (Funcionario) obj;
      if(id == null){
         if(other.id == null)
         return false;
      }
      else if(!id.equals(other.id))
         return false;
      return true;
   }
}
