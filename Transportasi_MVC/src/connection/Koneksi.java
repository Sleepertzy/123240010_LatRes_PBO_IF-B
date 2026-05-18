/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {

    private static Connection koneksi;

    public static Connection getConnection() {

        try {

            String url = "jdbc:mysql://localhost:3306/transportasi_db";
            String user = "root";
            String password = "";

            DriverManager.registerDriver(
                    new com.mysql.cj.jdbc.Driver()
            );

            koneksi = DriverManager.getConnection(
                    url,
                    user,
                    password
            );

        } catch (Exception e) {

            System.out.println(
                    "Koneksi gagal : " + e.getMessage()
            );
        }

        return koneksi;
    }
}