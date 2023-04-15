/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hastaneotomasyon.Model;

import hastaneotomasyon.Model.Poliklinik;
import hastaneotomasyon.Model.Hasta;
import hastaneotomasyon.Model.Doktor;

/**
 *
 * @author Mustafa
 */
public class Randevu {
    private int id;
    private Hasta hasta;
    private Doktor doktor;
    private Poliklinik poliklinik;
    private String tarih;
    private String saat;

    public Randevu(Hasta hasta, Doktor doktor, Poliklinik poliklinik, String tarih, String saat) {
        this.hasta = hasta;
        this.doktor = doktor;
        this.poliklinik = poliklinik;
        this.tarih = tarih;
        this.saat = saat;
    }

    public Randevu(int id, Doktor doktor, Poliklinik poliklinik, String tarih, String saat) {
        this.id = id;
        this.doktor = doktor;
        this.poliklinik = poliklinik;
        this.tarih = tarih;
        this.saat = saat;
    }

    public Randevu(Doktor doktor, Poliklinik poliklinik, String tarih, String saat) {
        this.doktor = doktor;
        this.poliklinik = poliklinik;
        this.tarih = tarih;
        this.saat = saat;
    }

    public Randevu(int id, Hasta hasta, Poliklinik poliklinik, String tarih, String saat) {
        this.id = id;
        this.hasta = hasta;
        this.poliklinik = poliklinik;
        this.tarih = tarih;
        this.saat = saat;
    }
    

    
    
    

    public Hasta getHasta() {
        return hasta;
    }

    public void setHasta(Hasta hasta) {
        this.hasta = hasta;
    }

    public Doktor getDoktor() {
        return doktor;
    }

    public void setDoktor(Doktor doktor) {
        this.doktor = doktor;
    }

    public Poliklinik getPoliklinik() {
        return poliklinik;
    }

    public void setPoliklinik(Poliklinik poliklinik) {
        this.poliklinik = poliklinik;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
}
