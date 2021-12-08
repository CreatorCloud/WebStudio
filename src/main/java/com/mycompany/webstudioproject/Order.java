/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webstudioproject;

import static com.mycompany.webstudioproject.SigninController.currentUserLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ccloud
 */
public class Order {
    
    private SimpleStringProperty name, date, status, cont, user;
    private SimpleIntegerProperty id, price;
   
    public Order(int id, String name, String date, int price, String status, String cont, String user){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(date);
        this.status = new SimpleStringProperty(status);
        this.cont = new SimpleStringProperty(cont);
        this.price = new SimpleIntegerProperty(price);
        this.user = new SimpleStringProperty(user);
    }
    public int getId(){
        return id.get();
    }
    public void setId(int value){
        id.set(value);
    }
    public String getName(){
        return name.get();
    }
    public void setName(String value){
        name.set(value);
    }
    public String getDate(){
        return date.get();
    }
    public void setDate(String value){
        date.set(value);
    }
    public int getPrice(){
        return price.get();
    }
    public void setPrice(int value){
        price.set(value);
    }
    public String getStatus(){
        return status.get();
    }
    public void setStatus(String value){
        status.set(value);
    }
    public String getCont(){
        return cont.get();
    }
    public void setCont(String value){
        cont.set(value);
    }
    public String getUser(){
        return user.get();
    }
    public void setUser(String value){
        user.set(value);
    }
    
    
}
