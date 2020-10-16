package com.lateautumn4lin.cattle.sqliteCattle;
/*
 * MySQLiteOpenHelper
 *
 * @author lateautumn4lin
 * @github https://github.com/lateautumn4lin
 * @date 2020/10/15 11:26
 */

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

/**
 * The type My sq lite open helper.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    /**
     * The constant FILE_DIR.
     */
    public static final String FILE_DIR = Environment.getExternalStorageDirectory().getPath() + "/SQLiteTest/";
    /**
     * The constant DATABASE_VERSION.
     */
    public static final int DATABASE_VERSION = 1;
    /**
     * The constant TABLE_NAME.
     */
    public static final String TABLE_NAME = "test";

    /**
     * Instantiates a new My sq lite open helper.
     *
     * @param context the context
     * @param name    the name
     */
    public MySQLiteOpenHelper(Context context, String name) {
        this(context, name, null, DATABASE_VERSION);
    }

    /**
     * Instantiates a new My sq lite open helper.
     *
     * @param context the context
     * @param name    the name
     * @param factory the factory
     * @param version the version
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        this(context, name, factory, version, null);
    }

    /**
     * Instantiates a new My sq lite open helper.
     *
     * @param context      the context
     * @param name         the name
     * @param factory      the factory
     * @param version      the version
     * @param errorHandler the error handler
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        File dir = new File(FILE_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }

        try {
            db.execSQL("create table if not exists " + TABLE_NAME +
                    "(id text primary key,name text)");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(sql);
            onCreate(db);
        }
    }
}
