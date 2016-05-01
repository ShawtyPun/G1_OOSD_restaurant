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
public class ReservationModel {
    private String no;
    private String name;
    private String date;
    private String time;
    private String peopleNo;

    public ReservationModel(String no, String name, String date, String time, String peopleNo) {
        this.no = no;
        this.name = name;
        this.date = date;
        this.time = time;
        this.peopleNo = peopleNo;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPeopleNo() {
        return peopleNo;
    }

    public void setPeopleNo(String peopleNo) {
        this.peopleNo = peopleNo;
    }
    
    public ReservationModel(){
        
    }
}
