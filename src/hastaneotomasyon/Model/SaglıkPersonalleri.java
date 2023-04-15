/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hastaneotomasyon.Model;

/**
 *
 * @author Mustafa
 */
public abstract class SaglıkPersonalleri implements Users{
    private int id;
    private String name;
     private String surname;
     private String tc;
     private String password;

    public SaglıkPersonalleri(int id, String name, String surname, String tc, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.tc = tc;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public SaglıkPersonalleri() {
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
    
   public abstract void setGörev(String görev);
   public abstract String getGörev();
   
     
     
    
}
