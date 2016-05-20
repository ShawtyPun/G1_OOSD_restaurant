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
public class TableModel {
    private String tableNo;
    private String people;
    private String isUsing;

    public TableModel(String tableNo, String people, String isUsing) {
        this.tableNo = tableNo;
        this.people = people;
        this.isUsing = isUsing;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(String isUsing) {
        this.isUsing = isUsing;
    }
            
    public TableModel(){
        
    }
}
