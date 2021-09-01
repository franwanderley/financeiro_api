package com.api.financeiro.domain.enums;

public enum TipoReceita {
   DESPESA(1, "despesa"),
   LUCROS(2, "Administrador");

   private int cod;
   private String descricao; 

   private TipoReceita(int cod, String descricao){
      this.cod = cod;
      this.descricao = descricao;
   }
   private TipoReceita(){
   }

   public String getDescricao() {
      return descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public int getCod() {
      return cod;
   }

   public void setCod(int cod) {
      this.cod = cod;
   }

   public static TipoReceita toEnum(Integer cod){
      if(cod == null)
         return null;
      for(TipoReceita x : TipoReceita.values()){
         if(cod.equals(x.getCod()))
            return x;
      }
      throw new IllegalArgumentException("Id invalido "+ cod);
   }
   
}

