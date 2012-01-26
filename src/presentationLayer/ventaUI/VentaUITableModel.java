/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationLayer.ventaUI;

import bussinessLayer.ItemVentaBO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jony
 */
public class VentaUITableModel extends AbstractTableModel {
    private String[] columnNames;
    private Object[][] data;

    public VentaUITableModel(ArrayList<ItemVentaBO> list) {
        this.data = new Object[list.size()][];
        for( int i = 0; i < list.size(); ++i ) {
            this.data[i] = new Object[5];
            this.data[i][0] = list.get(i).getProducto().getId();
            this.data[i][1] = list.get(i).getProducto().getNombre();
            this.data[i][2] = list.get(i).getPrecioVenta();
            this.data[i][3] = list.get(i).getCantidad();
            this.data[i][4] = list.get(i).getTotal();
        }
        this.columnNames = new String[5];
        this.columnNames[0] = "ID_Producto";
        this.columnNames[1] = "Producto";
        this.columnNames[2] = "Precio";
        this.columnNames[3] = "Q";
        this.columnNames[4] = "Total";
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
