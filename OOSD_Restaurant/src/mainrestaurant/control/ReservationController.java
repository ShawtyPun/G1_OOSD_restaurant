/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainrestaurant.control;

import edu.sit.cs.db.CSDbDelegate;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mainrestaurant.model.DBMethod;
import mainrestaurant.model.ReservationModel;

/**
 *
 * @author ngunngun
 */
public class ReservationController {
    int line = 1;
    private DBMethod db = new DBMethod();
    
    public void showReserver(DefaultTableModel model) throws NumberFormatException {
        if(model.getRowCount()==0){
            getAllResever(model);
        }
    }

    
    
    public void showTableReserve(DefaultTableModel model, ArrayList<ReservationModel> all) throws NumberFormatException {
        for (ReservationModel t : all) {
            String name = (String) t.getName();
            String date = (String) t.getDate();
            String time = (String) t.getTime();
            int human = Integer.parseInt((String) t.getPeopleNo());
            model.addRow(new Object[0]);
            model.setValueAt(line, line - 1, 0);
            model.setValueAt(name, line - 1, 1);
            model.setValueAt(date, line - 1, 2);
            model.setValueAt(time, line - 1, 3);
            model.setValueAt(human, line - 1, 4);
            line++;
        }
    }
    
    public void getAllResever(DefaultTableModel model) throws NumberFormatException {
        showTableReserve(model,db.getAllReserver());
    }
    
    public void addReserverColumn(DefaultTableModel model, JTextField tfName, JTextField tfDate, JTextField tfTime, JTextField tfTable) throws NumberFormatException {
        model.addRow(new Object[0]);
        model.setValueAt(line, line - 1, 0);
        model.setValueAt(tfName.getText(), line - 1, 1);
        model.setValueAt(tfDate.getText(), line - 1, 2);
        model.setValueAt(tfTime.getText(), line - 1, 3);
        model.setValueAt(Integer.parseInt(tfTable.getText()), line - 1, 4);
        line++;
    }
    
    public void addReserverDB(JTextField tfName, JTextField tfDate, JTextField tfTime, JTextField tfTable) {
        db.addReserverDB(tfName, tfDate, tfTime, tfTable);
    }
    
    public void RemoveReserve() {
        db.RemoveReserve();
    }
}
