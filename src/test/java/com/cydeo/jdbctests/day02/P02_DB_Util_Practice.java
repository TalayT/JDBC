package com.cydeo.jdbctests.day02;

import com.cydeo.jdbctests.utility.DB_Util;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class P02_DB_Util_Practice {

    @Test
    public void task1(){

        // create connection
        DB_Util.createConnection();

        // run query
        DB_Util.runQuery("select FIRST_NAME,LAST_NAME,SALARY,JOB_ID  from EMPLOYEES");

        // get All data as LIst of Map
        List<Map<String, String>> allRowAsListOfMap = DB_Util.getAllRowAsListOfMap();

        // print each row into screen

        for (Map<String, String> eachRow: allRowAsListOfMap) {
            System.out.println(eachRow);
        }
        // close connection
        DB_Util.destroy();
    }

    @Test
    public void task2(){

        // create connection
        DB_Util.createConnection();

        // run query
        DB_Util.runQuery("select FIRST_NAME,LAST_NAME,SALARY,JOB_ID  from EMPLOYEES");

        // get first row as Map
        Map<String, String> rowMap = DB_Util.getRowMap(1);
        System.out.println(rowMap);

        // print Steven in first row

        System.out.println(DB_Util.getFirstRowFirstColumn()); // Steven
        System.out.println(DB_Util.getCellValue(1,1)); // Steven

        // Can I run another query? --- YES
        DB_Util.runQuery("select count(*) from employees");

        System.out.println(DB_Util.getFirstRowFirstColumn());// 107
        System.out.println(DB_Util.getCellValue(1,1));


        // close connection
        DB_Util.destroy();

    }
}
