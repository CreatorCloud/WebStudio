/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.webstudioproject;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ccloud
 */
public class UserTest {
    
    String fio = "qq";
    String date_of_birth = "03.04.2002";
    String login = "qwerty";
    String password = "12312";
    int role = 1;
    
    @Test
    public void testAddUser() throws Exception {       
        User.addUser(fio, date_of_birth, login, password, role);
        if (User.findUser(login)){
            return;
        }
        else{
            fail("User not added!");
        }
    }

    @Test
    public void testRemoveUser() throws Exception {
        User.removeUser(login);
        if (User.findUser(login)){
            fail("User not removed!");
        }
        else{
            return;
        }
    }
    
}
