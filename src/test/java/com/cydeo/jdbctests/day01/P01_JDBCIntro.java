package com.cydeo.jdbctests.day01;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class P01_JDBCIntro {

    String dbUrl = "jdbc:oracle:thin:@54.210.59.197:1521:XE";
    String dbUserName = "hr";
    String dbPassword = "hr";

    @Test
    public void task() throws SQLException {

        Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        Statement statement = conn.createStatement();

        ResultSet rs = statement.executeQuery("select * from departments where manager_id is not null");



        rs.close();
        statement.close();
        conn.close();
    }
}
