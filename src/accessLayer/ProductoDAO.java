/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package accessLayer;

import accessLayer.resource.DBconection;
import bussinessLayer.CategoriaProductoBO;
import bussinessLayer.ProductoBO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author java
 */
public class ProductoDAO {
   private static final String TABLE = "productos";

   public static void save(ProductoBO producto) throws ExceptionDAL {
       try{
            Statement query = DBconection.createStatement();
            query.executeUpdate(
                    "INSERT INTO " + TABLE + " ( nombre, precio, stock, id_categorias_productos ) " +
                    "VALUES ('" + producto.getNombre() + "', " + producto.getPrecio() + ", " +
                    producto.getStock() + ", " + producto.getCategoria().getId() + ")"
                    );

            query.close();
        }
        catch(SQLException ex){
            switch (ex.getErrorCode()){
                case ExceptionDAL.ERROR_ACCESS_CLOSE:
                    throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, ex);

                case ExceptionDAL.ERROR_UNIQUE_KEY:
                    throw new ExceptionDAL("Ya existe un producto con el nombre: " + producto.getNombre(), ex);
            }
        }

        finally{
            try {
                DBconection.close();
            }
            catch(SQLException ex){
                throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, ex);
            }
        }
   }

   public static ArrayList<ProductoBO> getAll(int idCategoria) throws ExceptionDAL {
        ArrayList<ProductoBO> lista = new ArrayList<ProductoBO>();
        ProductoBO producto = null;

        try{
            Statement query = DBconection.createStatement();
            ResultSet result = query.executeQuery(
                    "SELECT * FROM " + TABLE + " WHERE id_categorias_productos = " + idCategoria
                    );

            while(result.next()){
                CategoriaProductoBO categoria = new CategoriaProductoBO(idCategoria, null);
                producto = new ProductoBO(
                        result.getInt("id"),
                        result.getString("nombre"),
                        result.getInt("precio"),
                        result.getInt("stock"),
                        categoria);

                lista.add(producto);
            }

            result.close();
            query.close();
        }
        catch(SQLException ex){
            throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, ex);
        }

        finally{
            try {
                DBconection.close();
            }
            catch(SQLException ex){
                throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, ex);
            }
        }

        return lista;
   }

   public static void update(ProductoBO producto) throws ExceptionDAL {
       try{
            Statement query = DBconection.createStatement();
            query.executeUpdate(
                    "UPDATE " + TABLE + " SET " +
                    "nombre = '" + producto.getNombre() +
                    "', precio = " + producto.getPrecio() +
                    ", stock = " + producto.getStock() +
                    ", id_categorias_productos = " + producto.getCategoria().getId() +
                    " WHERE id = " + producto.getId()
                    );

            query.close();
        }
        catch(SQLException ex){
            switch (ex.getErrorCode()){
                case ExceptionDAL.ERROR_ACCESS_CLOSE:
                    throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, ex);

                case ExceptionDAL.ERROR_UNIQUE_KEY:
                    throw new ExceptionDAL("Ya existe un producto con el nombre: " + producto.getNombre(), ex);
            }
        }

        finally{
            try {
                DBconection.close();
            }
            catch(SQLException ex){
                throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, ex);
            }
        }
   }

   public static ProductoBO get(int id) throws ExceptionDAL {
      ProductoBO producto = null;

        try{
            Statement query = DBconection.createStatement();
            ResultSet result = query.executeQuery(
                    "SELECT * FROM " + TABLE + " WHERE id = " + id
                    );

            while(result.next()){
                //CategoriaProductoBO categoria = new CategoriaProductoBO(id, null);

                producto = new ProductoBO(
                        result.getInt("id"),
                        result.getString("nombre"),
                        result.getInt("precio"),
                        result.getInt("stock"),
                        null);
            }

            result.close();
            query.close();
        }
        catch(SQLException ex){
            throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, ex);
        }

        finally{
            try {
                DBconection.close();
            }
            catch(SQLException ex){
                throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, ex);
            }
        }

        return producto;
   }

}
