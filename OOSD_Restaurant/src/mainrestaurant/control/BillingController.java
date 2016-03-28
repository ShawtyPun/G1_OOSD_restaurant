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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mainrestaurant.model.DBMethod;

/**
 *
 * @author ngunngun
 */
public class BillingController {
    
    private DBMethod db = new DBMethod();
    int line;
    int total;
    
    public void checkTable(DefaultTableModel model, JLabel lbTotal, JComboBox cbbTable) throws HeadlessException {
        if (model.getRowCount() > 0) {
            int answer = JOptionPane.showConfirmDialog(null, "Do you really want to check bill?", "WARNING!!!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE);
            if (answer == JOptionPane.YES_OPTION) {
                db.checkTableDB(cbbTable, total);
                
                while(model.getRowCount()>0){
                    model.removeRow(0);
                    line=0;
                    total = 0;
                    lbTotal.setText("" + total);
                }
            } else if (answer == JOptionPane.NO_OPTION) {
                // User clicked NO.
            }
        }
    }
    
    public void checkBill(JComboBox cbbTable, DefaultTableModel model, JLabel lbTotal) throws NumberFormatException {
        
        while(model.getRowCount()>0){
            model.removeRow(0);
            line=0;
        }
        
        ArrayList<HashMap> order = db.getTableDB(cbbTable);
        int orderPrice = 0;
        int amount = 0;
        int sum = 0;
        
        for (HashMap p : order) {
            String getOrder = (String) p.get("ORDER");
            ArrayList<HashMap> menu = db.getBillOrder(getOrder);
            for (HashMap t : menu) {
                orderPrice = Integer.valueOf((String) t.get("price"));
            }
            
            amount = (Integer.parseInt((String) p.get("AMOUNT")));
            sum = sum + (orderPrice * amount);
            model.addRow(new Object[0]);
            model.setValueAt(line + 1, line, 0);
            model.setValueAt((String) p.get("ORDER"), line, 1);
            model.setValueAt((String) p.get("AMOUNT"), line, 2);
            model.setValueAt(orderPrice, line, 3);
            model.setValueAt(orderPrice * amount, line, 4);
            line++;
        }
        total = sum;
        lbTotal.setText("" + total);
    }

    

    
    
}
