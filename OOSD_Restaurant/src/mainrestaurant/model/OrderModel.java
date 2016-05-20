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
public class OrderModel {
    private String no;
    private String list;
    private String price;

    public OrderModel(String no, String list, String price) {
        this.no = no;
        this.list = list;
        this.price = price;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    public OrderModel(){
        
    }
}
