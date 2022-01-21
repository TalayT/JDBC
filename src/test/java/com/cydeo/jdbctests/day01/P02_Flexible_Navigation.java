package com.cydeo.jdbctests.day01;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class P02_Flexible_Navigation {

    String dbUrl = "jdbc:oracle:thin:@54.210.59.197:1521:XE";
    String dbUserName = "hr";
    String dbPassword = "hr";

    @Test
    public void task1() throws SQLException {

        Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = statement.executeQuery("select first_name, last_name from employees");

        // first row
        rs.next();
        System.out.println(rs.getString(1) + " " + rs.getString(2) );

        // second row
        rs.next();
        System.out.println(rs.getString(1) + " " + rs.getString(2) );

        // last row
        rs.last();
        System.out.println(rs.getString(1) + " " + rs.getString(2) );
        /*
        ResultSet.TYPE_SCROLL_INSENSITIVE --> to be able to scroll cursor or to jump directly in a certain row
        ResultSet.CONCUR_READ_ONLY --> means we don't want to change anything in database; makes the object immutable
         */

        // how to find how many row do we have
        int row = rs.getRow();
        System.out.println(row);// 107

        // jump exactly to row 8
        rs.absolute(8);
        row = rs.getRow();
        System.out.println(row);// 8
        System.out.println(rs.getString(1) + " " + rs.getString(2) );// Elizabeth Bates

        // go back to previous row
        rs.previous();
        row = rs.getRow();
        System.out.println(row);// 7
        System.out.println(rs.getString(1) + " " + rs.getString(2) );// Amit Banda

        //last
        rs.last();
        row = rs.getRow();
        System.out.println(row);


        rs.beforeFirst(); // jump back to table beginning before first row

        // As long, we are at the end of the table (107), to print ALL data from table,
        // we need to jump back to table beginning using beforeFirst command
        while(rs.next()){
            System.out.println(rs.getString(1) + " " + rs.getString(2) );
        }

        rs.first(); // jump to the first row
        System.out.println(rs.getRow()); // 1


        rs.close();
        statement.close();
        conn.close();
    }
}
