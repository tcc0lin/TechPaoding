package com.lateautumn4lin.cattle.sqliteCattle;
/*
 * MySQLiteOpenHelper
 *
 * @author lateautumn4lin
 * @github https://github.com/lateautumn4lin
 * @date 2020/10/15 11:26
 */

import android.content.Context;
import android.os.Environment;

import net.sqlcipher.DatabaseErrorHandler;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.io.File;

/**
 * The type My sq lite open helper.
 */
public class MySQLCipherOpenHelper extends SQLiteOpenHelper {
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
     * @param factory the factory
     * @param version the version
     */
    public MySQLCipherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
    public MySQLCipherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, (SQLiteDatabaseHook) errorHandler);
    }

    /**
     * Instantiates a new My sql cipher open helper.
     *
     * @param context the context
     * @param name    the name
     */
    public MySQLCipherOpenHelper(Context context, String name) {
        this(context, name, null, DATABASE_VERSION);
        SQLiteDatabase.loadLibs(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
