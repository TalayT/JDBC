package com.cydeo.jdbctests.day01;

import java.sql.*;

public class TestOracleConnection {
    public static void main(String[] args) throws SQLException {

        /**
         This is Connection String
         IP, port, username and password will be given in future on job
         */
        String dbUrl = "jdbc:oracle:thin:@54.210.59.197:1521:XE";
        String dbUserName = "hr";
        String dbPassword = "hr";

        // DriverManager class getConnection method is used for make connection with DB
        Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        // Create Statement from Connection to runQueries
        Statement statement = conn.createStatement();

        // using with Statement object we will execute query
        ResultSet rs = statement.executeQuery("select * from regions");

//        // move cursor to first row
//        rs.next();
//
//        // now we are at first row and will get data
//        System.out.println(rs.getInt(1) + "-" + rs.getString("region_name")); // Europe, OR
//        System.out.println(rs.getString(2));// Europe
//
//        // second row
//        rs.next();
//        System.out.println(rs.getInt(1) + "-" + rs.getString(2));
//
//        // third row
//        rs.next();
//        System.out.println(rs.getInt("region_id") + "-" + rs.getString("region_name"));

        while (rs.next()){
            System.out.println(rs.getInt(1) + "-" + rs.getString(2));
        }

        // close Connection
        rs.close();
        statement.close();
        conn.close();

    }
}
