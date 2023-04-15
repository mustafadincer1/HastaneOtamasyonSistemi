/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hastaneotomasyon.Model;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mustafa
 */
public interface Users {
    public  ArrayList <Users> getUser() throws SQLException;
    public void updateUser(int id,String name, String surname, String tc, String password) throws SQLException;
    
}
