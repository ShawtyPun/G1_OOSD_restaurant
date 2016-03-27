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

/**
 *
 * @author ngunngun
 */
public class OrderController {
    public int line = 1;
    private DBMethod db = new DBMethod();
    
    public void showPopularMenu() throws HeadlessException {
        JOptionPane.showMessageDialog(null, db.showPopularMenu());
    }

    public void getPopularMenu() {
        db.getPopularMenu(db, null, null);
    }    


    
    
}
