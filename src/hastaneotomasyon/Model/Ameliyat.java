/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hastaneotomasyon.Model;

import hastaneotomasyon.Helper.DbHelper;
import hastaneotomasyon.View.*;
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
public class Ameliyat extends Doktorİşlemleri {
    private Hasta hasta;
    private Ameliyathane ameliyathane;

    public Ameliyat(Doktor doktor, String tarih, String saat,Hasta hasta, Ameliyathane ameliyathane) {
        super(doktor, tarih, saat);
        this.hasta = hasta;
        this.ameliyathane = ameliyathane;
    }

    public Ameliyat() {
       
    }

    public Ameliyat(Hasta hasta, Ameliyathane ameliyathane, String tarih, String saat) {
        super(tarih, saat);
        this.hasta = hasta;
        this.ameliyathane = ameliyathane;
    }

    

   

    public Hasta getHasta() {
        return hasta;
    }

    public void setHasta(Hasta hasta) {
        this.hasta = hasta;
    }

    public Ameliyathane getAmeliyathane() {
        return ameliyathane;
    }

    public void setAmeliyathane(Ameliyathane ameliyathane) {
        this.ameliyathane = ameliyathane;
        
    }
    
    public static ArrayList<Ameliyat> getAmeliyat() throws SQLException{
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result = null;
        ResultSet result2 = null;
        PreparedStatement preparedStatement =null;
        Doktor doktor = new Doktor();
        Randevu randevu;
        Ameliyathane ameliyathane = new Ameliyathane();
        Hasta hasta = new Hasta();
        ArrayList<Ameliyat> ameliyat = new ArrayList<Ameliyat>();
        ArrayList <Hasta> hastalar=new ArrayList<Hasta>();
        ArrayList<String> tarihler=new ArrayList<String>();
        ArrayList<String> saatler=new ArrayList<String>();
        ArrayList<Ameliyathane> ameliyathaneler = new ArrayList<Ameliyathane>();
        ArrayList <Doktor> doktorlar =new ArrayList<Doktor>();
        try{
            connect =db.getConnection();
            state = connect.createStatement();
      
            String sql2= "SELECT users.id,users.name,users.surname,users.password,users.tc,ameliyat.ameliyathane_id,ameliyathane.name,ameliyat.tarih,ameliyat.saat "
                    + "FROM ameliyat INNER JOIN users,ameliyathane WHERE users.id = ameliyat.hasta_id AND ameliyat.ameliyathane_id = ameliyathane.id;";
            result = state.executeQuery(sql2);
            while(result.next()){

               hastalar.add(new Hasta(result.getInt("id"),result.getString("name"),result.getString("surname"),
                        result.getString("tc"),result.getString("password")));
               ameliyathaneler.add(new Ameliyathane(result.getInt("ameliyat.ameliyathane_id"),result.getString("ameliyathane.name")));
               tarihler.add(result.getString("ameliyat.tarih"));
               saatler.add(result.getString("ameliyat.saat"));
               
               
                 
            
            }
            String sql3= "SELECT users.id,users.name,users.surname,users.password,users.tc,doktor_detail.alan FROM ameliyat INNER JOIN users,doktor_detail "
                    + "WHERE users.id = ameliyat.doktor_id AND doktor_detail.doktor_id = ameliyat.doktor_id;";
            result2 = state.executeQuery(sql3);
            
            while(result2.next()){
             
            
                doktorlar.add(new Doktor(result2.getInt("id"),result2.getString("name"),result2.getString("surname"),
                        result2.getString("tc"),result2.getString("password"),result2.getString("alan")));
            }
            
            for(int i = 0; i< doktorlar.size();++i){
                ameliyat.add(new Ameliyat(doktorlar.get(i),tarihler.get(i),saatler.get(i),hastalar.get(i),ameliyathaneler.get(i)));
            
            }
            return ameliyat;

        
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            result.close();

            
        }
        
        return null;
        
    
    }

    @Override
    public void çalış(String doktor_tc,String hasta_tc,String tarih, String saat,int a) throws SQLException {
         Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result = null;
        PreparedStatement preparedStatement =null;
        Doktor doktor = new Doktor();
        Randevu randevu;
        Ameliyathane ameliyathane = new Ameliyathane();
        Hasta hasta = new Hasta();
        
        
 
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "INSERT INTO ameliyat (doktor_id,hasta_id,ameliyathane_id,tarih,saat) VALUES" + "(?,?,?,?,?)";
            
            String sql2= "SELECT users.id,users.name,users.surname,users.password,users.tc,doktor_detail.alan  FROM users INNER JOIN doktor_detail WHERE users.id= doktor_detail.doktor_id AND users.tc ="+ doktor_tc;
            result = state.executeQuery(sql2);
            while(result.next()){
                
                doktor =new Doktor(result.getInt("id"),result.getString("name"),result.getString("surname"),
                        result.getString("tc"),result.getString("password"),result.getString("alan"));
                 
            
            }
            
      
            
            String sql3 = "SELECT users.id,users.name,users.surname,users.password,users.tc FROM users INNER JOIN hasta_detail WHERE users.id= hasta_detail.kişi_id AND users.tc ="+hasta_tc;

            result = state.executeQuery(sql3);
            while(result.next()){
                hasta = new Hasta(result.getInt("id"),result.getString("name"),result.getString("surname"),
                        result.getString("tc"),result.getString("password"));
                
                
            
            }
            String sql4 = "SELECT * FROM `ameliyathane` WHERE ameliyathane.id ="+a;
            while(result.next()){

                ameliyathane =new Ameliyathane(result.getInt("id"),result.getString("name"));
                 
            
            }
            
           
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, doktor.getId());
            preparedStatement.setInt(2, hasta.getId());
             preparedStatement.setInt(3, a);
             preparedStatement.setString(4, tarih);
             preparedStatement.setString(5, saat);
            
                 
            preparedStatement.execute();
            Ameliyat ameliyat = new Ameliyat(doktor,tarih,saat,hasta,ameliyathane);
           
            

            
            


        
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            result.close();

            
        }
        
    }
    
    
    
}
