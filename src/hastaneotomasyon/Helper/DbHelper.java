/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hastaneotomasyon.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Mustafa
 */
public class DbHelper {
    private String user_name ="root";
    private String password ="";
    private String dbUrl ="jdbc:mysql://localhost:3306/otomasyon";
    
    public Connection getConnection() throws SQLException {
        return (DriverManager.getConnection(dbUrl, user_name, password));
    }
    public void showError(SQLException e){
        System.out.println(e.getMessage());
    }
    
}
