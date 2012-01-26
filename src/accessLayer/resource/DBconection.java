/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package accessLayer.resource;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author java
 */
public class DBconection {
    private final static String database = "ventas";
    private final static String user = "root";
    private final static String password = "";
    private final static String host = "localhost";

    private static Connection link;
    private static int num_statement = 0;

    private static Connection getConnection() throws SQLException{
        if (link == null || link.isClosed()){
            link = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+database,user,password);
        }
        return link;
    }
    public static Statement createStatement() throws SQLException {
        ++num_statement;
        return getConnection().createStatement();
    }
    public static void close() throws SQLException {
        if (num_statement == 1){
            getConnection().close();
        }
        ++num_statement;
    }
    public static void beginTransaction() throws SQLException {
        getConnection().setAutoCommit(false);
    }
    public static void endTransaction() throws SQLException {
        getConnection().commit();
        getConnection().setAutoCommit(true);
    }
}
