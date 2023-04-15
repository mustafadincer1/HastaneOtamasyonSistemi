/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hastaneotomasyon.Helper;

import javax.swing.JOptionPane;

/**
 *
 * @author Mustafa
 */
public class Message {
    
    public static void showMessage(String a){
        String str;
        if(a == "fill" )
            str = "Lütfen Tüm Alanları Doldurunuz";
        else if(a == "sifre")
            str = "Şifre Yanlış";
        else if(a == "kayıt")
            str = "Kayıt Başarıyla Oluşturuldu";
        else if (a == "doktor")
            str = "Doktor Başarıyla Eklendi";
        
        else if(a == "randevu")
            str="Randevu Başarıyla Eklendi";
        else if (a=="Ameliyat")
            str="Ameliyat Başarıyla Eklendi";
        else if(a == "hastaGün")
            str = "Hasta Bilgisi Güncellendi";
        else if(a=="tahlil")
            str = "Tahlil Başarıyla Eklendi";
        else if(a=="alerji")
            str = "Alerji Başarıyla Eklendi";
        else
            str = null;
        JOptionPane.showMessageDialog(null, str);
    }
   
    
}
