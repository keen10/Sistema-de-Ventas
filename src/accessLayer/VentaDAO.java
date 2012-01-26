/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package accessLayer;

import accessLayer.resource.DBconection;
import bussinessLayer.ItemVentaBO;
import bussinessLayer.VentaBO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jony
 */
public class VentaDAO {
    private static final int OBSOLETO = 1;
    private static final int NO_OBSOLETO = 0;

    private static final String TABLA = "ventas";
    private static final String TABLA_ITEMS_VENTA = "itemes_venta";

    public static ArrayList<VentaBO> getAll() throws ExceptionDAL {
        ArrayList<VentaBO> list = new ArrayList<VentaBO>();
        try {
            Statement query = DBconection.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM " + TABLA);

            while( result.next() ) {
                list.add(
                        new VentaBO(
                              result.getInt("id"),
                              ClienteDAO.get(result.getInt("id_cliente")),
                              null,
                              result.getInt("subtotal"),
                              result.getInt("iva"),
                              result.getInt("total"),
                              result.getBoolean("subtotal")
                        )
                );
            }

            result.close();
            query.close();
        }
        catch(SQLException e) {
            throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, e);
        }
        finally {
            try {
                DBconection.close();
            }
            catch(SQLException e) {
                throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, e);
            }
        }
        return list;
    }

    public static void save(VentaBO venta) throws ExceptionDAL {
        try {
            Statement query = DBconection.createStatement();
            DBconection.beginTransaction();
            query.executeUpdate(
                    " INSERT INTO " + TABLA + " (id_cliente, subtotal, iva, total)" +
                    " VALUES (" +
                        "'" + venta.getCliente().getId() + "'," +
                        "'" + venta.getSubtotal() + "'," +
                        "'" + venta.getIva() + "'," +
                        "'" + venta.getTotal() + "')", Statement.RETURN_GENERATED_KEYS);

            int id_venta = 0;
            ResultSet result = query.getGeneratedKeys();
            while( result.next() )
            {
                id_venta = result.getInt(1);
            }
            result.close();

            for(ItemVentaBO item : venta.getCarro())
            {
                query.executeUpdate(
                        " INSERT INTO " + TABLA_ITEMS_VENTA + " (id_venta, id_producto, precio_venta, cantidad, total)" +
                        " VALUES(" +
                            "'" + id_venta + "'," +
                            "'" + item.getProducto().getId() + "'," +
                            "'" + item.getPrecioVenta() + "'," +
                            "'" + item.getCantidad() + "'," +
                            "'" + item.getTotal() + "')"
                );
            }

            DBconection.endTransaction();
            query.close();

        } catch(SQLException e) {
            switch( e.getErrorCode() ) {
                case ExceptionDAL.ERROR_ACCESS_CLOSE:
                    throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, e);

                default:
                    throw new ExceptionDAL("Se ha producido un error inesperado", e);
            }
        }
        finally {
            try {
                DBconection.close();
            }
            catch(SQLException e) {
                throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, e);
            }
        }
    }

    public static void setNull(ArrayList<VentaBO> ventas) throws ExceptionDAL {
        try {
            Statement query = DBconection.createStatement();
            DBconection.beginTransaction();
            for( VentaBO item : ventas )
            {
                query.executeUpdate(
                        " UPDATE " + TABLA +
                        " SET nula = '" + (item.getNula() ? OBSOLETO : NO_OBSOLETO ) + "'" +
                        " WHERE id = '" + item.getId() + "'"
                );
            }
            DBconection.endTransaction();

        } catch(SQLException e) {
            switch( e.getErrorCode() ) {
                case ExceptionDAL.ERROR_ACCESS_CLOSE:
                    throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, e);

                default:
                    throw new ExceptionDAL("Se ha producido un error inesperado", e);
            }
        }
        finally {
            try {
                DBconection.close();
            }
            catch(SQLException e) {
                throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, e);
            }
        }
    }

    public static VentaBO get(int id) throws ExceptionDAL {
        VentaBO obj = null;
        try {
            Statement query = DBconection.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM " + TABLA + " WHERE id = '" + id + "'");

            while( result.next() )
            {
                obj = new VentaBO(
                        result.getInt("id"),
                        ClienteDAO.get( result.getInt("id_cliente") ),
                        getItems( result.getInt("id") ),
                        result.getInt("subtotal"),
                        result.getInt("iva"),
                        result.getInt("total"),
                        result.getBoolean("nula")
                );
            }

            result.close();
            query.close();
        }
        catch(SQLException e) {
            throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, e);
        }
        finally {
            try {
                DBconection.close();
            }
            catch(SQLException e) {
                throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, e);
            }
        }

        return obj;
    }

    private static ArrayList<ItemVentaBO> getItems(int id_venta) throws ExceptionDAL {
        ArrayList<ItemVentaBO> lista = new ArrayList<ItemVentaBO>();
        try {
            Statement query = DBconection.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM " + TABLA_ITEMS_VENTA + " WHERE id_venta = " + id_venta + "");

            while( result.next() )
            {
                lista.add(
                    new ItemVentaBO(
                        result.getInt("id"),
                        ProductoDAO.get( result.getInt("id_producto") ),
                        result.getInt("cantidad"),
                        result.getInt("precio_venta"),
                        result.getInt("total")
                    )
                );
            }

            result.close();
            query.close();
        }
        catch(SQLException e) {
            throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, e);
        }
        finally {
            try {
                DBconection.close();
            }
            catch(SQLException e) {
                throw new ExceptionDAL(ExceptionDAL.MSG_ERROR_ACCESS, e);
            }
        }
        return lista;
    }
}
