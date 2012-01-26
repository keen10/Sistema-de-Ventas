/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bussinessLayer;

/**
 *
 * @author java
 */
public class ClienteBO {

   private int id;
   private java.lang.String nombre;
   private java.lang.String rut;
   private java.lang.String direccion;
   private java.lang.String correo;

   public ClienteBO() {

   }

   public ClienteBO(int id, java.lang.String nombre, java.lang.String rut, java.lang.String direccion, java.lang.String correo) {
      this.id = id;
      this.nombre = nombre;
      this.rut = rut;
      this.direccion = direccion;
      this.correo = correo;
   }

   public int getId() {
      return id;
   }

   public void setId(int newId) throws ExceptionBOL {
      if( newId < 1 ){
          throw new ExceptionBOL("El id del cliente debe ser mayor a cero");
      }
      this.id = newId;
   }

   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String newNombre) throws ExceptionBOL {
      if( newNombre.isEmpty() ){
          throw new ExceptionBOL("El cliente debe tener un nombre");
      }
      this.nombre = newNombre;
   }

   public java.lang.String getRut() {
      return rut;
   }

   public void setRut(java.lang.String newRut) throws ExceptionBOL {
      if( newRut.isEmpty() ){
          throw new ExceptionBOL("El cliente debe tener un rut");
      }
      this.rut = newRut;
   }

   public java.lang.String getDireccion() {
      return direccion;
   }

   public void setDireccion(java.lang.String newDireccion) throws ExceptionBOL {
       if( newDireccion.isEmpty() ){
          throw new ExceptionBOL("El cliente debe tener una direccion");
      }
      this.direccion = newDireccion;
   }

   public java.lang.String getCorreo() {
      return correo;
   }

   public void setCorreo(java.lang.String newCorreo) throws ExceptionBOL {
      if( newCorreo.isEmpty() ){
          throw new ExceptionBOL("El cliente debe tener un correo");
      }
      this.correo = newCorreo;
   }
   
   @Override
   public String toString(){
       return nombre;
   }
}
