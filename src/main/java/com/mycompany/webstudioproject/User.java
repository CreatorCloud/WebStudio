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

/**
 *
 * @author ccloud
 */
public class User {
    
    public static void addUser(String fio, String date_of_birth, String login, String password, int role) throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/webstudio?serverTimezone=Europe/Moscow&allowPublicKeyRetrieval=true&useSSL=false", "admin", "admin");
        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO `users` (`fio`, `date_of_birth`, `login`, `password`, `role`) VALUES ('" + fio + "', "  + "'" + date_of_birth + "', " + "'" + login + "', "  + "'" + password + "', " + role  +");");       
    }
    
    public static void removeUser(String login) throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/webstudio?serverTimezone=Europe/Moscow&allowPublicKeyRetrieval=true&useSSL=false", "admin", "admin");
        Statement statement = conn.createStatement();
        statement.executeUpdate("DELETE FROM `users` WHERE `users`.`login` = " + "'" + login + "'");       
    }
    
    public static boolean findUser(String login) throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/webstudio?serverTimezone=Europe/Moscow&allowPublicKeyRetrieval=true&useSSL=false", "admin", "admin");
        Statement statement = conn.createStatement();
        ResultSet userData = statement.executeQuery("SELECT * FROM `users` WHERE `users`.`login` = " + "'" + login + "'");       
        if (userData.next()){
            return true;
        }
        return false;
    }
    
}
