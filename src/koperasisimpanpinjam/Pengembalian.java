/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasisimpanpinjam;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Didin
 */
public class Pengembalian extends javax.swing.JFrame {
Connection conn = null;
    PreparedStatement pst = null;
    DefaultTableModel tablepengembalian;
    ResultSet rs;
    /**
     * Creates new form Pengembalian
     */
    public Pengembalian() {
        initComponents();
        try {
            conn = Koneksi.getConnection();
            System.out.println("Koneksi berhasil");
        } catch (SQLException ex) {
            Logger.getLogger(Simpan.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Koneksi gagal");
        }
        try {
            showdata();
        } catch (SQLException ex) {
            Logger.getLogger(Pengembalian.class.getName()).log(Level.SEVERE, null, ex);
        }
    try {
        nokategori();
        tglhariini();
    } catch (SQLException ex) {
        Logger.getLogger(Pengembalian.class.getName()).log(Level.SEVERE, null, ex);
    }
    
     try {
            
            tampildata();
            jComboBox1.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        tampildata1();
                    } catch (SQLException ex) {
                        Logger.getLogger(Pengembalian.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(Pengembalian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void showdata() throws SQLException {
        String sql = "SELECT * FROM pengembalian";
        try {
            pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
            tablepengembalian = new DefaultTableModel();
            tablepengembalian.addColumn("id pengembalian");
            tablepengembalian.addColumn("id pinjam");
            tablepengembalian.addColumn("nama pinjam");
            tablepengembalian.addColumn("tanggal");
            tablepengembalian.addColumn("biaya pengembalian");
            tablepengembalian.addColumn("administrasi");
         while(rs.next()) {
           tablepengembalian.addRow(new Object[]{
           rs.getString(1),
           rs.getString(2),
           rs.getString(3),
           rs.getString(4),
           rs.getString(5),
           rs.getString(6)
           });
        }
        jTable1.setModel(tablepengembalian);
        } catch (SQLException ex) {
            Logger.getLogger(Pengembalian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void reset(){
    jTextField1.setText("");
    jTextField2.setText("");
    jComboBox1.setSelectedItem("");
    jTextField3.setText("");
    jTextField4.setText("");
    jTextField5.setText("");
    jTextField5.setText("");
    
    }
     private void nokategori() throws SQLException {
        conn = Koneksi.getConnection();
        try {
            rs = conn.createStatement().executeQuery("select * from pengembalian order by id_pengembalian desc");
            if (rs.next()) {
                String nofak = rs.getString("id_pengembalian").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "0000";
                } else if (AN.length() == 2) {
                    Nol = "000";
                } else if (AN.length() == 3) {
                    Nol = "00";
                } else if (AN.length() == 4) {
                    Nol = "0";
                } else if (AN.length() == 5) {
                    Nol = "";
                }

                jTextField1.setText("P" + Nol + AN);
            } else {
                jTextField1.setText("P00001");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void tampildata() throws SQLException {
    conn=Koneksi.getConnection();
    //jTextField3.setText("");
        try {
            rs=conn.createStatement().executeQuery("select id from pinjam");
            while (rs.next()){
             Object[] ob = new Object[3];
                ob[0] = rs.getString(1);
                jComboBox1.addItem(ob[0]);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Pengembalian.class.getName()).log(Level.SEVERE, null, ex);
        }   
    
    }
    private void tglhariini(){
    Date now = new Date();
        SimpleDateFormat simple = new SimpleDateFormat("dd MMMM YYYY");
         String tgl = simple.format(now);
         jTextField3.setText(tgl);
    }
public void tampildata1() throws SQLException {
        conn = Koneksi.getConnection();
        try {
            rs = conn.createStatement().executeQuery("select nama from pinjam where id='" + jComboBox1.getSelectedItem() + "'");      // disini saya menampilkan NIM, anda dapat menampilkan
            while (rs.next()) {
                Object[] ob = new Object[2];
                ob[0] = rs.getString(1);

                jTextField2.setText((String) ob[0]);
                
            }
            
            //jComboBox1.addActionListener(new ActionListener () {
            //public void actionPerformed(ActionEvent e) {
           
            //}
            //});
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Pengembalian.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
private void hitung(){
int satu = Integer.valueOf(jTextField4.getText());
int tiga = (satu*5)/100;
jTextField5.setText(String.valueOf(tiga));
int hasil = satu+tiga;
jTextField6.setText(String.valueOf(hasil));
if(satu >= 1000000000){
JOptionPane.showMessageDialog(null,"Maaf total pengembalian anda terlalu besar mohon diisi ulang kembali sesuai ketentuan");
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

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jTextField1.setBackground(new java.awt.Color(0, 102, 102));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));

        jTextField2.setBackground(new java.awt.Color(0, 102, 102));
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setBackground(new java.awt.Color(0, 102, 102));
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(0, 102, 102));
        jTextField5.setForeground(new java.awt.Color(255, 255, 255));
        jTextField5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Applikasi Pengembalian pinjaman");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("id pengembalian");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama pinjam");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("tanggal");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("biaya pengembalian");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("administrasi");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Logout");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("id pinjam");

        jComboBox1.setBackground(new java.awt.Color(0, 255, 0));
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total pengembalian");

        jTextField6.setBackground(new java.awt.Color(0, 102, 102));
        jTextField6.setForeground(new java.awt.Color(255, 255, 255));
        jTextField6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));

        jTextField4.setBackground(new java.awt.Color(0, 153, 153));
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(58, 58, 58)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(458, 458, 458))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(jLabel8)
                        .addComponent(jLabel2)
                        .addComponent(jLabel9)))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(41, 41, 41)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3)
                            .addComponent(jTextField5)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(56, 56, 56)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addContainerGap(59, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int baris;
        baris = jTable1.getSelectedRow();

        String click;
        click=(jTable1.getModel().getValueAt(baris, 0).toString());

        String perintah;
        perintah = "Select * From pengembalian Where id_pengembalian='"+click+"'";
        try {
            pst=conn.prepareStatement(perintah);
            rs = pst.executeQuery();
            if(rs.next()){
                String obj1;
                obj1=rs.getString("id_pengembalian");
                jTextField1.setText(obj1);

                String obj4;
                obj4=rs.getString("tanggal");
                jTextField3.setText(obj4);

                String obj5;
                obj5=rs.getString("biaya_pengembalian");
                jTextField4.setText(obj5);
                
                String obj6;
                obj6=rs.getString("administrasi");
                jTextField5.setText(obj6);
                
                String obj7;
                obj7=rs.getString("Total");
                jTextField6.setText(obj7);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Simpan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String perintah = "INSERT INTO pengembalian(id_pengembalian,id_pinjam,nama_peminjam,tanggal,biaya_pengembalian,administrasi,Total) VALUE(?,?,?,?,?,?,?)";
        try {
            conn = Koneksi.getConnection();
            pst = conn.prepareStatement(perintah);
            pst.setString(1, jTextField1.getText());
            pst.setString(2, (String) jComboBox1.getSelectedItem());
            pst.setString(3, jTextField2.getText());
            pst.setString(4, jTextField3.getText());
            pst.setString(5, jTextField4.getText());
            pst.setString(6, jTextField5.getText());
            pst.setString(7, jTextField6.getText());
            pst.execute();
            showdata();
            reset();
            nokategori();
        } catch (SQLException ex) {
            Logger.getLogger(Pengembalian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String perintah = "UPDATE pengembalian SET id_pinjam=?, nama_peminjam=?, tanggal=?, biaya_pengembalian=?, administrasi=?, Total=? Where id_pengembalian=?";
        try {
            conn = Koneksi.getConnection();
            pst = conn.prepareStatement(perintah);
            pst.setString(1, (String) jComboBox1.getSelectedItem());
            pst.setString(2, jTextField2.getText());
            pst.setString(3, jTextField3.getText());
            pst.setString(4, jTextField4.getText());
            pst.setString(5, jTextField5.getText());
            pst.setString(6, jTextField6.getText());
            pst.setString(7, jTextField1.getText());
            pst.execute();
            showdata();
            reset();
            nokategori();
        } catch (SQLException ex) {
            Logger.getLogger(Pengembalian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String perintah2 = "DELETE FROM pengembalian WHERE id_pengembalian= ?";
        try {
            pst=conn.prepareStatement(perintah2);
            pst.setString(1, jTextField1.getText());
            pst.execute();
            showdata();
            reset();
            nokategori();
        } catch (SQLException ex) {
            Logger.getLogger(Pinjam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Home ad = new Home();
        ad.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        Login ad = new Login();
        ad.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        hitung();
    }
    }//GEN-LAST:event_jTextField4KeyPressed

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
            java.util.logging.Logger.getLogger(Pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pengembalian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
