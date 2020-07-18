/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import koneksi.koneksi;

/**
 *
 * @author danielmanullang
 */
public class menu_utama extends javax.swing.JFrame {

    private final Connection con = new koneksi().connect();
    private Statement st;
    private ResultSet rs;
    private String sql = "";
    public static Integer user_id;

    /**
     * Creates new form menu_utama
     */
    public menu_utama(Integer user_id) throws SQLException {
        initComponents();
        setUserId(user_id);
        sql = "SELECT * FROM users where id='" + user_id + "'";
        st = con.createStatement();
        rs = st.executeQuery(sql);
        if (rs.first()) {
            text_name.setText(rs.getString("name"));
            sql = "SELECT * FROM activities where user_id=" + user_id + " and end_time is null";
            System.out.println("sql " + sql);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("rs.first() " + rs.first());
            if (rs.first()) {
                btn_form_masuk.setVisible(false);
            } else {
                btn_form_keluar.setVisible(false);
            }
        }
    }

    public static void setUserId(Integer user_id) {
        menu_utama.user_id = user_id;
    }

    public static Integer getUserId() {
        return user_id;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btn_form_masuk = new javax.swing.JButton();
        btn_form_keluar = new javax.swing.JButton();
        btn_form_laporan = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        text_name = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("APLIKASI MONITORING AKTIVITAS HARIAN");

        btn_form_masuk.setText("Form Masuk");
        btn_form_masuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_form_masukActionPerformed(evt);
            }
        });

        btn_form_keluar.setText("Form Keluar");
        btn_form_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_form_keluarActionPerformed(evt);
            }
        });

        btn_form_laporan.setText("Laporan");
        btn_form_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_form_laporanActionPerformed(evt);
            }
        });

        btn_keluar.setText("Keluar");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });

        text_name.setText("Nama");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_form_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_name)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_form_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_form_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_form_keluar, btn_form_laporan, btn_form_masuk, btn_keluar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(text_name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_form_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_form_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_form_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_form_keluar, btn_form_laporan, btn_form_masuk, btn_keluar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_form_masukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_form_masukActionPerformed
        try {
            // TODO add your handling code here:
            form_masuk m = new form_masuk(user_id);
            m.setVisible(true);
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(menu_utama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_form_masukActionPerformed

    private void btn_form_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_form_keluarActionPerformed
        try {
            // TODO add your handling code here:
            form_keluar k = new form_keluar(user_id);
            k.setVisible(true);
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(menu_utama.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(menu_utama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_form_keluarActionPerformed

    private void btn_form_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_form_laporanActionPerformed
        // TODO add your handling code here:
        form_laporan l = new form_laporan();
        l.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_form_laporanActionPerformed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btn_keluarActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    new menu_utama(user_id).setVisible(true);
//                } catch (SQLException ex) {
//                    Logger.getLogger(menu_utama.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_form_keluar;
    private javax.swing.JButton btn_form_laporan;
    private javax.swing.JButton btn_form_masuk;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel text_name;
    // End of variables declaration//GEN-END:variables
}
