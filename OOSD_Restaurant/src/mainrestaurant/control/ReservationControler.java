/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainrestaurant.control;

import edu.sit.cs.db.CSDbDelegate;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import mainrestaurant.model.DBMethod;

/**
 *
 * @author ngunngun
 */
public class ReservationControler {
    int line = 1;
    private DBMethod db = new DBMethod();
    
    public void showReserver(DefaultTableModel model) throws NumberFormatException {
        if(model.getRowCount()==0){
            getAllResever(model);
        }
    }

    
    
    public void showTableReserve(DefaultTableModel model, ArrayList<HashMap> all) throws NumberFormatException {
        for (HashMap t : all) {
            String name = (String) t.get("NAME");
            String date = (String) t.get("DATE");
            String time = (String) t.get("TIME");
            int human = Integer.parseInt((String) t.get("PEOPLE"));
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
        CSDbDelegate db = new CSDbDelegate("csprog-in.sit.kmutt.ac.th", "3306", "CSC105_G2", "csc105_2014", "csc105");
        System.out.println(db.connect());
        String re = "SELECT * FROM RESTAURANT_Reservation";
        ArrayList<HashMap> all = db.queryRows(re);
        showTableReserve(model,all);
        db.disconnect();
    }
}
