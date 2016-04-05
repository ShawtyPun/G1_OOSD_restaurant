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
public class RevenueModel {
    private String no;
    private String date;
    private String total;

    public RevenueModel(String no, String date, String total) {
        this.no = no;
        this.date = date;
        this.total = total;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    public RevenueModel(){
        
    }
}
