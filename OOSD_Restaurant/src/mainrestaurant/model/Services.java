/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainrestaurant.model;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author ngunngun
 */
public interface Services {
    
    ArrayList<RevenueModel> getAllBill();
    void clearRevenue();
    
    ArrayList<ReservationModel> getAllReserver();
    void addReserverDB (JTextField tfName, JTextField tfDate, JTextField tfTime, JTextField tfTable);
    void RemoveReserve();
    
    String showPopularMenu();
    String getPopularMenu(String pop, String num);
    void doUndoDB(JComboBox cbbTable, int line);
    ArrayList<TableModel> getUseTableDB(JComboBox cbbTable);
    void setUseTable(JComboBox cbbTable);
    void insertOrder(int line, String menu, int amount, int tableNo);
    
    void checkTableDB(JComboBox cbbTable, int total);
    ArrayList<KeeperModel> getTableDB(JComboBox cbbTable);
    ArrayList<OrderModel> getBillOrder(String getOrder);
}
