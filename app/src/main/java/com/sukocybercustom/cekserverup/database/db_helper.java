package com.sukocybercustom.cekserverup.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by suko on 21/12/17.
 */

public class db_helper extends SQLiteOpenHelper {
    private static final String DB_NAME = "db_list";
    private  static final int DB_VERSION = 1;

    public db_helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            String query = "CREATE TABLE t_server (" +
                    "id INTEGER primary key AUTOINCREMENT, " +
                    "nama text null," +
                    "ip_or_host text null," +
                    "port integer null);";
            sqLiteDatabase.execSQL(query);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        
    }
}
