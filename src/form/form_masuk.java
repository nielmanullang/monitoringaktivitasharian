/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import koneksi.koneksi;

/**
 *
 * @author danielmanullang
 */
public class form_masuk extends javax.swing.JFrame {

    private final Connection con = new koneksi().connect();
    private Statement st;
    private ResultSet rs;
    private String sql = "";
    public static Integer user_id;
    private String tasklist, start_time, start_time_hidden;

    /**
     * @param user_id
     * @throws SQLException
     * Creates new form form_masuk
     */
    public form_masuk(Integer user_id) throws SQLException {
        initComponents();
        setUserId(user_id);
        System.out.println("user_id " + user_id);
        sql = "SELECT * FROM users where id='" + user_id + "'";
        st = con.createStatement();
        rs = st.executeQuery(sql);
        if (rs.first()) {
            tf_name.setText(rs.getString("name"));
            tf_name.setEditable(false);
            tf_npk.setText(rs.getString("npk"));
            tf_npk.setEditable(false);
            tf_start_time.setText(new SimpleDateFormat("dd MMM yyyy hh:mm ").format(new Date()) + "WIB");
            start_time_hidden = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ").format(new Date());
            tf_start_time.setEditable(false);
        }
    }

    public static void setUserId(Integer user_id) {
        form_masuk.user_id = user_id;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tf_tasklist = new javax.swing.JTextArea();
        tf_name = new javax.swing.JTextField();
        tf_npk = new javax.swing.JTextField();
        tf_start_time = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btn_submit = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 400));

        jLabel1.setText("NPK");

        jLabel2.setText("Nama");

        jLabel4.setText("Start Time");

        jLabel5.setText("Tasklist");

        tf_tasklist.setColumns(20);
        tf_tasklist.setRows(5);
        jScrollPane1.setViewportView(tf_tasklist);

        tf_name.setText("Nama");

        tf_npk.setText("NPK");
        tf_npk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_npkActionPerformed(evt);
            }
        });

        tf_start_time.setText("Start Time");
        tf_start_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_start_timeActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel6.setText("Form Masuk");

        btn_submit.setText("Submit");
        btn_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(338, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(269, 269, 269))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_submit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_npk, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_name, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                            .addComponent(tf_start_time, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_npk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tf_start_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_submit)
                    .addComponent(btn_cancel))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel4, jLabel5});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_start_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_start_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_start_timeActionPerformed

    private void tf_npkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_npkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_npkActionPerformed

    private void btn_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitActionPerformed
        // TODO add your handling code here:
        tasklist = String.valueOf(tf_tasklist.getText());
        if (tasklist == null) {
            JOptionPane.showMessageDialog(null, "Maaf, tasklist belum diinput");
        }
        try {
            sql = "INSERT INTO activities (start_time, tasklist, user_id) "
                    + "VALUE ('" + start_time_hidden + "','" + tasklist + "'," + user_id + ")";
            System.out.println("sql" + sql);
            st = con.createStatement();
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            menu_utama m = new menu_utama(user_id);
            m.setVisible(true);
            dispose();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal disimpan \n" + e.getMessage());
        }
    }//GEN-LAST:event_btn_submitActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        try {
            // TODO add your handling code here:
            menu_utama m = new menu_utama(user_id);
            m.setVisible(true);
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(form_masuk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_cancelActionPerformed

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
    //            java.util.logging.Logger.getLogger(form_masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        } catch (InstantiationException ex) {
    //            java.util.logging.Logger.getLogger(form_masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        } catch (IllegalAccessException ex) {
    //            java.util.logging.Logger.getLogger(form_masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
    //            java.util.logging.Logger.getLogger(form_masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        }
    //        //</editor-fold>
    //
    //        /* Create and display the form */
    //        java.awt.EventQueue.invokeLater(new Runnable() {
    //            public void run() {
    //                try {
    //                    new form_masuk().setVisible(true);
    //                } catch (SQLException ex) {
    //                    Logger.getLogger(form_masuk.class.getName()).log(Level.SEVERE, null, ex);
    //                }
    //            }
    //        });
    //    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_submit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tf_name;
    private javax.swing.JTextField tf_npk;
    private javax.swing.JTextField tf_start_time;
    private javax.swing.JTextArea tf_tasklist;
    // End of variables declaration//GEN-END:variables
}
