/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
    public static final String DEST = "/Users/danielmanullang/simple_table.pdf";

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

        String whereClause = "";
        if (role.equals("KARYAWAN")) {
            if (whereClause.equals("")) {
                whereClause += "a.user_id=" + user_id;
            }
        }
        if (!npk.equals("")) {
            if (whereClause.equals("")) {
                whereClause += "u.npk='" + npk + "'";
            } else {
                whereClause += " AND u.npk = '" + npk + "'";
            }
        }
        if (!name.equals("")) {
            if (whereClause.equals("")) {
                whereClause += "u.name like '%" + name + "%'";
            } else {
                whereClause += " AND u.name like '%" + name + "%'";
            }
        }
        if (tf_start_date.getDate() != null) {
            start_date = (new SimpleDateFormat("yyyy-MM-dd").format(tf_start_date.getDate()));
            if (whereClause.equals("")) {
                whereClause += "a.start_time >= '" + start_date + "'";
            } else {
                whereClause += "AND a.start_time >= '" + start_date + "'";
            }
        }
        if (tf_end_date.getDate() != null) {
            end_date = (new SimpleDateFormat("yyyy-MM-dd").format(tf_end_date.getDate()));
            if (whereClause.equals("")) {
                whereClause += "a.end_time <= '" + end_date + "'";
            } else {
                whereClause += " AND a.end_time <= '" + end_date + "'";
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
        btn_export_excel = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_export_pdf = new javax.swing.JButton();
        tf_start_date = new com.toedter.calendar.JDateChooser();
        tf_end_date = new com.toedter.calendar.JDateChooser();
        btn_import_excel = new javax.swing.JButton();

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

        btn_export_excel.setText("Export to Excel");
        btn_export_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_export_excelActionPerformed(evt);
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

        btn_export_pdf.setText("Export to PDF");
        btn_export_pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_export_pdfActionPerformed(evt);
            }
        });

        btn_import_excel.setText("Import from Excel");
        btn_import_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_import_excelActionPerformed(evt);
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_start_date, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_end_date, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_export_excel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_export_pdf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_import_excel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_npk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_cancel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(btn_logout))
                                    .addComponent(tf_start_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(btn_export_pdf))
                            .addComponent(btn_export_excel))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_end_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(btn_exit))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_search)
                        .addComponent(btn_import_excel)))
                .addGap(52, 52, 52)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
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

    private void btn_export_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_export_excelActionPerformed
        // TODO add your handling code here:
        npk = tf_npk.getText();
        name = tf_name.getText();
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
        if (tf_start_date.getDate() != null) {
            start_date = (new SimpleDateFormat("yyyy-MM-dd").format(tf_start_date.getDate()));
            if (whereClause.equals("")) {
                whereClause += "start_time >= '" + start_date + "'";
            } else {
                whereClause += "AND start_time >= '" + start_date + "'";
            }
        }
        if (tf_end_date.getDate() != null) {
            end_date = (new SimpleDateFormat("yyyy-MM-dd").format(tf_end_date.getDate()));
            if (whereClause.equals("")) {
                whereClause += "end_time <= '" + end_date + "'";
            } else {
                whereClause += " AND end_time <= '" + end_date + "'";
            }
        }
        if (!whereClause.equals("")) {
            sql = "SELECT u.npk,u.name,  a.tasklist, a.result, a.start_time, a.end_time "
                    + "FROM activities a INNER JOIN users u on u.id=a.user_id where " + whereClause;
        } else {
            sql = "SELECT u.npk, u.name, a.tasklist, a.result, a.start_time, a.end_time "
                    + "FROM activities a INNER JOIN users u on u.id=a.user_id";
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("[B]export_output/excel[/B]"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                st = con.createStatement();
                rs = st.executeQuery(sql);

                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("report");
                HSSFRow rowhead = sheet.createRow((short) 0);

                rowhead.createCell((short) 0).setCellValue("NPK");
                rowhead.createCell((short) 1).setCellValue("Name");
                rowhead.createCell((short) 2).setCellValue("Start");
                rowhead.createCell((short) 3).setCellValue("End");
                rowhead.createCell((short) 4).setCellValue("Tasklist");
                rowhead.createCell((short) 5).setCellValue("Result");
                int i = 1;
                while (rs.next()) {
                    HSSFRow row = sheet.createRow((short) i);
                    row.createCell((short) 0).setCellValue(rs.getString("npk"));
                    row.createCell((short) 1).setCellValue(rs.getString("name"));
                    row.createCell((short) 2).setCellValue(rs.getString("start_time"));
                    row.createCell((short) 3).setCellValue(rs.getString("end_time"));
                    row.createCell((short) 4).setCellValue(rs.getString("tasklist"));
                    row.createCell((short) 5).setCellValue(rs.getString("result"));
                    i++;
                }

                //write this workbook to an Outputstream.
                try (FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile() + ".xls")) {
                    //write this workbook to an Outputstream.
                    workbook.write(fileOut);
                    fileOut.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(form_laporan.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(form_laporan.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(form_laporan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_export_excelActionPerformed

    private void tf_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nameKeyPressed
        // TODO add your handling code here:
        int tekanenter = evt.getKeyCode();
        if (tekanenter == 10) {
            searchData();
        }
    }//GEN-LAST:event_tf_nameKeyPressed

    private void btn_export_pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_export_pdfActionPerformed
        // TODO add your handling code here:
        try {
            npk = tf_npk.getText();
            name = tf_name.getText();
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
            if (tf_start_date.getDate() != null) {
                start_date = (new SimpleDateFormat("yyyy-MM-dd").format(tf_start_date.getDate()));
                if (whereClause.equals("")) {
                    whereClause += "start_time >= '" + start_date + "'";
                } else {
                    whereClause += "AND start_time >= '" + start_date + "'";
                }
            }
            if (tf_end_date.getDate() != null) {
                end_date = (new SimpleDateFormat("yyyy-MM-dd").format(tf_end_date.getDate()));
                if (whereClause.equals("")) {
                    whereClause += "end_time <= '" + end_date + "'";
                } else {
                    whereClause += " AND end_time <= '" + end_date + "'";
                }
            }
            if (!whereClause.equals("")) {
                sql = "SELECT u.npk,u.name,  a.tasklist, a.result, a.start_time, a.end_time "
                        + "FROM activities a INNER JOIN users u on u.id=a.user_id where " + whereClause;
            } else {
                sql = "SELECT u.npk, u.name, a.tasklist, a.result, a.start_time, a.end_time "
                        + "FROM activities a INNER JOIN users u on u.id=a.user_id";
            }
            st = con.createStatement();
            rs = st.executeQuery(sql);

            File file = new File(DEST);
            file.getParentFile().mkdirs();
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(DEST));
            document.open();
            PdfPTable table = new PdfPTable(6);
            table.addCell("NPK");
            table.addCell("Name");
            table.addCell("Start Time");
            table.addCell("End Time");
            table.addCell("Tasklist");
            table.addCell("Result");
            while (rs.next()) {
                table.addCell(rs.getString("npk"));
                table.addCell(rs.getString("name"));
                table.addCell(rs.getString("start_time"));
                table.addCell(rs.getString("end_time"));
                table.addCell(rs.getString("tasklist"));
                table.addCell(rs.getString("result"));
            }
            document.add(table);
            document.close();
        } catch (FileNotFoundException | DocumentException | SQLException ex) {
            Logger.getLogger(form_laporan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_export_pdfActionPerformed

    private void btn_import_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_import_excelActionPerformed
        // TODO add your handling code here:
        File excelFile;
        String defaultCurrentDirectoryPath = "/Users";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        excelFileChooser.setDialogTitle("Select Excel File");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excelFileChooser.getSelectedFile();
                InputStream ExcelFileToRead = new FileInputStream(excelFile);
                HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);

                HSSFSheet sheet = wb.getSheetAt(0);
                for (int row = 1; row <= sheet.getLastRowNum(); row++) {
                    HSSFRow excelRow = sheet.getRow(row);

                    HSSFCell excelNpk = excelRow.getCell(0);
                    HSSFCell excelName = excelRow.getCell(1);
                    HSSFCell excelStart = excelRow.getCell(2);
                    HSSFCell excelEnd = excelRow.getCell(3);
                    HSSFCell excelTasklist = excelRow.getCell(4);
                    HSSFCell excelResult = excelRow.getCell(5);
                    if (role.equals("ADMIN")) {
                        // Admin upload excel untuk menambahkan activities Karyawan
                        sql = "SELECT * FROM activities a INNER JOIN users u on u.id=a.user_id WHERE u.npk='" + excelNpk + "' and DATE(a.start_time) = '" + excelStart.toString().substring(0, 10) + "'";
                        st = con.createStatement();
                        rs = st.executeQuery(sql);
                        if (rs.first()) {
                            JOptionPane.showMessageDialog(null, "NPK " + excelNpk + " sudah memiliki activities tanggal " + excelStart.toString().substring(0, 10));
                        } else {
                            sql = "INSERT INTO activities (start_time, tasklist, user_id) "
                                    + "SELECT '" + excelStart.toString().substring(0, 19) + "','" + excelTasklist + "',"
                                    + "(SELECT id FROM users where npk =" + excelNpk + ")";
                            st = con.createStatement();
                            st.execute(sql);
                            JOptionPane.showMessageDialog(null, "NPK " + excelNpk + " tanggal " + excelStart.toString().substring(0, 10) + " Data Berhasil disimpan");
                        }
                    } else {
                        // Karyawan upload excel untuk mengupdate result dan end datetime
                        String dateNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                        System.out.println("a " + dateNow);
                        System.out.println("b " + excelEnd.toString().substring(0, 10));
                        if (dateNow.equals(excelEnd.toString().substring(0, 10))) {
                            sql = "SELECT * FROM activities WHERE user_id=" + user_id + " and DATE(start_time) = '" + dateNow + "'";
                            System.out.println("sql1 " + sql);
                            st = con.createStatement();
                            rs = st.executeQuery(sql);
                            if (rs.first() && dateNow.equals(excelEnd.toString().substring(0, 10))) {
                                sql = "UPDATE activities SET result='" + excelResult + "', end_time='" + excelEnd.toString().substring(0, 19)
                                        + "' where user_id = " + user_id + " and DATE(start_time) = '" + dateNow + "'";
                                st = con.createStatement();
                                st.execute(sql);
                                JOptionPane.showMessageDialog(null, "Data Berhasil diupdate");
                            } else {
                                JOptionPane.showMessageDialog(null, "Anda tidak memiliki activity yang Result-nya belum dilengkapi pada hari ini");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Data pada excel row " + row + " tidak memiliki tanggal hari ini");
                        }
                    }
                    searchData();
                }
            } catch (IOException iOException) {
                JOptionPane.showMessageDialog(null, iOException.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(form_laporan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_import_excelActionPerformed

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
    private javax.swing.JButton btn_export_excel;
    private javax.swing.JButton btn_export_pdf;
    private javax.swing.JButton btn_import_excel;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table;
    private com.toedter.calendar.JDateChooser tf_end_date;
    private javax.swing.JTextField tf_name;
    private javax.swing.JTextField tf_npk;
    private com.toedter.calendar.JDateChooser tf_start_date;
    // End of variables declaration//GEN-END:variables
}
