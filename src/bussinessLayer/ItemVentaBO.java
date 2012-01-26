/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinessLayer;

/**
 *
 * @author jony
 */
public class ItemVentaBO {
    
    private int id;
    private ProductoBO producto;
    private int cantidad;
    private int precioVenta;
    private int total;
    
    public ItemVentaBO() {

    }
    
    public ItemVentaBO(int id, ProductoBO producto, int cantidad, int precioVenta, int total) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.total = total;
    }
    
    public ItemVentaBO(ProductoBO producto, int cantidad) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.precioVenta = producto.getPrecio();
        this.total = this.precioVenta * this.cantidad;
    }
    
    private void actualizar() {
        this.total = this.precioVenta * this.cantidad;
    }
    
    public void aumentarCantidad() throws ExceptionBOL {
        this.cantidad += 1;
        this.actualizar();
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) throws ExceptionBOL {
        if( id < 1 )
        {
            throw new ExceptionBOL("El id del item de venta debe ser mayor a 0");
        }
        this.id = id;
    }
    
    public ProductoBO getProducto() {
        return this.producto;
    }
    
    public void setProducto(ProductoBO producto) {
        this.producto = producto;
    }
    
    public int getCantidad() {
        return this.cantidad;
    }
    
    public int getPrecioVenta() {
        return this.precioVenta;
    }
    
    public int getTotal() {
        return this.total;
    }

}