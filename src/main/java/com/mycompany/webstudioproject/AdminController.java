/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webstudioproject;

import static com.mycompany.webstudioproject.App.dbHost;
import static com.mycompany.webstudioproject.App.dbLogin;
import static com.mycompany.webstudioproject.App.dbName;
import static com.mycompany.webstudioproject.App.dbPassword;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author ccloud
 */
public class AdminController {
    
    public static int idFromSelected;
    
    @FXML
    private TableView table;
    
    @FXML
    private TableColumn<Order, String> name, date, status, cont, user;
    
    @FXML
    private TableColumn<Order, Integer> id, price;
    
    @FXML
    private ChoiceBox name_capt, status_capt;
    
    @FXML
    private DatePicker first_date_capt, last_date_capt;
    
    @FXML
    private TextField price_capt;
    
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
            ResultSet orders = statement.executeQuery("SELECT * FROM `orders`");
            ObservableList<Order> order_list = FXCollections.observableArrayList();
            
            while (orders.next()){
                String str_date = orders.getString(3) + " = " + orders.getString(4);
                String str_date1 = str_date.replace('-', '.');
                String str_date2 = str_date1.replace('=', '-');
                Order e = new Order(orders.getInt(1), orders.getString(2), str_date2, orders.getInt(5), orders.getString(6), orders.getString(7), orders.getString(8));
                order_list.add(e);
            }
            table.setItems(order_list);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            status.setCellValueFactory(new PropertyValueFactory<>("status"));
            cont.setCellValueFactory(new PropertyValueFactory<>("cont"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            user.setCellValueFactory(new PropertyValueFactory<>("user"));
        }       
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
    
    @FXML
    private void setLabelsText() throws SQLException{
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHost +"/" + dbName, dbLogin, dbPassword);
            Statement statement = conn.createStatement();
            Order selectedOrder = (Order)table.getSelectionModel().getSelectedItem();
            idFromSelected = selectedOrder.getId();
            ResultSet nameSet = statement.executeQuery("SELECT `name` FROM `price`");
            ObservableList<String> names = FXCollections.observableArrayList();
            while(nameSet.next()){
                names.add(nameSet.getString(1));
            }
            name_capt.setItems(names); 
            name_capt.setValue(selectedOrder.getName());
            char[] first = new char[10];
            char[] second = new char[10];
            selectedOrder.getDate().getChars(0, 10, first, 0);
            selectedOrder.getDate().getChars(13, 23, second, 0);
            String first_str = new String(first);
            String second_str = new String(second);
            LocalDate localdate1 = LocalDate.parse(first_str.replace('.', '-'));
            LocalDate localdate2 = LocalDate.parse(second_str.replace('.', '-'));
            first_date_capt.setValue(localdate1);
            last_date_capt.setValue(localdate2);
            String price_str = "" + selectedOrder.getPrice();
            price_capt.setText(price_str);
            ObservableList<String> statuses = FXCollections.observableArrayList("Ожидает рассмотрения", "В процессе", "Завершен");
            status_capt.setItems(statuses); 
            status_capt.setValue(selectedOrder.getStatus());
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
    
    @FXML
    private void updateOrder() throws SQLException, IOException{
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHost +"/" + dbName, dbLogin, dbPassword);
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE `orders` SET `name` = " + "'" + name_capt.getValue() + "'," + "`first_date` = " + "'" + first_date_capt.getValue() + "'," + "`last_date` = " + "'" + last_date_capt.getValue() + "'," + "`price` = " + "'" + price_capt.getText() + "'," + "`status` = " + "'" + status_capt.getValue() + "'" + " WHERE `orders`.`id` = " + "'" + idFromSelected + "';");
            App.setRoot("admin");
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
    
    @FXML
    private void deleteOrder() throws SQLException, IOException{
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/webstudio?serverTimezone=Europe/Moscow&allowPublicKeyRetrieval=true&useSSL=false", "admin", "admin");
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM `orders` WHERE `orders`.`id` = " + "'" + idFromSelected + "';");
            App.setRoot("admin");
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
    
}
