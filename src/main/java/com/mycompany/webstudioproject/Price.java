/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webstudioproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.*;

/**
 *
 * @author ccloud
 */
public class Price {
    
    private SimpleStringProperty name;
    private SimpleIntegerProperty price;
    
    public Price(String name, int price){
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
    }
    
    public String getName(){
        return name.get();
    }
    public void setName(String value){
        name.set(value);
    }
    
    public int getPrice(){
        return price.get();
    }
    public void setPrice(int value){
        price.set(value);
    }
    
}
