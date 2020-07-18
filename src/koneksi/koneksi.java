/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vinzzah
 */
public class koneksi {
    private Connection con;
    public Connection connect() {
       try {
           Class.forName("com.mysql.jdbc.Driver");
           System.out.println("KONEKSI BERHASIL");
       } catch (ClassNotFoundException e) {
           System.out.println("KONEKSI GAGAL" +e);
       }
       String url = "jdbc:mysql://localhost:3306/db_monitoring_aktivitas_harian";
       try {
           con = DriverManager.getConnection(url, "root", "");
           System.out.println("KONEKSI DATABASE BERHASIL");
       } catch (SQLException e) {
           System.out.println("GAGAL KONEKSI DATABASE" +e);
       }
       return con;
    }
    
}
