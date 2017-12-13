/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasisimpanpinjam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author DIDIN
 */
public class Koneksi {
    public static final String url = "jdbc:mysql://localhost:3306/didinnuryahya";
    public static final String username = "root";
    public static final String password = "";
    
    public static Connection getConnection()throws SQLException{
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url,username,password);
            System.out.println("koneksi berhasil");
        } catch (ClassNotFoundException ex) {
            System.out.println("koneksi gagal");
        }
        return connect;
    }
}