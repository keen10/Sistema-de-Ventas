/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationLayer.ventaUI;

import bussinessLayer.VentaBO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jony
 */
public class ListarVentaUITableModel extends AbstractTableModel {
    private String[] columnNames;
    private Object[][] data;

    public ListarVentaUITableModel(ArrayList<VentaBO> list) {
        this.data = new Object[list.size()][];
        for( int i = 0; i < list.size(); ++i ) {
            this.data[i] = new Object[6];
            this.data[i][0] = list.get(i).getId();
            this.data[i][1] = list.get(i).getCliente().getNombre();
            this.data[i][2] = list.get(i).getSubtotal();
            this.data[i][3] = list.get(i).getIva();
            this.data[i][4] = list.get(i).getTotal();
            this.data[i][5] = list.get(i).getNula();
        }
        this.columnNames = new String[6];
        this.columnNames[0] = "ID_Venta";
        this.columnNames[1] = "Cliente";
        this.columnNames[2] = "Subtotal";
        this.columnNames[3] = "IVA";
        this.columnNames[4] = "Total";
        this.columnNames[5] = "Nula";
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
