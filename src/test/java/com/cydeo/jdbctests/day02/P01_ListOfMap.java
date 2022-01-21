package com.cydeo.jdbctests.day02;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P01_ListOfMap {

    String dbUrl = "jdbc:oracle:thin:@54.210.59.197:1521:XE";
    String dbUserName = "hr";
    String dbPassword = "hr";

    @Test
    public void task1() throws SQLException {


        Map<String, Object> rowMap1 = new HashMap<>();
        rowMap1.put("first_name", "Steven");
        rowMap1.put("last_name", "King");
        rowMap1.put("salary", 2400);

        System.out.println("rowMap1 = " + rowMap1);
        ;

        Map<String, Object> rowMap2 = new HashMap<>();
        rowMap2.put("first_name", "Neena");
        rowMap2.put("last_name", "Kochhar");
        rowMap2.put("salary", 1700);

        System.out.println("rowMap2 = " + rowMap2);


        List<Map<String, Object>> dataList = new ArrayList<>();
        dataList.add(rowMap1);
        dataList.add(rowMap2);


        // get last name of Steven
        System.out.println(dataList.get(0).get("last_name"));
    }

    @Test
    public void task2() throws SQLException {

        Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = statement.executeQuery("select FIRST_NAME,LAST_NAME,SALARY  from EMPLOYEES where ROWNUM<6");
        ResultSetMetaData rsmd = rs.getMetaData();

        //move to first row
        rs.next();

        Map<String, Object> rowMap1 = new HashMap<>();
        rowMap1.put(rsmd.getColumnName(1), rs.getString(1));
        rowMap1.put(rsmd.getColumnName(2), rs.getString(2));
        rowMap1.put(rsmd.getColumnName(3), rs.getString(3));

        System.out.println("rowMap1 = " + rowMap1);
        ;

        //move to second row
        rs.next();

        Map<String, Object> rowMap2 = new HashMap<>();
        rowMap2.put(rsmd.getColumnName(1), rs.getString(1));
        rowMap2.put(rsmd.getColumnName(2), rs.getString(2));
        rowMap2.put(rsmd.getColumnName(3), rs.getString(3));

        System.out.println("rowMap2 = " + rowMap2);


        List<Map<String, Object>> dataList = new ArrayList<>();
        dataList.add(rowMap1);
        dataList.add(rowMap2);


        // get last name of Steven
        System.out.println(dataList.get(0).get(rsmd.getColumnName(2)));
    }

    @Test
    public void task3() throws SQLException {

        Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = statement.executeQuery("select FIRST_NAME,LAST_NAME,SALARY  from EMPLOYEES where ROWNUM<6");
        ResultSetMetaData rsmd = rs.getMetaData();

        // get columnCount
        int columnCount = rsmd.getColumnCount();

        // to store all rows we need List of Map
        List<Map<String, Object>> dataList = new ArrayList<>();

        // iterate each row dynamically
        while (rs.next()) {

            Map<String, Object> rowMap = new HashMap<>();

            // to fill this map we need logic
            // move left -> right

            for (int i = 1; i <= columnCount; i++) { // runs $columnCount

                rowMap.put(rsmd.getColumnName(i), rs.getString(i));
            }

            // add ready map into List of Map
            dataList.add(rowMap);
        }

        System.out.println("======= PRINT OUT EACH ROW MAP ======== ");

        for (Map<String, Object> eachRowMap : dataList) {

            System.out.println(eachRowMap);

            rs.close();
            statement.close();
            conn.close();
        }
    }
}
