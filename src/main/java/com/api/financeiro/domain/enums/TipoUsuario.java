package com.api.financeiro.domain.enums;

public enum TipoUsuario {
   USER(1, "Usuario"),
   ADMIN(2, "Administrador");

   private int cod;
   private String descricao; 

   private TipoUsuario(int cod, String descricao){
      this.cod = cod;
      this.descricao = descricao;
   }
   private TipoUsuario(){
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

   public static TipoUsuario toEnum(Integer cod){
      if(cod == null)
         return null;
      for(TipoUsuario x : TipoUsuario.values()){
         if(cod.equals(x.getCod()))
            return x;
      }
      throw new IllegalArgumentException("Id invalido "+ cod);
   }
   
}

