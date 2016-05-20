/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainrestaurant.control;

import edu.sit.cs.db.CSDbDelegate;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import mainrestaurant.model.DBMethod;
import mainrestaurant.model.TableModel;

/**
 *
 * @author ngunngun
 */
public class OrderController {
    //int line = 1;
    private DBMethod db = new DBMethod();
    
    public void showPopularMenu() throws HeadlessException {
        JOptionPane.showMessageDialog(null, db.showPopularMenu());
    }

    public void getPopularMenu() {
        db.getPopularMenu(null, null);
    }    

    public int doUndo(DefaultTableModel model, JComboBox cbbMenu, JComboBox cbbTable, JSpinner spnAmount, int line) throws NumberFormatException {
        if (line - 1 > 0) {
            model.removeRow(line - 2);
            line--;
            String menu = cbbMenu.getSelectedItem().toString();
            int amount = Integer.parseInt(spnAmount.getValue().toString());
            int tableNo = Integer.parseInt(cbbTable.getSelectedItem().toString());
            db.doUndoDB(cbbTable, line);
        }
        return line;
    }
    
    public ArrayList<TableModel> getUseTable(JComboBox cbbTable) {
        return db.getUseTableDB(cbbTable);
    }

    public int orderMenu(int u, int i, int line, JComboBox cbbMenu, DefaultTableModel model, JComboBox cbbTable, JSpinner spnAmount) throws HeadlessException, NumberFormatException {
        if (u == 1 && i == -1) {
            JOptionPane.showMessageDialog(null, "This table is using");
        } else {
            model.addRow(new Object[0]);
            model.setValueAt(line, line - 1, 0);
            model.setValueAt(cbbMenu.getSelectedItem().toString(), line - 1, 1);
            model.setValueAt(spnAmount.getValue(), line - 1, 2);
            model.setValueAt(cbbTable.getSelectedItem().toString(), line - 1, 3);
            
            db.setUseTable(cbbTable);
            String menu = cbbMenu.getSelectedItem().toString();
            int amount = Integer.parseInt(spnAmount.getValue().toString());
            int tableNo = Integer.parseInt(cbbTable.getSelectedItem().toString());
            db.insertOrder(line, menu, amount, tableNo);
            line++; //for add new row for data
        }
        return line;
    }

    

    
}
