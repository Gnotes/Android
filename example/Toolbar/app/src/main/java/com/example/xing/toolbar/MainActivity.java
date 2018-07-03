package com.example.xing.toolbar;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "点击Item: ";
        switch (item.getItemId()){
            case R.id.action_settings: msg += "Setting";
            case R.id.action_share: msg += "Share";
            case R.id.action_more: msg += "More";
        }
        // Snackbar 和 Toast 有类似的功能，但提供了简单交互实现
        // 有意思的是第一个参数，需要传递一个View
        // 文档中是这样描述的：The view to find a parent from.从这个view去查找父级视图，简单了解为根据这个View去获取一个Snackbar展示的容器
        Snackbar snackbar = Snackbar.make(toolbar,msg,Snackbar.LENGTH_SHORT);
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("log","你点击啊");
            }
        }).show();
        return true;
    }
}
