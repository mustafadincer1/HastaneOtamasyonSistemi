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
public class Alerjiler extends Hasta implements Evraklar {
    private int hasta_id;
    private String alerji;

    @Override
    public void Bilgi() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    public static ArrayList <Alerjiler> getAlerji() throws SQLException{
       Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        ArrayList <Alerjiler> alerji = null;
        
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "SELECT users.id,users.name,users.surname,users.tc,users.password,alerjiler.alerji_adi FROM alerjiler INNER JOIN users WHERE alerjiler.hasta_id = users.id;";
            result = state.executeQuery(sql);
            alerji = new ArrayList <Alerjiler>();
            while(result.next()){ 
                alerji.add(new Alerjiler(result.getInt("id"),result.getString("name"),result.getString("surname"),
                        result.getString("tc"),result.getString("password"),result.getString("alerji_adi")));
                
                
            }
            return alerji;
          
            
        }catch(SQLException e){
                  System.out.println(e.getMessage());
            
        }
       
       
       return null;
   }

    public Alerjiler(int id, String name, String surname, String tc, String password,String alerji ) {
        super(id, name, surname, tc, password);
        this.alerji = alerji;
        hasta_id =id;
    }

    public int getHasta_id() {
        return hasta_id;
    }

    public void setHasta_id(int hasta_id) {
        this.hasta_id = hasta_id;
    }

    

    public String getAlerjiName() {
        return alerji;
    }

    public void setAlerji(String alerji) {
        this.alerji = alerji;
    }
            
    
}
