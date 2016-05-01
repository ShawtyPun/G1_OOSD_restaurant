/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainrestaurant.model;

/**
 *
 * @author lullaby
 */
public class KeeperModel {
    private String no;
    private String order;
    private String amount;
    private String tableno;

    public KeeperModel(String no, String order, String amount, String tableno) {
        this.no = no;
        this.order = order;
        this.amount = amount;
        this.tableno = tableno;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTableno() {
        return tableno;
    }

    public void setTableno(String tableno) {
        this.tableno = tableno;
    }
    
    public KeeperModel(){
        
    }
    
}
