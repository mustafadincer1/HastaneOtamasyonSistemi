/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hastaneotomasyon.View;

import hastaneotomasyon.Model.Alerjiler;
import hastaneotomasyon.Model.Ameliyat;
import hastaneotomasyon.Model.Doktor;
import hastaneotomasyon.Model.Hasta;
import hastaneotomasyon.View.HastaGUI;
import hastaneotomasyon.Helper.DbHelper;
import hastaneotomasyon.Helper.*;
import hastaneotomasyon.Model.Poliklinik;
import hastaneotomasyon.Model.Randevu;
import hastaneotomasyon.Model.Tahliller;
import hastaneotomasyon.View.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mustafa
 */
public class DoktorGUI extends javax.swing.JFrame {
    DefaultTableModel model;
    DefaultTableModel model2;
    DefaultTableModel model4;
     DefaultTableModel model5;
    
    static Doktor doktor=new Doktor();
    static int id;
    /**
     * Creates new form DoktorGUI
     */
    public DoktorGUI(Doktor doktor) {
        initComponents();
        id = doktor.getId();
        TextAD.setText(doktor.getName());
        TextSOYAD.setText(doktor.getSurname());
        TextTC1.setText(doktor.getTc());
        TextPass1.setText(doktor.getPassword());
        model = (DefaultTableModel)TableTahlil.getModel();
        model2 = (DefaultTableModel)TableAlerji.getModel();
        model4 = (DefaultTableModel)TableMyRandevu.getModel();
        model5 =(DefaultTableModel)TableAmeliyat.getModel();
         LabelHoş.setText("Hoşgeldiniz " + doktor.getName() + " " +doktor.getSurname());
         
        try {           
            ArrayList <Tahliller> tahliller = Tahliller.getTahlil();
            ArrayList <Alerjiler> alerjiler = Alerjiler.getAlerji();
             ArrayList <Randevu> randevular = getRandevu();
              ArrayList<Ameliyat> ameliyat = Ameliyat.getAmeliyat();
              ArrayList<Hasta> hastalar =   Hasta.getHasta();
              
              for(Hasta hasta : hastalar){
                 ComboHasta.addItem(hasta.getTc());
                 ComboHasta2.addItem(hasta.getTc());
                 ComboHasta3.addItem(hasta.getTc());
                
            
            
            }
            for(Tahliller tahlil : tahliller){
            
                Object[] row2 ={tahlil.getName(),tahlil.getSurname(),tahlil.getTc(),tahlil.getTahlilName()};
               
                model.addRow(row2);
            }
            for(Alerjiler alerji:alerjiler){
                Object[] row = {alerji.getName(),alerji.getSurname(),alerji.getTc(),alerji.getAlerjiName()};
                
                model2.addRow(row);
            
            }
            for(Randevu randevu :randevular){
                String hasta = randevu.getHasta().getName() +" "+ randevu.getHasta().getSurname();
                String neden = randevu.getHasta().getHastaligi();
                String poli = randevu.getPoliklinik().getKlinikAdı();
                
                
                Object[] row3 ={randevu.getId(),hasta,neden,poli,randevu.getTarih(),randevu.getSaat()};
                model4.addRow(row3);
            
            }
            for(Ameliyat ameliyat1 :ameliyat){
                if(ameliyat1.getDoktor().getId()==doktor.getId()){
                    String hasta = ameliyat1.getHasta().getName()+ " " +ameliyat1.getHasta().getSurname();
                    Object[] row = {hasta,ameliyat1.getTarih(),ameliyat1.getSaat(),ameliyat1.getAmeliyathane().getAd()};
                    model5.addRow(row);
               
                
                
                }
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoktorGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateAlerji(){
        
        int rowCount1 = model2.getRowCount();
        for (int i = rowCount1 - 1; i >= 0; i--) {
                 model2.removeRow(i);

           }
       model2 = (DefaultTableModel)TableAlerji.getModel();
        try{
            ArrayList <Alerjiler> alerjiler = Alerjiler.getAlerji();
            for(Alerjiler alerji:alerjiler){
                Object[] row = {alerji.getName(),alerji.getSurname(),alerji.getTc(),alerji.getAlerjiName()};
                
                model2.addRow(row);
            
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    
    }
    public void updateTahlil(){
    
        int rowCount1 = model.getRowCount();
        for (int i = rowCount1 - 1; i >= 0; i--) {
                 model.removeRow(i);

           }
        model = (DefaultTableModel)TableTahlil.getModel();
        try{
            ArrayList <Tahliller> tahliller = Tahliller.getTahlil();
            for(Tahliller tahlil : tahliller){
            
                Object[] row2 ={tahlil.getName(),tahlil.getSurname(),tahlil.getTc(),tahlil.getTahlilName()};
               
                model.addRow(row2);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
    
    public ArrayList <Randevu> getRandevu() throws SQLException{
         Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
         ArrayList<Randevu> randevu = null;
         Hasta hasta = new Hasta();
  
         Poliklinik poliklinik = new Poliklinik();
         
          try{
            connect =db.getConnection();
            state = connect.createStatement();
        
            String sql ="SELECT users.id,users.name,users.surname,users.password,users.tc,poliklinik.poliklinik_adi,hasta_detail.hastalıgı,randevu.tarih,randevu.saat,randevu.randevu_id FROM randevu INNER JOIN hasta_detail,users,poliklinik "
                    + "WHERE users.id = randevu.hasta_id AND randevu.kontrol = 1 AND poliklinik.id =randevu.poliklinik_id AND users.id = hasta_detail.kişi_id AND randevu.doktor_id = " + id;
            result = state.executeQuery(sql);
            randevu = new ArrayList <Randevu>();
            while(result.next()){
                hasta =new Hasta(result.getInt("id"),result.getString("name"),result.getString("surname"),
                        result.getString("tc"),result.getString("password"),result.getString("hastalıgı"));
                
                poliklinik  = new Poliklinik(result.getInt("id"),result.getString("poliklinik_adi"));
                
                randevu.add(new Randevu(result.getInt("randevu_id"),hasta,poliklinik,result.getString("tarih"),result.getString("saat")));
                
                
            
            }
            
            return randevu;
          
            
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

        LabelHoş = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TextAD = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TC1 = new javax.swing.JLabel();
        TC = new javax.swing.JLabel();
        TextSOYAD = new javax.swing.JTextField();
        TextTC1 = new javax.swing.JTextField();
        TextPass1 = new javax.swing.JTextField();
        ButtonUpdate = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableMyRandevu = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableAmeliyat = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        ComboHasta = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TextHastalık = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableAlerji = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        ComboHasta3 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        TextAlerji = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableTahlil = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        ComboHasta2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        TextTahlil = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LabelHoş.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LabelHoş.setText("jLabel1");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(TC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TC1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextAD, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextSOYAD, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextTC1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(ButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextAD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextSOYAD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TC1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextTC1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(ButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TC, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );

        jTabbedPane1.addTab("Bilgi Güncelleme", jPanel3);

        TableMyRandevu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Hasta", "Hastalık Nedeni", "Poliklinik", "Tarih", "Saat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(TableMyRandevu);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Randevular", jPanel4);

        TableAmeliyat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hasta", "Tarih", "Saat", "Ameliyathane"
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
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ameliyatlar", jPanel5);

        jLabel1.setText("Hasta TC:");

        jLabel2.setText("Hastalığı");

        TextHastalık.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextHastalıkActionPerformed(evt);
            }
        });

        jButton1.setText("Ekle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ComboHasta, 0, 132, Short.MAX_VALUE)
                    .addComponent(TextHastalık))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComboHasta, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextHastalık, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hasta Bilgisi", jPanel6);

        TableAlerji.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ad", "Soyad", "TC", "Alerji"
            }
        ));
        jScrollPane2.setViewportView(TableAlerji);

        jLabel7.setText("Hasta TC");

        jLabel8.setText("Alerji");

        jButton3.setText("Ekle");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboHasta3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextAlerji, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ComboHasta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextAlerji, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Alerjiler", jPanel2);

        TableTahlil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ad", "Soyad", "TC", "Tahliller"
            }
        ));
        jScrollPane1.setViewportView(TableTahlil);

        jLabel5.setText("Hasta TC");

        jButton2.setText("Ekle");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Tahlil Adı");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboHasta2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextTahlil))
                .addGap(0, 43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ComboHasta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextTahlil, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tahliller", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelHoş, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(LabelHoş, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextADActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextADActionPerformed

    private void ButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpdateActionPerformed

        try {
            doktor.updateUser(id, TextAD.getText(), TextSOYAD.getText(), TextTC1.getText(), TextPass1.getText());

            Doktor doktor2 = new Doktor(id,TextAD.getText(),TextSOYAD.getText(),
                TextTC1.getText(),TextPass1.getText(),"doktor");
            DoktorGUI d = new DoktorGUI(doktor2);
            d.setVisible(true);
            dispose();

        } catch (SQLException ex) {
            Logger.getLogger(HastaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ButtonUpdateActionPerformed

    private void TextHastalıkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextHastalıkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextHastalıkActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tc = ComboHasta.getSelectedItem().toString();
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        PreparedStatement preparedStatement =null;
        Hasta hasta = null;
        
        try{
            System.out.println(tc);
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "SELECT * FROM users WHERE tc="+ tc;
            result = state.executeQuery(sql);
            
            while(result.next()){
                hasta = new Hasta(result.getInt("id"),result.getString("name"),result.getString("surname"),
                        result.getString("tc"),result.getString("password"));
            }
            
            String sql2 = "INSERT INTO `hasta_detail` (`kişi_id`, `hastalıgı`) VALUES" + "(?, ?)";
            preparedStatement = connect.prepareStatement(sql2);
            preparedStatement.setInt(1, hasta.getId());
            preparedStatement.setString(2, TextHastalık.getText());
            
            preparedStatement.execute();
            Message.showMessage("hastaGün");
            
            
        }catch(SQLException e){
                  System.out.println(e.getMessage());
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String tc = ComboHasta2.getSelectedItem().toString();
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        PreparedStatement preparedStatement =null;
        Hasta hasta = null;
        
        try{
            System.out.println(tc);
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "SELECT * FROM users WHERE tc="+ tc;
            result = state.executeQuery(sql);
            
            while(result.next()){
                hasta = new Hasta(result.getInt("id"),result.getString("name"),result.getString("surname"),
                        result.getString("tc"),result.getString("password"));
            }
            
            String sql2 = "INSERT INTO tahliller(`hasta_id`, `tahlil_adi`) VALUES" + "(?, ?)";
            preparedStatement = connect.prepareStatement(sql2);
            preparedStatement.setInt(1, hasta.getId());
            preparedStatement.setString(2, TextTahlil.getText());
            
            preparedStatement.execute();
            Message.showMessage("tahlil");
            updateTahlil();
            jPanel1.revalidate();
           jPanel1.repaint();
            
            
            
        }catch(SQLException e){
                  System.out.println(e.getMessage());
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String tc = ComboHasta3.getSelectedItem().toString();
        Connection connect =null;
        DbHelper db = new DbHelper();
        Statement state =null;
        ResultSet result;
        PreparedStatement preparedStatement =null;
        Hasta hasta = null;
        
        try{
            System.out.println(tc);
            connect =db.getConnection();
            state = connect.createStatement();
            String sql = "SELECT * FROM users WHERE tc="+ tc;
            result = state.executeQuery(sql);
            
            while(result.next()){
                hasta = new Hasta(result.getInt("id"),result.getString("name"),result.getString("surname"),
                        result.getString("tc"),result.getString("password"));
            }
            
            String sql2 = "INSERT INTO `alerjiler`(`hasta_id`, `alerji_adi`) VALUES" + "(?, ?)";
            preparedStatement = connect.prepareStatement(sql2);
            preparedStatement.setInt(1, hasta.getId());
            preparedStatement.setString(2, TextAlerji.getText());
            
            preparedStatement.execute();
            Message.showMessage("alerji");
            updateAlerji();
            jPanel2.revalidate();
           jPanel2.repaint();
            
            
            
        }catch(SQLException e){
                  System.out.println(e.getMessage());
            
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(DoktorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoktorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoktorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoktorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoktorGUI(doktor).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonUpdate;
    private javax.swing.JComboBox<String> ComboHasta;
    private javax.swing.JComboBox<String> ComboHasta2;
    private javax.swing.JComboBox<String> ComboHasta3;
    private javax.swing.JLabel LabelHoş;
    private javax.swing.JLabel TC;
    private javax.swing.JLabel TC1;
    private javax.swing.JTable TableAlerji;
    private javax.swing.JTable TableAmeliyat;
    private javax.swing.JTable TableMyRandevu;
    private javax.swing.JTable TableTahlil;
    private javax.swing.JTextField TextAD;
    private javax.swing.JTextField TextAlerji;
    private javax.swing.JTextField TextHastalık;
    private javax.swing.JTextField TextPass1;
    private javax.swing.JTextField TextSOYAD;
    private javax.swing.JTextField TextTC1;
    private javax.swing.JTextField TextTahlil;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
