/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinessLayer;

import java.util.ArrayList;

/**
 *
 * @author jony
 */
public class VentaBO {
    private final static int num_items = 16;
    
    private int id;
    private ClienteBO cliente; 
    private ArrayList<ItemVentaBO> carro;
    private int subtotal;
    private int iva;
    private int total;
    private boolean nula;
    
    public VentaBO() {
        carro = new ArrayList<ItemVentaBO>();
    }
    
    public VentaBO(int id, ClienteBO cliente, ArrayList<ItemVentaBO> carro, int subtotal, int iva, int total, boolean nula) {
        this.id = id;
        this.cliente = cliente;
        this.carro = carro;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.nula = nula;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) throws ExceptionBOL {
        if( id < 1 )
        {
            throw new ExceptionBOL("El id de la venta debe ser mayor a 0");
        }
        this.id = id;
    }
    
    public ClienteBO getCliente() {
        return this.cliente;
    }
    
    public void setCliente(ClienteBO cliente) {
        this.cliente = cliente;
    }
    
    public ArrayList<ItemVentaBO> getCarro() {
        return this.carro;
    }
    
    public void setCarro(ArrayList<ItemVentaBO> carro) {
        this.carro = carro;
    }
    
    public int getSubtotal() {
        return this.subtotal;
    }
    
    public int getIva() {
        return this.iva;
    }
      
    public int getTotal() {
        return this.total;
    }
    
    public boolean getNula() {
        return this.nula;
    }
    
    public void setNula(boolean nula) {
        this.nula = nula;
    }
    
    public boolean carroVacio() {
        return carro.isEmpty();
    }
    
    public boolean carroLleno() {
        return carro.size() >= num_items;
    }
    
    private boolean existeProducto(int id_producto) {
        int i;
        for(i=0;i<carro.size();++i){
            if(carro.get(i).getProducto().getId() == id_producto){
                return true;
            }
        }
        return false;
    }
    
    private ItemVentaBO getItem(int id_producto) throws ExceptionBOL {
        int i;
        if(!carroVacio()){
            for(i=0;i<carro.size();++i){
                if(carro.get(i).getProducto().getId() == id_producto){
                    ItemVentaBO item = carro.get(i);
                    return item;
                }
            }
        }
        else{
            throw new ExceptionBOL("El carro de venta no posee item, esta vacio");
        }
        return null;
    }
    
    private void actualizar() {
        int i;
        this.subtotal = 0;
        for(i=0;i<carro.size();++i){
            this.subtotal += this.carro.get(i).getTotal();
        }
        double total_iva = this.subtotal * 1.19;
        this.total = (int) total_iva;
        this.iva = this.total - this.subtotal;
    }
    
    public void agregarItem(ItemVentaBO item) throws ExceptionBOL {
        int i;
        if(!carroLleno()){
            if(!existeProducto(item.getProducto().getId())){
                carro.add(item);
            }
            else{
                getItem(item.getProducto().getId()).aumentarCantidad();
            }
            this.actualizar();
        }
    }
    
    public void removerItem(int id_producto) throws ExceptionBOL {
        int i;
        if(!carroVacio()){
            for(i=0;i<carro.size();++i){
                if(carro.get(i).getProducto().getId() == id_producto){
                    ItemVentaBO item = carro.get(i);
                    carro.remove(item);
                }
            }
            this.actualizar();
        }
    }
}