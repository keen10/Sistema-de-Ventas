/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bussinessLayer;

/**
 *
 * @author java
 */
public class CategoriaProductoBO {
   private int id;
   private java.lang.String nombre;

   public CategoriaProductoBO() {

   }

   public CategoriaProductoBO(int id, java.lang.String nombre) {
        this.id = id;
        this.nombre = nombre;
   }

   public int getId() {
      return id;
   }

   public void setId(int newId) throws ExceptionBOL {
      if( newId < 1 ){
          throw new ExceptionBOL("El id de la categoria debe ser mayor que 0");
      }
      this.id = newId;
   }

   public java.lang.String getNombre() {
      return nombre;
   }
   
   public void setNombre(java.lang.String newNombre) throws ExceptionBOL {
      if( newNombre.isEmpty() ){
          throw new ExceptionBOL("El nombre de la categoria no debe ser vacio");
      }
      this.nombre = newNombre;
   }

    @Override
   public String toString(){
       return nombre;
   }

}
