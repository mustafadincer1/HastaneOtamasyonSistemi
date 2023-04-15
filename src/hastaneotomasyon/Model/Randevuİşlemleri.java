/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hastaneotomasyon.Model;

import java.sql.SQLException;

/**
 *
 * @author Mustafa
 */
public interface Randevuİşlemleri {
    public void RandevuAl(int id,int hasta_id) throws SQLException;
    public void Randevuİptal(int id) throws SQLException;
    
}
