package com.api.financeiro.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.api.financeiro.domain.enums.TipoReceita;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Receita implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String nome;
   private String descricao;
   private Double preco;
   @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
   private Date data;
   private Integer tipo;
   @ManyToOne
   @JoinColumn(name = "funcionario_id")
   private Funcionario funcionario;

   public Receita(Integer id, String nome, String descricao, Double preco, Date data, TipoReceita tipo) {
      this.id = id;
      this.nome = nome;
      this.descricao = descricao;
      this.preco = preco;
      this.data = data;
      this.tipo = tipo.getCod();
   }
   public Receita() {
   }

   public TipoReceita getTipo() {
      return TipoReceita.toEnum(tipo);
   }
   public Integer getId() {
      return id;
   }
   public void setId(Integer id) {
      this.id = id;
   }
   public String getNome() {
      return nome;
   }
   public void setNome(String nome) {
      this.nome = nome;
   }
   public String getDescricao() {
      return descricao;
   }
   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }
   public Double getPreco() {
      return preco;
   }
   public void setPreco(Double preco) {
      this.preco = preco;
   }
   public Date getData() {
      return data;
   }
   public void setData(Date data) {
      this.data = data;
   }
   public void setTipo(TipoReceita tipo) {
      this.tipo = tipo.getCod();
   }
   public Funcionario getFuncionario(){
      return this.funcionario;
   }
   public void setFuncionario(Funcionario funcionario){
      this.funcionario = funcionario;
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
      Receita other = (Receita) obj;
      if(id == null){
         if(other.id == null)
         return false;
      }
      else if(!id.equals(other.id))
         return false;
      return true;
   }
}
