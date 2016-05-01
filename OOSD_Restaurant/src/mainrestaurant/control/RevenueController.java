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
import mainrestaurant.model.RevenueModel;

/**
 *
 * @author ngunngun
 */
public class RevenueController {
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
    
    public ArrayList<RevenueModel> getAllBill() {
        return db.getAllBill();
    }
    
    public void showRevenueList(DefaultTableModel model, JLabel lbTotal, ArrayList<RevenueModel> total) throws NumberFormatException {
        for (RevenueModel t : total) {
            String getDT = (String) t.getDate();
            int income = Integer.parseInt(t.getTotal());
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
