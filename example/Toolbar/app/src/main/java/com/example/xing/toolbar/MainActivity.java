package com.example.xing.toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * 应用栏（操作栏）
 * https://developer.android.google.cn/training/appbar/
 *
 * 从 Android 3.0（API 级别 11）开始，所有使用默认主题的 Activity 均使用 ActionBar 作为应用栏。
 * 不过，经过不同 Android 版本的演化，应用栏功能已逐渐添加到原生 ActionBar 中。
 * 因此，原生 ActionBar 的行为会随设备使用的 Android 系统的版本而发生变化。
 * 相比之下，最新功能已添加到支持库版本的 Toolbar 中，并且这些功能可以在任何能够使用该支持库的设备上使用
 *
 * 因此，您应使用支持库的 Toolbar 类来实现 Activity 的应用栏
 *
 * 步骤：
 * 1. 在 build.gradle中 添加 com.android.support:appcompat-v7:*
 * 2. activity 继承自 AppCompatActivity
 * 3. 在清单文件AndroidManifest.xml中的application主题选择NoActionBar的一类主题，
 *    因为使用这类主题，可以防止应用使用原生 ActionBar 类提供应用栏
 * 4. 添加Toolbar
 * 5. setSupportActionBar(toolbar)
 * 如果需要添加返回按钮
 * 6. 在Activity 中添加 android:parentActivityName
 * 7. getSupportActionBar()
 * 8. setDisplayHomeAsUpEnabled(true)
 */

public class MainActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = findViewById(R.id.container_layout);
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
        Snackbar snackbar = Snackbar.make( coordinatorLayout ,msg,Snackbar.LENGTH_SHORT);
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"你点击了UNDO",Toast.LENGTH_SHORT).show();
            }
        }).show();
        return true;
    }

    public void navToToolbar(View view){
        Log.i("tag",view.getId() + "");
        Intent intent = new Intent();
        intent.setClass(this,ToolbarActivity.class);
        startActivity(intent);
    }
}
