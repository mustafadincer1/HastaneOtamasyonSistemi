/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hastaneotomasyon.Model;

import hastaneotomasyon.Helper.DbHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mustafa
 */
public class Doktor extends SaglıkPersonalleri {
    private String görev;

    @Override
    public ArrayList<Users> getUser() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    public Doktor(int id, String name, String surname, String tc, String password,String görev ) {
        super(id, name, surname, tc, password);
        this.görev = görev;
    }
    

    @Override
    public void updateUser(int id, String name, String surname, String tc, String password) throws SQLException {
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        PreparedStatement preparedStatement =null;
       
        
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "UPDATE users SET name = ?, surname = ?,tc = ?, password = ? WHERE id = ?"  ;
             preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, tc);
            preparedStatement.setString(4, password);
            preparedStatement.setInt(5, id);
            preparedStatement.execute();
           
            


        
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }

    

    public Doktor() {
    }
    
    
    

    @Override
    public void setGörev(String Görev) {
       görev = Görev;
    }

    @Override
    public String getGörev() {
       return görev;
    }
    public static ArrayList <Doktor> getDoktor() throws SQLException{
       Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        ArrayList <Doktor> doktor = null;
        
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "SELECT users.id, users.name, users.surname,users.password ,users.tc,doktor_detail.alan FROM users INNER JOIN doktor_detail ON users.id = doktor_detail.doktor_id;";
            result = state.executeQuery(sql);
            doktor = new ArrayList <Doktor>();
            while(result.next()){ 
                doktor.add(new Doktor(result.getInt("id"),result.getString("name"),result.getString("surname"),
                        result.getString("tc"),result.getString("password"),result.getString("alan")));
                
                
            }
            return doktor;
          
            
        }catch(SQLException e){
                  System.out.println(e.getMessage());
            
        }
       
       
       return null;
   }
    
    
}
