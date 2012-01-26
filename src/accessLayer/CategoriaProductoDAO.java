/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package accessLayer;

import accessLayer.resource.DBconection;
import bussinessLayer.CategoriaProductoBO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jony
 */
public class CategoriaProductoDAO {

    private static String tabla = "categorias_productos";

    public static ArrayList<CategoriaProductoBO> getAll() throws ExceptionDAL {
        ArrayList<CategoriaProductoBO> list = new ArrayList<CategoriaProductoBO>();
        try {
            Statement query = DBconection.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM " + tabla);

            while( result.next() ) {
                list.add(
                        new CategoriaProductoBO(
                              result.getInt("id"),
                              result.getString("nombre")
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
}
