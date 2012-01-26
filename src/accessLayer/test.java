/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package accessLayer;

import bussinessLayer.CategoriaProductoBO;
import bussinessLayer.ClienteBO;
import bussinessLayer.ExceptionBOL;
import bussinessLayer.ItemVentaBO;
import bussinessLayer.ProductoBO;
import bussinessLayer.VentaBO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author java
 */
public class test {
    public static void main(String[] args) throws SQLException{
        try{
            /*
            ClienteBO cliente = ClienteDAO.get(4);

            ArrayList<ClienteBO> lista = new ArrayList<ClienteBO>();

            lista.add(ClienteDAO.get(4));
            lista.add(ClienteDAO.get(1));
            lista.add(ClienteDAO.get(2));

            ClienteBO cliente2 = new ClienteBO();

            cliente2.setNombre("leonardo");
            cliente2.setRut("12345678-9");
            cliente2.setDireccion("Calle 1 # 69");
            cliente2.setCorreo("lgaticastyle@gmail.com");

            ClienteDAO.save(cliente2);

             
            ClienteBO cliente3 = new ClienteBO(9, "Leonardo Gatica", "12.345.678-9", "Calle 1 # 69", "lgaticastyle@gmail.com");

            ClienteDAO.update(cliente3);
            
            ProductoBO producto = ProductoDAO.get(4);

            CategoriaProductoBO categoria = new CategoriaProductoBO(4, "Impresoras");
            ProductoBO producto = new ProductoBO(22, "impresora nuevas", 1000, 50, categoria);

            ProductoDAO.update(producto);
             * 
             */


            VentaBO venta = new VentaBO();

            ProductoBO producto1 = ProductoDAO.get(4);

            ItemVentaBO item1 = new ItemVentaBO(producto1, 1);
            ItemVentaBO item2 = new ItemVentaBO(producto1, 1);

            venta.agregarItem(item1);
            venta.agregarItem(item2);

             
            

            int b = 0;
        }
        catch(ExceptionDAL ex){
            System.out.println(ex.getMessage());
        }
        catch(ExceptionBOL ex){
            System.out.println(ex.getMessage());
        }
    }
}
