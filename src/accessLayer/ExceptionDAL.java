/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package accessLayer;

/**
 *
 * @author java
 */
public class ExceptionDAL extends Exception {
    public final static int ERROR_ACCESS_CLOSE = 1049;
    public final static int ERROR_UNIQUE_KEY = 1062;

    public final static String MSG_ERROR_ACCESS = "Se ha producido un error al ingresar a la base de datos";

    public ExceptionDAL(String mensaje, Throwable cause){
        super(mensaje, cause);
    }
}
