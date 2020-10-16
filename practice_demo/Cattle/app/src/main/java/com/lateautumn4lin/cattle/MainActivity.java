package com.lateautumn4lin.cattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        启动sqlite studio服务
        SQLiteStudioService.instance().start(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SQLiteStudioService.instance().stop();
    }

    public void to_sqlite(View view){
        Intent intent = new Intent(MainActivity.this, SQLiteCattleActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void to_sqlcipher(View view){
        Intent intent = new Intent(MainActivity.this, SQLcipherCattleActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
