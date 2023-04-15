/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hastaneotomasyon.Model;

import hastaneotomasyon.Helper.DbHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mustafa
 */
public class Poliklinik {
    private int id;
    private String klinikAdı;

    public Poliklinik(int id, String klinikAdı) {
        this.id = id;
        this.klinikAdı = klinikAdı;
    }

    public Poliklinik() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKlinikAdı() {
        return klinikAdı;
    }

    public void setKlinikAdı(String klinikAdı) {
        this.klinikAdı = klinikAdı;
    }
    
    public static ArrayList <Poliklinik> getPoliklinik() throws SQLException{
       Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        ArrayList <Poliklinik> poliklinik = null;
        
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "SELECT * FROM `poliklinik` ";
            result = state.executeQuery(sql);
            poliklinik = new ArrayList <Poliklinik>();
            while(result.next()){ 
                poliklinik.add(new Poliklinik(result.getInt("id"),result.getString("poliklinik_adi")));
                
                
            }
            return poliklinik;
          
            
        }catch(SQLException e){
                  System.out.println(e.getMessage());
            
        }
       
       
       return null;
   }

    
}
