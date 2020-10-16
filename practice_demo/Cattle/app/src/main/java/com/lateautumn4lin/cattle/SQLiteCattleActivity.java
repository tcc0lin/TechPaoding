package com.lateautumn4lin.cattle;
/*
 * SQLiteCattleActivity
 *
 * @author lateautumn4lin
 * @github https://github.com/lateautumn4lin
 * @date 2020/10/15 11:23
 */

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.lateautumn4lin.cattle.sqliteCattle.MySQLiteOpenHelper;

/**
 * The type Sq lite cattle activity.
 */
public class SQLiteCattleActivity extends AppCompatActivity {
    private SQLiteDatabase database;

    /**
     * The constant DATABASE_NAME.
     */
    @SuppressLint("SdCardPath")
    public static final String DATABASE_NAME = "/data/data/com.lateautumn4lin.cattle/databases/test.db";
    private static final int CODE_PERMISSION_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_cattle);
//        判断是否具备存储权限以及重建db
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //申请写入权限
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, CODE_PERMISSION_REQUEST);
            Logger.logi("存储申请权限");
        } else {
            createDB();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // requestCode即所声明的权限获取码，在checkSelfPermission时传入
        if (requestCode == CODE_PERMISSION_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                createDB();
            }
        }
    }

    /**
     * Click back.
     *
     * @param view the view
     */
    public void  clickBack(View view){
        Intent intent = new Intent(SQLiteCattleActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    /**
     * Click event.
     *
     * @param view the view
     */
    public void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                if (database != null) {
                    ContentValues values = new ContentValues();
                    values.put("id", "1");
                    values.put("name", "name1");
                    database.insert("test", null, values);
                    database.execSQL("insert into test(id, name) values(2, 'name2')");
                }
                break;
            case R.id.btn_delete:
                if (database != null) {
                    //添加多条数据用来测试
                    database.execSQL("insert into test(id, name) values(3, 'name3')");
                    database.execSQL("insert into test(id, name) values(4, 'name4')");
                    database.execSQL("insert into test(id, name) values(5, 'name5')");
                    String whereClause = "id=?";
                    String[] whereArgs = {"3"};
                    database.delete("test", whereClause, whereArgs);
                    database.execSQL("delete from test where name = 'name4'");
                }
                break;
            case R.id.btn_update:
                if (database != null) {
                    ContentValues values = new ContentValues();
                    values.put("name", "update2");
                    String whereClause = "id=?";
                    String[] whereArgs = {"2"};
                    database.update("test", values, whereClause, whereArgs);
                    database.execSQL("update test set name = 'update5' where id = 5");
                }
                break;
            case R.id.btn_query:
                if(database!=null){
                    @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM test", null);
                    while (cursor.moveToNext()){
                        String id = cursor.getString(0);
                        String name=cursor.getString(1);
                        Logger.logi(String.format("Query Result: %s,%s",id,name));
                    }
                }
                break;
        }
    }

    private void createDB() {
        try {
            Logger.logi(String.format("Create Normal Database %s",DATABASE_NAME));
//            两种获取database实例的不同方法
//            方法一
            database = this.openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
//            方法二
            MySQLiteOpenHelper sqLiteOpenHelper = new MySQLiteOpenHelper(this, DATABASE_NAME);
            this.database = sqLiteOpenHelper.getWritableDatabase();
//            获取database实例之后创建表
            database.execSQL("create table if not exists " + "test" +
                    "(id text primary key,name text)");
        } catch (Exception e) {
            Logger.loge(e.toString());
        }
    }
}
