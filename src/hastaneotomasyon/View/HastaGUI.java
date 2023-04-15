/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hastaneotomasyon.View;

import hastaneotomasyon.Model.Alerjiler;
import hastaneotomasyon.Model.Ameliyat;
import hastaneotomasyon.Model.Doktor;
import hastaneotomasyon.Model.Hasta;
import hastaneotomasyon.Helper.DbHelper;
import hastaneotomasyon.Model.Poliklinik;
import hastaneotomasyon.Model.Randevu;
import hastaneotomasyon.Model.Tahliller;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mustafa
 */
public class HastaGUI extends javax.swing.JFrame {
    DefaultTableModel model;
    DefaultTableModel model2;
    DefaultTableModel model3;
    DefaultTableModel model4;
    DefaultTableModel model5;
    public static Hasta hasta = new Hasta();
    static int id  ;
    

    /**
     * Creates new form HastaGUI
     */
    public HastaGUI(Hasta hasta) {
        initComponents();
        id = hasta.getId();
         
        
        
        model = (DefaultTableModel)TableAlerji.getModel();
        model2 = (DefaultTableModel)TableTahlil.getModel();
        model3 = (DefaultTableModel)TableRandevu.getModel();
        model4 = (DefaultTableModel)TableMyRandevu.getModel();
        model5 =(DefaultTableModel)TableAmeliyat.getModel();
        LabelHoş.setText("Hoşgeldiniz " + hasta.getName() + " " +hasta.getSurname());
        TextAD.setText(hasta.getName());
        TextSOYAD.setText(hasta.getSurname());
        TextTC1.setText(hasta.getTc());
        TextPass1.setText(hasta.getPassword());
        try{
            ArrayList <Alerjiler> alerjiler = getAlerji(hasta);
            ArrayList <Tahliller> tahliller = getTahlil(hasta);
            ArrayList <Randevu> randevular = getRandevu();
            ArrayList <Randevu> myrandevular = getMyRandevu();
             ArrayList<Ameliyat> ameliyat = Ameliyat.getAmeliyat();
            int i =1;
            for(Alerjiler alerji:alerjiler){
                Object[] row = {i,alerji.getAlerjiName()};
                ++i;
                model.addRow(row);
            
            }
            int j =1;
            for(Tahliller tahlil : tahliller){
            
                Object[] row2 ={j,tahlil.getTahlilName()};
                ++j;
                model2.addRow(row2);
            }
            for(Randevu randevu :randevular){
                String doktor = randevu.getDoktor().getName() + " " +randevu.getDoktor().getSurname();
                String poli = randevu.getPoliklinik().getKlinikAdı();
                
                
                Object[] row3 ={randevu.getId(),doktor,poli,randevu.getTarih(),randevu.getSaat()};
                model3.addRow(row3);
            
            }
            for(Randevu myrandevu :myrandevular){
                String doktor = myrandevu.getDoktor().getName() + " " + myrandevu.getDoktor().getSurname();
                String poli = myrandevu.getPoliklinik().getKlinikAdı();
                
                
                Object[] row4 ={myrandevu.getId(),doktor,poli,myrandevu.getTarih(),myrandevu.getSaat()};
                model4.addRow(row4);
            
            }
            for(Ameliyat ameliyat1 :ameliyat){
                if(ameliyat1.getHasta().getId()==hasta.getId()){
                    String hasta1 = ameliyat1.getDoktor().getName()+ " " +ameliyat1.getDoktor().getSurname();
                    Object[] row = {hasta1,ameliyat1.getTarih(),ameliyat1.getSaat(),ameliyat1.getAmeliyathane().getAd()};
                    model5.addRow(row);
               
                
                
                }
                
                
                
            }
       
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
       
    }
    public void updateMyRandevu(){
        int rowCount1 = model4.getRowCount();

           for (int i = rowCount1 - 1; i >= 0; i--) {
                 model4.removeRow(i);

           }
        model4 = (DefaultTableModel)TableMyRandevu.getModel();
        try{
           
            ArrayList <Randevu> myrandevular = getMyRandevu();
           
            for(Randevu myrandevu :myrandevular){
                String doktor = myrandevu.getDoktor().getName() + myrandevu.getDoktor().getSurname();
                String poli = myrandevu.getPoliklinik().getKlinikAdı();
                
                
                Object[] row4 ={myrandevu.getId(),doktor,poli,myrandevu.getTarih(),myrandevu.getSaat()};
                model4.addRow(row4);
            
            }
       
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    
    }
    public void updateRandevu(){
        int rowCount = model3.getRowCount();

           for (int i = rowCount - 1; i >= 0; i--) {
                 model3.removeRow(i);

           }
        model3 = (DefaultTableModel)TableRandevu.getModel();
         try{

            ArrayList <Randevu> randevular = getRandevu();
        
            for(Randevu randevu :randevular){
                String doktor = randevu.getDoktor().getName() + randevu.getDoktor().getSurname();
                String poli = randevu.getPoliklinik().getKlinikAdı();
                
                
                Object[] row3 ={randevu.getId(),doktor,poli,randevu.getTarih(),randevu.getSaat()};
                model3.addRow(row3);
            
            }
       
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
       
        
    
    }
    
    public ArrayList <Randevu> getMyRandevu() throws SQLException{
    
         Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
         ArrayList<Randevu> randevu = null;
         Doktor doktor = new Doktor();
  
         Poliklinik poliklinik = new Poliklinik();
         
          try{
            connect =db.getConnection();
            state = connect.createStatement();
        
            String sql = "SELECT users.id,users.name,users.surname,users.password,users.tc,poliklinik.poliklinik_adi,doktor_detail.alan,randevu.tarih,randevu.saat,randevu.randevu_id FROM randevu INNER JOIN doktor_detail,users,poliklinik "
                    + "WHERE users.id = randevu.doktor_id AND randevu.kontrol = 1 AND poliklinik.id =randevu.poliklinik_id AND users.id = doktor_detail.doktor_id AND randevu.hasta_id = " +id;
            result = state.executeQuery(sql);
            randevu = new ArrayList <Randevu>();
            while(result.next()){
                doktor =new Doktor(result.getInt("id"),result.getString("name"),result.getString("surname"),
                        result.getString("tc"),result.getString("password"),result.getString("alan"));
                
                poliklinik  = new Poliklinik(result.getInt("id"),result.getString("poliklinik_adi"));
                
                randevu.add(new Randevu(result.getInt("randevu_id"),doktor,poliklinik,result.getString("tarih"),result.getString("saat")));
                
                
            
            }
            
            return randevu;
          
            
        }catch(SQLException e){
                  System.out.println(e.getMessage());
            
        }
         
        
          return null;
    
    }
   
    
    public ArrayList<Randevu> getRandevu() throws SQLException{
    
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
         ArrayList<Randevu> randevu = null;
         Doktor doktor = new Doktor();
  
         Poliklinik poliklinik = new Poliklinik();
         
          try{
            connect =db.getConnection();
            state = connect.createStatement();
            String a = Integer.toString(hasta.getId());
            String sql = "SELECT users.id,users.name,users.surname,users.password,users.tc,poliklinik.poliklinik_adi,doktor_detail.alan,randevu.tarih,randevu.saat,randevu.randevu_id FROM randevu INNER JOIN doktor_detail,users,poliklinik WHERE users.id = randevu.doktor_id AND randevu.kontrol = 0 AND poliklinik.id =randevu.poliklinik_id AND users.id = doktor_detail.doktor_id;";
            result = state.executeQuery(sql);
            randevu = new ArrayList <Randevu>();
            while(result.next()){
                doktor =new Doktor(result.getInt("id"),result.getString("name"),result.getString("surname"),
                        result.getString("tc"),result.getString("password"),result.getString("alan"));
                
                poliklinik  = new Poliklinik(result.getInt("id"),result.getString("poliklinik_adi"));
                
                randevu.add(new Randevu(result.getInt("randevu_id"),doktor,poliklinik,result.getString("tarih"),result.getString("saat")));
                
                
            
            }
            
            return randevu;
          
            
        }catch(SQLException e){
                  System.out.println(e.getMessage());
            
        }
         
        
          return null;
    }
    
    
    public ArrayList <Alerjiler> getAlerji(Hasta hasta) throws SQLException{
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        ArrayList <Alerjiler> alerji = null;
        
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String a = Integer.toString(hasta.getId());
            String sql = "SELECT users.id,users.name,users.surname,users.tc,users.password,alerjiler.alerji_adi FROM alerjiler INNER JOIN users WHERE alerjiler.hasta_id = users.id AND alerjiler.hasta_id ="+a  ;
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
    public ArrayList <Tahliller> getTahlil(Hasta hasta) throws SQLException{
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        ArrayList <Tahliller> tahlil = null;
        
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String a = Integer.toString(hasta.getId());
            String sql = "Select users.id,users.name,users.surname,users.password,users.tc,tahliller.tahlil_adi FROM tahliller INNER JOIN users WHERE tahliller.hasta_id=users.id AND tahliller.hasta_id= "+a ;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableAlerji = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableTahlil = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TextAD = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TC1 = new javax.swing.JLabel();
        TC = new javax.swing.JLabel();
        TextSOYAD = new javax.swing.JTextField();
        TextTC1 = new javax.swing.JTextField();
        TextPass1 = new javax.swing.JTextField();
        ButtonUpdate = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableRandevu = new javax.swing.JTable();
        RandevuAL = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableMyRandevu = new javax.swing.JTable();
        Buttonİptal = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableAmeliyat = new javax.swing.JTable();
        LabelHoş = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TableAlerji.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Alerji"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TableAlerji);
        if (TableAlerji.getColumnModel().getColumnCount() > 0) {
            TableAlerji.getColumnModel().getColumn(0).setResizable(false);
            TableAlerji.getColumnModel().getColumn(1).setResizable(false);
        }

        TableTahlil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Tahliller"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TableTahlil);
        if (TableTahlil.getColumnModel().getColumnCount() > 0) {
            TableTahlil.getColumnModel().getColumn(0).setResizable(false);
            TableTahlil.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Alerjiler ve Tahliller", jPanel1);

        jLabel3.setText("AD:");

        TextAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextADActionPerformed(evt);
            }
        });

        jLabel4.setText("SOYAD:");

        TC1.setText("TC KİMLİK NO:");

        TC.setText("ŞİFRE:");

        ButtonUpdate.setText("Bilgileri Güncelle");
        ButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(TC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TC1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextAD, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextSOYAD, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextTC1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(ButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextAD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextSOYAD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TC1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextTC1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(ButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TC, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );

        jTabbedPane1.addTab("Bilgi Güncelleme", jPanel2);

        TableRandevu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Doktor", "Poliklinik", "Tarih", "Saat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(TableRandevu);
        if (TableRandevu.getColumnModel().getColumnCount() > 0) {
            TableRandevu.getColumnModel().getColumn(0).setResizable(false);
            TableRandevu.getColumnModel().getColumn(1).setResizable(false);
            TableRandevu.getColumnModel().getColumn(2).setResizable(false);
            TableRandevu.getColumnModel().getColumn(3).setResizable(false);
            TableRandevu.getColumnModel().getColumn(4).setResizable(false);
        }

        RandevuAL.setText("Randevuyu Al");
        RandevuAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RandevuALActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(RandevuAL)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(RandevuAL, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Randevu Al", jPanel3);

        TableMyRandevu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Doktor", "Poliklinik", "Tarih", "Saat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(TableMyRandevu);
        if (TableMyRandevu.getColumnModel().getColumnCount() > 0) {
            TableMyRandevu.getColumnModel().getColumn(0).setResizable(false);
            TableMyRandevu.getColumnModel().getColumn(1).setResizable(false);
            TableMyRandevu.getColumnModel().getColumn(2).setResizable(false);
            TableMyRandevu.getColumnModel().getColumn(3).setResizable(false);
            TableMyRandevu.getColumnModel().getColumn(4).setResizable(false);
        }

        Buttonİptal.setText("Randevu İptal");
        Buttonİptal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonİptalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 387, Short.MAX_VALUE)
                .addComponent(Buttonİptal, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(181, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(Buttonİptal, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addGap(3, 3, 3)))
        );

        jTabbedPane1.addTab("Randevularım", jPanel4);

        TableAmeliyat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doktor", "Tarih", "Saat", "Ameliyathane"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(TableAmeliyat);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ameliyatlar", jPanel5);

        LabelHoş.setBackground(new java.awt.Color(255, 255, 255));
        LabelHoş.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LabelHoş.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LabelHoş, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(LabelHoş, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextADActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextADActionPerformed

    private void ButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpdateActionPerformed
       // System.out.println(id);
       
        try {
            hasta.updateUser(id, TextAD.getText(), TextSOYAD.getText(), TextTC1.getText(), TextPass1.getText());
            
            Hasta hasta2 = new Hasta(id,TextAD.getText(),TextSOYAD.getText(),
                                    TextTC1.getText(),TextPass1.getText());
                            HastaGUI d = new HastaGUI(hasta2);
                            d.setVisible(true);
                            dispose();
                           
        } catch (SQLException ex) {
            Logger.getLogger(HastaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ButtonUpdateActionPerformed

    private void RandevuALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RandevuALActionPerformed
        //System.out.println(id);
        //System.out.println(TableRandevu.getValueAt(TableRandevu.getSelectedRow(),0).toString());
     
        try {
            String a = TableRandevu.getValueAt(TableRandevu.getSelectedRow(),0).toString();
            int b = Integer.valueOf(a);
            hasta.RandevuAl(b, id);
            
           
           updateRandevu();
           updateMyRandevu();
            jPanel3.revalidate();
            jPanel3.repaint();
           
            
            
            
           
           
        } catch (SQLException ex) {
            Logger.getLogger(HastaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_RandevuALActionPerformed

    private void ButtonİptalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonİptalActionPerformed
        try {
            String a = TableMyRandevu.getValueAt(TableMyRandevu.getSelectedRow(),0).toString();
            int b = Integer.valueOf(a);
            hasta.Randevuİptal(b);
             updateRandevu();
           updateMyRandevu();
            jPanel3.revalidate();
            jPanel3.repaint();
           
            
        } catch (SQLException ex) {
            Logger.getLogger(HastaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ButtonİptalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HastaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HastaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HastaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HastaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HastaGUI(hasta).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonUpdate;
    private javax.swing.JButton Buttonİptal;
    private javax.swing.JLabel LabelHoş;
    private javax.swing.JButton RandevuAL;
    private javax.swing.JLabel TC;
    private javax.swing.JLabel TC1;
    private javax.swing.JTable TableAlerji;
    private javax.swing.JTable TableAmeliyat;
    private javax.swing.JTable TableMyRandevu;
    private javax.swing.JTable TableRandevu;
    private javax.swing.JTable TableTahlil;
    private javax.swing.JTextField TextAD;
    private javax.swing.JTextField TextPass1;
    private javax.swing.JTextField TextSOYAD;
    private javax.swing.JTextField TextTC1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
