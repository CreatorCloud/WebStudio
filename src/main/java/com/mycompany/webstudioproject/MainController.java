/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webstudioproject;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 *
 * @author ccloud
 */
public class MainController {
       
    @FXML
    private void switchToCreateOrder() throws IOException{
        App.setRoot("order_main");
    }
    
    @FXML
    private void switchToOrders() throws IOException{
        App.setRoot("user_orders");
    }
    
    @FXML
    private void logout() throws IOException{
        Stage stage = new Stage();
        App.backToLogin(stage);
    }
    
}
