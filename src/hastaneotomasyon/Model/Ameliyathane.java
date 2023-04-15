/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hastaneotomasyon.Model;

import hastaneotomasyon.Helper.DbHelper;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Mustafa
 */
public class Ameliyathane {
    private int id;
    private String ad;

    public Ameliyathane(int id, String ad) {
        this.id = id;
        this.ad = ad;
    }

    public Ameliyathane() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }
    
     public static ArrayList<Ameliyathane> getAmeliyathane() throws SQLException{
        
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        ArrayList <Ameliyathane> ameliyathane = null;
        
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "SELECT id,name FROM `ameliyathane`;";
            result = state.executeQuery(sql);
            ameliyathane = new ArrayList <Ameliyathane>();
            while(result.next()){ 
               
                ameliyathane.add(new Ameliyathane(result.getInt("id"),result.getString("name")));
                
                
            }
            return ameliyathane;
          
            
        }catch(SQLException e){
                  System.out.println(e.getMessage());
            
        }
       
       
       return null;
    }
    
}
