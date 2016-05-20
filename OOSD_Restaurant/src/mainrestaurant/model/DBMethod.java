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
public class DBMethod extends CSDbDelegate implements Services {
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
    
    @Override
    public ArrayList<RevenueModel> getAllBill() {
        dbConnect();
        String bill = "SELECT * FROM RESTAURANT_Income";
        ArrayList<RevenueModel> billList = new ArrayList<RevenueModel>();
        ArrayList<HashMap> bills = dbb.queryRows(bill);
        for(HashMap t : bills){
            String no = (String)t.get("BillingId");
            String date = (String)t.get("DateTime");
            String total = (String)t.get("TOTAL");
            billList.add(new RevenueModel(no, date, total));
        }
        dbDisConnect();
        return billList;
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
    @Override
    public ArrayList<ReservationModel> getAllReserver() {
        dbConnect();
        String re = "SELECT * FROM RESTAURANT_Reservation";
        ArrayList<ReservationModel> reserver = new ArrayList<ReservationModel>();
        ArrayList<HashMap> all = dbb.queryRows(re);
        for(HashMap t : all){
            String no = (String)t.get("NUM");
            String name = (String)t.get("NAME");
            String date = (String)t.get("DATE");
            String time = (String)t.get("TIME");
            String peopleNo = (String)t.get("PEOPLE");
            reserver.add(new ReservationModel(no, name, date, time, peopleNo));
        }
        dbDisConnect();
        return reserver;
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
     
    public ArrayList<TableModel> getUseTableDB(JComboBox cbbTable) {
        dbConnect();
        String check = "SELECT isUsing FROM RESTAURANT_Tables WHERE TABLENUM = " + cbbTable.getSelectedItem().toString();
        ArrayList<TableModel> table = new ArrayList<TableModel>();
        ArrayList<HashMap> a = dbb.queryRows(check);
        for(HashMap t : a){
            String tableNo = (String)t.get("tableNum");
            String people = (String)t.get("people");
            String isUsing = (String)t.get("isUsing");
            table.add(new TableModel(tableNo, people, isUsing));
        }
        dbDisConnect();
        return table;
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
    
    public ArrayList<KeeperModel> getTableDB(JComboBox cbbTable){
        dbConnect();
        String table = "SELECT * FROM RESTAURANT_Keeper WHERE TABLENUM = " + cbbTable.getSelectedItem().toString();
        ArrayList<KeeperModel> tableList = new ArrayList<KeeperModel>();
        ArrayList<HashMap> orders = dbb.queryRows(table);
        for(HashMap t : orders){
            String no = (String)t.get("NO");
            String order = (String)t.get("ORDER");
            String amount = (String)t.get("AMOUNT");
            String tableno = (String)t.get("TABLENUM");
            tableList.add(new KeeperModel(no, order, amount, tableno));
        }
        dbDisConnect();
        return tableList;
    }
    
    public ArrayList<OrderModel> getBillOrder(String getOrder) {
        dbConnect();
        String sql = "SELECT price FROM RESTAURANT_Order WHERE list = '" + getOrder.trim() + "'";
        ArrayList<OrderModel> order = new ArrayList<OrderModel>();
        ArrayList<HashMap> menu = dbb.queryRows(sql);
        for(HashMap t : menu){
            String no = (String)t.get("num");
            String list = (String)t.get("list");
            String price = (String)t.get("price");
            order.add(new OrderModel(no, list, price));
        }
        dbDisConnect();
        return order;
    }
    
}
