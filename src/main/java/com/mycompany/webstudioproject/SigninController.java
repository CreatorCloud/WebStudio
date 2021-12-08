/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webstudioproject;

import static com.mycompany.webstudioproject.App.scene;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author ccloud
 */

public class SigninController {
    
    public static String currentUserLogin;
    
    @FXML
    private void switchToSignup() throws IOException{
        App.setRoot("signup");
    }
    
    @FXML
    private TextField login;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private Label hidden_label;
    
    @FXML
    private void signin() throws IOException{
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/webstudio?serverTimezone=Europe/Moscow&allowPublicKeyRetrieval=true&useSSL=false", "admin", "admin");
            Statement statement = conn.createStatement();           
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `users` WHERE `login` LIKE" + "'" + login.getText() + "'"); 
            if(resultSet.next()){
               if(resultSet.getString(5).equals("1")){
                    if(password.getText().equals(resultSet.getString(4))){
                        Stage stage = new Stage();
                        App.switchToAdmin(stage);                           
                    }
                    else{
                        hidden_label.setText("Неверный пароль!");
                        hidden_label.setVisible(true);                            
                    }
                }
                else{
                    if(password.getText().equals(resultSet.getString(4))){
                        Stage stage = new Stage();
                        App.switchToMain(stage);   
                        currentUserLogin = resultSet.getString(3);
                    }
                    else{
                        hidden_label.setText("Неверный пароль!");
                        hidden_label.setVisible(true);                            
                    }
                }
            }
            else{
                hidden_label.setText("Данного пользователя не существует!");
                hidden_label.setVisible(true);
            }                 
            conn.close();
        }
        catch (SQLException ex) {            
            System.out.println("SQLException: " + ex.getMessage());
        }
    }    
    
}
