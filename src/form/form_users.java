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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.TrippleDes;
import koneksi.koneksi;

/**
 *
 * @author danielmanullang
 */
public class form_users extends javax.swing.JFrame {

    private final Connection con = new koneksi().connect();
    private Statement st;
    private ResultSet rs;
    private String sql = "";
    public static Integer user_id;
    private String name, npk, position, username, password, role;

    /**
     * @param user_id
     * @throws SQLException Creates new form form_users
     */
    public form_users(Integer user_id) throws SQLException {
        initComponents();
        TampilData();
        setUserId(user_id);
    }

    public static void setUserId(Integer user_id) {
        form_users.user_id = user_id;
    }

    public static Integer getUserId() {
        return user_id;
    }

    private void TampilData() {
        DefaultTableModel datalist = new DefaultTableModel();
        datalist.addColumn("No");
        datalist.addColumn("NPK");
        datalist.addColumn("Name");
        datalist.addColumn("Username");
        datalist.addColumn("Position");
        datalist.addColumn("Role");
        try {
            int i = 1;
            st = con.createStatement();
            rs = st.executeQuery("select u.*, s.name as role from users u INNER JOIN roles s on s.id=u.role_id");
            while (rs.next()) {
                datalist.addRow(new Object[]{
                    (i++),
                    rs.getString("npk"), rs.getString("name"),
                    rs.getString("username"), rs.getString("position"),
                    rs.getString("role")
                });
            }
            table.setModel(datalist);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal Tampil \n" + e.getMessage());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tf_name = new javax.swing.JTextField();
        tf_npk = new javax.swing.JTextField();
        tf_position = new javax.swing.JTextField();
        tf_username = new javax.swing.JTextField();
        cb_role = new javax.swing.JComboBox<>();
        btn_save = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        tf_password = new javax.swing.JPasswordField();

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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Name");

        jLabel2.setText("NPK");

        jLabel3.setText("Position");

        jLabel4.setText("Username");

        jLabel5.setText("Password");

        jLabel6.setText("Role");

        tf_npk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_npkActionPerformed(evt);
            }
        });
        tf_npk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_npkKeyPressed(evt);
            }
        });

        cb_role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH", "ADMIN", "KARYAWAN" }));
        cb_role.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_roleActionPerformed(evt);
            }
        });

        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        btn_logout.setText("Log Out");
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });

        btn_exit.setText("Exit");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NPK", "Username", "Name", "Position", "Role"
            }
        ));
        table.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                tableMouseWheelMoved(evt);
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(66, 66, 66))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(103, 103, 103)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tf_username, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_position, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_name, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_role, 0, 212, Short.MAX_VALUE)
                            .addComponent(tf_npk, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(tf_password))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_save)
                    .addComponent(tf_npk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_update)
                    .addComponent(jLabel1)
                    .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_delete)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(tf_position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btn_logout)
                    .addComponent(tf_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cb_role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_exit))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btn_exitActionPerformed

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        // TODO add your handling code here:
        form_login l = new form_login();
        l.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        try {
            // TODO add your handling code here:
            menu_utama m = new menu_utama(user_id);
            m.setVisible(true);
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(form_users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void cb_roleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_roleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_roleActionPerformed

    private void Bersih() {
        tf_name.setText("");
        tf_npk.setText("");
        tf_position.setText("");
        tf_username.setText("");
        tf_password.setText("");
        cb_role.setSelectedItem("PILIH");
    }

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        name = String.valueOf(tf_name.getText());
        npk = String.valueOf(tf_npk.getText());
        position = String.valueOf(tf_position.getText());
        username = String.valueOf(tf_username.getText());
        password = String.valueOf(tf_password.getText());
        role = String.valueOf(cb_role.getSelectedItem());
        if (npk.equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Anda belum mengisi field NPK");
        } else if (name.equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Anda belum mengisi field Name");
        } else if (position.equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Anda belum mengisi field Position");
        } else if (username.equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Anda belum mengisi field Username");
        } else if (password.equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Anda belum mengisi field Password");
        } else if (cb_role.getSelectedItem().equals("PILIH")) {
            JOptionPane.showMessageDialog(null, "Maaf, Role belum dipilih");
        } else {
            try {
                sql = "SELECT * FROM users where npk='" + npk + "'";
                st = con.createStatement();
                rs = st.executeQuery(sql);
                if (rs.first()) {
                    JOptionPane.showMessageDialog(null, "Maaf, NPK sudah digunakan");
                } else {
                    TrippleDes td = new TrippleDes();
                    String encryptedPassword = td.encrypt(password);

                    sql = "SELECT * FROM users where username='" + username + "'";
                    st = con.createStatement();
                    rs = st.executeQuery(sql);
                    if (rs.first()) {
                        JOptionPane.showMessageDialog(null, "Maaf, Username sudah digunakan");
                    } else {
                        sql = "INSERT INTO users (name, npk, position, password, role_id, username) "
                                + "SELECT '" + name + "','" + npk + "','" + position + "','" + encryptedPassword + "',"
                                + "(SELECT id FROM roles where name ='"+role+"'),'" + username + "'";
                        st = con.createStatement();
                        st.execute(sql);
                        Bersih();
                        TampilData();
                        JOptionPane.showMessageDialog(null, "Data Berhasil disimpan");
                    }
                }
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal disimpan \n" + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(form_users.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void tf_npkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_npkKeyPressed
        // TODO add your handling code here:
        npk = tf_npk.getText();
        int tekanenter = evt.getKeyCode();
        if (tekanenter == 10) {
            try {
                sql = "select u.*, r.name as role FROM users u inner join roles r on u.role_id=r.id "
                        + "where u.npk='" + npk + "'";
                st = con.createStatement();
                rs = st.executeQuery(sql);
                if (rs.first()) {
                    TrippleDes td = new TrippleDes();
                    String decryptPassword = td.decrypt(rs.getString("password"));
                    tf_name.setText(rs.getString("name"));
                    tf_position.setText(rs.getString("position"));
                    tf_username.setText(rs.getString("username"));
                    tf_password.setText(decryptPassword);
                    tf_password.disable();
                    cb_role.setSelectedItem(rs.getString("role"));
                } else {
                    tf_password.enable();
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
                    tf_npk.requestFocus();
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan \n" + e.getMessage());
                tf_npk.requestFocus();
            } catch (Exception ex) {
                Logger.getLogger(form_users.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tf_npkKeyPressed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        name = String.valueOf(tf_name.getText());
        npk = String.valueOf(tf_npk.getText());
        position = String.valueOf(tf_position.getText());
        username = String.valueOf(tf_username.getText());
        password = String.valueOf(tf_password.getText());
        role = String.valueOf(cb_role.getSelectedItem());
        if (cb_role.getSelectedItem().equals("PILIH")) {
            JOptionPane.showMessageDialog(null, "Maaf, Role belum dipilih");
        } else {
            try {
                sql = "SELECT * FROM users where username='" + username + "' and npk!='" + npk + "'";
                st = con.createStatement();
                rs = st.executeQuery(sql);
                if (rs.first()) {
                    JOptionPane.showMessageDialog(null, "Maaf, Username sudah digunakan");
                } else {
                    TrippleDes td = new TrippleDes();
                    String encryptedPassword = td.encrypt(password);
                    sql = "UPDATE users SET name='" + name + "', position='" + position + "', username='" + username 
                            + "', password='" + encryptedPassword + "', role_id=(SELECT id FROM roles where name ='"+role+"')"
                            + " where npk = '" + npk + "'";
                    st = con.createStatement();
                    st.execute(sql);
                    Bersih();
                    TampilData();
                    JOptionPane.showMessageDialog(null, "Data Berhasil diupdate");
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal diupdate \n" + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(form_users.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        npk = String.valueOf(tf_npk.getText());
        try {
            sql = "DELETE FROM users where npk='" + npk + "'";
            st = con.createStatement();
            st.execute(sql);
            tf_password.enable();
            Bersih();
            TampilData();
            JOptionPane.showMessageDialog(null, "Data Berhasil dihapus");
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal dihapus \n" + e.getMessage());
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        System.out.println("row " + table.rowAtPoint(evt.getPoint()));
    }//GEN-LAST:event_tableMouseClicked

    private void tableMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_tableMouseWheelMoved
        // TODO add your handling code here:
        System.out.println("row " + table.rowAtPoint(evt.getPoint()));
    }//GEN-LAST:event_tableMouseWheelMoved

    private void tf_npkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_npkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_npkActionPerformed

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
//            java.util.logging.Logger.getLogger(form_users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(form_users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(form_users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(form_users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new form_users().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cb_role;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable table;
    private javax.swing.JTextField tf_name;
    private javax.swing.JTextField tf_npk;
    private javax.swing.JPasswordField tf_password;
    private javax.swing.JTextField tf_position;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
