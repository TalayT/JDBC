package com.cydeo.jdbctests.day01;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class P03_MetadataTest {

    String dbUrl = "jdbc:oracle:thin:@54.210.59.197:1521:XE";
    String dbUserName = "hr";
    String dbPassword = "hr";

    @Test
    public void task() throws SQLException {

        Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        Statement statement = conn.createStatement();

        ResultSet rs = statement.executeQuery("select * from employees");

        // Database Metadata --> information about database

        DatabaseMetaData dbMetaData = conn.getMetaData();

        System.out.println(dbMetaData.getUserName());
        System.out.println(dbMetaData.getDatabaseProductName());
        System.out.println(dbMetaData.getDatabaseProductVersion());
        System.out.println(dbMetaData.getDriverName());
        System.out.println(dbMetaData.getDriverVersion());

        System.out.println();

        // ResultSet MetaData

        ResultSetMetaData rsmd = rs.getMetaData();

        // how many column do we have
        int columnCount = rsmd.getColumnCount();
        System.out.println(columnCount);// 11

        // how can we learn columnName for second column
        System.out.println(rsmd.getColumnName(2));// first_name


        // print All column name dynamically
        for (int i = 1; i <= columnCount; i++) {
            System.out.println(rsmd.getColumnName(i));
        }

        /**
        rs.next(); --> ti iterate each row
        rs.getString(index) --> to get data from related column

        ResultSet MetaDAta
            rs.getColumnCount() --> to get column numbers count
            rs.getColumnName() --> to get column information / name
         */

        // Print ALL info from table
        while (rs.next()){

            for (int i = 1; i <= columnCount; i++) {
                System.out.println(rsmd.getColumnName(i) + "-" + rs.getString(i));
            }
        }

        rs.close();
        statement.close();
        conn.close();
    }
}
