/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hastaneotomasyon.View;

import hastaneotomasyon.Model.Ameliyat;
import hastaneotomasyon.Model.Ameliyat;
import hastaneotomasyon.Model.Ameliyathane;
import hastaneotomasyon.Model.Ameliyathane;
import hastaneotomasyon.Model.BasHekim;
import hastaneotomasyon.Model.BasHekim;
import hastaneotomasyon.Helper.DbHelper;
import hastaneotomasyon.Model.Doktor;
import hastaneotomasyon.Model.Doktor;
import hastaneotomasyon.Model.Hasta;
import hastaneotomasyon.Model.Hasta;
import hastaneotomasyon.Helper.Message;
import hastaneotomasyon.Model.Poliklinik;
import hastaneotomasyon.Model.Poliklinik;
import hastaneotomasyon.Model.Randevu;
import hastaneotomasyon.Model.Randevu;
import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mustafa
 */
public class BaşHekimGUI extends javax.swing.JFrame {
     int id ;
     static int id2; 
     static BasHekim hekim = new BasHekim();
     DefaultTableModel model;
      DefaultTableModel model2;
      DefaultTableModel model3;
      DefaultTableModel model4;
    /**
     * Creates new form BaşHekimGUI
     */
    public BaşHekimGUI(BasHekim hekim) {
        initComponents();
        LabelHoş.setText("Hoşgeldiniz " + hekim.getName() + " " +hekim.getSurname());
        id2 = hekim.getId();
        TextAD1.setText(hekim.getName());
        TextSOYAD1.setText(hekim.getSurname());
        TextTC2.setText(hekim.getTc());
        TextPass2.setText(hekim.getPassword());
        TableDoktor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               TextTC.setText(TableDoktor.getValueAt(TableDoktor.getSelectedRow(), 2).toString());
            }
        });
        model = (DefaultTableModel)TableDoktor.getModel();
         model2 = (DefaultTableModel)TablePoliklinik.getModel();
         model3=(DefaultTableModel)TableAmeliyathane.getModel();
         model4 =(DefaultTableModel)TableAmeliyat.getModel();

         try{
            ArrayList<Doktor> doktorlar =   Doktor.getDoktor();
            ArrayList<Hasta> hastalar =   Hasta.getHasta();
            ArrayList<Poliklinik> poliklinikler=Poliklinik.getPoliklinik();
            ArrayList <Ameliyathane> ameliyathane =Ameliyathane.getAmeliyathane();
            ArrayList<Ameliyat> ameliyat = Ameliyat.getAmeliyat();
            
            
            for(Hasta hasta : hastalar){
                 ComboHasta.addItem(hasta.getTc());
                
            
            
            }
            for(Doktor doktor :doktorlar){
                Object[] row = {doktor.getName(),doktor.getSurname(),doktor.getTc(),doktor.getGörev()};
                model.addRow(row);
                ComboDoktor.addItem(doktor.getTc());
                ComboDoktor2.addItem(doktor.getTc());
                
            }
            int i = 1;
            for(Poliklinik poliklinik : poliklinikler){
                Object[] row2 = {i,poliklinik.getKlinikAdı()};
                ++i;
                model2.addRow(row2);
                ComboPoliklinik.addItem(poliklinik.getKlinikAdı());
                
            
            }
            for(Ameliyathane ameliyathane1 :ameliyathane){
                Object[] row = {ameliyathane1.getId(),ameliyathane1.getAd()};
                model3.addRow(row);
                 int a = ameliyathane1.getId();
                String b = Integer.toString(a);
                ComboAmeliyathane.addItem(b);
                
                
            }
            for(Ameliyat ameliyat1 :ameliyat){
                
               
               
                
                String hasta = ameliyat1.getHasta().getName()+ " " +ameliyat1.getHasta().getSurname();
                String doktor = ameliyat1.getDoktor().getName() +" "+ ameliyat1.getDoktor().getSurname();
                Object[] row = {hasta,doktor,ameliyat1.getTarih(),ameliyat1.getSaat(),ameliyat1.getAmeliyathane().getAd()};
                model4.addRow(row);
               
                
                
            }
           
            
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void updateAmeliyat(){
        int rowCount1 = model4.getRowCount();
        for (int i = rowCount1 - 1; i >= 0; i--) {
                 model4.removeRow(i);

           }
        model4 =(DefaultTableModel)TableAmeliyat.getModel();
        try{
            ArrayList<Ameliyat> ameliyat = Ameliyat.getAmeliyat();
            for(Ameliyat ameliyat1 :ameliyat){
                
                String hasta = ameliyat1.getHasta().getName()+ " " +ameliyat1.getHasta().getSurname();
                String doktor = ameliyat1.getDoktor().getName() +" "+ ameliyat1.getDoktor().getSurname();
                Object[] row = {hasta,doktor,ameliyat1.getTarih(),ameliyat1.getSaat(),ameliyat1.getAmeliyathane().getAd()};
                model4.addRow(row);
               
                
                
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    
    };
    public void updateAmeliyathane(){
        int rowCount1 = model3.getRowCount();

           for (int i = rowCount1 - 1; i >= 0; i--) {
                 model3.removeRow(i);

           }
        model3 = (DefaultTableModel)TableAmeliyathane.getModel();
        try{
           
            ArrayList <Ameliyathane> Ameliyathaneler = Ameliyathane.getAmeliyathane();
           
            for(Ameliyathane ameliyathane :Ameliyathaneler){
                ;
                
                
                Object[] row4 ={ameliyathane.getId(),ameliyathane.getAd()};
                model3.addRow(row4);
            
            }
       
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    
    }
    
    public void updateDoktor(){
        int rowCount1 = model.getRowCount();

           for (int i = rowCount1 - 1; i >= 0; i--) {
                 model.removeRow(i);

           }
        model = (DefaultTableModel)TableDoktor.getModel();
        try{
           
            ArrayList <Doktor> Doktorlar = Doktor.getDoktor();
           
            for(Doktor doktor :Doktorlar){
                
                Object[] row = {doktor.getName(),doktor.getSurname(),doktor.getTc(),doktor.getGörev()};
                model.addRow(row);
                ComboDoktor.addItem(doktor.getTc());
            
            }
       
        }catch(SQLException e){
            
        }
    
    }
    public void updatePoliklinik(){
        int rowCount1 = model2.getRowCount();

           for (int i = rowCount1 - 1; i >= 0; i--) {
                 model2.removeRow(i);

           }
        model2 = (DefaultTableModel)TablePoliklinik.getModel();
        try{
           
            ArrayList <Poliklinik> Poliklinikler = Poliklinik.getPoliklinik();
           
            for(Poliklinik poliklinik :Poliklinikler){
                
                
                
                Object[] row4 ={poliklinik.getId(),poliklinik.getKlinikAdı()};
                model2.addRow(row4);
            
            }
       
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    
    }
   
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        LabelHoş = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableDoktor = new javax.swing.JTable();
        Sil = new javax.swing.JButton();
        TextTC = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TextAD = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TextSOYAD = new javax.swing.JTextField();
        TC1 = new javax.swing.JLabel();
        TextTC1 = new javax.swing.JTextField();
        TC = new javax.swing.JLabel();
        TextPass1 = new javax.swing.JTextField();
        Alan = new javax.swing.JLabel();
        TextALAN = new javax.swing.JTextField();
        ButtonDoktor = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        TextAD1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TC2 = new javax.swing.JLabel();
        TC3 = new javax.swing.JLabel();
        TextSOYAD1 = new javax.swing.JTextField();
        TextTC2 = new javax.swing.JTextField();
        TextPass2 = new javax.swing.JTextField();
        ButtonUpdate = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablePoliklinik = new javax.swing.JTable();
        TextPoliklinik = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        ButtonPekle = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ComboDoktor = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        ComboPoliklinik = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        TextTarih = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TextSaat = new javax.swing.JTextField();
        ButtonRandevuEkle = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableAmeliyathane = new javax.swing.JTable();
        TextAmeliyathane = new javax.swing.JTextField();
        ButtonAmeliyathane = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableAmeliyat = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ComboDoktor2 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        ComboHasta = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        ComboAmeliyathane = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        TextTarih2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        TextSaat2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LabelHoş.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        LabelHoş.setForeground(new java.awt.Color(255, 255, 255));
        LabelHoş.setText("Hoşgeldiniz");
        LabelHoş.setToolTipText("");

        TableDoktor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ad", "Soyad", "TC", "Alanı"
            }
        ));
        jScrollPane1.setViewportView(TableDoktor);
        if (TableDoktor.getColumnModel().getColumnCount() > 0) {
            TableDoktor.getColumnModel().getColumn(0).setResizable(false);
            TableDoktor.getColumnModel().getColumn(1).setResizable(false);
            TableDoktor.getColumnModel().getColumn(2).setResizable(false);
            TableDoktor.getColumnModel().getColumn(3).setResizable(false);
        }

        Sil.setText("Sil");
        Sil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SilActionPerformed(evt);
            }
        });

        TextTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextTCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Sil, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(TextTC))
                .addGap(12, 12, 12))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(Sil, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(TextTC, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Doktorlar", jPanel1);

        jLabel3.setText("AD:");

        TextAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextADActionPerformed(evt);
            }
        });

        jLabel4.setText("SOYAD:");

        TC1.setText("TC KİMLİK NO:");

        TC.setText("ŞİFRE:");

        Alan.setText("ALANI:");

        ButtonDoktor.setText("Doktor Ekle");
        ButtonDoktor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDoktorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(TC, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(TextPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TC1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TextAD, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextTC1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                            .addComponent(Alan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextSOYAD, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextALAN, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                        .addComponent(ButtonDoktor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextAD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextSOYAD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TC1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextTC1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Alan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextALAN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TC, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonDoktor, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(287, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Doktor Ekleme", jPanel2);

        jLabel5.setText("AD:");

        TextAD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextAD1ActionPerformed(evt);
            }
        });

        jLabel6.setText("SOYAD:");

        TC2.setText("TC KİMLİK NO:");

        TC3.setText("ŞİFRE:");

        ButtonUpdate.setText("Bilgileri Güncelle");
        ButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(TC3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextAD1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextSOYAD1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextPass2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextTC2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                .addComponent(ButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextAD1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextSOYAD1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TC2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextTC2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(ButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TC3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextPass2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(243, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Bilgi Güncelleme", jPanel3);

        TablePoliklinik.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Poliklinik"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TablePoliklinik);
        if (TablePoliklinik.getColumnModel().getColumnCount() > 0) {
            TablePoliklinik.getColumnModel().getColumn(0).setResizable(false);
            TablePoliklinik.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel1.setText("Poliklinik Adı:");

        ButtonPekle.setText("EKLE");
        ButtonPekle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPekleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TextPoliklinik)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonPekle, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                .addGap(0, 174, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 231, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TextPoliklinik, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(ButtonPekle, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Poliklinik", jPanel4);

        jLabel2.setText("Doktor:");

        ComboDoktor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboDoktorİtemStateChanged(evt);
            }
        });
        ComboDoktor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboDoktorActionPerformed(evt);
            }
        });

        jLabel7.setText("Polikilnik:");

        ComboPoliklinik.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboPoliklinikİtemStateChanged(evt);
            }
        });
        ComboPoliklinik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboPoliklinikActionPerformed(evt);
            }
        });

        jLabel8.setText("Tarih");

        jLabel9.setText("Saat:");

        TextSaat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextSaatActionPerformed(evt);
            }
        });

        ButtonRandevuEkle.setText("Randevu Ekle");
        ButtonRandevuEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRandevuEkleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboDoktor, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComboPoliklinik, 0, 86, Short.MAX_VALUE)
                            .addComponent(TextTarih)
                            .addComponent(TextSaat))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                .addComponent(ButtonRandevuEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboDoktor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboPoliklinik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextTarih, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ButtonRandevuEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextSaat, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(212, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Randevu", jPanel5);

        TableAmeliyathane.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Ameliyathane"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(TableAmeliyathane);
        if (TableAmeliyathane.getColumnModel().getColumnCount() > 0) {
            TableAmeliyathane.getColumnModel().getColumn(0).setResizable(false);
            TableAmeliyathane.getColumnModel().getColumn(1).setResizable(false);
        }

        ButtonAmeliyathane.setText("EKLE");
        ButtonAmeliyathane.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAmeliyathaneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TextAmeliyathane)
                    .addComponent(ButtonAmeliyathane, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(TextAmeliyathane, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(ButtonAmeliyathane, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(216, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ameliyathane", jPanel6);

        jButton1.setText("Ameliyat Ekle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        TableAmeliyat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hasta", "Doktor", "Tarih", "Saat", "Ameliyathane"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(TableAmeliyat);
        if (TableAmeliyat.getColumnModel().getColumnCount() > 0) {
            TableAmeliyat.getColumnModel().getColumn(0).setResizable(false);
            TableAmeliyat.getColumnModel().getColumn(1).setResizable(false);
            TableAmeliyat.getColumnModel().getColumn(2).setResizable(false);
            TableAmeliyat.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel14.setText("Doktor:");

        ComboDoktor2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboDoktor2İtemStateChanged(evt);
            }
        });
        ComboDoktor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboDoktor2ActionPerformed(evt);
            }
        });

        jLabel15.setText("Hasta:");

        ComboHasta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboHastaİtemStateChanged(evt);
            }
        });
        ComboHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboHastaActionPerformed(evt);
            }
        });

        jLabel16.setText("Ameliyathane");

        ComboAmeliyathane.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboAmeliyathaneİtemStateChanged(evt);
            }
        });
        ComboAmeliyathane.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboAmeliyathaneActionPerformed(evt);
            }
        });

        jLabel17.setText("Tarih");

        jLabel18.setText("Saat:");

        TextSaat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextSaat2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboDoktor2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextSaat2)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TextTarih2)
                                            .addComponent(ComboAmeliyathane, 0, 86, Short.MAX_VALUE))
                                        .addGap(0, 2, Short.MAX_VALUE)))))
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel11)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel12)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 54, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboDoktor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboAmeliyathane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextTarih2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextSaat2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel11)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel12)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Ameliyat Ekle", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(LabelHoş, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(LabelHoş, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SilActionPerformed
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        PreparedStatement preparedStatement =null;
        
        try{
            connect =db.getConnection();
            String sql = "DELETE FROM users WHERE tc =?"  ;
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, TableDoktor.getValueAt(TableDoktor.getSelectedRow(), 2).toString());
           
            preparedStatement.execute();
            
            BaşHekimGUI a = new BaşHekimGUI( hekim);
            a.setVisible(true);
            dispose();
            


        
        }catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        updateDoktor();
        
    }//GEN-LAST:event_SilActionPerformed

    private void TextADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextADActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextADActionPerformed

    private void ButtonDoktorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDoktorActionPerformed
         Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        PreparedStatement preparedStatement =null;
        PreparedStatement preparedStatement2 =null;
        
        
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "INSERT INTO users " + "(name,surname,password,tc,type) VALUES " +"(?,?,?,?,?)"  ;
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, TextAD.getText());
            preparedStatement.setString(2, TextSOYAD.getText());
            preparedStatement.setString(3, TextPass1.getText());
            preparedStatement.setString(4, TextTC1.getText());
            preparedStatement.setString(5, "doktor");
            preparedStatement.execute();
            String sql2 = "Select * FROM users WHERE tc= " + TextTC1.getText();
            result = state.executeQuery(sql2);
            while(result.next()){
                id= result.getInt("id");
                
            
            }
            System.out.println(id);
            
            
            String sql3 = "INSERT INTO doktor_detail " + "(doktor_id,alan) VALUES " +"(?,?)";
            preparedStatement2 = connect.prepareStatement(sql3);
            preparedStatement2.setInt(1, id);
            preparedStatement2.setString(2, TextALAN.getText());
            preparedStatement2.execute();
            
            
            Message.showMessage("doktor");
            TextAD.setText("");
            TextSOYAD.setText(""); 
            TextTC1.setText("");
            TextALAN.setText("");
            TextPass1.setText("");
          
           
            


        
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        updateDoktor();
        
    }//GEN-LAST:event_ButtonDoktorActionPerformed

    private void TextAD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextAD1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextAD1ActionPerformed

    private void ButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpdateActionPerformed

        try {
            hekim.updateUser(id2, TextAD1.getText(), TextSOYAD1.getText(), TextTC2.getText(), TextPass2.getText());

            BasHekim hekim2 = new BasHekim(id,TextAD1.getText(),TextSOYAD1.getText(),
                TextTC2.getText(),TextPass2.getText(),"Başhekim");
            BaşHekimGUI d = new BaşHekimGUI(hekim2);
            d.setVisible(true);
            dispose();

        } catch (SQLException ex) {
            
        }
    }//GEN-LAST:event_ButtonUpdateActionPerformed

    private void ButtonPekleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPekleActionPerformed
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        PreparedStatement preparedStatement =null;
        PreparedStatement preparedStatement2 =null;
        
        
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "INSERT INTO poliklinik " + "(poliklinik_adi) VALUES " +"(?)"  ;
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, TextPoliklinik.getText());
            preparedStatement.execute();
            

           updatePoliklinik();
           jPanel4.revalidate();
           jPanel4.repaint();
            


        
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
       
    }//GEN-LAST:event_ButtonPekleActionPerformed

    private void ComboDoktorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboDoktorActionPerformed
        
    }//GEN-LAST:event_ComboDoktorActionPerformed

    private void ComboDoktorİtemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboDoktorİtemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboDoktorİtemStateChanged

    private void ComboPoliklinikİtemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboPoliklinikİtemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboPoliklinikİtemStateChanged

    private void ComboPoliklinikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboPoliklinikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboPoliklinikActionPerformed

    private void ButtonRandevuEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRandevuEkleActionPerformed
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        ResultSet result2;
        PreparedStatement preparedStatement =null;
        PreparedStatement preparedStatement2 =null;
        Doktor doktor = new Doktor();
        Poliklinik poliklinik = new Poliklinik();
        Randevu randevu;
        
        
        
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "INSERT INTO `randevu` (`doktor_id`, `poliklinik_id`, `tarih`, `saat`) VALUES " + "(?,?,?,?)";
            preparedStatement = connect.prepareStatement(sql);
            String sql2= "SELECT id,name,surname,tc,password,doktor_detail.alan FROM `users` INNER JOIN doktor_detail WHERE id = doktor_detail.doktor_id AND tc ="+ComboDoktor.getSelectedItem();
            result = state.executeQuery(sql2);
            while(result.next()){
                doktor =new Doktor(result.getInt("id"),result.getString("name"),result.getString("surname"),
                        result.getString("tc"),result.getString("password"),result.getString("alan"));
                
            preparedStatement.setInt(1, doktor.getId());
            
            }
            ArrayList<Poliklinik> poliklinikler=Poliklinik.getPoliklinik();
            for(Poliklinik poliklinik1 : poliklinikler){
                if(poliklinik1.getKlinikAdı().equals(ComboPoliklinik.getSelectedItem().toString())){
                    
                    preparedStatement.setInt(2, poliklinik1.getId());
                    poliklinik = new Poliklinik(poliklinik1.getId(),poliklinik1.getKlinikAdı());
                }
            
            }
         
            preparedStatement.setString(3, TextTarih.getText());
             preparedStatement.setString(4, TextSaat.getText());
                 
            preparedStatement.execute();
            randevu = new Randevu(doktor,poliklinik,TextTarih.getText(),TextSaat.getText());
            

            Message.showMessage("randevu");
            TextTarih.setText("");
            TextSaat.setText("");
            


        
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_ButtonRandevuEkleActionPerformed

    private void TextSaatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextSaatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextSaatActionPerformed

    private void ButtonAmeliyathaneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAmeliyathaneActionPerformed
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        PreparedStatement preparedStatement =null;
        PreparedStatement preparedStatement2 =null;
        
        
        try{
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "INSERT INTO ameliyathane " + "(name) VALUES " +"(?)"  ;
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, TextAmeliyathane.getText());
            preparedStatement.execute();
            

            
            


        
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        updateAmeliyathane();
         
            jPanel6.revalidate();
            jPanel6.repaint();

    }//GEN-LAST:event_ButtonAmeliyathaneActionPerformed

    private void TextSaat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextSaat2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextSaat2ActionPerformed

    private void ComboAmeliyathaneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboAmeliyathaneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboAmeliyathaneActionPerformed

    private void ComboAmeliyathaneİtemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboAmeliyathaneİtemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboAmeliyathaneİtemStateChanged

    private void ComboHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboHastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboHastaActionPerformed

    private void ComboHastaİtemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboHastaİtemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboHastaİtemStateChanged

    private void ComboDoktor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboDoktor2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboDoktor2ActionPerformed

    private void ComboDoktor2İtemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboDoktor2İtemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboDoktor2İtemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Ameliyat ameliyat = new Ameliyat();
        try {
            ameliyat.çalış( ComboDoktor2.getSelectedItem().toString(),ComboHasta.getSelectedItem().toString(), TextTarih2.getText(), TextSaat2.getText(),Integer.valueOf(ComboAmeliyathane.getSelectedItem().toString()));
            TextTarih2.setText("");
            TextSaat2.setText("");
            Message.showMessage("Ameliyat");
           updateAmeliyat();
           jPanel7.revalidate();
           jPanel7.repaint();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(BaşHekimGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TextTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextTCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextTCActionPerformed

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
            java.util.logging.Logger.getLogger(BaşHekimGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BaşHekimGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BaşHekimGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BaşHekimGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaşHekimGUI(hekim).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Alan;
    private javax.swing.JButton ButtonAmeliyathane;
    private javax.swing.JButton ButtonDoktor;
    private javax.swing.JButton ButtonPekle;
    private javax.swing.JButton ButtonRandevuEkle;
    private javax.swing.JButton ButtonUpdate;
    private javax.swing.JComboBox<String> ComboAmeliyathane;
    private javax.swing.JComboBox<String> ComboDoktor;
    private javax.swing.JComboBox<String> ComboDoktor2;
    private javax.swing.JComboBox<String> ComboHasta;
    private javax.swing.JComboBox<String> ComboPoliklinik;
    private javax.swing.JLabel LabelHoş;
    private javax.swing.JButton Sil;
    private javax.swing.JLabel TC;
    private javax.swing.JLabel TC1;
    private javax.swing.JLabel TC2;
    private javax.swing.JLabel TC3;
    private javax.swing.JTable TableAmeliyat;
    private javax.swing.JTable TableAmeliyathane;
    private javax.swing.JTable TableDoktor;
    private javax.swing.JTable TablePoliklinik;
    private javax.swing.JTextField TextAD;
    private javax.swing.JTextField TextAD1;
    private javax.swing.JTextField TextALAN;
    private javax.swing.JTextField TextAmeliyathane;
    private javax.swing.JTextField TextPass1;
    private javax.swing.JTextField TextPass2;
    private javax.swing.JTextField TextPoliklinik;
    private javax.swing.JTextField TextSOYAD;
    private javax.swing.JTextField TextSOYAD1;
    private javax.swing.JTextField TextSaat;
    private javax.swing.JTextField TextSaat2;
    private javax.swing.JTextField TextTC;
    private javax.swing.JTextField TextTC1;
    private javax.swing.JTextField TextTC2;
    private javax.swing.JTextField TextTarih;
    private javax.swing.JTextField TextTarih2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
