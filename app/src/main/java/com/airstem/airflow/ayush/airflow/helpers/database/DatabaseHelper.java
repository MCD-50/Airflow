package com.airstem.airflow.ayush.airflow.helpers.database;

import java.util.Map;

/**
 * Created by mcd-50 on 14/7/17.
 */

public class DatabaseHelper{
    public static String createTable(String tableName){
        return DatabaseConstant.CREATE_TABLE + DatabaseConstant.SPACE + tableName;
    }

    public static String pushColumns(String table, Map columns){
        String _table = table + "(";
        for (Object o : columns.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            _table += (String) entry.getKey() + DatabaseConstant.SPACE;
            _table += (String) entry.getValue() + DatabaseConstant.COMMA;
        }
        _table += ")";
        return  _table;
    }
}
