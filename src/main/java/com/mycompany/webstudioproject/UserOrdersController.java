/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webstudioproject;

import static com.mycompany.webstudioproject.App.dbHost;
import static com.mycompany.webstudioproject.App.dbLogin;
import static com.mycompany.webstudioproject.App.dbName;
import static com.mycompany.webstudioproject.App.dbPassword;
import static com.mycompany.webstudioproject.SigninController.currentUserLogin;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author ccloud
 */
public class UserOrdersController {
    
    @FXML
    private TableView table;
    
    @FXML
    private TableColumn<Order, String> name, date, status, cont;
    
    @FXML
    private TableColumn<Order, Integer> id, price;
    
    @FXML
    private void switchToMain() throws IOException{
        App.setRoot("main");
    }
    
    @FXML
    private void switchToCreateOrder() throws IOException{
        App.setRoot("order_main");
    }    
    
    @FXML
    private void logout() throws IOException{
        Stage stage = new Stage();
        App.backToLogin(stage);
    }
    
    @FXML
    private void initialize() throws SQLException{
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHost +"/" + dbName, dbLogin, dbPassword);
            Statement statement = conn.createStatement(); 
            ResultSet orders = statement.executeQuery("SELECT * FROM `orders` WHERE `user` LIKE" + "'" + currentUserLogin + "'");
            ObservableList<Order> order_list = FXCollections.observableArrayList();
            
            while (orders.next()){
                String str_date = orders.getString(3) + " = " + orders.getString(4);
                String str_date1 = str_date.replace('-', '.');
                String str_date2 = str_date1.replace('=', '-');
                Order e = new Order(orders.getInt(1), orders.getString(2), str_date2, orders.getInt(5), orders.getString(6), orders.getString(7), "");
                order_list.add(e);
            }
            table.setItems(order_list);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            status.setCellValueFactory(new PropertyValueFactory<>("status"));
            cont.setCellValueFactory(new PropertyValueFactory<>("cont"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
        }       
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
    
    @FXML
    private void dropOrderFromDatabase() throws SQLException, IOException{
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHost +"/" + dbName, dbLogin, dbPassword);
            Statement statement = conn.createStatement();         
            Order selectedOrder = (Order)table.getSelectionModel().getSelectedItem();
            statement.executeUpdate("DELETE FROM `orders` WHERE `orders`.`id` = " + "'" + selectedOrder.getId() + "'");     
            App.setRoot("user_orders");
        }
        catch (SQLException ex){
                System.out.println("SQLException: " + ex.getMessage());
        }
        
    }
    
}
