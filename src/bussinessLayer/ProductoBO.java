/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bussinessLayer;


/**
 *
 * @author java
 */
public class ProductoBO {
   private int id;
   private java.lang.String nombre;
   private int precio;
   private int stock;
   private CategoriaProductoBO categoria;

   public ProductoBO() {

   }

   public ProductoBO(int id, java.lang.String nombre, int precio, int stock, CategoriaProductoBO categoria) {
       this.id = id;
       this.nombre = nombre;
       this.precio = precio;
       this.stock = stock;
       this.categoria = categoria;
   }

   public int getId() {
      return id;
   }

   public void setId(int newId) throws ExceptionBOL {
      if( newId < 1 ){
          throw new ExceptionBOL("El id del producto debe ser mayor que 0");
      }
      this.id = newId;
   }

   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String newNombre) throws ExceptionBOL {
      if( newNombre.isEmpty() ){
          throw new ExceptionBOL("El nombre del producto no debe ser vacio");
      }
      this.nombre = newNombre;
   }

   public int getPrecio() {
      return precio;
   }

   public void setPrecio(int newPrecio) throws ExceptionBOL {
      if( newPrecio < 1 ){
          throw new ExceptionBOL("El precio del producto debe ser mayor que 0");
      }
      this.precio = newPrecio;
   }

   public int getStock() {
      return stock;
   }

   public void setStock(int newStock) throws ExceptionBOL {
      if( newStock < 1 ){
          throw new ExceptionBOL("El stock del producto debe ser mayor que 0");
      }
      this.stock = newStock;
   }

   public CategoriaProductoBO getCategoria() {
      return this.categoria;
   }
   
   public void setCategoria(CategoriaProductoBO categoria) throws ExceptionBOL {
       this.categoria = categoria;
   }

   @Override
   public String toString(){
       return nombre;
   }

}