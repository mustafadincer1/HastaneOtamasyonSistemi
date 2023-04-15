/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hastaneotomasyon.Model;

import hastaneotomasyon.Model.Hasta;
import hastaneotomasyon.Model.Evraklar;
import hastaneotomasyon.Helper.DbHelper;
import  hastaneotomasyon.View.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mustafa
 */
public class Tahliller extends Hasta implements Evraklar {
    private int hasta_id;
    
    private String tahlil;

    @Override
    public void Bilgi() {
        try {
            ArrayList<Tahliller> tahliller = getTahlil();
            for(Tahliller tahlil : tahliller){
            
                System.out.println(tahlil.getName());
                System.out.println(tahlil.getTahlilName());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Tahliller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static ArrayList <Tahliller> getTahlil() throws SQLException{
       Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        ArrayList <Tahliller> tahlil = null;
        
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "SELECT users.id,users.name,users.surname,users.tc,users.password,tahliller.tahlil_adi FROM tahliller INNER JOIN users WHERE tahliller.hasta_id = users.id;";
            result = state.executeQuery(sql);
            tahlil = new ArrayList <Tahliller>();
            while(result.next()){ 
                tahlil.add(new Tahliller(result.getInt("id"),result.getString("name"),result.getString("surname"),
                        result.getString("tc"),result.getString("password"),result.getString("tahlil_adi")));
                
                
            }
            return tahlil;
          
            
        }catch(SQLException e){
                  System.out.println(e.getMessage());
            
        }
       
       
       return null;
   }

    public Tahliller(int id, String name, String surname, String tc, String password,String tahlil ) {
        super(id, name, surname, tc, password);
        this.tahlil = tahlil;
    }
          



    public int getHasta_id() {
        return hasta_id;
    }

    public void setHasta_id(int hasta_id) {
        this.hasta_id = hasta_id;
    }

    public String getTahlilName() {
        return tahlil;
    }

    public void setTahlil(String tahlil) {
        this.tahlil = tahlil;
    }
    
}
