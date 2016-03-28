/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainrestaurant.model;

import edu.sit.cs.db.CSDbDelegate;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ngunngun
 */
public class DBMethod extends CSDbDelegate{
    private CSDbDelegate dbb;
    
    public DBMethod() {
        dbb = new CSDbDelegate("csprog-in.sit.kmutt.ac.th", "3306", "CSC105_G2", "csc105_2014", "csc105");
    }
    
    public void dbConnect() {
        System.out.println(dbb.connect());
    }
    
    public void dbDisConnect() {
        dbb.disconnect();
    }
    
    public void dbExecuteQuery(String sql){
        dbb.executeQuery(sql);
    }

    
    //REVENUE
    
    public ArrayList<HashMap> getAllBill() {
        dbConnect();
        String bill = "SELECT * FROM RESTAURANT_Income";
        ArrayList<HashMap> total = dbb.queryRows(bill);
        dbDisConnect();
        return total;
    }
        
    public void clearRevenue() {
        dbConnect();
        String Clear = "DELETE FROM `RESTAURANT_Income` WHERE 1";
        dbExecuteQuery(Clear);
        String ai = "ALTER TABLE RESTAURANT_Income AUTO_INCREMENT = 1";
        dbExecuteQuery(ai);
        dbDisConnect();
    }
    
    
    //RESERVATION
    public ArrayList<HashMap> getAllReserver() {
        dbConnect();
        String re = "SELECT * FROM RESTAURANT_Reservation";
        ArrayList<HashMap> all = dbb.queryRows(re);
        dbDisConnect();
        return all;
    }
    
    public void addReserverDB(JTextField tfName, JTextField tfDate, JTextField tfTime, JTextField tfTable) {
        dbConnect();
        String Customer = "INSERT INTO RESTAURANT_Reservation(NAME, DATE, TIME,PEOPLE)"
                + "VALUE(" + "'" + tfName.getText() + "'" + "," + "'" + tfDate.getText()
                + "'" + "," + "'" + tfTime.getText() + "'" + "," + "'" + tfTable.getText()
                + "'" + ")";
        dbExecuteQuery(Customer);
        dbDisConnect();
    }
    
    public void RemoveReserve() {
        dbConnect();
        String Clear = "DELETE FROM `RESTAURANT_Reservation` WHERE 1";
        dbExecuteQuery(Clear);
        String ai = "ALTER TABLE RESTAURANT_Reservation AUTO_INCREMENT = 1";
        dbExecuteQuery(ai);
        dbDisConnect();
    }
    
    //ORDER
    public String showPopularMenu() throws HeadlessException {
        dbConnect();
        String num = "";
        String pop = "SELECT `ORDER`,SUM(`AMOUNT`) FROM `RESTAURANT_mostPopular` WHERE 1 GROUP BY `ORDER` ORDER BY SUM(`AMOUNT`) ASC";
        num = getPopularMenu(pop, num);
        dbDisConnect();
        return num;
    }
    
    public String getPopularMenu(String pop, String num) {
        dbConnect();
        ArrayList<HashMap> mostP = dbb.queryRows(pop);
        for (HashMap m : mostP) {
            num = (String) m.get("ORDER");
        }
        dbDisConnect();
        return num;
    }
    
     public void doUndoDB(JComboBox cbbTable, int line) {
        dbConnect();
        String keep = "DELETE FROM `RESTAURANT_Keeper` WHERE NO = " + (line);
        String use = "UPDATE `RESTAURANT_Tables` SET `isUsing`= 0 WHERE TABLENUM = " + cbbTable.getSelectedItem().toString();
        dbExecuteQuery(keep);
        dbExecuteQuery(use);
        dbDisConnect();
    }
     
    public ArrayList<HashMap> getUseTableDB(JComboBox cbbTable) {
        dbConnect();
        String check = "SELECT isUsing FROM RESTAURANT_Tables WHERE TABLENUM = " + cbbTable.getSelectedItem().toString();
        ArrayList<HashMap> total = dbb.queryRows(check);
        dbDisConnect();
        return total;
    }
    
    public void setUseTable(JComboBox cbbTable) {
        dbConnect();
        String use = "UPDATE `RESTAURANT_Tables` SET `isUsing`= 1 WHERE TABLENUM = " + cbbTable.getSelectedItem().toString();
        dbExecuteQuery(use);
        dbDisConnect();
    }
    
    public void insertOrder(int line, String menu, int amount, int tableNo) {
        dbConnect();
        String keep = "INSERT INTO `RESTAURANT_Keeper`(`NO`, `ORDER`, `AMOUNT`, `TABLENUM`) VALUES "
                + " ( " + line + " ,' " + menu + " ', " + amount + " , " + tableNo + ");";
        dbExecuteQuery(keep);
        String pop = "INSERT INTO `RESTAURANT_mostPopular`(`ORDER`, `AMOUNT`) VALUES "
                + "('" + menu + " ',' " + amount + "');";
        dbExecuteQuery(pop);
        dbDisConnect();
    }
    
    //BILLING
    public void checkTableDB(JComboBox cbbTable, int total) {
        dbConnect();
        String use = "UPDATE `RESTAURANT_Tables` SET `isUsing`= 0 WHERE TABLENUM = " + cbbTable.getSelectedItem().toString();
        dbExecuteQuery(use);
        String add = "INSERT INTO RESTAURANT_Income(TOTAL) VALUES (" + total + ")";
        dbExecuteQuery(add);
        String clear = "DELETE FROM RESTAURANT_Keeper WHERE TABLENUM =" + cbbTable.getSelectedItem().toString();
        dbExecuteQuery(clear);
        dbDisConnect();
    }
    
    public ArrayList<HashMap> getTableDB(JComboBox cbbTable){
        dbConnect();
        String table = "SELECT * FROM RESTAURANT_Keeper WHERE TABLENUM = " + cbbTable.getSelectedItem().toString();
        ArrayList<HashMap> order = dbb.queryRows(table);
        dbDisConnect();
        return order;
    }
    
    public ArrayList<HashMap> getBillOrder(String getOrder) {
        dbConnect();
        String price = "SELECT price FROM RESTAURANT_Order WHERE list = '" + getOrder.trim() + "'";
        ArrayList<HashMap> menu = dbb.queryRows(price);
        dbDisConnect();
        return menu;
    }
    
    
}
