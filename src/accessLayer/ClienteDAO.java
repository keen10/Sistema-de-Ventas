/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package accessLayer;

import accessLayer.resource.DBconection;
import bussinessLayer.ClienteBO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author java
 */
public class ClienteDAO {
    private static final String TABLE = "clientes";

    public static ClienteBO get(int id) throws ExceptionDAL {
        ClienteBO cliente = null;

        try{
            Statement query = DBconection.createStatement();
            ResultSet result = query.executeQuery(
                    "SELECT * FROM " + TABLE + " WHERE id = " + id
                    );

            while(result.next()){
                cliente = new ClienteBO(
                        result.getInt("id"),
                        result.getString("nombre"),
                        result.getString("rut"),
                        result.getString("direccion"),
                        result.getString("correo"));
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

        return cliente;
   }

    public static ArrayList<ClienteBO> getAll() throws ExceptionDAL {
        ArrayList<ClienteBO> lista = new ArrayList<ClienteBO>();
        ClienteBO cliente = null;

        try{
            Statement query = DBconection.createStatement();
            ResultSet result = query.executeQuery(
                    "SELECT * FROM " + TABLE
                    );

            while(result.next()){
                cliente = new ClienteBO(
                        result.getInt("id"),
                        result.getString("nombre"),
                        result.getString("rut"),
                        result.getString("direccion"),
                        result.getString("correo"));

                lista.add(cliente);
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

    public static void save(ClienteBO cliente) throws ExceptionDAL {
        try{
            Statement query = DBconection.createStatement();
            query.executeUpdate(
                    "INSERT INTO " + TABLE + " ( nombre, rut, direccion, correo ) " +
                    "VALUES ('" + cliente.getNombre() + "', '" + cliente.getRut() + "', '" +
                    cliente.getDireccion() + "', '" + cliente.getCorreo() + "')"
                    );

            query.close();
        }
        catch(SQLException ex){
            switch (ex.getErrorCode()){
                case ExceptionDAL.ERROR_ACCESS_CLOSE:
                    throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, ex);

                case ExceptionDAL.ERROR_UNIQUE_KEY:
                    throw new ExceptionDAL("Ya existe un cliente con el RUT: " + cliente.getRut(), ex);
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

    public static void update(ClienteBO cliente) throws ExceptionDAL {
        try{
            Statement query = DBconection.createStatement();
            query.executeUpdate(
                    "UPDATE " + TABLE + " SET " +
                    "nombre = '" + cliente.getNombre() +
                    "', rut = '" + cliente.getRut() +
                    "', direccion = '" + cliente.getDireccion() +
                    "', correo = '" + cliente.getCorreo() +
                    "' WHERE id = " + cliente.getId()
                    );

            query.close();
        }
        catch(SQLException ex){
            switch (ex.getErrorCode()){
                case ExceptionDAL.ERROR_ACCESS_CLOSE:
                    throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, ex);

                case ExceptionDAL.ERROR_UNIQUE_KEY:
                    throw new ExceptionDAL("Ya existe un cliente con el RUT: " + cliente.getRut(), ex);
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
}
