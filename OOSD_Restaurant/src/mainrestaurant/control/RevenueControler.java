/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainrestaurant.control;

import edu.sit.cs.db.CSDbDelegate;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import mainrestaurant.model.DBMethod;

/**
 *
 * @author ngunngun
 */
public class RevenueControler {
    int money = 0;
    int line = 1;
    private DBMethod db = new DBMethod();
    
    public void showDefaultTotal(DefaultTableModel model, JLabel lbTotal) {
        while(model.getRowCount() > 0) {
            model.removeRow(0);
            money = 0;
            line = 1;
            lbTotal.setText("...........");
        }
    }
    
    public ArrayList<HashMap> getAllBill() {
        return db.getAllBill();
    }
    
    public void showRevenueList(DefaultTableModel model, JLabel lbTotal, ArrayList<HashMap> total) throws NumberFormatException {
        for (HashMap t : total) {
            String getDT = (String) t.get("DateTime");
            int income = Integer.parseInt((String) t.get("TOTAL"));
            model.addRow(new Object[0]);
            model.setValueAt(line, line - 1, 0);
            model.setValueAt(getDT, line - 1, 1);
            model.setValueAt(income + ".-", line - 1, 2);
            line++;
            
            money = money + income;
            lbTotal.setText("" + money);
            
        }
    }
    
    
    public void setNewRevenue(DefaultTableModel model, JLabel lbTotal) {
        if (model.getRowCount() > 0) {
            db.clearRevenue();
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            lbTotal.setText("...........");
        }
    }


    
}
