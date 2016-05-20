/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainrestaurant.model;

/**
 *
 * @author ngunngun
 */
public class MostPopularModel {
    private String no;
    private String order;
    private String amount;
    
     public MostPopularModel(String no, String order, String amount) {
        this.no = no;
        this.order = order;
        this.amount = amount;
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
        
    public MostPopularModel(){
        
    }
    
}
