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
public class Hasta implements Users,Randevuİşlemleri {
    private int id;
    private String name;
     private String surname;
     private String tc;
     private String password;
     private String hastaligi;

    public Hasta(int id, String name, String surname, String tc, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.tc = tc;
        this.password = password;
    }

    public Hasta(int id, String name, String surname, String tc, String password, String hastaligi) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.tc = tc;
        this.password = password;
        this.hastaligi = hastaligi;
    }
    

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public Hasta() {
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHastaligi() {
        return hastaligi;
    }

    public void setHastaligi(String hastaligi) {
        this.hastaligi = hastaligi;
    }
    
    public static ArrayList <Hasta> getHasta() throws SQLException{
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        ArrayList <Hasta> hasta = null;
        
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "Select * FROM users WHERE type =\"hasta\"";
            result = state.executeQuery(sql);
            hasta = new ArrayList <Hasta>();
            while(result.next()){ 
                hasta.add(new Hasta(result.getInt("id"),result.getString("name"),result.getString("surname"),
                        result.getString("tc"),result.getString("password")));
                
                
            }
            return hasta;
          
            
        }catch(SQLException e){
                  System.out.println(e.getMessage());
            
        }finally{
            connect.close();
            state.close();
        }
       
       
       return null;
    
    }
    
     

    @Override
    public ArrayList<Users> getUser() throws SQLException {
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        ArrayList <Users> users = null;
        
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "Select * FROM users";
            result = state.executeQuery(sql);
            users = new ArrayList <Users>();
            while(result.next()){ 
                users.add(new Hasta(result.getInt("id"),result.getString("name"),result.getString("surname"),
                        result.getString("tc"),result.getString("password")));
                
                
            }
            return users;
          
            
        }catch(SQLException e){
                  System.out.println(e.getMessage());
            
        }finally{
            connect.close();
            state.close();
        }
       
       
       return null;
        
    }

    @Override
    public void updateUser(int id,String name, String surname, String tc, String password) throws SQLException {
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

    @Override
    public void RandevuAl(int id,int hasta_id) throws SQLException {
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        PreparedStatement preparedStatement =null;
       
        
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "UPDATE randevu SET hasta_id = ?, kontrol =1 WHERE randevu_id = ?"  ;
             preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, hasta_id);
            preparedStatement.setInt(2, id);
           
            preparedStatement.execute();
           
            


        
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }

    @Override
    public void Randevuİptal(int id) throws SQLException{
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        PreparedStatement preparedStatement =null;
        
        try{
            connect =db.getConnection();
            String sql = "UPDATE randevu SET randevu.hasta_id = null, randevu.kontrol =0 WHERE randevu_id = ?"  ;
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, id);
         
           
            preparedStatement.execute();
            
            
            


        
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    
    }
    
    
    
    
    
}
