/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import com.mysql.jdbc.StringUtils;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import koneksi.koneksi;

/**
 *
 * @author danielmanullang
 */
public class form_laporan extends javax.swing.JFrame {

    private final Connection con = new koneksi().connect();
    private Statement st;
    private ResultSet rs;
    private String sql = "";
    public static Integer user_id;
    public static String role;
    private String name, npk, start_date, end_date, ext, path, filename;

    /**
     * @param user_id
     * @throws SQLException Creates new form form_report
     */
    public form_laporan(Integer user_id) throws SQLException {
        initComponents();
        setUserId(user_id);
        sql = "select u.*, s.name as role from users u INNER JOIN roles s on s.id=u.role_id where u.id='" + user_id + "'";
        st = con.createStatement();
        rs = st.executeQuery(sql);
        if (rs.first()) {
            setRole(rs.getString("role"));
            TampilData();
        }
    }

    public static void setUserId(Integer user_id) {
        form_laporan.user_id = user_id;
    }

    public static Integer getUserId() {
        return user_id;
    }

    public static void setRole(String role) {
        form_laporan.role = role;
    }

    public static String getRole() {
        return role;
    }

    private void TampilData() {
        System.out.println("role " + role);
        DefaultTableModel datalist = new DefaultTableModel();
        datalist.addColumn("NPK");
        datalist.addColumn("Name");
        datalist.addColumn("Start Time");
        datalist.addColumn("End Time");
        datalist.addColumn("Tasklist");
        datalist.addColumn("Result");
        try {
            if (role.equals("ADMIN")) {
                sql = "SELECT u.name, u.npk, a.tasklist, a.result, a.start_time, a.end_time "
                        + "FROM activities a INNER JOIN users u on u.id=a.user_id";
            } else {
                sql = "SELECT u.name, u.npk, a.tasklist, a.result, a.start_time, a.end_time "
                        + "FROM activities a INNER JOIN users u on u.id=a.user_id WHERE a.user_id=" + user_id;
            }
            System.out.println("sql " + sql);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                datalist.addRow(new Object[]{
                    rs.getString("npk"), rs.getString("name"),
                    rs.getString("start_time"), rs.getString("end_time"),
                    rs.getString("tasklist"), rs.getString("result")
                });
            }
            table.setModel(datalist);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal Tampil \n" + e.getMessage());
        }
    }

    public void searchData() {
        DefaultTableModel datalist = new DefaultTableModel();
        datalist.addColumn("NPK");
        datalist.addColumn("Name");
        datalist.addColumn("Start Time");
        datalist.addColumn("End Time");
        datalist.addColumn("Tasklist");
        datalist.addColumn("Result");

        npk = tf_npk.getText();
        name = tf_name.getText();
        start_date = tf_start_date.getText();
        end_date = tf_end_date.getText();
        String whereClause = "";
        if (role.equals("KARYAWAN")) {
            if (whereClause.equals("")) {
                whereClause += "a.user_id=" + user_id;
            }
        }
        if (!npk.equals("")) {
            if (whereClause.equals("")) {
                whereClause += "npk='" + npk + "'";
            } else {
                whereClause += " AND npk = '" + npk + "'";
            }
        }
        if (!name.equals("")) {
            if (whereClause.equals("")) {
                whereClause += "name like '%" + name + "%'";
            } else {
                whereClause += " AND name like '%" + name + "%'";
            }
        }
        if (!start_date.equals("")) {
            if (whereClause.equals("")) {
                whereClause += "start_date = '" + start_date + "'";
            } else {
                whereClause += "AND start_date = '" + start_date + "'";
            }
        }
        if (!end_date.equals("")) {
            if (whereClause.equals("")) {
                whereClause += "end_time = '" + end_date + "'";
            } else {
                whereClause += " AND end_time = '" + end_date + "'";
            }
        }
        try {
            if (!whereClause.equals("")) {
                sql = "SELECT u.name, u.npk, a.tasklist, a.result, a.start_time, a.end_time "
                        + "FROM activities a INNER JOIN users u on u.id=a.user_id where " + whereClause;
            } else {
                sql = "SELECT u.name, u.npk, a.tasklist, a.result, a.start_time, a.end_time "
                        + "FROM activities a INNER JOIN users u on u.id=a.user_id";
            }
            System.out.println("sql " + sql);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                datalist.addRow(new Object[]{
                    rs.getString("npk"), rs.getString("name"),
                    rs.getString("start_time"), rs.getString("end_time"),
                    rs.getString("tasklist"), rs.getString("result")
                });
            }
            table.setModel(datalist);
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan \n" + e.getMessage());
            tf_npk.requestFocus();
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

        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tf_npk = new javax.swing.JTextField();
        tf_name = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        btn_export = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf_start_date = new javax.swing.JTextField();
        tf_end_date = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "NPK", "Name", "Start Time", "End Time", "Tasklist", "Result"
            }
        ));
        jScrollPane2.setViewportView(table);

        jLabel2.setText("NPK");

        jLabel1.setText("Name");

        tf_npk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_npkKeyPressed(evt);
            }
        });

        tf_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_nameKeyPressed(evt);
            }
        });

        btn_search.setText("Search");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        btn_export.setText("Export");
        btn_export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportActionPerformed(evt);
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

        jLabel3.setText("Start Date");

        jLabel4.setText("End Date");

        tf_start_date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_start_dateKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(92, 92, 92)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tf_name)
                                    .addComponent(tf_npk, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tf_start_date, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_end_date, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_export, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_search)
                    .addComponent(tf_npk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_export)
                    .addComponent(jLabel1)
                    .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_logout))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tf_start_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tf_end_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_exit)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_npkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_npkKeyPressed
        // TODO add your handling code here:
        int tekanenter = evt.getKeyCode();
        if (tekanenter == 10) {
            searchData();
        }
    }//GEN-LAST:event_tf_npkKeyPressed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        // TODO add your handling code here:
        searchData();
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed

        // TODO add your handling code here:
        try {
            menu_utama m = new menu_utama(user_id);
            m.setVisible(true);
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(form_laporan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        // TODO add your handling code here:
        form_login l = new form_login();
        l.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btn_exitActionPerformed

    private void btn_exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportActionPerformed
        // TODO add your handling code here:
        FileWriter fileWriter;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("[B]export_output/excel[/B]"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                TableModel tModel = table.getModel();
                fileWriter = new FileWriter(new File(chooser.getSelectedFile() + ".csv"));
                // write header
                for (int i = 0; i < tModel.getColumnCount(); i++) {
                    fileWriter.write(tModel.getColumnName(i).toUpperCase() + ",");
                }
                fileWriter.write("\n");
                // write record
                for (int i = 0; i < tModel.getRowCount(); i++) {
                    for (int j = 0; j < tModel.getColumnCount(); j++) {
                        fileWriter.write(tModel.getValueAt(i, j).toString() + ",");
                    }
                    fileWriter.write("\n");
                }
                fileWriter.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btn_exportActionPerformed

    private void tf_start_dateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_start_dateKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_start_dateKeyPressed

    private void tf_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nameKeyPressed
        // TODO add your handling code here:
        int tekanenter = evt.getKeyCode();
        if (tekanenter == 10) {
            searchData();
        }
    }//GEN-LAST:event_tf_nameKeyPressed

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
//            java.util.logging.Logger.getLogger(form_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(form_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(form_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(form_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new form_laporan().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_export;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table;
    private javax.swing.JTextField tf_end_date;
    private javax.swing.JTextField tf_name;
    private javax.swing.JTextField tf_npk;
    private javax.swing.JTextField tf_start_date;
    // End of variables declaration//GEN-END:variables
}
