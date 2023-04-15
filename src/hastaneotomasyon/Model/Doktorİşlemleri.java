/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hastaneotomasyon.Model;

import hastaneotomasyon.Model.Doktor;
import java.sql.SQLException;

/**
 *
 * @author Mustafa
 */
public abstract class Doktorİşlemleri {
    private Doktor doktor;
    private String tarih;
    private String saat;

    public Doktorİşlemleri(Doktor doktor, String tarih, String saat) {
        this.doktor = doktor;
        this.tarih = tarih;
        this.saat = saat;
    }

    public Doktorİşlemleri() {
    }

    public Doktorİşlemleri(String tarih, String saat) {
        this.tarih = tarih;
        this.saat = saat;
    }

    
    

    public Doktor getDoktor() {
        return doktor;
    }

    public void setDoktor(Doktor doktor) {
        this.doktor = doktor;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }
    
    
    public abstract void çalış(String doktor_tc,String hasta_tc,String tarih, String saat,int a)  throws SQLException ;
    
    
    
}
