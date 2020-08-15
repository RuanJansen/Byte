package com.example.bytev2;

import java.sql.Connection;
import java.sql.DriverManager;
public class MySQLConnect {
    Connection conn = null;
    public static Connection ConnectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println( "Driver Loaded");
            //Connection conn = DriverManager.getConnection("jdbc:mysql://155.93.254.134:3306/pearsonapp", "ITpeeps", "NZNJ6JJV7zachlinde");//zach
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/pearsonapp","root","");//local
            System.out.println("Database Connected");
            return conn;
        } catch (Exception var1) {
            System.out.println(var1);
            System.out.println("Database Connection Error");
            return null;
        }
    }
}
